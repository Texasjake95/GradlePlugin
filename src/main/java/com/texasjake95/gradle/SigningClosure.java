package com.texasjake95.gradle;

import groovy.lang.Closure;

import org.gradle.api.Project;

public class SigningClosure extends Closure<Boolean> {

	private static final long serialVersionUID = 8489621936872239815L;
	Project project;

	public SigningClosure(Project project)
	{
		super(project);
		this.project = project;
	}

	@Override
	public Boolean call()
	{
		if (project.getGradle().getTaskGraph().hasTask("uploadArchives"))
			if (project.hasProperty("signJars"))
				return true;
		return false;
	}
}
