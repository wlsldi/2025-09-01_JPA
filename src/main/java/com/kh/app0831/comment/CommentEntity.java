package com.kh.app0831.comment;

import com.kh.app0831.board.BoardEntity;
import com.kh.app0831.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT")
@Getter
@Setter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardNo", nullable = false)
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerNo", nullable = false)
    private MemberEntity writer;

    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentEntity() {
        this.delYn = "N";
        this.createdAt = LocalDateTime.now();
    }

    public void delete() {
        this.delYn = "Y";
        this.updatedAt = LocalDateTime.now();
    }
}