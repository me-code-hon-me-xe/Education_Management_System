package SE2.Project.Backend.controller;

import SE2.Project.Backend.model.*;
import SE2.Project.Backend.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    private AccountantRepository accountantRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private SemesterRepository semesterRepository;

    @ModelAttribute("admin")
    public Admin admin() {
        Admin admin=new Admin();
        User user = new User();
        user.setRole("Admin");
        admin.setUser(user);
        return admin;
    }

    @ModelAttribute("student")
    public Student student() {
        Student student=new Student();
        User user = new User();
        user.setRole("Student");
        student.setUser(user);
        return student;
    }

    @ModelAttribute("teacher")
    public Teacher teacher() {
        Teacher teacher=new Teacher();
        User user = new User();
        user.setRole("Teacher");
        teacher.setUser(user);
        return teacher;
    }

    @ModelAttribute("accountant")
    public Accountant accountant() {
        Accountant accountant=new Accountant();
        User user = new User();
        user.setRole("Accountant");
        accountant.setUser(user);
        return accountant;
    }

    // CRUD admin
    @GetMapping("/addUser")
    public String addUser(){
        return "admin-account-management";
    }

    @RequestMapping(value = "/insertAdmin")
    public String insertAdmin(@Valid Admin admin, BindingResult result) {

        if(result.hasErrors()){
            return "admin-account-management";
        } else if (isDuplicateEntry(admin.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "admin-account-management";
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
            return "admin-account-management";
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

        return "admin-account-management";
    }

    @GetMapping(value = "/adminDetail/{adminCode}")
    public String showAdminDetail(@PathVariable Integer adminCode, Model model){
        Admin admin = adminRepository.findByAdminCode(adminCode);
        model.addAttribute("admin", admin);
        return "admin-detail";
    }

    @GetMapping(value = "/updateAdmin/{adminCode}")
    public String updateAdmin(@PathVariable Integer adminCode, Model model){
        Admin admin = adminRepository.findByAdminCode(adminCode);
        model.addAttribute("admin", admin);
        return "admin-detail-edit";
    }

    @PostMapping(value = "/saveAdmin")
    public String saveAdmin(@Valid Admin admin, BindingResult result){
        if(result.hasErrors()){
            return "admin-detail-edit";
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

    @RequestMapping(value = "/insertStudent")
    public String insertStudent(@Valid Student student, BindingResult result) {

        if(result.hasErrors()){
            return "student-account-management";
        } else if (isDuplicateEntry(student.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "student-account-management";
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
        Iterable<Major> majors;
        Major major;
        if (showAll != null) {
            students = studentRepository.findAll();
            model.addAttribute("students", students);
            return "student-account-management";
        }
        if(studentCode != null){
            student = studentRepository.findByStudentCode(studentCode);
            if(student != null){
                model.addAttribute("students", student);
            } else {
                model.addAttribute("notFoundMessage", "No student found with the provided student code");
            }
        } else {
            majors = majorRepository.findAll();
            model.addAttribute("majors", majors);
            students = studentRepository.findAll();
            model.addAttribute("students", students);
        }

        return "student-account-management";
    }

    @GetMapping(value = "/studentDetail/{studentCode}")
    public String showStudentDetail(@PathVariable Integer studentCode, Model model){
        Student student = studentRepository.findByStudentCode(studentCode);
        System.out.println(student.getUser().getEmail());
        model.addAttribute("student", student);
        return "student-detail";
    }



    @GetMapping(value = "/updateStudent/{studentCode}")
    public String updateStudent(@PathVariable Integer studentCode, Model model){
        Iterable<Major> majors;
        Major major;
        Student student = studentRepository.findByStudentCode(studentCode);
        model.addAttribute("student", student);
        majors = majorRepository.findAll();
        model.addAttribute("majors", majors);
        return "student-detail-edit";
    }


    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student-detail-edit"; // Return to the update form if there are validation errors
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

    @RequestMapping(value = "/insertTeacher")
    public String insertTeacher(@Valid Teacher teacher, BindingResult result) {
        if(result.hasErrors()){
            return "teacher-account-management";
        } else if (isDuplicateEntry(teacher.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "teacher-account-management";
        }

        User user = teacher.getUser();
        userRepository.save(user);
        teacherRepository.save(teacher);

        return "redirect:/admin/listTeacher";
    }

    private boolean isDuplicateEntry(String username) {
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
            return "teacher-account-management";
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

        return "teacher-account-management";
    }

    @GetMapping(value = "/teacherDetail/{teacherCode}")
    public String showTeacherDetail(@PathVariable Integer teacherCode, Model model){
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode);
        model.addAttribute("teacher", teacher);
        return "teacher-detail";
    }

    @GetMapping(value = "/updateTeacher/{teacherCode}")
    public String updateTeacher(@PathVariable Integer teacherCode, Model model){
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode);
        model.addAttribute("teacher", teacher);
        return "teacher-detail-edit";
    }

    @PostMapping(value = "/saveTeacher")
    public String saveTeacher(@Valid Teacher teacher, BindingResult result){
        if(result.hasErrors()){
            return "teacher-detail-edit";
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
















    // CRUD accountant

    @RequestMapping(value = "/insertAccountant")
    public String insertAccountant(@Valid Accountant accountant, BindingResult result) {
        if(result.hasErrors()){
            return "accountant-account-management";
        } else if (isDuplicateEntry(accountant.getUser().getUsername())) {
            result.rejectValue("user.username", "duplicate.key", "Username already exists");
            return "accountant-account-management";
        }

        User user = accountant.getUser();
        user.setRole("Accountant");
        accountant.setUser(user);
        userRepository.save(user);
        accountantRepository.save(accountant);

        return "redirect:/admin/listAccountant";
    }
    @GetMapping(value = "/listAccountant")
    public String showAllAccountant(Model model, @RequestParam(name = "accountantCode", required = false) Integer accountantCode,
                                  @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Accountant> accountants;
        Accountant accountant;
        if (showAll != null) {
            accountants = accountantRepository.findAll();
            model.addAttribute("accountants", accountants);
            return "accountant-account-management";
        }
        if(accountantCode != null){
            accountant = accountantRepository.findByAccountantCode(accountantCode);
            if(accountant != null){
                model.addAttribute("accountants", accountant);
            } else {
                model.addAttribute("notFoundMessage", "No teacher found with the provided teacher id");
            }
        } else {
            accountants = accountantRepository.findAll();
            model.addAttribute("accountants", accountants);
        }

        return "accountant-account-management";
    }
    @GetMapping(value = "/accountantDetail/{accountantCode}")
    public String showAccountantDetail(@PathVariable Integer accountantCode, Model model){
        Accountant accountant = accountantRepository.findByAccountantCode(accountantCode);
        model.addAttribute("accountant", accountant);
        return "accountant-detail";
    }
    @GetMapping(value = "/updateAccountant/{accountantCode}")
    public String updateAccountant(@PathVariable Integer accountantCode, Model model){
        Accountant accountant = accountantRepository.findByAccountantCode(accountantCode);
        model.addAttribute("accountant", accountant);
        return "accountant-detail-edit";
    }

    @PostMapping(value = "/saveAccountant")
    public String saveAccountant(@Valid Accountant accountant, BindingResult result){
        if(result.hasErrors()){
            return "accountant-detail-edit";
        }
        User user = accountant.getUser();
        userRepository.save(user);
        accountantRepository.save(accountant);
        return "redirect:/admin/listAccountant";
    }

    @RequestMapping(value = "/deleteAccountant/{accountantCode}")
    public String deleteAccountant(@PathVariable Integer accountantCode){
        Accountant accountant = accountantRepository.findByAccountantCode(accountantCode);
        User user = accountant.getUser();
        accountantRepository.delete(accountant);
        userRepository.delete(user);
        return "redirect:/admin/listAccountant";
    }











    // CRUD major
    @GetMapping("/addMajor")
    public String addMajor(Model model) {
        model.addAttribute("major", new Major());

        return "majorAdd";
    }
    @RequestMapping(value = "/insertMajor")
    public String insertMajor(@Valid Major major, BindingResult result) {
        if(result.hasErrors()){
            return "majorAdd";
        }else if (isDuplicateMajorName(major.getMajorName())) {
            result.rejectValue("majorName", "duplicate.key", "Major Name already exists");
            return "majorAdd";
        }
        System.out.println(major.getMajorName());
        majorRepository.save(major);

        return "redirect:/admin/listMajor";
    }

    private boolean isDuplicateMajorName(String majorName) {
        return majorRepository.existsByMajorName(majorName);
    }

    @GetMapping(value = "/listMajor")
    public String showAllMajor(Model model, @RequestParam(name = "majorId", required = false) Long majorId,
                                    @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Major> majors;
        Major major;
        if (showAll != null) {
            majors = majorRepository.findAll();
            model.addAttribute("majors", majors);
            return "majorList";
        }
        if(majorId != null){
            major = majorRepository.findByMajorId(majorId);
            if(major != null){
                model.addAttribute("majors", major);
            } else {
                model.addAttribute("notFoundMessage", "No teacher found with the provided teacher id");
            }
        } else {
            majors = majorRepository.findAll();
            model.addAttribute("majors", majors);
        }

        return "majorList";
    }
    @GetMapping("/majorDetail/{majorId}")
    public String showMajorDetail(@PathVariable Long majorId, Model model){
        Major major = majorRepository.findByMajorId(majorId);
        model.addAttribute("major", major);
        return "majorDetail";
    }
    @GetMapping(value = "/updateMajor/{majorId}")
    public String updateMajor(@PathVariable Long majorId, Model model){
        Major major = majorRepository.findByMajorId(majorId);
        model.addAttribute("major", major);
        return "majorUpdate";
    }

    @PostMapping(value = "/saveMajor")
    public String saveMajor(@Valid Major major, BindingResult result){
        if(result.hasErrors()){
            return "majorUpdate";
        }
        majorRepository.save(major);
        return "redirect:/admin/listMajor";
    }
    @RequestMapping(value = "/deleteMajor/{majorId}")
    public String deleteMajor(@PathVariable Long majorId){
        Major major = majorRepository.findByMajorId(majorId);
        majorRepository.delete(major);
        return "redirect:/admin/listMajor";
    }









    // CRUD semester
    @GetMapping("/addSemester")
    public String addSemester(Model model) {
        model.addAttribute("semester", new Semester());

        return "semesterAdd";
    }
    @RequestMapping(value = "/insertSemester")
    public String insertSemester(@Valid Semester semester, BindingResult result) {
        if(result.hasErrors()){
            return "semesterAdd";
        }else if (isDuplicateSemester(semester.getStartYear(), semester.getEndYear(), semester.getSemesterNum())) {
            result.rejectValue("startYear", "duplicate.key", "Semester already exists");
            return "semesterAdd";
        }
        semesterRepository.save(semester);
        return "redirect:/admin/listSemester";
    }
    private boolean isDuplicateSemester(Integer startYear, Integer endYear, Integer semesterNum) {
        return semesterRepository.existsByStartYearAndEndYearAndSemesterNum(startYear, endYear, semesterNum);

    }

    @GetMapping(value = "/listSemester")
    public String showAllSemester(Model model, @RequestParam(name = "semesterId", required = false) Long semesterId,
                               @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Semester> semesters;
        Semester semester;
        if (showAll != null) {
            semesters = semesterRepository.findAll();
            model.addAttribute("semesters", semesters);
            return "semesterList";
        }
        if(semesterId != null){
            semester = semesterRepository.findBySemesterId(semesterId);
            if(semester != null){
                model.addAttribute("semesters", semester);
            } else {
                model.addAttribute("notFoundMessage", "No teacher found with the provided teacher id");
            }
        } else {
            semesters = semesterRepository.findAll();
            model.addAttribute("semesters", semesters);
        }

        return "semesterList";
    }

    @GetMapping("/semesterDetail/{semesterId}")
    public String showSemesterDetail(@PathVariable Long semesterId, Model model){
        Semester semester = semesterRepository.findBySemesterId(semesterId);
        model.addAttribute("semester", semester);
        return "semesterDetail";
    }
    @GetMapping(value = "/updateSemester/{semesterId}")
    public String updateSemester(@PathVariable Long semesterId, Model model){
        Semester semester = semesterRepository.findBySemesterId(semesterId);
        model.addAttribute("semester", semester);
        return "semesterUpdate";
    }

    @PostMapping(value = "/saveSemester")
    public String saveSemester(@Valid Semester semester, BindingResult result){
        if(result.hasErrors()){
            return "semesterUpdate";
        }
        semesterRepository.save(semester);
        return "redirect:/admin/listSemester";
    }
    @RequestMapping(value = "/deleteSemester/{semesterId}")
    public String deleteSemester(@PathVariable Long semesterId){
        Semester semester = semesterRepository.findBySemesterId(semesterId);
        semesterRepository.delete(semester);
        return "redirect:/admin/listSemester";
    }




    @GetMapping("/addCourse")
    public String addCourse(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("course", new Course());
        model.addAttribute("majors", majorRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        if (redirectAttributes.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", redirectAttributes.getAttribute("errorMessage"));
        }
        return "courseAdd";
    }

    @RequestMapping(value = "/insertCourse")
    public String insertCourse(@Valid Course course, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            return "redirect:/admin/addCourse";
        }else if (isDuplicateCourse(course.getCourseCode(), course.getCourseName())) {
            System.out.println("yo");
            redirectAttributes.addFlashAttribute("errorMessage", "Course already exists");

            return "redirect:/admin/addCourse";
        }
        courseRepository.save(course);
        return "redirect:/admin/listCourse";
    }

    private boolean isDuplicateCourse(String courseCode, String courseName) {
        return courseRepository.existsByCourseCodeAndCourseName(courseCode, courseName);
    }


    @GetMapping(value = "/listCourse")
    public String showAllCourse(Model model, @RequestParam(name = "courseId", required = false) Long courseId,
                                  @RequestParam(name = "showAll", required = false) String showAll){
        Iterable<Course> courses;
        Course course;
        if (showAll != null) {
            courses = courseRepository.findAll();
            model.addAttribute("courses", courses);
            return "courseList";
        }
        if(courseId != null){
            course = courseRepository.findByCourseId(courseId);
            System.out.println("Iam");
            if(course != null){
                model.addAttribute("courses", course);
            } else {
                model.addAttribute("notFoundMessage", "No Course found with the provided course code");
            }
        } else {
            courses = courseRepository.findAll();
            model.addAttribute("courses", courses);
        }
        return "courseList";
    }

    @GetMapping(value = "/updateCourse/{courseId}")
    public String updateCourse(@PathVariable Long courseId, Model model){
        Course course = courseRepository.findByCourseId(courseId);
        model.addAttribute("course", course);
        model.addAttribute("majors", majorRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        return "courseUpdate";
    }

    @PostMapping(value = "/saveCourse")
    public String saveCourse(@Valid Course course, BindingResult result){
        if(result.hasErrors()){
            return "courseUpdate";
        }
        courseRepository.save(course);
        return "redirect:/admin/listCourse";
    }
    @RequestMapping(value = "/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable Long courseId){
        Course course = courseRepository.findByCourseId(courseId);
        courseRepository.delete(course);
        return "redirect:/admin/listCourse";
    }

}