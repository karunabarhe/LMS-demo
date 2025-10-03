# 📚 Library Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=for-the-badge&logo=spring)
![React](https://img.shields.io/badge/React-18.2.0-blue?style=for-the-badge&logo=react)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Tailwind CSS](https://img.shields.io/badge/Tailwind-3.3-38B2AC?style=for-the-badge&logo=tailwind-css)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**A modern, full-stack web application for comprehensive library operations management**

[Features](#-features) • [Demo](#-demo) • [Installation](#-installation) • [Documentation](#-documentation) • [Contributing](#-contributing)

</div>

---

## 🌟 Overview

A comprehensive web-based Library Management System built with cutting-edge technologies to automate and streamline library operations. Features separate interfaces for **Students** and **Librarians** with role-based access control, real-time notifications, automated fine calculations, and extensive reporting capabilities.

### 🎯 Perfect For

- 🏫 Educational Institutions
- 📖 Public Libraries
- 🏢 Corporate Libraries
- 👨‍💻 Learning Full-Stack Development
- 💼 Portfolio Projects

---

## ✨ Features

### 👨‍🎓 Student Features

- ✅ **Self-Service Registration** - Quick and easy account creation
- 🔍 **Advanced Book Search** - Search by title, author, ISBN, category with filters
- 📚 **Book Management** - View issued books, due dates, and renewal options
- 💰 **Fine Tracking** - Real-time fine calculation and payment history
- ❤️ **Wishlist System** - Request books not currently in the library inventory
- 🔔 **Smart Notifications** - Due date reminders, fine alerts, and wishlist updates
- 👤 **Profile Management** - Update personal information and preferences

### 👨‍💼 Librarian Features

- 📊 **Comprehensive Dashboard** - Real-time metrics and library statistics
- 📖 **Book Inventory** - Complete CRUD operations for book management
- 🔄 **Issue/Return Processing** - Quick book transactions with validation
- 👥 **Student Management** - Account management and activity tracking
- 💵 **Fine Management** - Automated calculation, payment processing, and waivers
- 📝 **Wishlist Approval** - Review and process student book requests
- 📈 **Report Generation** - Detailed reports with PDF/Excel export

---

## 🛠️ Technology Stack

### Frontend
- **React.js 18+** - Modern UI library
- **Vite** - Lightning-fast build tool
- **Tailwind CSS** - Utility-first CSS framework
- **React Router v6** - Client-side routing
- **Lucide React** - Beautiful icon library

### Backend
- **Spring Boot 3.2** - Enterprise Java framework
- **Java 17** - Latest LTS version
- **Spring Security** - Authentication & authorization
- **JWT** - Stateless authentication
- **Spring Data JPA** - Database abstraction
- **Maven** - Dependency management

### Database
- **MySQL 8+** - Reliable relational database

### DevOps
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Nginx** - Web server for frontend

---

## 🚀 Quick Start

### Option 1: Docker (Recommended)

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/library-management-system.git
cd library-management-system

# Start all services
docker-compose up -d

# Access the application
# Frontend: http://localhost:3000
# Backend: http://localhost:8080
```

### Option 2: Manual Setup

#### Prerequisites
- Java 17+
- Node.js 18+
- MySQL 8+
- Maven 3.6+

#### Backend Setup

```bash
# Navigate to backend
cd backend

# Configure database in src/main/resources/application.properties
# Update username and password

# Run the application
mvn spring-boot:run
```

#### Frontend Setup

```bash
# Navigate to frontend
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

**Detailed setup instructions**: See [SETUP_GUIDE.md](SETUP_GUIDE.md)

---

## 📸 Screenshots

### Student Dashboard
![Student Dashboard](screenshots/student-dashboard.png)

### Book Catalog
![Book Catalog](screenshots/book-catalog.png)

### Librarian Dashboard
![Librarian Dashboard](screenshots/librarian-dashboard.png)

*Add your screenshots to `/screenshots` folder*

---

## 📖 Documentation

Comprehensive documentation is available:

- 📘 [**README.md**](README.md) - Full feature overview and API documentation
- 🚀 [**QUICK_START.md**](QUICK_START.md) - Get running in 5 minutes
- 🔧 [**SETUP_GUIDE.md**](SETUP_GUIDE.md) - Detailed setup with troubleshooting
- 💻 [**IMPLEMENTATION_STATUS.md**](IMPLEMENTATION_STATUS.md) - Development roadmap
- 📋 [**DEVELOPMENT_CHECKLIST.md**](DEVELOPMENT_CHECKLIST.md) - Feature checklist
- 📝 [**PROJECT_SUMMARY.md**](PROJECT_SUMMARY.md) - High-level overview
- 🐙 [**GIT_SETUP.md**](GIT_SETUP.md) - GitHub setup guide

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    React Frontend (Port 3000)                │
│               Vite + Tailwind CSS + React Router             │
└────────────────────────┬────────────────────────────────────┘
                         │ REST API (JSON)
                         │ JWT Authentication
┌────────────────────────▼────────────────────────────────────┐
│                Spring Boot Backend (Port 8080)               │
│          Spring Security + JWT + Spring Data JPA             │
└────────────────────────┬────────────────────────────────────┘
                         │ JDBC
┌────────────────────────▼────────────────────────────────────┐
│                    MySQL Database (Port 3306)                │
│         Users | Books | Issues | Fines | Notifications       │
└─────────────────────────────────────────────────────────────┘
```

---

## 📊 Database Schema

<details>
<summary>Click to expand database structure</summary>

### Core Tables

- **users** - Student and librarian accounts
- **books** - Book inventory with availability tracking
- **issues** - Issued book records with due dates
- **fines** - Fine calculation and payment history
- **wishlist** - Student book requests with voting
- **notifications** - User notification system
- **reservations** - Book reservation queue

</details>

---

## 🔐 Security Features

- 🔒 **JWT Authentication** - Stateless, secure token-based auth
- 🔑 **BCrypt Password Hashing** - Industry-standard encryption (12 rounds)
- 👮 **Role-Based Access Control** - Granular permissions
- 🛡️ **CORS Protection** - Secure cross-origin requests
- 🚫 **XSS Prevention** - Input sanitization
- 💉 **SQL Injection Prevention** - Parameterized queries

---

## 📋 Business Rules

All rules are configurable via `application.properties`:

| Rule | Value | Description |
|------|-------|-------------|
| Max books per student | 3 | Maximum concurrent issues |
| Issue period | 14 days | Standard borrowing period |
| Renewal period | 14 days | Extension period |
| Max renewals | 1 | Number of renewals allowed |
| Fine (first week) | ₹5/day | Initial overdue fine |
| Fine (after week) | ₹10/day | Increased overdue fine |
| Max fine per book | ₹500 | Fine cap |
| Grace period | 1 day | Before fine starts |
| Issue threshold | ₹100 | Max outstanding fine to issue |

---

## 🎨 UI/UX Highlights

- 🎨 Modern, clean design with Tailwind CSS
- 📱 Fully responsive (mobile, tablet, desktop)
- 🎯 Intuitive navigation with sidebar
- 🎨 Color-coded status indicators
- ⚡ Fast load times with Vite
- ♿ Accessible components

---

## 🔄 API Endpoints

### Authentication
```
POST   /api/auth/register    - Register new student
POST   /api/auth/login       - User login
POST   /api/auth/logout      - User logout
```

### Student APIs
```
GET    /api/student/dashboard      - Get dashboard data
GET    /api/student/books/search   - Search books
GET    /api/student/my-books       - Current issues
POST   /api/student/wishlist       - Add to wishlist
GET    /api/student/fines          - Fine history
```

### Librarian APIs
```
GET    /api/librarian/dashboard    - Dashboard metrics
POST   /api/librarian/books        - Add book
POST   /api/librarian/issue        - Issue book
POST   /api/librarian/return       - Return book
GET    /api/librarian/students     - All students
PUT    /api/librarian/fines/:id    - Process payment
```

**Full API documentation**: See [README.md](README.md#-api-documentation)

---

## 🧪 Testing

```bash
# Backend tests
cd backend
mvn test

# Frontend tests
cd frontend
npm test
```

---

## 📦 Deployment

### Docker Deployment
```bash
docker-compose up -d
```

### Manual Deployment
See [SETUP_GUIDE.md](SETUP_GUIDE.md) for production deployment instructions.

---

## 🗺️ Roadmap

- [x] Core authentication system
- [x] Book catalog and search
- [x] Issue/Return workflow
- [ ] Complete fine calculation logic
- [ ] Email notification system
- [ ] SMS alerts integration
- [ ] QR code scanning
- [ ] Mobile application (React Native)
- [ ] Advanced analytics
- [ ] Book recommendations
- [ ] Multi-library support

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/YOUR_USERNAME)

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- React Documentation
- Tailwind CSS
- Lucide Icons
- All contributors and supporters

---

## 📞 Support

- 📧 Email: support@library-system.com
- 🐛 Issues: [GitHub Issues](https://github.com/YOUR_USERNAME/library-management-system/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/YOUR_USERNAME/library-management-system/discussions)

---

## ⭐ Show Your Support

If you like this project, please consider giving it a ⭐ on GitHub!

---

## 📊 Project Stats

![GitHub stars](https://img.shields.io/github/stars/YOUR_USERNAME/library-management-system?style=social)
![GitHub forks](https://img.shields.io/github/forks/YOUR_USERNAME/library-management-system?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/YOUR_USERNAME/library-management-system?style=social)

---

<div align="center">

**Built with ❤️ using React.js and Spring Boot**

Made with passion for learning and sharing knowledge

[⬆ Back to Top](#-library-management-system)

</div>
