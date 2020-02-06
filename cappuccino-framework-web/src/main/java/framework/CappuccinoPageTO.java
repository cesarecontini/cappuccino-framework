package framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CappuccinoPageTO<T,S>
{

	public static final String CAPPUCCINO_FORM_OBJECT_KEY = "formObject";
	public static final String CAPPUCCINO_PAGES_COMMON_FRAGMENTS = "cappuccino-page-common-fragments";

	public CappuccinoPageTO(String pageTitle, String pageSubTitle, List<CappuccinoFragmentTO> fragments)
	{
		this.pageTitle = pageTitle;
		this.pageSubTitle = pageSubTitle;
		this.fragments = fragments;
	}

	private String pageTitle;
	private String pageSubTitle;
	private List<CappuccinoFragmentTO> fragments;
	private T to;
	private S formObject;
	private boolean displayAlphaBanner = true;
	private boolean displayHeader = true;
	private boolean displayBreadcrumbs = true;
	private boolean displayFooter = true;
	private CappuccinoCssClasses cssClasses = new CappuccinoCssClasses();
	private List<CappuccinoBreadcrumb> breadcrumbs = new ArrayList<>();

}
