package raisetech.studentManagement.data;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class Student {

  /** 受講生ID　*/
  private String id;
  /**受講生の名前 */
  private String name;
  /**受講生のフリガナ */
  private String furigana;
  /**受講生のニックネーム */
  private String nickname;
  /**受講生の email */
  private String email;
  /**受講生の在住都道府県 */
  private String city;
  /**受講生の年齢 */
  private int age;
  /**受講生の性別 */
  private String gender;
  /**受講生に関する備考欄 */
  private String remarks;
  /**受講生のキャンセンルフラグ（論理削除とする） */
  boolean isDeleted;

}
