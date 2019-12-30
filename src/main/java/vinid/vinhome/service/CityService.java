package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.City;
import vinid.vinhome.repository.ICityRepsitory;
import vinid.vinhome.response.CityResponse;

import java.util.LinkedList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private ICityRepsitory iCityRepsitory;

    public List<CityResponse> getAll(){
        List<City> cityList = iCityRepsitory.getAll();
        List<CityResponse> result =  new LinkedList<>();
        if (cityList != null && !cityList.isEmpty()){
            cityList.forEach( x ->{
                result.add(mapEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    private CityResponse mapEntitiToResponse(City city){
        return new CityResponse(city.getCode(),city.getName());
    }
}
