package user;
import builderpattern.*;
import builderpattern.Error;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import utils.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
public class DmoneyUser {
    Properties prop= new Properties();
    FileInputStream file;
    {
        try {
            file=new FileInputStream("./src/test/resources/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void callingLoginAPI() throws IOException, ConfigurationException {
        prop.load(file);
        String email="salman@roadtocareer.net";
        String password="1234";
        UserLoginPost userLoginPost= UserLoginPost.builder().email(email).password(password).build();
        RestAssured.baseURI= prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        body(userLoginPost).
                        when().
                        post("/user/login").
                        then().
                        assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());
        userLoginPost=res.getBody().as(userLoginPost.getClass());
        Assert.assertEquals(userLoginPost.getMessage(),"Login successfully");
        Utils.setEnvVar("token",userLoginPost.getToken());
    }
    public void callingInvalEmailAPI() throws IOException {
        prop.load(file);
        String email="TestEmailYKDZXFx@gmail.com";
        String password="1234";
        UserLoginPost userLoginPost=UserLoginPost.builder().email(email).password(password).build();
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        body(userLoginPost).
                        when().
                        post("user/login").
                        then().
                        assertThat().statusCode(404).extract().response();
        System.out.println(res.asString());
        userLoginPost=res.getBody().as(userLoginPost.getClass());
        Assert.assertEquals(userLoginPost.getMessage(),"User not found");
    }
    public void callingLoginWithInvalPass() throws IOException {
        prop.load(file);
        String email="salman@roadtocareer.net";
        String password="123";
        UserLoginPost userLoginPost=UserLoginPost.builder().email(email).password(password).build();
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        body(userLoginPost).
                        when().
                        post("user/login").
                        then().
                        assertThat().statusCode(401).extract().response();
        System.out.println(res.asString());

        userLoginPost=res.getBody().as(userLoginPost.getClass());
        Assert.assertEquals(userLoginPost.getMessage(),"Password incorrect");
    }
    public void callingGetlistAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header("Authorization",prop.getProperty("token")).
                        when().
                        get("user/list").
                        then().
                        assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());

        GetUserList getUserList=GetUserList.builder().build();
        getUserList=res.getBody().as(getUserList.getClass());
        List<Userlist> userlist=getUserList.getUsers();

        Userlist userlist1=getUserList.getUsers().get(0);
        Userlist userlist2=getUserList.getUsers().get(1);

