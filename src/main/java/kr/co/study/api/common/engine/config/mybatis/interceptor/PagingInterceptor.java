package kr.co.study.api.common.engine.config.mybatis.interceptor;

import com.google.common.collect.ImmutableList;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.engine.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Properties;

/**
 * @since       2020.02.19
 * @author      lucas
 * @description paging interceptor
 **********************************************************************************************************************/
@Component
@Intercepts({ @Signature(method="handleResultSets", type=ResultSetHandler.class, args= Statement.class) })
public class PagingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        DefaultResultSetHandler handler = DefaultResultSetHandler.class.cast(invocation.getTarget());
        Field field   = ReflectionUtils.findField(DefaultResultSetHandler.class, StringUtils.uncapitalize(MappedStatement.class.getSimpleName()));

        ReflectionUtils.makeAccessible(field);
        MappedStatement statement = MappedStatement.class.cast(field.get(handler));
        Object result    = invocation.proceed();

        return isPaging(statement) && isEmpty(result) ? ImmutableList.of(Paging.builder()
                .number  (Constant.Integer.ZERO)
                .size    (Constant.Integer.TEN)
                .total   (Constant.Integer.ZERO)
                .contents(Collections.emptyList())
                .build()) : result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private boolean isPaging(MappedStatement statement) {
        return Optional.ofNullable(statement.getResultMaps()).orElse(Collections.emptyList()).stream()
                .anyMatch(m -> Paging.class == m.getType());
    }

    private boolean isEmpty(Object result) {
        return CollectionUtils.isEmpty(Collection.class.cast(result));
    }
}
