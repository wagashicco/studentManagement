package raisetech.studentManagement.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

  //HTMLのワードと紐づく
  private Student student;
  private List<StudentsCourses> studentsCourses;

}
