package com.github.cesarecontini.cappuccino.framework.web;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.thymeleaf.spring5.util.DetailedError;

import java.util.List;
import java.util.stream.Collectors;

@DisplayName("Assertions for CappuccinoTemplateUtilsTest")
class CappuccinoTemplateUtilsTest
{

	@Test
	void getFormErrorMessage()
	{
		DetailedError de1 = new DetailedError("fieldOne", "code", new Object[] {}, "Field one is required");
		DetailedError de2= new DetailedError("fieldTwo", "code", new Object[] {}, "Field two is required");
		DetailedError de3 = new DetailedError("fieldOne", "code", new Object[] {}, "Field one is too short");

		List<CappuccinoErrorMessage> cappuccinoErrorMessages = CappuccinoTemplateUtils.getFormErrorMessages(Lists.newArrayList(de1, de2, de3), Lists.newArrayList("fieldOne", "fieldTwo"));
		Assertions.assertEquals(3, cappuccinoErrorMessages.size(), "3 items");
		Assertions.assertTrue(cappuccinoErrorMessages.stream().map(CappuccinoErrorMessage::getFieldName).collect(
				Collectors.toList()).containsAll(Lists.newArrayList("fieldOne", "fieldTwo")));

		Assertions.assertTrue(cappuccinoErrorMessages.stream().map(CappuccinoErrorMessage::getMessage).collect(
				Collectors.toList()).containsAll(Lists.newArrayList("Field one is required", "Field two is required", "Field one is too short")));

		Assertions.assertEquals("fieldOne", cappuccinoErrorMessages.get(0).getFieldName());
		Assertions.assertEquals("fieldOne", cappuccinoErrorMessages.get(1).getFieldName());
		Assertions.assertEquals("fieldTwo", cappuccinoErrorMessages.get(2).getFieldName());
	}
}