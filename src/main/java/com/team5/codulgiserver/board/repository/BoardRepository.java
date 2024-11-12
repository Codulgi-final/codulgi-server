package com.team5.codulgiserver.board.repository;

import com.team5.codulgiserver.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
