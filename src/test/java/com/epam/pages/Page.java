package com.epam.pages;

import org.openqa.selenium.*;

public class Page {
	WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public boolean elementIsPresentById(final String idLocator) {
		return (driver.findElements(By.id(idLocator)).size() != 0);
	}
	public void close(){
		driver.close();
	}
}