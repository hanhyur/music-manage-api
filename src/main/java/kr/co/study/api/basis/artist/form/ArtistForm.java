package kr.co.study.api.basis.artist.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @since       2022.10.31
 * @author      aslan
 * @description artist form
 **********************************************************************************************************************/
public class ArtistForm {

    public static class Request {

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Find {

            @ApiModelProperty(value = "아티스트 아이디")
            private Integer id;

        }

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Add {

            @ApiModelProperty(value = "아티스트 명")
            @NotBlank
            private String name;

            @ApiModelProperty(value = "생년월일")
            @NotNull
            private LocalDate birth;

            @ApiModelProperty(value = "소속사")
            private String agency;

            @ApiModelProperty(value = "국적")
            @NotBlank
            private String nationality;

            @ApiModelProperty(value = "소개")
            private String introduction;

        }

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Modify {


            @ApiModelProperty(value = "아티스트 명")
            @NotBlank
            private String name;

            @ApiModelProperty(value = "생년월일")
            @NotNull
            private LocalDate birth;

            @ApiModelProperty(value = "소속사")
            private String agency;

            @ApiModelProperty(value = "국적")
            @NotBlank
            private String nationality;

            @ApiModelProperty(value = "소개")
            private String introduction;

        }
    }

    public static class Response {

        @Data
        public static class FindAll {

            @ApiModelProperty(value = "아티스트 아이디")
            private Integer id;

            @ApiModelProperty(value = "아티스트 명")
            private String name;

            @ApiModelProperty(value = "생년월일")
            private LocalDate birth;

            @ApiModelProperty(value = "소속사")
            private String agency;

            @ApiModelProperty(value = "국적")
            private String nationality;

            @ApiModelProperty(value = "소개")
            private String introduction;

            @ApiModelProperty(value = "등록자")
            private String registrant;

            @ApiModelProperty(value = "등록일")
            private LocalDateTime createdAt;

            @ApiModelProperty(value = "수정일")
            private LocalDateTime updatedAt;

        }

        @Data
        public static class FindOne {

            @ApiModelProperty(value = "아티스트 아이디")
            private Integer id;

            @ApiModelProperty(value = "아티스트 명")
            private String name;

            @ApiModelProperty(value = "생년월일")
            private LocalDate birth;

            @ApiModelProperty(value = "소속사")
            private String agency;

            @ApiModelProperty(value = "국적")
            private String nationality;

            @ApiModelProperty(value = "소개")
            private String introduction;

            @ApiModelProperty(value = "등록자")
            private String registrant;

            @ApiModelProperty(value = "등록일")
            private LocalDateTime createdAt;

        }

    }

}
