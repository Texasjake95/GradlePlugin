package com.texasjake95.gradle

import org.gradle.api.Project


class ProjectHelperGroovy
{
	
	public static <T> T getExtension(Project project, String name)
	{
		return (T)project.extensions.findByName(name)
	}
	
	public static def find(Project project,String depName)
	{
		return project.configurations.default.find { it.name.contains(depName) }
	}
}
