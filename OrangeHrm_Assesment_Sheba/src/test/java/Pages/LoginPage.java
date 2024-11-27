package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name = "username")
    WebElement txtUserName;
    @FindBy(name="password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;

    @FindBy(className="oxd-alert-content-text")
    public List<WebElement> lblCredentials;

    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String adminUser, String adminPass) {
        txtUserName.sendKeys(adminUser);
        txtPassword.sendKeys(adminPass);
        btnSubmit.click();
    }
    public void doLoginWithInvalidCreds(String adminUser, String adminPass){
        txtUserName.clear();
        txtUserName.sendKeys(adminUser);
        txtPassword.clear();
        txtPassword.sendKeys(adminPass);
        btnSubmit.click();
    }
}
