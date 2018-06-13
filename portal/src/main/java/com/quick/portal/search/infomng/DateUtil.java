package com.quick.portal.search.infomng;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 
 * @版本: V1.0
 * @创建日期: 2018-6-16
 * @类描述: 日期工具类。
 * @修改时间:
 * @修改备注:
 */
public class DateUtil{
	
	/**
     * 时间格式化字符串
     */
    public static final String timeFormatter = "yyyy-MM-dd hh:mm:ss";

    /**
     * 时间格式化字符串
     */
    public static final String time24Formatter = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 时间格式化字符串
     */
    public static final String timeStringFormatter = "yyyyMMdd HH:mm:ss";
    
    public static final String timeNoSecondFormatter = "yyyyMMdd HH:mm";
    
    /**
     * 日期格式化字符串
     */
    public static final String dateFormatter = "yyyy-MM-dd";

    /**
     * 日期格式化字符串
     */
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    /**
     * 日期格式化字符串
     */
    public static final String yyyyMMdd = "yyyyMMdd";
    
    /**
     * 日期格式化字符串
     */
    public static final String yyyyMM = "yyyyMM";

    /**
     * 年格式化字符串
     */
    public static final String YEAR_PATTERN = "yyyy";

    /**
     * 月格式化字符串
     */
    public static final String MONTHOFYEAR_PATTERN = "MM";

    /**
     * 日格式化字符串
     */
    public static final String DAYOFMONTH_PATTERN = "dd";

    /**
     * 定义一天的毫秒数
     */
    public static final long MILLSECOND_OF_DAY = 86400000;

