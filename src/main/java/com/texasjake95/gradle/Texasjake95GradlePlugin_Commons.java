package com.texasjake95.gradle;

import org.gradle.api.Project;

public class Texasjake95GradlePlugin_Commons extends Texasjake95GradlePlugin {
	
	@Override
	public void apply(Project project)
	{
		super.apply(project);
		CommonsExtension commons = (CommonsExtension) project.getExtensions().getByName("CommonsVersion");
		commons.shouldDownload = false;
	}
}
