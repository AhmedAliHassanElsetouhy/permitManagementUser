package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

	protected static WebDriver driver;
	public JavascriptExecutor jse;
	public Select select;

	// create constructor
	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	protected static void moveToElement(WebElement elementToMoveTo) {
		Actions generateReportHoverAction = new Actions(driver);
		generateReportHoverAction.moveToElement(elementToMoveTo).perform();
	}

	public void mouseHover(WebElement elementToHoverOn) {
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(elementToHoverOn).perform();
	}

	protected static void clickNotClickableElement(WebElement notClickableElement) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", notClickableElement);
	}

	public void jsClick(WebElement jsClickBtn) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", jsClickBtn);
	}

	protected static void clickButton(WebElement button) {
		button.click();
	}

	protected static void setTextElementText(WebElement textElement, String value) {
		textElement.sendKeys(value);
	}

	protected static void setTextElementText(WebElement textElement, int value) {
		textElement.sendKeys(Integer.toString(value));
	}

	public void scrollToBottom() {
		jse.executeScript("scrollBy(0,2500)");
	}

	public void doupleClick(WebElement dClick) {
		dClick.click();
		dClick.click();
	}

	public void selectItemWithIndex(WebElement menuItem, int index) {
		select = new Select(menuItem);
		select.selectByIndex(index);
	}

	public void selectItemWithVisible(WebElement menuItem, String selectedElement) {
		select = new Select(menuItem);
		select.selectByVisibleText(selectedElement);
	}

	public String split(String strMain, String from) {
		String[] arrSplit = strMain.split(from);
		return arrSplit[1];
	}

	// public void PointSample(WebElement ele) {
	// Actions actionBuilder = new Actions(driver);
	// // actionBuilder.clickAndHold(ele).moveByOffset(552,
	// 309).release().perform();
	// actionBuilder.moveToElement(ele).moveByOffset(552, 309).click().perform();
	// }

	public void triggerBothPopups(WebElement popupView) {
		popupView.click();
		driver.switchTo().alert().accept();
	}

	public void testFileUpload(String folderName, WebElement uploadFileIcon) throws InterruptedException {
		String imagePath = System.getProperty("user.dir") + "/Uploads/" + folderName;
		clickButton(uploadFileIcon);
		uploadFileIcon.sendKeys(imagePath);
		Thread.sleep(3000);
		// Assert.assertEquals(folderName, uploadedFiles.getText());
	}

	public void FileUploadWithRobot(WebElement fileUploader, String folderName)
			throws InterruptedException, AWTException {
		// File Name
		String imagePath = System.getProperty("user.dir") + "\\Uploads\\" + folderName;

		// Transferable File Name declaration
		// CTRL + C copy image path
		StringSelection selection = new StringSelection(imagePath);

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
		// System.out.println(imagePath);

		clickButton(fileUploader);

		// Code using robot class for file upload
		Robot robot = new Robot();
		robot.delay(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000);
		// Click on CTRL + V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(1000);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void rightClick(WebElement rightClcElement) {
		Actions builder = new Actions(driver);
		builder.contextClick(rightClcElement).perform();
	}

	public void refreshPage() throws AWTException {
		// throw new PendingException();
		// log.info("Now refresh the page to keep the session valid");
		// or blow
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);
		;
	}
}