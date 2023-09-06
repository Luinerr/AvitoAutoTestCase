package Tests.Classes;

import Settings.Core.RuleResultTests;
import Tests.Pages.PageProduct;
import Settings.SubCore.BaseAddFavorite;
import Settings.TestValues.TestValuesAddFavourite;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import Settings.Assistant.ReadProperties.ConfProperties;

public class TestAddFavourites extends BaseAddFavorite {

    @Rule
    public RuleResultTests ruleResultTests = new RuleResultTests(driver);

    @Test
    public void checkPopUpWindowIsUp() {
        pageProduct.clickButtonAddFavourites();
        Assert.assertTrue(pageProduct.isPopUpWindowAppeared());
    }

    @Test
    public void checkPopUpWindowHasText() {
        pageProduct.clickButtonAddFavourites();
        Assert.assertEquals(pageProduct.getTitlePopUpWindow(), TestValuesAddFavourite.TEST_POP_UP_TEXT);
    }

    @Test
    public void checkPopUpWindowIsClosed() {
        pageProduct.clickButtonAddFavourites();
        Assert.assertFalse(pageProduct.isPopUpWindowClosed());
    }

    @Test
    public void checkButtonAddFavouritesChangeHeart() {
        String firstStyle = pageProduct.getAttributeOfHeartAddFavorites();
        pageProduct.clickButtonAddFavourites();
        Assert.assertNotEquals(firstStyle, pageProduct.getAttributeOfHeartAddFavorites());
    }

    @Test
    public void checkProductInFavourites() {
        pageProduct.clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertTrue(pageFavourites.isFavouriteElementAdded());
    }

    @Test
    public void checkTextProductInFavourites() {
        pageProduct.clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertEquals(TestValuesAddFavourite.TEST_NAME_PRODUCT, pageFavourites.getFavouriteElementTitle());
    }

    @Test
    public void checkProductAddedLastInFavourites() {
        PageProduct pageProduct1 = new PageProduct(ConfProperties.getProperty("urlProduct1"));
        pageProduct1.clickButtonAddFavourites()
                .openPageProduct(pageProduct)
                .clickButtonAddFavourites()
                .openPageFavorites();
        Assert.assertTrue(pageFavourites.isFavouriteElementAddedLast());
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
    }
}
