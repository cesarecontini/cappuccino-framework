package com.github.cesarecontini.cappuccino.framework.web;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ExtendWith(SpringExtension.class)
class CappuccinoPageUtilsTest
{


	@Test
	public void renderNonWebAppTemplate() throws Exception {

		List<CappuccinoFragmentTO> fragmentTOS = Lists.newArrayList(
				new CappuccinoFragmentTO(
						new CappuccinoFormBuilder<JunitForm>("my-form", CappuccinoForm.CappuccinoFormMethod.POST, "/hello")
								.addTextField("name", "First name", "Enter your first name", 100)
								.addTextField("surname", "Last name", "Please specify your last name or surname", 100)
								.addTextAreaField("comments", "Comments", null)
								.addCheckboxFieldInline("hobbies", "Hobbies", "Check one or more hobbies", Lists.newArrayList(
										new CappuccinoKeyValuePair("Sport", "sport"),
										new CappuccinoKeyValuePair("Reading", "reading"),
										new CappuccinoKeyValuePair("Gaming", "gaming")
								))
								.addRadioFieldInline("acceptCookies", "Accept cookies", null, Lists.newArrayList(
										new CappuccinoKeyValuePair("Yes", "yes"),
										new CappuccinoKeyValuePair("No", "no")
								))

								.build()
				)
		);

		CappuccinoPageTO<Object, JunitForm> cappuccinoPageTO = new CappuccinoPageTO<>("Title", "Subtitle", fragmentTOS);
		cappuccinoPageTO.setFormObject(new JunitForm());
		cappuccinoPageTO.setBreadcrumbs(
				Lists.newArrayList(new CappuccinoBreadcrumb("Home", "/"))
		);
		ModelAndView modelAndView = CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO);
		Assertions.assertNotNull(modelAndView);
		Assertions.assertEquals(2, modelAndView.getModel().size());
		Assertions.assertTrue(modelAndView.getModel().containsKey(CappuccinoPageTO.CAPPUCCINO_FORM_OBJECT_KEY));
		Assertions.assertTrue(modelAndView.getModel().containsKey("cappuccinoPageTO"));

		Assertions.assertTrue(modelAndView.getModel().get(CappuccinoPageTO.CAPPUCCINO_FORM_OBJECT_KEY) instanceof JunitForm);
		CappuccinoPageTO cappuccinoPageTO1 = (CappuccinoPageTO)modelAndView.getModel().get("cappuccinoPageTO");
		Assertions.assertEquals("Title", cappuccinoPageTO1.getPageTitle());
		Assertions.assertEquals("Subtitle", cappuccinoPageTO1.getPageSubTitle());
		Assertions.assertEquals(1, cappuccinoPageTO1.getFragments().size());

		CappuccinoFragmentTO fragmentTO = (CappuccinoFragmentTO)cappuccinoPageTO1.getFragments().get(0);
		Assertions.assertEquals("my-form", fragmentTO.getCappuccinoForm().getFormId());
		Assertions.assertEquals("/hello", fragmentTO.getCappuccinoForm().getFormAction());
		Assertions.assertEquals(5, fragmentTO.getCappuccinoForm().getFields().size());
		Assertions.assertEquals(CappuccinoFormFieldType.TEXT, fragmentTO.getCappuccinoForm().getFields().get(0).getType());
		Assertions.assertEquals(CappuccinoFormFieldType.TEXT, fragmentTO.getCappuccinoForm().getFields().get(1).getType());
		Assertions.assertEquals(CappuccinoFormFieldType.TEXTAREA, fragmentTO.getCappuccinoForm().getFields().get(2).getType());
		Assertions.assertEquals(CappuccinoFormFieldType.CHECKBOX, fragmentTO.getCappuccinoForm().getFields().get(3).getType());
		Assertions.assertEquals(CappuccinoFormFieldType.RADIO, fragmentTO.getCappuccinoForm().getFields().get(4).getType());


	}
}