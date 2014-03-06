package com.texasjake95.gradle;

import java.util.ArrayList;

import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;

public class DependencyAdder implements BuildListener {
	
	public static ArrayList<IDependency> dependencies = new ArrayList<IDependency>();
	
	@Override
	public void buildFinished(BuildResult arg0)
	{
	}
	
	@Override
	public void buildStarted(Gradle arg0)
	{
	}
	
	@Override
	public void projectsEvaluated(Gradle arg0)
	{
		for (IDependency dep : dependencies)
			ProjectHelper.addDependency(arg0.getRootProject(), dep.getDependencyName(arg0.getRootProject()));
	}
	
	@Override
	public void projectsLoaded(Gradle arg0)
	{
	}
	
	@Override
	public void settingsEvaluated(Settings arg0)
	{
	}
}
