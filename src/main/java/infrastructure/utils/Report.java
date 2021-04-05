package infrastructure.utils;

import io.qameta.allure.Step;

public class Report {
    @Step("{0}")
    public static void log(String message) {
        org.testng.Reporter.log(" LOG - " + message, true);
    }

}
