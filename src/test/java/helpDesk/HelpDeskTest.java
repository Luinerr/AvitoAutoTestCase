package helpDesk;

import Core.BaseSeleniumTest;
import Core.ScreenShotRule;
import TestValues.TestValues;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import readProperties.ConfProperties;

public class HelpDeskTest extends BaseSeleniumTest {

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
        try {
            pageProduct.clickButtonAddFavourites();
            Assert.assertTrue(pageProduct.isPopUpWindowAppeared());
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkPopUpWindowHasText() {
        try {
            pageProduct.clickButtonAddFavourites();
            Assert.assertEquals(pageProduct.getTitlePopUpWindow(), TestValues.TEST_POP_UP_TEXT);
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkPopUpWindowIsClosed() {
        try {
            pageProduct.clickButtonAddFavourites();
            Assert.assertFalse(pageProduct.isPopUpWindowClosed());
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkButtonAddFavouritesChangeHeart() {
        try {
            String firstStyle = pageProduct.getAttributeOfHeartAddFavorites();
            pageProduct.clickButtonAddFavourites();
            Assert.assertNotEquals(firstStyle, pageProduct.getAttributeOfHeartAddFavorites());
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkProductInFavourites() {
        try {
            pageProduct.clickButtonAddFavourites()
                    .openPageFavorites();
            Assert.assertTrue(pageFavourites.isFavouriteElementAdded());
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkTextProductInFavourites() {
        try {
            pageProduct.clickButtonAddFavourites()
                    .openPageFavorites();
            Assert.assertEquals(pageFavourites.getFavouriteElementTitle(), TestValues.TEST_NAME_PRODUCT);
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkProductAddedLastInFavourites() {
        try {
            PageProduct pageProduct1 = new PageProduct(ConfProperties.getProperty("urlProduct1"));
            pageProduct1.clickButtonAddFavourites()
                    .openPageProduct(pageProduct)
                    .clickButtonAddFavourites()
                    .openPageFavorites();
            Assert.assertTrue(pageFavourites.isFavouriteElementAddedLast());
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }

    @Test
    public void checkProductAddedNotLastInFavourites() {
        try {
            PageProduct pageProduct1 = new PageProduct(ConfProperties.getProperty("urlProduct1"));
            pageProduct1.openPageProduct(pageProduct)
                    .clickButtonAddFavourites()
                    .openPageProduct(pageProduct1)
                    .clickButtonAddFavourites()
                    .openPageFavorites();
            Assert.assertFalse(pageFavourites.isFavouriteElementAddedNotLast());
            analyzeLog();
        } catch (Exception e) {
            analyzeLog();
        }
    }
}
