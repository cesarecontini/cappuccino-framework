package framework;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CappuccinoAnnotatedDemoForm
{
	@NotEmpty(message = "First name cannot be empty")
	@NotNull
	@CappuccinoField(label = "FirstName label", cappuccinoFormFieldType = CappuccinoFormFieldType.TEXT, maxLength = 10)
	private String firstName;

	@NotEmpty(message = "Answer is required")
	@CappuccinoField(label = "Yes no label?",
			cappuccinoFormFieldType = CappuccinoFormFieldType.RADIO,
			optionIds = {"Yes","No"},
			optionValues = {"yes","no"},
			radioOrCheckboxInline = true)
	private String yesNo;

	@NotEmpty(message = "Answer is required")
	@CappuccinoField(label = "Multiple radio field?",
			cappuccinoFormFieldType = CappuccinoFormFieldType.RADIO,
			optionIds = {"Yes","No","Maybe"},
			optionValues = {"yes","no","maybe"})
	private String multipleRadio;

	@CappuccinoField(label = "Numeric", cappuccinoFormFieldType = CappuccinoFormFieldType.NUMBER, max = 10)
	private Integer numericField;

	@CappuccinoField(label = "Date of birth", hint = "For example, 31 3 1981", cappuccinoFormFieldType = CappuccinoFormFieldType.DATE)
	@NotNull(message = "Day is required")
	@Range(min = 1, max = 31, message = "Day must be between 1 and 31")
	private Integer dobDay;

	@NotNull(message = "Month is required")
	@Range(min = 1, max = 12, message = "Month must be between 1 and 12")
	private Integer dobMonth;

	@NotNull(message = "Year is required")
	private Integer dobYear;

	@NotEmpty(message = "Cannot be empty")
	@NotNull
	private String notAnnotated;
}
