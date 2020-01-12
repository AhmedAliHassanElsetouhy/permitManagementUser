package tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.DefaultPage;
import pages.HomePage;
import pages.RegistrationFormPage;

public class RegistrationFormTest extends TestBase {

	RegistrationFormPage registrationFormPage;
	DefaultPage defaultPage;

	Faker fakeDataAR = new Faker(new Locale("AR"));

	Faker fakeData = new Faker();
	String fullName = fakeData.name().firstName();
	String nationalId = "273032401566";
	String nationalEndDate = "2020-05-02";
	String nationalIdImage = "اخطار النقل.png";
	int NationalityIdIndex = 4;
	String workPhone = fakeData.number().digits(8).toString();
	HomePage homePage;
	String mobile = fakeData.number().digits(8).toString();
	String email = fakeData.internet().emailAddress();
	// ariel.lubowitz@hotmail.com
	String password = "P@55word";

	String companyARName = fakeDataAR.name().fullName();
	String companyENName = (new Locale("EN", companyARName)).toString();
	String licenseNumber = fakeData.number().digits(8).toString();
	String companyAddress = fakeData.name().title();
	String PACI = fakeData.number().digits(8).toString();

	@Test(priority = 1)
	public void openRegisterFormTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		registrationFormPage = new RegistrationFormPage(driver);
		defaultPage.openRegisterForm();
		Assert.assertEquals(registrationFormPage.registrationFormHeaderTxt.getText(), "إنشاء حساب جديد");
		Assert.assertTrue(registrationFormPage.registrationFormHeaderTxt.getText().contains("إنشاء حساب جديد"));
	}

	@Test(priority = 2, dependsOnMethods = { "openRegisterFormTest" })
	public void submitRegisterFormTest() throws InterruptedException, AWTException {
		registrationFormPage = new RegistrationFormPage(driver);
		System.out.println(email + " " + password);
		registrationFormPage.registerFormData(fullName, nationalId, nationalEndDate, nationalIdImage,
				NationalityIdIndex, workPhone, mobile, email, password, companyARName, companyENName, licenseNumber,
				companyAddress, PACI);
		System.out.println(registrationFormPage.captchaImage.getText());

	}

}