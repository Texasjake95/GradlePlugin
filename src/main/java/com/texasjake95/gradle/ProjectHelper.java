package com.texasjake95.gradle;

import java.io.File;
import java.util.HashMap;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

public class ProjectHelper {

	public static void addDependency(Project project, String convention, String dep)
	{
		if (project.getConfigurations().findByName(convention) == null)
			project.getConfigurations().create(convention);
		project.getDependencies().add(convention, dep);
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

	public static void addRepos(Project project)
	{
		project.getRepositories().mavenLocal();
		project.getRepositories().mavenCentral();
		ProjectHelper.addMaven(project, "texasjake95Maven", "https://github.com/Texasjake95/maven-repo/raw/master/");
		ProjectHelper.addMaven(project, "sonatype snapshots", "https://oss.sonatype.org/content/repositories/snapshots/");
		ProjectHelper.addMaven(project, "sonatype releases", "https://oss.sonatype.org/content/repositories/releases/");
	}

	@SuppressWarnings("unchecked")
	public static <T extends Task> T addTask(Project proj, String name, Class<T> type)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("type", type);
		return (T) proj.task(map, name);
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

	public static void applyPlugins(Project project)
	{
		applyPlugin(project, "java");
		applyPlugin(project, "eclipse");
		applyPlugin(project, "idea");
		applyPlugin(project, "maven");
		applyPlugin(project, "signing");
	}

	public static File find(Project project, String configuration, String depName)
	{
		if (project.getConfigurations().getNames().contains(configuration))
			for (File file : project.getConfigurations().getByName(configuration).resolve())
			{
				String fileName = file.getName();
				if (fileName.contains(depName))
					return file;
			}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getExtension(Project project, String name)
	{
		return (T) project.getExtensions().findByName(name);
	}

	public static File getFile(Project project, String configuration, String depName, String version, String classifer)
	{
		String depFile = getFileName(depName, version, classifer);
		return find(project, configuration, depFile);
	}

	private static String getFileName(String artifact, String version, String classifer)
	{
		String format = classifer == null ? "%s-%s" : "%s-%s-%s";
		Object[] array = classifer == null ? new Object[] { artifact, version } : new Object[] { artifact, version, classifer };
		return String.format(format, array);
	}
}
