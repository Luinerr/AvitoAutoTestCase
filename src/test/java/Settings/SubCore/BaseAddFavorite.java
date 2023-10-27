package Settings.SubCore;

import Settings.Core.BaseSeleniumTest;
import Settings.Assistant.ReadProperties.ConfProperties;
import Tests.Pages.PageFavourites;
import Tests.Pages.PageProduct;
import org.junit.Before;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseAddFavorite extends BaseSeleniumTest {
    /**
     * Страница продукта
     */
    protected PageProduct pageProduct;
    /**
     * Страница избранное
     */
    protected PageFavourites pageFavourites;
    /**
     * Настройки перед каждый тестом
     */
    @Before
    public void setUpTest() {
        item = parseStringItemFromUrl(ConfProperties.getProperty("urlProduct"));
        pageProduct = new PageProduct(ConfProperties.getProperty("urlProduct"));
        pageFavourites = new PageFavourites(item);
        if (pageProduct.getTitleButtonAddFavourites().equals("В избранном")) {
            pageProduct.clickButtonAddFavourites();
        }
        /*
         * Ожидание перед тестом. иначе бан.¯\_(ツ)_/¯
         */
        try {
            driverWaitBeforeTest.until(ExpectedConditions.alertIsPresent());
        } catch (Exception ignored) {
        }
    }
}
