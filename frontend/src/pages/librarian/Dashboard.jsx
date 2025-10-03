import Layout from '../../components/Layout'

const LibrarianDashboard = () => {
  return (
    <Layout role="LIBRARIAN">
      <div>
        <h1 className="text-3xl font-bold text-gray-900 mb-6">Librarian Dashboard</h1>
        <div className="card">
          <p className="text-gray-600">Library statistics and metrics will be displayed here.</p>
        </div>
      </div>
    </Layout>
  )
}

export default LibrarianDashboard
