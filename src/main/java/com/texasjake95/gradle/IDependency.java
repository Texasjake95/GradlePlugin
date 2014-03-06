package com.texasjake95.gradle;

import org.gradle.api.Project;

public interface IDependency {
	
	public String getDependencyName(Project project);

	boolean shouldDependencyBeDownloaded(Project project);
}
