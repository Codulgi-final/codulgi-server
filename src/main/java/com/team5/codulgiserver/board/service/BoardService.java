package com.team5.codulgiserver.board.service;

import com.team5.codulgiserver.board.dto.BoardRequest;
import com.team5.codulgiserver.board.dto.BoardResponse;
import com.team5.codulgiserver.board.entity.Board;
import com.team5.codulgiserver.board.repository.BoardRepository;
import com.team5.codulgiserver.member.entity.Member;
import com.team5.codulgiserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /* 게시판 생성*/
    public ResponseEntity<?> createBoard(BoardRequest.save request) {

        /* 작성자 조회 */
        Optional<Member> findMember = memberRepository.findById(request.getAuthorId());

        /* 사용자가 없을 경우*/
        if (findMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("로그인 세션을 확인해주세요.");
        }

        Board newBoard = boardRepository.save(
                new Board(request, findMember.get())
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(newBoard);
    }

    /* 게시판 조회 - 상세 조회 */
    public ResponseEntity<?> getBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);

        if (findBoard.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미 삭제된 게시글이거나 존재하지 않는 게시물입니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BoardResponse.board(findBoard.get()));
    }

    /* 게시판 조회 - 모두 조회 (Pageable) */
    public ResponseEntity<?> getAllBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(boards);
    }

    /* 게시판 수정 */
    public ResponseEntity<?> updateBoard(BoardRequest.update boardRequest) {

        Optional<Board> findBoard = boardRepository.findById(boardRequest.getId());
        if (findBoard.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미 삭제된 게시글이거나 존재하지 않는 게시물입니다.");
        }

        Board update = findBoard.get().update(boardRequest);

        return ResponseEntity.status(HttpStatus.OK).body(new BoardResponse.board(update));

    }

    /* 게시판 삭제 */
    public ResponseEntity<?> deleteBoard(Long id) {
        try {
            boardRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("성공적으로 게시물을 삭제 하였습니다.");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}