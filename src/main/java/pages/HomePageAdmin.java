package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageAdmin extends PageBase {

	public HomePageAdmin(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='إدارة المستخدمين]")
	WebElement usersManagementLink;

	public void openUsersManagementFun() {
		clickButton(usersManagementLink);
	}
}
