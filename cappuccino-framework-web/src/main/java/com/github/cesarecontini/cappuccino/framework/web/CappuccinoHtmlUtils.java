package com.github.cesarecontini.cappuccino.framework.web;

import org.apache.commons.lang3.StringUtils;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.util.List;
import java.util.stream.IntStream;

public class CappuccinoHtmlUtils
{
	private static final String P_FORMAT = "<p id=\"%s\">%s</p>";
	private static final String LI_FORMAT = "<li id=\"%s\">%s</li>";

	private CappuccinoHtmlUtils()
	{
		// nothing to do here
	}

	private static StringBuilder getElements(StringBuilder sb, String baseId, String tag, String tagFormat, String tagCssClass, List<String> elements)
	{
		if(StringUtils.isNotEmpty(tag)) sb.append(String.format("<%s id=\"%s\"%s>", tag, tag + "-" + baseId, tagCssClass == null ? StringUtils.EMPTY : " class=\"" + tagCssClass + "\""));
		IntStream.range(0, elements.size()).forEach(i -> sb.append( String.format(tagFormat, baseId + "-" + i, elements.get(i))));
		if(StringUtils.isNotEmpty(tag)) sb.append(String.format("</%s>", tag));
		return sb;
	}

	private static String getList(String baseId, String preListText, String tag, String tagCssClass, List<String> elements)
	{
		if(StringUtils.isEmpty(baseId) || StringUtils.isEmpty(tag) || elements == null || elements.size() == 0) return StringUtils.EMPTY;

		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotEmpty(preListText)) sb.append(String.format(P_FORMAT, baseId, preListText));
		sb = getElements(sb, baseId, tag, LI_FORMAT, tagCssClass, elements);
		return sb.toString();
	}

	public static String getUnorderedList(String baseId, String preListText, List<String> elements)
	{
		return getList(baseId, preListText, "ul", "list list-bullet", elements);
	}

	public static String getOrderedList(String baseId, String preListText, List<String> elements)
	{
		return getList(baseId, preListText, "ol", "list list-number", elements);
	}

	public static String getParagraphs(String baseId, List<String> elements)
	{
		return getElements(new StringBuilder(), baseId, null,  P_FORMAT, null, elements).toString();
	}

	public static String sanitizeHtml(String potentiallyUnsafeHtml)
	{
		PolicyFactory policyFactory = new HtmlPolicyBuilder()
				.allowElements("p", "b", "table", "thead", "tbody", "tr", "td", "th", "div", "ul", "ol",  "li", "h1", "h2", "h3", "h4")
				.allowAttributes(new String[] {"class", "id"})
				.globally()
				.toFactory();

		return Sanitizers.BLOCKS
					.and(Sanitizers.FORMATTING)
					.and(policyFactory)
					.sanitize(potentiallyUnsafeHtml);
	}
}
