# Customer System

This project is a simple customer system implemented using Spring Boot for the backend and HTML/CSS/JavaScript for the frontend.

## Features

- Register new customers
- Login functionality
- View, update, and delete customer details
- Search customers by various criteria
- Sync customer data from a remote API

## Prerequisites

Before running this project, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL database (Using XAMPP)
- IDE(VSCode)

## Getting Started

To get this project up and running, follow these steps:

1. **Clone the repository:**

  
   git clone https://github.com/Gayatritumram/customer.git
   cd customer

2. To Run Application 
   I. Build Project and check all dependency intalled
   II. Open CustomerApplication.java and rightclick click Run Without Debugging.
   III. Open Register.html and rightclick click Run without Debugging.
   IV. Test All API in Postman Which I have attached API Documentation below.

##API Documentation

1. register
 http://localhost:1036/customer/register
 
2. login
http://localhost:1036/customer/login

3. update
http://localhost:1036/customer/update

4. Delete
http://localhost:1036/customer/delete/{customerId}

5. getAllCustomers
http://localhost:1036/customer/getAllCustomers

6. getAllCustomerById
http://localhost:1036/customer/getCustomerById/{customerId}

7. search
http://localhost:1036/customer/search

8. search
http://localhost:1036/customer/search


