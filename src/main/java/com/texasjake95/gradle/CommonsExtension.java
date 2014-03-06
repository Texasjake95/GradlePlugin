package com.texasjake95.gradle;

import org.gradle.api.Project;

public class CommonsExtension {
	
	@SuppressWarnings("unused")
	private Project project;
	protected String version = "1.0.18";
	protected boolean shouldDownload = true;
	
	public CommonsExtension(Project project)
	{
		this.project = project;
	}
	
	public String getVesion()
	{
		return this.version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public boolean getShouldDownload()
	{
		return this.shouldDownload;
	}
	
	public void setShouldDownload(boolean shouldDownload)
	{
		this.shouldDownload = shouldDownload;
	}
}
