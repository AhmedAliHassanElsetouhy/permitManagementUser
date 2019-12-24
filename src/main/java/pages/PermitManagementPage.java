package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PermitManagementPage extends PageBase{

	public PermitManagementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//*[text()='إدارة التصاريح']")
	public WebElement permitManagementHeader;
}