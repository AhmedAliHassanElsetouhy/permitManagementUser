package pages;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExportPermitFormPage extends PageBase {

	public ExportPermitFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='طلب تصريح تصدير نفايات']")
	public WebElement exportFormPageHeader;

	@FindBy(xpath = "//*[text()='طلب تصريح جديد']")
	public WebElement askNewPermitPageHeader;

	@FindBy(xpath = "//*[text()='إختر نوع التصريح']")
	public WebElement selectPermitTypeHeader;

	@FindBy(id = "OtherCompanyNameAr")
	WebElement importCompanyARTxtBox;

	@FindBy(xpath = "//*[text()='اسم الشركة المستوردة مطلوب']")
	public WebElement importCompanyARReqValidationMsg;

	@FindBy(id = "OtherCompanyNameEn")
	WebElement importCompanyENTxtBox;

	@FindBy(id = "WasteAmountTons")
	WebElement wasteAmountTxtBox;

	@FindBy(id = "WasteTypeId")
	WebElement wasteTypeLst;

	@FindBy(id = "OtherCountryId")
	WebElement importCountryLst;

	@FindBy(xpath = "//*[text()='دولة الإستيراد مطلوبة']")
	public WebElement importCountryReqValidationMsg;

	@FindBy(id = "OtherCountryId")
	WebElement crossCountryLst;

	@FindBy(id = "TransportMeans")
	WebElement transferWayLst;

	@FindBy(xpath = "//*[text()='وسيلة النقل مطلوبة']")
	public WebElement terminalReqValidationMsg;

	@FindBy(id = "TerminalId")
	WebElement terminalLst;

	@FindBy(id = "ContractStartDate")
	WebElement contractStartDateTxtBox;

	@FindBy(xpath = "//*[text()='تاريخ بداية العقد مطلوب']")
	public WebElement contractStartDateReqValidationMsg;

	@FindBy(id = "ContractEndDate")
	WebElement contractEndDateTxtBox;

	@FindBy(xpath = "//*[text()='تاريخ نهاية العقد مطلوب']")
	public WebElement contractEndDateReqValidationMsg;

	@FindBy(id = "WasteSource")
	WebElement wasteSourceTxtBox;

	@FindBy(xpath = "//*[text()='مصدر النفاية مطلوب']")
	public WebElement wasteSourceReqValidationMsg;

	@FindBy(id = "ExpectedContainersCount")
	WebElement numOfContainersTxtox;

	@FindBy(id = "ReasonId")
	WebElement exportReasonLst;

	@FindBy(xpath = "//*[text()='الغرض من التصدير مطلوب']")
	public WebElement exportReasonReqValidationMsg;

	// @FindBy(xpath = "//*[text()='إرسال الطلب']")
	@FindBy(xpath = "//input[@value='إرسال الطلب']")
	// @FindBy(xpath = "//input[@class='btn btn-primary']")
	public WebElement sendRequestBtn;

	@FindBy(xpath = "//label[@for='InsurancePolicyFile']")
	public WebElement insurancePolictBtn;

	@FindBy(xpath = "//label[@for='ContractFile']")
	// @FindBy(id = "ContractFile")
	WebElement contractFileBtn;

	@FindBy(xpath = "//label[@for='ImportCountryAcceptanceFile']")
	WebElement importCountryBtn;

	@FindBy(id = "PassByCountryIds")
	public WebElement passByCountryLst;

	@FindBy(xpath = "//label[@for='PassByCountryAcceptanceFile']")
	public WebElement passByCountryAcceptanceResultBtn;

	@FindBy(xpath = "//label[@for='AnalysisLabResultsFile']")
	public WebElement analyticalLabResultBtn;

	@FindBy(xpath = "//label[@for='BankGuaranteeFile']")
	WebElement bankGuaranteeFileBtn;

	@FindBy(xpath = "//label[@for='ImportCompanyLicenseFile']")
	WebElement importCompanyLicenseFileBtn;

	@FindBy(xpath = "//label[@for='SeventhAnnexFile']")
	WebElement seventhAnnexFileBtn;

	@FindBy(xpath = "//label[@for='TransportNotificationFile']")
	public WebElement transportNotificationFileBtn;

	@FindBy(xpath = "//label[@for='CommitmentDocumentFile']")
	WebElement commitmentDocumentFileBtn;

	@FindBy(xpath = "//label[@for='PermitRequestConfirmation']")
	WebElement permitRequestCheckBox;

	@FindBy(xpath = "//*[text()='تأكيد']")
	WebElement confirmBtn;

	public void sendRequestFun() {
		// moveToElement(sendRequestBtn);
		// clickButton(sendRequestBtn);
		clickButton(permitRequestCheckBox);
		clickButton(confirmBtn);

	}

	public void passByCountryFun(int passByCountryIndex) throws InterruptedException, AWTException {
		selectItemWithIndex(passByCountryLst, passByCountryIndex);
	}

	public void exportRequestFun(int wasteTypeIndex) throws InterruptedException, AWTException {
		selectItemWithIndex(wasteTypeLst, wasteTypeIndex);
	}

	public void exportRequestFun(String impComARName, String impComENName, int wasteAmount, int wasteTypeIndex,
			int impCountryIndex, int crossCountryIndex, int transferWayIndex, int terminalIndex, String conStartDate,
			String conEndDate, String wasteSource, int exportReasonIndex, String folderName)
			throws InterruptedException, AWTException {
		setTextElementText(importCompanyARTxtBox, impComARName);
		setTextElementText(importCompanyENTxtBox, impComENName);
		wasteAmountTxtBox.clear();
		setTextElementText(wasteAmountTxtBox, wasteAmount);
		selectItemWithIndex(wasteTypeLst, wasteTypeIndex);
		selectItemWithIndex(importCountryLst, impCountryIndex);
		selectItemWithIndex(crossCountryLst, crossCountryIndex);
		Thread.sleep(1000);
		// clickButton(transferWayLst);
		selectItemWithIndex(transferWayLst, transferWayIndex);
		Thread.sleep(3000);
		clickButton(terminalLst);
		// selectItemWithIndex(terminalLst, terminalIndex);
		selectItemWithVisible(terminalLst, "طائرة");
		setTextElementText(contractStartDateTxtBox, conStartDate);
		contractStartDateTxtBox.sendKeys(Keys.TAB);
		setTextElementText(contractEndDateTxtBox, conEndDate);
		contractEndDateTxtBox.sendKeys(Keys.TAB);
		setTextElementText(wasteSourceTxtBox, wasteSource);
		setTextElementText(numOfContainersTxtox, (wasteAmount / 25) * 5);
		selectItemWithIndex(exportReasonLst, exportReasonIndex);
		Thread.sleep(1000);
	}

	public void upload_files(String folderName) throws InterruptedException, AWTException {
		FileUploadWithRobot(insurancePolictBtn, folderName);
		FileUploadWithRobot(contractFileBtn, folderName);
		FileUploadWithRobot(importCountryBtn, folderName);
		FileUploadWithRobot(bankGuaranteeFileBtn, folderName);
		FileUploadWithRobot(importCompanyLicenseFileBtn, folderName);
		FileUploadWithRobot(transportNotificationFileBtn, folderName);
		FileUploadWithRobot(commitmentDocumentFileBtn, folderName);
		// commitmentDocumentFileBtn.sendKeys(Keys.TAB);
		// sendRequestBtn.sendKeys(Keys.ENTER);
		// FileUploadWithRobot(seventhAnnexFileBtn, folderName);
	}
}