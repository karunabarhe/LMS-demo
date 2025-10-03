import Layout from '../../components/Layout'

const IssuedBooks = () => {
  return (
    <Layout role="LIBRARIAN">
      <div>
        <h1 className="text-3xl font-bold text-gray-900 mb-6">Issued Books</h1>
        <div className="card">
          <p className="text-gray-600">All issued books tracking will be displayed here.</p>
        </div>
      </div>
    </Layout>
  )
}

export default IssuedBooks
