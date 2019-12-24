package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;
import pages.ExportPermitFormPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationFormPage;
import pages.RequestPermitPage;

public class RequestPermitTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	RequestPermitPage requestPermitPage;
	RegistrationFormPage registrationFormPage;
	// String email = "ahmed.ali.hassan.elsetouhy@gmail.com";
	// String password = "P@55word";
	String exportPermitType = "تصريح تصدير نفايات";
	String importPermitType = "تصريح إستيراد نفايات";
	ExportPermitFormPage exportPermitFormPage;

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
	public void requestPermitTest() {
		homePage = new HomePage(driver);
		requestPermitPage = new RequestPermitPage(driver);
		registrationFormPage = new RegistrationFormPage(driver);
		exportPermitFormPage = new ExportPermitFormPage(driver);
		homePage.openPermitRequestPageFun();
		// if (requestPermitPage.licenceExpiredMsg.isEnabled() ||
		// requestPermitPage.nationalIdExpiredMsg.isEnabled()) {
		if (!exportPermitFormPage.selectPermitTypeHeader.isDisplayed()) {
			requestPermitPage.openUpdateDataFun();

			registrationFormPage.companyLicenseEndDateTxtBox.sendKeys("2026-12-31");
			registrationFormPage.companyLicenseEndDateTxtBox.sendKeys(Keys.TAB);

			registrationFormPage.nationalEndDateTxtBox.sendKeys("2026-12-31");
			registrationFormPage.nationalEndDateTxtBox.sendKeys(Keys.TAB);

			registrationFormPage.submitRegisterFormFun();
		} else {
			Assert.assertTrue(exportPermitFormPage.askNewPermitPageHeader.isDisplayed());
		}
	}

	@Test(priority = 4, dependsOnMethods = { "requestPermitTest" })
	public void selectPermitTypeTest() throws AWTException {
		homePage = new HomePage(driver);
		requestPermitPage = new RequestPermitPage(driver);
		homePage.openPermitRequestPageFun();
		requestPermitPage.nextPageFun();
		Assert.assertTrue(requestPermitPage.permitTypeRequiredMsg.isDisplayed());
		requestPermitPage.refreshPage();
	}

	@Test(priority = 5, dependsOnMethods = { "selectPermitTypeTest" })
	public void exportPermitRequestTest() {
		requestPermitPage = new RequestPermitPage(driver);
		exportPermitFormPage = new ExportPermitFormPage(driver);
		requestPermitPage.selectPermitRequestTypeFun(exportPermitType);
		Assert.assertTrue(exportPermitFormPage.exportFormPageHeader.isDisplayed());
	}

	// @Test(priority = 6, dependsOnMethods = { "makeLoginFormTest" })
	// public void makeLogoutTest() throws AWTException {
	// homePage = new HomePage(driver);
	// defaultPage = new DefaultPage(driver);
	// homePage.logoutFun();
	// Assert.assertTrue(defaultPage.loginBtn.isDisplayed());
	// }
	
	@Test(priority = 6, dependsOnMethods = { "exportPermitRequestTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginBtn.isDisplayed());
	}
}
