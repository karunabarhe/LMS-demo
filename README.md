# Library Management System

A comprehensive web-based Library Management System built with **React.js** (frontend) and **Spring Boot** (backend), featuring role-based access control for Students and Librarians.

## 🚀 Features

### Student Features
- **Dashboard**: View issued books, due dates, fines, and notifications
- **Book Catalog**: Search and browse books with advanced filters
- **Book Management**: Issue, return, and renew books
- **Fine Tracking**: View and pay library fines
- **Wishlist**: Request books not in the library
- **Notifications**: Receive alerts for due dates, fines, and wishlist updates
- **Profile Management**: Update personal information and preferences

### Librarian Features
- **Dashboard**: View library statistics and metrics
- **Book Management**: Add, edit, and delete books from inventory
- **Issue/Return Processing**: Handle book transactions
- **Student Management**: Manage student accounts
- **Fine Management**: Generate, track, and process fine payments
- **Wishlist Management**: Review and process book requests
- **Reports**: Generate comprehensive reports (issues, fines, inventory, etc.)

## 🛠️ Technology Stack

### Frontend
- React.js 18+
- Vite (Build Tool)
- Tailwind CSS (Styling)
- React Router v6 (Routing)
- Lucide React (Icons)
- Fetch API (HTTP Client)

### Backend
- Spring Boot 3.2.0
- Java 17
- Spring Security + JWT
- Spring Data JPA
- MySQL 8+
- Maven (Build Tool)

## 📋 Prerequisites

Before running the application, ensure you have:

- **Java 17** or higher
- **Node.js 18+** and npm/yarn
- **MySQL 8+**
- **Maven 3.6+**

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd library-management-system
```

### 2. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE library_db;
```

The application will automatically create the required tables on first run.

### 3. Backend Setup

Navigate to the backend directory:

```bash
cd backend
```

Update database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

Build and run the backend:

```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### 4. Frontend Setup

Navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will start on `http://localhost:3000`

## 🐳 Docker Setup (Optional)

Run the entire application using Docker Compose:

```bash
docker-compose up -d
```

This will start:
- MySQL database on port 3306
- Spring Boot backend on port 8080
- React frontend on port 3000

## 📚 API Documentation

### Authentication Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new student |
| POST | `/api/auth/login` | User login |
| POST | `/api/auth/logout` | User logout |
| POST | `/api/auth/forgot-password` | Password recovery |

### Student Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/student/dashboard` | Get dashboard data |
| GET | `/api/student/books/search` | Search books |
| GET | `/api/student/my-books` | Get issued books |
| POST | `/api/student/books/request-renewal` | Request book renewal |
| GET | `/api/student/fines` | Get fine history |
| GET | `/api/student/notifications` | Get notifications |
| GET | `/api/student/wishlist` | Get wishlist |
| POST | `/api/student/wishlist` | Add to wishlist |

### Librarian Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/librarian/dashboard` | Get dashboard metrics |
| GET | `/api/librarian/books` | Get all books |
| POST | `/api/librarian/books` | Add new book |
| PUT | `/api/librarian/books/:id` | Update book |
| DELETE | `/api/librarian/books/:id` | Delete book |
| POST | `/api/librarian/issue` | Issue book |
| POST | `/api/librarian/return` | Return book |
| GET | `/api/librarian/students` | Get all students |
| GET | `/api/librarian/fines` | Get all fines |
| PUT | `/api/librarian/fines/:id/pay` | Mark fine as paid |

## 🔐 Security

- JWT-based authentication
- Role-based access control (RBAC)
- Password encryption using BCrypt
- HTTPS support (production)
- CORS configuration
- XSS and CSRF protection

## 📊 Business Rules

- **Maximum books per student**: 3
- **Issue period**: 14 days
- **Renewal period**: 14 days (max 1 renewal)
- **Fine structure**:
  - ₹5 per day for first 7 days overdue
  - ₹10 per day after 7 days
  - Maximum fine per book: ₹500
- **Grace period**: 1 day
- **Issue restriction**: Cannot issue if outstanding fine > ₹100

## 🗂️ Project Structure

```
library-management-system/
├── backend/
│   ├── src/main/java/com/library/
│   │   ├── controller/       # REST API controllers
│   │   ├── service/          # Business logic
│   │   ├── repository/       # Data access layer
│   │   ├── entity/           # JPA entities
│   │   ├── dto/              # Data transfer objects
│   │   ├── security/         # Security config & JWT
│   │   └── LibraryManagementSystemApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── components/       # Reusable components
│   │   ├── context/          # React context (Auth)
│   │   ├── pages/            # Page components
│   │   │   ├── auth/         # Login, Register
│   │   │   ├── student/      # Student pages
│   │   │   └── librarian/    # Librarian pages
│   │   ├── utils/            # API utilities
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── index.html
│   ├── package.json
│   ├── vite.config.js
│   └── tailwind.config.js
│
├── docker-compose.yml
└── README.md
```

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm run test
```

## 📝 Default Credentials

For testing purposes, you can create a librarian account manually in the database or use the registration system.

**Sample Student Account** (after registration):
- Email: student@example.com
- Password: Student@123

**Sample Librarian Account** (manually create in DB):
- Email: librarian@example.com
- Password: Librarian@123

## 🔄 Future Enhancements

- [ ] Mobile application (React Native)
- [ ] Email notification service integration
- [ ] SMS alerts via Twilio
- [ ] QR code scanning for books
- [ ] Advanced analytics dashboard
- [ ] Multi-library branch support
- [ ] Digital library (e-books)
- [ ] Book reservation queue system
- [ ] Reading history and recommendations
- [ ] Book review and rating system

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👥 Authors

- Your Name - Initial work

## 🆘 Support

For support and queries:
- Create an issue in the repository
- Email: support@library-system.com

## 🙏 Acknowledgments

- Spring Boot Documentation
- React Documentation
- Tailwind CSS
- Lucide React Icons

---

**Built with ❤️ using React.js and Spring Boot**
