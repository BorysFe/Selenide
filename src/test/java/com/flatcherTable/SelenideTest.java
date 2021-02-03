package com.flatcherTable;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Condition;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelenideTest {

    public ChromeDriver driver;

    private String downArrow = ".//img[@alt='scroll down']";
    private String pageHeaderBtn = ".//li[contains(@class, 'menu-item-%s')]";
    private String errorContactPage = ".//h5[@class='error']";

    private String portfolioBtn = "26";
    private String contactBtn = "22";
    private String detailsBtn = "24";
    private String videoBtn = "25";

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver()
                        .setup();
        open("https://www.fletchertables.com/");
    }

    @Test
    public void homePageHeaderLinkCheck() {
        $(By.xpath(downArrow)).click();
        $(By.xpath(".//a[@href= 'https://www.fletchertables.com/portfolio/']")).shouldHave(Condition.text("Portfolio"));
    }

    @Test
    public void homePageTitleCheck() {
        $(By.xpath(".//div[@class= 'home_one_inner']/h1")).shouldHave(Condition.exactText("The Fletcher Capstan " +
                "Table"));
    }

    @Test
    public void homePageFollowUsInk() {
        String socialLink = $(By.xpath(".//span[@class= 'social social_home']/a")).getAttribute("href");
        Assert.assertEquals(socialLink, "https://www.facebook.com/fletchertables");
    }

    @Test
    public void contactPageTitleCheck() {
        $(By.xpath(String.format(pageHeaderBtn, contactBtn))).click();
        $(By.xpath(".//button[@id= 'submit']")).click();
        $(By.xpath(".//div[@class= 'col-1']/h1")).shouldHave(Condition.exactText("Contact Us"));
    }

    @Test
    public void contactPageRequiredStarsCheck() {
        $(By.xpath(String.format(pageHeaderBtn, contactBtn))).click();
        $(By.xpath(".//label[@for= 'contactname']/em")).shouldHave(Condition.exactText("*"));
        $(By.xpath(".//label[@for= 'contactemail']/em")).shouldHave(Condition.exactText("*"));
    }

    @Test
    public void contactPageErrorNotVisible() {
        $(By.xpath(String.format(pageHeaderBtn, contactBtn))).click();
        $(By.xpath(errorContactPage)).shouldNotBe(Condition.visible);
    }

    @Test
    public void portfolioPagePhotoHoverTextCheck() {
        $(By.xpath(String.format(pageHeaderBtn, portfolioBtn))).click();
        $$(By.xpath(".//div[@class='portfolio-information']")).get(1)
                                                              .hover();
        $$(By.xpath(".//div[@class='portfolio-information']//div//dl")).get(1)
                                                                       .shouldBe(Condition.visible);
    }

    @Test
    public void portfolioPagePhotoOpenCheck() {
        $(By.xpath(String.format(pageHeaderBtn, portfolioBtn))).click();
        $$(By.xpath(".//div[@class='portfolio-information']/..")).get(1)
                                                                 .hover()
                                                                 .click();
        $(By.xpath(".//p[@class='cattag residential']/../h1")).shouldHave(Condition.exactText("Mayfair Mahogany"));
    }

    @Test
    public void detailsPageTitleCheck() {
        $(By.xpath(String.format(pageHeaderBtn, detailsBtn))).click();
        $(By.xpath(".//div[@class='sizeone']/h4")).shouldHave(Condition.text("1.67m - 2.2m"));
    }

    @Test
    public void videosCountCheck() {
        $(By.xpath(String.format(pageHeaderBtn, videoBtn))).click();
        $$(By.tagName("iframe")).shouldHaveSize(9);
    }
}
