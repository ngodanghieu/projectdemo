package vinid.vinhome.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vinid.vinhome.model.HomeWorkTimeModel;

import java.util.List;
@Getter
@AllArgsConstructor
public class HomeRequest {
    private String content;
    private String imageUrl;
    private double price;
    List<HomeWorkTimeModel> homeWorkTimeModels;
}
