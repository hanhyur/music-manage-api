package kr.co.study.api.common.engine.config.mybatis.handler;

import kr.co.study.api.common.engine.auditor.AuditorEvent;
import kr.co.study.api.common.engine.helper.model.SnakeCaseJsonHelper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @since       2020.02.18
 * @author      lucas
 * @description json handler
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class JsonHandler extends BaseTypeHandler<Object> {

    private final ApplicationContext applicationContext;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        applicationContext.publishEvent(new AuditorEvent(parameter));
        ps.setObject(i, SnakeCaseJsonHelper.toJson(parameter));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object result = rs.getObject(columnName);
        return Objects.isNull(result) ? null : SnakeCaseJsonHelper.toJson(result);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object result = rs.getObject(columnIndex);
        return Objects.isNull(result) ? null : SnakeCaseJsonHelper.toJson(result);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object result = cs.getObject(columnIndex);
        return Objects.isNull(result) ? null : SnakeCaseJsonHelper.toJson(result);
    }
}