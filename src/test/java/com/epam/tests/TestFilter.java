package com.epam.tests;

import static com.epam.utils.FilterUtils.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.pages.FilterPage;
import com.epam.pages.FilterPresetPage;

public class TestFilter {
	private static WebDriver driver;
	private static FilterPage filterPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = setupBrowser();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		filterPage = openNotebooksFilterPage(driver);
	}

	@Test
	public void testFilter() {
//		assertFalse(isFilterApplied(filterPage));
		FilterPresetPage resultPage = doFilterByPrice(filterPage, "10000", "15000");
		resultPage.resetFilter();
//		assertEquals("1",getFilteredAmount(resultPage));
//		int calculatedAmount = calculateFilteredProducts(resultPage);
//		System.out.println("111");
//		assertEquals("1",Integer.toString(calculatedAmount));
		
	}

}
