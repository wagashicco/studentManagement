package raisetech.studentManagement.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService (StudentRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/studentList")
  public List<Student> searchStudentList() {
    return repository.search();
  }

  @GetMapping("/coursesList")
  public List<StudentsCourses> searchStudentCoursesList() {
   return repository.searchCourses();
  }
  //受講生情報登録 Serviceで登録、削除、更新するものはつける必要がある DBの手前にあるクラスで管理する
  @Transactional
  public void  registerStudent(StudentDetail studentDetail) {
    repository.resisterStudent(studentDetail.getStudent());
    //TODO:コース情報を登録　

    //  @Transactional　付けないと↓の失敗したときに、上までデータを戻せる。管理されないとロールバックせず、どちらかだけ不正に登録されてしまったりする。
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      //コースの他の値を設定
      studentsCourses.setStudentId(studentDetail.getStudent().getId());
      studentsCourses.setStartDate(new Date());
      //終了日を計算するための開始日
      Date startDate = new Date();
      Calendar finishCalender = Calendar.getInstance();
      finishCalender.setTime(startDate); // 1年を追加
      finishCalender.add(Calendar.YEAR, 1);
      Date finishDate = new Date();
      finishDate = finishCalender.getTime();
      studentsCourses.setFinishDate(finishDate);

      repository.registerStudentsCourses(studentsCourses);
    }
  }
}

