package SE2.Project.Backend.model;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @NotNull
    @Column(name = "course_code")
    private String courseCode;

    @NotNull
    @Column(name = "course_name")
    @Unique
    private String courseName;

    @NotNull
    @Column(name = "credit")
    private Integer credit;

    @NotNull
    @Column(name = "fee")
    private Double fee;

    @NotNull
    @Column(name = "course_type")
    private String courseType;

   
    @NotNull
    @ManyToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id")
    private Semester semester;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "major_id")
    private Major major;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacherCode", referencedColumnName = "teacherCode")
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


    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

}
