package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourses;


@Mapper
public interface StudentRepository {

  //DBから情報の取得
  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM courses")
  List<StudentCourses> searchCourses();

  @Insert("INSERT students (name,furigana,nickname,email,city,age,gender,remarks) VALUES(#{name},#{furigana},#{nickname},#{email},#{city},#{age},#{gender},#{remarks})")
  void resisterStudent(String name,String furigana, String nickname, String email,String city,
      int age, String gender, String remarks);

  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void updateStudent(String name, int age);

  @Delete("DELETE FROM student WHERE name =#{name}")
  void deleteStudent(String name);

}
