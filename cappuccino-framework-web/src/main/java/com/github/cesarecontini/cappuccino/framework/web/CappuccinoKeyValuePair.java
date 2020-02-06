package com.github.cesarecontini.cappuccino.framework.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CappuccinoKeyValuePair
{
	public CappuccinoKeyValuePair(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	private String key;
	private String value;
	private String dataTarget;
}
