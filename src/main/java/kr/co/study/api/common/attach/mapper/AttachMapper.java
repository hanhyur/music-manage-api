package kr.co.study.api.common.attach.mapper;

import kr.co.study.api.common.attach.entity.Attach;
import kr.co.study.api.common.attach.form.AttachForm.Request;
import kr.co.study.api.common.attach.form.AttachForm.Response;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**   
 * @since       2020.02.25
 * @author      lucas
 * @description attach mapper
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface AttachMapper {

    AttachMapper mapper = Mappers.getMapper(AttachMapper.class);

    Attach           toAttach (Request.Remove form);
    Response.FindOne toFindOne(Attach entity);
}