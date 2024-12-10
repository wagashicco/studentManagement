package raisetech.studentManagement;

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

  /*　↓　ランボックで自動生成する部分
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

   */
}
