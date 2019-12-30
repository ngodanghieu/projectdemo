package vinid.vinhome.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.City;
import vinid.vinhome.entities.District;
import vinid.vinhome.repository.IDistrictRepository;
import vinid.vinhome.response.CityResponse;
import vinid.vinhome.response.DistrictResponse;

import java.util.LinkedList;
import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private IDistrictRepository iDistrictRepository;

    public List<DistrictResponse> getAll(){
        List<District> districtList = iDistrictRepository.getAllDistrict();
        List<DistrictResponse> result = new LinkedList<>();
        if (districtList != null && !districtList.isEmpty()){
            districtList.forEach( x -> {
                result.add(mappingEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }

    }
    private DistrictResponse mappingEntitiToResponse(District district){
        return new DistrictResponse(mapEntitiToResponseCity(district.getCity()),district.getCode(),district.getName());
    }
    private CityResponse mapEntitiToResponseCity(City city){
        return new CityResponse(city.getCode(),city.getName());
    }
}
