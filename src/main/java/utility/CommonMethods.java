package utility;

import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonMethods extends Configuration {

	private WebDriverWait wait;
	protected WebDriver driver;
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 15);

	}

	/**
	 * To wait for an element to be present in the page.
	 * 
	 * @param element WebElement
	 */
	public void waitForElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * To fetch a cell data from the Vivino_TestData.xlsx Pass the testDataPath from
	 * Configuration.java class
	 * 
	 * @param SheetName and Key value to select.
	 */
	public String getCellData(String SheetName, String keyValue) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(testDataPath);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int rowCount = ExcelWSheet.getLastRowNum();
		String Value = "";
		for (int i = 1; i <= rowCount; i++) {

			Cell = ExcelWSheet.getRow(i).getCell(0);
			String CellData = Cell.getStringCellValue();

			if (CellData.equals(keyValue)) {
				Value = ExcelWSheet.getRow(i).getCell(1).getStringCellValue().toString().trim();
				break;
			}
		}
		return Value;

	}

}
