// API utility functions

const API_BASE_URL = '/api'

const getAuthHeaders = () => {
  const token = localStorage.getItem('token')
  return {
    'Content-Type': 'application/json',
    ...(token && { Authorization: `Bearer ${token}` }),
  }
}

const handleResponse = async (response) => {
  if (!response.ok) {
    const error = await response.json().catch(() => ({ message: 'Request failed' }))
    throw new Error(error.message || `HTTP error! status: ${response.status}`)
  }
  return response.json()
}

// Generic API call function
export const apiCall = async (endpoint, options = {}) => {
  const config = {
    headers: getAuthHeaders(),
    ...options,
  }

  const response = await fetch(`${API_BASE_URL}${endpoint}`, config)
  return handleResponse(response)
}

// Student APIs
export const studentApi = {
  getDashboard: () => apiCall('/student/dashboard'),
  searchBooks: (query, category) => {
    const params = new URLSearchParams()
    if (query) params.append('query', query)
    if (category) params.append('category', category)
    return apiCall(`/student/books/search?${params}`)
  },
  getMyBooks: () => apiCall('/student/my-books'),
  requestRenewal: (issueId) => apiCall('/student/books/request-renewal', {
    method: 'POST',
    body: JSON.stringify({ issueId }),
  }),
  getFines: () => apiCall('/student/fines'),
  getNotifications: () => apiCall('/student/notifications'),
  markNotificationAsRead: (id) => apiCall(`/student/notifications/${id}/read`, {
    method: 'PUT',
  }),
  getWishlist: () => apiCall('/student/wishlist'),
  addToWishlist: (book) => apiCall('/student/wishlist', {
    method: 'POST',
    body: JSON.stringify(book),
  }),
  removeFromWishlist: (id) => apiCall(`/student/wishlist/${id}`, {
    method: 'DELETE',
  }),
  getProfile: () => apiCall('/student/profile'),
  updateProfile: (data) => apiCall('/student/profile', {
    method: 'PUT',
    body: JSON.stringify(data),
  }),
}

// Librarian APIs
export const librarianApi = {
  getDashboard: () => apiCall('/librarian/dashboard'),
  getAllBooks: () => apiCall('/librarian/books'),
  addBook: (book) => apiCall('/librarian/books', {
    method: 'POST',
    body: JSON.stringify(book),
  }),
  updateBook: (id, book) => apiCall(`/librarian/books/${id}`, {
    method: 'PUT',
    body: JSON.stringify(book),
  }),
  deleteBook: (id) => apiCall(`/librarian/books/${id}`, {
    method: 'DELETE',
  }),
  issueBook: (data) => apiCall('/librarian/issue', {
    method: 'POST',
    body: JSON.stringify(data),
  }),
  returnBook: (data) => apiCall('/librarian/return', {
    method: 'POST',
    body: JSON.stringify(data),
  }),
  getIssuedBooks: () => apiCall('/librarian/issued-books'),
  getAllStudents: () => apiCall('/librarian/students'),
  getStudentDetails: (id) => apiCall(`/librarian/students/${id}`),
  updateStudent: (id, data) => apiCall(`/librarian/students/${id}`, {
    method: 'PUT',
    body: JSON.stringify(data),
  }),
  getAllFines: () => apiCall('/librarian/fines'),
  markFineAsPaid: (id, data) => apiCall(`/librarian/fines/${id}/pay`, {
    method: 'PUT',
    body: JSON.stringify(data),
  }),
  getAllWishlists: () => apiCall('/librarian/wishlist'),
  updateWishlistStatus: (id, data) => apiCall(`/librarian/wishlist/${id}`, {
    method: 'PUT',
    body: JSON.stringify(data),
  }),
  generateReport: (type, params) => {
    const queryParams = new URLSearchParams(params)
    return apiCall(`/librarian/reports/${type}?${queryParams}`)
  },
}
