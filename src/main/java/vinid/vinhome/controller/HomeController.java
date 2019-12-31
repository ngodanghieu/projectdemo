package vinid.vinhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinid.vinhome.repository.IHomeRepository;
import vinid.vinhome.repository.IHomeWorkTimeRepository;
import vinid.vinhome.request.SearchRequset;
import vinid.vinhome.response.HomeResponse;
import vinid.vinhome.response.ResponseData;
import vinid.vinhome.service.HomeService;
import vinid.vinhome.service.HomeWorkTimeService;
import vinid.vinhome.util.Constant;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home/")
public class HomeController {
    @Autowired
    private HomeWorkTimeService homeWorkTimeService;

    @Autowired
    private HomeService homeService;

    @GetMapping(value = "get-all-home")
    public ResponseEntity<?> getAllHome(){
        ResponseData responseData = new ResponseData();
        try {
            List<HomeResponse> result =  homeService.getAllHome();
            if (result != null){
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(result);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }else
            {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.getMessage().toString());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-search")
    public ResponseEntity<?> getDataSearch(SearchRequset searchRequset){
        ResponseData responseData = new ResponseData();
        try {
            List<HomeResponse> result =  homeService.getDataSearch(searchRequset);
            if (result != null){
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(result);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setErrorType(e.getMessage());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }
}
