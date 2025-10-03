#!/bin/bash

# Library Management System - GitHub Push Script
# This script helps you push the project to your GitHub repository

echo "========================================"
echo "Library Management System"
echo "GitHub Push Helper Script"
echo "========================================"
echo ""

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Check if git is installed
if ! command -v git &> /dev/null; then
    echo -e "${RED}Error: Git is not installed!${NC}"
    echo "Please install Git from: https://git-scm.com/downloads"
    exit 1
fi

echo -e "${GREEN}✓ Git is installed${NC}"
echo ""

# Check if git is configured
GIT_USER=$(git config --global user.name)
GIT_EMAIL=$(git config --global user.email)

if [ -z "$GIT_USER" ] || [ -z "$GIT_EMAIL" ]; then
    echo -e "${YELLOW}Git is not configured yet.${NC}"
    echo "Please configure Git first:"
    echo ""
    echo -e "${BLUE}git config --global user.name \"Your Name\"${NC}"
    echo -e "${BLUE}git config --global user.email \"your.email@example.com\"${NC}"
    echo ""
    read -p "Press Enter after configuring Git..."
fi

echo -e "${GREEN}Git Configuration:${NC}"
echo "Name: $(git config --global user.name)"
echo "Email: $(git config --global user.email)"
echo ""

# Get GitHub username
echo -e "${YELLOW}Step 1: GitHub Repository Information${NC}"
read -p "Enter your GitHub username: " GITHUB_USERNAME

if [ -z "$GITHUB_USERNAME" ]; then
    echo -e "${RED}Error: GitHub username cannot be empty!${NC}"
    exit 1
fi

# Repository name
REPO_NAME="library-management-system"
echo "Repository name: $REPO_NAME"
echo ""

# Check if repository exists on GitHub
REPO_URL="https://github.com/$GITHUB_USERNAME/$REPO_NAME.git"
echo -e "${BLUE}Repository URL: $REPO_URL${NC}"
echo ""

# Instructions
echo -e "${YELLOW}Step 2: Create Repository on GitHub${NC}"
echo "If you haven't already, create a new repository on GitHub:"
echo ""
echo "1. Go to: https://github.com/new"
echo "2. Repository name: $REPO_NAME"
echo "3. Description: Full-stack Library Management System with React.js and Spring Boot"
echo "4. Choose: Public or Private"
echo "5. DO NOT initialize with README, .gitignore, or license"
echo "6. Click 'Create repository'"
echo ""
read -p "Have you created the repository? (y/n): " REPO_CREATED

if [ "$REPO_CREATED" != "y" ] && [ "$REPO_CREATED" != "Y" ]; then
    echo -e "${YELLOW}Please create the repository first, then run this script again.${NC}"
    exit 0
fi

echo ""
echo -e "${YELLOW}Step 3: Adding Remote and Pushing${NC}"

# Check if remote already exists
if git remote | grep -q "^origin$"; then
    echo "Remote 'origin' already exists. Removing..."
    git remote remove origin
fi

# Add remote
echo "Adding remote origin..."
git remote add origin "$REPO_URL"

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Remote added successfully${NC}"
else
    echo -e "${RED}Error: Failed to add remote${NC}"
    exit 1
fi

# Show remote
echo ""
echo "Remote repositories:"
git remote -v
echo ""

# Push to GitHub
echo -e "${YELLOW}Step 4: Pushing to GitHub${NC}"
echo "Pushing to main branch..."
echo ""

# Try to push
git push -u origin HEAD:main

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}========================================"
    echo "✓ Successfully pushed to GitHub!"
    echo "========================================${NC}"
    echo ""
    echo "Your repository is now available at:"
    echo -e "${BLUE}https://github.com/$GITHUB_USERNAME/$REPO_NAME${NC}"
    echo ""
    echo -e "${GREEN}Next Steps:${NC}"
    echo "1. Visit your repository on GitHub"
    echo "2. Add description and topics (tags)"
    echo "3. Star your own repository ⭐"
    echo "4. Share with others!"
    echo ""
else
    echo ""
    echo -e "${RED}Error: Push failed!${NC}"
    echo ""
    echo -e "${YELLOW}Common Issues:${NC}"
    echo ""
    echo "1. Authentication Error:"
    echo "   - Use Personal Access Token instead of password"
    echo "   - Generate token at: https://github.com/settings/tokens"
    echo "   - Select 'repo' scope"
    echo "   - Use token as password when prompted"
    echo ""
    echo "2. Repository doesn't exist:"
    echo "   - Make sure you created the repository on GitHub"
    echo "   - Check the repository name is correct"
    echo ""
    echo "3. Try manual push:"
    echo -e "   ${BLUE}git push -u origin main${NC}"
    echo ""
    exit 1
fi

# Final message
echo -e "${GREEN}Congratulations! 🎉${NC}"
echo "Your Library Management System is now on GitHub!"
echo ""
echo "To make future updates:"
echo -e "${BLUE}git add .${NC}"
echo -e "${BLUE}git commit -m \"Your commit message\"${NC}"
echo -e "${BLUE}git push${NC}"
echo ""
