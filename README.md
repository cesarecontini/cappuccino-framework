# CAPPUCCINO FRAMEWORK

## A framework for Spring Boot & Thymeleaf

This is the documentation for the CAPPUCCINO PAGES framework.

The framework allows the following:

* Creation of CAPPUCCINO pages using thymeleaf fragments instead of duplicating an existing template file
* Creation of dynamic forms programmatically using the frameworks API

## Key source code package

The framework source code is located @ ./cappuccino-framework-web/src/main/java/com/github/cesarecontini/cappuccino/framework/web

## Usage example

Please refer to the  ./cappuccino-framework-web/src/test/java/com/github/cesarecontini/cappuccino/framework/web/FakeController.java
for an example usage for the framework. This controller class is used for testing purposes only.

### Non-form fragments

Instantiate the CappuccinoPageTO class. Feed the first generics argument with your own TO object 
to be passed to the view. The second generic is to be used if you want to pass a form object
to the template. If you have neither a TO object and/or a form use the 'Object' type
```$xslt
CappuccinoPageTO<MyTO, Object> cappuccinoPageTO = new CappuccinoPageTO();
// or with no TO class
CappuccinoPageTO<Object, Object> cappuccinoPageTO = new CappuccinoPageTO();
```
Then set page's title and subtitle
```$xslt
cappuccinoPageTO.setPageTitle("A title");
cappuccinoPageTO.setPageSubTitle("Some subtitle");
```

Add fragments with a list of CappuccinoFragmentTO objects. CappuccinoFragmentTO takes the first argument with
the path of the thymeleaf template file holding the fragments you want to add. The second argument is 
the name of the fragment itself.

```$xslt
cappuccinoPageTO.setFragments(Lists.newArrayList(
				new CappuccinoFragmentTO("cappuccino-demo-fragments", "fragmentA"),
				new CappuccinoFragmentTO("cappuccino-demo-fragments", "fragmentB")
		));

```
Instantiate and set your own TO object if you have one:

```$xslt
CappuccinoDemoTO to = new CappuccinoDemoTO();
to.setData("some data");

cappuccinoPageTO.setTo(to);
```

get the ModelAndView object using the framework's CappuccinoPageUtils.getModelAndViewForCappuccinoPage method:

```$xslt
return CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO)
```

Putting it all together:

```$xslt
@Controller
@RequestMapping("/{dispCode}/some-url")
public class CappuccinoDemoController extends CappuccinoPageBaseController
{

	@GetMapping("/page1")
	public ModelAndView getPageOne(
		@PathVariable("dispCode") String dispCode,
		@ModelAttribute("template") TemplateUtil templateUtil
	)
	{
		breadcrumbManager.forSomeBreadcrumbs(templateUtil, dispCode);
		templateUtil.setTitle("A title");

		CappuccinoPageTO<CappuccinoDemoTO, Object> cappuccinoPageTO = new CappuccinoPageTO();
		cappuccinoPageTO.setPageTitle("A title");
		cappuccinoPageTO.setPageSubTitle("Some subtitle");
		cappuccinoPageTO.setFragments(Lists.newArrayList(
            new CappuccinoFragmentTO("cappuccino-demo-fragments", "fragmentA"),
            new CappuccinoFragmentTO("cappuccino-demo-fragments", "fragmentB")
		));
		
		CappuccinoDemoTO to = new CappuccinoDemoTO();
		to.setData("some data");
		cappuccinoPageTO.setTo(to);

        // this adds JS files dynamically to the page
        cappuccinoPageTO.setJsFilesPaths(
            Lists.newArrayList("/js/somefile_A.js", "/js/somefile_B.js")
        );

		ModelAndView modelAndView = CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO);
		return modelAndView;
	}

...
```

In case you need to display content within an iframe the framework allows the possibility
to only display the main content and hide main navigation, breadcrumbs, footer and beta banner sections
i.e 

```
	public static ModelAndView getQpsDomain2P2ModelAndView()
	{
		CappuccinoPageTO<Object, Object> cappuccinoPageTO = new CappuccinoPageTO();
		cappuccinoPageTO.setPageTitle(null);
		cappuccinoPageTO.setDisplayHeader(false);
		cappuccinoPageTO.setDisplayAlphaBanner(false);
		cappuccinoPageTO.setDisplayBreadcrumbs(false);
		cappuccinoPageTO.setDisplayFooter(false);

		cappuccinoPageTO.setFragments(Lists.newArrayList(
				new CappuccinoFragmentTO("pqs-fragments", "domain2SnapSurveyCompleted")
		));

		return CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO);
	}

```

