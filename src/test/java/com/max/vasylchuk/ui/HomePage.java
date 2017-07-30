package com.max.vasylchuk.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        String locator = String.format("[data-title=\"%s\"]", name);
        driver.findElement(By.cssSelector(locator)).click();
        Thread.sleep(1000);

        WebElement element = this.driver.findElement(By.cssSelector(locator));

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.cssSelector(locator))));

        driver.findElement(By.cssSelector(locator)).click();
        return new CatalogLvl1Page(driver);
    }
}
