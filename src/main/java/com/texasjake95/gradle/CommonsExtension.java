package com.texasjake95.gradle;


public class CommonsExtension {
	
	public String version = "1.0.18";
	public boolean shouldDownload = true;
	
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
	
	public boolean getShouldDownload()
	{
		return this.shouldDownload;
	}
	
	public void setShouldDownload(boolean shouldDownload)
	{
		this.shouldDownload = shouldDownload;
	}
}
