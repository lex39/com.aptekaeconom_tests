package com.aptekaEconom.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import com.codeborne.selenide.Configuration;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Objects;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


public class MainPage {
  private static final String nameCookieRegion = "current_region";
  private static final String valueCookieRegion = "119202";
  private static final String browserSizeSmall = "1366x768";
  private static final SelenideElement wishIcon = $(".basket-link.delay.with_price.big.basket-count");
  private static final SelenideElement basketIcon = $(".basket-link.basket.has_prices.with_price.big");

  @BeforeAll
  public static void setDriver() throws MalformedURLException {
    String isRemote = System.getenv("IS_REMOTE");
    if (Objects.equals(isRemote, "true")) {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setCapability("enableVNC:", true);
      WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), chromeOptions);
      setWebDriver(driver);
    } else {
      Configuration.browser = "chrome";
    }
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
