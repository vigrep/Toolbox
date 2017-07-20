package com.wcb.clock.db.obj;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Calendar;

/**
 * 闹钟
 * Created by RS on 2017/7/15.
 */

@Entity
public class Clock {

    @Id
    private Integer id;
    private int time;               //时间
    private boolean enable = true;  //闹钟是否开启
    private byte dayOfWeek = 0;     //每周的重复
    private int alarmMode;          //闹钟提醒方式
    private int closeMode;          //闹钟关闭方式
    private boolean vibrate;        //是否震动
    private String label;           //标签
    private String musicPath;       //铃声
    private int volume;             //音量

    //摇晃模式下的额外属性
    private int shakeCount;         //晃动次数
    private int shakeLevel;         //晃动的强度级别

    //一周的分钟数
    private static int weekTime = 1440;         //晃动的强度级别

    public enum Repeat {
        MONDAY((byte) 0x01),
        TUESDAY((byte) 0x02),
        WEDNESDAY((byte) 0x04),
        THURSDAY((byte) 0x08),
        FRIDAY((byte) 0x10),
        SATURDAY((byte) 0x20),
        SUNDAY((byte) 0x40),
        EVERYDAY((byte) 0x7f),
        NONE((byte) 0x00);

        private byte value;

        Repeat(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return this.value;
        }
    }

    private void enableDay(byte day) {
        dayOfWeek |= (1 << (day - 1));
    }

    public void enableDay(Repeat day) {
//        dayOfWeek += day.getValue();
        dayOfWeek |= day.getValue();
    }

    public void setRepeat(Repeat[] days) {
        dayOfWeek = 0x00;
        if (days != null && days.length > 0) {
            for (Repeat repeat : days)
                dayOfWeek += repeat.getValue();
        }
    }

    public void setAllRepeat() {
        dayOfWeek = Repeat.EVERYDAY.getValue();
    }

    private void disableDay(byte day) {
        dayOfWeek &= ~(1 << (day - 1));
    }

    public void disableDay(Repeat day) {
        dayOfWeek &= ~day.getValue();
    }

    //判断某一天是否重复，1<<（day-1) 然后与cricle进行与操作，不为0，就重复
    private boolean dayIsEnable(byte day) {
        return ((dayOfWeek & (1 << (day - 1))) > 0);
    }

    public boolean dayEnable(Repeat day) {
        return (dayOfWeek & day.getValue()) > 0;
    }

    /**
     * 获取最近的提醒时间
     * @param day current day of week
     * @return
     */
    public int getNearestTime(byte day){
        byte currentDay = day;
        int nearestTime = -1;
        do{
            if(dayIsEnable(currentDay))
                nearestTime = time+valueOfDay(currentDay)*weekTime;
        }while(valueOfDay(currentDay) < valueOfDay(Repeat.SUNDAY.getValue()));
        return nearestTime;
    }

    private int valueOfDay(byte day){
        return day & 0xFF;
    }

    // ************************************ 以下自动生成 ****************************

    @Generated(hash = 1314528118)
    public Clock(Integer id, int time, boolean enable, byte dayOfWeek,
            int alarmMode, int closeMode, boolean vibrate, String label,
            String musicPath, int volume, int shakeCount, int shakeLevel) {
        this.id = id;
        this.time = time;
        this.enable = enable;
        this.dayOfWeek = dayOfWeek;
        this.alarmMode = alarmMode;
        this.closeMode = closeMode;
        this.vibrate = vibrate;
        this.label = label;
        this.musicPath = musicPath;
        this.volume = volume;
        this.shakeCount = shakeCount;
        this.shakeLevel = shakeLevel;
    }

    @Generated(hash = 1588708936)
    public Clock() {
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public byte getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(byte dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAlarmMode() {
        return this.alarmMode;
    }

    public void setAlarmMode(int alarmMode) {
        this.alarmMode = alarmMode;
    }

    public int getCloseMode() {
        return this.closeMode;
    }

    public void setCloseMode(int closeMode) {
        this.closeMode = closeMode;
    }

    public boolean getVibrate() {
        return this.vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMusicPath() {
        return this.musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getShakeCount() {
        return this.shakeCount;
    }

    public void setShakeCount(int shakeCount) {
        this.shakeCount = shakeCount;
    }

    public int getShakeLevel() {
        return this.shakeLevel;
    }

    public void setShakeLevel(int shakeLevel) {
        this.shakeLevel = shakeLevel;
    }
}
