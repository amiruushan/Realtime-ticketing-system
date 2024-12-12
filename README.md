# Real time Ticketing system

This project is a real-time ticketing system which allows vendors to release tickets and customers to purchase tickets concurrently, with backend synchronization and multithreading to handle concurrent operations. The system also includes a configuration module for defining key parameters as follows,

    Total Number of Tickets
    Maximum Number of Tickets
    Ticket Release Rate
    Customer Retreival Rate

Features

Backend (Spring Boot)

Vendor Management:

Signup and Login for Vendors.

Ticket release functionality with multithreading.

Customer Management:

Signup and Login for Customers.

Ticket purchasing functionality with multithreading.

Configuration Management:

Save configuration parameters (e.g., ticket release rate, max tickets).

Frontend (React.js + TypeScript)

Configuration Form:

GUI to input and save configuration parameters to the backend.
