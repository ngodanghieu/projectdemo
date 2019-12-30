package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.repository.IHomeRepository;

@Service
public class HomeService {
    @Autowired
    private IHomeRepository iHomeRepository;


}
