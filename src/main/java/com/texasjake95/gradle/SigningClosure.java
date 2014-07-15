package com.texasjake95.gradle;

import java.util.concurrent.Callable;

import org.gradle.api.Project;

public class SigningClosure implements Callable<Boolean> {

	Project project;

	public SigningClosure(Project project)
	{
		this.project = project;
	}

	@Override
	public Boolean call() throws Exception
	{
		if (project.getGradle().getTaskGraph().hasTask("uploadArchives"))
			if (project.hasProperty("signJars"))
				return true;
		return false;
	}
}
