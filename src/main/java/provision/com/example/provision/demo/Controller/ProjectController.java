package provision.com.example.provision.demo.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import provision.com.example.provision.demo.Entity.ClassEntity;
import provision.com.example.provision.demo.Service.ServiceClass;
import provision.com.example.provision.demo.Service.WebServiceClass;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@Api(value = " API Dökümantasyonu")
public class ProjectController {
    @Autowired
    private ServiceClass serviceClass;
    @Autowired
    private  WebServiceClass webServiceClass;




    @PostMapping("/datas")
    @ApiOperation(value="Post listesi döner")
    public String getIndex1(Model model, @RequestParam(value = "currency") String currency) throws JSONException {
       try {
           webServiceClass.getDataFromEntity(currency); // Verileri Web Serviceden Alır DataBase e Yazar
       }
       catch (Exception e)
       {
           return "index";
       }






        List<ClassEntity> arrList=serviceClass.getAll();
        serviceClass.deleteAll();


        PrintWriter out;
        try {
            out = new PrintWriter("datalar.csv");
            out.print("date,");
            out.println("value");

            for(int i=0;i<arrList.size();i++) {
                out.print(arrList.get(i).getDate() +",");
                out.println(arrList.get(i).getDovizDegisim());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist");
            e.printStackTrace();
        }





        return "index";
    }



    @GetMapping("/")
    @ApiOperation(value="Post listesi döner")
    public String getIndex(Model model) throws JSONException {

      webServiceClass.getDataFromEntity("JPY"); // Verileri Web Serviceden Alır DataBase e Yazar






        List<ClassEntity> arrList=serviceClass.getAll();
        serviceClass.deleteAll();

        PrintWriter out;
        try {
            out = new PrintWriter("datalar.csv");
             out.print("date,");
             out.println("value");

            for(int i=0;i<arrList.size();i++) {
                out.print(arrList.get(i).getDate() +",");
                out.println(arrList.get(i).getDovizDegisim());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist");
            e.printStackTrace();
        }





        return "index";

    }



}
