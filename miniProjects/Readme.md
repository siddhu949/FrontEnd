# 🚀 LoginProject - Java Servlet Based Authentication System

## 📌 Overview

This project is a **Java Servlet and JSP based Login & Registration System** with features like user registration, login, logout, password recovery via OTP (with **Google App Password for secure email sending**), and password reset. It uses **MySQL** for persistent data storage and follows an MVC-style architecture.

---

## 🗂️ Project Structure

```
LoginProject/
│── ServletsProgram/
│   ├── src/main/java/
│   │   ├── FirstServ.java
│   │   ├── com/unquieprogram/Registration/
│   │   │   ├── DbUtility.java
│   │   │   ├── LoginServlet.java
│   │   │   ├── LogoutServlet.java
│   │   │   ├── RegistartionServlet.java
│   │   │   ├── ForgotPassword.java
│   │   │   ├── NewPassword.java
│   │   │   ├── ValidateOtp.java
│   ├── src/main/webapp/
│   │   ├── index.jsp
│   │   ├── login.jsp
│   │   ├── registration.jsp
│   │   ├── forgotPassword.jsp
│   │   ├── EnterOtp.jsp
│   │   ├── newPassword.jsp
│   │   ├── css/, fonts/, assets/
```

---

## 🔧 Functionality

### ✅ Servlets

* **`LoginServlet`** → Handles user authentication (username/password check).
* **`LogoutServlet`** → Ends user session and redirects to login page.
* **`RegistartionServlet`** → Handles new user registration (stores details in DB).
* **`ForgotPassword`** → Allows users to request OTP for password reset (sends OTP via email).
* **`ValidateOtp`** → Validates OTP entered by user.
* **`NewPassword`** → Updates password after successful OTP validation.
* **`DbUtility`** → Provides MySQL database connection and helper methods.

### ✅ JSP Pages

* **`index.jsp`** → Landing page.
* **`login.jsp`** → User login form.
* **`registration.jsp`** → User signup form.
* **`forgotPassword.jsp`** → Enter email to request OTP.
* **`EnterOtp.jsp`** → Enter OTP received.
* **`newPassword.jsp`** → Reset password.

### ✅ SQL Database

Database used: **MySQL**

```sql
CREATE DATABASE login_system;
USE login_system;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE otp_verification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    otp_code VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 📦 Required JAR Files

Make sure to add these libraries in your **Tomcat `lib/` folder** or project `WEB-INF/lib/`:

* `jakarta.servlet-api.jar` → For Servlets & JSP
* `mysql-connector-j-<version>.jar` → For MySQL database connectivity
* `javax.mail.jar` → For sending OTP emails via Gmail SMTP

---

## 📧 Google App Password Setup

For sending OTP emails using **Gmail SMTP**, you need to generate a **Google App Password**:

1. Go to your **Google Account** → **Security**.
2. Enable **2-Step Verification**.
3. Under **App Passwords**, generate a new password for "Mail" and "Other (Custom name: LoginProject)".
4. Use this 16-character password in your email configuration inside `ForgotPassword.java`.

```java
final String senderEmail = "your-email@gmail.com";
final String senderPassword = "your-app-password"; // Generated from Google
```

This ensures **secure OTP delivery** without exposing your real Gmail password.

---

## ▶️ How to Run

1. Install **Apache Tomcat** (>=10.x).
2. Setup **MySQL** database using the script above.
3. Configure **DbUtility.java** with your MySQL `username` & `password`.
4. Configure **ForgotPassword.java** with your Gmail & App Password.
5. Deploy project in Tomcat (`LoginProject/ServletsProgram`).
6. Access in browser:

   * `http://localhost:8080/ServletsProgram/login.jsp`

---

## 🎨 Features

* 🔐 Secure login & logout
* 📝 User registration with validation
* 📧 Forgot password with OTP verification (Google App Password enabled)
* 🔑 Password reset functionality
* 📊 MySQL-based storage
* 🎨 Styled JSP frontend with CSS & assets

---

## 👨‍💻 Author

Developed as part of **Java Web Development using Servlets & JSP** learning project.
