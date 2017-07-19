package com.wcb.clock.db;

import android.content.Context;
import com.wcb.clock.db.obj.Clock;
import com.wcb.clock.db.obj.ClockDao;
import com.wcb.clock.db.obj.DaoMaster;
import com.wcb.clock.db.obj.DaoSession;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/**
 * Created by RS on 2017/7/15.
 */

@SuppressWarnings("unchecked")
public class DbManager {

    private static DbManager instance = null;
    private int dbVersion;
    private String dbName = "wcb_clock.db";

    public static DbManager getInstance(Context context) {
        if (instance == null)
            instance = new DbManager(context);
        return instance;
    }

    //TODO: 添加了新实体后，需要再此处添加相应的信息
    private static DaoSession daoSession;
    private static ClockDao clockDao;

    public DbManager(Context context) {
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName);
        Database db = helper.getWritableDb();
        dbVersion = DaoMaster.SCHEMA_VERSION;

        daoSession = new DaoMaster(db).newSession();
        clockDao = daoSession.getClockDao();
    }

    public int getDbVersion() {
        return this.dbVersion;
    }

    /**
     * 查询
     * @param context   上下文
     * @param cls   查询对象
     * @param conditions 查询条件
     * @param <T>
     * @return
     */
    public static <T> Query dbQuery(Context context, Class cls, WhereCondition... conditions) {
        instance = getInstance(context);
        QueryBuilder<T> queryBuilder = getQueryBuilder(cls);
        if (queryBuilder != null) {
            addCondition(queryBuilder, conditions);
            return queryBuilder.build();
        }
        return null;
    }

    /**
     * 插入
     * @param context 上下文
     * @param obj   插入的对象
     * @param <T>
     * @return
     */
    public static <T> long dbInsert(Context context, Object obj) {
        instance = getInstance(context);
        AbstractDao<T, Long> dao = getDao(obj);
        if (dao != null)
            return dao.insertOrReplace((T)obj);
        return -1;
    }

    /**
     * 删除
     * @param context 上下文
     * @param obj  删除的对象
     * @param <T>
     */
    public static <T> void dbDelete(Context context, Object obj) {
        instance = getInstance(context);
        AbstractDao<T, Long> dao = getDao(obj);
        if (dao != null)
            dao.delete((T) obj);
    }

    /**
     * 更新原有对象
     * 需要注意: 调用前需判断数据库中是否已经插入该对象
     * @param context 上下文
     * @param obj   更新对象
     * @param <T>
     */
    public static <T> void dbUpdate(Context context, Object obj) {
        instance = getInstance(context);
        AbstractDao<T, Long> dao = getDao(obj);
        if (dao != null)
            dao.update((T) obj);
    }

    /**
     * 统计个数
     * @param context 上下文
     * @param cls   统计对象
     * @param conditions    统计条件
     * @param <T>
     * @return  符合条件的结果个数
     */
    public static <T> long dbCount(Context context, Class cls, WhereCondition... conditions) {
        instance = getInstance(context);
        QueryBuilder<T> queryBuilder = getQueryBuilder(cls);
        if (queryBuilder != null) {
            addCondition(queryBuilder, conditions);
            return queryBuilder.count();
        }
        return 0;
    }

    public static <T> void dbDeleteAll(Context context, Class cls) {
        instance = getInstance(context);
        AbstractDao<T, Long> dao = getDao(cls);
        if (dao != null)
            dao.deleteAll();
    }

    //TODO：按条件删除
    //FIXME: github上新建临时分支，用于开发未完成时，公司电脑和家里电脑同步
    public static <T> void dbDelete(Context context, Class cls, WhereCondition... conditions) {
//        instance = getInstance(context);
//        QueryBuilder<T> queryBuilder = getQueryBuilder(cls);
//        if (queryBuilder != null) {
//            addCondition(queryBuilder, conditions);
//            DeleteQuery<T> d = queryBuilder.buildDelete();
//            d.executeDeleteWithoutDetachingEntities();
//        }
    }

    /**
     * 获取某个类对应的QueryBuilder
     * @param cls
     * @param <T>
     * @return
     */
    private static <T> QueryBuilder<T> getQueryBuilder(Class cls) {
        if (cls == null)
            return null;

        QueryBuilder<T> queryBuilder;
        if (cls.equals(Clock.class)) {
            queryBuilder = (QueryBuilder<T>) clockDao.queryBuilder();
            queryBuilder.orderAsc(ClockDao.Properties.Id);
        } else {
            return null;
        }

        return queryBuilder;
    }

    /**
     * 获取某个对象对应的dao
     * @param obj
     * @param <T>
     * @return
     */
    private static <T> T getDao(Object obj) {
        if (obj == null)
            return null;
        if (obj instanceof Clock) {
            return (T) clockDao;
        } else {
            return null;
        }
    }

    private static <T> T getDao(Class cls) {
        if (cls == null)
            return null;
        if (cls.equals(Clock.class)) {
            return (T) clockDao;
        } else {
            return null;
        }
    }

    /**
     * 添加查询条件
     * @param queryBuilder
     * @param conditions
     */
    private static void addCondition(QueryBuilder queryBuilder, WhereCondition... conditions) {
        if (conditions != null) {
            for (WhereCondition c : conditions)
                queryBuilder.where(c);
        }
    }


}
