package com.max.vasylchuk;

import com.max.vasylchuk.WebData.CatalogLvl3PageGoodsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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
        this.driver.findElement(By.cssSelector(locator)).click();
        return this;
    }

    public List<WebElement> findPopularGoods() {
        List<WebElement> titleBoxes = driver.findElements(By.xpath("//*[contains(@class,'g-tag-icon-middle-popularity')]/ancestor::*[contains(@class,'g-i-tile-i-box-desc')]"));
        return titleBoxes;
    }

    public List<CatalogLvl3PageGoodsData> findPopularGoodsAndGetData() {
        List<WebElement> popularGoods = findPopularGoods();
       List<CatalogLvl3PageGoodsData> goodsData = new ArrayList<CatalogLvl3PageGoodsData>();
        for(  WebElement item : popularGoods)
        {
            String title = item.findElement(By.cssSelector(".g-i-tile-i-title a")).getText();
            String price = item.findElement(By.cssSelector(".g-price-uah")).getText();
            goodsData.add(new CatalogLvl3PageGoodsData(title, price));
        }
        return goodsData;
    }

}
