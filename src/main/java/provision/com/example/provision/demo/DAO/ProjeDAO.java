package provision.com.example.provision.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import provision.com.example.provision.demo.Entity.ClassEntity;

public interface ProjeDAO extends JpaRepository<ClassEntity,Long> {
}
