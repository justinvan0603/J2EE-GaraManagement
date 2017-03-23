package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Helper class for parsing date from string relating to application context
 * 
 * @author TNS
 *
 */
public class DateUtil {
	private DateUtil() {

	}

	/**
	 * Format String value should be 'dd/MM/yyyy'.
	 * 
	 * @param values
	 *            :
	 * @return : {@link Date} for input String array
	 */
	public static Date parseFromStringArray(String[] values) {
		Calendar calendar = Calendar.getInstance();
		// NOTE : month value should be minus 1
		calendar.set(Integer.parseInt(values[2]), Integer.parseInt(values[1]) - 1, Integer.parseInt(values[0]));
		return calendar.getTime();
	}

}
