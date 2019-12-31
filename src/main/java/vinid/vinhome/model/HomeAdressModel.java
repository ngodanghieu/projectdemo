package vinid.vinhome.model;

import lombok.Getter;
import lombok.Setter;
import vinid.vinhome.entities.Building;
import vinid.vinhome.entities.Home;
import vinid.vinhome.entities.Status;

@Setter
@Getter
public class HomeAdressModel {
    private Long adressHomeId;
    private Building building;
    private Status status;
    private int floor;
    private String nameHome;
}
