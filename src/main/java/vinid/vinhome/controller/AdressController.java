package vinid.vinhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vinid.vinhome.response.AdressResponse;
import vinid.vinhome.response.DistrictResponse;
import vinid.vinhome.response.ResponseData;
import vinid.vinhome.service.AdressService;
import vinid.vinhome.util.Constant;

import java.util.List;

@RestController
@RequestMapping(value = "/api/adress/")
public class AdressController {
    @Autowired
    private AdressService adressService;

    @RequestMapping(value = "get-all-adress-by-district/{code}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDistrictByCityCode(@RequestParam("code") String code){
        ResponseData responseData = new ResponseData();
        try {
            List<AdressResponse> adressResponses = adressService.getAllByDistrictCode(code);
            if (adressResponses != null && !adressResponses.isEmpty()){
                responseData.setContent(adressResponses);
                responseData.setStatus(1);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
