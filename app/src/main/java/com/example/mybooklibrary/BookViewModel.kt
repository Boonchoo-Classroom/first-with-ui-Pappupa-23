import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository
    val allBooks: LiveData<List<Book>>

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }

    fun update(book: Book) = viewModelScope.launch {
        repository.update(book)
    }

    fun delete(book: Book) = viewModelScope.launch {
        repository.delete(book)
    }

    fun getBookById(bookId: Int): LiveData<Book> {
        return repository.getBookById(bookId)
    }
}
