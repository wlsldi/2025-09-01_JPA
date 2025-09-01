package com.kh.app0831.board;

import com.kh.app0831.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
@Setter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerNo" , nullable = false)
    private MemberEntity writer;

    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardEntity() {
        this.delYn = "N";
        this.createdAt = LocalDateTime.now();
    }

    public void delete() {
        this.delYn = "Y";
        this.updatedAt = LocalDateTime.now();
    }

    public void update(BoardDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.updatedAt = LocalDateTime.now();
    }
}