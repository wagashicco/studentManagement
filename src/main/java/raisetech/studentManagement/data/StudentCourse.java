package raisetech.studentManagement.data;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  private String studentId;
  private String courseId;
  private String courseName;
  private Date startDate;
  private Date finishDate;


}
