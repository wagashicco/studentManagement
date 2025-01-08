package raisetech.studentManagement.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 *  受講生を扱うクラス
 */
@Schema(description = "受講生情報")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Student {

  /** 受講生ID　*/
  @Schema(description = "受講生を識別するための一意のID", example = "1")
  @Setter(AccessLevel.NONE)
  @Pattern(regexp = "\\d+")
  private String id;

  /**受講生の名前 */
  @Schema(description = "フルネーム", example = "小俣恵利佳")
  @NotNull
  private String name;

  /**受講生のフリガナ */
  @Schema(description = "フリガナ", example = "オマタエリカ")
  @NotNull
  private String furigana;

  /**受講生のニックネーム */
  @Schema(description = "ニックネーム", example = "和菓子っこ")
  @NotBlank
  private String nickname;

  /**受講生の email */
  @Schema(description = "メールアドレス", example = "xxx@co.jp")
  @Email
  private String email;

  /**受講生の在住都道府県 */
  @Schema(description = "住所", example = "神奈川県")
  @NotBlank
  private String city;

  /**受講生の年齢 */
  @Schema(description = "年齢", example = "３９")
  @NotNull
  private int age;

  /**受講生の性別 */
  @Schema(description = "性別", example = "女性")
  @NotBlank
  private String gender;

  /**受講生に関する備考欄 */
  @Schema(description = "備考欄", example = "Java Silver資格　勉強中")
  private String remarks;

  /**受講生のキャンセンルフラグ（論理削除とする） */
  @Schema(description = "論理削除", example = "falseかtrue")
  @JsonProperty("isDeleted")//APIドキュメント等の他ツールと互換維持のため（Deleteになる）
  @NotNull
  boolean isDeleted;

}
