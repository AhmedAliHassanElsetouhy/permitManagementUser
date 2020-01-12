package pages;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationFormPage extends PageBase {

	public RegistrationFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='إنشاء حساب جديد']")
	public WebElement registrationFormHeaderTxt;

	@FindBy(id = "FullName")
	public WebElement fullNameTxtBox;

	@FindBy(id = "NationalId")
	public WebElement nationalIdTxtBox;

	@FindBy(id = "NationalIdEndDate")
	public WebElement registerNationalEndDateTxtBox;

	@FindBy(id = "User_NationalIdEndDate")
	public WebElement nationalEndDateTxtBox;

	@FindBy(xpath = "//label[@for='NationalIdImage']")
	// @FindBy(id = "NationalIdImage")
	public WebElement nationalIdImageModel;

	@FindBy(id = "NationalityId")
	public WebElement nationalityIdList;

	@FindBy(id = "WorkPhone")
	public WebElement workPhoneTxtBox;

	@FindBy(id = "User_Company_LicenseEndDate")
	public WebElement companyLicenseEndDateTxtBox;

	@FindBy(xpath = "//input[@value='تسجيل']")
	public WebElement registerBtn;

	@FindBy(xpath = "//div[@class='modal-content']")
	WebElement confirmRegisterPopupWindow;

	@FindBy(xpath = "//label[@for='UpdateProfileConfirmation']")
	WebElement acceptUpdateDataCheckBox;

	@FindBy(xpath = "//input[@value='تأكيد']")
	WebElement confirmBtn;

	@FindBy(id = "Mobile")
	WebElement mobileTxtBox;

	@FindBy(id = "Email")
	WebElement emailTxtBox;

	@FindBy(id = "Password")
	WebElement passwordTxtBox;

	@FindBy(id = "ConfirmPassword")
	WebElement confirmPasswordTxtBox;

	@FindBy(id = "CompanyNameArabic")
	WebElement companyArabicNameTxtBox;

	@FindBy(id = "CompanyNameEnglish")
	WebElement companyEnglishNameTxtBox;

	@FindBy(xpath = "//label[@for='DelegationLetter']")
	WebElement delegationLetterFile;

	@FindBy(id = "LicenseNumber")
	WebElement licenseNumberTxtBox;

	@FindBy(xpath = "//label[@for='LicenseImage']")
	WebElement licenseImage;

	@FindBy(id = "LicenseEndDate")
	WebElement licenseEndDateTxtBox;

	@FindBy(id = "CompanyAdderess")
	WebElement companyAddressTxtBox;

	@FindBy(id = "CompanyPACINumber")
	WebElement PACITxtBox;
	
	@FindBy(id="CaptchaImage")
	public WebElement captchaImage;

	public void confirmRegisterForm() {
		clickButton(acceptUpdateDataCheckBox);
		clickButton(confirmBtn);
	}

	public void submitRegisterFormFun() {
		clickButton(registerBtn);
	}

	public void registerFormData(String fullName, String nationalId, String nationalEndDate, String nationalIdImage,
			int NationalityIdIndex, String workPhone, String mobile, String email, String password,
			String companyARName, String companyENName, String licenseNumber, String companyAddress, String PACI)
			throws InterruptedException, AWTException {
		setTextElementText(fullNameTxtBox, fullName);
		setTextElementText(nationalIdTxtBox, nationalId);
		setTextElementText(registerNationalEndDateTxtBox, nationalEndDate);
		// nationalEndDateTxtBox.submit();
		registerNationalEndDateTxtBox.sendKeys(Keys.TAB);
		// testFileUpload(nationalIdImage, nationalIdImageModel);
		FileUploadWithRobot(nationalIdImageModel, nationalIdImage);
		selectItemWithIndex(nationalityIdList, NationalityIdIndex);
		setTextElementText(workPhoneTxtBox, workPhone);
		setTextElementText(mobileTxtBox, mobile);
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, password);
		setTextElementText(companyArabicNameTxtBox, companyARName);
		setTextElementText(companyEnglishNameTxtBox, companyENName);
		FileUploadWithRobot(delegationLetterFile, nationalIdImage);
		setTextElementText(licenseNumberTxtBox, licenseNumber);
		FileUploadWithRobot(licenseImage, nationalIdImage);
		setTextElementText(licenseEndDateTxtBox, nationalEndDate);
		licenseEndDateTxtBox.sendKeys(Keys.TAB);
		setTextElementText(companyAddressTxtBox, companyAddress);
		setTextElementText(PACITxtBox, PACI);
	}
}