/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.dataapi;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2019Äê06ÔÂ03ÈÕ 11:10 mazexiang Exp $
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        ZoneId zone3 = ZoneId.of("GMT+8");
        ZoneId zone4 = ZoneId.of("Asia/Shanghai");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
        System.out.println(zone3.getRules());
        System.out.println(zone4.getRules());

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        LocalTime now3 = LocalTime.now(zone3);
        LocalTime now4 = LocalTime.now(zone4);
        System.out.println(now1.isBefore(now2));  // false

        System.out.println(now3.getHour()+"_"+now3.getMinute());

        System.out.println(now4.getHour()+"_"+now4.getMinute());

        System.out.println(System.currentTimeMillis());

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239
    }
}