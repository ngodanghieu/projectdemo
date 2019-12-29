package vinid.vinhome.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private String userFullName;
    private String userPhone;
    private String userEmail;
    private String nameRole;
}
