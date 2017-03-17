package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class handling format of string in application such as Date, Time, Currency
 * 
 * @author TNS
 *
 */
public class StringFormatUtil {

	// private contructor to force noninstantiability
	private StringFormatUtil() {

	}

	/**
	 * Format {@link Date} value to format 'dd/MM/yyyy'.
	 * 
	 * @param value
	 *            : {@link Date} value to be formatted
	 * @return : {@link String} formatted
	 */
	public static String shortDateTime(Date value) {
		return new SimpleDateFormat("dd/MM/yyyy").format(value);
	}

	/**
	 * Format {@link Double} value to currency {@link String}.<br>
	 * For example : 9,000,000 represents for 9 millions.
	 * 
	 * @param value
	 * @return
	 */
	public static String toCurrencyString(double value) {
		return String.format("#,###,###", value);
	}
}
