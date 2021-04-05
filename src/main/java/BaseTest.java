
import com.codeborne.selenide.Configuration;
import infrastructure.utils.Report;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    public static String keepit_test_site_url = "https://gorest.co.in/public-api";

    @BeforeClass
    protected void beforeClass() {
        System.out.println("before class");
    }
}
