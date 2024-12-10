package raisetech.studentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {
//DBから情報の取得
  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM courses")
  List<StudentCourse> searchCourses();

  /*  23　の講義で使ってないから一回消すと
  //DBへ設定
  @Insert("INSERT student VALUES(#{name}, #{age})")
  void registerStudent(String name, int age);

  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void updateStudent(String name, int age);

  @Delete("DELETE FROM student WHERE name =#{name}")
  void deleteStudent(String name);

   */
}
