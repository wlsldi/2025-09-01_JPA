package com.kh.app0831.board;

import com.kh.app0831.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final com.kh.app0831.board.BoardService boardService;

    @PostMapping
    public Long insert(@RequestBody BoardDto dto){
        return boardService.insert(dto);
    }

    @GetMapping("{no}")
    public BoardDto findBoardByNo(@PathVariable Long no){
        return boardService.findBoardByNo(no);
    }

    @GetMapping
    public List<BoardDto> findBoardAll(){
        return boardService.findBoardAll();
    }

    @DeleteMapping("{no}")
    public void deleteBoardByNo(@PathVariable Long no, @RequestBody BoardDto dto){
        boardService.delete(no, dto.getWriterNo());
    }

    @PutMapping
    public void updateBoard(@RequestBody BoardDto dto){
        boardService.updateBoard(dto, dto.getWriterNo());
    }
}