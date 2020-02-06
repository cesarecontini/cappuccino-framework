package framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CappuccinoField
{
	String label() default "";
	String hint() default "";
	CappuccinoFormFieldType cappuccinoFormFieldType() default CappuccinoFormFieldType.TEXT;
	String[] optionIds() default {};
	String[] optionValues() default {};
	String[] dataTargets() default {};
	int maxLength() default 255;
	int min() default 0;
	int max() default 255;
	boolean radioOrCheckboxInline() default false;
	CappuccinoFormField.DisplayMode displayMode() default CappuccinoFormField.DisplayMode.FULL_COLUMN;
	boolean isDataTargetSource() default false;
	String targetedByFieldName() default "";
}
