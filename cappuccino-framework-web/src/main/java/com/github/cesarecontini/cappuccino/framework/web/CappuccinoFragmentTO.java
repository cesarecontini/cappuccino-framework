package com.github.cesarecontini.cappuccino.framework.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CappuccinoFragmentTO
{
	public CappuccinoFragmentTO(String fragmentsTemplateName, String fragmentName)
	{
		this.fragmentsTemplateName = fragmentsTemplateName;
		this.fragmentName = fragmentName;
	}

	public CappuccinoFragmentTO(CappuccinoForm cappuccinoForm)
	{
		this.isForm = true;
		this.cappuccinoForm = cappuccinoForm;
	}

	private String fragmentsTemplateName;
	private String fragmentName;
	private boolean isForm;
	private CappuccinoForm cappuccinoForm;
}
