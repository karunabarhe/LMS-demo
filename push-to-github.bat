@echo off
REM Library Management System - GitHub Push Script (Windows)

echo ========================================
echo Library Management System
echo GitHub Push Helper Script
echo ========================================
echo.

REM Check if git is installed
git --version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Git is not installed!
    echo Please install Git from: https://git-scm.com/downloads
    pause
    exit /b 1
)

echo [OK] Git is installed
echo.

REM Check git configuration
for /f "tokens=*" %%i in ('git config --global user.name') do set GIT_USER=%%i
for /f "tokens=*" %%i in ('git config --global user.email') do set GIT_EMAIL=%%i

if "%GIT_USER%"=="" (
    echo [WARNING] Git is not configured yet.
    echo.
    echo Please configure Git first:
    echo git config --global user.name "Your Name"
    echo git config --global user.email "your.email@example.com"
    echo.
    pause
    exit /b 1
)

echo Git Configuration:
echo Name: %GIT_USER%
echo Email: %GIT_EMAIL%
echo.

REM Get GitHub username
echo Step 1: GitHub Repository Information
echo.
set /p GITHUB_USERNAME="Enter your GitHub username: "

if "%GITHUB_USERNAME%"=="" (
    echo [ERROR] GitHub username cannot be empty!
    pause
    exit /b 1
)

set REPO_NAME=library-management-system
echo Repository name: %REPO_NAME%
echo.

set REPO_URL=https://github.com/%GITHUB_USERNAME%/%REPO_NAME%.git
echo Repository URL: %REPO_URL%
echo.

REM Instructions
echo Step 2: Create Repository on GitHub
echo.
echo If you haven't already, create a new repository on GitHub:
echo.
echo 1. Go to: https://github.com/new
echo 2. Repository name: %REPO_NAME%
echo 3. Description: Full-stack Library Management System with React.js and Spring Boot
echo 4. Choose: Public or Private
echo 5. DO NOT initialize with README, .gitignore, or license
echo 6. Click 'Create repository'
echo.
set /p REPO_CREATED="Have you created the repository? (y/n): "

if /i not "%REPO_CREATED%"=="y" (
    echo Please create the repository first, then run this script again.
    pause
    exit /b 0
)

echo.
echo Step 3: Adding Remote and Pushing
echo.

REM Check and remove existing origin
git remote | findstr "^origin$" >nul 2>&1
if not errorlevel 1 (
    echo Remote 'origin' already exists. Removing...
    git remote remove origin
)

REM Add remote
echo Adding remote origin...
git remote add origin "%REPO_URL%"

if errorlevel 1 (
    echo [ERROR] Failed to add remote
    pause
    exit /b 1
)

echo [OK] Remote added successfully
echo.

REM Show remote
echo Remote repositories:
git remote -v
echo.

REM Push to GitHub
echo Step 4: Pushing to GitHub
echo.
echo Pushing to main branch...
echo.

git push -u origin HEAD:main

if errorlevel 1 (
    echo.
    echo [ERROR] Push failed!
    echo.
    echo Common Issues:
    echo.
    echo 1. Authentication Error:
    echo    - Use Personal Access Token instead of password
    echo    - Generate token at: https://github.com/settings/tokens
    echo    - Select 'repo' scope
    echo    - Use token as password when prompted
    echo.
    echo 2. Repository doesn't exist:
    echo    - Make sure you created the repository on GitHub
    echo    - Check the repository name is correct
    echo.
    echo 3. Try manual push:
    echo    git push -u origin main
    echo.
    pause
    exit /b 1
)

echo.
echo ========================================
echo [SUCCESS] Successfully pushed to GitHub!
echo ========================================
echo.
echo Your repository is now available at:
echo https://github.com/%GITHUB_USERNAME%/%REPO_NAME%
echo.
echo Next Steps:
echo 1. Visit your repository on GitHub
echo 2. Add description and topics (tags)
echo 3. Star your own repository
echo 4. Share with others!
echo.
echo Congratulations! Your Library Management System is now on GitHub!
echo.
echo To make future updates:
echo git add .
echo git commit -m "Your commit message"
echo git push
echo.
pause

