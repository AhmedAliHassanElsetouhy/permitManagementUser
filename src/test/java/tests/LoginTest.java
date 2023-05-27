package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;
import pages.HomePage;
import pages.LoginPage;
public class LoginTest extends TestBase {
	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	// String email = "ahmed.ali.hassan.elsetouhy@gmail.com";
	// String password = "P@55word";
	@Test(priority = 1, alwaysRun = true)
	public void openLoginFormTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		loginPage = new LoginPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.loginFormHeaderMsg.getText().equals("تسجيل الدخول"));
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}
	@Test(priority = 2, dependsOnMethods = { "openLoginFormTest" })
	public void emptyDataLoginFormTest() {
		loginPage = new LoginPage(driver);
		loginPage.submitLoginformBtn();
		Assert.assertTrue(loginPage.emailRequiredMsg.getText().equals("البريد الإلكتروني مطلوب"));
		Assert.assertTrue(loginPage.passwordRequiredMsg.getText().equals("كلمة المرور مطلوبة"));
	}
	@Test(priority = 3, dependsOnMethods = { "emptydataLoginFormTest" })
	public void emptyEmailFieldLoginFormTest() throws AWTException, IOException {
		loginPage = new LoginPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun("", ER.getExcelData(0, 2)[2][1]);
		loginPage.submitLoginformBtn();
		Assert.assertTrue(loginPage.emailRequiredMsg.getText().equals("البريد الإلكتروني مطلوب"));
		// loginPage.refreshPage();
		loginPage.passwordFieldTxt.clear();
	}
	@Test(priority = 4, dependsOnMethods = { "emptyEmailFieldLoginFormTest" })
	public void emptyPasswordFieldLoginFormTest() throws AWTException, IOException {
		loginPage = new LoginPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], "");
		loginPage.submitLoginformBtn();
		Assert.assertTrue(loginPage.passwordRequiredMsg.getText().equals("كلمة المرور مطلوبة"));
		// loginPage.refreshPage();
		loginPage.emailFieldTxt.clear();
	}
	@Test(priority = 5, dependsOnMethods = { "emptyPasswordFieldLoginFormTest" })
	public void makeLoginFormTest() throws AWTException, IOException {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		loginPage.submitLoginformBtn();
		Assert.assertTrue(homePage.logoutBtn.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "makeLoginFormTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginBtn.isDisplayed());
	}
}