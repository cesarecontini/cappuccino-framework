package framework;
import org.springframework.web.servlet.ModelAndView;

public class CappuccinoPageUtils
{
	private static final String MULTI_PURPOSE_CAPPUCCINO_PAGE_TEMPLATE_NAME = "cappuccino-standard-page";
	private static final String CAPPUCCINO_PAGE_TO_KEY = "cappuccinoPageTO";
	private static final String CAPPUCCINO_FORM_OBJECT_KEY = CappuccinoPageTO.CAPPUCCINO_FORM_OBJECT_KEY;


	private CappuccinoPageUtils()
	{
	}

	public static <T,S> ModelAndView getModelAndViewForCappuccinoPage(
			CappuccinoPageTO<T,S> cappuccinoPageTO
	)
	{
		ModelAndView modelAndView = new ModelAndView(MULTI_PURPOSE_CAPPUCCINO_PAGE_TEMPLATE_NAME);
		modelAndView.addObject(CAPPUCCINO_PAGE_TO_KEY, cappuccinoPageTO);
		if(cappuccinoPageTO.getFormObject() != null) modelAndView.addObject(CAPPUCCINO_FORM_OBJECT_KEY, cappuccinoPageTO.getFormObject());

		return modelAndView;
	}

}
