package com.max.vasylchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {


    //protected String title = "Интернет-магазин ROZETKA™: фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие";


    public HomePage(WebDriver driver) {
       super(driver);
       this.title = "Интернет-магазин ROZETKA™: фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие";
        if (!title.equals(this.driver.getTitle())) {

            throw new IllegalStateException("This is not the home page");
        }

    }

    public CatalogLvl1Page clickOnCatalogMenuItem(String name) throws InterruptedException {
        String locator = String.format("[data-title=\"%s\"]", "Смартфоны, ТВ и электроника");
        driver.findElement(By.cssSelector(locator)).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(locator)).click();
        return new CatalogLvl1Page(driver);
    }
}
