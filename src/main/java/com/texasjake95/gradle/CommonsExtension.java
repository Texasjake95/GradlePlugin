package com.texasjake95.gradle;

import org.gradle.api.Project;

public class CommonsExtension {
	
	public String version = "1.0.18";
	public String shouldDownload = "true";
	
	public CommonsExtension()
	{
	}
	
	public String getVesion()
	{
		return this.version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public String getShouldDownload()
	{
		return this.shouldDownload;
	}
	
	public void setShouldDownload(String shouldDownload)
	{
		this.shouldDownload = shouldDownload;
	}
}
