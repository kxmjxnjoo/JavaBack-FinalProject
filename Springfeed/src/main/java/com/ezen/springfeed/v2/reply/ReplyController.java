package com.ezen.springfeed.v2.reply;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/reply")
public class ReplyController {
    private final ReplyService rs;

    public ReplyController(ReplyService rs) {
        this.rs = rs;
    }

    @GetMapping("/{postnum}")
    public List<Reply> getAllReplyByPostnum(@PathVariable("postnum") Long postnum) {
        return rs.getAllReplyByPostnum(postnum);
    }

    @PostMapping
    public void addReply(@RequestBody Reply reply) {
        rs.addReply(reply);
    }

    @PutMapping
    public void editReply(@RequestBody Reply reply) {
        rs.editReply(reply);
    }

    @DeleteMapping
    public void deleteReply(Long replyNum) {
        rs.deleteReply(replyNum);
    }
}
