package com.max.vasylchuk.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CatalogLvl2Page extends  Page{

    public CatalogLvl2Page(WebDriver driver) {
        super(driver);

    }

    public boolean isOpen(){
        return this.driver.findElements(By.cssSelector(".p-auto-block-1")).size() > 0;
    }

    public CatalogLvl3Page clickOnCatalogMenuItem(String name){
        String locator = String.format(".//*[contains(@class,'m-cat')]//a[text()='%s']", name);
        WebElement catalogMenuItem = driver.findElement(By.xpath(locator));
        catalogMenuItem.click();
        return new CatalogLvl3Page(this.driver);
    }
}
