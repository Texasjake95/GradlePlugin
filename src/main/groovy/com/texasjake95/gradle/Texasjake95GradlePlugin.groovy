package com.texasjake95.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention

class Texasjake95GradlePlugin implements Plugin<Project>
{
	private static boolean debug = false
	
	@Override
	public void apply(Project project)
	{
		project.extensions.create("eclipseSetup", ExtensionEclipseSetup.class)
		if (debug)
			System.out.println("Applying Plugins")
		this.applyPlugins(project)
		if (debug)
			System.out.println("Adding Repos")
		this.addRepos(project)
		JavaPluginConvention javaConv = (JavaPluginConvention) project.getConvention().getPlugins().get("java")
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
			if(filePath && codejar && sourceJar && find(project,codejar) && find(project,sourceJar))
			{
				if (file(filePath) == file(find(project,codejar)))
				{
					node.attributes().put("sourcepath",find(project,sourceJar))
				}
				if (file(filePath) == file(find(project,sourceJar)))
				{
					list.add(node)
				}
			}
		}
	}
	
	def find(project, depName)
	{
		return project.configurations.default.find { it.name.contains(depName) }
	}
	
	private <T> T getExtension(Project project, String name)
	{
		return (T)project.extensions.findByName(name)
	}
	
	public void addRepos(Project project)
	{
		project.getRepositories().mavenLocal()
		project.getRepositories().mavenCentral()
		ProjectHelper.addMaven(project, "texasjake95Maven", "https://github.com/Texasjake95/maven-repo/raw/master/")
	}
	
	public void applyPlugins(Project project)
	{
		ProjectHelper.applyPlugin(project, "java")
		ProjectHelper.applyPlugin(project, "eclipse")
		ProjectHelper.applyPlugin(project, "maven")
		ProjectHelper.applyPlugin(project, "maven-publish")
	}
}
