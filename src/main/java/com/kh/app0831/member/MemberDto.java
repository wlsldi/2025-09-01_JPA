package com.kh.app0831.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDto {

    private Long no;
    private String userId;
    private String userPwd;
    private String userNick;
    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberDto from(MemberEntity entity){
        MemberDto dto = new MemberDto();
        dto.setNo(entity.getNo());
        dto.setUserId(entity.getUserId());
        dto.setUserPwd(entity.getUserPwd());
        dto.setUserNick(entity.getUserNick());
        dto.setDelYn(entity.getDelYn());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}