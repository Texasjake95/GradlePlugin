package com.texasjake95.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.bundling.Jar

class Texasjake95GradlePlugin implements Plugin<Project>
{
	private static boolean debug = false
	
	@Override
	public void apply(Project project)
	{
		if (debug)
			System.out.println("Applying Plugins")
		ProjectHelper.applyPlugins(project)
		
		JavaPluginConvention javaConv = (JavaPluginConvention) project.getConvention().getPlugins().get("java")
		
		ProjectHelper.addTask(project, "javadocJar", Jar.class)
		{
			dependsOn "javadoc"
			classifier "javadoc"
			from project.buildDir.absolutePath + "/docs/javadoc"
		}
		
		ProjectHelper.addTask(project, "sourceJar", Jar.class)
		{
			dependsOn "classes"
			classifier = 'sources'
			from javaConv.sourceSets.main.java
		}
		
		project.extensions.create("eclipseSetup", ExtensionEclipseSetup.class)
		
		if (debug)
			System.out.println("Adding Repos")
		ProjectHelper.addRepos(project)
		if (debug)
			System.out.println("Setting Compatibility")
		javaConv.setSourceCompatibility("1.7")
		javaConv.setTargetCompatibility("1.7")
		if (debug)
			System.out.println("Setting Group")
		project.group = "com.texasjake95"
		project.eclipse.classpath.file.withXml
		{
			def node = it.asNode()
			def toRemove = new ArrayList<Node>()
			node.each
			{
				for(EclipseData dep : project.eclipseSetup.data)
					handle(project,dep.dep,it,toRemove,dep.code,dep.src)
			}
			toRemove.each
			{ node.remove(it) }
		}
	}
	
	public void addTasks(Project project)
	{
		project.task("")
		{
		}
	}
	
	def handle(project, depName,node,list,code,src)
	{
		if(depName)
		{
			def codejar = depName
			def sourceJar = depName
			if(code)
				codejar += "-" + code
			if(src)
				sourceJar += "-" + src
			
			def filePath = node.attribute('path')
			if(filePath && codejar && sourceJar && ProjectHelperGroovy.find(project,codejar) && ProjectHelperGroovy.find(project,sourceJar))
			{
				if (project.file(filePath) == project.file(ProjectHelperGroovy.find(project,codejar)))
				{
					node.attributes().put("sourcepath",ProjectHelperGroovy.find(project,sourceJar))
				}
				if (project.file(filePath) == project.file(ProjectHelperGroovy.find(project,sourceJar)))
				{
					list.add(node)
				}
			}
		}
	}
}
