package com.ezen.springfeed.qna;

import com.ezen.springfeed.model.Qna;
import com.ezen.springfeed.v2.qna.QnaRepository;
import com.ezen.springfeed.v2.qna.QnaService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class QnaServiceTest {

    @Mock
    private QnaRepository qnaRepository;

    @Captor
    private ArgumentCaptor<Qna> qnaArgumentCaptor;

    private QnaService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new QnaService(qnaRepository);
    }

    // Get All Qna
    @Test
    void isShouldSelectAllQna() {
        // Given

        // When

        // Then
    }

    // Get All Qna By Userid

    // Add Qna

    // Edit Qna

    // Delete Qna By Qna Num
}
