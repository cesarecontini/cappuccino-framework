package com.github.cesarecontini.cappuccino.framework.web;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CappuccinoFormBuilder<T>
{
	private CappuccinoForm cappuccinoForm;
	private List<String> tempFieldNames = new ArrayList<>();

	public CappuccinoFormBuilder(String formId, CappuccinoForm.CappuccinoFormMethod method, String formAction)
	{
		CappuccinoForm form = new CappuccinoForm();
		form.setFormId(formId);
		form.setCappuccinoFormMethod(method);
		form.setFormAction(formAction);

		this.cappuccinoForm = form;
	}

	private List<CappuccinoKeyValuePair> getOptions(CappuccinoField cappuccinoField)
	{
		return IntStream.range(0, cappuccinoField.optionIds().length).boxed()
				.map(m -> new CappuccinoKeyValuePair(cappuccinoField.optionIds()[m], cappuccinoField.optionValues()[m]))
				.collect(Collectors.toList());
	}

	public CappuccinoFormBuilder withDefaultFields(Class<T> cls)
	{
		for(Field f : cls.getDeclaredFields())
		{
			CappuccinoField[] cappuccinoFields = f.getAnnotationsByType(CappuccinoField.class);
			if (cappuccinoFields.length > 0)
			{
				CappuccinoField cappuccinoField = cappuccinoFields[0];

				switch (cappuccinoField.cappuccinoFormFieldType())
				{
					case TEXT:
						this.addTextField(f.getName(), cappuccinoField.label(), cappuccinoField.hint(), cappuccinoField.maxLength(), cappuccinoField
								.displayMode());
						break;
					case NUMBER:
						this.addNumberField(f.getName(), cappuccinoField.label(), cappuccinoField.hint(), cappuccinoField
								.min(), cappuccinoField.max(), cappuccinoField.displayMode());
						break;
					case TEXTAREA:
						this.addTextAreaField(f.getName(), cappuccinoField.label(), cappuccinoField.hint(), cappuccinoField
								.displayMode());
						break;
					case CHECKBOX:
						this.addCheckboxField(f.getName(),  cappuccinoField.label(), cappuccinoField.hint(), getOptions(
								cappuccinoField), cappuccinoField.displayMode(), cappuccinoField.radioOrCheckboxInline());
						break;
					case RADIO:
						this.addRadioField(f.getName(),  cappuccinoField.label(), cappuccinoField.hint(), getOptions(
								cappuccinoField), cappuccinoField.displayMode(), cappuccinoField.radioOrCheckboxInline());
						break;
					case DATE:
						this.addDateField(f.getName().replace("Day", StringUtils.EMPTY), cappuccinoField.label(), cappuccinoField
								.hint());
						break;
					case SELECT:
						this.addSelectField(f.getName(), cappuccinoField.label(), cappuccinoField.hint(), getOptions(
								cappuccinoField));
						break;
					case HIDDEN:
						this.addHiddenField(f.getName());
						break;
				}

			}
		}
		return this;
	}

	public CappuccinoFormBuilder withDataTargetField(String checkBoxOrRadioFieldName, String checkBoxOrRadioValue, String dataTargetedFieldName)
	{
		boolean dataTargetFieldSet = false;
		boolean targetedByIdSet = false;
		String dataTargetId = String.format("%s-%s", checkBoxOrRadioValue, dataTargetedFieldName);
		for(CappuccinoFormField field : this.cappuccinoForm.getFields())
		{
			if(
				(CappuccinoFormFieldType.RADIO.equals(field.getType()) || CappuccinoFormFieldType.CHECKBOX.equals(field.getType())) &&
				field.getFieldName().equals(checkBoxOrRadioFieldName)
			)
			{
				List<CappuccinoKeyValuePair> cappuccinoKeyValuePairs = field.getOptions()
						.stream()
						.map(mkvp -> {
							if(mkvp.getValue() != null && checkBoxOrRadioValue != null && mkvp.getValue().equals(checkBoxOrRadioValue))
							{
								mkvp.setDataTarget(dataTargetId);
							}
							return mkvp;
						})
						.collect(Collectors.toList());
				field.setOptions(cappuccinoKeyValuePairs);

				dataTargetFieldSet = true;
			}

			if(dataTargetedFieldName.equals(field.getFieldName()))
			{
				field.setDataTargetField(true);
				field.setTargetedById(dataTargetId);
				targetedByIdSet = true;
			}

			if(dataTargetFieldSet && targetedByIdSet) break;
		}

		return this;
	}

	public CappuccinoFormBuilder withFieldIntroHeaderAndHintText(String fieldName, String headerTitle, String htmlIntroText)
	{
		List<CappuccinoFormField> fields = this.cappuccinoForm.getFields()
				.stream()
				.map(f -> {
					if(f.getFieldName().equals(fieldName))
					{
						if(StringUtils.isNotEmpty(headerTitle)) f.setIntroHeader(headerTitle);
						if(StringUtils.isNotEmpty(htmlIntroText)) f.setIntroHtmlHintText(htmlIntroText);
					}
					return f;
				}).collect(Collectors.toList());
		this.cappuccinoForm.setFields(fields);
		return this;
	}

	private CappuccinoFormField getCappuccinoFormField(CappuccinoFormFieldType cappuccinoFormFieldType, String fieldName, String label, String hintText)
	{
		CappuccinoFormField field = new CappuccinoFormField();
		field.setFieldName(fieldName);
		field.setLabel(label);
		field.setType(cappuccinoFormFieldType);
		field.setHintText(hintText);
		return field;
	}

	public CappuccinoFormBuilder addTextField(String fieldName, String label, String hintText)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.TEXT, fieldName, label, hintText);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addTextField(String fieldName, String label, String hintText, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.TEXT, fieldName, label, hintText);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addTextField(String fieldName, String label, String hintText, int maxLength)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.TEXT, fieldName, label, hintText);

		field.setMaxLength(maxLength);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addTextField(String fieldName, String label, String hintText, int maxLength, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.TEXT, fieldName, label, hintText);

		field.setMaxLength(maxLength);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addTextAreaField(String fieldName, String label, String hintText)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.TEXTAREA, fieldName, label, hintText);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addTextAreaField(String fieldName, String label, String hintText, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.TEXTAREA, fieldName, label, hintText);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}


	public CappuccinoFormBuilder addNumberField(String fieldName, String label, String hintText)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.NUMBER, fieldName, label, hintText);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addNumberField(String fieldName, String label, String hintText, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.NUMBER, fieldName, label, hintText);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addNumberField(String fieldName, String label, String hintText, int min, int max)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.NUMBER, fieldName, label, hintText);

		field.setMin(min);
		field.setMax(max);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addNumberField(String fieldName, String label, String hintText, int min, int max, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.NUMBER, fieldName, label, hintText);

		field.setMin(min);
		field.setMax(max);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}


	public CappuccinoFormBuilder addRadioField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.RADIO, fieldName, label, hintText);

		field.setOptions(options);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addRadioField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.RADIO, fieldName, label, hintText);

		field.setOptions(options);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addRadioField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options, CappuccinoFormField.DisplayMode displayMode, boolean inline)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.RADIO, fieldName, label, hintText);

		field.setOptions(options);
		field.setDisplayMode(displayMode);
		field.setRadioOrCheckboxInline(inline);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addRadioFieldInline(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.RADIO, fieldName, label, hintText);

		field.setOptions(options);
		field.setRadioOrCheckboxInline(true);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addCheckboxField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.CHECKBOX, fieldName, label, hintText);

		field.setOptions(options);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addCheckboxField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.CHECKBOX, fieldName, label, hintText);
		field.setOptions(options);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addCheckboxField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options, CappuccinoFormField.DisplayMode displayMode, boolean isRadioOrCheckboxInline)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.CHECKBOX, fieldName, label, hintText);
		field.setOptions(options);
		field.setDisplayMode(displayMode);
		field.setRadioOrCheckboxInline(isRadioOrCheckboxInline);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addCheckboxFieldInline(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.CHECKBOX, fieldName, label, hintText);
		field.setOptions(options);
		field.setRadioOrCheckboxInline(true);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addSelectField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.SELECT, fieldName, label, hintText);
		field.setOptions(options);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addSelectField(String fieldName, String label, String hintText, List<CappuccinoKeyValuePair> options, CappuccinoFormField.DisplayMode displayMode)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.SELECT, fieldName, label, hintText);
		field.setOptions(options);
		field.setDisplayMode(displayMode);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}


	public CappuccinoFormBuilder addDateField(String fieldName, String label, String hintText)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.DATE, fieldName, label, hintText);

		field.setDayDateFieldName(String.format("%sDay", fieldName));
		field.setMonthDateFieldName(String.format("%sMonth", fieldName));
		field.setYearDateFieldName(String.format("%sYear", fieldName));

		tempFieldNames.add(field.getDayDateFieldName());
		tempFieldNames.add(field.getMonthDateFieldName());
		tempFieldNames.add(field.getYearDateFieldName());

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder addHiddenField(String fieldName)
	{
		CappuccinoFormField field = getCappuccinoFormField(CappuccinoFormFieldType.HIDDEN, fieldName, null, null);

		tempFieldNames.add(fieldName);

		this.cappuccinoForm.getFields().add(field);
		return this;
	}

	public CappuccinoFormBuilder withSubmitButtonText(String submitButtonText)
	{
		this.cappuccinoForm.setSubmitButtonText(submitButtonText);
		return this;
	}

	public CappuccinoFormBuilder withLinkButtonText(String text, String hrefLink)
	{
		this.cappuccinoForm.setLinkButtonText(text);
		this.cappuccinoForm.setLinkButtonHref(hrefLink);
		return this;
	}

	public CappuccinoFormBuilder withInlineForm(boolean isInlineFOrm)
	{
		this.cappuccinoForm.setInlineForm(isInlineFOrm);
		return this;
	}

	public CappuccinoFormBuilder withSubmitButtonExtraCssClass(String spaceSeparatedCssClassNames)
	{
		this.cappuccinoForm.setSubmitButtonExtraCssClass(spaceSeparatedCssClassNames);
		return this;
	}

	public CappuccinoFormBuilder withLinkButtonExtraCssClass(String spaceSeparatedCssClassNames)
	{
		this.cappuccinoForm.setLinkButtonExtraCssClass(spaceSeparatedCssClassNames);
		return this;
	}

	public CappuccinoForm build()
	{
		this.cappuccinoForm.setOrderedFields(this.tempFieldNames.stream().toArray(String[]::new));
		return cappuccinoForm;
	}

}
