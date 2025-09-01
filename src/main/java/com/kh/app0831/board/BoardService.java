package com.kh.app0831.board;

import com.kh.app0831.comment.CommentDto;
import com.kh.app0831.comment.CommentService;
import com.kh.app0831.member.MemberEntity;
import com.kh.app0831.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentService commentService;

    public Long insert(BoardDto dto) {
        MemberEntity memberEntity = memberRepository.findByNo(dto.getWriterNo());
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setWriter(memberEntity);
        boardRepository.insert(entity);
        return entity.getNo();
    }

    public BoardDto findBoardByNo(Long no) {
        BoardEntity entity = boardRepository.findBoardByNo(no);
        if (entity == null || entity.getDelYn().equals("Y")) {
            throw new BoardException("BOARD-003", "게시글을 찾을 수 없습니다.");
        }

        BoardDto dto = new BoardDto();
        dto.setNo(entity.getNo());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setWriterNo(entity.getWriter().getNo());
        dto.setWriterNick(entity.getWriter().getUserNick());
        dto.setDelYn(entity.getDelYn());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        // 댓글 목록을 가져와서 DTO에 추가
        List<CommentDto> comments = commentService.findByBoardNo(no);
        dto.setComments(comments);

        return dto;
    }

    public List<BoardDto> findBoardAll() {
        List<BoardEntity> entityList = boardRepository.findBoardAll();
        return entityList.stream().map(BoardDto::from).collect(Collectors.toList());
    }

    // 게시글 삭제
    public void delete(Long no, Long writerNo) {
        BoardEntity entity = boardRepository.findBoardByNo(no);
        if (!entity.getWriter().getNo().equals(writerNo)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        entity.delete();
    }


    // 게시글 수정
    public void updateBoard(BoardDto dto, Long writerNo) {
        BoardEntity entity = boardRepository.findBoardByNo(dto.getNo());
        if (!entity.getWriter().getNo().equals(writerNo)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        entity.update(dto);
    }
}