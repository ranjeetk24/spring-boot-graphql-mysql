# Spring Boot + GraphQL + MySQL Template

Go to https://start.spring.io/

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.2</version>
		<relativePath/>

    <java.version>21</java.version>
------------------------------------------------------------
Add Dependencies

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-graphql</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>

<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
</dependency>


------------------------------------------------------------
Open application.properties and add the following configuration


spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password=

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto= update



------------------------------------------------------------
Creating Schema for GraphQL


Next, create a user.graphqls file inside src/main/resources/graphql/ and define the types and operations (queries and mutations) for managing users


type User{
id: ID
firstName: String
lastName: String
email: String
mobileNumber: String
}

type UserResponse {
message: String
user: User
userList: [User]
}

type Query{
user(id: ID!): UserResponse
users: UserResponse
}

type Mutation {
createUser(firstName: String!,lastName: String!, email: String!,mobileNumber: String!): UserResponse
updateUser(id: ID!, firstName: String, lastName: String, email: String,mobileNumber: String): UserResponse
deleteUser(id: ID!): String
}

------------------------------------------------------------

Create Controller, Service, Repository, Model and Entities


http://localhost:8080/graphql

Create User Mutation

mutation CreateUser {
createUser(
lastName: "Martin"
email: "sam@gmail.com"
mobileNumber: "9876543210"
firstName: "Sam"
) {
message
}
}


response 

{
"data": {
"createUser": {
"message": "User Saved Successfully"
}
}
}



Query Users

query Users {
users {
message
userList {
id
firstName
lastName
email
mobileNumber
}
}
}


