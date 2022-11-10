package kr.co.study.api.common.base.entity;

import kr.co.study.api.common.engine.annotation.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**   
 * @since       2020.02.21
 * @author      lucas
 * @description base
 **********************************************************************************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base {

	@Description("등록자")
	@CreatedBy
	private String createdBy;

	@Description("수정자")
	@LastModifiedBy
	private String updatedBy;

	@Description("등록일시")
	@CreatedDate
	private LocalDateTime createdAt;

	@Description("수정일시")
	@LastModifiedDate
	private LocalDateTime updatedAt;

}
