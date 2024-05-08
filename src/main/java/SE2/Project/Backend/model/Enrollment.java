package SE2.Project.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "student_code", referencedColumnName = "student_code")
    private Student student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id")
    private Semester semester;

    @NotNull
    @Column(name = "enroll_date")
    private LocalDate enrollDate;

    @NotNull
    @Column(name = "enroll_time")
    private LocalTime enrollTime;

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public LocalTime getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(LocalTime enrollTime) {
        this.enrollTime = enrollTime;
    }
}