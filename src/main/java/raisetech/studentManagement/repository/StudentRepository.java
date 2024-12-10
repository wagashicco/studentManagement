package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;

@Mapper
public interface StudentRepository {
//DBから情報の取得
  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM courses")
  List<StudentCourse> searchCourses();

}
