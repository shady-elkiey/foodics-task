# Automation Testing

The Automation Testing repository contains the test cases related to the following:

- login to amazon web app and add the required products in the cart
- Comprehensive test cases that cover the creation, retrieval, and updating of the user endpoint.

### Tools and Technologies Used

* [Selenium 4.13.0](https://www.selenium.dev/)
* [REST Assured](https://rest-assured.io/)
* [TestNG 7.8.0](https://testng.org/doc/)
* [Maven 3.9.9](https://maven.apache.org)

### Requirements

For building and running the application you need:

- [JDK 22.0.1](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.9.9](https://maven.apache.org)

### Running the application locally

There are several ways to run the application on your local machine. One way is to execute the `test cases` method
in the `tests.ApiTest` class or `tests.AmazonTest` from your IDE.

you also can run the testng.xml file to run both classes from your IDE.

Alternatively you can use the maven goal like so:

#### Windows:

```shell
mvn test -DsuiteXmlFile= path to your testng.xml
```

Also note that you will only be able to run when you set password while logging into Amazon in your environment variables.

### Further Enhancements

* [thread.sleep needs to be removed as it is used with 3 elements](https://www.selenium.dev/)
* [the test ignores the decimal part of the total price](https://rest-assured.io/)