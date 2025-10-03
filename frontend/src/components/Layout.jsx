import { Link, useLocation } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { 
  Home, Book, BookOpen, DollarSign, Heart, Bell, User, 
  LogOut, Users, FileText, Settings, BookMarked 
} from 'lucide-react'

const Layout = ({ children, role }) => {
  const { user, logout } = useAuth()
  const location = useLocation()

  const studentMenuItems = [
    { path: '/student/dashboard', label: 'Dashboard', icon: Home },
    { path: '/student/books', label: 'Book Catalog', icon: Book },
    { path: '/student/my-books', label: 'My Books', icon: BookOpen },
    { path: '/student/fines', label: 'Fines', icon: DollarSign },
    { path: '/student/wishlist', label: 'Wishlist', icon: Heart },
    { path: '/student/notifications', label: 'Notifications', icon: Bell },
    { path: '/student/profile', label: 'Profile', icon: User },
  ]

  const librarianMenuItems = [
    { path: '/librarian/dashboard', label: 'Dashboard', icon: Home },
    { path: '/librarian/books', label: 'Book Management', icon: Book },
    { path: '/librarian/issue-return', label: 'Issue/Return', icon: BookOpen },
    { path: '/librarian/issued-books', label: 'Issued Books', icon: BookMarked },
    { path: '/librarian/students', label: 'Students', icon: Users },
    { path: '/librarian/fines', label: 'Fines', icon: DollarSign },
    { path: '/librarian/wishlist', label: 'Wishlist', icon: Heart },
    { path: '/librarian/reports', label: 'Reports', icon: FileText },
  ]

  const menuItems = role === 'STUDENT' ? studentMenuItems : librarianMenuItems

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Sidebar */}
      <aside className="fixed top-0 left-0 h-full w-64 bg-white shadow-lg z-10">
        <div className="p-6">
          <h1 className="text-2xl font-bold text-primary-600">Library System</h1>
          <p className="text-sm text-gray-600 mt-1">{user?.fullName}</p>
          <p className="text-xs text-gray-500">{user?.role}</p>
        </div>

        <nav className="mt-6">
          {menuItems.map((item) => {
            const Icon = item.icon
            const isActive = location.pathname === item.path
            return (
              <Link
                key={item.path}
                to={item.path}
                className={`flex items-center px-6 py-3 text-gray-700 hover:bg-primary-50 hover:text-primary-600 transition-colors ${
                  isActive ? 'bg-primary-50 text-primary-600 border-r-4 border-primary-600' : ''
                }`}
              >
                <Icon className="w-5 h-5 mr-3" />
                <span>{item.label}</span>
              </Link>
            )
          })}

          <button
            onClick={logout}
            className="flex items-center w-full px-6 py-3 text-gray-700 hover:bg-red-50 hover:text-red-600 transition-colors mt-4"
          >
            <LogOut className="w-5 h-5 mr-3" />
            <span>Logout</span>
          </button>
        </nav>
      </aside>

      {/* Main Content */}
      <main className="ml-64 p-8">
        {children}
      </main>
    </div>
  )
}

export default Layout
