package com.aptekaEconom.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductCardPage extends MainPage {
  private static final SelenideElement selectIcon = $(".like_icons");
  private static final SelenideElement textSelectIcon = $("span.wish_item.to");
  private static final SelenideElement availabilityText = $(".store_view");
  private static final SelenideElement priceValue = $(".price_value");
  private static final SelenideElement textProductItemCard = $(".item-title span");

  public void selectWishIconClick() {
    checkTextWishIcon();
    selectIcon.click();
  }

  public void checkTextWishIcon() {
    textSelectIcon.shouldHave(attribute("title", "Отложить"))
            .shouldNotHave(attribute("style", "display: none;"));
  }

  public void checkAvailabilityStatus() {
    availabilityText.shouldHave(text("В наличии"));
  }

  public String getPriceValue() {
    return priceValue.getText();
  }

  public boolean checkPriceWishList() {
    return getTextHoverWishIcon().contains(getPriceValue());
  }

  public String getTextProductItemCard() {
    return textProductItemCard.getText();
  }
}
