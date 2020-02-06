package com.github.cesarecontini.cappuccino.framework.web;

import org.thymeleaf.spring5.util.DetailedError;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CappuccinoTemplateUtils
{

	private CappuccinoTemplateUtils()
	{
	}

	public static List<CappuccinoErrorMessage> getFormErrorMessages(List<DetailedError> detailedErrors, List<String> orderedFields)
	{
		return orderedFields.stream()
				.map(f -> detailedErrors.stream().filter(de -> de.getFieldName().equalsIgnoreCase(f)).collect(Collectors.toList()))
				.flatMap(Collection::stream)
				.collect(Collectors.toList())
				.stream()
				.map(de -> new CappuccinoErrorMessage(de.getFieldName(), de.getMessage()))
				.collect(Collectors.toList());

	}
}
