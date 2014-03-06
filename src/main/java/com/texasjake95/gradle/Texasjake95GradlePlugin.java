package com.texasjake95.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginConvention;

public class Texasjake95GradlePlugin implements Plugin<Project> {
	
	private static boolean debug = false;
	
	@Override
	public void apply(Project project)
	{
		if (debug)
			System.out.println("Applying Plugins");
		this.applyPlugins(project);
		if (debug)
			System.out.println("Adding Repos");
		this.addRepos(project);
		addDelayedDependency(new CommonsDependency());
		if (debug)
			System.out.println("Creating Extension");
		project.getExtensions().create("CommonsVersion", CommonsExtension.class);
		JavaPluginConvention javaConv = (JavaPluginConvention) project.getConvention().getPlugins().get("java");
		if (debug)
			System.out.println("Setting Compatibility");
		javaConv.setSourceCompatibility("1.7");
		javaConv.setTargetCompatibility("1.7");
		if (debug)
			System.out.println("Setting Group");
		project.setGroup("com.texasjake95");
		project.getGradle().addBuildListener(new DependencyAdder());
	}
	
	public void addRepos(Project project)
	{
		project.getRepositories().mavenLocal();
		project.getRepositories().mavenCentral();
		ProjectHelper.addMaven(project, "texasjake95Maven", "https://github.com/Texasjake95/maven-repo/raw/master/");
	}
	
	public void applyPlugins(Project project)
	{
		ProjectHelper.applyPlugin(project, "java");
		ProjectHelper.applyPlugin(project, "eclipse");
		ProjectHelper.applyPlugin(project, "maven");
	}
	
	public static void addDelayedDependency(IDependency dep)
	{
		DependencyAdder.dependencies.add(dep);
	}
}
