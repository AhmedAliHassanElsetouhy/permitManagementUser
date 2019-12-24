package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordPage extends PageBase {

	public ForgetPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='هل نسيت كلمة المرور ؟']")
	public WebElement forgetPassFormHeaderMsg;

	@FindBy(xpath = "//*[text()='ارسال الرابط الي البريد الإلكتروني']")
	WebElement sendForgottenEmailBtn;

	@FindBy(id = "EmailRequired")
	public WebElement emailValidationMsg;

	
	@FindBy(id = "EmailInvalid")
	public WebElement invalidEmailValidationMsg;
	
	// @FindBy(xpath = "//*[text(),'البريد الإلكتروني غير صحيح']")
	@FindBy(id = "EmailDoesNotExist")
	public WebElement doesnotExistEmailValidationMsg;

	@FindBy(id = "Email")
	public WebElement emailFieldTxt;
	
	@FindBy(xpath="//*[text()='تأكيد تغيير كلمة المرور.']")
	public WebElement confirmPassHeaderMsg;

	@FindBy(xpath="//*[text(),'  من فضلك راجع بريدك الإلكتروني لتغيير كلمة السر']")
	public WebElement reviewEmailMsg;
	
	public void sendForgottenEmailBtnFun() {
		clickButton(sendForgottenEmailBtn);
	}

	public void forgetPassFun(String email) {
		setTextElementText(emailFieldTxt, email);
	}

}
