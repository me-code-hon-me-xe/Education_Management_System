package SE2.Project.Backend.repository;

import SE2.Project.Backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.form.SelectTag;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long>{
    // We can customize @SQL statement to check the condition with database
    // This is JPA Query by Name Convention
//    boolean existsByUsername(String username);
//    boolean existsByEmail(String email);
//    boolean existsByStudentCode(String studentCode);
}
