package TestClass;

import Core.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFavourites extends BaseSeleniumPage {

    /*
    item затем стоит использовать для FindBy с параметрами.
    Но сначала необходимо расширить аннотацию @FindBy ¯\_(ツ)_/¯
     */
    private String item;
    @FindBy(xpath = "//*[@data-marker='item-2639542363']")
    private WebElement favouriteElementByDataMarker;

    @FindBy(xpath = "//*[@data-marker='item-2639542363']/div[2]/a/p/strong")
    private WebElement favouriteElementTitle;

    public PageFavourites(String item) {
        this.item = item;
        PageFactory.initElements(driver, this);
    }

    public Boolean isFavouriteElementAdded() {
        return favouriteElementByDataMarker.isDisplayed();
    }

    public String getFavouriteElementTitle() {
        try {
            favouriteElementTitle.isDisplayed();
            return favouriteElementTitle.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isFavouriteElementAddedLast() {
        String xpath = "//*[@id=\"app\"]/div/div[4]/div/div/favorite-items-list/div/div/div[1]/div[2]/div[1]/div/div";
        return driver.findElement(By.xpath(xpath)).equals(favouriteElementByDataMarker);
    }

    public Boolean isFavouriteElementAddedNotLast() {
        String xpath = "//*[@id=\"app\"]/div/div[4]/div/div/favorite-items-list/div/div/div[1]/div[2]/div[1]/div/div";
        return driver.findElement(By.xpath(xpath)).equals(favouriteElementByDataMarker);
    }
}
