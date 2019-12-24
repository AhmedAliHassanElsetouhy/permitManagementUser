package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewPage extends PageBase {

	public ReviewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@value='قبول']")
	WebElement acceptBtn;

	public void acceptFun() {
		clickButton(acceptBtn);
	}

}
