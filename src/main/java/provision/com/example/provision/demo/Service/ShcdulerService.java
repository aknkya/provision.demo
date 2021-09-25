package provision.com.example.provision.demo.Service;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ShcdulerService {


    @Scheduled(fixedRate = 400000)
    public void wrıteIt(){
        System.out.println("Merhaba");
    }
}
