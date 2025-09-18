# BookMyShow - User Registration & Login System

## 📌 Overview
This project is a simplified **BookMyShow** web application built with **Spring MVC**.  
It provides features for **user registration, login, and profile management** with secure password handling.

---

## 🚀 Features
- User **Signup** with name, email, and password
- **Login** using email & password
- **Profile page** to view registered details
- Secure password storage (hashing)
- Spring MVC with **controllers, services, DTOs, and model**
- JSP-based frontend (`login.jsp`, `register.jsp`, `profile.jsp`)
- Configured with **Maven** and `application.properties`

---

## 🛠️ Technologies Used
- **Java** (JDK 8+)
- **Spring MVC**
- **Spring Security**
- **Maven**
- **JSP**
- **Tomcat / any servlet container**

---

## 📂 Project Structure
BookMyShow/
├── src/main/java/com/book/...
│ ├── config/ (SecurityConfig)
│ ├── controllers/ (PageController, UserController)
│ ├── dto/ (Login & Signup DTOs)
│ ├── model/ (User.java)
│ └── services/ (UserService, UserServiceImpl)
├── src/main/resources/ (application.properties)
├── src/main/webapp/WEB-INF/
│ ├── Views/ (login.jsp, register.jsp, profile.jsp)
│ ├── FrontController-servlet.xml
│ └── web.xml
├── database.txt
└── pom.xml

## ⚙️ Setup & Run
1. Clone this repository:
   ```bash
   git clone <repo-url>
   cd BookMyShow
2. Build with Maven:
   ```bash
   mvn clean install
3. Deploy on Tomcat or run from IDE (Eclipse/IntelliJ).

4. Access app in browser:
 ```bash
   http://localhost:8080/BookMyShow/login
```
## 👨‍💻 Future Enhancements
- Integrate **MySQL/PostgreSQL** instead of text file database
- Add **Movie browsing & booking features**
- Implement **Ratings & Reviews** for movies
- Implement **JWT authentication**
- Add **Admin dashboard**


