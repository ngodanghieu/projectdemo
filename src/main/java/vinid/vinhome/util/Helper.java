package vinid.vinhome.util;

import org.mindrot.jbcrypt.BCrypt;

public class Helper  {
    public static String HasPw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(4));
    }

    public static Boolean CheckPw(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
