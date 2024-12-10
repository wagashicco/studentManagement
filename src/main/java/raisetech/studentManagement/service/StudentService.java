package raisetech.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService (StudentRepository repository) {

    this.repository = repository;
  }
  @GetMapping("/studentList")
  public List<Student> seachStudentList() {
    //検索処理
    List<Student> studentResult = repository.search();

    //絞り込み　年齢が30代の人を抽出
    //抽出したリストをコントローラーに返す
    return studentResult.stream()
        .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
        .toList();
  }

  @GetMapping("/coursesList")
  public List<StudentCourse> searchStudentCoursesList() {

   List<StudentCourse> coursesResult = repository.searchCourses();
    //javaコースの受講情報のみを抽出

    return coursesResult.stream()
        .filter(course -> course.getCourseName().equals("java"))
        .toList();
  }
}

