package SE2.Project.Backend.controller;

import SE2.Project.Backend.model.*;
import SE2.Project.Backend.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    // CRUD admin
    @GetMapping("/addAdmin")
    public String addAdmin(Model model){
        model.addAttribute("admin", new Admin());
        return "adminAdd";
    }

    @RequestMapping("/insertAdmin")
    public String insertAdmin(@Valid Admin admin, BindingResult result, Model model){
        User user = admin.getUser();
        admin.setUser(user);
        userRepository.save(user);
        adminRepository.save(admin);
        return "redirect:/admin/adminDetail/" + user.getId();
    }

    // CRUD student
    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "studentAdd";
    }

    @RequestMapping(value = "/insertStudent")
    public String insertStudent(@Valid Student student, BindingResult result, Model model) {

        User user = student.getUser();
        student.setUser(user);
        userRepository.save(user);
        studentRepository.save(student);

        return "redirect:/admin/studentDetail/" + user.getId();

    }

    @GetMapping(value = "/listStudent")
    public String showAllStudents(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "studentList";
    }


    // CRUD teacher
    @GetMapping("/addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherAdd";
    }

    @RequestMapping(value = "/insertTeacher")
    public String insertTeacher(@Valid Teacher teacher, BindingResult result, Model model) {

        User user = teacher.getUser();
        teacher.setUser(user);
        userRepository.save(user);
        teacherRepository.save(teacher);

        return "redirect:/admin/teacherDetail/" + user.getId();

    }

    @GetMapping("/addCourse")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("teacherUsernames", teacherRepository.findAllTeacherUsernames());
        System.out.println(teacherRepository.findAllTeacherUsernames());
        return "courseAdd";
    }

    @RequestMapping(value = "/insertCourse")
    public String insertCourse(@Valid Course course, BindingResult result, Model model) {
        courseRepository.save(course);
        return "redirect:/admin/courseDetail/" + course.getCourseId();
    }

}