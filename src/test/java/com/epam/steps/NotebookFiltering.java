package com.epam.steps;

import static com.epam.utils.FilterUtils.*;
import static org.junit.Assert.*;

import java.util.List;

import org.jbehave.core.annotations.*;
import org.openqa.selenium.WebDriver;

import com.epam.pages.FilterPage;
import com.epam.pages.FilterPresetPage;

public class NotebookFiltering {
	private static WebDriver driver;
	private static FilterPage filterPage;
	private static FilterPresetPage resultPage;

	@BeforeStory
	public void beforeStory() {
		driver = setupBrowser();
	}

	@Given("notebook filtering page")
	public void givenNotebookFilteringPage() {
		filterPage = openNotebooksFilterPage(driver);
	}

	@Given("all filters are turned off")
	public void givenAllFiltersAreTurnedOff() {
		// assertFalse(isFilterApplied(filterPage));
	}

	@When("Customer includes a filter parameter $parameter with value $value")
	public void whenCustomerIncludesAFilterParameterWithValue(@Named("parameter") String filterParameter,
			@Named("value") String filterValue) {
		resultPage = doFilter(filterPage, filterParameter, filterValue);
	}

	@Then("amount of displayed filtered notebooks equals $amount")
	public void thenAmountOfDisplayedNotebooksEquals(@Named("amount") String filterAmount) {
		assertEquals(filterAmount, getFilteredAmount(resultPage));
		assertEquals(Integer.parseInt(filterAmount),calculateFilteredProducts(resultPage));
	}

	@Then("all notebooks with such $parameters and $values are displayed")
	public void allNotebooksWithSuchParameterAndValueAreDisplayed(@Named("parameters") List<String> filterParameters,
			@Named("values") List<String> filterValues) {
		// // Quite difficult to implement using black-box model
	}

	@Then("all displayed notebooks have value $value in parameter $parameter")
	public void thenAllDisplayedNotebooksHaveValuevalueInParameterparameter(@Named("parameter") String filterParameter,
			@Named("value") String filterValue) {
		// Quite difficult to implement using black-box model
	}

	@When("Customer filters by $min price and $max price")
	public void filterByMinAndMaxPrice(@Named("min") String min, @Named("max") String max) {
		resultPage = doFilterByPrice(filterPage, min, max);
	}

	@Then("all displayed notebooks' cost ranges between $min and $max")
	public void checkDisplayedFilterByMinAndMaxPrice(@Named("min") String min, @Named("max") String max) {
		int minPrice = Integer.parseInt(min);
		int maxPrice = Integer.parseInt(max);
		List<Integer> productPrices = getCataloguePrices(resultPage);
		for (int i = 0; i < productPrices.size(); i++) {
			assertTrue(((minPrice <= productPrices.get(i)) && (maxPrice >= productPrices.get(i))));
		}
	}

	@Then("all notebooks with price between $min and $max price are displayed")
	public void checAllFilterByMinAndMaxPrice(@Named("min") String min, @Named("max") String max) {
		// Quite difficult to implement using black-box model
	}

	@When("Customer includes a filter parameters $parameters with value $values")
	public void includesFilterWithParametersAndValue(@Named("parameters") List<String> filterParameters, @Named("values") List<String> filterValues) {
		if (filterParameters.size()!=0){
			resultPage = doFilter(filterPage, filterParameters.get(0), filterValues.get(0));
		}
		for (int i = 1; i < filterParameters.size(); i++) {
			resultPage = doFilter(filterPage, filterParameters.get(i), filterValues.get(i));
		}
	}
	@Then("all displayed notebooks have values $values in parameters $parameters")
	public void allDisplayedNotebooksMeetFilter(@Named("parameters") List<String> filterParameters, @Named("values") List<String> filterValues) {
		//Quite difficult to implement using black-box model
	}
	
	@AfterStory
	public void afterStory() {
		driver.close();
	}
}