import { Navigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

const PrivateRoute = ({ children, role }) => {
  const { user, loading } = useAuth()

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-xl">Loading...</div>
      </div>
    )
  }

  if (!user) {
    return <Navigate to="/login" replace />
  }

  if (role && user.role !== role) {
    // Redirect to appropriate dashboard if role doesn't match
    const redirectPath = user.role === 'STUDENT' ? '/student/dashboard' : '/librarian/dashboard'
    return <Navigate to={redirectPath} replace />
  }

  return children
}

export default PrivateRoute
