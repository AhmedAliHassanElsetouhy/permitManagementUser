package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PermitManagementPage;

public class PermitManagementListTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	DefaultPage defaultPage;
	// String email = "ahmed.ali.hassan.elsetouhy@gmail.com";
	// String password = "P@55word";
	PermitManagementPage permitManagementPage;

	@Test(priority = 1)
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
	public void makeLoginFormTest() throws AWTException, IOException {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		loginPage.submitLoginformBtn();
		Assert.assertTrue(homePage.logoutBtn.isDisplayed());
		System.out.println(homePage.homePageText.getText());
	}

	@Test(priority = 3, dependsOnMethods = { "makeLoginFormTest" })
	public void openPermitMagementListTest() {
		homePage = new HomePage(driver);
		permitManagementPage = new PermitManagementPage(driver);
		homePage.openPermitManagementListFun();
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// homePage.permitManagementBtn);

		Assert.assertTrue(permitManagementPage.permitManagementHeader.isDisplayed());
	}
	
	@Test(priority = 4, dependsOnMethods = { "openPermitMagementListTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginBtn.isDisplayed());
	}
}