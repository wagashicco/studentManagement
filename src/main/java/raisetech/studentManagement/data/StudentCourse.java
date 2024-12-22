package raisetech.studentManagement.data;

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
@Getter
@Setter
public class StudentCourse {
  /**受講生のもつ一意のID  */
  private String studentId;
  /**コースの一意のID */
  private String id;
  /**コース名 */
  private String courseName;
  /**受講開始日  */
  private Date startDate;
  /**受講終了日  */
  private Date endDate;

}
