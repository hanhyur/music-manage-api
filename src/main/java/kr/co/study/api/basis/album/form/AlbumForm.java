package kr.co.study.api.basis.album.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album form
 **********************************************************************************************************************/
public class AlbumForm {

    public static class Request {

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Find {

            @ApiModelProperty(value = "앨범 아이디")
            private Integer id;

        }

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Add {

            @ApiModelProperty(value = "앨범 명")
            @NotBlank
            private String title;

            @ApiModelProperty(value = "발매일")
            @NotNull
            private LocalDate releaseDate;

            @ApiModelProperty(value = "장르")
            private String genre;

            @ApiModelProperty(value = "설명")
            private String description;

            @ApiModelProperty(value = "아티스트")
            @Valid
            private Artist artist;

            @ApiModelProperty(value = "음원")
            @Valid
            private List<Soundtrack> soundtracks;

            @Getter
            @Setter
            @Builder
            @ToString
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Soundtrack {

                @ApiModelProperty(value = "음원 넘버")
                private Integer trackNo;

                @ApiModelProperty(value = "음원 명")
                private String title;

                @ApiModelProperty(value = "재생시간")
                private String playTime;

                @ApiModelProperty(value = "노출여부")
                private String exposure;

            }

            @AssertFalse(message = "중복된 음원 순서가 존재합니다.")
            public boolean isValidTrackOrders() {
                if (CollectionUtils.isEmpty(soundtracks)) {
                    return false;
                }

                return soundtracks.stream().anyMatch(
                        t1 -> soundtracks.stream().anyMatch(
                                t2 -> t1 != t2 && t1.getTrackNo() == t2.getTrackNo())
                );
            }

        }

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Modify {

            @ApiModelProperty(value = "앨범 명")
            @NotBlank
            private String title;

            @ApiModelProperty(value = "발매일")
            @NotNull
            private LocalDate releaseDate;

            @ApiModelProperty(value = "장르")
            private String genre;

            @ApiModelProperty(value = "설명")
            private String description;

            @ApiModelProperty(value = "아티스트")
            @Valid
            private Artist artist;

            @ApiModelProperty(value = "음원")
            @Valid
            private List<Soundtrack> soundtracks;

            @Getter
            @Setter
            @Builder
            @ToString
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Soundtrack {

                @ApiModelProperty(value = "음원 아이디")
                private Integer id;

                @ApiModelProperty(value = "음원 넘버")
                private Integer trackNo;

                @ApiModelProperty(value = "음원 명")
                private String title;

                @ApiModelProperty(value = "재생시간")
                private String playTime;

                @ApiModelProperty(value = "노출여부")
                private String exposure;

            }

            @AssertFalse(message = "중복된 음원 순서가 존재합니다.")
            public boolean isValidTrackOrders() {
                if (CollectionUtils.isEmpty(soundtracks)) {
                    return false;
                }

                return soundtracks.stream().anyMatch(
                        t1 -> soundtracks.stream()
                                .filter(t2 -> BooleanUtils.isFalse(t2.equals(t1)))
                                .anyMatch(t2 -> t1.getTrackNo().equals(t2.getTrackNo()))
                );
            }

        }

        @Getter
        @Setter
        @Builder(toBuilder = true)
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Artist {

            @ApiModelProperty(value = "아티스트 아이디")
            @NotNull
            private Integer id;

        }

    }

    public static class Response {

        @Data
        public static class FindAll {

            @ApiModelProperty(value = "앨범 아이디")
            private Integer id;

            @ApiModelProperty(value = "앨범 명")
            private String title;

            @ApiModelProperty(value = "발매일")
            private LocalDate releaseDate;

            @ApiModelProperty(value = "장르")
            private String genre;

            @ApiModelProperty(value = "설명")
            private String description;

            @ApiModelProperty(value = "등록자")
            private String registrant;

            @ApiModelProperty(value = "등록일")
            private LocalDateTime createdAt;

            @ApiModelProperty(value = "수정일")
            private LocalDateTime updatedAt;

            @ApiModelProperty(value = "아티스트")
            private Artist artist;

        }

        @Data
        public static class FindOne {

            @ApiModelProperty(value = "앨범 아이디")
            private Integer id;

            @ApiModelProperty(value = "앨범 명")
            private String title;

            @ApiModelProperty(value = "발매일")
            private LocalDate releaseDate;

            @ApiModelProperty(value = "장르")
            private String genre;

            @ApiModelProperty(value = "설명")
            private String description;

            @ApiModelProperty(value = "등록자")
            private String registrant;

            @ApiModelProperty(value = "등록일")
            private LocalDateTime createdAt;

            @ApiModelProperty(value = "아티스트")
            private Artist artist;

            @ApiModelProperty(value = "음원")
            private List<Soundtrack> soundtracks;

            @Data
            public static class Soundtrack {

                @ApiModelProperty(value = "음원 아이디")
                private Integer id;

                @ApiModelProperty(value = "음원 넘버")
                private Integer trackNo;

                @ApiModelProperty(value = "음원 명")
                private String title;

                @ApiModelProperty(value = "재생시간")
                private String playTime;

                @ApiModelProperty(value = "노출여부")
                private String exposure;

            }

        }

        @Data
        public static class Artist {

            @ApiModelProperty(value = "아티스트 아이디")
            @NotNull
            private Integer id;

            @ApiModelProperty(value = "아티스트 명")
            private String name;

        }
    }
}
