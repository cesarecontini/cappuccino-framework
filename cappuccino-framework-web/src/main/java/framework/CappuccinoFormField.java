package framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CappuccinoFormField
{
	public enum DisplayMode
	{
		FULL_COLUMN,
		HALF_COLUMN,
		ONE_THIRD_COLUMN
	}

	private String fieldName;
	private String label;
	private String hintText;
	private CappuccinoFormFieldType type;
	private List<CappuccinoKeyValuePair> options = new ArrayList();
	private boolean radioOrCheckboxInline;
	private String dayDateFieldName;
	private String monthDateFieldName;
	private String yearDateFieldName;
	private Integer maxLength;
	private Integer min;
	private Integer max;
	private DisplayMode displayMode = DisplayMode.FULL_COLUMN;
	private boolean dataTargetField;
	private String targetedById;
	private String introHeader;
	private String introHtmlHintText;

	public void setLabel(String label)
	{
		this.label = CappuccinoHtmlUtils.sanitizeHtml(label);
	}

	public void setHintText(String hintText)
	{
		this.hintText = CappuccinoHtmlUtils.sanitizeHtml(hintText);
	}

	public void setIntroHtmlHintText(String introHtmlHintText)
	{
		this.introHtmlHintText = CappuccinoHtmlUtils.sanitizeHtml(introHtmlHintText);
	}
}
