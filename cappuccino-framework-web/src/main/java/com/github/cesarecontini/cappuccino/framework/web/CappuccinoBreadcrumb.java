package com.github.cesarecontini.cappuccino.framework.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CappuccinoBreadcrumb
{
	private String label;
	private String link;
}
