# API Automation Assignment

## Task Overview
The objective of this assignment is to automate the validation that all users from the city `FanCode` have completed more than half of their todo tasks. 

## Scenario
- **Given** a user has todo tasks,
- **And** the user belongs to the city `FanCode`,
- **Then** the user’s completed task percentage should be greater than 50%.

## Notes
- The city `FanCode` can be identified by:
  - Latitude: between `-40` and `5`
  - Longitude: between `5` and `100`
- APIs used for this assignment are hosted at [jsonplaceholder.typicode.com](http://jsonplaceholder.typicode.com/):
  - `/users`: Fetches user information.
  - `/todos`: Fetches todo tasks for users.

## Requirements
- You can use any programming language to implement the API automation.
- The code should demonstrate good practices such as:
  - Code reusability
  - Logical problem-solving approach
  - Well-structured project organization

## Implementation Steps
1. **Set Up Environment**
   - See Prerequisite Section.
  
2. **Fetch Users**
   - Retrieve the list of users from the `/users` endpoint.
  
3. **Filter Users**
   - Identify users based on the latitude and longitude conditions to determine if they belong to `FanCode`.
  
4. **Fetch Todos**
   - For each user identified in the previous step, fetch their todos from the `/todos` endpoint.
  
5. **Calculate Completion Percentage**
   - Calculate the completion percentage using the formula:
     ```
     Completion Percentage = (Completed Tasks / Total Tasks) * 100
     ```
  
6. **Filter Users with > 50% Completion**
   - Collect and display the user IDs that have a completion percentage greater than 50%.
   
### Prerequisites
Before you begin, ensure you have the following installed:
- **Java JDK** (version 8 or higher)
- **Maven** (for Java projects) or the appropriate package manager for your chosen language
- **IDE** (such as IntelliJ IDEA, Eclipse, or any text editor)

### How to Clone the Repository
Clone the project repository to your local machine using Git. Replace `YOUR_REPOSITORY_URL` with the actual URL of your project.

```bash
git clone YOUR_REPOSITORY_URL
```
Open Eclipse.
Go to File > Import....
Select Maven > Existing Maven Projects.
Browse to the location of your cloned repository and click Finish.
  
