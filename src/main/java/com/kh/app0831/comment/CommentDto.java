package com.kh.app0831.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {

    private Long no;
    private String content;
    private Long boardNo;
    private Long writerNo;
    private String writerNick;
    private String delYn;
    private LocalDateTime createdAt;

    public static CommentDto from(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setNo(entity.getNo());
        dto.setContent(entity.getComment());
        dto.setBoardNo(entity.getBoard().getNo());
        dto.setWriterNo(entity.getWriter().getNo());
        dto.setWriterNick(entity.getWriter().getUserNick());
        dto.setDelYn(entity.getDelYn());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}