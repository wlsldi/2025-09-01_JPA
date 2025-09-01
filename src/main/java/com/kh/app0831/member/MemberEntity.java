package com.kh.app0831.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "user_id", nullable = false , unique = true , length = 100)
    private String userId;

    @Column(name = "user_pwd", nullable = false , length = 100)
    private String userPwd;

    @Column(name = "user_nick", nullable = false , length = 30)
    private String userNick;

    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberEntity() {
        delYn = "N";
        createdAt = LocalDateTime.now();
    }

    public static MemberEntity from(MemberDto dto){
        MemberEntity entity = new MemberEntity();
        entity.userId = dto.getUserId();
        entity.userPwd = dto.getUserPwd();
        entity.userNick = dto.getUserNick();
        return entity;
    }
}