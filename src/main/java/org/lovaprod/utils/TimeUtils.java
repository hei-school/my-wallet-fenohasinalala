package org.lovaprod.utils;

import java.time.Instant;

public class TimeUtils {
    public static String currentDatetime(){
        return Instant.now().toString();
    }


}
