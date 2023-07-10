package com.aptekaEconom.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends SearchPage {

  @BeforeEach
  public void setUp() {
    open();
  }

  @AfterEach
  public void tearDown() {
    closeWebDriver();
  }

  @Test
  @DisplayName("Проверка поиска, в поисковую строку вводится запрос, проверяется поисковая выдача")
  public void testOneWordSearch() {
    step("Шаг 1. В поисковую строку сайта ввожу запрос", () -> {
      SearchPage.search("Аспирин");
    });

    step("Шаг 2. Проверяю наличие блоков на странице поиска", () -> {
      checkBlocksVisibleOnSearchPage();
    });

    step("Шаг 3. Проверяю наличие текста в результатах поиска", () -> {
      assertTrue(checkSearchResultTextItemCard("Аспирин"));
    });

    step("Шаг 4. Проверяю количество карточек товара на странице выдачи поиска", () -> {
      assertTrue(checkCountItemCardOnPage(5),
              "Проверяем количество карточек товара на странице выдачи поиска, должно быть 5");
    });
  }
}
