package com.max.vasylchuk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.max.vasylchuk.WebData.CatalogLvl3PageGoodsData;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class RozetkaTest {
    WebDriver driver;
    @Before
    public void testSetup(){
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\пк\\IdeaProjects\\amadeus\\src\\test\\java\\com\\max\\vasylchuk\\chromedriver.exe");
       driver = new ChromeDriver();
       driver.manage().window().maximize();
    }
    @Test
    public void testRozetka() throws InterruptedException {
        driver.get("https://www.rozetka.com.ua");
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isOpen());
        CatalogLvl1Page catalogLvl1Page = homePage.clickOnCatalogMenuItem("Смартфоны, ТВ и электроника");
        CatalogLvl2Page catalogLvl2Page = catalogLvl1Page.clickOnCatalogMenuItem("Телефоны");
        assertTrue(catalogLvl2Page.isOpen());
        CatalogLvl3Page catalogLvl3Page = catalogLvl2Page.clickOnCatalogMenuItem("Смартфоны");

        List<CatalogLvl3PageGoodsData> popularGoods = catalogLvl3Page.findPopularGoodsAndGetData();
        catalogLvl3Page.clickOnPaginator(2);
        popularGoods.addAll(catalogLvl3Page.findPopularGoodsAndGetData());
        catalogLvl3Page.clickOnPaginator(3);
        popularGoods.addAll(catalogLvl3Page.findPopularGoodsAndGetData());

    }


}