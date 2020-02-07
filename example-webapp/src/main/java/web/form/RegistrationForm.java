package web.form;

import com.github.cesarecontini.cappuccino.framework.web.CappuccinoField;
import com.github.cesarecontini.cappuccino.framework.web.CappuccinoFormFieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    @CappuccinoField(label = "First name", cappuccinoFormFieldType = CappuccinoFormFieldType.TEXT, hint = "Your first name, max 50 characters", maxLength = 50)
    @NotEmpty(message = "First name is a required field")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @CappuccinoField(label = "Last name", cappuccinoFormFieldType = CappuccinoFormFieldType.TEXT, hint = "Your last name, max 50 characters", maxLength = 50)
    @NotEmpty(message = "Last name is a required field")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @CappuccinoField(label = "Email", cappuccinoFormFieldType = CappuccinoFormFieldType.TEXT, hint = "A valid email address", maxLength = 254)
    @NotEmpty(message = "Email is a required field")
    @Email(message = "Please enter a valid email address")
    private String email;

}
