package kr.co.study.api.common.engine.validator.form.date;

import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @since       2019.01.30
 * @author      lucas
 * @description date validator
 **********************************************************************************************************************/
public class DateValidator {

    /**
     * @param  startDt 시작일
     * @param  endDt   종료일
     * @return         시작/종료일이 모두 널이면 true
     *                 시작/종료일이 하나라도 널이면 false
     *                 시작/종료일이 모두 널이 아니고 startDt 가 endDt 이전일이면 true 아니면 false
     */
    public static boolean isValidDateRange(LocalDate startDt, LocalDate endDt) {
        if(ObjectUtils.allNotNull(startDt, endDt)) { return startDt.equals(endDt) || startDt.isBefore(endDt); }
        if(ObjectUtils.anyNotNull(startDt, endDt)) { return false;                                            }
        return true;
    }

    /**
     * @param  startDt 시작일
     * @param  endDt   종료일
     * @return         시작/종료일이 모두 널이면 true
     *                 시작/종료일이 하나라도 널이면 false
     *                 시작/종료일이 모두 널이 아니고 startDt 가 endDt 이전일이면 true 아니면 false
     */
    public static boolean isValidDateRange(LocalDateTime startDt, LocalDateTime endDt) {
        if(ObjectUtils.allNotNull(startDt, endDt)) { return startDt.equals(endDt) || startDt.isBefore(endDt); }
        if(ObjectUtils.anyNotNull(startDt, endDt)) { return false;                                            }
        return true;
    }
}
