package com.texasjake95.gradle;

import java.util.ArrayList;
import java.util.List;

public class ExtensionEclipseSetup {

	private List<EclipseData> data = new ArrayList<EclipseData>();

	public void addEclipseSetup(String dep, String src, String srcConfig)
	{
		addEclipseSetup(dep, null, src, "default", srcConfig);
	}

	public void addEclipseSetup(String dep, String code, String src, String codeConfig, String srcConfig)
	{
		data.add(new EclipseData(dep, code, src, codeConfig, srcConfig));
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
