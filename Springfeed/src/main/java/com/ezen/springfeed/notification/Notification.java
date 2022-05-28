package com.ezen.springfeed.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @Column(name = "num", nullable = false)
    private long num;

    private String user_to;
    private String user_from;
    private Integer noti_type;
    private Integer post_num;
    private Integer reply_num;
    private Timestamp create_date;
    private String content;
    private Integer checked;
}