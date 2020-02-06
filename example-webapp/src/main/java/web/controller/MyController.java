package web.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoBreadcrumb;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoForm;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoFormBuilder;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoFragmentTO;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoKeyValuePair;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoPageTO;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoPageUtils;
import framework.form.MyForm;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MyController
{

	private ModelAndView getModelAndView(MyForm form)
	{
		List<CappuccinoFragmentTO> fragmentTOS = Lists.newArrayList(
				new CappuccinoFragmentTO(
						new CappuccinoFormBuilder<MyForm>("my-form", CappuccinoForm.CappuccinoFormMethod.POST, "/hello")
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
		CappuccinoPageTO<Object, MyForm> cappuccinoPageTO = new CappuccinoPageTO<>("Title", "Subtitle", fragmentTOS);
		cappuccinoPageTO.setFormObject(form);
		cappuccinoPageTO.setBreadcrumbs(
				Lists.newArrayList(new CappuccinoBreadcrumb("Home", "/"))
		);
		return CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO);

	}

	@GetMapping("/hello")
	public ModelAndView helloEndpoint()
	{
		return getModelAndView(new MyForm());
	}

	@PostMapping("/hello")
	public ModelAndView postHello(
			@Valid @ModelAttribute(CappuccinoPageTO.CAPPUCCINO_FORM_OBJECT_KEY) MyForm form,
			BindingResult bindingResult
	)
	{
		if(bindingResult.hasErrors())
		{
			return getModelAndView(form);
		}
		return new ModelAndView("redirect:/cool");
	}

}
