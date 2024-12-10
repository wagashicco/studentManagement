package raisetech.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourses;
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
    return repository.search();
  }

  @GetMapping("/coursesList")
  public List<StudentCourses> searchStudentCoursesList() {

   List<StudentCourses> coursesResult = repository.searchCourses();
    //javaコースの受講情報のみを抽出

    return coursesResult.stream()
        .filter(course -> course.getCourseName().equals("java"))
        .toList();
  }
}

