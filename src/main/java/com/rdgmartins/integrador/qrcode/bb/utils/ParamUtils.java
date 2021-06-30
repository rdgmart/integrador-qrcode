package com.rdgmartins.integrador.qrcode.bb.utils;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParamUtils {

    public static String convertDateToString(Date dt, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        String dateToString = df.format(dt);
        return dateToString;
    }

    public static String convertDateToString(Date dt) {
        return convertDateToString(dt, "dd/MM/yyyy");
    }

    public static Date convertStringToDate(String dt) {
        return convertStringToDate(dt, "dd/MM/yyyy");
    }

    public static Date convertStringToDate(String dt, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long calcExpirationTimeQrCode(Date dueDate){
        if(dueDate == null) return 0;

        LocalDateTime dueDate235959 =
                dueDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().atTime(LocalTime.MAX);
        Duration duration = Duration.between(LocalDateTime.now(), dueDate235959);

        return duration.getSeconds();
    }

    public static boolean isExpired(Date dtGenerateAccessToken) {

        return dtGenerateAccessToken == null ||
                (new Date().getTime() - dtGenerateAccessToken.getTime()) > PropsConfigUtils.ACCESS_TOKEN_EXPIRE_IN;
    }

}
