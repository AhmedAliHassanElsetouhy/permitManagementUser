package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RequestPermitPage extends PageBase {

	public RequestPermitPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "LicenseExpired")
	public WebElement licenceExpiredMsg;

	@FindBy(id = "NationalIdExpired")
	public WebElement nationalIdExpiredMsg;

	@FindBy(partialLinkText = "تحديث البيانات")
	WebElement updataDataLink;

	// @FindBy(id = "permission-type-dropdown")
	@FindBy(xpath = "//select[@class='form-control']")
	WebElement permitTypeLst;

	// @FindBy(xpath = "//*[text()='التالي']")
	@FindBy(xpath = "//input[@value='التالي']")
	WebElement nextBtn;

	@FindBy(id = "permissionTypeRequired")
	public WebElement permitTypeRequiredMsg;

	public void openUpdateDataFun() {
		clickButton(updataDataLink);
	}

	public void selectPermitRequestTypeFun(String permitType) {
		selectItemWithVisible(permitTypeLst, permitType);
		nextPageFun();
		// permitTypeLst.sendKeys(Keys.TAB);
		// permitTypeLst.sendKeys(Keys.ENTER);
	}

	public void nextPageFun() {
		clickButton(nextBtn);
	}
}