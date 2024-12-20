package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   *
   * @return 受講生一覧（全件）
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * 受講生のコース情報の全件検索を主ないます。
   * @return 受講生のコース情報（全件）
   */
  @Select("SELECT * FROM courses")
  List<StudentsCourses> searchStudentsCoursesList();

  /**
   * 受講生の検索を行います。
   *
   * @param id　受講生ID
   * @return 受講生
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   * @param studentId　受講生ID
   * @return 受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);

//受講生の登録
  @Insert("INSERT INTO students(name,furigana,nickname,email,city,age,gender,remarks,is_deleted)"
      + "VALUES(#{name}, #{furigana}, #{nickname}, #{email}, #{city}, #{age}, #{gender}, #{remarks}, false)")
  @Options(useGeneratedKeys = true,keyProperty = "id")
  void resisterStudent(Student student);

  //コースの登録
  @Insert("INSERT INTO courses(student_id, course_name, start_date, end_date)"
  + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
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
