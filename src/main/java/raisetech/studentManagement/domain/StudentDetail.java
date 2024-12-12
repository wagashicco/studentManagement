package raisetech.studentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourses;

@Getter
@Setter

public class StudentDetail {

  private Student student;
  private List<StudentCourses> studentCourses;

}
