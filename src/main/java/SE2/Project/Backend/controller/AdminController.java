package SE2.Project.Backend.controller;

import SE2.Project.Backend.model.*;
import SE2.Project.Backend.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Strings;

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

    @RequestMapping(value = "/insertAdmin")
    public String insertAdmin(@Valid Admin admin, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "adminAdd";
        } else if (isDuplicateEntry(admin.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "adminAdd";
        }
        User user = admin.getUser();
        admin.setUser(user);
        userRepository.save(user);
        adminRepository.save(admin);

        return "redirect:/admin/listAdmin";
    }

    @GetMapping(value = "/listAdmin")
    public String showAllAdmin(Model model, @RequestParam(name = "adminCode", required = false) Integer adminCode,
                                  @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Admin> admins;
        Admin admin;
        if (showAll != null) {
            admins = adminRepository.findAll();
            model.addAttribute("admins", admins);
            return "adminList";
        }
        if(adminCode != null){
            admin = adminRepository.findByAdminCode(adminCode);
            if(admin != null){
                model.addAttribute("admins", admin);
            } else {
                model.addAttribute("notFoundMessage", "No teacher found with the provided admin code");
            }
        } else {
            admins = adminRepository.findAll();
            model.addAttribute("admins", admins);
        }

        return "adminList";
    }

    @GetMapping(value = "/adminDetail/{adminCode}")
    public String showAdminDetail(@PathVariable Integer adminCode, Model model){
        Admin admin = adminRepository.findByAdminCode(adminCode);
        model.addAttribute("admin", admin);
        return "adminDetail";
    }

    @GetMapping(value = "/updateAdmin/{adminCode}")
    public String updateAdmin(@PathVariable Integer adminCode, Model model){
        Admin admin = adminRepository.findByAdminCode(adminCode);
        model.addAttribute("admin", admin);
        return "adminUpdate";
    }

    @PostMapping(value = "/saveAdmin")
    public String saveAdmin(@Valid Admin admin, BindingResult result){
        if(result.hasErrors()){
            return "adminUpdate";
        }
        User user = admin.getUser();
        userRepository.save(user);
        adminRepository.save(admin);
        return "redirect:/admin/listAdmin";
    }

    @RequestMapping(value = "/deleteAdmin/{adminCode}")
    public String deleteAdmin(@PathVariable Integer adminCode){
        Admin admin = adminRepository.findByAdminCode(adminCode);
        User user = admin.getUser();
        adminRepository.delete(admin);
        userRepository.delete(user);
        return "redirect:/admin/listAdmin";
    }













    // CRUD student
    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "studentAdd";
    }

    @RequestMapping(value = "/insertStudent")
    public String insertStudent(@Valid Student student, BindingResult result, Model model) {

        if(result.hasErrors()){
            return "studentAdd";
        } else if (isDuplicateEntry(student.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "studentAdd";
        }
        User user = student.getUser();
        student.setUser(user);
        userRepository.save(user);
        studentRepository.save(student);

        return "redirect:/admin/listStudent";
    }

    @GetMapping(value = "/listStudent")
    public String showAllStudents(Model model, @RequestParam(name = "studentCode", required = false) Integer studentCode,
                                  @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Student> students;
        Student student;
        if (showAll != null) {
            students = studentRepository.findAll();
            model.addAttribute("students", students);
            return "studentList";
        }
        if(studentCode != null){
            student = studentRepository.findByStudentCode(studentCode);
            if(student != null){
                model.addAttribute("students", student);
            } else {
                model.addAttribute("notFoundMessage", "No student found with the provided student code");
            }
        } else {
            students = studentRepository.findAll();
            model.addAttribute("students", students);
        }

        return "studentList";
    }

    @GetMapping(value = "/studentDetail/{studentCode}")
    public String showStudentDetail(@PathVariable Integer studentCode, Model model){
        Student student = studentRepository.findByStudentCode(studentCode);
        System.out.println(student.getUser().getEmail());
        model.addAttribute("student", student);
        return "studentDetail";
    }



    @GetMapping(value = "/updateStudent/{studentCode}")
    public String updateStudent(@PathVariable Integer studentCode, Model model){
        Student student = studentRepository.findByStudentCode(studentCode);
        model.addAttribute("student", student);
        return "studentUpdate";
    }


    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "studentUpdate"; // Return to the update form if there are validation errors
        }
        User user = student.getUser();
        userRepository.save(user);
        // Save the student
        studentRepository.save(student);

        // Redirect to the student list page
        return "redirect:/admin/listStudent";
    }

    @RequestMapping(value = "/deleteStudent/{studentCode}")
    public String deleteStudent(@PathVariable Integer studentCode){
        Student student = studentRepository.findByStudentCode(studentCode);
        User user = student.getUser();
        studentRepository.delete(student);
        userRepository.delete(user);
        return "redirect:/admin/listStudent";
    }
















    // CRUD teacher
    @GetMapping("/addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherAdd";
    }

    @RequestMapping(value = "/insertTeacher")
    public String insertTeacher(@Valid Teacher teacher, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "teacherAdd";
        } else if (isDuplicateEntry(teacher.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "teacherAdd";
        }

        User user = teacher.getUser();
        teacher.setUser(user);
        userRepository.save(user);
        teacherRepository.save(teacher);

        return "redirect:/admin/listTeacher";
    }

    private boolean isDuplicateEntry(String username) {
        System.out.println(userRepository.existsByUsername(username));
        return userRepository.existsByUsername(username);
    }

    @GetMapping(value = "/listTeacher")
    public String showAllTeachers(Model model, @RequestParam(name = "teacherCode", required = false) Integer teacherCode,
                                  @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Teacher> teachers;
        Teacher teacher;
        if (showAll != null) {
            teachers = teacherRepository.findAll();
            model.addAttribute("teachers", teachers);
            return "teacherList";
        }
        if(teacherCode != null){
            teacher = teacherRepository.findByTeacherCode(teacherCode);
            if(teacher != null){
                model.addAttribute("teachers", teacher);
            } else {
                model.addAttribute("notFoundMessage", "No teacher found with the provided teacher id");
            }
        } else {
            teachers = teacherRepository.findAll();
            model.addAttribute("teachers", teachers);
        }

        return "teacherList";
    }

    @GetMapping(value = "/teacherDetail/{teacherCode}")
    public String showTeacherDetail(@PathVariable Integer teacherCode, Model model){
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode);
        model.addAttribute("teacher", teacher);
        return "teacherDetail";
    }

    @GetMapping(value = "/updateTeacher/{teacherCode}")
    public String updateTeacher(@PathVariable Integer teacherCode, Model model){
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode);
        model.addAttribute("teacher", teacher);
        return "teacherUpdate";
    }

    @PostMapping(value = "/saveTeacher")
    public String saveTeacher(@Valid Teacher teacher, BindingResult result){
        if(result.hasErrors()){
            return "teacherUpdate";
        }
        User user = teacher.getUser();
        userRepository.save(user);
        teacherRepository.save(teacher);
        return "redirect:/admin/listTeacher";
    }

    @RequestMapping(value = "/deleteTeacher/{teacherCode}")
    public String deleteTeacher(@PathVariable Integer teacherCode){
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode);
        User user = teacher.getUser();
        teacherRepository.delete(teacher);
        userRepository.delete(user);
        return "redirect:/admin/listTeacher";
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