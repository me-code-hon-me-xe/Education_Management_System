package SE2.Project.Backend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "student_code", referencedColumnName = "student_code")
    private Student student;

    @NotNull
    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @NotNull
    @Column(name = "attendance")
    private double attendance;

    @NotNull
    @Column(name = "midterm_grade")
    private double midtermGrade;

    @NotNull
    @Column(name = "final_grade")
    private double finalGrade;

    @NotNull
    @Column(name = "total_grade")
    private double totalGrade;

    @NotNull
    @Column(name = "study_status")
    private String studyStatus;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
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

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public double getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(double midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }
}