package com.ezen.springfeed.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QnaService {
    private final QnaRepository qr;

    @Autowired
    public QnaService(QnaRepository qr) {
        this.qr = qr;
    }

    public List<Qna> getAllQna() {
        return qr.findAll();
    }

    public List<Qna> getAllQnaByUserid(String userid) {
        return qr.findAllByUserid(userid);
    }

    public void addQnaService(Qna qna) {
        qr.save(qna);
    }

    @Transactional
    public void editQna(Qna editedQna) {
        Qna qna = qr.findById(editedQna.getQnaNum())
                .orElseThrow(() -> new IllegalStateException(
                        "수정히려는 QNA가 없어요"
                ));

        if(editedQna.getQnaSubject() != null) {
            qna.setQnaSubject(editedQna.getQnaSubject());
        }

        if(editedQna.getQnaContent() != null) {
            qna.setQnaContent(editedQna.getQnaContent());
        }
    }

    public void deleteQnaByQnaNum(Long qnaNum) {
        qr.deleteById(qnaNum);
    }

}
