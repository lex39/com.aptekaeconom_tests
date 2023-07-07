package com.aptekaEconom.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import com.codeborne.selenide.Configuration;


public class MainPage {
  private static final String nameCookieRegion = "current_region";
  private static final String valueCookieRegion = "119202";
  private static final String browserSizeSmall = "1366x768";
  private static final SelenideElement wishIcon = $(".basket-link.delay.with_price.big.basket-count");
  private static final SelenideElement basketIcon = $(".basket-link.basket.has_prices.with_price.big");

  @BeforeEach
  public void setUp() {
    Configuration.browser = CHROME;
    Configuration.browserSize = browserSizeSmall;
  }

  public void open() {
    Selenide.open("https://aptekaeconom.com/");
    addCookie(nameCookieRegion, valueCookieRegion);
    refresh();
  }

  public void addCookie(String nameCookie, String valueCookie) {
    Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie(nameCookie, valueCookie));
  }

  public String getTextHoverWishIcon() {
    return wishIcon.getAttribute("title");
  }

  public void clickBasketIcon() {
    basketIcon.click();
  }
}
