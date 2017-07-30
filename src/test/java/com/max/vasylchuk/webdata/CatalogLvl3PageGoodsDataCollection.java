package com.max.vasylchuk.webdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CatalogLvl3PageGoodsDataCollection {
    private List<CatalogLvl3PageGoodsData> goods;

    public List<CatalogLvl3PageGoodsData> getGoods() {
        return goods;
    }

    public CatalogLvl3PageGoodsDataCollection(List<CatalogLvl3PageGoodsData> goods) {
        this.goods = goods;
    }

    public CatalogLvl3PageGoodsDataCollection add(CatalogLvl3PageGoodsData goodsdata){
        goods.add(goodsdata);
        return this;
    }

    public CatalogLvl3PageGoodsDataCollection() {
        this.goods = new ArrayList<>();
    }

    public CatalogLvl3PageGoodsDataCollection insertToDb(Connection conn){
        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO AMADEUS (TITLE, PRICE) VALUES (?, ?)")) {
            for (CatalogLvl3PageGoodsData item : goods) {
                preparedStatement.setString(1, item.getTitle());
                preparedStatement.setString(2, item.getPrice());
                preparedStatement.addBatch();
            }
            int[] result = preparedStatement.executeBatch();

            return this;

        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
    }
}
