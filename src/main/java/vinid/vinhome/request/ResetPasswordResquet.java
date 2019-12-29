package vinid.vinhome.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordResquet {
    private String Phone;
    private String oldPassword;
    private String newPassword;
}