#### Retrieving TO variables from within fragments template files

You must prefix the TO variable name with the `cappuccinoPageTO.to` object in the fragmentB file i.e.

```
<th:block th:fragment="someFragment">

    <div class="grid-row">
        <div class="column-two-thirds">
            <p id="text-p1" th:text="${cappuccinoPageTO.to.textInfo1}"></p>
            <p id="text-p2" th:text="${cappuccinoPageTO.to.textInfo1}"></p>
        </div>
    </div>

</th:block>
```

### Dynamic form-based fragments

When you have to create a dynamic form then instantiate a CappuccinoFragmentTO type object with a 
single parameter taking an CappuccinoForm object. Use the CappuccinoFormBuilder to build the CappuccinoForm object.
The builder takes a generics type for the form object to be injected into the view context.

Constructor takes the following parameters:

1) String form ID - id to be assigned to the form in html
2) CappuccinoForm.CappuccinoFormMethod enum for http method
3) String form action url path

For example:

```$xslt
CappuccinoFragmentTO cappuccinoFragmentTO = new CappuccinoFragmentTO(
		
new CappuccinoFormBuilder<MyForm>("cappuccinoDemoForm", CappuccinoForm.CappuccinoFormMethod.POST, String.format("/%s/mp/page2", dispCode))
    .addTextField("firstName", "First Name", "some hint", 10)
    .addTextField("lastName", "Last Name", null)
    .addNumberField("propertiesOwned", "Number of properties owned", null, 0, 10)
    // also you can use the 
    // .addRadioFieldInline for inline radio buttons
    .addRadioField("gender", "Gender", null, Lists.newArrayList(
        new CappuccinoKeyValuePair("Male", "male"),
        new CappuccinoKeyValuePair("Female", "female")
    ))
    // also you can use the 
    // .addCheckboxFieldInline for inline checkboxes
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
    .build()
);
```

And putting it all together:

```$xslt
private ModelAndView getPage2ModelAndView(TemplateUtil templateUtil, String dispCode, CappuccinoDemoForm form)
{
    breadcrumbManager.forSomeBreadcrumbs(templateUtil, dispCode);
    templateUtil.setTitle("A title");

    CappuccinoPageTO<Object, CappuccinoDemoForm> cappuccinoPageTO = new CappuccinoPageTO();
    cappuccinoPageTO.setPageTitle("Form Page");
    cappuccinoPageTO.setPageSubTitle("This demonstrates a dynamic form");

    // create a CappuccinoFragmentTO object with a CappuccinoForm object
    CappuccinoFragmentTO cappuccinoFragmentTO = new CappuccinoFragmentTO(
        new CappuccinoFormBuilder<CappuccinoDemoForm>("cappuccinoDemoForm", CappuccinoForm.CappuccinoFormMethod.POST, String.format("/%s/mp/page2", dispCode))
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
            .build()
    );
    cappuccinoPageTO.setFragments(Lists.newArrayList(
        cappuccinoFragmentTO
    ));

     cappuccinoPageTO.setFormObject(form);

    return CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO);
}

@GetMapping("/page2")
public ModelAndView getPageTwoWithForm(
        @PathVariable("dispCode") String dispCode,
        @ModelAttribute("template") TemplateUtil templateUtil
)
{
    return  getPage2ModelAndView(templateUtil, dispCode, new CappuccinoDemoForm());
}
```

You can also dynamically set a specific radio button or checkbox to reveal a previously hidden field by setting the 
`withDataTargetField` command i.e.

```$xslt
...

.withDataTargetField("gender", "male", "comments")

...
```

in the above example the `comments` field will be revealed when the gender's male radio button is checked (or hidden when
radio button is unchecked)

A link-based button will also be displayed along the main submit button if the `withLinkButtonText` command is set:

```$xslt
...

.withLinkButtonText("Save and continue later", "/save-continue-later")

...
```

Any field hint text cab also be provided as html i.e.

```$xslt
.addTextField("firstName", "First Name", "<ul><li>This is a list</li><ul>", 10)
```

Check out the  .\cappuccino\cappuccino-framework-web\src\test\java\webapp\FakeController.java
getPageWithFormsHiddenElement method where these commands are used.


