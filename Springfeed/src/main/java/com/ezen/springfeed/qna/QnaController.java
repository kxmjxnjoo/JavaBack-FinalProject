//package com.ezen.springfeed.qna;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/qna")
//public class QnaController {
//    private final QnaService qs;
//
//    @Autowired
//    public QnaController(QnaService qs) {
//        this.qs = qs;
//    }
//
//    @GetMapping
//    public List<Qna> getAllQna() {
//        return qs.getAllQna();
//    }
//
//    @GetMapping("{userid}")
//    public List<Qna> getUserQna(@PathVariable("userid") String userid) {
//        return qs.getAllQnaByUserid(userid);
//    }
//
//    @PutMapping
//    public void addQnaController(@RequestBody @Valid Qna qna) {
//        qs.addQnaService(qna);
//    }
//
//    @PutMapping
//    public void editUserQna(@RequestBody Qna qna) {
//        qs.editQna(qna);
//    }
//
//    @DeleteMapping("/{qnanum}")
//    public void deleteQnaByQnaNum(@PathVariable("qnanum") Long qnaNum) {
//        qs.deleteQnaByQnaNum(qnaNum);
//    }
//}
