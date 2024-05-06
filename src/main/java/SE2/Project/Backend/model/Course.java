package SE2.Project.Backend.model;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    @Unique
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id", unique = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "major_id", unique = false)
    private Major major;

    @ManyToOne
    @JoinColumn(name = "teacherCode", referencedColumnName = "teacherCode",unique = false)
    private Teacher teacher;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
