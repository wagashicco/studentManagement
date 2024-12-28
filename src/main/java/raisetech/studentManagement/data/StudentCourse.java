package raisetech.studentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


/**
 * 受講生に紐付くコース情報を扱うクラス
 */
@Schema(description = "受講生コース情報")
@Getter
@Setter
@Validated
public class StudentCourse {
  /**受講生のもつ一意のID  */
  @Schema(description = "受講生ID", example = "１")
  private String studentId;

  /**コースの一意のID */
  @Schema(description = "コースを識別するためのID", example = "１")
  private String id;

  /**コース名 */
  @Schema(description = "コース名", example = "Javaコース")
  @NotBlank
  private String courseName;

  /**受講開始日  */
  @Schema(description = "受講を開始した日", example = "2024-01-09T15:00:00.000+00:00")
  private Date startDate;

  /**受講終了日  */
  @Schema(description = "受講日から1年後の終了予定日", example = "2024-03-09T15:00:00.000+00:00")
  private Date endDate;

}
