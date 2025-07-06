package com.portolio.notic_board.repository;

import com.portolio.notic_board.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {   // 엔티티명 변경
    // 기본 CRUD 메서드는 JpaRepository에 의해 자동으로 제공됩니다.
}
