package vinid.vinhome.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
public class HomeResponse {
    private Long homeId;
    private String title;
    private String content;
    private String imageUrl;
    private double price;
    private Date createdOn;
    private String createdBy;
}
