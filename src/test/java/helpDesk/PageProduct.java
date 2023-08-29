package helpDesk;

import Core.BaseSeleniumPage;
import Core.BaseSeleniumTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageProduct extends BaseSeleniumPage {

    protected String url;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div[3]/div[1]/div[1]/div/div[3]/div/div/div/div[1]/button")
    private WebElement buttonAddFavourites;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div[3]/div[1]/div[1]/div/div[3]/div/div/div/div[1]/button/*[1]/*")
    private WebElement pictureOfHeart;

    @FindBy(xpath = "/html/body/div[2]/div[6]/div")
    private WebElement popUpWindow;

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div/div/div[2]/div/p")
    private WebElement textPopUpWindow;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[1]/div/div/div[1]/a[1]")
    private WebElement buttonOpenFavorites;

    public PageProduct(String urlProduct) {
        url = urlProduct;
        driver.get(urlProduct);
        PageFactory.initElements(driver, this);
    }

    public PageProduct clickButtonAddFavourites() {
        buttonAddFavourites.click();
        return this;
    }

    public PageFavourites openPageFavorites() {
        buttonOpenFavorites.click();
        return new PageFavourites(BaseSeleniumTest.getItem());
    }

    public PageProduct openPageProduct(PageProduct pageProduct) {
        driver.get(pageProduct.getUrl());
        return this;
    }

    public String getTitlePopUpWindow() {
        return textPopUpWindow.getText();
    }

    public Boolean isPopUpWindowAppeared() {
        driverWait.until(ExpectedConditions.visibilityOf(popUpWindow));
        return popUpWindow.isDisplayed();
    }

    public Boolean isPopUpWindowClosed() {
        driverWait.until(ExpectedConditions.invisibilityOf(popUpWindow));
        try {
            if(!popUpWindow.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public String getUrl() {
        return new String(url);
    }

    public String getTitleButtonAddFavourites() {
        return buttonAddFavourites.getText();
    }

    public String getAttributeOfHeartAddFavorites() {
        return pictureOfHeart.getAttribute("d");
    }
}
