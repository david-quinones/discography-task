# Discography Task

This project is a Spring Boot application for managing artists and LPs. 
It uses SQLite as the database and Swagger for API documentation.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- SQLite

## Installation

### Clone the Repository

```bash
git clone https://github.com/david-quinones/discography-task.git
cd discography-task
```
### IDE configuration
#### IntelliJ IDEA
- Open IntelliJ IDEA.
- Click on **File** -> **Open....**
- Select the **discography-task** directory.
- IntelliJ will automatically detect the Maven project and import it.
- Wait for the dependencies to be downloaded.
- Run the application by clicking on the Run button or by executing the main method in the DiscographyTaskApplication class.

#### NetBeans
- Open NetBeans.
- Click on **File** -> **Open....**
- Select the **discography-task** directory.
- NetBeans will automatically detect the Maven project and import it.
- Wait for the dependencies to be downloaded.
- Run the application by right-clicking on the project and selecting Run.

#### Visual Studio Code
- Open Visual Studio Code.
- Click on **File** -> **Open....**
- Select the **discography-task** directory.
- Install the **Java Extension Pack** and **Spring Boot Extension Pack** from the Extensions view if you haven't already.
- Open the **src/main/java/com/dquinones/DiscographyTaskApplication.java** file.
- Click on the Run button above the main method or press F5 to start the application.

## Configuration
The application configuration is located in the **src/main/resources/application.properties** file. You can modify the database connection settings and other properties as needed.  
## API Documentation
The API documentation is available at **http://localhost:8080/swagger-api** after running the application.  
## License
This project is licensed under the MIT License.