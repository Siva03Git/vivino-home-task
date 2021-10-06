package testscripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import browsersetup.BrowserSetup;
import pageobjects.HomePage;
import utility.CommonMethods;

public class TestScriptSearchWine extends BrowserSetup {
	@BeforeTest
	public void init() throws InterruptedException {

		home = new HomePage(driver);
		common = new CommonMethods(driver);
	}

	/**
	 * Test case to validate Search functionality and to validate search keyword on
	 * search results page and summary page. Update test data search 'Keyword' on
	 * the Vivino_TestData.xlsx
	 */
	@Test
	public void testSerachResults() throws Exception {
		String Keyword = common.getCellData("VivinoTestData", "Search_Text");
		home.searchForWine(Keyword);
		home.validateSearchKeywordOnSearchResults(Keyword);

	}
}
