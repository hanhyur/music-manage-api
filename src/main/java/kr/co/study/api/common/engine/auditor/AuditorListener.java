package kr.co.study.api.common.engine.auditor;

import kr.co.study.api.common.engine.annotation.entity.Table;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @since       2020.02.21
 * @author      lucas
 * @description auditor listener
 **********************************************************************************************************************/
@Slf4j
@Component
public class AuditorListener {

    @EventListener
    public void onEvent(AuditorEvent event) {
        recursive(event.getSource());
    }

    public void recursive(Object source){

        if(Objects.nonNull(source)){
            List<Field> fieldsOfClass      = Arrays.asList(Optional.ofNullable(source.getClass().getDeclaredFields()).orElse                (new Field[]{}));
            List<Field> fieldsOfSuperClass = Arrays.asList(Optional.ofNullable(source.getClass().getSuperclass().getDeclaredFields()).orElse(new Field[]{}));

            for (Field field : Stream.of(fieldsOfClass, fieldsOfSuperClass)
                    .flatMap(f -> f.stream())
                    .filter (f -> BooleanUtils.isFalse(f.getType().isEnum()))
                    .filter (f -> BooleanUtils.isFalse(f.getType().isPrimitive()))
                    .collect(Collectors.toList())) {

                ReflectionUtils.makeAccessible(field);
                setValueIfCreateByOrLastMddifiedBy    (field, source);
                setValueIfCreateDateOrLastMddifiedDate(field, source);
                executeRecursiveIfIncludedPackage     (field, source);
                executeRecursiveIfList                (field, source);
            }
        }
    }

    private void setValueIfCreateByOrLastMddifiedBy(Field field, Object source){
        if(ObjectUtils.anyNotNull(field.getAnnotation(CreatedBy.class), field.getAnnotation(LastModifiedBy.class))){
            ReflectionUtils.setField(field, source,  "admin");
        }
    }

    private void setValueIfCreateDateOrLastMddifiedDate(Field field, Object source){
        if(ObjectUtils.anyNotNull(field.getAnnotation(CreatedDate.class), field.getAnnotation(LastModifiedDate.class))){
            ReflectionUtils.setField(field, source, LocalDateTime.now());
        }
    }

    @SneakyThrows
    private void executeRecursiveIfIncludedPackage(Field field, Object source){
        Object object = field.get(source);
        if(Objects.nonNull(object)){
            if(ObjectUtils.allNotNull(field.getType().getAnnotation(Table.class))) {
                recursive(field.get(source));
            }
        }
    }

    @SneakyThrows
    private void executeRecursiveIfList(Field field, Object source){
        Object object = field.get(source);
        if(Objects.nonNull(object)){
            if(Objects.equals(List.class, field.getType())){
                List fieldsOfList = List.class.cast(object);
                for (Object fieldOfList : fieldsOfList) {
                    recursive(fieldOfList);
                }
            }
        }
    }
}