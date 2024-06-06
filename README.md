# User Manager App

## 📝 Description

A user account management application. It allows for adding, deleting, editing, and viewing user accounts within the system.

## ⚙️ Features

### 👥 View Users
- The application enables viewing a list of all users in the system.
- Ability to view detailed information about a single user.

### ➕ Add User
- Ability to add new users to the system.
- Validation of input data correctness during user addition.

### 🔄 Update User Data
- Users can update their data such as first name, last name, gender, and age.

### 🗑️ Delete User
- Users can delete their account.

### 📧 Send Activation Email
- Alongside account creation, the application provides an option to send an activation email to users for account activation. The activation link has a limited validity period for security reasons.

### 🔍 Sort Records
- The application allows sorting the number of records displayed per page based on various criteria such as username, age, gender, and account creation date. Users can choose the sorting criteria and adjust the display according to their preferences.

## 🚀 Running the Project

### ✅ Prerequisites
- Installed Java 8 or higher.
- Installed relational database (PostgreSQL).

### 📦 Installation and Running

To run the project, follow these steps:
1. Clone or download the source code of the project.
2. In the `application.properties` file, set up the connection to your database.
3. Run the `mvn clean install` command to build the project.
4. After successful compilation, run the `mvn spring-boot:run` command to start the application.
5. The application will be available at `http://localhost:8080`.
6. Sample data for populating the database is available in the `cases.sql` file in the `resources` directory.

## 🌐 User Interface

- The application offers an HTML user interface that allows for adding a new user account: [http://localhost:8080/createUserAccount](http://localhost:8080/createUserAccount)

## 🛠️ Used Technologies

- ☕ Java 17
- 🌱 Spring Boot v.3.3.0
- 🔒 Spring Security v.5.7.1
- ✅ JUnit 4
- 🐍 Hibernate
- 🛠️ Maven
- 🐘 PostgreSQL
- 📄 Thymeleaf
- 🌐 HTML