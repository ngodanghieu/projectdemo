package vinid.vinhome.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vinid.vinhome.response.CityResponse;
import vinid.vinhome.response.ResponseData;
import vinid.vinhome.service.CityService;
import vinid.vinhome.util.Constant;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @ApiOperation(value = "Get All City",response = CityResponse.class,responseContainer = "List")
    @ApiResponses({
           @ApiResponse(code = 500 , message = "Loi server")
    })
    @RequestMapping(value = "/api/get-all-city", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCity(){
        ResponseData responseData = new ResponseData();
        try {
            List<CityResponse> cityResponseList =  cityService.getAll();
            if (cityResponseList != null && !cityResponseList.isEmpty()){
                responseData.setContent(cityResponseList);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return  new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return  new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.toString());
        }
        return  new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }
}
