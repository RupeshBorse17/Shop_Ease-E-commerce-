<img width="1920" height="1080" alt="Screenshot (204)" src="https://github.com/user-attachments/assets/44c8857d-c6f8-43c8-9fb2-e60a7bbc12f5" />
# 🛒 ShopEase – Full Stack E-Commerce Application

ShopEase is a full-stack E-Commerce application built using **React.js**, **Spring Boot**, and **PostgreSQL**. It enables users to browse products, search items, manage a shopping cart, place orders, and view order history through a responsive and user-friendly interface.

---

## 🚀 Features

- Product CRUD (Add, Update, Delete, View)
- Product Search & Category Filter
- Image Upload
- Shopping Cart (Context API + Local Storage)
- Order Placement & Order History
- Automatic Inventory Management
- Responsive UI with Validation & Toast Notifications

---

## 🛠 Tech Stack

### Frontend
- React.js
- Context API
- React Router
- Axios
- Bootstrap
- HTML, CSS, JavaScript

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs
- Maven

### Database
- PostgreSQL

### Tools
- Git
- GitHub
- Postman
- IntelliJ IDEA
- VS Code

---

## 🏗 Architecture

```
React UI
   ↓
Axios
   ↓
Spring Boot REST APIs
   ↓
Controller
   ↓
Service
   ↓
Repository (JPA)
   ↓
PostgreSQL
```

---

## 🗄 Database

**Tables**
- Product
- Orders
- OrderItem

**Relationship**

```
Orders (1)
    │
    ▼
OrderItem (Many)
    │
    ▼
Product (1)
```

---

## 📌 REST APIs

### Product
- GET `/api/products`
- GET `/api/product/{id}`
- POST `/api/product`
- PUT `/api/product/{id}`
- DELETE `/api/product/{id}`
- GET `/api/products/search`

### Order
- POST `/api/orders`
- GET `/api/orders`

---

## 💡 Key Highlights

- Layered Architecture (Controller → Service → Repository)
- RESTful API Development
- Context API State Management
- Hibernate ORM
- Inventory Management
- Clean & Scalable Code
- Separation of Concerns

---

## 🎯 Learning Outcomes

This project strengthened my skills in:

- Full Stack Development
- Java & Spring Boot
- REST API Design
- React State Management
- Database Design
- Hibernate & JPA
- SQL
- Git & GitHub
- Software Architecture
- Debugging & Problem Solving

- ## 🚀 Future Scope

- User Authentication & Authorization (JWT, Spring Security)
- Secure Online Payments (Stripe/Razorpay)
- Wishlist & Favorites
- Product Reviews & Ratings
- Admin Dashboard for Product & Order Management
- Order Tracking & Email Notifications
- Pagination, Filtering & Sorting
- Docker Containerization & CI/CD
- Cloud Deployment (AWS/Azure/Render)
- Redis Caching for Improved Performance
