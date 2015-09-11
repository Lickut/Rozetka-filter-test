package com.epam.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class FilterPage extends Page {
	@FindBy(id = "price[min]")
	private WebElement minPriceTextField;
	@FindBy(id = "price[max]")
	private WebElement maxPriceTextField;
	@FindBy(id = "submitprice")
	private WebElement priceSubmitButton;
	@FindBy(xpath = "//ul[@name='paginator']/li")
	private List<WebElement> cataloguePagesNavigator;
	private String filterClassTitle = "Класс";

	public FilterPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getFilterListByID(String filterParameterID) {
		List<WebElement> test = driver.findElements(By.xpath("//ul[@id='" + filterParameterID + "']/li"));
		return test;
	}

	public FilterPresetPage filter(String filterParameterID, String filterParameterValue) {
		List<WebElement> filterValues = getFilterListByID(filterParameterID);
		for (WebElement i : filterValues) {
			if (i.getText().contains(filterParameterValue)) {
				i.findElement(By.xpath(".//i")).click();
				return PageFactory.initElements(driver, FilterPresetPage.class);
			}

		}
		throw new NoSuchElementException("No filter parameter " + filterParameterID + " or value "
				+ filterParameterValue + " on page " + driver.getCurrentUrl());
	}

	public FilterPresetPage filterByPrice(String minPrice, String maxPrice) {
		maxPriceTextField.clear();
		maxPriceTextField.sendKeys(maxPrice);
		minPriceTextField.clear();
		minPriceTextField.sendKeys(minPrice);
		priceSubmitButton.click();
		return PageFactory.initElements(driver, FilterPresetPage.class);
	}

	public List<WebElement> getCheckedBoxes() {
		return driver.findElements(By.xpath("//input[@class='hidden' and @checked]"));
	}

	public List<WebElement> getCatalogueElements() {
		return driver.findElements(By.xpath("//div[@class='g-i-tile g-i-tile-catalog']"));
	}

	public List<WebElement> getCataloguePagesNavagator() {
		return cataloguePagesNavigator;
	}

	public <T extends FilterPage> T goToCataloguePage(int number) {
		if ((number > 0) && (number <= cataloguePagesNavigator.size())) {
			WebElement buttonToPageNumber = cataloguePagesNavigator.get(number - 1);
			if (buttonToPageNumber.findElements(By.xpath(".//a")).size() != 0) {
				buttonToPageNumber.findElement(By.xpath(".//a")).click();
				return (T) PageFactory.initElements(driver, this.getClass());
			} else {
				return (T) this;
			}
		} else {
			throw new IllegalArgumentException(number + " should be between 1 and " + cataloguePagesNavigator.size());
		}
	}

	public String getFilterListIDByParameter(String filterParameter) {
		String xPathQuery;
		if (filterParameter.equals(filterClassTitle)) {
			xPathQuery = "//div[@name='filter_parameters_block']/h4[contains(text(),'" + filterParameter
					+ "')]/../div[@name='filter_parameters']/ul";
		} else {
			xPathQuery = "//div[@name='filter_parameters_block']/div/span[@name='filter_parameters_title' and contains(text(),'"
					+ filterParameter + "')]/../../div[@name='filter_parameters']/ul";
		}
		return driver.findElement(By.xpath(xPathQuery)).getAttribute("id");
	}
}