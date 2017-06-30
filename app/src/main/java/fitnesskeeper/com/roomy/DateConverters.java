package fitnesskeeper.com.roomy;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * @author stuckj, created on 6/29/17.
 */
public class DateConverters
{
    @TypeConverter
    public static Long dateToLong(final Date date)
    {
        return (date == null) ? null : date.getTime();
    }

    @TypeConverter
    public static Date longToDate(final Long time)
    {
        return (time == null) ? null : new Date(time);
    }
}