package com.texasjake95.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.api.tasks.javadoc.Javadoc;

public class JavaProxy {
	
	private static boolean debug = false;
	
	public static void apply(Project project)
	{
		if (debug)
			System.out.println("Applying Plugins");
		ProjectHelper.applyPlugins(project);
		JavaPluginConvention javaConv = (JavaPluginConvention) project.getConvention().getPlugins().get("java");
		Jar task = ProjectHelper.addTask(project, "javadocJar", Jar.class);
		{
			task.dependsOn("javadoc");
			task.setClassifier("javadoc");
			task.from(((Javadoc) project.getTasks().getByName("javadoc")).getDestinationDir());
		}
		project.getArtifacts().add("archives", task);
		task = ProjectHelper.addTask(project, "sourceJar", Jar.class);
		{
			task.dependsOn("classes");
			task.setClassifier("sources");
			task.from(javaConv.getSourceSets().getByName("main").getAllSource());
		}
		project.getArtifacts().add("archives", task);
		project.getTasks().getByName("build").dependsOn("sourceJar", "javadocJar");
		project.getExtensions().create("eclipseSetup", ExtensionEclipseSetup.class);
		if (debug)
			System.out.println("Adding Repos");
		ProjectHelper.addRepos(project);
		if (debug)
			System.out.println("Setting Compatibility");
		javaConv.setSourceCompatibility("1.7");
		javaConv.setTargetCompatibility("1.7");
		if (debug)
			System.out.println("Setting Group");
		project.setGroup("com.texasjake95");
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
		ProjectHelper.applyPlugin(project, "maven-publish");
	}
}
