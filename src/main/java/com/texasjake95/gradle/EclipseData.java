package com.texasjake95.gradle;

public class EclipseData {
	
	private String dep;
	private String code;
	private String src;
	
	EclipseData(String dep, String code, String src)
	{
		this.dep = dep;
		this.code = code;
		this.src = src;
	}
	
	public String getDep()
	{
		return this.dep;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public String getSrc()
	{
		return this.src;
	}
	
	public void setDep(String dep)
	{
		this.dep = dep;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public void setSrc(String src)
	{
		this.src = src;
	}
	
}
