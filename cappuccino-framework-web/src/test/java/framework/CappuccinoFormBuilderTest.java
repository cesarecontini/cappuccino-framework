package framework;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
public class CappuccinoFormBuilderTest
{

    CappuccinoForm form;
    CappuccinoFormBuilder<Object> builder = new CappuccinoFormBuilder("id", CappuccinoForm.CappuccinoFormMethod.POST, "/action");

    @BeforeAll
    static void setUp() throws Exception
    {
        // nothing in here
    }

    @Test
    public void addTextField()
    {
        builder.addTextField("textField1", "Some label text", "hint text");

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textField1", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textField1", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXT, form.getFields().get(0).getType());
    }

    @Test
    public void addTextFieldWithDisplayMode()
    {
        builder.addTextField("textField1", "Some label text", "hint text", CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textField1", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textField1", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXT, form.getFields().get(0).getType());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }


    @Test
    public void addTextFieldWithMaxLength()
    {
        builder.addTextField("textField1", "Some label text", "hint text", 100);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textField1", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textField1", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXT, form.getFields().get(0).getType());
        Assertions.assertEquals(new Integer(100), form.getFields().get(0).getMaxLength());

    }

    @Test
    public void addTextFieldWithMaxLengthAndDisplayMode()
    {
        builder.addTextField("textField1", "Some label text", "hint text", 100, CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textField1", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textField1", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXT, form.getFields().get(0).getType());
        Assertions.assertEquals(new Integer(100), form.getFields().get(0).getMaxLength());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void addTextAreaField()
    {
        builder.addTextAreaField("textAreaField", "textAriaFieldLabel", "hint text");

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textAreaField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textAreaField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("textAriaFieldLabel", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXTAREA, form.getFields().get(0).getType());
    }

    @Test
    public void addTextAreaFieldWidthDisplayMode()
    {
        builder.addTextAreaField("textAreaField", "textAriaFieldLabel", "hint text", CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textAreaField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textAreaField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("textAriaFieldLabel", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXTAREA, form.getFields().get(0).getType());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void testAddTextField()
    {
        builder.addTextField("textField2", "Some label text", "hint text");

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("textField2", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("textField2", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.TEXT, form.getFields().get(0).getType());
    }

    @Test
    public void addNumberField()
    {
        builder.addNumberField("numberField", "Some label text", "hint text");

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("numberField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("numberField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.NUMBER, form.getFields().get(0).getType());
    }

    @Test
    public void addNumberFieldWidthDisplayMode()
    {
        builder.addNumberField("numberField", "Some label text", "hint text", CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("numberField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("numberField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.NUMBER, form.getFields().get(0).getType());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void addNumberFieldWithMinMax()
    {
        builder.addNumberField("numberField", "Some label text", "hint text", 0, 10);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("numberField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("numberField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.NUMBER, form.getFields().get(0).getType());
        Assertions.assertEquals(new Integer(0), form.getFields().get(0).getMin());
        Assertions.assertEquals(new Integer(10), form.getFields().get(0).getMax());
    }

    @Test
    public void addNumberFieldWithMinMaxAndDisplayMode()
    {
        builder.addNumberField("numberField", "Some label text", "hint text", 0, 10, CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("numberField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("numberField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.NUMBER, form.getFields().get(0).getType());
        Assertions.assertEquals(new Integer(0), form.getFields().get(0).getMin());
        Assertions.assertEquals(new Integer(10), form.getFields().get(0).getMax());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void addRadioField()
    {
        builder.addRadioField("radioField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ));

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("radioField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("radioField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.RADIO, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
    }

    @Test
    public void addRadioFieldWithDisplayMode()
    {
        builder.addRadioField("radioField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ), CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("radioField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("radioField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.RADIO, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void addRadioFieldInline()
    {
        builder.addRadioFieldInline("radioField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ));

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("radioField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("radioField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.RADIO, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
        Assertions.assertTrue(form.getFields().get(0).isRadioOrCheckboxInline());
    }

    @Test
    public void addCheckboxField()
    {
        builder.addCheckboxField("checkboxField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ));

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("checkboxField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("checkboxField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.CHECKBOX, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
        Assertions.assertFalse(form.getFields().get(0).isRadioOrCheckboxInline());
    }

    @Test
    public void addCheckboxFieldWithDisplayMode()
    {
        builder.addCheckboxField("checkboxField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ), CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("checkboxField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("checkboxField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.CHECKBOX, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
        Assertions.assertFalse(form.getFields().get(0).isRadioOrCheckboxInline());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void addCheckboxFieldInline()
    {
        builder.addCheckboxFieldInline("radioField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ));

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("radioField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("radioField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.CHECKBOX, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
        Assertions.assertTrue(form.getFields().get(0).isRadioOrCheckboxInline());
    }

    @Test
    public void addSelectField()
    {
        builder.addSelectField("selectField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ));

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("selectField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("selectField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.SELECT, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
    }

    @Test
    public void addSelectFieldWithDisplayMode()
    {
        builder.addSelectField("selectField", "Some label text", "hint text", Lists.newArrayList(
                new CappuccinoKeyValuePair("Male", "male"),
                new CappuccinoKeyValuePair("Female", "female")
        ), CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN);

        form = builder.build();
        Assertions.assertEquals(1, form.getOrderedFields().length);
        Assertions.assertEquals("selectField", form.getOrderedFields()[0]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("selectField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.SELECT, form.getFields().get(0).getType());
        Assertions.assertEquals(2, form.getFields().get(0).getOptions().size());
        Assertions.assertEquals("Male", form.getFields().get(0).getOptions().get(0).getKey());
        Assertions.assertEquals("male", form.getFields().get(0).getOptions().get(0).getValue());
        Assertions.assertEquals("Female", form.getFields().get(0).getOptions().get(1).getKey());
        Assertions.assertEquals("female", form.getFields().get(0).getOptions().get(1).getValue());
        Assertions.assertEquals(CappuccinoFormField.DisplayMode.ONE_THIRD_COLUMN, form.getFields().get(0).getDisplayMode());
    }

    @Test
    public void addDateField()
    {
        builder.addDateField("dateField", "Some label text", "hint text");

        form = builder.build();
        Assertions.assertEquals(3, form.getOrderedFields().length);
        Assertions.assertEquals("dateFieldDay", form.getOrderedFields()[0]);
        Assertions.assertEquals("dateFieldMonth", form.getOrderedFields()[1]);
        Assertions.assertEquals("dateFieldYear", form.getOrderedFields()[2]);
        Assertions.assertEquals(1, form.getFields().size());
        Assertions.assertEquals("dateField", form.getFields().get(0).getFieldName());
        Assertions.assertEquals("Some label text", form.getFields().get(0).getLabel());
        Assertions.assertEquals("hint text", form.getFields().get(0).getHintText());
        Assertions.assertEquals(CappuccinoFormFieldType.DATE, form.getFields().get(0).getType());
    }

    @Test
    public void withSubmitButtonText()
    {
        builder.withSubmitButtonText("Next");

        form = builder.build();
        Assertions.assertEquals("Next", form.getSubmitButtonText());
    }

    @Test
    public void withIsInlineForm()
    {
        builder.withInlineForm(true);

        form = builder.build();
        Assertions.assertTrue(form.isInlineForm());
    }

    @Test
    public void allFieldsAreCreated()
    {
        form = builder
                .addTextField("firstName", "First Name", "some hint", 10)
                .addTextField("lastName", "Last Name", null)
                .addNumberField("propertiesOwned", "Number of properties owned", null, 0, 10)
                .addRadioField("gender", "Gender", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Male", "male"),
                        new CappuccinoKeyValuePair("Female", "female")
                ))
                .addCheckboxField("hobbies", "Hobbies", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Sport", "sport"),
                        new CappuccinoKeyValuePair("Music", "music"),
                        new CappuccinoKeyValuePair("Drinking", "drinking")
                ))
                .addDateField("dob", "Date of birth", "For example, 31 3 1981")
                .addSelectField("type", "Type", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Type A", "typeA"),
                        new CappuccinoKeyValuePair("Type B", "typeB"),
                        new CappuccinoKeyValuePair("Type C", "typeC")
                ))
                .addTextAreaField("Comments", "comments", "Some comments hints")
                .withSubmitButtonText("Next")
                .build();
        Assertions.assertEquals(8, form.getFields().size());
        Assertions.assertEquals(10, form.getOrderedFields().length);
    }

    @Test
    public void allAnnotatedFieldsAreCreated()
    {
        CappuccinoFormBuilder<CappuccinoAnnotatedDemoForm> builder = new CappuccinoFormBuilder("id", CappuccinoForm.CappuccinoFormMethod.POST, "/action");
        form = builder
                .withDefaultFields(CappuccinoAnnotatedDemoForm.class)
                .withSubmitButtonText("Next")
                .build();
        Assertions.assertEquals(5, form.getFields().size());
        Assertions.assertEquals(7, form.getOrderedFields().length);
        Assertions.assertTrue(Arrays.stream(form.getOrderedFields()).noneMatch(f -> f.equalsIgnoreCase("notOrdered")));
    }

    @Test
    public void defaultAnnotationArgumentEvaluated()
    {
        CappuccinoFormBuilder<CappuccinoAnnotatedDemoForm> builder = new CappuccinoFormBuilder("id", CappuccinoForm.CappuccinoFormMethod.POST, "/action");
        form = builder
                .withDefaultFields(CappuccinoAnnotatedDemoForm.class)
                .withSubmitButtonText("Next")
                .build();

        Assertions.assertEquals(CappuccinoFormField.DisplayMode.FULL_COLUMN, form.getFields().stream().filter(f -> "firstName".equalsIgnoreCase(f.getFieldName())).findFirst().orElse(null).getDisplayMode());
    }

    @Test
    public void referencesForHiddenFieldsAreCreated()
    {
        form = builder
                .addTextField("firstName", "First Name", "some hint", 10)
                .addTextField("lastName", "Last Name", null)
                .addNumberField("propertiesOwned", "Number of properties owned", null, 0, 10)
                .addRadioField("gender", "Gender", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Male", "male"),
                        new CappuccinoKeyValuePair("Female", "female")
                ))
                .addCheckboxField("hobbies", "Hobbies", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Sport", "sport"),
                        new CappuccinoKeyValuePair("Music", "music"),
                        new CappuccinoKeyValuePair("Drinking", "drinking")
                ))
                .addDateField("dob", "Date of birth", "For example, 31 3 1981")
                .addSelectField("type", "Type", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Type A", "typeA"),
                        new CappuccinoKeyValuePair("Type B", "typeB"),
                        new CappuccinoKeyValuePair("Type C", "typeC")
                ))
                .addTextAreaField("comments", "comments", "Some comments hints")
                .withDataTargetField("gender", "male", "comments")
                .withSubmitButtonText("Next")
                .build();

        Assertions.assertTrue(form.getFields().stream()
                .filter(f -> f.getFieldName().equals("gender"))
                .findFirst()
                .get()
                .getOptions()
                .stream()
                .anyMatch(o -> o.getDataTarget().equals("male-comments")));

        Assertions.assertTrue(form.getFields().stream()
                .filter(f -> f.getFieldName().equals("comments"))
                .findFirst()
                .get()
                .isDataTargetField()
                );

        Assertions.assertEquals("male-comments", form.getFields().stream()
                .filter(f -> f.getFieldName().equals("comments"))
                .findFirst()
                .get()
                .getTargetedById()
        );
    }

    @Test
    public void assertFormLinkButtonTextAndHrefValuesAreSet()
    {
        form = builder
                .addTextField("firstName", "First Name", "some hint", 10)
                .addTextField("lastName", "Last Name", null)
                .addNumberField("propertiesOwned", "Number of properties owned", null, 0, 10)
                .addRadioField("gender", "Gender", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Male", "male"),
                        new CappuccinoKeyValuePair("Female", "female")
                ))
                .addCheckboxField("hobbies", "Hobbies", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Sport", "sport"),
                        new CappuccinoKeyValuePair("Music", "music"),
                        new CappuccinoKeyValuePair("Drinking", "drinking")
                ))
                .addDateField("dob", "Date of birth", "For example, 31 3 1981")
                .addSelectField("type", "Type", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Type A", "typeA"),
                        new CappuccinoKeyValuePair("Type B", "typeB"),
                        new CappuccinoKeyValuePair("Type C", "typeC")
                ))
                .addTextAreaField("comments", "comments", "Some comments hints")
                .withSubmitButtonText("Next")
                .withLinkButtonText("Continue later", "/continue-later")
                .build();


        Assertions.assertEquals("Continue later", form.getLinkButtonText());
        Assertions.assertEquals("/continue-later", form.getLinkButtonHref());

    }

    @Test
    public void assertFieldIntroHeaderAndHintTextAreSet()
    {
        form = builder
                .addTextField("firstName", "First Name", "some hint", 10)
                .addTextField("lastName", "Last Name", null)
                .addNumberField("propertiesOwned", "Number of properties owned", null, 0, 10)
                .addRadioField("gender", "Gender", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Male", "male"),
                        new CappuccinoKeyValuePair("Female", "female")
                ))
                .addCheckboxField("hobbies", "Hobbies", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Sport", "sport"),
                        new CappuccinoKeyValuePair("Music", "music"),
                        new CappuccinoKeyValuePair("Drinking", "drinking")
                ))
                .addDateField("dob", "Date of birth", "For example, 31 3 1981")
                .addSelectField("type", "Type", null, Lists.newArrayList(
                        new CappuccinoKeyValuePair("Type A", "typeA"),
                        new CappuccinoKeyValuePair("Type B", "typeB"),
                        new CappuccinoKeyValuePair("Type C", "typeC")
                ))
                .addTextAreaField("comments", "comments", "Some comments hints")
                .withFieldIntroHeaderAndHintText("dob", "Some intro big text", "Some text")
                .build();


        Assertions.assertEquals("Some intro big text", form.getFields().stream()
            .filter(f -> f.getFieldName().equals("dob"))
                .findFirst()
                .get()
                .getIntroHeader()
        );

        Assertions.assertEquals("Some text", form.getFields().stream()
                .filter(f -> f.getFieldName().equals("dob"))
                .findFirst()
                .get()
                .getIntroHtmlHintText()
        );

    }
}