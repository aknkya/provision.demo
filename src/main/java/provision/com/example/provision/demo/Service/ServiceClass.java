package provision.com.example.provision.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import provision.com.example.provision.demo.DAO.ProjeDAO;
import provision.com.example.provision.demo.Entity.ClassEntity;

import java.util.List;

@Service
public class ServiceClass {
    @Autowired
   private  ProjeDAO projeDAO;



   public void deleteAll()
   {
       projeDAO.deleteAll();
   }
    public void addEntity(ClassEntity classEntity){
        projeDAO.save(classEntity);

    }

    public  List<ClassEntity> getAll(){
        List<ClassEntity> dataList=projeDAO.findAll();
        return dataList;
    }

}
