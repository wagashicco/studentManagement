package raisetech.studentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourses;
import raisetech.studentManagement.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> allOfStudents,
      List<StudentCourses> allOfStudentCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : allOfStudents) {
      StudentDetail studentDetail = new StudentDetail();
      //受講生1件分の情報を入れる
      studentDetail.setStudent(student);
//受講情報のリストを用意　受講生IDから受講コース情報が一致するコースをリストに加える

      List<StudentCourses> convertStudentCourses = allOfStudentCourses.stream()
          .filter(studentCourse -> student.getId().equals(studentCourse.getStudentId()))
          .collect(Collectors.toList());
      //受講生1件分のオブジェクトに全受講コースのリストをセットする

      studentDetail.setStudentCourses(convertStudentCourses);
      //全受講生がもつ全受講コース情報をリストにする
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }



}
