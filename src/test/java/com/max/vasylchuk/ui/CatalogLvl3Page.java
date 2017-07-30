package com.max.vasylchuk.ui;

import com.max.vasylchuk.webdata.CatalogLvl3PageGoodsData;
import com.max.vasylchuk.webdata.CatalogLvl3PageGoodsDataCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CatalogLvl3Page extends Page {

    public CatalogLvl3Page(WebDriver driver) {
        super(driver);

    }

    public boolean isOpen(){
        return this.driver.findElements(By.cssSelector("#block_with_goods")).size() == 1;
    }

    public CatalogLvl3Page clickOnPaginator(int page){
        String locator = String.format("#page%s", page);
        WebElement element = this.driver.findElement(By.cssSelector(locator));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public List<WebElement> findPopularGoods() {
        List<WebElement> titleBoxes = driver.findElements(By.xpath("//*[contains(@class,'g-tag-icon-middle-popularity')]/ancestor::*[contains(@class,'g-i-tile-i-box-desc')]"));
        return titleBoxes;
    }

    public CatalogLvl3PageGoodsDataCollection findPopularGoodsAndGetData() {
        int titleBoxesNumber = driver.findElements(By.xpath("//*[contains(@class,'g-tag-icon-middle-popularity')]/ancestor::*[contains(@class,'g-i-tile-i-box-desc')]")).size();

        CatalogLvl3PageGoodsDataCollection goodsData = new CatalogLvl3PageGoodsDataCollection();
        for(  int i = 0; i<titleBoxesNumber; i++ )
        {
            String title =driver.findElements(By.xpath("//*[contains(@class,'g-tag-icon-middle-popularity')]/ancestor::*[contains(@class,'g-i-tile-i-box-desc')]")).get(i).findElement(By.cssSelector(".g-i-tile-i-title a")).getText();
            String price =driver.findElements(By.xpath("//*[contains(@class,'g-tag-icon-middle-popularity')]/ancestor::*[contains(@class,'g-i-tile-i-box-desc')]")).get(i).findElement(By.cssSelector(".g-price-uah")).getText();
            goodsData.add(new CatalogLvl3PageGoodsData(title, price));
        }
        return goodsData;
    }

}
