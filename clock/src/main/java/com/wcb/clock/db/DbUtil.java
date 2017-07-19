package com.wcb.clock.db;

import android.content.Context;
import com.wcb.clock.db.obj.Clock;
import com.wcb.clock.db.obj.ClockDao;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 * Created by RS on 2017/7/14.
 */

public class DbUtil {

    /**
     * 获取数据库中所有闹钟
     * @param context
     * @return
     */
    public static List<Clock> getAllClock(Context context) {
        Query<Clock> query = DbManager.dbQuery(context, Clock.class);
        if (query != null)
            return query.list();
        return new ArrayList<>();
    }

    /**
     * 获取数据库中所有处于enable状态的闹钟
     * @param context
     * @return
     */
    public static List<Clock> getAllEnableClock(Context context) {
        WhereCondition condition = ClockDao.Properties.Enable.eq(true);
        Query<Clock> query = DbManager.dbQuery(context, Clock.class, condition);
        if (query != null)
            return query.list();
        return new ArrayList<>();
    }

    /**
     * 获取数据库中所有处于disable状态的闹钟
     * @param context
     * @return
     */
    public static List<Clock> getAllDisableClock(Context context) {
        WhereCondition condition = ClockDao.Properties.Enable.eq(false);
        Query<Clock> query = DbManager.dbQuery(context, Clock.class, condition);
        if (query != null)
            return query.list();
        return new ArrayList<>();
    }


}
