import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybooklibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ตั้งค่า RecyclerView
        bookAdapter = BookAdapter(emptyList()) { book ->
            // เมื่อกดหนังสือ จะเปิดหน้าแก้ไข
            val intent = Intent(this, EditBookActivity::class.java).apply {
                putExtra("BOOK_ID", book.id)
            }
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bookAdapter
        }

        // อัปเดตรายการหนังสือ
        bookViewModel.allBooks.observe(this) { books ->
            bookAdapter.updateBooks(books)
        }

        // กดปุ่มเพิ่มหนังสือ
        binding.btnAddBook.setOnClickListener {
            startActivity(Intent(this, AddBookActivity::class.java))
        }
    }
}
