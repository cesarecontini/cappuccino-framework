package com.github.cesarecontini.cappuccino.framework.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CappuccinoHtmlUtilsTest
{

	@Test
	public void getUnorderedList()
	{
		String html = CappuccinoHtmlUtils.getUnorderedList("some-id", "Some pre-text", Lists.newArrayList("One", "two", "three"));
		Assertions.assertEquals("<p id=\"some-id\">Some pre-text</p><ul id=\"ul-some-id\" class=\"list list-bullet\"><li id=\"some-id-0\">One</li><li id=\"some-id-1\">two</li><li id=\"some-id-2\">three</li></ul>", html);
	}

	@Test
	public void getOrderedList()
	{
		String html = CappuccinoHtmlUtils.getOrderedList("some-id", "Some pre-text", Lists.newArrayList("One", "two", "three"));
		Assertions.assertEquals("<p id=\"some-id\">Some pre-text</p><ol id=\"ol-some-id\" class=\"list list-number\"><li id=\"some-id-0\">One</li><li id=\"some-id-1\">two</li><li id=\"some-id-2\">three</li></ol>", html);

	}

	@Test
	public void getParagraphs()
	{
		String html = CappuccinoHtmlUtils.getParagraphs("some-id", Lists.newArrayList("One", "two", "three"));
		Assertions.assertEquals("<p id=\"some-id-0\">One</p><p id=\"some-id-1\">two</p><p id=\"some-id-2\">three</p>", html);
	}

	@Test
	public void sanitizeHtmlMethod()
	{
		String sanitizedHtml = CappuccinoHtmlUtils.sanitizeHtml("<p class=\"a-class\">" +
				"<a href=\"/unsafe-link\">Link to a phishing page</a>" +
				"<span><b>Bold text</b></span>" +
				"<ul class=\"myclass\"><li id=\"some-id\">Unordered list</li></ul>" +
				"<ol><li>Unordered list</li></ol>" +
				"<img src=\"/some-hackers-image\" alt=\"Hacking in an accessible way\" />" +
				"<script>someNastyJavascript()</script>" +
				"</p>");
		Assertions.assertEquals("<p class=\"a-class\">Link to a phishing page<b>Bold text</b></p><ul class=\"myclass\"><li id=\"some-id\">Unordered list</li></ul><ol><li>Unordered list</li></ol>", sanitizedHtml);
	}

	@Test
	public void sanitizeHtmlMethodWithSingleQuotes()
	{
		String sanitizedHtml = CappuccinoHtmlUtils.sanitizeHtml("<p class=\"a-class\" id=\"an-id\">" +
				"NHS 'BSA' unit" +
				"</p>");

		Assertions.assertEquals("<p class=\"a-class\" id=\"an-id\">NHS &#39;BSA&#39; unit</p>", sanitizedHtml);

	}
}