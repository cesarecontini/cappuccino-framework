package framework;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/fake")
public class FakeController
{
	private ModelAndView page1ModelAndView(JunitForm form)
	{
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
		cappuccinoPageTO.setFormObject(form);
		cappuccinoPageTO.setBreadcrumbs(
				Lists.newArrayList(new CappuccinoBreadcrumb("Home", "/"))
		);
		return CappuccinoPageUtils.getModelAndViewForCappuccinoPage(cappuccinoPageTO);
	}

	@GetMapping("/page1")
	public ModelAndView page1()
	{
		return page1ModelAndView(new JunitForm());
	}

	@PostMapping("/page1")
	public ModelAndView postPage1(
			@Valid @ModelAttribute(CappuccinoPageTO.CAPPUCCINO_FORM_OBJECT_KEY) JunitForm form,
			BindingResult bindingResult
	)
	{
		if(bindingResult.hasErrors())
		{
			return page1ModelAndView(form);
		}

		return new ModelAndView("redirect:/fake/page2");
	}


}
