import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooklibrary.databinding.ActivityAddBookBinding
import java.text.SimpleDateFormat
import java.util.*

class AddBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBookBinding
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveBook()
        }
    }

    private fun saveBook() {
        val title = binding.etTitle.text.toString().trim()
        val author = binding.etAuthor.text.toString().trim()
        val isbn = binding.etIsbn.text.toString().trim()
        val purchaseDate = binding.etPurchaseDate.text.toString().trim()

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || purchaseDate.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show()
            return
        }

        val book = Book(
            id = 0, // ID จะถูกสร้างอัตโนมัติ
            title = title,
            author = author,
            isbn = isbn,
            purchaseDate = purchaseDate
        )

        bookViewModel.insert(book)
        Toast.makeText(this, "เพิ่มหนังสือเรียบร้อย", Toast.LENGTH_SHORT).show()
        finish() // ปิดหน้านี้และกลับไปหน้า MainActivity
    }
}
