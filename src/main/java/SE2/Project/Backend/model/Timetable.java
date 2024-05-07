package SE2.Project.Backend.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private Long timetableId;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;


    @Column(name = "TeachingDay")
    private String teachingDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "start_time")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "end_time")
    private LocalTime endTime;

    public Long getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Long timetableId) {
        this.timetableId = timetableId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }



    public String getTeachingDay() {
        return teachingDay;
    }

    public void setTeachingDay(String teachingDay) {
        this.teachingDay = teachingDay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
