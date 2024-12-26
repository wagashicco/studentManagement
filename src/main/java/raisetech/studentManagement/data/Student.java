package raisetech.studentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 *  受講生を扱うクラス<br>
 * ーーー　フィールド　ーーー<br>
 * （id）   受講生の一意のID<br>
 * （name）     受講生の名前 <br>
 * （furigana） 受講生のフリガナ <br>
 * （nickname）受講生のニックネーム <br>
 * （email） 受講生の email <br>
 * （city）受講生の在住都道府県 <br>
 * （age）受講生の年齢 <br>
 * （gender） 受講生の性別 <br>
 * （remarks）  受講生に関する備考欄 <br>
 * （isDeleted）　受講生のキャンセンルフラグ（論理削除とする）<br>
 */
@Schema(description = "受講生")
@Getter
@Setter
@Validated
public class Student {

  /** 受講生ID　*/
  @Pattern(regexp = "\\d+")
  private String id;
  /**受講生の名前 */
  @NotNull
  private String name;
  /**受講生のフリガナ */
  @NotNull
  private String furigana;
  /**受講生のニックネーム */
  @NotBlank
  private String nickname;
  /**受講生の email */
  @Email
  private String email;
  /**受講生の在住都道府県 */
  @NotBlank
  private String city;
  /**受講生の年齢 */
  @NotNull
  private int age;
  /**受講生の性別 */
  @NotBlank
  private String gender;
  /**受講生に関する備考欄 */
  private String remarks;
  /**受講生のキャンセンルフラグ（論理削除とする） */
  @NotNull
  boolean isDeleted;

}
