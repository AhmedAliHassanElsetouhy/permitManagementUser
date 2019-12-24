package pages;

import org.openqa.selenium.WebDriver;

public class LoginAdminPage extends PageBase{
	public LoginAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static WebDriver driver;

	LoginPage loginPage;

	public void openAdmin() {
		driver.navigate().to("https://enterprise.emisk.org/eMISKWastePermitManagementAdmin/");
		loginPage = new LoginPage(driver);
		String userNameText = "yasser@emisk.org";
		String PasswordText = "Yasso?2468";
		loginPage.loginFun(userNameText, PasswordText);
	}

}
