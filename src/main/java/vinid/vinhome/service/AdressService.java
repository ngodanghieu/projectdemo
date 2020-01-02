package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.Adress;
import vinid.vinhome.entities.City;
import vinid.vinhome.entities.District;
import vinid.vinhome.repository.IAdress;
import vinid.vinhome.response.AdressResponse;
import vinid.vinhome.response.CityResponse;
import vinid.vinhome.response.DistrictResponse;

import java.util.LinkedList;
import java.util.List;

@Service
public class AdressService {

    @Autowired
    private IAdress iAdress;
    public List<AdressResponse> getAllByDistrictCode(String code){
        List<Adress> districtList = iAdress.getAdressByCodeAndDistrict(code);
        List<AdressResponse> result = new LinkedList<>();
        if (districtList != null && !districtList.isEmpty()){
            districtList.forEach( x -> {
                result.add(mappingEntitiToModel(x));
            });
            return result;
        }else {
            return null;
        }

    }

    private AdressResponse mappingEntitiToModel(Adress adress){
        return new AdressResponse(adress.getAdressId(),mappingEntitiToResponse(adress.getDistrict()),adress.getHomeId(),adress.getLatitude(),adress.getLongtitude(),adress.getContentDetail());
    }

    private DistrictResponse mappingEntitiToResponse(District district){
        return new DistrictResponse(mapEntitiToResponseCity(district.getCity()),district.getCode(),district.getName());
    }
    private CityResponse mapEntitiToResponseCity(City city) {
        return new CityResponse(city.getCode(), city.getName());
    }
}
