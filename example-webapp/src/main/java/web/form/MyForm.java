package framework.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyForm
{
	@NotEmpty(message = "Please enter your first name")
	private String name;

	@NotEmpty(message = "Please enter your surname")
	private String surname;

	private List<String> hobbies;

	@NotEmpty(message = "Please accept cookies")
	private String acceptCookies;

	@NotEmpty(message = "Please enter your comments")
	private String comments;
}
