package com.epam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.epam.pages.FilterPage;
import com.epam.pages.FilterPresetPage;

public class FilterUtils {
	private static String pageUrl;
	private static int productsPerPage = 32;

	public static WebDriver setupBrowser() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public static FilterPage openNotebooksFilterPage(WebDriver driver) {
		pageUrl = "http://rozetka.com.ua/notebooks/c80004/filter/";
		driver.get(pageUrl);
		return PageFactory.initElements(driver, FilterPage.class);
	}

	public static FilterPresetPage doFilter(FilterPage filterPage, String filterParameter, String filterValue) {
		String filterParameterID = filterPage.getFilterListIDByParameter(filterParameter);
		FilterPresetPage resultPage = filterPage.filter(filterParameterID, filterValue);
		return resultPage;
	}

	public static boolean isFilterApplied(FilterPage filterPage) {
		return (!filterPage.getCheckedBoxes().isEmpty());
	}

	public static String getFilteredAmount(FilterPresetPage filterPresetPage) {
		String filteredTextResult = filterPresetPage.getFilteredTextResult();
		String regexPattern = "(\\d+)";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(filteredTextResult);
		if (matcher.find()) {
			return matcher.group(0);
		} else {
			return "";
		}
	}

	public static int calculateFilteredProducts(FilterPage filterPage) {
		List<WebElement> cataloguePagesNavigator = filterPage.getCataloguePagesNavagator();
		FilterPage lastCataloguePage;
		int amountOfPages;
		int amountOfFilteredProducts = 0;
		if (cataloguePagesNavigator.size() != 0) {
			amountOfPages = Integer.parseInt(cataloguePagesNavigator.get(cataloguePagesNavigator.size() - 1).getText())
					- 1;
			amountOfFilteredProducts = amountOfPages * productsPerPage;
			lastCataloguePage = filterPage.goToCataloguePage(cataloguePagesNavigator.size());
			amountOfFilteredProducts += lastCataloguePage.getCatalogueElements().size();

		} else {
			amountOfFilteredProducts = filterPage.getCatalogueElements().size();
		}
		return amountOfFilteredProducts;
	}

	public static FilterPresetPage doFilterByPrice(FilterPage filterPage, String minPrice, String maxPrice) {
		return filterPage.filterByPrice(minPrice, maxPrice);
	}

	public static List<Integer> getCataloguePrices(FilterPage filterPage) {
		List<WebElement> catalogueProducts = filterPage.getCatalogueElements();
		List<Integer> productPrices = new ArrayList<Integer>();
		for (int i = 0; i < catalogueProducts.size(); i++) {
			productPrices.add(Integer.parseInt(catalogueProducts.get(i)
					.findElement(By.xpath(".//div[@class='g-price-uah']")).getText().replaceAll("\\D+", "")));
		}
		return productPrices;
	}
}
