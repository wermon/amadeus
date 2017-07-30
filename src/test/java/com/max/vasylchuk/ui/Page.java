package com.max.vasylchuk.ui;

import org.openqa.selenium.WebDriver;

public class Page {
    protected String title = "";
    protected  WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;

    }
    public boolean isOpen(){
        if (!title.equals(this.driver.getTitle())) {

            return false;
        }
        return true;
    }
}
