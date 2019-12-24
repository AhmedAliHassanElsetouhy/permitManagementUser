package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "تسجيل الخروج")
	public WebElement logoutBtn;

	@FindBy(linkText = "طلب تصريح جديد")
	WebElement requestPermitBtn;

	// @FindBy(linkText = "إدارة التصاريح")
	@FindBy(xpath = "//*[text()='إدارة التصاريح']")
	public WebElement permitManagementBtn;

	@FindBy(xpath = "/html/body/div[2]/div")
	public WebElement homePageText;

	public void logoutFun() {
		clickButton(logoutBtn);
	}

	public void openPermitRequestPageFun() {
		clickButton(requestPermitBtn);
	}

	public void openPermitManagementListFun() {
		clickButton(permitManagementBtn);
	}

}
