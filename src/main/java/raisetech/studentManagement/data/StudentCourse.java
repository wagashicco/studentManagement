package raisetech.studentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生に紐付くコース情報を扱うクラス<br>
 * ーーーフィールド情報ーーー<br>
 *（studentId）   受講生のもつ一意のID<br>
 *（id）　        コースの一意のID<br>
 *（courseName）  コース名<br>
 *（startDate）     受講開始日<br>
 *（endDate）       受講終了日<br>
 */

@Schema(description = "受講生コース情報")
@Getter
@Setter
public class StudentCourse {
  /**受講生のもつ一意のID  */
  //新規登録の際はnullになるのが正しい
  private String studentId;
  /**コースの一意のID */
  //新規登録の際はnullになるのが正しい
  private String id;
  /**コース名 */
  @NotBlank
  private String courseName;
  /**受講開始日  */
  //新規登録の際はnullになるのが正しい
  private Date startDate;
  /**受講終了日  */
  //新規登録の際はnullになるのが正しい
  private Date endDate;

}
