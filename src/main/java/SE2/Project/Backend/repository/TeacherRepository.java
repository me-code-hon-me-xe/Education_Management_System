package SE2.Project.Backend.repository;

import SE2.Project.Backend.model.Course;
import SE2.Project.Backend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT u.userID AS id, u.userFullname AS username FROM User u WHERE u.role = 'teacher'")
    List<Map<String, Object>> findAllTeacherUsernames();
    Teacher findByTeacherCode(Integer teacherCode);
}
