package com.texasjake95.gradle;

public class EclipseData {

	private String dep;
	private String code;
	private String codeConfig;
	private String src;
	private String srcConfig;

	EclipseData(String dep, String code, String src, String codeConfig, String srcConfig)
	{
		this.dep = dep;
		this.code = code;
		this.src = src;
		this.codeConfig = codeConfig;
		this.srcConfig = srcConfig;
	}

	public String getCodeConfig()
	{
		return codeConfig;
	}

	public void setCodeConfig(String codeConfig)
	{
		this.codeConfig = codeConfig;
	}

	public String getSrcConfig()
	{
		return srcConfig;
	}

	public void setSrcConfig(String srcConfig)
	{
		this.srcConfig = srcConfig;
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
