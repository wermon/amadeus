package com.max.vasylchuk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.max.vasylchuk.ui.CatalogLvl1Page;
import com.max.vasylchuk.ui.CatalogLvl2Page;
import com.max.vasylchuk.ui.CatalogLvl3Page;
import com.max.vasylchuk.ui.HomePage;
import com.max.vasylchuk.webdata.CatalogLvl3PageGoodsData;
import com.max.vasylchuk.webdata.CatalogLvl3PageGoodsDataCollection;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RozetkaTest {

    private static final String DRIVER_KEY = "webdriver.chrome.driver";

    private static Connection conn;

    private static WebDriver driver;

    @Before
    public void testSetup(){


       System.setProperty(DRIVER_KEY, Paths.get("lib/chromedriver.exe").toString());
       driver = new ChromeDriver();
       driver.manage().window().maximize();
    }

    @BeforeClass
    public static void databaseSetup() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            conn.prepareStatement("CREATE TABLE AMADEUS(ID INT AUTO_INCREMENT, TITLE VARCHAR, PRICE VARCHAR)").execute();
        } catch (SQLException e) {
            //e.printStackTrace();
            //If error then the table was created earlier
            try {
                conn.prepareStatement("Delete from AMADEUS").execute();
            } catch (SQLException e1) {
                //e1.printStackTrace();
                //If error then the table was created earlier
            }
        }

    }

    @AfterClass
    public static void tearDown() throws ClassNotFoundException, SQLException {

            conn.close();
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

        CatalogLvl3PageGoodsDataCollection popularGoods = catalogLvl3Page.findPopularGoodsAndGetData();
        Assert.assertEquals("Xiaomi Redmi Note 4X 3/32GB Black", popularGoods.getGoods().get(0).getTitle());
        Assert.assertEquals("4 349 грн",popularGoods.getGoods().get(0).getPrice());
        Assert.assertEquals("Xiaomi Redmi 4X 3/32GB Black (Международная версия)", popularGoods.getGoods().get(1).getTitle());
        Assert.assertEquals("4 299 грн",popularGoods.getGoods().get(1).getPrice());
        popularGoods.insertToDb(conn);
//        catalogLvl3Page.clickOnPaginator(2);
//        List<CatalogLvl3PageGoodsData> page2PopularGoods  = catalogLvl3Page.findPopularGoodsAndGetData();
//        if (page2PopularGoods.size() > 0) { popularGoods.addAll(page2PopularGoods);}
//        catalogLvl3Page.clickOnPaginator(3);
//        List<CatalogLvl3PageGoodsData> page3PopularGoods  = catalogLvl3Page.findPopularGoodsAndGetData();
//        if (page3PopularGoods.size() > 0) { popularGoods.addAll(page2PopularGoods);}

    }


    public void testWriteToH2() throws Exception {
        try (PreparedStatement pStmt = conn.prepareStatement("SELECT TITLE, PRICE FROM AMADEUS");
             ResultSet resultSet = pStmt.executeQuery()) {

            List<CatalogLvl3PageGoodsData> popularGoods = new ArrayList<>();
            while (resultSet.next()) {
                popularGoods.add(new CatalogLvl3PageGoodsData(resultSet.getString(1), resultSet.getString(2)));
            }

            //assertEquals(2, popularGoods.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}