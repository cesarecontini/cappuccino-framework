package framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CappuccinoForm
{
	public enum CappuccinoFormMethod
	{
		POST,
		PUT,
		DELETE
	}
	private String formId;
	private String formAction;
	private List<CappuccinoFormField> fields = new ArrayList<>();
	private CappuccinoFormMethod cappuccinoFormMethod;
	private String[] orderedFields;
	private String submitButtonText = "Submit";
	private String submitButtonExtraCssClass;
	private String linkButtonText;
	private String linkButtonHref;
	private String linkButtonExtraCssClass;
	private boolean inlineForm;
}
