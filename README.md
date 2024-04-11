

## Travel Booking System 

#### Tech Stacks - Java Programming, Servlet, JDBC, JSP and Bootstrap 5

1. Clone the repo
```shell
git clone https://github.com/Hein-HtetSan/travel-booking-j2ee.git
```

2. Open the project
```shell
cd travel-booking-j2ee
code .
```

3. create database by this diagram [Click Here!](https://github.com/Hein-HtetSan/travel-booking-j2ee/tree/main/guide)
```shell
CREATE DATABASE travel_booking_system;
use travel_booking_system;
```
**Then, create the following tables according to the database diagram image.**

4. Import the tomcat server  
	- First you need to download tomcat server and unzip it under C: Drive
	- Add the tomcat server, open Server Terminal > click Add local tomcat server
	- Start the server,

5. Don't Forget to add **jar files** which is already existed in **src/main/webapp/WEB_INF/lib/**
	- To add the jar file, **right click on project folder** and then **click configure build path**
	- Then, **click Modules path** and on the right side, you will see the **add jar button** is activated
	- Click the **Add jars** button and search the jar files (sqlconnect, and so on) which is under **src/main/webapp/WEB_INF/lib/**
	- Finally, **click apply and close** button, **Well Done** 

6. Check everything and then run the application    

**Start Running from AdminController where is at java/controller/AdminController.java**
	- Run that controller by clicking the <run on server> not java application


**NOTE: if there any server occurs, you can delete the server and put again. If this step is not working for you, you can change the serverport by double click on server and change the HTTP port which is 8080 to 8085 or 9090.**
