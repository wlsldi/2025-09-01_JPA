package com.kh.app0831.comment;

import com.kh.app0831.board.BoardRepository;
import com.kh.app0831.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final com.kh.app0831.comment.CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long insert(com.kh.app0831.comment.CommentDto dto) {
        var boardEntity = boardRepository.findBoardByNo(dto.getBoardNo());
        var memberEntity = memberRepository.findByNo(dto.getWriterNo());
        var entity = new com.kh.app0831.comment.CommentEntity();
        entity.setComment(dto.getContent());
        entity.setBoard(boardEntity);
        entity.setWriter(memberEntity);
        commentRepository.insert(entity);
        return entity.getNo();
    }

    @Transactional(readOnly = true)
    public List<com.kh.app0831.comment.CommentDto> findByBoardNo(Long boardNo) {
        return commentRepository.findByBoardNo(boardNo)
                .stream()
                .map(com.kh.app0831.comment.CommentDto::from)
                .collect(Collectors.toList());
    }


}