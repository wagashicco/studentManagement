package raisetech.studentManagement.data;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourses {

  private String studentId;
  private String id;
  private String courseName;
  private Date startDate;
  private Date finishDate;

}
