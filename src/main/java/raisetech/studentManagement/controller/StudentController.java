package raisetech.studentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;

} //受講生に紐付けた情報を取得
  @GetMapping("/studentList")
  public List<StudentDetail> seachStudentList() {
    //受講生と受講コース情報をそれぞれ全件取得
    List<Student> allOfStudents = service.seachStudentList();
    List<StudentCourses> allOfStudentCourses = service.searchStudentCoursesList();

    //受講生と受講コース情報を持ったreturn用のオブジェクトリストを返す
    return converter.convertStudentDetails(allOfStudents, allOfStudentCourses);
  }

  @GetMapping("/coursesList")
  public List<StudentCourses> searchStudentCoursesList() {
    return service.searchStudentCoursesList();
  }

}
