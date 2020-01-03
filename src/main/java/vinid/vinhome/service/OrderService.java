package vinid.vinhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vinid.vinhome.entities.Home;
import vinid.vinhome.entities.Order;
import vinid.vinhome.entities.User;
import vinid.vinhome.repository.IOrderHistoryRepository;
import vinid.vinhome.repository.IOrderInfoRepository;
import vinid.vinhome.repository.IOrderRepository;
import vinid.vinhome.request.OrderRequest;
import vinid.vinhome.util.RandomStringHelper;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Autowired
    private IOrderInfoRepository iOrderInfoRepository;

    @Autowired
    private IOrderHistoryRepository iOrderHistoryRepository;

    @Autowired
    private HomeService homeService;

    @Autowired
    private UserService userService;

    public Boolean save(OrderRequest orderRequest){
        return true;
    }

    private Order mappModelToEntiti(OrderRequest orderRequest, Home homem, User user){
        Order resuslt = new Order();
        RandomStringHelper gen = new RandomStringHelper(8, ThreadLocalRandom.current());
        String code = gen.nextString();
        resuslt.setHome(homem);
        resuslt.setOrderCode(code.toUpperCase());
        resuslt.setUser(user);
        resuslt.setOrderDate(new Date());
        return resuslt;

    }
}
