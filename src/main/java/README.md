# ** Savings Goals API Assesment**

## **Introduction**
The SavingsGoals API is a Spring Boot application designed to help users manage and track their savings goals. It allows for creating, retrieving, updating, and deleting savings goal records, stored in a JSON format using Gson for serialization and deserialization.

## Getting Started
Follow these instructions to get the SavingsGoals API up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have the following installed before proceeding:

- Java JDK 11 or later
- Maven (for building and running the project)
- Spring Boot 2.x

### Installation

#### 1. Clone the Repository
```sh
git clone [REPO_URL]
cd [REPO_NAME]
```

Replace [REPO_URL] with the link to your GitHub repository and [REPO_NAME] with the repository's name.

#### 2. Navigate to the project directory:

```sh
cd [REPO_NAME]
```

#### 3. Run the application using Maven:

```sh
mvn spring-boot:run
```

## API Endpoints
### Create a Savings Goal
- Endpoint: /api/savingsgoals
- Method: POST
- Body Example:

```sh
{
  "goalName": "Vacation",
  "currentAmount": 500.0,
  "targetAmount": 3000.0
}

```
- Success Response: HTTP 200 OK with the created SavingsGoals object.

### Retrieve All Savings Goals
- Endpoint: /api/savingsgoals
- Method: GET
- Success Response: HTTP 200 OK with a list of all SavingsGoals.

### Retrieve a Specific Savings Goal

- Endpoint: /api/savingsgoals/{id}
- Method: GET
- URL Params: id=[UUID]
Success Response: HTTP 200 OK with the requested SavingsGoals object.
- Error Response: HTTP 404 Not Found if the goal is not found.


### Update a Savings Goal
- Endpoint: /api/savingsgoals/{id}
- Method: PUT
- URL Params: id=[UUID]
- Body Example:
```sh
{
  "goalName": "Updated Goal",
  "currentAmount": 1000.0,
  "targetAmount": 4000.0
}
```
- Success Response: HTTP 200 OK with the updated SavingsGoals object.
- Error Response: HTTP 404 Not Found if the goal is not found.

### Delete a Savings Goal
- Endpoint: /api/savingsgoals/{id}
- Method: DELETE
- URL Params: id=[UUID]
- Success Response: HTTP 200 OK.
- Error Response: HTTP 404 Not Found if the goal is not found.

## Built With
- Spring Boot - The web framework used for building the RESTful API.
- Gson - Used for serialization and deserialization of Java objects to/from JSON.
- Maven - Dependency Management and project build tool.

