package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.AdressHome;
import vinid.vinhome.repository.IAdressHome;

@Service
public class AdressHomeService {
    @Autowired
    private IAdressHome iAdressHome;

    public AdressHome getiAdressHomeByIdHome(Long idHome) {
        if (idHome != null)
            return iAdressHome.findAdressHomeByHome(idHome);
        else
        return null;
    }
}
