package de.pvws.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class PvwsCalendar {

	/**
	 * 
	 * @return
	 */
	public static String getDateTime () {
		int iAdd = 0;
		String strDate;

		GregorianCalendar date = new GregorianCalendar(TimeZone.getDefault(), Locale.getDefault(Locale.Category.FORMAT));// (new GregorianCalendar(GregorianValendar).getTime()).toString();
		if (date.get(Calendar.AM_PM) == Calendar.PM)
			iAdd = 12;
		strDate = Integer.toString(date.get(Calendar.YEAR)) + "-";
		if (date.get(Calendar.MONTH) + 1 < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.MONTH) + 1) + "-";
		else
			strDate += Integer.toString(date.get(Calendar.MONTH) + 1) + "-";
		if (date.get(Calendar.DAY_OF_MONTH) < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.DAY_OF_MONTH)) + "-";
		else
			strDate += Integer.toString(date.get(Calendar.DAY_OF_MONTH)) + "-";
		if (date.get(Calendar.HOUR) + iAdd < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.HOUR) + iAdd) + ":";
		else
			strDate += Integer.toString(date.get(Calendar.HOUR) + iAdd) + ":";
		if (date.get(Calendar.MINUTE) < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.MINUTE)) + ":";
		else
			strDate += Integer.toString(date.get(Calendar.MINUTE)) + ":";
		if (date.get(Calendar.SECOND) < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.SECOND)) + ".";
		else
			strDate += Integer.toString(date.get(Calendar.SECOND)) + ".";
		if (date.get(Calendar.MILLISECOND) < 10)
			strDate += "00" + Integer.toString(date.get(Calendar.MILLISECOND));
		else if (date.get(Calendar.MILLISECOND) < 100)
			strDate += "0" + Integer.toString(date.get(Calendar.MILLISECOND));
		else
			strDate += Integer.toString(date.get(Calendar.MILLISECOND));
		
		return strDate;
	} // setDateTime


}
