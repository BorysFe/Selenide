package com.flatcherTable;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


import com.codeborne.selenide.Condition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelenideTest {

    public ChromeDriver driver;

    @FindBy(xpath = "")
    private WebElement link;

    @BeforeClass
    public void webDriver() {
        WebDriverManager.chromedriver()
                        .setup();
        open("https://www.fletchertables.com/");
    }

    @Test
    public void homePageLinkVerifications() {
        $(By.xpath(".//img[@alt='scroll down']")).click();
        $(By.xpath(".//li[contains(@class, 'menu-item-26')]")).innerText().equals("Portfolio");
        System.out.println($(By.xpath(".//li[contains(@class, 'menu-item-26')]")).innerText());
        $(By.xpath(".//a[@href= 'https://www.fletchertables.com/portfolio/']")).shouldHave(Condition.text("Portfolio"));
    }

}
