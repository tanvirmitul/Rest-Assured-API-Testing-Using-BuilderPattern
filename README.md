# Rest-Assured-API-Testing-Using-BuilderPattern
### An [API](http://dmoney.roadtocareer.net) is tested by using REST Assured framework integrated with TestNG as testing framework for validation purpose. Here, the status codes, validation messages and the flow of API is tested using a Dmoney API where there is login,searching,creating,updating and deleting features.
Here the following tasks are done:
- Login feature tested using proper valiadtion,negative test cases added for email and password.
- Can get user list by user authorization token, both positive and negative test cases are added for it.
- Can search any user by proper id.
- Can create a user by random name,email,password,nid and phone number using proper validation and secret key token.
- Can update any user by the corresponding id, validated using PATCH method.
- Can delete any user by the id, negative test cases are added for it.
- The variables are set and used from config.properties file.
### Technology: </br>
- Tool: REST Assured
- IDE: Intellij
- Build tool: Gradle
- Language: Java
- Test_Runner: TestNG

### Prerequisites</br>
- Install jdk 1.8 or any LTS versio
- Configure JAVA_HOME and GRADLE_HOME
- Download Allure 2.20.1 and configure environment path
- Stable internet connection

### Project Run
- Clone the repo.
- Open cmd in the root folder.
#### Run the Automation Script by the following command:
 ```
 gradle clean test 
 ```
- The following report is generated:

![Sample report](https://user-images.githubusercontent.com/59876702/219868898-59e6663b-b6e5-4613-87a8-9c28461e1fc8.PNG)

- After automation to vie![TestReport]
w allure report , give the following commands:
 ```
allure generate allure-results --clean -o allure-report
allure serve allure-results
 ```
**Here is the Allure report overview:**

![AllureReport](https://user-images.githubusercontent.com/59876702/219868924-ed88c5d0-9c40-4909-872b-a05296b1fedf.PNG)

**Below the suites run are shown via Allure :**

![AllureReport2](https://user-images.githubusercontent.com/59876702/219868958-95d7e7da-e6a9-453b-9a41-a37b04de8890.PNG)
