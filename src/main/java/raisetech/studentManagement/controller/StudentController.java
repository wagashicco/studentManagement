package raisetech.studentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行う　REST　APIとして実行されるControllerです。
 */
@Validated
@RestController
public class StudentController {

  private final StudentService service;

  /**
   * コンストラクタ
   *
   * @param service 受講生サービス
   */
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細の一覧（全件）
   */
  @Operation(summary = "受講生一覧検索",description = "コース情報も含めた受講生全員分の情報を一覧表示する")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   * @param id　受講生ID
   * @return 受講生
   */
  @Operation(summary = "受講生ID検索",description = "IDで受講生の情報を検索します")
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @NotBlank @Pattern(regexp = "^\\d+s") String id) {
    return service.searchStudent(id);
  }

  /***
   * 受講生書斎の登録を行います。
   * @param studentDetail 受講生詳細
   * @return 実行結果（登録しようとした受講生詳細情報）
   */
  @Operation(summary = "受講生登録", description = "受講生を新規登録します")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。
   * キャンセルフラグの更新もここで行います。（論理削除）
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "更新",description = "コース情報も含めた受講生情報を更新を行います（キャンセルもここで行う）")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功");
  }

  //エラーを強制的に起こします
  @Operation(summary = "テスト用の強制エラー",description = "検証のためにエラーを強制的に起こす事ができます")
  @GetMapping("/forcedError")
  public void forcedError() throws Exception{
    throw new Exception("エラーを発生させてます");
  }
}
