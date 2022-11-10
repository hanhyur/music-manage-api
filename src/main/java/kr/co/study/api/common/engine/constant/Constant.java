package kr.co.study.api.common.engine.constant;

/**
* @since       2019.04.15
* @author      lucas
* @description constant
**********************************************************************************************************************/
public class Constant {

    public static class String {
        public final static java.lang.String EMPTY    = "";
        public final static java.lang.String BLANK    = " ";
        public final static java.lang.String ASTERISK = "*";
        public final static java.lang.String COMMA    = ",";
        public final static java.lang.String DOT      = ".";
        public final static java.lang.String SLASH    = "/";
        public final static java.lang.String ZERO     = "0";
        public final static java.lang.String TEN      = "10";
        public final static java.lang.String Y        = "Y";
        public final static java.lang.String ONE      = "1";
    }

    public static class Integer {
        public final static java.lang.Integer ZERO  =  0;
        public final static java.lang.Integer ONE   =  1;
        public final static java.lang.Integer TWO   =  2;
        public final static java.lang.Integer THREE =  3;
        public final static java.lang.Integer TEN   = 10;
    }

    public static class Message {
        public static final java.lang.String ERROR = "E";
    }

    public static class Environment {
        public static final java.lang.String DEV  = "dev";
        public static final java.lang.String PROD = "prod";
    }
}