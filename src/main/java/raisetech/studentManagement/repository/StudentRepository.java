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
  List<StudentsCourses> searchStudentsCoursesList();

//指定idの生徒情報を取得  コース情報の単体取得
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);
  @Select("SELECT * FROM courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);


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

  //受講生のUPDATE　
  @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickname}, email = #{email}, "
      + "city = #{city}, age = #{age}, gender = #{gender}, remarks = #{remarks}, is_deleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  //コースのUPDATE
  @Update("UPDATE courses SET course_name = #{courseName}"
      + "WHERE id = #{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);

}
