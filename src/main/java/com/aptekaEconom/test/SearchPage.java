package com.aptekaEconom.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPage extends MainPage{
  private static final SelenideElement searchHeader = $("#pagetitle");
  private static final SelenideElement searchInput = $("#title-search-input_fixed");
  private static final SelenideElement searchButton = $(".btn.btn-search");
  private static final SelenideElement leftFilterAndSectionBlock = $(".left_block.filter_visible");
  private static final SelenideElement leftSubscribeBlock = $(".subscribe_wrap");
  private static final SelenideElement itemCardTitle = $(".item-title");
  private static final ElementsCollection itemsList = $$(".catalog_item.main_item_wrapper.item_wrap");


  public static void setSearchInput(String query) {
    searchInput.setValue(query);
  }

  public static void clickSearchButton() {
    searchButton.click();
  }

  public static void search(String query) {
    setSearchInput(query);
    clickSearchButton();
  }

  public boolean checkHeaderVisible() {
    searchHeader.shouldBe(visible);
    searchHeader.shouldHave(text("Поиск"));
    return true;
  }

  public boolean checkBlockFilterAndSectionsVisible() {
    leftFilterAndSectionBlock.shouldBe(visible);
    return true;
  }

  public boolean checkLeftSubscribeBlockVisible() {
    leftSubscribeBlock.shouldBe(visible);
    return true;
  }

  public boolean checkCountItemCardOnPage(int count) {
    return itemsList.size() == count;
  }

  public boolean checkSearchResultTextItemCard(String searchText) {
    itemCardTitle.shouldHave(text(searchText));
    return itemCardTitle.exists();
  }

  public void checkBlocksVisibleOnSearchPage() {
    assertTrue(checkHeaderVisible());
    assertTrue(checkBlockFilterAndSectionsVisible());
    assertTrue(checkLeftSubscribeBlockVisible());
  }
}