### Annotation based form

If form fields are annotated with ``@CappuccinoField`` annotation, form can be built with ``withDefaultFields`` method of CappuccinoFormBuilder.
In this case it is not necessary to explicitly define addField methods for each field.

```$xslt
		CappuccinoFragmentTO cappuccinoFragmentTO = new CappuccinoFragmentTO(

				// use the CappuccinoFormBuilder to build a proper CappuccinoForm object
				// use method withDefaultFields to build field for every annotated property of the form
				new CappuccinoFormBuilder<CappuccinoAnnotatedDemoForm>("cappuccinoAnnotatedDemoForm", CappuccinoForm.CappuccinoFormMethod.POST, String.format("/%s/mp/page3", dispCode))
						.withDefaultFields(CappuccinoAnnotatedDemoForm.class)
						.withSubmitButtonText("Next")
						.build()
		);

```


### CappuccinoFormFields DisplayMode

When using the CAPPUCCINO form builder you will be able for some fields i.e, text, textarea, radio, checkbox, number, select to also set their specific display mode. If this property is not set as in the above examples then fields will be displayed in a stacked mode. 

The CappuccinoFormField class contains an inner DisplayMode enum i.e.
    	
```$xslt
	public enum DisplayMode {
		FULL_COLUMN,
		HALF_COLUMN,
		ONE_THIRD_COLUMN
	}
``` 

### Form object

The form object must contain all the properties as defined in the form builder. Note the 
DATE field type will add 3 extra fields: 

1. **nameOfDateField**Day
2. **nameOfDateField**Month
3. **nameOfDateField**Year

So the 'dob' date field would expect **dobDay**, **dobMonth** and **dobYear** fields.

For example:

```$xslt
@Data
public class CappuccinoDemoForm
{
	@NotEmpty(message = "First name cannot be empty")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	private String lastName;

	private Integer propertiesOwned;

	@NotEmpty(message = "Gender is required field")
	private String gender;

	private List<String> hobbies;

	@NotNull(message = "Day is required")
	@Range(min = 1, max = 31, message = "Day must be between 1 and 31")
	private Integer dobDay;

	@NotNull(message = "Month is required")
	@Range(min = 1, max = 12, message = "Month must be between 1 and 12")
	private Integer dobMonth;

	@NotNull(message = "Year is required")
	private Integer dobYear;

	private String type;

	private String comments;
}
``` 

Form object can contain annotations about CAPPUCCINO form fields, e.g.: 

```$xslt
	@CappuccinoField(label = "FirstName label", cappuccinoFormFieldType = CappuccinoFormFieldType.TEXT, maxLength = 10)
	private String firstName;

	@CappuccinoField(label = "Yes no label?",
			cappuccinoFormFieldType = CappuccinoFormFieldType.RADIO,
			optionIds = {"Yes","No"},
			optionValues = {"yes","no"},
			radioOrCheckboxInline = true)
	private String yesNo;

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
```

Finally, the form CappuccinoDemoForm will be retrievable from the 'formObject' ModelAndView key i.e.

```$xslt
	@PostMapping(value = "/page2")
	public ModelAndView PostPageTwoWithForm(
			@PathVariable("dispCode") String dispCode,
			@ModelAttribute("template") TemplateUtil templateUtil,
			@Valid @ModelAttribute("formObject") CappuccinoDemoForm form,
			BindingResult bindingResult
	)
	{
		// CappuccinoDemoForm is exposed with key 'formObject' and validated
		if(bindingResult.hasErrors())
		{
			return getPage2ModelAndView(templateUtil, dispCode, form);
		}

		// do something with your form by using a service

		return new ModelAndView(String.format("redirect:/{dispCode}/redirect-somewhere", dispCode));
	}
```

## SUPPORT, MAINTAIN & CONTRIBUTIONS TO THE FRAMEWORK

### Dynamic form fields

The following fields are available:

* TEXT
* NUMBER
* TEXTAREA
* CHECKBOX
* RADIO
* DATE
* SELECT
* HIDDEN

### Key frameworks templates:

Top hierarchy template dealing with page, resolving fragments:

* ./cappuccino-framework-web/src/main/resources/templates/cappuccino-form-fragments.html

Template dealing with resolving form fields dynamically:

* ./cappuccino-framework-web/src/main/resources/templates/cappuccino-standard-page.html


