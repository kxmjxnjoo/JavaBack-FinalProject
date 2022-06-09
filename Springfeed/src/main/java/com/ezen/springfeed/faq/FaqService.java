package com.ezen.springfeed.faq;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {
    private final FaqRepository fr;

    // GET
    public FaqService(FaqRepository fr) {
        this.fr = fr;
    }

    public List<Faq> getAllFaq() {
        return fr.findAll();
    }

    public Faq selectFaqById(Long id) {
        return fr.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "해당 FAQ가 없어요"
                ));
    }

    // INSERT
    public void addFaq(Faq faq) {
        fr.save(faq);
    }

    // UPDATE
    @Transactional
    public void updateFaq(Faq updatedFaq, Long id) {
        Faq faq = fr.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "해당 FAQ가 존재하지 않아요"
                ));

        if(updatedFaq.getSubject() != null) {
            faq.setSubject(updatedFaq.getSubject());
        }

        if(updatedFaq.getContent() != null) {
            faq.setContent(updatedFaq.getContent());
        }
    }

    // DELETE
    public void deleteFaq(Long num) {
        fr.deleteById(num);
    }
}
