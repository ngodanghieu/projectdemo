package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.User;
import vinid.vinhome.repository.IUserRepository;
import vinid.vinhome.request.UserRequest;
import vinid.vinhome.response.UserResponse;
import vinid.vinhome.util.Constant;
import vinid.vinhome.util.Helper;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private SmsService smsService;

    public UserResponse save(UserRequest userRequest){
        String HasPw = Helper.HasPw(userRequest.getUserHash());
        User newUser = new User();
        newUser.setUserPhone(userRequest.getUserPhone());
        newUser.setUserHash(HasPw);
        newUser.setUserFullName(userRequest.getUserFullName());
        newUser.setUserCreatedOn(new Date());
        newUser.setStatus(1);
        sendOTP(newUser,userRequest.getUserPhone());
        User result = iUserRepository.save(newUser);

        if (result == null)
            return null;
        else
            return MapEntitytoModelResponse(newUser);
    }

    public String resetPassword(String phone, String passOld,String passNew){
        Optional<User> optionalUser= iUserRepository.findUserByPhone(phone);
        User user = optionalUser.isPresent() ? optionalUser.get(): null;
        if (user != null){
            if (Helper.CheckPw(passOld,user.getUserHash())){
                String hPassw = Helper.HasPw(passNew);
                user.setUserHash(hPassw);
                User result = iUserRepository.save(user);
                if (result == null)
                    return "Loi trong qua trinh xu ly data";
                else
                    return "DONE";
            }else {
                return "PASS NOT TRUE!";
            }
        }else {
            return "HAVE NOT USER !!";
        }
    }

    public UserResponse login(UserRequest userRequest){
        if (checkExistPhoneNumber(userRequest.getUserPhone())){
            Optional<User> optionalUser= iUserRepository.findUserByPhone(userRequest.getUserPhone());
            User user = optionalUser.isPresent() ? optionalUser.get(): null;
            if (user != null && Helper.CheckPw(userRequest.getUserHash(),user.getUserHash())){
                return MapEntitytoModelResponse(user);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public Boolean checkExistPhoneNumber(String phone){
        Optional<User> optionalUser= iUserRepository.findUserByPhone(phone);
         User user = optionalUser.isPresent() ? optionalUser.get(): null;
         if (user != null)
             return true;
         else
             return false;
    }

    private UserResponse MapEntitytoModelResponse(User user){
        return new UserResponse(user.getUserFullName(),user.getUserPhone(),user.getUserEmail(),"");
    }

    private void sendOTP(User u, String phone) {
//        String otp = new RandomStringHelper(6, ThreadLocalRandom.current(), RandomStringHelper.digits).nextString();
        String otp = "123456";
//        smsService.sendSMSOTPCode(phone, otp);
        u.setUserOptCode(otp);
        long nowTime = new Date().getTime();
        u.setUserExpiredOtp(new Date(nowTime + Constant.TIME_EXPIRED_OTP));
    }

    public boolean validateOTPCode(String phone, String otpCode) throws Exception {
        Optional<User> optionalUser= iUserRepository.findUserByPhone(phone);
        User user = optionalUser.isPresent() ? optionalUser.get(): null;
        if (user != null){
                if(user.getUserOptCode().equals(otpCode) && user.getUserExpiredOtp().after(new Date())) {
                    return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}
