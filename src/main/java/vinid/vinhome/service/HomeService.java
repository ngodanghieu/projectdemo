package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.Home;
import vinid.vinhome.repository.IHomeRepository;
import vinid.vinhome.request.HomeRequest;
import vinid.vinhome.request.SearchRequset;
import vinid.vinhome.response.HomeResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class HomeService {
    @Autowired
    private IHomeRepository iHomeRepository;

    public List<HomeResponse> getAllHome(){
        List<Home> homeResponseList = iHomeRepository.getAllHome();
        List<HomeResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()){
            homeResponseList.forEach(x ->{
                    result.add(mappingEntitiToResponse(x));
            });
            return result;
        }else {
            return null;
        }
    }

    public HomeResponse getHomeById(Long id){
        Optional<Home> optionalHome = iHomeRepository.findById(id);
        Home home = optionalHome.isPresent() ? optionalHome.get(): null;
        if (home != null ){
            return mappingEntitiToResponse(home);
        }else {
            return null;
        }
    }

    public List<HomeResponse> getDataSearch(SearchRequset searchRequset){
        List<Home> homeResponseList = (searchRequset.getAdress() == null && searchRequset.getCodeCity() == null && searchRequset.getCodeDistrit() == null) ?
                iHomeRepository.getAllHome() : iHomeRepository.getDataSearch(
                searchRequset.getCodeCity() == null ? "''" : searchRequset.getCodeCity(),
                searchRequset.getCodeDistrit() == null ? "''" : searchRequset.getCodeDistrit(),
                searchRequset.getAdress() == null ? "''" : searchRequset.getAdress());
        List<HomeResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()){
            homeResponseList.forEach(x ->{

                    result.add(mappingEntitiToResponse(x));

            });
            return result;
        }else {
            return null;
        }
    }

    public Boolean careateHome(HomeRequest homeRequest){
        return true;
    }

    private HomeResponse mappingEntitiToResponse(Home home){
        return new HomeResponse(home.getHomeId(),home.getContent(),home.getImageUrl(),home.getPrice(),home.getCreatedOn(),home.getCreatedBy());
    }


}
