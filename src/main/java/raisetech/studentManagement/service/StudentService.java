package raisetech.studentManagement.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録：更新処理を行います。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService (StudentRepository repository, StudentConverter converter) {
        this.repository = repository;
        this.converter = converter;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行うので条件指定は行いません。
   *
   * @return 受講生詳細一覧（全件）
   */
  public List<StudentDetail> searchStudentList() {
     List<Student> studentList = repository.search();
     List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }
  /**
   * 受講生詳細の一覧検索です。
   * IDに紐づく受講生情報を取得したあと、受講生に紐づくコース情報を取得して設定します。
   *
   * @param id 受講生
   * @return 受講生詳細情報
   */
  public StudentDetail searchStudent(String  id){
    //repositoryのsearchStudentを呼ぶ。serviceのメソッドではない。
    Student student = repository.searchStudent(id);
   List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
   return new StudentDetail(student, studentCourse);
  }

  /**
   * 受講詳細の登録を行います。
   * 受講生とコース情報を個別に登録し、コース情報の設定では、受講生を紐づける値（受講生ＩＤ）や日付（開始日・終了日）の設定を行う。
   * @param studentDetail 受講生詳細
   * @return 登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentCourse -> {
      initStudentsCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);
    });
    return studentDetail;
  }

  /**
   * 受講生コーヅ情報を登録する際の初期情報を設定する。
   * @param studentCourse 受講生コース情報
   * @param student 受講生
   */
  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    studentCourse.setStudentId(student.getId());
    studentCourse.setStartDate(new Date());
    Calendar endDateOfCalendar = Calendar.getInstance();
    endDateOfCalendar.setTime(studentCourse.getStartDate()); // 1年を追加
    endDateOfCalendar.add(Calendar.YEAR, 1);
    studentCourse.setEndDate(endDateOfCalendar.getTime());
  }

  /**
   * 受講生詳細の更新を行います。
   * 受講生とコース情報をそれぞれ更新します。
   * @param studentDetail 受講生詳細
   */
  @Transactional
  public void  updateStudent(StudentDetail studentDetail){
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
  }
}

