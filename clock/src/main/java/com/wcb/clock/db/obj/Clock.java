package com.wcb.clock.db.obj;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 闹钟
 * Created by RS on 2017/7/15.
 */

@Entity
public class Clock {

    @Id
    private Long id;

    @Generated(hash = 911477612)
    public Clock(Long id) {
        this.id = id;
    }

    @Generated(hash = 1588708936)
    public Clock() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
