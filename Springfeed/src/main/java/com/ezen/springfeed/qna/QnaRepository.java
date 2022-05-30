package com.ezen.springfeed.qna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findAllOrderByIndate();

    List<Qna> findAllByUserid(String userid);
}
