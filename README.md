# Overview

The server-side of the project was developed during my 30-day internship at TÜBİTAK Bilgem YTE. Client-side application is available in [this repository](https://github.com/umutbayramoglu/event-management-system-frontend).

| Language | Framework | Java | Maven 
| -------- | -------- |--------|--------
| Java | Spring Boot| 1.8+| 3.8.1+

# API Documentation
All documents related to API are available on [here](https://github.com/umutbayramoglu/event-management-system-backend/wiki).

# Getting Started
1. Clone this repository:
`git clone https://github.com/umutbayramoglu/event-management-system-backend`. 
2. Open `event-management-system-backend` folder with your IDE. 

> You can also import the project straight into [Intellij IDEA](https://github.com/JetBrains/intellij-community) by selecting New > Project from Version Control

# Configurations
You can make database and mail configurations on **application.properties**.

### Database Configuration
```
spring.datasource.url=jdbc: POSTGRESQL_CONNECTION_URL
spring.datasource.username= POSTGRESQL_USERNAME 
spring.datasource.password= POSTGRESQL_PASSWORD 
```
### Mail Configuration
```
spring.mail.username= GMAIL
spring.mail.password= PASSWORD
```
