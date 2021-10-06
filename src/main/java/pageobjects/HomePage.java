package pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.CommonMethods;

/**
 * All the page objects and methods on the Vivino home page
 */
public class HomePage extends CommonMethods {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

		// TODO Auto-generated constructor stub
	}

	/**
	 * Page factory pattern used for the web elements
	 */

	@FindBy(xpath = "//form[contains(@class,'searchBar')]/input")
	WebElement searchInput;
	@FindBy(xpath = "//div[@class='wine-card__content']/div/span[1]/a")
	List<WebElement> WineLinkList;
	@FindBy(xpath = "//div[@class='alert alert-warning text-center']")
	WebElement noResultMessage;
	@FindBy(xpath = "//div[@class='wine-card__content']/div/span[1]/a/span")
	List<WebElement> WineTitleList;
	@FindBy(xpath = "//div[@class='wine-card__content']/div/span[2]/a[1]")
	List<WebElement> WineCityList;
	@FindBy(xpath = "//div[@class='wine-card__content']/div/span[2]/a[2]")
	List<WebElement> WineCountryList;
	@FindBy(xpath = "//div[@class='row header breadCrumbs']//span[@class='headline']/a")
	WebElement WineHeader;
	@FindBy(xpath = "//div[@class='row header breadCrumbs']//span[2]")
	WebElement WineTitle;
	@FindBy(xpath = "//div[@class='row rating']//span[1]/a")
	WebElement WineCountry;
	@FindBy(xpath = "//div[@class='row rating']//span[2]/a")
	WebElement WineCity;
	@FindBy(xpath = "//div[@class='row rating']//span/a")
	List<WebElement> WineAttributeList;

	/**
	 * Searching for any keyword
	 * 
	 * @param Search <keyword> as input
	 */
	public void searchForWine(String Input) throws InterruptedException {
		searchInput.sendKeys(Input);
		searchInput.sendKeys(Keys.ENTER);
		try {
			waitForElement(WineLinkList.get(0));
		} catch (Exception e) {
			Assert.assertEquals(noResultMessage.getText(), "Sorry, we couldn't find any wines matching your keywords");
			System.out.println("No Results found for the search keyword");
		}

	}

	/**
	 * To click on any random item listed in the search results page
	 */

	public void ClickOnAnyRandomItem() throws InterruptedException {
		Random rand = new Random();
		int item = rand.nextInt(WineLinkList.size() - 1);
		WineLinkList.get(item).click();
		waitForElement(WineHeader);

	}

	/**
	 * To get all the wine titles on the search results page
	 * 
	 * @return Titles as an array list
	 */
	public ArrayList<String> getWineTitleFromSearchResults() {
		ArrayList<String> Title = new ArrayList<String>();
		for (int i = 0; i < WineTitleList.size(); i++) {
			Title.add(WineTitleList.get(i).getText().toString());
		}
		return Title;
	}

	/**
	 * To get all the countries on the search results page
	 * 
	 * @return Countries as an array list
	 */
	public ArrayList<String> getWineCountryFromSearchResults() {
		ArrayList<String> Country = new ArrayList<String>();
		for (int i = 0; i < WineCountryList.size(); i++) {
			Country.add(WineCountryList.get(i).getText().toString());
		}
		return Country;
	}

	/**
	 * To get all the cities on the search results page
	 * 
	 * @return Cities as an array list
	 */
	public ArrayList<String> getWineCityFromSearchResults() {
		ArrayList<String> Country = new ArrayList<String>();
		for (int i = 0; i < WineCityList.size(); i++) {
			Country.add(WineCityList.get(i).getText().toString());
		}
		return Country;
	}

	/**
	 * To get the header attribute from the summary page of the selected wine
	 * 
	 * @return Header
	 */
	public String getWineHeader() {

		return WineHeader.getText().toString();
	}

	/**
	 * To get the Title attribute from the summary page of the selected wine
	 * 
	 * @return Title
	 */
	public String getWineTitle() {

		return WineTitle.getText().toString();

	}

	/**
	 * To get the country attribute from the summary page of the selected wine
	 * 
	 * @return Country
	 */
	public String getWineCountry() {

		return WineCountry.getText().toString();

	}

	/**
	 * To get the city attribute from the summary page of the selected wine
	 * 
	 * @return City
	 */
	public String getWineCity() {

		return WineCity.getText().toString();

	}

	/**
	 * To get all the attributes from the summary page of the selected wine
	 * 
	 * @return attributes as an array list
	 */

	public ArrayList<String> getWineAttributesFromSummary() {
		ArrayList<String> Country = new ArrayList<String>();
		for (int i = 0; i < WineAttributeList.size(); i++) {
			Country.add(WineAttributeList.get(i).getText().toString());
		}
		Country.add(WineHeader.getText()); // Add Wine Header to the attributes list.
		Country.add(WineTitle.getText()); // Add Wine Title also to the attributes list.
		return Country;
	}

	/**
	 * To verify at least one attribute (title, country, etc) of each item (found
	 * wine) from parsed search results contains <keyword> Log in (Print) which wine
	 * and attributes contain <keyword>` and which do not Click on a random wine's
	 * title Check that each attribute value is equal to one of those stored in the
	 * stored array list from the search results page Check whether at least one
	 * attribute contains `<keyword>` and log which one
	 * 
	 * @param Search <keyword> as input
	 */

	public void validateSearchKeywordOnSearchResults(String SearchKeyword) throws InterruptedException {
		ArrayList<String> WineTitle = new ArrayList<String>();
		ArrayList<String> WineCountry = new ArrayList<String>();
		ArrayList<String> WineCity = new ArrayList<String>();

		for (int i = 0; i < getWineTitleFromSearchResults().size(); i++) {
			if (getWineTitleFromSearchResults().get(i).contains(SearchKeyword)
					|| getWineCountryFromSearchResults().get(i).contains(SearchKeyword)
					|| getWineCityFromSearchResults().get(i).contains(SearchKeyword)) {
				System.out
						.println(getWineTitleFromSearchResults().get(i) + ": Wine contains keyword: " + SearchKeyword);
				System.out.println("Country, City : " + getWineCountryFromSearchResults().get(i) + ", "
						+ getWineCityFromSearchResults().get(i));
				System.out.println("---------------------");
			} else {
				System.out.println(getWineTitleFromSearchResults().get(i) + ": Wine does not contains the keyword: "
						+ SearchKeyword);
				System.out.println("Country, City : " + getWineCountryFromSearchResults().get(i) + ", "
						+ getWineCityFromSearchResults().get(i));
				System.out.println("---------------------");
			}

			WineTitle.add(getWineTitleFromSearchResults().get(i));
			WineCountry.add(getWineCountryFromSearchResults().get(i));
			WineCity.add(getWineCityFromSearchResults().get(i));
		}

		ClickOnAnyRandomItem(); // Click on random wine's title

		for (int i = 0; i < WineTitle.size(); i++) {
			if (WineTitle.get(i).contains(getWineHeader() + " " + getWineTitle())) {
				Assert.assertEquals(WineTitle.get(i), getWineHeader() + " " + getWineTitle());
				Assert.assertEquals(WineCountry.get(i), getWineCountry());
				Assert.assertEquals(WineCity.get(i), getWineCity());
				System.out.println("Selected wine details : " + getWineHeader() + " " + getWineTitle() + ", "
						+ getWineCountry() + ", " + getWineCity());
			}
		}

		for (String item : getWineAttributesFromSummary()) {
			if (item.contains(SearchKeyword)) {
				System.out.println("Attribute: " + item + " contains search keyword");
			}
		}

	}

}
