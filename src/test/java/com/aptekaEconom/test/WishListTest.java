package com.aptekaEconom.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishListTest extends ProductCardPage {


  @BeforeEach
  public void setUp() {
    open();
  }

  @AfterEach
  public void tearDown() {
    closeWebDriver();
  }

  @Test
  @DisplayName("Проверяю функционал - Отложить товар")
  public void selectedItemTest() {
    checkAvailabilityStatus();
    selectWishIconClick();
    String nameItemMainPage = getTextProductItemCard();

    assertTrue(checkPriceWishList());

    BasketProductPage basketProductPage = new BasketProductPage();
    clickBasketIcon();
    basketProductPage.checkHeaderBasketPage();
    String nameItemBasketPage = basketProductPage.getTextItemBasketPage();

    assertEquals(nameItemMainPage, nameItemBasketPage);
    assertTrue(basketProductPage.checkOrderAmount(0));
  }

  @Test
  @DisplayName("Проверяю возможность добавить отложенный товар в заказ")
  public void addWishItemToOrder() {
    checkAvailabilityStatus();
    selectWishIconClick();

    int price = Integer.parseInt(getPriceValue());

    BasketProductPage basketProductPage = new BasketProductPage();
    clickBasketIcon();
    basketProductPage
            .checkHeaderBasketPage()
            .addWishItemToOrder()
            .checkLinkAddWishItemToOrderNotVisible();

    assertTrue(basketProductPage.checkOrderAmount(price));
  }
}
