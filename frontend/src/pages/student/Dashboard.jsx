import { useState, useEffect } from 'react'
import Layout from '../../components/Layout'
import { studentApi } from '../../utils/api'
import { Book, AlertCircle, DollarSign, Heart, Calendar } from 'lucide-react'

const Dashboard = () => {
  const [dashboard, setDashboard] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    loadDashboard()
  }, [])

  const loadDashboard = async () => {
    try {
      const data = await studentApi.getDashboard()
      setDashboard(data)
    } catch (error) {
      console.error('Failed to load dashboard:', error)
    } finally {
      setLoading(false)
    }
  }

  if (loading) {
    return (
      <Layout role="STUDENT">
        <div className="flex items-center justify-center h-64">
          <div className="text-xl text-gray-600">Loading...</div>
        </div>
      </Layout>
    )
  }

  const stats = [
    {
      label: 'Books Issued',
      value: dashboard?.booksIssued || 0,
      icon: Book,
      color: 'bg-blue-500',
    },
    {
      label: 'Due Soon',
      value: dashboard?.upcomingDueDates || 0,
      icon: Calendar,
      color: 'bg-yellow-500',
    },
    {
      label: 'Outstanding Fines',
      value: `₹${dashboard?.outstandingFines || 0}`,
      icon: DollarSign,
      color: 'bg-red-500',
    },
    {
      label: 'Wishlist Items',
      value: dashboard?.wishlistItems || 0,
      icon: Heart,
      color: 'bg-pink-500',
    },
  ]

  return (
    <Layout role="STUDENT">
      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">
            Welcome back, {dashboard?.userName}!
          </h1>
          <p className="text-gray-600 mt-1">Here's what's happening with your library account</p>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {stats.map((stat) => {
            const Icon = stat.icon
            return (
              <div key={stat.label} className="card">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm text-gray-600">{stat.label}</p>
                    <p className="text-2xl font-bold text-gray-900 mt-1">{stat.value}</p>
                  </div>
                  <div className={`${stat.color} p-3 rounded-lg`}>
                    <Icon className="w-6 h-6 text-white" />
                  </div>
                </div>
              </div>
            )
          })}
        </div>

        {/* Current Issues */}
        <div className="card">
          <h2 className="text-xl font-bold text-gray-900 mb-4">Currently Issued Books</h2>
          {dashboard?.currentIssues && dashboard.currentIssues.length > 0 ? (
            <div className="space-y-3">
              {dashboard.currentIssues.map((issue) => (
                <div
                  key={issue.issueId}
                  className="flex items-center justify-between p-4 bg-gray-50 rounded-lg"
                >
                  <div className="flex-1">
                    <h3 className="font-semibold text-gray-900">{issue.bookTitle}</h3>
                    <p className="text-sm text-gray-600">by {issue.bookAuthor}</p>
                  </div>
                  <div className="text-right">
                    <p className="text-sm text-gray-600">Due Date</p>
                    <p className="font-semibold text-gray-900">
                      {new Date(issue.dueDate).toLocaleDateString()}
                    </p>
                    {issue.daysRemaining !== undefined && (
                      <span
                        className={`badge ${
                          issue.statusColor === 'GREEN'
                            ? 'badge-success'
                            : issue.statusColor === 'YELLOW'
                            ? 'badge-warning'
                            : 'badge-danger'
                        } mt-1`}
                      >
                        {issue.daysRemaining > 0
                          ? `${issue.daysRemaining} days left`
                          : `${Math.abs(issue.daysOverdue)} days overdue`}
                      </span>
                    )}
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <p className="text-gray-600 text-center py-8">No books currently issued</p>
          )}
        </div>

        {/* Recent Notifications */}
        <div className="card">
          <h2 className="text-xl font-bold text-gray-900 mb-4">Recent Notifications</h2>
          {dashboard?.recentNotifications && dashboard.recentNotifications.length > 0 ? (
            <div className="space-y-2">
              {dashboard.recentNotifications.slice(0, 5).map((notification) => (
                <div
                  key={notification.notificationId}
                  className={`p-3 rounded-lg ${
                    notification.isRead ? 'bg-gray-50' : 'bg-blue-50'
                  }`}
                >
                  <div className="flex items-start">
                    <AlertCircle className="w-5 h-5 text-blue-500 mr-3 mt-0.5" />
                    <div className="flex-1">
                      <p className="font-medium text-gray-900">{notification.title}</p>
                      <p className="text-sm text-gray-600 mt-1">{notification.message}</p>
                      <p className="text-xs text-gray-500 mt-1">
                        {new Date(notification.sentAt).toLocaleString()}
                      </p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <p className="text-gray-600 text-center py-8">No notifications</p>
          )}
        </div>

        {/* Featured Books */}
        {dashboard?.featuredBooks && dashboard.featuredBooks.length > 0 && (
          <div className="card">
            <h2 className="text-xl font-bold text-gray-900 mb-4">New Arrivals</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
              {dashboard.featuredBooks.slice(0, 4).map((book) => (
                <div key={book.bookId} className="border rounded-lg p-4 hover:shadow-md transition-shadow">
                  <div className="aspect-[3/4] bg-gray-200 rounded-lg mb-3 flex items-center justify-center">
                    <Book className="w-12 h-12 text-gray-400" />
                  </div>
                  <h3 className="font-semibold text-gray-900 text-sm mb-1 line-clamp-2">
                    {book.title}
                  </h3>
                  <p className="text-xs text-gray-600 mb-2">{book.author}</p>
                  <span
                    className={`badge ${
                      book.isAvailable ? 'badge-success' : 'badge-danger'
                    } text-xs`}
                  >
                    {book.isAvailable ? 'Available' : 'Issued'}
                  </span>
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </Layout>
  )
}

export default Dashboard
