package raisetech.studentManagement.controller;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;
import raisetech.studentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;
  private StudentRepository repository;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter, StudentRepository repository) {
    this.service = service;
    this.converter = converter;
    this.repository = repository;

  }
  //受講生に紐付けた情報を取得
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(Model model) {
    //受講生と受講コース情報をそれぞれ全件取得
    List<Student> allOfStudents = service.searchStudentList();
    List<StudentsCourses> allOfStudentCourses = service.searchStudentCoursesList();

    model.addAttribute("studentList", converter.convertStudentDetails(allOfStudents,allOfStudentCourses));
    //受講生と受講コース情報を持ったreturn用のオブジェクトリストを返す
    return converter.convertStudentDetails(allOfStudents, allOfStudentCourses);
    }

  @GetMapping("/coursesList")
  public List<StudentsCourses> getStudentCoursesList() {
    return service.searchStudentCoursesList();
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model){
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourses(Arrays.asList(new StudentsCourses()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }
//新規登録
  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      //エラー
      return "resisterStudent";
    }
    /* 自分で考えて実装した受講生登録　　※正解はServiceクラスに実装する
    repository.resisterStudent(studentDetail.getStudent().getName(),
        studentDetail.getStudent().getFurigana(),studentDetail.getStudent().getNickname(),
        studentDetail.getStudent().getEmail(),studentDetail.getStudent().getCity(),studentDetail.getStudent().getAge(),
        studentDetail.getStudent().getGender(),studentDetail.getStudent().getRemarks());
        */
    System.out.println(
        studentDetail.getStudent().getName() + "さんが受講生として新規登録されました");

//正解
service.registerStudent(studentDetail);
    return "redirect:/studentList";
    //新規受講生情報を登録する処理の実装
    //コース情報も登録できるように実装

  }
  @PatchMapping("/student")
  public void updateStudentName(String name, int age) {
    repository.updateStudent(name, age);
  }

  @DeleteMapping("/student")
  public void deleteStudent(String name) {
    repository.deleteStudent(name);
  }
}
