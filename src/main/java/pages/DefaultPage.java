package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DefaultPage extends PageBase{

	public DefaultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(linkText="إنشاء حساب جديد")
	WebElement createAccBtn;
	
	@FindBy(id="loginLink")
	public WebElement loginBtn;
	
	public void openRegisterForm() {
		clickButton(createAccBtn);
	}
	
	public void openLoginForm() {
		clickButton(loginBtn);
	}
}