	/**
	 * 两个给定的时间是否相隔i个月，如果是返回true，如果不是，返回false，比如2013-01-01和2013-04-01 相隔3个月
	 * @param begin 起始时间
	 * @param end   结束时间
	 * @param i     相隔的月份
	 * @return
	 * @throws ParseException
	 */
	public static boolean betweenMonth(String begin,String end,int i) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(begin);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, i);
		Date d2 = cal.getTime();
		String finalStr = sdf.format(d2);
		if(finalStr.equals(end)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
     * 根据传入的日期，欲转换成的日期样式，返回转换后的日期字符串
     * @param ldate -日期
     * @param pattern -具体的值范围见java.text.SimpleDateFormat的说明
     * @see java.text.SimpleDateFormat
     * @return String 转换后的日期字符串
     */
    public static String format(Date ldate, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(ldate);
    }
	/**
     * 根据传入的日期，欲转换成的日期样式，返回转换后的日期字符串数组
     * @param ldate -日期
     * @return String[] 转换后的日期字符串数组，Examples: ldate="2011-10-01";返回的是String{"2011","10","01"};
     * 
     */
    public static String[] formatYMD(Date ldate) {
        String[] yMds = new String[3];
        yMds[0] = DateUtil.format(ldate, DateUtil.YEAR_PATTERN);
        yMds[1] = DateUtil.format(ldate, DateUtil.MONTHOFYEAR_PATTERN);
        yMds[2] = DateUtil.format(ldate, DateUtil.DAYOFMONTH_PATTERN);
        return yMds;
    }
	
	/**
	 * 根据给定的Date,求此Date之后days天的Date，比如 2012-06-25 10:11:10 之后90天的日期为2012-09-23 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回Date
	 */
	public static Date getAfterDateByDays(Date endDate,int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}
	
	/**
	 * 根据给定的String格式的Date,求此String之后days天的Date，比如 2012-06-25 10:11:10 之后90天的日期为2012-09-23 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回Date
	 */
	public static Date getAfterDateByDays(String strEndDate,int days){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(strEndDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}
	/**
	 * 根据给定的Date,求此Date之后days天的Date，比如 2012-06-25 10:11:10 之后90天的日期为2012-09-23 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回String
	 */
	public static String getAfterStringByDays(Date endDate,int days){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 根据给定的String格式的Date,求此Date之后days天的Date，比如 2012-06-25 10:11:10 之后90天的日期为2012-09-23 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回String
	 */
	public static String getAfterStringByDays(String strEndDate,int days){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(strEndDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, days);
		return sdf.format(cal.getTime());
	}
	
	 /**
	 * 根据给定的Date,求此Date前days天的Date，比如 2012-06-25 10:11:10 前90天的日期为2012-03-27 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回Date
	 */
	public static Date getBeforeDateByDays(Date endDate,int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return cal.getTime();
	}
    
    /**
	 * 根据给定的String格式的Date,求此String前days天的Date，比如 2012-06-25 10:11:10 前90天的日期为2012-03-27 10:11:10
	 * 当然如果天数传的是正整数，则是之前的天数，如果传的是负整数，则是之后多少天
	 * @param endDate
	 * @param days 天数
	 * @return 返回Date
	 */
	public static Date getBeforeDateByDays(String strEndDate,int days){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(strEndDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return cal.getTime();
	}
    
    /**
	 * 根据给定的Date,求此Date前days天的Date，比如 2012-06-25 10:11:10 前90天的日期为2012-03-27 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回String
	 */
	public static String getBeforeStringByDays(Date endDate,int days){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return sdf.format(cal.getTime());
	}
    
    /**
	 * 根据给定的String格式的Date,求此Date前days天的Date，比如 2012-06-25 10:11:10 前90天的日期为2012-03-27 10:11:10
	 * @param endDate
	 * @param days 天数
	 * @return 返回String
	 */
	public static String getBeforeStringByDays(String strEndDate,int days){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(strEndDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return sdf.format(cal.getTime());
	}

    /**
     * 得到当前月份的周期开始日期
     * @param currentDate 当前日期
     * @return 当前月份的周期开始日期
     */
    public static Date getCurBeginCycleDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        String year = "" + calendar.get(Calendar.YEAR);
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        String dateStr = year + "-" + month + "-01 00:00:00";
        return DateUtil.parserLong(dateStr);
    }
    
    /**
     * 取得当前周期的周期结束日期
     * @param currentDate 当前日期
     * @return 当前周期的周期结束日期
     */
    public static Date getCurEndCycleDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        String year = "" + calendar.get(Calendar.YEAR);
        String month = (calendar.get(Calendar.MONTH) + 2) + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        String dateStr = year + "-" + month + "-01 23:59:59";
        calendar.setTime(DateUtil.parserLong(dateStr));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获得小时和分
     * @return
     */
    public static String getCurrentHourAndMinute() {
		String current = "";
		Calendar c = Calendar.getInstance();
		int H = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		current = H + ":" + m;
		return current;
	}

    /**
	 * 根据给定日期格式的字符串和转化模式将字符串转化为Date类型
	 * @param init 初始给定的日期字符串，比如 20120921
	 * @param lPattern 转化模式，比如 "yyyyMMdd"，注意：次模式和上面的日期类型要保持一致
	 * @return
	 */
	public static Date getDateByString(String init,String lPattern){
		SimpleDateFormat sdf = new SimpleDateFormat(lPattern);
		Date d = null;
		try {
			 d = sdf.parse(init);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

    /**
     * 
     * 返回指定日期的下一月
     * @param date 需要转换的日期
     * @param field 需要更改的域-目前只对月份域起作用
     * @return
     */
    public static long getDateField(Date date, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (field == Calendar.MONTH)
            return cal.get(field) + 1;
        else
            return cal.get(field);
    }

    public static String getDateStrByFormat(String format){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(new Date());
		}catch(Exception e){
			return "";
		}
	}

    /**
	 * 输出当前时间的年月日格式
	 * @return
	 */
	public static String getDateStrgYMD(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		return sdf.format(new Date());
	}
    
    /**
	 * 输出当前时间的年月格式
	 * @return
	 */
	public static String getDateStrYM(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMM);
		return sdf.format(new Date());
	}

    /**
	 * 输出当前时间的年月日格式
	 * @return
	 */
	public static String getDateStrYMD(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
		return sdf.format(new Date());
	}

    /**
	 * 输出当前时间的年月日时分格式
	 * @return
	 */
	public static String getDateStrYMDHM(){
		SimpleDateFormat sdf = new SimpleDateFormat(timeNoSecondFormatter);
		return sdf.format(new Date());
	}

    /**
	 * 输出当前时间的年月日时分秒格式(yyyy-MM-dd HH:mm:ss);
	 * @return
	 */
	public static String getDateStrYMDHMS(){
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		return sdf.format(new Date());
	}
    
    /**
	 * 输出当前时间的年月日时分秒格式
	 * @return
	 */
	public static String getDateStrYMDMS(){
		SimpleDateFormat sdf = new SimpleDateFormat(timeStringFormatter);
		return sdf.format(new Date());
	}
	
	/**
	 * 输出当前时间的年月日时分秒格式
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date getDateYMDHMS () throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(time24Formatter);
		return parse(sdf.format(new Date()),time24Formatter);
	}
	
	/**
     * 获取开始和结束日期之间的间隔日期
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param roundingMode 舍入方式 见 BigDecimal的定义
     * @return 相隔的日期数
     */
    public static long getDaysBetweenDate(Date startDate, Date endDate,
            int roundingMode) {

        BigDecimal bStart = new BigDecimal(startDate.getTime());
        BigDecimal bEnd = new BigDecimal(endDate.getTime());
        BigDecimal bUnit = new BigDecimal(MILLSECOND_OF_DAY);
        
        return (bEnd.subtract(bStart)).divide(bUnit, roundingMode).longValue();
    }
	
	/**
     * 获取开始和结束日期之间的间隔日期
     * @param startDate 开始日期字符串 比如：“2012-06-21 10:10:10”
     * @param endDate 结束日期字符串 比如：“2012-06-25 10:10:10”
     * @param roundingMode 舍入方式 见 BigDecimal的定义
     * @return 相隔的日期数
     */
    public static long getDaysBetweenString(String start, String end,
            int roundingMode) {
    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormatter);
    	Date startDate = null;
    	Date endDate = null;
		try {
			startDate = sdf.parse(start);
			endDate = sdf.parse(end);
	        BigDecimal bStart = new BigDecimal(startDate.getTime());
	        BigDecimal bEnd = new BigDecimal(endDate.getTime());
	        BigDecimal bUnit = new BigDecimal(MILLSECOND_OF_DAY);
	        
	        return (bEnd.subtract(bStart)).divide(bUnit, roundingMode).longValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1l;
    }
	
	/**
	 * 取得当前年的第一天和最后一天，数组中第一个元素是当前年的第一天，第二个元素是当前年的最后一天，日期格式为yyyy-MM-dd
	 * @return
	 */
	public static String[] getFirstAndLastDayOfCurrentYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String [] s = new String[2];
		s[0] = year+"-01-01";
		s[1] = year+"-12-31";
		return s;
	}
	
	/**
     * 获取两个日期之间相差的月份数
     * @param cal1 开始日期
     * @param cal2 结束日期
     * @param flag false 为全月舍
     * @return 返回的月份数
     */
    public static long getMonthsBetweenDate(Calendar cal1, Calendar cal2,
            boolean flag) {
        long month = 0L;
        while (cal1.before(cal2)) {
            cal1.add(Calendar.MONTH, 1);
            month++;
            if (flag) {
                if ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
                        && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                        && (cal1.get(Calendar.DAY_OF_MONTH) > cal2
                                .get(Calendar.DAY_OF_MONTH))) {
                    month--;
                    break;
                }
                if ((cal1.get(Calendar.MONTH) > cal2.get(Calendar.MONTH))
                        && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))

                ) {
                    month--;
                    break;
                }
            }
        }
        return month;
    }
	
	/**
     * 获取两个日期之间相差的月份数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param flag false 为全月舍
     * @return 返回的月份数
     */
    public static long getMonthsBetweenDate(Date startDate, Date endDate,
            boolean flag) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(startDate);
        cal2.setTime(endDate);
        if (endDate.before(startDate)) {
            cal1.setTime(endDate);
            cal2.setTime(startDate);
        }

        cal1.clear(Calendar.MILLISECOND);
        cal1.clear(Calendar.SECOND);
        cal1.clear(Calendar.MINUTE);
        cal1.clear(Calendar.HOUR_OF_DAY);

        cal2.clear(Calendar.MILLISECOND);
        cal2.clear(Calendar.SECOND);
        cal2.clear(Calendar.MINUTE);
        cal2.clear(Calendar.HOUR_OF_DAY);

        return getMonthsBetweenDate(cal1, cal2, flag);
    }
	
	/**
     * 得到下nextCycle周期的月份
     * @param currentDate当前日期
     * @param nextCycle下nextCycle周期
     * @return 下nextCycle周期
     */
    public static Date getNextCycleDate(Date currentDate, long nextCycle) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        String year = "" + calendar.get(Calendar.YEAR);
        nextCycle++;
        String month = (calendar.get(Calendar.MONTH) + nextCycle) + "";
        if (month.length() < 2) {
            month = "0" + month;
        }
        String dateStr = year + "-" + month + "-01 00:00:00";
        return DateUtil.parserLong(dateStr);
    }
	
	public static Date getTimeEnd(String endTime){
		Date edTime = null;
		if(null !=endTime && !"".equals(endTime)){
			try {
				edTime = parse(endTime,dateFormatter);				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Calendar cal = Calendar.getInstance();
		if(edTime != null){
			cal.setTime(edTime);
		}
		cal.add(Calendar.DATE, 1);
		edTime = cal.getTime();
		return edTime;
	}
	
	public static Date getTimeStat(String startTime){
		Date stTime = null;
		if(null !=startTime && !"".equals(startTime)){
			 try {
				stTime = parse(startTime,dateFormatter);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stTime;
	}
	
	/**
	 * 得到上个月
	 * @return
	 */
	public static String lastMonth(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStrYMD = sdf.format(cal.getTime());
		String dateStrMonth = dateStrYMD.substring(4,dateStrYMD.length()-2);
		return dateStrMonth;
	}
	
	/**
     * 根据传入的字符串，欲转换成的日期样式，返回转换后的日期
     * @param ldate -日期
     * @param pattern -具体的值范围见java.text.SimpleDateFormat的说明
     * @see java.text.SimpleDateFormat
     * @return Date 转换后的日期
     * @throws ParseException
     */
    public static Date parse(String ldate, String pattern)
            throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(ldate);
    }
	
	/**
     * 格式化长日期,日期格式为 yyyy-MM-dd HH:mm:ss
     * @param strDate 符合格式的字符串
     * @return 格式后的日期
     */
    public static Date parserLong(String strDate) {
        Date result = null;
        try {
            result = DateUtil.parse(strDate, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;        
    }
	
	/**
     * 格式化长日期,日期格式为 yyyyMMddHHmmss
     * @param strDate 符合格式的字符串
     * @return 格式后的日期
     */
    public static Date parserLong2(String strDate) {
        Date result = null;
        try {
            result = DateUtil.parse(strDate, "yyyyMMddHHmmss");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;        
    }
	
	/**
     * 格式化短日期,日期格式为yyyy-MM-dd
     * @param strDate 符合格式的字符串
     * @return 格式后的日期
     * @throws ParseException
     */
    public static Date parserShort(String strDate) {
        Date result = null;
        try {
            result = DateUtil.parse(strDate, "yyyy-MM-dd");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
    }
	
	/**
	 * 传入Date型数组，排序后得到最小日期和最大日期放入ArrayList中，第一个是最小的，第二个是最大的。
	 * @param d
	 * @return
	 */
	public static ArrayList<Date> returnHighAndLowDate(Date [] d){
		int len = d.length;
		Date temp = new Date();
		/**
		 * 冒泡排序法
		 */
		for(int i = 0; i < len -1; i++){
			for ( int j = len - 1 ; j > i ; j--){
				if(d[j].compareTo(d[j-1]) < 0 ){
					temp = d[j];
					d[j] = d[j-1];
					d[j-1] = temp;
				}
			}
		}
		
		ArrayList<Date> array = new ArrayList<Date>();
		array.add(d[0]);
		array.add(d[len-1]);
		return array;
	}
	
	/**
     * 将long型的秒转换为XX时XX分XX秒
     * @param seconds
     * @return
     */
	public static String secondsToTime(long seconds) {
		long h = 0;
		long d = 0;
		long s = 0;
		long temp = seconds % 3600;
		if (seconds > 3600) {
			h = seconds / 3600;
			if (temp != 0) {
				if (temp > 60) {
					d = temp / 60;
					if (temp % 60 != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			d = seconds / 60;
			if (seconds % 60 != 0) {
				s = seconds % 60;
			}
		}
		return (h < 10 ? "0" + h : h+"") + "时" + (d < 10 ? "0" + d : d+"") + "分"
			+ (s < 10 ? "0" + s : s+"")+"秒";
	}
	
	
	/**
	 * 得到本年和上个月，数组形式，第一个为四位年，如2012，第二个为二位月，如08
	 * @return
	 */
	public static String[] yearAndLastMonth(){
		String [] yearLastMonth = new String[2];
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStrYMD = sdf.format(cal.getTime());
		yearLastMonth[0] = dateStrYMD.substring(0, 4);
		yearLastMonth[1] = dateStrYMD.substring(4,dateStrYMD.length()-2);
		return yearLastMonth;
	}
	
	
	/**
	 * 得到本年和上个月，字符串形式201208
	 * @return
	 */
	public static String yearAndLastMonthStr(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStrYMD = sdf.format(cal.getTime());
		return dateStrYMD;
	}
	
}
