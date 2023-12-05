package common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Utils {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static Long primaryKey = 1L;

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public static String parseLocalDateToString(LocalDate localDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(localDate);
    }

    public static Date parseToDate(String stringDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.parse(stringDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Long generatePrimaryKey() {
        return primaryKey++;
    }
}