        Assert.assertEquals(getUserList.getMessage(),"User list");
        Assert.assertEquals(userlist1.getName(),"Test Customer 1");
        Assert.assertEquals(userlist1.getEmail(),"user57662@test.com");
    }
    public  void calllingGetListWithoutToken() throws IOException {
     prop.load(file);
     RestAssured.baseURI=prop.getProperty("baseUrl");
     Response res=
             given().
                     contentType(prop.getProperty("contentType")).
                     when().
                     get("user/list").
                     then().
                     assertThat().statusCode(401).extract().response();
        System.out.println(res.asString());
        JsonPath response=res.jsonPath();
        ErrorRes errorRes=ErrorRes.builder().build();
        errorRes=res.getBody().as(errorRes.getClass());
        Error error =errorRes.getError();
        System.out.println(error);
    }
    public void callingGetListWithWrongToken() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header("Authorization","1234").
                        when().
                        get("user/list").
                        then().
                        assertThat().statusCode(403).extract().response();
        System.out.println(res.asString());
        ErrorRes errorRes=ErrorRes.builder().build();
        errorRes=res.getBody().as(errorRes.getClass());
        Error error=errorRes.getError();
        String message=error.getMessage();
        System.out.println(message);
        Assert.assertEquals(message,"Token expired!");
    }
    public void callingGetSingleUserList() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header("Authorization",prop.getProperty("token")).
                        header("X-AUTH-SECRET-KEY",prop.getProperty("secKey")).
                        when().
                        get("user/search/id/9").
                        then().
                        assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());
        SingleUserList singleUserList=SingleUserList.builder().build();
        singleUserList=res.getBody().as(singleUserList.getClass());
        Userlist userlist=singleUserList.getUser();
        Assert.assertEquals(userlist.getEmail(),"user21965@test.com");
        Assert.assertEquals(userlist.getName(),"Test Customer 2");
    }

    public void callCreateUser() throws IOException, ConfigurationException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        int random=(int) Math.floor(Math.random()*(10000000-99999999)+99999999);
        Faker faker=new Faker();
        String name=faker.name().fullName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password();
        String phone_Number="017"+random;
        String nid="987"+random;
        String role="customer";
        User user=User.builder().name(name).email(email).email(email).password(password)
                .phone_number(phone_Number).nid(nid).role(role).build();
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header(prop.getProperty("auth"),prop.getProperty("token")).
                        header(prop.getProperty("sec"),prop.getProperty("secKey")).
                        body(user).
                        when().
                        post("user/create").
                        then().
                        assertThat().statusCode(201).extract().response();
        System.out.println(res.asString());
        CreateUserRes createUserRes=CreateUserRes.builder().build();
        createUserRes=res.getBody().as(createUserRes.getClass());
        Assert.assertEquals(createUserRes.getMessage(),"User created");
        Assert.assertEquals(user.getName(),name);
        Assert.assertEquals(user.getEmail(),email);
        JsonPath response=res.jsonPath();
        String id=response.get("user.id").toString();
        System.out.println(id);
        Utils.setEnvVar("id", id);
    }
    public void callGetNewUser() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String id=prop.getProperty("id");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header("Authorization",prop.getProperty("token")).
                        header("X-AUTH-SECRET-KEY",prop.getProperty("secKey")).
                        when().
                        get("user/search/id/"+id).
                        then().
                        assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());

    }
    public void callUpdateAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String id=prop.getProperty("id");
        String role="police";
        UpdateBody updateBody=UpdateBody.builder().role(role).build();
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header("Authorization",prop.getProperty("token")).
                        header("X-AUTH-SECRET-KEY",prop.getProperty("secKey")).
                        body(updateBody).
                        when().
                        patch("user/update/"+id).
                        then().
                        assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());
        updateBody=res.getBody().as(updateBody.getClass());
        Assert.assertEquals(updateBody.getMessage(),"User updated successfully");
        UpdateRes updateRes=updateBody.getUser();
        Assert.assertEquals(updateRes.getRole(),"police");
    }
    public void callDeleteAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header(prop.getProperty("auth"),prop.getProperty("token")).
                        header(prop.getProperty("sec"),prop.getProperty("secKey")).
                        when().delete("/user/delete/"+prop.getProperty("id")).
                        then().
                        assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());
        Error error=Error.builder().build();
        error=res.getBody().as(error.getClass());
        Assert.assertEquals(error.getMessage(),"User deleted successfully");
    }
    public void callDeleteDeletedAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header(prop.getProperty("auth"),prop.getProperty("token")).
                        header(prop.getProperty("sec"),prop.getProperty("secKey")).
                        when().delete("/user/delete/"+prop.getProperty("id")).
                        then().
                        assertThat().statusCode(404).extract().response();
        System.out.println(res.asString());
        Error error=Error.builder().build();
        error=res.getBody().as(error.getClass());
        Assert.assertEquals(error.getMessage(),"User not found");
    }
    public void callDeletedAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=
                given().
                        contentType(prop.getProperty("contentType")).
                        header(prop.getProperty("auth"),prop.getProperty("token")).
                        header(prop.getProperty("sec"),prop.getProperty("secKey")).
                        when().
                        get("user/search/id/"+prop.getProperty("id")).
                        then().
                        assertThat().statusCode(404).extract().response();
        System.out.println(res.asString());
        DeleteUserRes deleteUserRes=DeleteUserRes.builder().build();
        deleteUserRes=res.getBody().as(deleteUserRes.getClass());
        Assert.assertEquals(deleteUserRes.getMessage(),"User not found");
    }
}
