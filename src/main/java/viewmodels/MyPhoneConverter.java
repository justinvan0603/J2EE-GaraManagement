package viewmodels;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;

@SuppressWarnings("rawtypes")
public class MyPhoneConverter implements Converter {

	/**
	 * The method coerceToUi() is invoked when loading ViewModel's property to
	 * component and its return type should correspond to bound component
	 * attribute's value[1]. The coerceToBean() is invoked when saving. If you
	 * only need to one way conversion, you can leave unused method empty.
	 */

	@Override
	public Object coerceToBean(Object val, Component arg1, BindContext arg2) {
		return val;
	}

	@Override
	public Object coerceToUi(Object val, Component arg1, BindContext arg2) {

		if (val == null)
			return null;
		String value = (String) val;
		value = value.trim();
		if (value.length() != 10)
			return val;
		value = "(" + value.substring(0, 3) + ") " + value.substring(3, 6) + "-" + value.substring(6, 10);
		return value;
	}
}
