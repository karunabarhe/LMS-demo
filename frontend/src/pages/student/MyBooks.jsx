import Layout from '../../components/Layout'

const MyBooks = () => {
  return (
    <Layout role="STUDENT">
      <div>
        <h1 className="text-3xl font-bold text-gray-900 mb-6">My Books</h1>
        <div className="card">
          <p className="text-gray-600">Currently issued books will be displayed here.</p>
        </div>
      </div>
    </Layout>
  )
}

export default MyBooks
