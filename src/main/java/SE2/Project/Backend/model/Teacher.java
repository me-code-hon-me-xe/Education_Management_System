package SE2.Project.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.boot.model.naming.Identifier;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherCode;

//    @Valid
    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Integer getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(Integer teacherCode) {
        this.teacherCode = teacherCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Constructors, getters, and setters
}