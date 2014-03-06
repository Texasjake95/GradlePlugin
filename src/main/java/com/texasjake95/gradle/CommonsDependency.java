package com.texasjake95.gradle;

import org.gradle.api.Project;

public class CommonsDependency implements IDependency {
	
	@Override
	public String getDependencyName(Project project)
	{
		CommonsExtension commons = (CommonsExtension) project.getExtensions().getByName("CommonsVersion");
		return "com.texasjake95:Texasjake95Commons:" + commons.getVesion();
	}
	
	@Override
	public boolean shouldDependencyBeDownloaded(Project project)
	{
		CommonsExtension commons = (CommonsExtension) project.getExtensions().getByName("CommonsVersion");
		return Boolean.parseBoolean(commons.getShouldDownload());
	}
}
