/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ln.evalmodel.common.uitl;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false)
     *
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }

    /**
     * 格式化日期
     */
    public static Date parseDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     */
    public static Date parseTimeMinutes(String date) {
        return parse(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 格式化日期
     */
    public static Date parseTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期转换为Timestamp
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (beginDate != null && endDate != null) {
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        }
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    //相加
    public static final String ADD = "add";
    //相减
    public static final String MINUS = "minus";


    /**
     * 更新日期
     *
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/3 10:34
     */
    public static Date updateDate(Date date, String updateType, int num) {
        //整数往后推,负数往前移动
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        //年
        if ("year".equals(updateType)) {
            calendar.add(calendar.YEAR, num);

        }
        //月
        if ("month".equals(updateType)) {
            calendar.add(calendar.MONTH, num);

        }
        //日
        if ("day".equals(updateType)) {

            calendar.add(calendar.DAY_OF_MONTH, num);
        }
        //小时
        if ("hour".equals(updateType)) {

            calendar.add(calendar.HOUR_OF_DAY, num);
        }
        return calendar.getTime();
    }


    /**
     * 计算日期相差天数,不包含边界
     *
     * @param
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/1 10:24
     */
    public static int daysBetween(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 60 * 60 * 24) - 1;

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算日期相差天数,包含边界
     *
     * @param
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/1 10:24
     */
    public static int daysBetweenBoundary(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = ((time2 - time1) / (1000 * 60 * 60 * 24)) + 1;

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 计算日期相差月份
     * (边界为包含endDate月)
     *
     * @param
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/25 20:50
     */
    public static int getMonths(Date startDate, Date endDate) {
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        before.setTime(startDate);
        after.setTime(endDate);
        int result = after.get(Calendar.MONTH) - before.get(Calendar.MONTH);
        int month = (after.get(Calendar.YEAR) - before.get(Calendar.YEAR)) * 12;
        int num = result + month;
        return num + 1;
    }

    /**
     * 比较日期
     *
     * @param -1小于，0等于，1，大于
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/1 10:28
     */
    public static Integer compareDate(Date startDate, Date endDate) {
        Calendar calStartDate = Calendar.getInstance();
        calStartDate.setTime(startDate);
        Calendar calendDate = Calendar.getInstance();
        calendDate.setTime(endDate);
        //小于
        if (startDate.before(endDate)) {
            return -1;
        }
        //大于
        if (startDate.after(endDate)) {
            return 1;
        }
        //等于
        if (startDate.equals(endDate)) {
            return 0;
        }
        return null;
    }


    /**
     * 得到指定月的天数
     */
    public static int getMonthDayNumber(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 重置日期号数
     *
     * @param
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/25 19:58
     */
    public static Date setDateDay(Date date, Integer day) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, day);
        return a.getTime();
    }


    public static Integer getMonth(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        int month = a.get(Calendar.MONTH) + 1;
        return month;
    }


    /**
     * 设置日期为1号
     *
     * @param
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/25 18:49
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        return a.getTime();
    }

    /**
     * 设置日期某月最后一天
     *
     * @param
     * @return
     * @throws
     * @Description: todo
     * @author Wayne
     * @date 2019/7/25 18:54
     */
    public static Date getMonthLastDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return a.getTime();
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @return 日期数组
     */
    public static ArrayList<String> pastDay(String time, Integer num) {
        if (num == null) {
            num = 6;
        } else {
            num = num - 1;
        }
        ArrayList<String> pastDaysList = new ArrayList<>();
        try {
            //我这里传来的时间是个string类型的，所以要先转为date类型的。
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            for (int i = num; i >= 0; i--) {
                pastDaysList.add(getPastDate(i, date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pastDaysList;
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @return 日期数组
     */
    public static ArrayList<String> pastDay(Date time) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            pastDaysList.add(getPastDate(i, time));
        }
        return pastDaysList;
    }

    /**
     * 获取某天的最后一秒对应的时间:当天的23:59.000
     *
     * @param day
     * @return
     */
    public static Date dayEnd(Date day) {
        if (day == null) {
            return null;
        }
        long theDay = dayStart(day).getTime();
        final long ONE_DAY_MILLIS = 1 * 24 * 60 * 60 * 1000;

        return new Date(theDay + ONE_DAY_MILLIS - 1);
    }

    /**
     * 获取某天的第一秒对应的时间:当天的00:00.000
     *
     * @param date
     * @return
     */
    public static Date dayStart(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return Date.from(localDateTime
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .atZone(zoneId)
                .toInstant());
    }
}
