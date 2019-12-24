package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// @FindBy(id = "userNameArea")
	// public WebElement userNameTxtBox;
	//
	// @FindBy(id = "passwordInput")
	// public WebElement passwordTxtBox;
	//
	// @FindBy(id = "kmsiArea")
	// WebElement keepMeLoggedIn;
	//
	// @FindBy(id = "submitButton")
	// public WebElement SubmitLoginBtn;
	//
	// @FindBy(id ="error")
	// public WebElement errorMsg;

	@FindBy(xpath = "//*[text()='تسجيل الدخول']")
	public WebElement loginFormHeaderMsg;

	@FindBy(xpath = "//*[@value='دخول']")
	WebElement loginBtn;

	@FindBy(xpath = "//*[text()='البريد الإلكتروني مطلوب']")
	public WebElement emailRequiredMsg;

	@FindBy(xpath = "//*[text()='كلمة المرور مطلوبة']")
	public WebElement passwordRequiredMsg;

	@FindBy(id = "Email")
	public WebElement emailFieldTxt;

	@FindBy(id = "Password")
	public WebElement passwordFieldTxt;

	// @FindBy(id="RememberMe")
	@FindBy(xpath = "//*[text()='تذكرني']")
	WebElement rememberMeTxtBox;

	@FindBy(linkText="نسيت كلمة المرور؟")
	public WebElement forgetPassLink;
	
	@FindBy(xpath="إنشاء حساب جديد")
	public WebElement registerLink;
	
	// public void loginFun(String userNameText, String PasswordText) {
	// setTextElementText(userNameTxtBox, userNameText);
	// setTextElementText(passwordTxtBox, PasswordText);
	// clickButton(keepMeLoggedIn);
	// clickButton(SubmitLoginBtn);
	// }

	public void submitLoginformBtn() {
		clickButton(loginBtn);
	}

	public void loginFun(String email, String password) {
		setTextElementText(emailFieldTxt, email);
		setTextElementText(passwordFieldTxt, password);
		clickButton(rememberMeTxtBox);
	}
	
	public void openForgetPassForm() {
		clickButton(forgetPassLink);
	}
	
	public void openRegisterForm() {
		clickButton(registerLink);
	}

}
