import Layout from '../../components/Layout'

const Wishlist = () => {
  return (
    <Layout role="STUDENT">
      <div>
        <h1 className="text-3xl font-bold text-gray-900 mb-6">My Wishlist</h1>
        <div className="card">
          <p className="text-gray-600">Your wishlist items will be displayed here.</p>
        </div>
      </div>
    </Layout>
  )
}

export default Wishlist
