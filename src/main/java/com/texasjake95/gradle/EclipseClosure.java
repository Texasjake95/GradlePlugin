package com.texasjake95.gradle;

import groovy.lang.Closure;
import groovy.util.Node;

import java.util.ArrayList;
import java.util.Iterator;

import org.gradle.api.Project;
import org.gradle.api.XmlProvider;

public class EclipseClosure extends Closure<XmlProvider> {

	private static final long serialVersionUID = -2401942758340829774L;

	public EclipseClosure(Project owner)
	{
		super(owner);
	}

	public Object doCall(Object args)
	{
		return call(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public XmlProvider call(Object provider)
	{
		Node nodes = ((XmlProvider) provider).asNode();
		Iterator<Node> iter = nodes.iterator();
		ArrayList<Node> toRemove = new ArrayList<Node>();
		Node node;
		while (iter.hasNext())
		{
			node = iter.next();
			ExtensionEclipseSetup eclipseSetup = ProjectHelper.getExtension((Project) this.getOwner(), "eclipseSetup");
			for (EclipseData data : eclipseSetup.getData())
			{
				handle((Project) this.getOwner(), node, data.getDep(), data.getCode(), data.getSrc(), data.getCodeConfig(), data.getSrcConfig(), toRemove);
			}
			handleGradle((Project) this.getOwner(), node);
		}
		for (Node remove : toRemove)
		{
			nodes.remove(remove);
		}
		return (XmlProvider) provider;
	}

	@SuppressWarnings("unchecked")
	private static void handleGradle(Project project, Node node)
	{
		String t = (String) node.attribute("path");
		String[] split = t.split("/");
		t = split[split.length - 1];
		if (t.contains("gradle") && t.contains("jar"))
		{
			if (project.getGradle().getGradleHomeDir() != null || project.getGradle().getGradleHomeDir().exists())
			{
				node.attributes().put("sourcepath", project.getGradle().getGradleHomeDir() + "/src/" + parseName(t));
			}
		}
		if (project.hasProperty("groovyVersion"))
			if (t.contains("groovy-all-" + project.property("groovyVersion")))
			{
				Object temp = ProjectHelper.find(project, "sourceOnly", "groovy-all-" + project.property("groovyVersion") + "-sources");
				node.attributes().put("sourcepath", temp);
			}
	}

	private static String parseName(String name)
	{
		String t = name;
		String[] split = t.split("-");
		if (split.length == 3)
			t = split[1];
		else if (split.length == 4)
			t = split[1] + "-" + split[2];
		return t;
	}

	@SuppressWarnings("unchecked")
	private static void handle(Project project, Node node, String depName, String code, String src, String codeConfig, String srcConfig, ArrayList<Node> list)
	{
		if (depName != null)
		{
			String codeJar = depName;
			String sourceJar = depName;
			if (code != null)
				codeJar += "-" + code;
			if (src != null)
				sourceJar += "-" + src;
			Object path = node.attribute("path");
			if (path != null && codeJar != null && sourceJar != null)
			{
				if (ProjectHelper.find(project, codeConfig, codeJar) != null && ProjectHelper.find(project, srcConfig, sourceJar) != null)
				{
					if (project.file(path).toString().equals(project.file(ProjectHelper.find(project, srcConfig, codeJar)).toString()))
					{
						node.attributes().put("sourcepath", ProjectHelper.find(project, srcConfig, sourceJar));
					}
					if (project.file(path).equals(ProjectHelper.find(project, srcConfig, sourceJar)))
					{
						list.add(node);
					}
				}
			}
		}
	}
}
