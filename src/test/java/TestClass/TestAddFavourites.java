package TestClass;

import Core.BaseSeleniumTest;
import Core.ScreenShotRule;
import TestValues.TestValues;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ReadProperties.ConfProperties;

public class TestAddFavourites extends BaseSeleniumTest {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            ScreenShotRule.setName(description.getMethodName());
            BaseSeleniumTest.setNameTest(description.getMethodName());
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Rule
    public ScreenShotRule screenShotRule = new ScreenShotRule(driver);

    @Test
    public void checkPopUpWindowIsUp() {
        pageProduct.clickButtonAddFavourites();
        Assert.assertTrue(pageProduct.isPopUpWindowAppeared());
        analyzeLog(true);
    }

    @Test
    public void checkPopUpWindowHasText() {
        pageProduct.clickButtonAddFavourites();
        Assert.assertEquals(pageProduct.getTitlePopUpWindow(), TestValues.TEST_POP_UP_TEXT);
        analyzeLog(true);
    }

    @Test
    public void checkPopUpWindowIsClosed() {
        pageProduct.clickButtonAddFavourites();
        Assert.assertFalse(pageProduct.isPopUpWindowClosed());
        analyzeLog(true);
    }

    @Test
    public void checkButtonAddFavouritesChangeHeart() {
        String firstStyle = pageProduct.getAttributeOfHeartAddFavorites();
        pageProduct.clickButtonAddFavourites();
        Assert.assertNotEquals(firstStyle, pageProduct.getAttributeOfHeartAddFavorites());
        analyzeLog(true);
    }

    @Test
    public void checkProductInFavourites() {
        pageProduct.clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertTrue(pageFavourites.isFavouriteElementAdded());
        analyzeLog(true);
    }

    @Test
    public void checkTextProductInFavourites() {
        pageProduct.clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertEquals(TestValues.TEST_NAME_PRODUCT, pageFavourites.getFavouriteElementTitle());
        analyzeLog(true);
    }

    @Test
    public void checkProductAddedLastInFavourites() {
        PageProduct pageProduct1 = new PageProduct(ConfProperties.getProperty("urlProduct1"));
        pageProduct1.clickButtonAddFavourites()
                .openPageProduct(pageProduct)
                .clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertTrue(pageFavourites.isFavouriteElementAddedLast());
        analyzeLog(true);
    }

    @Test
    public void checkProductAddedNotLastInFavourites() {
        PageProduct pageProduct1 = new PageProduct(ConfProperties.getProperty("urlProduct1"));
        pageProduct1.openPageProduct(pageProduct)
                .clickButtonAddFavourites()
                .openPageProduct(pageProduct1)
                .clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertFalse(pageFavourites.isFavouriteElementAddedNotLast());
        analyzeLog(true);
    }
}
