package raisetech.studentManagement.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *テスト用のエラーを扱います。
 * 各エラーに対応したメッセージを表示させる時に使用します。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleTestException(Exception ex){
    System.out.println("handleTestExceptionを実行中");
    //ログ出力 BAD_REQUESTは送信結果400がでる
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  //Validationによる入力チェックで起きたエラーの詳細を表示する。（オブジェクト名、フィールド名を表示）
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return new ResponseEntity<>("Validation error: " + e.getBindingResult().getFieldError(),HttpStatus.BAD_REQUEST);
  }
}
