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
  @DisplayName("Проверка поиска, в поисковую строку вводится запрос, проверяется поисковая выдача. В выдаче 5 карточек товара.")
  public void testOneWordSearch() {
    step("В поисковую строку сайта ввожу запрос", () -> {
      SearchPage.search("Аспирин");
    });
//    SearchPage.search("Аспирин");

    checkBlocksVisibleOnSearchPage();
    assertTrue(checkSearchResultTextItemCard("Аспирин"));
    assertTrue(checkCountItemCardOnPage(5),"Проверяем количество карточек товара на странице выдачи поиска");
  }
}
