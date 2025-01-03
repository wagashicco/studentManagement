package raisetech.studentManagement.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;

@SuppressWarnings("NonAsciiCharacters")//日本語メソッドの警告を消してくれる
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;
  //事前準備  (全部の検証で使いたい場合、フィールドにする）
  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せている事() {

    //事前準備　検証Bで使う
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);
    List<StudentDetail> expected = new ArrayList<>();
    //実行
    List<StudentDetail> actual = sut.searchStudentList();
    //検証
    Assertions.assertEquals(expected, actual);
    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  // searchStudent のテスト
  @Test
  void 受講生をIDで検索_repositoryが受講生のID検索とIDに紐付くコース情報を適切に呼出せている事() {
      Student student = new Student();
      List<StudentCourse> courseList = new ArrayList<>();
      StudentDetail expected = new StudentDetail(student, courseList);

      when(repository.searchStudent(student.getId())).thenReturn(student);
      when(repository.searchStudentCourse(student.getId())).thenReturn(courseList);

      //期待される値（返り値）と実行
      StudentDetail actual = sut.searchStudent(student.getId());
      //検証　
      Assertions.assertEquals(expected, actual);
      verify(repository, times(1)).searchStudent(student.getId());
      verify(repository, times(1)).searchStudentCourse(student.getId());
    }

  // registerStudent　
  @Test
  void 受講生とコース情報を個別に作成_コース情報の設定で受講生に紐付けること() {
    Student student = new Student();
    List<StudentCourse> courseList = new ArrayList<>();
    StudentDetail expected = new StudentDetail(student, courseList);
    StudentDetail studentDetail = new StudentDetail(student, courseList);

    doNothing().when(repository).resisterStudent(student);
    //期待される値（返り値）と実行
    StudentDetail actual = sut.registerStudent(studentDetail);

    Assertions.assertEquals(expected, actual);
    verify(repository, times(1)).resisterStudent(student);
    verify(repository, times(courseList.size())).registerStudentCourse(any(StudentCourse.class));

  }

  // updateStudent　のテスト
  @Test
  void 受講生とコース情報の更新_それぞれ更新すること() {
    Student student = new Student();
    List<StudentCourse> courseList = new ArrayList<>();
    StudentDetail studentDetail = new StudentDetail(student, courseList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(courseList.size())).updateStudentCourse(any(StudentCourse.class));
  }
}
