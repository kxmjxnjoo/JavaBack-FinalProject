package com.ezen.springfeed.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @Column(name = "num")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "notification_seq")
    private long num;

    @Column(name = "user_to", nullable = false)
    private String userTo;
    @Column(name = "user_from", nullable = false)
    private String userFrom;
    @Column(name = "noti_type")
    private Integer notiType;
    @Column(name = "post_num")
    private Integer postNum;
    @Column(name = "reply_num")
    private Integer replyNum;
    @Column(name = "create_date")
    private Timestamp createDate;
    private String content;
    private Integer checked;
}