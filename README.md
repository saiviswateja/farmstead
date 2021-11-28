# Farmstead

This project is the backend implementation of Farmstead project which provides RestFul Services for grabbing the products and placing orders.

The Admin can add products into the portal using a formatted CSV file. He can upload the csv in Specified bucket in AWS S3 and the product feed will run, inserts the products 
in the Database.

Everyday the orders will shipped for every 6 hours.

The application is developed using Spring Boot. For Storage, I used MySQL database.

