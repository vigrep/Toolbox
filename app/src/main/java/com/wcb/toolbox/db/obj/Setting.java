package com.wcb.toolbox.db.obj;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by RS on 2017/7/15.
 */

@Entity
public class Setting {
    @Id
    private Long id;

    @Generated(hash = 1392621202)
    public Setting(Long id) {
        this.id = id;
    }

    @Generated(hash = 909716735)
    public Setting() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
