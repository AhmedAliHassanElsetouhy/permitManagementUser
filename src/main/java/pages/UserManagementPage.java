package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserManagementPage extends PageBase {

	public UserManagementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='مراجعة]")
	WebElement reviewLink;

	public void reviewFun() {
		clickButton(reviewLink);
	}
}
