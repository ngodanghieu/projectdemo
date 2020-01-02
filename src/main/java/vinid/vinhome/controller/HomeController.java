package vinid.vinhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vinid.vinhome.model.UploadForm;
import vinid.vinhome.repository.IHomeRepository;
import vinid.vinhome.repository.IHomeWorkTimeRepository;
import vinid.vinhome.request.SearchRequset;
import vinid.vinhome.response.DataResultResponse;
import vinid.vinhome.response.HomeResponse;
import vinid.vinhome.response.ResponseData;
import vinid.vinhome.service.HomeService;
import vinid.vinhome.service.HomeWorkTimeService;
import vinid.vinhome.util.Constant;

import javax.websocket.server.PathParam;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home/")
public class HomeController {
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/upload";

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
            responseData.setErrorType(e.toString());
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

    @GetMapping(value = "get-all-by-user/{idUser}")
    public ResponseEntity<?> getAllHomeByUser(@RequestParam("idUser") Long idUser){
        ResponseData responseData = new ResponseData();
        try {
            List<DataResultResponse> result =  homeService.getHomeByIdUser(idUser);
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
            responseData.setErrorType(e.toString());
            responseData.setMessage(Constant.ErrorTypeCommon.ERROR_PROCESS_DATA);
        }
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("uploadForm") UploadForm form) {
        // Create folder to save file if not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile fileData = form.getFileData();
        String name = fileData.getOriginalFilename();
        if (name != null && name.length() > 0) {
            try {
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                return ResponseEntity.ok("Upload success");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when uploading");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }
}
