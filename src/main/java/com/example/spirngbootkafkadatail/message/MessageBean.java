package com.example.spirngbootkafkadatail.message;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息实体类
 *
 * @author chen
 * @version 1.0.0
 */
@Data
public class MessageBean implements Serializable {

    /** uuid */
    private String uuid;

    /** 时间  */
    private Date date;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
