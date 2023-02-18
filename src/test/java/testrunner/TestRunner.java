package testrunner;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;
import user.DmoneyUser;
import java.io.IOException;

public class TestRunner {
    @Test(priority = 1, description = "Calling login API with valid credentials")
    public void doLogin() throws IOException, ConfigurationException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callingLoginAPI();
    }
    @Test(priority = 2, description = "Calling login API with invalid email")
    public void doLoginWithInvalEmail() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callingInvalEmailAPI();
    }
    @Test(priority = 3, description = "calling login API with invalid password")
    public void doLoginWithInvalPass() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callingLoginWithInvalPass();
    }
    @Test(priority = 4, description = "calling get user list API")
    public void getUserList() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callingGetlistAPI();
    }
    @Test(priority = 5, description = "calling get user list API without token" )
    public void getUserListWithoutToken() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.calllingGetListWithoutToken();
    }
    @Test(priority = 6, description = "calling get user list API with wrong token")
    public void getUserListWithWrongToken() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callingGetListWithWrongToken();
    }
    @Test(priority = 7, description = "calling get single user list API")
    public void getSingleUserList() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callingGetSingleUserList();
    }
    @Test(priority = 8, description = "calling post API to create new user")
    public void createUser() throws IOException, ConfigurationException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callCreateUser();
    }
    @Test(priority = 9, description = "Calling get new user API")
    public void getNewUser() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callGetNewUser();
    }
    @Test(priority = 10, description = "calling update API")
    public void updateNewUser() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callUpdateAPI();

    }@Test(priority = 11, description = "calling delete API")
    public void deleteUser() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callDeleteAPI();
    }@Test(priority = 12, description = "calling delete deleted API")
    public void deletedUser() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callDeleteDeletedAPI();
    }
    @Test(priority = 13,description = "calling deleted user API")
    public void getDeletedUser() throws IOException {
        DmoneyUser dmoneyUser= new DmoneyUser();
        dmoneyUser.callDeletedAPI();
    }
}
