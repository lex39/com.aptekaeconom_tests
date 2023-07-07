package com.aptekaEconom.test;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketProductPage extends MainPage{
  private static final SelenideElement basketPageTitle = $("#pagetitle");
  private static final SelenideElement textItemBasketPage = $("span[data-entity='basket-item-name']");
  private static final SelenideElement totalPrice = $("div[data-entity='basket-total-price']");
  private static final SelenideElement addItemToOrderLink = $("a[data-entity='basket-item-remove-delayed']");

  public BasketProductPage checkHeaderBasketPage() {
    basketPageTitle.shouldBe(visible);
    basketPageTitle.shouldHave(text("Корзина"));
    return this;
  }

  public String getTextItemBasketPage() {
    return textItemBasketPage.getText();
  }

  public boolean checkOrderAmount(int value) {
    String textTotalPrice = totalPrice.getText();
    int intTotalPrice = Integer.parseInt(textTotalPrice.replaceAll("[^\\d]", ""));
    assertEquals(value,intTotalPrice);
    return true;
  }

  public BasketProductPage addWishItemToOrder() {
    checkLinkAddWishItemToOrderVisible();
    addItemToOrderLink.click();
    return this;
  }

  public BasketProductPage checkLinkAddWishItemToOrderVisible() {
    addItemToOrderLink.shouldBe(visible);
    return this;
  }

  public BasketProductPage checkLinkAddWishItemToOrderNotVisible() {
    addItemToOrderLink.shouldNotBe(visible);
    return this;
  }
}
