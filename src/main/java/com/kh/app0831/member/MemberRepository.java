package com.kh.app0831.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void join(com.kh.app0831.member.MemberEntity entity) {
        em.persist(entity);
    }

    public com.kh.app0831.member.MemberEntity login(MemberDto dto) {
        String jpql = """
                select m from MemberEntity m 
                where m.userId = :x 
                and m.userPwd = :y 
                and m.delYn = 'N'
                """;
        List<com.kh.app0831.member.MemberEntity> result = em
                .createQuery(jpql, MemberEntity.class)
                .setParameter("x", dto.getUserId())
                .setParameter("y", dto.getUserPwd())
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    public com.kh.app0831.member.MemberEntity findByNo(Long writerNo) {
        return em.find(MemberEntity.class, writerNo);
    }
}