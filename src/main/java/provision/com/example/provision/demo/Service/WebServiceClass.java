package provision.com.example.provision.demo.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import provision.com.example.provision.demo.DAO.ProjeDAO;
import provision.com.example.provision.demo.Entity.ClassEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class WebServiceClass {
    private final RestTemplate restTemplate;
    private final ProjeDAO projeDAO;

    public WebServiceClass(RestTemplate restTemplate, ProjeDAO projeDAO) {
        this.restTemplate = restTemplate;
        this.projeDAO = projeDAO;
    }


    public void getDataFromEntity(String currency) throws JSONException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        Date date1 = new Date();
        String dateNow=dateFormat.format(date);
         date1.setMonth(date.getMonth()-2);
        String dateStart=dateFormat.format(date1);


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://evds2.tcmb.gov.tr/service/evds/series=TP.DK."+currency+".A")
                .queryParam("startDate", dateStart)
                .queryParam("endDate", dateNow)
                .queryParam("type", "json")
                .queryParam("key", "2JrZdzC4CS");
              /*  .queryParam("aggregationTypes","avg")
                .queryParam("formulas","1")
                .queryParam("frequency","1");*/

            String urlnew=builder.build().encode().toUriString();
            urlnew=urlnew.replace("?","&");


    HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                urlnew,
                HttpMethod.GET,
                entity,
                String.class);
        String bodyOfResponse=response.getBody();

          JSONObject obj = new JSONObject(bodyOfResponse);


          JSONArray arr= obj.getJSONArray("items");
           String[] tarihler=new String[arr.length()];
           Double[] degerler=new Double[arr.length()];
           ArrayList<ClassEntity> dataArray= new ArrayList<ClassEntity>();


          for (int i=0;i<arr.length();i++){
               ClassEntity entity1=new ClassEntity();
               tarihler[i]=arr.getJSONObject(i).getString("Tarih");
             try {
                 if(arr.getJSONObject(i).getString("TP_DK_"+currency+"_A")!=null){
                     degerler[i] = arr.getJSONObject(i).getDouble("TP_DK_"+currency+"_A");


             }

              }
             catch (Exception e){
                 degerler[i]=degerler[i-1];
             }

                 entity1.setDate(tarihler[i]);
                 entity1.setDovizDegisim(degerler[i]);
                 projeDAO.save(entity1);


          }







        int c =5;


    }

}
