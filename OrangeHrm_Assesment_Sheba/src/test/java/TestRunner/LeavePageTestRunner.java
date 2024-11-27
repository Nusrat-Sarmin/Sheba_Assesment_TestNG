package TestRunner;

import Pages.LeaveApply;
import Pages.LoginPage;
import Setup.Setup;
import io.opentelemetry.api.internal.Utils;
import io.qameta.allure.Allure;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class LeavePageTestRunner extends Setup {
    LoginPage loginPage;
    LeaveApply leaveApply;
    Utils utils;
    String comment;
    String invalidFromDateRange;
    String invalidToDateRange;
    String invalidFromDateFormat;
    String invalidToDateFormat;
    String validFromDate;
    String validToDate;

    public void basicInfo() {
        comment = "I need to take leave due to illness";
        invalidFromDateRange = "2024-27-11";
        invalidToDateRange = "2024-01-10";
        invalidFromDateFormat = "05-10-2024";
        invalidToDateFormat = "10-10-2024";
        validFromDate = "2024-2-12";
        validToDate = "2024-5-12";
    }

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage = new LoginPage(driver);
        String adminUser= "Admin";
        String adminPass="admin123";
        loginPage.doLogin(adminUser,adminPass);
    }

    @Test(priority = 0, description = "Mandatory Fields is empty")
    public void mandatoryAllFieldShouldBlank() throws InterruptedException {
        Thread.sleep(2000);
        leaveApply = new LeaveApply(driver);
        leaveApply.sideBarTime.get(3).click();
        leaveApply.topBar.get(0).click();
        Thread.sleep(3000);
        leaveApply.btnApply.get(3).click();
        String isGotErrorMsg = leaveApply.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed" +
                "then an error message will be thrown to the user 'Required'");
    }

    @Test(priority = 1, description = "Apply for Leave with Blank Date Fields")
    public void doLeaveApplyWithDateFieldAreBlank() throws InterruptedException {
        Thread.sleep(2000);
        leaveApply = new LeaveApply(driver);
        leaveApply.dropdownLeaveType.click();
        Thread.sleep(3000);
        for (int i = 0; i < 1; i++) {  // Adjust this based on how many options you want to scroll through
            Thread.sleep(3000);
            leaveApply.dropdownLeaveType.sendKeys(Keys.ARROW_DOWN);  // Simulates pressing the arrow down key
        }
        Thread.sleep(3000);
        leaveApply.dropdownLeaveType.sendKeys(Keys.ENTER);
        leaveApply.btnApply.get(3).click();
        String isGotErrorMsg = leaveApply.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("Required"));
        Allure.description("Verifies that the leave application cannot be submitted when the date fields " +
                "(\"From Date\" or \"To Date\") are left blank, ensuring proper input validation for the required fields.");
    }

    @Test(priority = 2, description = "Apply for Leave with Invalid Date Range")
    public void doLeaveApplyWithInvalidDateRange() throws InterruptedException {
        Thread.sleep(2000);
        leaveApply = new LeaveApply(driver);
        basicInfo();
        leaveApply.doLeaveApplyWithValidData(invalidFromDateRange, invalidToDateRange, comment);
        String isGotErrorMsg = leaveApply.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("To date should be after from date"));
        Allure.description("Verifies that the system enforces date range validation by preventing submission when " +
                "the 'To date should be after from date'.");
    }

    @Test(priority = 3, description = "Apply for Leave with Invalid Date Format")
    public void doLeaveApplyWithInvalidDateFormat() throws InterruptedException {
        Thread.sleep(2000);
        leaveApply = new LeaveApply(driver);
        basicInfo();
        leaveApply.doLeaveApplyWithValidData(invalidFromDateFormat, invalidToDateFormat, comment);
        String isGotErrorMsg = leaveApply.inLineErrorMsg.getText();
        Assert.assertTrue(isGotErrorMsg.contains("Should be a valid date in yyyy-dd-mm format"));
        Allure.description("Validates that the system enforces proper date format validation by preventing submission " +
                "when an invalid date format is entered in the leave application form.");
    }
}
