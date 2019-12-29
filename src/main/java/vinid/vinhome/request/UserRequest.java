package vinid.vinhome.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userFullName;
    private String userPhone;
    private String userEmail;
    private String userHash;
    private String token;
}
