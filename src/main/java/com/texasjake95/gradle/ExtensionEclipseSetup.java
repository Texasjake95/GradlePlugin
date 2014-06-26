package com.texasjake95.gradle;

import java.util.ArrayList;
import java.util.List;

public class ExtensionEclipseSetup {
	
	private List<EclipseData> data = new ArrayList<EclipseData>();
	
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
