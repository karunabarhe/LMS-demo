# Library Management System - Setup Guide

This guide will help you set up and run the Library Management System on your local machine.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Database Setup](#database-setup)
3. [Backend Setup](#backend-setup)
4. [Frontend Setup](#frontend-setup)
5. [Running with Docker](#running-with-docker)
6. [Creating Test Accounts](#creating-test-accounts)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisites

Ensure you have the following installed:

### Required Software
- **Java 17 or higher** - [Download](https://adoptium.net/)
- **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- **Node.js 18+** - [Download](https://nodejs.org/)
- **MySQL 8+** - [Download](https://dev.mysql.com/downloads/mysql/)
- **Git** - [Download](https://git-scm.com/downloads)

### Optional (for Docker)
- **Docker** - [Download](https://www.docker.com/get-started)
- **Docker Compose** - Usually included with Docker Desktop

### Verify Installations

```bash
java -version        # Should show Java 17+
mvn -version         # Should show Maven 3.6+
node -version        # Should show Node 18+
mysql --version      # Should show MySQL 8+
```

---

## Database Setup

### Step 1: Start MySQL

Start your MySQL server:

**Windows:**
```bash
# Start MySQL service
net start MySQL80
```

**macOS/Linux:**
```bash
# Start MySQL service
sudo service mysql start
# or
sudo systemctl start mysql
```

### Step 2: Create Database

Login to MySQL:

```bash
mysql -u root -p
```

Create the database:

```sql
CREATE DATABASE library_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Optional: Create dedicated user
CREATE USER 'library_user'@'localhost' IDENTIFIED BY 'library_password';
GRANT ALL PRIVILEGES ON library_db.* TO 'library_user'@'localhost';
FLUSH PRIVILEGES;

-- Verify database creation
SHOW DATABASES;

-- Exit MySQL
EXIT;
```

### Step 3: Verify Connection

Test the connection:

```bash
mysql -u library_user -p library_db
# Enter password: library_password
```

---

## Backend Setup

### Step 1: Navigate to Backend Directory

```bash
cd backend
```

### Step 2: Configure Database Connection

Open `src/main/resources/application.properties` and update:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library_db?createDatabaseIfNotExist=true
spring.datasource.username=library_user
spring.datasource.password=library_password

# Or if using root user:
# spring.datasource.username=root
# spring.datasource.password=your_root_password
```

### Step 3: Build the Backend

```bash
# Clean and install dependencies
mvn clean install

# This will:
# - Download all dependencies
# - Compile the code
# - Run tests (if any)
# - Create JAR file in target/
```

### Step 4: Run the Backend

```bash
# Start Spring Boot application
mvn spring-boot:run
```

**Expected Output:**
```
Started LibraryManagementSystemApplication in X.XXX seconds
```

The backend will be available at: **http://localhost:8080**

### Step 5: Verify Backend

Open a new terminal and test the API:

```bash
# Test health endpoint (if implemented)
curl http://localhost:8080/actuator/health

# Or simply open in browser
# http://localhost:8080/api/auth/login (should return 405 or method not allowed for GET)
```

---

## Frontend Setup

### Step 1: Navigate to Frontend Directory

Open a **new terminal** (keep backend running) and:

```bash
cd frontend
```

### Step 2: Install Dependencies

```bash
# Install all npm packages
npm install

# This will install:
# - React and ReactDOM
# - Vite
# - Tailwind CSS
# - React Router
# - Lucide React (icons)
# - All other dependencies
```

### Step 3: Start Development Server

```bash
npm run dev
```

**Expected Output:**
```
VITE v5.x.x ready in XXX ms

➜  Local:   http://localhost:3000/
➜  Network: use --host to expose
```

The frontend will be available at: **http://localhost:3000**

### Step 4: Access the Application

Open your browser and navigate to:

**http://localhost:3000**

You should see the **Login Page**.

---

## Running with Docker

If you prefer using Docker (easiest method):

### Step 1: Ensure Docker is Running

```bash
docker --version
docker-compose --version
```

### Step 2: Build and Start All Services

From the project root directory:

```bash
# Build and start all containers
docker-compose up -d

# View logs
docker-compose logs -f

# To stop
docker-compose down
```

This will start:
- **MySQL** on port 3306
- **Backend** on port 8080
- **Frontend** on port 3000

### Step 3: Access the Application

Wait for all services to start (about 30-60 seconds), then open:

**http://localhost:3000**

---

## Creating Test Accounts

### Option 1: Register via UI (Student Account)

1. Open http://localhost:3000
2. Click **"Register here"**
3. Fill in the registration form:
   - Full Name: John Doe
   - Email: john.doe@example.com
   - Mobile: 9876543210
   - Student ID: STU-2024-00001
   - Password: Student@123
   - Confirm Password: Student@123
4. Click **Register**
5. You'll be automatically logged in

### Option 2: Create Librarian Account (Database)

Since librarian accounts cannot be registered via UI, create one manually:

```bash
# Login to MySQL
mysql -u root -p library_db
```

```sql
-- Insert a librarian account
INSERT INTO users (
    full_name, 
    email, 
    password_hash, 
    mobile, 
    role, 
    status, 
    created_at, 
    updated_at
) VALUES (
    'Library Admin',
    'admin@library.com',
    '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewY5GyYVvMPftHPO', -- Password: Admin@123
    '9876543210',
    'LIBRARIAN',
    'ACTIVE',
    NOW(),
    NOW()
);

-- Verify insertion
SELECT * FROM users WHERE role = 'LIBRARIAN';
```

**Librarian Login Credentials:**
- Email: admin@library.com
- Password: Admin@123

### Generate BCrypt Password

If you need to generate a BCrypt hash for a custom password:

```java
// Run this in a Java REPL or create a simple test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
String hashedPassword = encoder.encode("YourPassword@123");
System.out.println(hashedPassword);
```

---

## Troubleshooting

### Backend Issues

#### Problem: Port 8080 already in use
```bash
# Find process using port 8080
# Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# macOS/Linux:
lsof -i :8080
kill -9 <PID>
```

#### Problem: Database connection failed
- Verify MySQL is running
- Check credentials in `application.properties`
- Ensure database `library_db` exists
- Check firewall settings

#### Problem: Maven build fails
```bash
# Clear Maven cache
mvn clean
mvn dependency:purge-local-repository

# Rebuild
mvn clean install -U
```

### Frontend Issues

#### Problem: Port 3000 already in use
Edit `vite.config.js` to change port:

```javascript
export default defineConfig({
  server: {
    port: 3001, // Change to any available port
  }
})
```

#### Problem: npm install fails
```bash
# Clear npm cache
npm cache clean --force

# Delete node_modules and package-lock.json
rm -rf node_modules package-lock.json

# Reinstall
npm install
```

#### Problem: API calls fail (CORS errors)
- Ensure backend is running on port 8080
- Check proxy configuration in `vite.config.js`
- Verify CORS settings in Spring Boot `SecurityConfig.java`

### Docker Issues

#### Problem: Containers won't start
```bash
# Check container status
docker-compose ps

# View logs
docker-compose logs backend
docker-compose logs mysql

# Restart specific service
docker-compose restart backend
```

#### Problem: Database initialization issues
```bash
# Remove volumes and restart
docker-compose down -v
docker-compose up -d
```

---

## Next Steps

1. **Create sample data**: Use the librarian account to add books to the library
2. **Test student features**: Register as a student and explore the catalog
3. **Explore the code**: Check the codebase structure in the README.md
4. **Customize**: Modify business rules in `application.properties`

---

## Additional Commands

### Backend Commands
```bash
# Run tests
mvn test

# Package without tests
mvn package -DskipTests

# Run specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Frontend Commands
```bash
# Build for production
npm run build

# Preview production build
npm run preview

# Run linting
npm run lint
```

### Database Commands
```bash
# Backup database
mysqldump -u root -p library_db > backup.sql

# Restore database
mysql -u root -p library_db < backup.sql

# View tables
mysql -u root -p library_db -e "SHOW TABLES;"
```

---

## Support

If you encounter any issues:

1. Check this troubleshooting guide
2. Review the main README.md
3. Check application logs:
   - Backend: Console output or `logs/` directory
   - Frontend: Browser console (F12)
4. Create an issue in the repository with:
   - Error message
   - Steps to reproduce
   - Your environment details

---

**Happy Coding! 🚀**
