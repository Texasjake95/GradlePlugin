package com.texasjake95.gradle;

import java.util.HashMap;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

public class ProjectHelper {
	
	public static void addDependency(Project project, String dep)
	{
		project.getDependencies().add("compile", dep);
	}
	
	public static void addMaven(Project project, final String name, final String url)
	{
		project.getRepositories().maven(new Action<MavenArtifactRepository>() {
			
			@Override
			public void execute(MavenArtifactRepository repo)
			{
				repo.setName(name);
				repo.setUrl(url);
			}
		});
	}
	
	public static void applyPlugin(Project project, String plugin)
	{
		if (!project.getPlugins().hasPlugin(plugin))
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("plugin", plugin);
			project.apply(map);
		}
	}
	
	public static void addRepos(Project project)
	{
		project.getRepositories().mavenLocal();
		project.getRepositories().mavenCentral();
		addMaven(project, "texasjake95Maven", "https://github.com/Texasjake95/maven-repo/raw/master/");
	}
	
	public static void applyPlugins(Project project)
	{
		applyPlugin(project, "java");
		applyPlugin(project, "eclipse");
		applyPlugin(project, "maven");
	}
}
