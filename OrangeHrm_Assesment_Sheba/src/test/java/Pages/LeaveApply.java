package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeaveApply {
    @FindBy(tagName = "a")
    public List<WebElement> sideBarTime; //3
    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    public List<WebElement> topBar; //0
    @FindBy(className = "oxd-select-text-input")
    public WebElement dropdownLeaveType;
    @FindBy(tagName = "input")
    public List<WebElement> inputDate; //1,2
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    public WebElement inLineErrorMsg;
    @FindBy(tagName = "button")
    public List<WebElement> btnApply; //3
    @FindBy(tagName = "textarea")
    public WebElement txtComment;
    @FindBy(css = ".oxd-text.oxd-text--p.orangehrm-leave-balance-text")
    public WebElement lblLeaveBalance;
    @FindBy(xpath = "//div[contains(text(),'2024-02-12 to 2024-05-12')]")
    public WebElement tableValidation;

    public LeaveApply(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void doLeaveApplyWithValidData(String fromDate, String toDate, String comment) throws InterruptedException {
        inputDate.get(1).sendKeys(Keys.CONTROL, "a");
        inputDate.get(1).sendKeys(Keys.BACK_SPACE);
        inputDate.get(1).sendKeys(fromDate);
        Thread.sleep(2000);

        inputDate.get(2).sendKeys(Keys.CONTROL, "a");
        inputDate.get(2).sendKeys(Keys.BACK_SPACE);
        inputDate.get(2).sendKeys(toDate);
        Thread.sleep(2000);

        txtComment.sendKeys(Keys.CONTROL, "a");
        txtComment.sendKeys(Keys.BACK_SPACE);
        txtComment.sendKeys(comment);
        Thread.sleep(2000);
        btnApply.get(3).click();
    }
}
