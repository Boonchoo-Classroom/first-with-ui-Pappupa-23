import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooklibrary.databinding.ActivityEditBookBinding

class EditBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBookBinding
    private val bookViewModel: BookViewModel by viewModels()
    private var bookId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookId = intent.getIntExtra("BOOK_ID", -1)

        if (bookId == -1) {
            Toast.makeText(this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        bookViewModel.allBooks.observe(this) { books ->
            val book = books.find { it.id == bookId }
            book?.let {
                binding.etTitle.setText(it.title)
                binding.etAuthor.setText(it.author)
                binding.etIsbn.setText(it.isbn)
                binding.etPurchaseDate.setText(it.purchaseDate)
            }
        }

        binding.btnUpdate.setOnClickListener { updateBook() }
        binding.btnDelete.setOnClickListener { deleteBook() }
    }

    private fun updateBook() {
        val title = binding.etTitle.text.toString().trim()
        val author = binding.etAuthor.text.toString().trim()
        val isbn = binding.etIsbn.text.toString().trim()
        val purchaseDate = binding.etPurchaseDate.text.toString().trim()

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || purchaseDate.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedBook = Book(bookId, title, author, isbn, purchaseDate)
        bookViewModel.update(updatedBook)

        Toast.makeText(this, "อัปเดตหนังสือเรียบร้อย", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun deleteBook() {
        val book = Book(bookId, "", "", "", "")
        bookViewModel.delete(book)

        Toast.makeText(this, "ลบหนังสือเรียบร้อย", Toast.LENGTH_SHORT).show()
        finish()
    }
}
