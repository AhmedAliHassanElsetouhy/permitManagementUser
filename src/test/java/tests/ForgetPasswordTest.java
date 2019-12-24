package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.DefaultPage;
import pages.ForgetPasswordPage;
import pages.HomePage;
import pages.LoginPage;

public class ForgetPasswordTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	ForgetPasswordPage forgetPasswordPage;
	// String email = "ahmed.ali.hassan.elsetouhy@gmail.com";
	String invalidEmail = fakeData.internet().emailAddress();
	// String password = "P@55word";

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
	public void openForgetPasswordFormTest() {
		loginPage = new LoginPage(driver);
		forgetPasswordPage = new ForgetPasswordPage(driver);
		loginPage.openForgetPassForm();
		Assert.assertTrue(forgetPasswordPage.forgetPassFormHeaderMsg.getText().equals("هل نسيت كلمة المرور ؟"));
	}

	@Test(priority = 3, dependsOnMethods = { "openForgetPasswordFormTest" })
	public void emptydataForgetPasswordFormTest() {
		loginPage = new LoginPage(driver);
		forgetPasswordPage = new ForgetPasswordPage(driver);
		forgetPasswordPage.sendForgottenEmailBtnFun();
		Assert.assertTrue(forgetPasswordPage.emailValidationMsg.getText().equals("البريد الاكتروني مطلوب"));
	}

	@Test(priority = 4, dependsOnMethods = { "emptydataForgetPasswordFormTest" })
	public void invalidDataForgetPasswordFormTest() throws AWTException {
		forgetPasswordPage = new ForgetPasswordPage(driver);
		forgetPasswordPage.forgetPassFun(invalidEmail);
		forgetPasswordPage.sendForgottenEmailBtnFun();
		// Assert.assertTrue(forgetPasswordPage.invalidEmailValidationMsg.getText().equals("البريد
		// الاكتروني غير صحيح"));
		System.out.println(forgetPasswordPage.doesnotExistEmailValidationMsg.getText());
		Assert.assertTrue(forgetPasswordPage.doesnotExistEmailValidationMsg.getText()
				.contains("البريد الإلكتروني المدخل غير موجود بالنظام")
				|| forgetPasswordPage.invalidEmailValidationMsg.getText().contains("البريد الإكتروني غير صحيح"));
		// forgetPasswordPage.refreshPage();
		forgetPasswordPage.emailFieldTxt.clear();
	}

	@Test(priority = 5, dependsOnMethods = { "invalidDataForgetPasswordFormTest" })
	public void forgetPasswordFormTest() throws IOException {
		forgetPasswordPage = new ForgetPasswordPage(driver);
		ExcelReader ER = new ExcelReader();
		forgetPasswordPage.forgetPassFun(ER.getExcelData(0, 2)[1][1]);
		forgetPasswordPage.sendForgottenEmailBtnFun();
		Assert.assertTrue(forgetPasswordPage.confirmPassHeaderMsg.getText().contains("تأكيد تغيير كلمة المرور.")
				|| forgetPasswordPage.reviewEmailMsg.getText()
						.contains("من فضلك راجع بريدك الإلكتروني لتغيير كلمة السر"));
	}

}