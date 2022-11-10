package kr.co.study.api.common.engine.auditor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @since       2020.02.20
 * @author      lucas
 * @description auditor event
 **********************************************************************************************************************/
@Data
@AllArgsConstructor
public class AuditorEvent<T> {
    private T source;
}