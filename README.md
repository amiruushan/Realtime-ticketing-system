# Realtime Ticketing System

## Overview
The Realtime Ticketing System is a web application developed using **Spring Boot** and **React**. This platform enables vendors to release tickets for events or services, while customers can purchase tickets in real-time. The backend operations utilize multithreading and synchronization to handle concurrent transactions efficiently, ensuring data consistency and reliability.

## Features
- **Vendor Dashboard**: Vendors can release tickets.
- **Customer Portal**: Customers can make purchases.
- **Real-time Updates**: Ensures ticket availability is updated instantly.
- **Multithreading Support**: Handles multiple ticket purchases simultaneously.
- **Data Synchronization**: Prevents overselling of tickets through robust backend synchronization mechanisms.

## Technologies Used
### Backend
- Spring Boot (Java)
- Multithreading and synchronization
- RESTful APIs

### Frontend
- React.js
- CSS

### Database
- MySQL 

### Tools
- IntelliJ IDEA (Backend Development)
- Visual Studio Code (Frontend Development)
- Postman (API testing)

## Installation
### Prerequisites
- Java 17+
- Node.js 16+
- MySQL or PostgreSQL database
- Maven (for managing dependencies)

### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/gesith/Realtime-Ticketing-System.git
   cd realtime-ticketing-system
   ```
2. **Setup the backend:**
   - Navigate to the `ticketmanagement-backend` folder.
   - Update the `DatabaseConnection` file with your database credentials.
   - Create a local MySQL database named `TicketingSystem`.
   - Add the following tables:
     - **Customer**: Columns - `name`, `email`, `password`, `is_vip`
     - **Vendor**: Columns - `name`, `email`, `password`
   - Build and run the Spring Boot application:
     ```bash
     mvn clean install
     mvn spring-boot:run
     ```
3. **Setup the frontend:**
   - Navigate to the `ticketmanaging` folder.
   - Install dependencies:
     ```bash
     npm install
     ```
   - Start the development server:
     ```bash
     npm start
     ```
4. Access the application at `http://localhost:3000`.

## Usage
1. **For Vendors:**
   - Sign up or log in as a vendor.
   - Use the dashboard to release tickets.
2. **For Customers:**
   - Sign up or log in as a customer.
   - Purchase tickets in real-time.
3. **Admin Panel:** (if applicable)
   - Update configuration parameters.

## Contact
For further inquiries or support, please contact:
- **Name:** Gesith Bimsara
- **Email:** gesithg@icloud.com
- **GitHub:** [github.com/gesith](https://github.com/gesith)

---
Thank you for using the Realtime Ticketing System!

