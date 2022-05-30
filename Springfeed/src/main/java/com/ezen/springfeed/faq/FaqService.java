package com.ezen.springfeed.faq;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaqService {
    private final FaqRepository fr;

    public FaqService(FaqRepository fr) {
        this.fr = fr;
    }

    public List<Faq> getAllFaq() {
        return fr.findAll();
    }

    public void addFaq(Faq faq) {
        fr.save(faq);
    }

    @Transactional
    public void updateFaq(Faq updatedFaq) {
        Faq faq = fr.findById(updatedFaq.getNum())
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

    public void deleteFaq(Long num) {
        fr.deleteById(num);
    }
}
