package provision.com.example.provision.demo.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import provision.com.example.provision.demo.Entity.ClassEntity;
import provision.com.example.provision.demo.Service.ServiceClass;
import provision.com.example.provision.demo.Service.WebServiceClass;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


@Controller
@Api(value = " API Dökümantasyonu")
public class ProjectController {

    private static final Logger LOGGER= LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ServiceClass serviceClass;
    @Autowired
    private  WebServiceClass webServiceClass;


@GetMapping("/deneme")
public String getDeneme(){

    return "deneme";
}




    @GetMapping("/")
    @ApiOperation(value="Post listesi döner")
    public String getIndex(Model model, @RequestParam(required = false, value = "currency") String currency) throws JSONException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.debug("This is a debug message");
        LOGGER.info(auth.getName()+":  user logged in to maın page");
        LOGGER.warn("This is a warn message");
        LOGGER.error("This is an error message");

         webServiceClass.getDataFromEntity( currency==null ||currency.isEmpty() ? "JPY": currency);


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
