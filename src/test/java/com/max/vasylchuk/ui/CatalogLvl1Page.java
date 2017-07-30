package com.max.vasylchuk.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CatalogLvl1Page extends  Page{

    public CatalogLvl1Page(WebDriver driver) {
        super(driver);

    }

    public boolean isOpen(){
        return this.driver.findElements(By.cssSelector(".p-auto-block-2")).size() > 0;
    }


    public CatalogLvl2Page clickOnCatalogMenuItem(String name){
        String locator = String.format(".//*[contains(@class,'m-cat')]//a[text()='%s']", name);
        WebElement catalogMenuItem = driver.findElement(By.xpath(locator));
        catalogMenuItem.click();
        return new CatalogLvl2Page(this.driver);
    }
}
