import { useState, useEffect } from 'react'
import Layout from '../../components/Layout'
import { studentApi } from '../../utils/api'
import { Search, Book, Heart } from 'lucide-react'

const BookCatalog = () => {
  const [books, setBooks] = useState([])
  const [searchQuery, setSearchQuery] = useState('')
  const [selectedCategory, setSelectedCategory] = useState('')
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    loadBooks()
  }, [])

  const loadBooks = async () => {
    setLoading(true)
    try {
      const data = await studentApi.searchBooks(searchQuery, selectedCategory)
      setBooks(data)
    } catch (error) {
      console.error('Failed to load books:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleSearch = (e) => {
    e.preventDefault()
    loadBooks()
  }

  const handleAddToWishlist = async (book) => {
    try {
      await studentApi.addToWishlist({
        bookTitle: book.title,
        author: book.author,
        isbn: book.isbn,
        publisher: book.publisher,
      })
      alert('Added to wishlist!')
    } catch (error) {
      console.error('Failed to add to wishlist:', error)
      alert('Failed to add to wishlist')
    }
  }

  return (
    <Layout role="STUDENT">
      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Book Catalog</h1>
          <p className="text-gray-600 mt-1">Search and explore our library collection</p>
        </div>

        {/* Search and Filter */}
        <div className="card">
          <form onSubmit={handleSearch} className="space-y-4">
            <div className="flex gap-4">
              <div className="flex-1">
                <div className="relative">
                  <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
                  <input
                    type="text"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    placeholder="Search by title, author, ISBN, or publisher..."
                    className="input pl-10"
                  />
                </div>
              </div>
              <select
                value={selectedCategory}
                onChange={(e) => setSelectedCategory(e.target.value)}
                className="input w-48"
              >
                <option value="">All Categories</option>
                <option value="Fiction">Fiction</option>
                <option value="Non-Fiction">Non-Fiction</option>
                <option value="Science">Science</option>
                <option value="Technology">Technology</option>
                <option value="History">History</option>
                <option value="Biography">Biography</option>
              </select>
              <button type="submit" className="btn btn-primary">
                Search
              </button>
            </div>
          </form>
        </div>

        {/* Books Grid */}
        {loading ? (
          <div className="flex items-center justify-center h-64">
            <div className="text-xl text-gray-600">Loading books...</div>
          </div>
        ) : books.length > 0 ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
            {books.map((book) => (
              <div key={book.bookId} className="card hover:shadow-lg transition-shadow">
                <div className="aspect-[3/4] bg-gray-200 rounded-lg mb-4 flex items-center justify-center">
                  {book.coverImageUrl ? (
                    <img
                      src={book.coverImageUrl}
                      alt={book.title}
                      className="w-full h-full object-cover rounded-lg"
                    />
                  ) : (
                    <Book className="w-16 h-16 text-gray-400" />
                  )}
                </div>

                <h3 className="font-bold text-gray-900 mb-1 line-clamp-2">{book.title}</h3>
                <p className="text-sm text-gray-600 mb-2">{book.author}</p>
                
                <div className="flex items-center justify-between mb-3">
                  <span className="text-xs text-gray-500">{book.category}</span>
                  <span
                    className={`badge ${
                      book.isAvailable ? 'badge-success' : 'badge-danger'
                    } text-xs`}
                  >
                    {book.isAvailable ? 'Available' : 'Not Available'}
                  </span>
                </div>

                {book.description && (
                  <p className="text-sm text-gray-600 mb-4 line-clamp-3">{book.description}</p>
                )}

                <div className="flex gap-2">
                  {book.isAvailable ? (
                    <button className="btn btn-primary flex-1 text-sm">Request Issue</button>
                  ) : (
                    <button
                      onClick={() => handleAddToWishlist(book)}
                      className="btn btn-secondary flex-1 text-sm flex items-center justify-center gap-2"
                    >
                      <Heart className="w-4 h-4" />
                      Add to Wishlist
                    </button>
                  )}
                </div>
              </div>
            ))}
          </div>
        ) : (
          <div className="card text-center py-12">
            <Book className="w-16 h-16 text-gray-300 mx-auto mb-4" />
            <p className="text-xl text-gray-600 mb-2">No books found</p>
            <p className="text-gray-500">Try adjusting your search criteria</p>
          </div>
        )}
      </div>
    </Layout>
  )
}

export default BookCatalog
