import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider } from './context/AuthContext'
import PrivateRoute from './components/PrivateRoute'

// Auth Pages
import Login from './pages/auth/Login'
import Register from './pages/auth/Register'

// Student Pages
import StudentDashboard from './pages/student/Dashboard'
import BookCatalog from './pages/student/BookCatalog'
import MyBooks from './pages/student/MyBooks'
import Fines from './pages/student/Fines'
import Wishlist from './pages/student/Wishlist'
import Profile from './pages/student/Profile'
import Notifications from './pages/student/Notifications'

// Librarian Pages
import LibrarianDashboard from './pages/librarian/Dashboard'
import BookManagement from './pages/librarian/BookManagement'
import IssueReturn from './pages/librarian/IssueReturn'
import IssuedBooks from './pages/librarian/IssuedBooks'
import StudentManagement from './pages/librarian/StudentManagement'
import FineManagement from './pages/librarian/FineManagement'
import WishlistManagement from './pages/librarian/WishlistManagement'
import Reports from './pages/librarian/Reports'

function App() {
  return (
    <Router>
      <AuthProvider>
        <Routes>
          {/* Public Routes */}
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          
          {/* Student Routes */}
          <Route path="/student/dashboard" element={
            <PrivateRoute role="STUDENT">
              <StudentDashboard />
            </PrivateRoute>
          } />
          <Route path="/student/books" element={
            <PrivateRoute role="STUDENT">
              <BookCatalog />
            </PrivateRoute>
          } />
          <Route path="/student/my-books" element={
            <PrivateRoute role="STUDENT">
              <MyBooks />
            </PrivateRoute>
          } />
          <Route path="/student/fines" element={
            <PrivateRoute role="STUDENT">
              <Fines />
            </PrivateRoute>
          } />
          <Route path="/student/wishlist" element={
            <PrivateRoute role="STUDENT">
              <Wishlist />
            </PrivateRoute>
          } />
          <Route path="/student/notifications" element={
            <PrivateRoute role="STUDENT">
              <Notifications />
            </PrivateRoute>
          } />
          <Route path="/student/profile" element={
            <PrivateRoute role="STUDENT">
              <Profile />
            </PrivateRoute>
          } />
          
          {/* Librarian Routes */}
          <Route path="/librarian/dashboard" element={
            <PrivateRoute role="LIBRARIAN">
              <LibrarianDashboard />
            </PrivateRoute>
          } />
          <Route path="/librarian/books" element={
            <PrivateRoute role="LIBRARIAN">
              <BookManagement />
            </PrivateRoute>
          } />
          <Route path="/librarian/issue-return" element={
            <PrivateRoute role="LIBRARIAN">
              <IssueReturn />
            </PrivateRoute>
          } />
          <Route path="/librarian/issued-books" element={
            <PrivateRoute role="LIBRARIAN">
              <IssuedBooks />
            </PrivateRoute>
          } />
          <Route path="/librarian/students" element={
            <PrivateRoute role="LIBRARIAN">
              <StudentManagement />
            </PrivateRoute>
          } />
          <Route path="/librarian/fines" element={
            <PrivateRoute role="LIBRARIAN">
              <FineManagement />
            </PrivateRoute>
          } />
          <Route path="/librarian/wishlist" element={
            <PrivateRoute role="LIBRARIAN">
              <WishlistManagement />
            </PrivateRoute>
          } />
          <Route path="/librarian/reports" element={
            <PrivateRoute role="LIBRARIAN">
              <Reports />
            </PrivateRoute>
          } />
          
          {/* Default Redirect */}
          <Route path="/" element={<Navigate to="/login" replace />} />
        </Routes>
      </AuthProvider>
    </Router>
  )
}

export default App
