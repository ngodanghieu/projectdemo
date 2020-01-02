package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.Acreage;
import vinid.vinhome.entities.AcreageHome;
import vinid.vinhome.entities.AdressHome;
import vinid.vinhome.entities.Home;
import vinid.vinhome.repository.IAcreage;
import vinid.vinhome.repository.IAcreageHomeRepository;
import vinid.vinhome.repository.IAdressHome;
import vinid.vinhome.repository.IHomeRepository;
import vinid.vinhome.request.HomeRequest;
import vinid.vinhome.request.SearchRequset;
import vinid.vinhome.response.DataResultResponse;
import vinid.vinhome.response.HomeResponse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeService {
    @Autowired
    private IHomeRepository iHomeRepository;

    @Autowired
    private AdressHomeService adressHomeService;

    @Autowired
    private IAcreage iAcreage;

    @Autowired
    private IAcreageHomeRepository iAcreageHomeRepository;

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
        Home home = iHomeRepository.findByHomeId(homeRequest.getId());
        if (home != null){
             Home save =  mappingModelToEntitiHome(home,homeRequest);
             iHomeRepository.save(save);


            return true;
        }else {
            return false;
        }
    }

    public  List<DataResultResponse> getHomeByIdUser(Long idUser){
        List<Home> homeResponseList = iHomeRepository.getHomeByIdUser(idUser);
        List<DataResultResponse> result = new LinkedList<>();
        if (homeResponseList != null && !homeResponseList.isEmpty()){
            homeResponseList.forEach(x ->{
                AcreageHome acreageHome = iAcreageHomeRepository.getByIdHome(x.getHomeId());
                List<String> listPrice = null;
                if (acreageHome != null){
                    listPrice = removeDulicates(iHomeRepository.getAllPrice(acreageHome.getAcreage().getAcreageId()));
                }
                result.add(mapEntitiToDataResultResponse(x,listPrice == null ? null : listPrice));
            });
            return result;
        }else {
            return null;
        }

    }

    private List<String> removeDulicates(List<String> list){
        List<String> listWithoutDuplicates = list.stream()
                .distinct()
                .collect(Collectors.toList());
        return listWithoutDuplicates;
    }

    private HomeResponse mappingEntitiToResponse(Home home){
        String title = getTitleHome(home.getHomeId());
        Acreage acreage = iAcreage.getByIdHome(home.getHomeId());
        StringBuilder acreageString = new StringBuilder();
        acreageString.append(acreage.getTotalArea())
                .append("(").append(acreage.getWidth()).append("x").append(acreage.getHeight()).append(")");
        return new HomeResponse(home.getHomeId(),title,home.getContent(),home.getImageUrl(),acreageString.toString(),home.getPrice(),home.getCreatedOn(),home.getCreatedBy());
    }

    private String getTitleHome(Long id){
        AdressHome adressHome = adressHomeService.getiAdressHomeByIdHome(id);
        StringBuilder title = new StringBuilder();
        title.append(adressHome.getNameHome()).append(" + ").append(adressHome.getBuilding().getName());
        return title.toString();
    }

    private DataResultResponse mapEntitiToDataResultResponse(Home home,  List<String> priceList){
        String title = getTitleHome(home.getHomeId());
        return new DataResultResponse(home.getHomeId(),title,priceList);
    }

    private Home mappingModelToEntitiHome(Home home, HomeRequest homeRequest){
        home.setContent(homeRequest.getContent());
        home.setPrice(homeRequest.getPrice());
        home.setImageUrl(homeRequest.getImageUrl());
        return home;
    }

}
