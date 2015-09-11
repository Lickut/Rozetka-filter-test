package com.epam.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterPresetPage extends FilterPage {

	@FindBy(xpath = "//ul[@class='filter-active-l clearfix']/li")
	private List<WebElement> resultBar;

	public FilterPresetPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getResultBar() {
		return resultBar;
	}

	public FilterPage resetFilter() {
		for (WebElement i:resultBar) {
			if (i.getText().contains("Сбросить")) {
				i.findElement(By.xpath(".//a")).click();
				return PageFactory.initElements(driver, FilterPage.class);
			}
		}
		throw new NoSuchElementException("Page doesn't have reset filter functionality");
	}
	
	public String getFilteredTextResult() {
		if (resultBar.size() !=0)
			{ return resultBar.get(resultBar.size()-1).getText();}
			else{
				return "";
			}
	}
}
