package com.aqiu.rxzone.utils;

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeProcess {
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowTime(){
		Time time = new Time();
		time.setToNow();
		String year = formatNumber(time.year);
		String month = formatNumber(time.month + 1);
		String day = formatNumber(time.monthDay);
		String hour = formatNumber(time.hour);
		String minute = formatNumber(time.minute);
		String second = formatNumber(time.second);
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}
	/**
	 * 比较时间 和 当前时间大小
	 * @param time
	 * @return true 时间大于当前时间
	 */
	public static boolean isBigerNowTime(String time){
		if(time == null){
			return false;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(time);
			Date data = new Date();
			if (d1.getTime() >  data.getTime()){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	} 
	/**
	 * 时间转换
	 * @param time 2015-11-11 11:11:11
	 * @return 11月11日11:11
	 */
	public static String changeDataStyle(String time){
		try {
			String str = time.substring(5, 16);
			return str.replaceAll("-", "月").replaceAll(" ", "日");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "time";
	}
	/**
	 * 时间格式转换Date转string
	 * @param date
	 * @return
	 */
	public static String date2String(Date date){
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return time.format(date);
	}
	public static String dateFormat(String time){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date date = sdf.parse(time);
		    SimpleDateFormat timeFormat=new SimpleDateFormat("MM月dd HH:mm:ss");
			return timeFormat.format(date);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return time;
	}
	/**
	 * 根据时间获取日期
	 * @param time
	 * @return
	 */
	public static String getDateFromTime(String time){
		if(time == null || time.length() < 10){
			return time;
		}else{
			return time.substring(0, 10);
		}
	}
	/**
	 * 获取短时间格式
	 * @param time
	 * @return
	 */
	public static String getShortTime(String time){
		String shortTime = time;
		try {
			shortTime = time.substring(5,16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shortTime;
	}
	/**
	 * 把秒数转成显示的格式
	 * @param time
	 * @return
	 */
	public static String getRecordTime(int time){
		if(time < 60){
			return "00:" + formatNumber(time);
		}
		if(time < 60 * 60){
			return formatNumber(time/60) + ":" + formatNumber(time%60);
		}
		if(time < 60 * 60 *24){
			int hour = time/3600;
			int min = time%3600;
			return formatNumber(hour) + ":" + formatNumber(min/60) + ":" + formatNumber(min%60);
		}
		return ""+time;
	}
	/**
	 * 个位数前面加0
	 * @param time
	 * @return
	 */
	private static String formatNumber(int time){
		if(time < 10){
			return "0" + time;
		}
		return ""+time;
	}
}
