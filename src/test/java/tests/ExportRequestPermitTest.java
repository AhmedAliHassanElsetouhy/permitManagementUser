package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.DefaultPage;
import pages.ExportPermitFormPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RequestPermitPage;

public class ExportRequestPermitTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	RequestPermitPage requestPermitPage;
	// String email = "ahmed.ali.hassan.elsetouhy@gmail.com";
	// String password = "P@55word";
	String exportPermitType = "تصريح تصدير نفايات";
	String importPermitType = "تصريح إستيراد نفايات";
	ExportPermitFormPage expertPermitFormPage;
	String impComARName = fakeData.name().firstName();
	String impComENName = fakeData.name().fullName();
	int wasteAmount = 1000;
	int wasteTypeIndex = 3;
	int impCountryIndex = 3;
	int crossCountryIndex = 68;
	int transferWayIndex = 3;
	int terminalIndex = 5;
	String conStartDate = "2020-05-02";
	String conEndDate = "2029-05-02";
	String wasteSource = fakeData.name().fullName();
	int exportReasonIndex = 1;
	String folderName = "اخطار النقل.png";

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
	public void exportPermitRequestTest() {
		homePage = new HomePage(driver);
		requestPermitPage = new RequestPermitPage(driver);
		expertPermitFormPage = new ExportPermitFormPage(driver);
		homePage.openPermitRequestPageFun();
		requestPermitPage.selectPermitRequestTypeFun(exportPermitType);
		Assert.assertTrue(expertPermitFormPage.exportFormPageHeader.isDisplayed());
	}

	// @Test(priority = 5, dependsOnMethods = { "exportPermitRequestTest" })
	// public void emptyExportPermitForm() {
	// expertPermitFormPage = new ExportPermitFormPage(driver);
	// expertPermitFormPage.sendRequestFun();
	// Assert.assertTrue(expertPermitFormPage.importCompanyARReqValidationMsg.isDisplayed());
	// Assert.assertTrue(expertPermitFormPage.importCountryReqValidationMsg.isDisplayed());
	// Assert.assertTrue(expertPermitFormPage.terminalReqValidationMsg.isDisplayed());
	// Assert.assertTrue(expertPermitFormPage.contractStartDateReqValidationMsg.isDisplayed());
	// Assert.assertTrue(expertPermitFormPage.contractEndDateReqValidationMsg.isDisplayed());
	// Assert.assertTrue(expertPermitFormPage.wasteSourceReqValidationMsg.isDisplayed());
	// Assert.assertTrue(expertPermitFormPage.exportReasonReqValidationMsg.isDisplayed());
	// }
	//
	// @Test(priority = 6, dependsOnMethods = { "exportPermitRequestTest" })
	// public void validateWasteTypeListFun() throws InterruptedException,
	// AWTException {
	// expertPermitFormPage = new ExportPermitFormPage(driver);
	// expertPermitFormPage.exportRequestFun(3);
	// Assert.assertTrue(expertPermitFormPage.insurancePolictBtn.isEnabled());
	// Assert.assertTrue(expertPermitFormPage.transportNotificationFileBtn.isEnabled());
	// }
	//
	// @Test(priority = 7, dependsOnMethods = { "exportPermitRequestTest" })
	// public void validateanalyticalTypeListFun() throws InterruptedException,
	// AWTException {
	// expertPermitFormPage = new ExportPermitFormPage(driver);
	// expertPermitFormPage.exportRequestFun(2);
	// Assert.assertTrue(expertPermitFormPage.analyticalLabResultBtn.isEnabled());
	// }
	//
	// @Test(priority = 8, dependsOnMethods = { "exportPermitRequestTest" })
	// public void validatepassByCountryAcceptanceListFun() throws
	// InterruptedException, AWTException {
	// expertPermitFormPage = new ExportPermitFormPage(driver);
	// expertPermitFormPage.passByCountryFun(68);
	// Assert.assertTrue(expertPermitFormPage.passByCountryAcceptanceResultBtn.isEnabled());
	// }

	@Test(priority = 9, dependsOnMethods = { "exportPermitRequestTest" })
	public void validFillExportPermitForm() throws InterruptedException, AWTException {
		expertPermitFormPage = new ExportPermitFormPage(driver);
		expertPermitFormPage.exportRequestFun(impComARName, impComENName, wasteAmount, wasteTypeIndex, impCountryIndex,
				crossCountryIndex, transferWayIndex, terminalIndex, conStartDate, conEndDate, wasteSource,
				exportReasonIndex, folderName);
		expertPermitFormPage.upload_files(folderName);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", expertPermitFormPage.sendRequestBtn);
		// expertPermitFormPage.sendRequestFun();

	}

	@Test(priority = 10, dependsOnMethods = { "validFillExportPermitForm" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginBtn.isDisplayed());
	}
}