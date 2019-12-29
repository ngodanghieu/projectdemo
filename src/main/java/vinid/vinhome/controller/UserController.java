package vinid.vinhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinid.vinhome.entities.User;
import vinid.vinhome.request.ResetPasswordResquet;
import vinid.vinhome.request.UserRequest;
import vinid.vinhome.response.ResponseData;
import vinid.vinhome.response.UserResponse;
import vinid.vinhome.service.UserService;
import vinid.vinhome.util.Constant;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/test",method = RequestMethod.GET)
    public String zzz (){
        return  "sadasdas";
    }

    @RequestMapping(value = "/api/register",method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
        ResponseData responseData = new ResponseData();
        try{
            if (userRequest == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
            if(userRequest.getUserPhone().length() >=  12 && userRequest.getUserPhone().length() < 8){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_PHONE);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
            }
            boolean b = userService.checkExistPhoneNumber(userRequest.getUserPhone());
            if (b) {
                responseData.setStatus(2);
                responseData.setMessage("Exist an phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
            UserResponse userResponse = userService.save(userRequest);

            if(userResponse != null){
                responseData.setStatus(1);
                responseData.setContent(userResponse);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setErrorType(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage("ERROR_PROCESS_DATA");
                responseData.setErrorType(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/resetPass", method = RequestMethod.POST)
    public ResponseEntity<?> resetPass(@RequestBody ResetPasswordResquet resetPasswordResquet){
        ResponseData responseData = new ResponseData();
        try {
            if (resetPasswordResquet == null){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.FORM_DATA_INVALID);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
            }
            boolean b = userService.checkExistPhoneNumber(resetPasswordResquet.getPhone());
            if (!b) {
                responseData.setStatus(2);
                responseData.setMessage("No phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_PHONE);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }

            String result = userService.resetPassword(resetPasswordResquet.getPhone(),resetPasswordResquet.getOldPassword(),resetPasswordResquet.getNewPassword());
            responseData.setStatus(1);
            responseData.setContent(result);
            responseData.setMessage(Constant.ErrorTypeCommon.OK);
            return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
        ResponseData responseData = new ResponseData();
        try {
            if (userRequest == null) {
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setErrorType(Constant.ErrorTypeCommon.INVALID_INPUT);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
            if(userRequest.getUserPhone().length() >=  12 && userRequest.getUserPhone().length() < 8){
                responseData.setStatus(7);
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_PHONE);
                return  new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
            }
            boolean b = userService.checkExistPhoneNumber(userRequest.getUserPhone());
            if (!b) {
                responseData.setStatus(2);
                responseData.setMessage("Exist an phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
            UserResponse userResponse = userService.login(userRequest);

            if(userResponse != null){
                responseData.setStatus(1);
                responseData.setContent(userResponse);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setErrorType(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setStatus(2);
                responseData.setMessage("User or pass false!");
                responseData.setErrorType(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }


        }catch (Exception e){
            responseData.setStatus(2);
            responseData.setMessage(e.toString());
        }
        return new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
    }
}
