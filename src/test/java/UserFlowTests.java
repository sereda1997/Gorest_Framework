import com.codeborne.selenide.proxy.RequestSizeWatchdog;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class UserFlowTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test case verifies basic flow of creating/updating/deleting user flow")
    public void UserCreationUpdateDeleteActions() {

        UserPojo user = new UserPojo("John", "emaTest22@uk.com", "Male");
        int id =
        RequestUtils
                .createUser(user.getName(), user.getGender(), user.getEmail());
        RequestUtils
                .verifyUserCreated(id, user.getName());
        RequestUtils
                .editField(id, "name", "Changed");
        RequestUtils
                .verifyUserFields(id,"name","Changed");
        RequestUtils.deleteUser(id);

    }
}
