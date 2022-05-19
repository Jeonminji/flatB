package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReportEntity;
import com.example.flatB.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

public class ReportDto {

    //신고 게시글 등록 요청 클래스
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Integer boardNo;
        private String nickname;
        private String note;
        private String type;
        private String content;
        private UserEntity user;
        private LocalDateTime date;

        /* Dto -> Entity */
        public ReportEntity toEntity() {
            ReportEntity reports = ReportEntity.builder()
                    .boardNo(boardNo)
                    .nickname(nickname)
                    .note(note)
                    .type(type)
                    .content(content)
                    .user(user)
                    .date(date)
                    .build();

            return reports;
        }
    }

//    //게시글 정보 리턴할 클래스
//    @Getter
//    public static class Response {
//        private Integer boardNo;
//        private String nickname;
//        private String note;
//        private String type;
//        private String content;
//        private String writerId;
//        private LocalDateTime date;
//
//        /* Entity -> Dto*/
//        public Response(ReportEntity reports) {
//            this.boardNo = reports.getBoardNo();
//            this.nickname = reports.getNickname();
//            this.note = reports.getNote();
//            this.type = reports.getType();
//            this.content = reports.getContent();
//            this.writerId = reports.getUser().getUserId();
//            this.date = reports.getDate();
//        }
//    }
}
