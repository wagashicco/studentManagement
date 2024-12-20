package raisetech.studentManagement.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
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
   * @return 受講生一覧（全件） 
   */
  public List<StudentDetail> searchStudentList() {
     List<Student> studentList = repository.search();
     List<StudentsCourses> studentsCoursesList = repository.searchStudentsCoursesList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }
  /**
   * 受講生検索です。
   * IDに紐づく受講生情報を取得したあと、受講生に紐づくコース情報を取得して設定します。
   *
   * @param id 受講生
   * @return 受講生詳細情報
   */
  public StudentDetail searchStudent(String  id){
    Student student = repository.searchStudent(id);
   List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
   return new StudentDetail(student,studentsCourses);
  }
  
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    repository.resisterStudent(studentDetail.getStudent());

    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      //コースの他の値を設定
      studentsCourses.setStudentId(studentDetail.getStudent().getId());
      studentsCourses.setStartDate(new Date());
      //終了日を計算するためのコース開始日
      Date startDate = new Date();
      Calendar finishCalender = Calendar.getInstance();
      finishCalender.setTime(startDate); // 1年を追加
      finishCalender.add(Calendar.YEAR, 1);
      Date finishDate = finishCalender.getTime();
      studentsCourses.setEndDate(finishDate);

      repository.registerStudentsCourses(studentsCourses);
    }
    return studentDetail;
  }
  @Transactional
  public void  updateStudent(StudentDetail studentDetail){
    repository.updateStudent(studentDetail.getStudent());
    for(StudentsCourses studentsCourses:studentDetail.getStudentsCourses()){
      studentsCourses.setStudentId(studentDetail.getStudent().getId());
      repository.updateStudentsCourses(studentsCourses);
    }
  }
}

