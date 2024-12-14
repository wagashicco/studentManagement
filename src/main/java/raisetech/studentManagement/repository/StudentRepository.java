package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;


@Mapper
public interface StudentRepository {

  //DBから情報の取得
  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM courses")
  List<StudentsCourses> searchCourses();

//受講生の登録
  @Insert("INSERT INTO students(name,furigana,nickname,email,city,age,gender,remarks,is_deleted)"
      + "VALUES(#{name}, #{furigana}, #{nickname}, #{email}, #{city}, #{age}, #{gender}, #{remarks}, false)")
  @Options(useGeneratedKeys = true,keyProperty = "id")
  void resisterStudent(Student student);

  //コースの登録
  @Insert("INSERT INTO courses(student_id, course_name, start_date, end_date)"
  + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{finishDate})")
  @Options(useGeneratedKeys = true,keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void updateStudent(String name, int age);

  @Delete("DELETE FROM student WHERE name =#{name}")
  void deleteStudent(String name);

}
