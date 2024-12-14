package raisetech.studentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

@Getter
@Setter

public class StudentDetail {

  //HTMLのワードと紐づく
  private Student student;
  private List<StudentsCourses> studentsCourses;

}
