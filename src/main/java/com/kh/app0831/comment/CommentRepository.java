package com.kh.app0831.comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void insert(com.kh.app0831.comment.CommentEntity entity) {
        em.persist(entity);
    }

    public com.kh.app0831.comment.CommentEntity findCommentByNo(Long no) {
        return em.find(com.kh.app0831.comment.CommentEntity.class, no);
    }

    public List<com.kh.app0831.comment.CommentEntity> findByBoardNo(Long boardNo) {
        String jpql = """
                SELECT c FROM CommentEntity c
                WHERE c.board.no = :boardNo AND c.delYn = 'N'
                ORDER BY c.no DESC
                """;
        return em.createQuery(jpql, com.kh.app0831.comment.CommentEntity.class)
                .setParameter("boardNo", boardNo)
                .getResultList();
    }
}