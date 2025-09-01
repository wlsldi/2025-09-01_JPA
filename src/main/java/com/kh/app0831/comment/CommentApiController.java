package com.kh.app0831.comment;

import com.kh.app0831.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    public Long insert(@RequestBody CommentDto dto) {
        // dto.setWriterNo(loginMemberDto.getNo());
        return commentService.insert(dto);
    }


    @GetMapping("/board/{boardNo}")
    public List<com.kh.app0831.comment.CommentDto> findByBoardNo(@PathVariable Long boardNo) {
        return commentService.findByBoardNo(boardNo);
    }


}