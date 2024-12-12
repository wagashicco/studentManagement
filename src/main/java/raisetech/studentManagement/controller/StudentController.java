package raisetech.studentManagement.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;

  } //受講生に紐付けた情報を取得

  @GetMapping("/studentList")
  public String searchStudentList(Model model) {
    //受講生と受講コース情報をそれぞれ全件取得
    List<Student> allOfStudents = service.searchStudentList();
    List<StudentCourses> allOfStudentCourses = service.searchStudentCoursesList();

    //受講生と受講コース情報を持ったreturn用のオブジェクトリストを返す
    // return converter.convertStudentDetails(allOfStudents, allOfStudentCourses);
    model.addAttribute("studentList",converter.convertStudentDetails(allOfStudents,allOfStudentCourses));
    //HTMLの中の表示するリストの名前と一致させる
      return "studentList";

    }

  @GetMapping("/coursesList")
  public List<StudentCourses> searchStudentCoursesList() {

    return service.searchStudentCoursesList();
  }
}

