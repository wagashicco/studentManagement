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

  //Validationによる入力チェックで起きたエラーの詳細を表示する。（オブジェクト名、フィールド名を表示）
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return new ResponseEntity<>("Validation error: " + e.getBindingResult().getFieldError(),HttpStatus.BAD_REQUEST);
  }
}
