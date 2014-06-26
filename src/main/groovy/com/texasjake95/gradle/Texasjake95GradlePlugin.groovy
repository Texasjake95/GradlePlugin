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
		ProjectHelper.applyPlugins(project)
		if (debug)
			System.out.println("Adding Repos")
		ProjectHelper.addRepos(project)
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
