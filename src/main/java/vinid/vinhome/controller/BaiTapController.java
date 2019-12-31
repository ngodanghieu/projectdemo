package vinid.vinhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinid.vinhome.entities.User;
import vinid.vinhome.request.SearchUserRequest;
import vinid.vinhome.request.UserRequest;
import vinid.vinhome.response.ResponseData;
import vinid.vinhome.response.UserResponse;
import vinid.vinhome.service.UserService;
import vinid.vinhome.util.Constant;

import java.util.List;

@RestController
@RequestMapping(value = "/api/baitap/")
public class BaiTapController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "get-user-by-id/{id}")
    public ResponseEntity<?> getById(@RequestParam("id") Long id){
        ResponseData responseData = new ResponseData();
        try {
            if (id == null){
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setStatus(2);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }

            User user = userService.findById(id);

            if (user == null){
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                responseData.setStatus(3);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(user);
                responseData.setStatus(3);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

        }catch (Exception e){
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.getMessage());
            responseData.setStatus(3);
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "create-user")
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest){
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

    @PutMapping(value = "update-user")
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest){
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
            if (!b) {
                responseData.setStatus(2);
                responseData.setMessage("Have not Phone.");
                responseData.setErrorType(Constant.ErrorTypeCommon.PHONE_EXISTS);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }
            UserResponse userResponse = userService.update(userRequest);

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

    @DeleteMapping(value = "delete-user-by-id/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        ResponseData responseData = new ResponseData();
        try {
            if (id == null){
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setStatus(2);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }

            boolean isRemove = userService.deleteById(id);

            if (!isRemove){
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                responseData.setStatus(3);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(Constant.ErrorTypeCommon.OK);
                responseData.setStatus(1);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

        }catch (Exception e){
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.getMessage());
            responseData.setStatus(3);
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "search-user/{key}")
    public ResponseEntity<?> getById(@RequestParam("key") String key){
        ResponseData responseData = new ResponseData();
        try {
            if (key == null ){
                responseData.setMessage(Constant.ErrorTypeCommon.INVALID_INPUT);
                responseData.setStatus(2);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
            }

            List<User> users = userService.search(key);

            if (users == null || users.isEmpty()){
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                responseData.setStatus(3);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }else {
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                responseData.setContent(users);
                responseData.setStatus(3);
                return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);
            }

        }catch (Exception e){
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.getMessage());
            responseData.setStatus(3);
        }
        return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "get-all-user")
    public ResponseEntity<?> getAllUser(){
        ResponseData responseData = new ResponseData();
        try {
            List<User> result = userService.getAll();
            if (result == null){
                responseData.setStatus(2);
                responseData.setMessage(Constant.ErrorTypeCommon.NOT_FOUND_ITEM);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);
            }
                responseData.setStatus(1);
                responseData.setContent(result);
                responseData.setMessage(Constant.ErrorTypeCommon.OK);
                return new ResponseEntity<ResponseData>(responseData,HttpStatus.OK);

        }catch (Exception e){
            responseData.setStatus(3);
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
            responseData.setErrorType(e.getMessage());
        }
       return new ResponseEntity<ResponseData>(responseData,HttpStatus.BAD_REQUEST);
    }
}
