package com.aptekaEconom.test;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishListTest extends ProductCardPage {
  BasketProductPage basketProductPage = new BasketProductPage();
  @Test
  @DisplayName("Проверяю функционал - Отложить товар")
  public void selectedItemTest() {
    step("Шаг 1. Проверяем доступность товара", () -> {
      checkAvailabilityStatus();
    });

    step("Шаг 2. Наводимся на иконку избранного и добавляем товар", () -> {
      selectWishIconClick();
    });

    String nameItemMainPage = getTextProductItemCard();

    step("Шаг 3. Проверяем цену товара в избранном", () -> {
      assertTrue(checkPriceWishList());
    });

    step("Шаг 4. Кликаем на иконке Корзина", () -> {
      clickBasketIcon();
    });

    step("Шаг 5. Проверяем заголовок страницы Корзина", () -> {
      basketProductPage.checkHeaderBasketPage();
    });

    String nameItemBasketPage = basketProductPage.getTextItemBasketPage();

    step("Шаг 6. Проверяем что товар название товара из карточки совпадает с название товара из корзины", () -> {
      assertEquals(nameItemMainPage, nameItemBasketPage);
    });

    step("Шаг 7. Проверяем что цена товара из избранного не добавилась стоимость заказа", () -> {
      assertTrue(basketProductPage.checkOrderAmount(0));
    });
  }

  @Test
  @DisplayName("Проверяю возможность добавить отложенный товар в заказ")
  public void addWishItemToOrder() {
    step("Шаг 1. Проверяем доступность товара", this::checkAvailabilityStatus);

    step("Шаг 2. Наводимся на иконку избранного и добавляем товар", this::selectWishIconClick);

    int price = Integer.parseInt(getPriceValue());

    step("Шаг 3. Кликаем на иконке Корзина", this::clickBasketIcon);

    step("Шаг 4. Проверяем заголовок страницы Корзина", () -> {
      basketProductPage.checkHeaderBasketPage();
    });

    step("Шаг 5. Добавляем товар из избранного в заказ", () -> {
      basketProductPage.addWishItemToOrder();
    });

    step("Шаг 6. Проверяем что ссылка добавить товар в заказ не видна", () -> {
      basketProductPage.checkLinkAddWishItemToOrderNotVisible();
    });

    step("Шаг 7. Проверяем что стоимость товара из избранного добавилась в стоимость заказа", () -> {
      assertTrue(basketProductPage.checkOrderAmount(price));
    });
  }
}
