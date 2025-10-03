import Layout from '../../components/Layout'

const Profile = () => {
  return (
    <Layout role="STUDENT">
      <div>
        <h1 className="text-3xl font-bold text-gray-900 mb-6">My Profile</h1>
        <div className="card">
          <p className="text-gray-600">Your profile information will be displayed here.</p>
        </div>
      </div>
    </Layout>
  )
}

export default Profile
