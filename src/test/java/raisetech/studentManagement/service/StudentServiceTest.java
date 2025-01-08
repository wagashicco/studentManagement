package raisetech.studentManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
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

    //事前準備
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);
    List<StudentDetail> expected = new ArrayList<>();
    //実行
    List<StudentDetail> actual = sut.searchStudentList();
    //検証
    assertEquals(expected, actual);
    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  // searchStudent のテスト
  @Test
  void 受講生をIDで検索_repositoryが受講生のID検索とIDに紐付くコース情報を適切に呼出せている事() {
    Student student = new Student("16","小俣恵利佳","オマタエリカ","和菓子っこ","wagashicco@hotmail.co.jp","神奈川県",40,"female","",false);

    List<StudentCourse> courseList = new ArrayList<>();
      StudentDetail expected = new StudentDetail(student, courseList);

      when(repository.searchStudent(student.getId())).thenReturn(student);
      when(repository.searchStudentCourse(student.getId())).thenReturn(courseList);

      //期待される値（返り値）と実行
      StudentDetail actual = sut.searchStudent(student.getId());
      //検証　
      assertEquals(expected, actual);
      assertEquals(student.getId(), actual.getStudent().getId(), "受講生IDが正しく設定されていません");
      for (StudentCourse sc : actual.getStudentCourseList()) {
        assertEquals(student.getId(), sc.getStudentId(), "コース情報に受講生IDが正しく設定されていません");
      }
      verify(repository, times(1)).searchStudent(student.getId());
      verify(repository, times(1)).searchStudentCourse(student.getId());
    }

  // registerStudent　
  @Test
  void 受講生とコース情報を個別に作成_コース情報の設定で受講生に紐付けることを検証する() {
    Student student = new Student("16","小俣恵利佳","オマタエリカ","和菓子っこ","wagashicco@hotmail.co.jp","神奈川県",39,"female","",false);

    List<StudentCourse> courseList = new ArrayList<>();
    StudentDetail expected = new StudentDetail(student, courseList);
    StudentDetail studentDetail = new StudentDetail(student, courseList);
    // リポジトリメソッドのスタブ設定
    doNothing().when(repository).registerStudent(student);
    //期待される値（返り値）と実行
    StudentDetail actual = sut.registerStudent(studentDetail);
    //期待される値と実際の値の比較
    assertEquals(expected, actual);
    //リポジトリメソッドの呼び出し回数の検証
    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(courseList.size())).registerStudentCourse(any(StudentCourse.class));
    // コース情報に受講生IDが正しく設定されているかを検証
     for (StudentCourse sc : actual.getStudentCourseList()) {
       assertEquals(student.getId(), sc.getStudentId(), "コース情報に受講生IDが正しく設定されていません");
     }
  }

  // updateStudent　のテスト
  @Test
  void 受講生とコース情報の更新_それぞれ更新すること() {
    Student student = new Student("16","小俣恵利佳","オマタエリカ","和菓子っこ","wagashicco@hotmail.co.jp","神奈川県",39,"female","",false);

    List<StudentCourse> courseList = new ArrayList<>();
    StudentCourse course = new StudentCourse();
    course.setStudentId(student.getId()); // コース情報に受講生IDを設定
    course.setCourseName("Ｊａｖａコース"); // 旧コース名を設定
    courseList.add(course);
    StudentDetail studentDetail = new StudentDetail(student, courseList);

    // 更新後のデータを設定
    student.setName("更新後の名前"); // 新しい名前を設定
    course.setCourseName("更新後のコース名"); // 新しいコース名を設定

    // メソッドの実行
    sut.updateStudent(studentDetail);

    // リポジトリメソッドの呼び出し回数の検証
    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(courseList.size())).updateStudentCourse(any(StudentCourse.class));

    // Studentオブジェクトのフィールドが正しく更新されているかを確認
    assertEquals("更新後の名前", student.getName(), "受講生の名前が正しく更新されていません");

    // StudentCourseオブジェクトのフィールドが正しく更新されているかを確認
    for (StudentCourse sc : courseList) {
      assertEquals("更新後のコース名", sc.getCourseName(), "コース名が正しく更新されていません");
    }
  }
}
