# Library Management System - Quick Start Guide

Get the application running in 5 minutes! ⚡

## 🚀 Super Quick Setup (Docker - Recommended)

### Prerequisites
- Docker installed
- Docker Compose installed

### Steps

```bash
# 1. Clone the repository
git clone <repository-url>
cd library-management-system

# 2. Start everything
docker-compose up -d

# 3. Wait 30-60 seconds for services to initialize

# 4. Open browser
http://localhost:3000
```

**That's it!** 🎉

---

## 💻 Manual Setup (Without Docker)

### Prerequisites Check
```bash
java -version    # Need Java 17+
mvn -version     # Need Maven 3.6+
node -version    # Need Node 18+
mysql --version  # Need MySQL 8+
```

### Step 1: Database (2 minutes)
```bash
# Start MySQL and run:
mysql -u root -p

# In MySQL console:
CREATE DATABASE library_db;
EXIT;
```

### Step 2: Backend (2 minutes)
```bash
# Terminal 1
cd backend

# Update src/main/resources/application.properties if needed
# (Change MySQL username/password)

mvn spring-boot:run
```

Wait for: **"Started LibraryManagementSystemApplication"**

### Step 3: Frontend (1 minute)
```bash
# Terminal 2 (new terminal)
cd frontend
npm install
npm run dev
```

### Step 4: Open Application
```
http://localhost:3000
```

---

## 👤 Test Accounts

### Register a Student (via UI)
1. Click "Register here" on login page
2. Fill form with any valid data
3. Example:
   - Email: `student@test.com`
   - Student ID: `STU-2024-00001`
   - Password: `Student@123`

### Create a Librarian (via Database)
```sql
-- Login to MySQL
mysql -u root -p library_db

-- Insert librarian
INSERT INTO users (
    full_name, email, password_hash, mobile, role, status, created_at, updated_at
) VALUES (
    'Admin User', 
    'admin@library.com',
    '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewY5GyYVvMPftHPO',
    '9999999999',
    'LIBRARIAN',
    'ACTIVE',
    NOW(),
    NOW()
);
```

**Login as Librarian:**
- Email: `admin@library.com`
- Password: `Admin@123`

---

## 🔧 Common Commands

### Backend
```bash
# Start
mvn spring-boot:run

# Build
mvn clean install

# Tests
mvn test

# Package
mvn package
```

### Frontend
```bash
# Start dev server
npm run dev

# Build for production
npm run build

# Install dependencies
npm install
```

### Docker
```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Restart a service
docker-compose restart backend

# Remove everything including volumes
docker-compose down -v
```

---

## 📡 API Endpoints

### Test Backend is Running
```bash
curl http://localhost:8080/api/auth/login
# Should return 405 or 400 (means backend is running)
```

### Sample API Calls

**Register:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "john@test.com",
    "mobile": "9876543210",
    "studentId": "STU-2024-00001",
    "password": "Test@123",
    "confirmPassword": "Test@123"
  }'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john@test.com",
    "password": "Test@123"
  }'
```

---

## 🐛 Quick Troubleshooting

### Backend won't start
```bash
# Check if port 8080 is free
# Windows:
netstat -ano | findstr :8080

# Mac/Linux:
lsof -i :8080

# Check MySQL is running
mysql -u root -p
```

### Frontend won't start
```bash
# Clear cache
rm -rf node_modules package-lock.json
npm install

# Check if port 3000 is free
lsof -i :3000  # Mac/Linux
netstat -ano | findstr :3000  # Windows
```

### Can't login
- Verify backend is running (check http://localhost:8080)
- Check browser console for errors (F12)
- Verify database has user records
- Clear browser cache/cookies

### Docker issues
```bash
# Check container status
docker-compose ps

# View specific container logs
docker-compose logs backend
docker-compose logs mysql

# Restart everything
docker-compose restart

# Nuclear option (removes everything)
docker-compose down -v
docker-compose up -d
```

---

## 📱 Port Reference

| Service  | Port | URL |
|----------|------|-----|
| Frontend | 3000 | http://localhost:3000 |
| Backend  | 8080 | http://localhost:8080 |
| MySQL    | 3306 | localhost:3306 |

---

## 📚 Project Structure

```
library-management-system/
├── backend/          # Spring Boot application
│   ├── src/
│   └── pom.xml
├── frontend/         # React application
│   ├── src/
│   └── package.json
├── docker-compose.yml
└── README.md
```

---

## ✅ Verification Checklist

- [ ] Backend running on port 8080
- [ ] Frontend running on port 3000
- [ ] MySQL database created
- [ ] Can open http://localhost:3000
- [ ] Can see login page
- [ ] Can register a new student
- [ ] Can login successfully

---

## 📖 Next Steps

1. ✅ **Read**: [README.md](README.md) for detailed features
2. ✅ **Setup**: [SETUP_GUIDE.md](SETUP_GUIDE.md) for comprehensive setup
3. ✅ **Implementation**: [IMPLEMENTATION_STATUS.md](IMPLEMENTATION_STATUS.md) for development guide

---

## 🆘 Still Having Issues?

1. Check [SETUP_GUIDE.md](SETUP_GUIDE.md) troubleshooting section
2. Verify all prerequisites are installed correctly
3. Check if all ports (3000, 8080, 3306) are available
4. Try the Docker method instead of manual setup
5. Review application logs in console

---

## 💡 Tips

- **Use Docker** for quickest setup
- **Keep terminals open** to see logs
- **Check console** (F12) for frontend errors
- **Use MySQL Workbench** for easier database management
- **Clear browser cache** if UI doesn't update

---

**Ready to code?** Start by exploring the student dashboard! 🎓

**Need help?** Check the comprehensive guides linked above. 📚
