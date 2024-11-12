package com.team5.codulgiserver.domain.board.repository;

import com.team5.codulgiserver.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
