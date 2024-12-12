package raisetech.studentManagement.data;

import lombok.Getter;
import lombok.Setter;
//lombok用
@Getter
@Setter
public class Student {

  private String id;
  private String name;
  private String furigana;
  private String nickname;
  private String email;
  private String city;
  private int age;
  private String gender;
  private String remarks;
  boolean isDeleted;

}
