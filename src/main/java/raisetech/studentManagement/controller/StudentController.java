package raisetech.studentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {

    this.service = service;
  }


  @GetMapping("/studentList")
  public List<Student> seachStudentList() {

    return service.seachStudentList();
  }

  @GetMapping("/coursesList")
  public List<StudentCourse> searchStudentCoursesList() {
    return service.searchStudentCoursesList();
  }

}
