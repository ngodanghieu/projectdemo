package vinid.vinhome.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserRequest {
    private Long id;
    private String userFullName;
    @Length(min = 8 , max = 12)
    private String userPhone;
    @Email(message = "EMAIL INVALID")
    private String userEmail;
    private String userHash;
    private String token;
}
