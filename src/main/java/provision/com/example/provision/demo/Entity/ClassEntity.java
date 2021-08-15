package provision.com.example.provision.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityID;
    private String date;
    private Double  dovizDegisim;


    public ClassEntity() {
    }

    public ClassEntity(Long entityID, String date, Double dovizDegisim) {
        this.entityID = entityID;
        this.date = date;
        this.dovizDegisim = dovizDegisim;
    }

    public Long getEntityID() {
        return entityID;
    }

    public void setEntityID(Long entityID) {
        this.entityID = entityID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getDovizDegisim() {
        return dovizDegisim;
    }

    public void setDovizDegisim(Double dovizDegisim) {
        this.dovizDegisim = dovizDegisim;
    }
}
