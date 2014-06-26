package com.texasjake95.gradle;

import java.util.List;

import com.beust.jcommander.internal.Lists;

public class ExtensionEclipseSetup {
	
	private List<EclipseData> data = Lists.newArrayList();
	
	public void addEclipseSetup(String dep, String src)
	{
		addEclipseSetup(dep, null, src);
	}
	
	public void addEclipseSetup(String dep, String code, String src)
	{
		data.add(new EclipseData(dep, code, src));
	}
	
	public List<EclipseData> getData()
	{
		return data;
	}
	
	public void setData(List<EclipseData> data)
	{
		this.data = data;
	}
}
