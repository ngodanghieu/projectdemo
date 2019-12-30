package vinid.vinhome.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vinid.vinhome.entities.City;
import vinid.vinhome.entities.Status;
@Getter
@Setter
@AllArgsConstructor
public class DistrictResponse {
    private CityResponse city;
    private String code;
    private String name;
}
