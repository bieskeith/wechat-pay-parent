package com.biminds.framework.date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 *
 * @author Kevin Lv
 * @date 2015年4月29日 下午7:48:53
 * @since 1.0.0
 */
public class DateTimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final long SECOND = 1000L;

    public static final long MINUTE = 60 * SECOND;

    public static final long HOUR = 60 * MINUTE;

    public static final long DAY = 24 * HOUR;

    public static final long WEEK = 7 * DAY;

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIME_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";

    public static final String YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";

    public static final String YEAR_MONTH_FORMAT = "yyyyMM";

    public static final String YEAR_FORMAT = "yyyy";

    public static final String MONTH_DAY_FORMAT = "MMdd";

    public static final String MONTH_FORMAT = "MM";

    public static final String DAY_FORMAT = "dd";

    /**
     * 给日期增加或减少天数
     *
     * @param date Date
     * @param day  天数
     * @return Date
     * @author 何珏 2014-11-20
     */
    public static Date addDays(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 给日期+00:00:00.000
     *
     * @param date Date
     * @return Date
     */
    public static Date getBeginDateByDate(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return new Date(gc.getTimeInMillis());
    }

    /**
     * 给日期+23:59:59.999
     *
     * @param date Date
     * @return Date
     */
    public static Date getEndDateByDate(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 23);
        gc.set(Calendar.MINUTE, 59);
        gc.set(Calendar.SECOND, 59);
        gc.set(Calendar.MILLISECOND, 999);
        return new Date(gc.getTimeInMillis());
    }

    /**
     * 将Date对象格式化为字符串，格式为：yyyy-MM-dd
     *
     * @param date Date
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    /**
     * 将日期格式化为字符串
     *
     * @param date    Date
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        return (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 将Date对象格式化为字符串，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param date Date
     * @return 日期字符串
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DATETIME_FORMAT).format(date);
    }

    /**
     * 将Date对象格式化为字符串，格式为：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param date Date
     * @return 日期字符串
     */
    public static String formatLongDateTime(Date date) {
        return formatDate(date, DATETIME_LONG_FORMAT);
    }

    /**
     * 将毫秒数格式化为日期字符串，格式为：yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param milliseconds the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @return 日期字符串
     */
    public static String formatLongDateTime(long milliseconds) {
        return formatDate(new Date(milliseconds), DATETIME_LONG_FORMAT);
    }

    /**
     * 尝试使用以下格式对日期字符串进行转换，转换失败返回null.
     * <ol>
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>yyyy-MM-dd HH:mm:ss.SSS
     * <li>yyyy-MM-dd
     * <li>yyyy年MM月dd日
     * </ol>
     *
     * @param dateStr 日期字符串
     * @return Date
     */
    public static Date parseDateWithTry(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        String[] formats = {DATETIME_FORMAT, // NL
                DATETIME_LONG_FORMAT, // NL
                DATE_FORMAT, // NL
                DATE_FORMAT_CHINESE // NL
        };

        Date date = null;
        try {
            date = DateUtils.parseDate(dateStr, formats);
        } catch (ParseException e) {
            logger.error("Cannot parse {} as date.", dateStr);
        }
        return date;
    }

    /**
     * 将毫秒数转换为Date对象
     *
     * @param milliseconds the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @return Date
     */
    public static Date parseLongDateTime(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return true相同；false不同
     * @author 何珏 2014-9-4
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        return (year1 == year2) && (month1 == month2) && (day1 == day2);
    }

    /**
     * 判断两个日期忽略年后是否为同一天，如生日
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return true相同；false不同
     * @author 何珏 2014-9-4
     */
    public static boolean isSameDayIgnoreYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        return (month1 == month2) && (day1 == day2);
    }

    /**
     * @param @param  date 日期对象
     * @param @param  formatType 日期输出格式
     * @param @return 字符串
     * @return String
     * @throws
     * @Title: convertDate
     * @Description: 将日期格式转换成字符串格式
     */
    public static String convertDate(Date date, int formatType) {
        String format = "";
        if (null == date) {
            return null;
        }
        switch (formatType) {
            case 1:
                format = "yyyy-MM-dd";
                break;
            case 2:
                format = "yyyyMMdd";
                break;
            case 3:
                format = "yyyy";
                break;
            case 4:
                format = "yyyyMMddHHmmss";
                break;
            case 5:
                format = "MM";
                break;
            case 6:
                format = "yyyy-MM";
                break;
            case 8:
                format = "yyyyMMddHHmm";
                break;
            case 9:
                format = "HH:mm";
                break;
            default:
                format = "yyyy-MM-dd HH:mm:ss";
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String newDate = sdf.format(date);
        return newDate;
    }

    /**
     * @param @param  date 字符串日期
     * @param @return 日期
     * @return Date
     * @throws
     * @Title: covertStrToDate
     * @Description: 将字符串日期格式转换成日期格式
     */
    public static Date covertStrToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * @param @param  day 当前日期
     * @param @return 具体天数
     * @return String
     * @throws
     * @Title: getLastDayOfMonth
     * @Description: 获取某个月最后一天
     */
    public static String getLastDayOfMonth(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate;
        int maxDayOfMonth = 0;
        try {
            newDate = sdf.parse(day + "-1");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newDate);
            maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(maxDayOfMonth);
    }

    /**
     * @param @param  date 当前日期
     * @param @return 当前年份
     * @return String
     * @throws
     * @Title: getCurrentYear
     * @Description: 根据当前日期获取当前年份
     */
    public static String getCurrentYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    /**
     * @param @param  date 当前日期
     * @param @return 中文日期
     * @return String
     * @throws
     * @Title: getCnDate
     * @Description: 根据当前日期转换成中文日期 如：3月6日
     */
    public static String getCnDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String[] dateArray = sdf.format(date).split("-");
        String month = dateArray[0].startsWith("0") ? dateArray[0].replace("0", "") : dateArray[0];
        String day = dateArray[1].startsWith("0") ? dateArray[1].replace("0", "") : dateArray[1];
        return month.concat("月").concat(day).concat("日");
    }

    /**
     * @param @param  t1 日期1
     * @param @param  t2 日期2
     * @param @return
     * @param @throws ParseException
     * @return int
     * @throws
     * @Title: getBetweenDays
     * @Description: 获取两个月份相差的天数
     */
    public static int getBetweenDays(String t1, String t2) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        int betweenDays = 0;
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

    /**
     * @param @param  t1
     * @param @param  t2
     * @param @return
     * @param @throws ParseException    设定文件
     * @return int    返回类型
     * @throws
     * @Title: getBetweenTwoDays
     * @Description: 获取2个时间的间隔时间
     */
    public static int getBetweenTwoDays(String t1, String t2)
            throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int betweenDays = 0;
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR)
                - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

    /**
     * 根据当前时间计算和指定月份计算出指定日期
     *
     * @param currDate 当前时间
     * @param monthNum 月份数量
     * @return
     * @author Kevin Lv
     * @date 2015年3月30日 下午5:12:17
     */
    public static Date getSpecificTimeByCurrentDate(Date currDate, int monthNum) {
        Calendar thisDay = Calendar.getInstance();
        thisDay.setTime(new Date());
        thisDay.add(Calendar.MONTH, monthNum);
        return thisDay.getTime();
    }

    /**
     * 根据当前时间计算和指定月份计算出指定日期
     *
     * @param currDate 当前时间
     * @param dayNum   月份数量
     * @return
     * @author Kevin Lv
     * @date 2015年3月30日 下午5:12:17
     */
    public static Date getSpecificDayByCurrentDate(Date currDate, int dayNum) {
        Calendar thisDay = Calendar.getInstance();
        thisDay.setTime(new Date());
        thisDay.add(Calendar.DAY_OF_YEAR, dayNum);
        return thisDay.getTime();
    }

    /**
     * 判断当前时间是否在某个一个区间范围内
     *
     * @param currentDate
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     * @author Kevin Lv
     * @date 2015年4月29日 上午10:04:41
     */
    public static Boolean isWithinRegionByCurrentTime(Date currentDate, String startTime, String endTime) {

        Boolean isRegion = false;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date currDate = null;
        try {
            currDate = df.parse(df.format(currentDate));
            Date start = df.parse(startTime);
            Date end = df.parse(endTime);

            if (currDate.before(end) && currDate.after(start)) {
                isRegion = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isRegion;
    }


    /**
     * 判读两个日期大小
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 1:第一个日期大于第二个日期 <br>
     * 0:两个日期相等 <br>
     * -1:第一个日期小于第二个日期
     * @throws ParseException
     * @author Wesley.Wei
     * @date 2015年5月12日 下午2:10:39
     */
    public static int judgeDates(String date1, String date2) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date jDate1 = dateFormat.parse(date1);
        Date jDate2 = dateFormat.parse(date2);

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(jDate1);
        c2.setTime(jDate2);

        return c1.compareTo(c2);

    }

    public static int judgeDate(String date1, String date2) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        Date jDate1 = dateFormat.parse(date1);
        Date jDate2 = dateFormat.parse(date2);

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(jDate1);
        c2.setTime(jDate2);

        return c1.compareTo(c2);
    }

    /**
     * 判读两个日期大小
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 1:第一个日期大于第二个日期 <br>
     * 0:两个日期相等 <br>
     * -1:第一个日期小于第二个日期
     * @throws ParseException
     * @author Wesley.Wei
     * @date 2015年5月12日 下午2:10:39
     */
    public static int judgeDates(Date date1, Date date2) throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return c1.compareTo(c2);

    }

    /**
     * @param @param  repairCycle
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getDateByCycle
     * @Description: 获取时间起点
     */
    public static Date getDateByCycle(String cycle) {
        Date date = null;
        if (cycle.equals("近一个月")) {
            date = getFrontDateByMonth(1);
        } else if (cycle.equals("近三个月")) {
            date = getFrontDateByMonth(3);
        } else if (cycle.equals("近六个月")) {
            date = getFrontDateByMonth(6);
        } else if (cycle.equals("近一年")) {
            date = getFrontDateByMonth(12);
        } else if (cycle.equals("近两年")) {
            date = getFrontDateByMonth(24);
        } else if (cycle.equals("近三年")) {
            date = getFrontDateByMonth(36);
        }
        return date;
    }

    /**
     * @param @param  monthNum
     * @param @return 设定文件
     * @return Date    返回类型
     * @throws
     * @Title: getFrontDateByMonth
     * @Description: 根据当前时间往前推x月的时间
     */
    public static Date getFrontDateByMonth(int monthNum) {

        Date cDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - monthNum);
        try {
            cDate = sdf.parse(sdf.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cDate;
    }

    /**
     * @param @param  cycle
     * @param @param  balanceTime
     * @param @return 设定文件
     * @return Boolean    返回类型
     * @throws
     * @Title: checkDmsRepairTime
     * @Description: 校验DMS维系历史显示时间
     */
    public static Boolean checkDmsRepairTime(String cycle, String balanceTime) {
        Boolean isOk = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = getDateByCycle(cycle);
        try {
            Date endDate = sdf.parse(convertDate(new Date(), 1983));
            Date businessDate = sdf.parse(balanceTime);
            if (businessDate.before(startDate) || businessDate.after(endDate)) {
                isOk = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isOk;
    }


    public static String getNowTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static Date covertStrToDateHHmm(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date newDate = null;
        try {
            newDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }


    /**
     * 主要获取同比和环比的日期
     *
     * @param date 当前日期(yyyy-MM)
     * @return {lastYear：去年的今天 ; lastMonth:上个月}
     */
    public static Map<String, String> getY2YAndM2MMap(String date) {
        Map<String, String> map = new HashMap<>();
        String year = date.split("-")[0];
        String month = date.split("-")[1];

        Integer y = Integer.parseInt(year) - 1;
        Integer m = Integer.parseInt(month) - 1;

        map.put("lastYear", y + "-" + month);
        map.put("lastMonth", year + "-" + (m < 10 ? "0" + m : m + ""));

        return map;
    }

}
