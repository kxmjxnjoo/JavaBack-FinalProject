package com.ezen.springfeed.v2.qna;

import com.ezen.springfeed.model.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findAllByUserid(String userid);
}
