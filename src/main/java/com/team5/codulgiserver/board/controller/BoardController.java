package com.team5.codulgiserver.board.controller;

import com.team5.codulgiserver.board.dto.BoardRequest;
import com.team5.codulgiserver.board.service.BoardService;
import com.team5.codulgiserver.member.entity.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    /* 게시물 작성 */
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest.save request) {
        Member member = (Member) session.getAttribute("member");

        request.setAuthorId(member.getId());
        return boardService.createBoard(request);
    }

    /* 게시물 상세 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    /* 게시물 모두 조회 */
    @GetMapping
    public ResponseEntity<?> getAllBoards(@RequestParam(defaultValue = "10") Integer size,
                                          @RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return boardService.getAllBoards(pageable);
    }

    /* 게시물 수정 */
    @PutMapping
    public ResponseEntity<?> updateBoard(@RequestBody BoardRequest.update boardRequest) {
        return boardService.updateBoard(boardRequest);
    }

    /* 게시물 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

}
