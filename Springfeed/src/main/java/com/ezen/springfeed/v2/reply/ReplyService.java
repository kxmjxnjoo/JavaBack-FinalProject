package com.ezen.springfeed.v2.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyService {
    private final ReplyRepository rr;

    public ReplyService(ReplyRepository rr) {
        this.rr = rr;
    }


    public List<Reply> getAllReplyByPostnum(Long postnum) {
        return rr.findAllByPostNumOrderByReplyDate(postnum);
    }

    public void addReply(Reply reply) {
        rr.save(reply);
    }


    @Transactional
    public void editReply(Reply editedReply) {
        Reply reply = rr.findById(editedReply.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "수정하시려는 댓글이 없어요"
                ));

        if(editedReply.getContent() != null) {
            reply.setContent(editedReply.getContent());
        }
    }

    public void deleteReply(Long replyNum) {
        rr.deleteById(replyNum);
    }
}
