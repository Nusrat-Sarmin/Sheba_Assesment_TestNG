package TestRunner;

import Pages.LoginPage;
import Setup.Setup;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTestRunner extends Setup {
    LoginPage loginPage;
    //DashboardPage dashboardPage;

    @Test(priority = 1, description = "User can not login with wrong creds")
    public void doLoginWithInvalidCreds() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage=new LoginPage(driver);
        Thread.sleep(3000);
        String adminUser= "wronguser";
        String adminPass="password";
        loginPage.doLoginWithInvalidCreds(adminUser,adminPass);

        String validationErrorActual = loginPage.lblCredentials.get(0).getText();
        String validationErrorExpected="Invalid credentials";

        Assert.assertEquals(validationErrorActual,validationErrorExpected);
        Allure.description("User can not login with wrong creds");
    }

    @Test(priority = 2, description = "Admin log in successfully")
    public void doLogin() {
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage = new LoginPage(driver);
        String adminUser= "Admin";
        String adminPass="admin123";
        loginPage.doLogin(adminUser,adminPass);
        String urlActual = driver.getCurrentUrl();
        String urlExpected ="index";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }

    /*@Test(priority = 3, description = "Admin profile image showing")
    public void isProfileImageExists(){
        // WebElement imgProfile = driver.findElement(By.className("oxd-userdropdown-img"));
        dashboardPage= new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.imgProfile.isDisplayed());
    }*/
}
