package com.epam.runners;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import com.epam.steps.NotebookFiltering;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class RunFilterScenarios extends JUnitStories {
	private Configuration configuration;

	public RunFilterScenarios() {
		super();
		configuration = new MostUsefulConfiguration();
	}

	@Override
	public Configuration configuration() {
		return configuration;
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new NotebookFiltering());
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(System.getProperty("user.dir") + "\\src\\test\\resources\\stories\\",
				Arrays.asList("**\\*.story"), Arrays.asList(""), "stories\\");
	}
}