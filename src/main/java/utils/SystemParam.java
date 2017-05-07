package utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import business.entities.BangThamSo;;

public class SystemParam {
	public static List<BangThamSo> listBTS = new ArrayList<BangThamSo>() ;
	public static String getValueByKey(String key){
		for (Iterator<BangThamSo> iterator = listBTS.iterator(); iterator.hasNext();) {
			BangThamSo bts = iterator.next();
			if (bts.getTenThamSo().equals(key)) {
				return bts.getGiaTri();
			}
		}
		return null;
	}
}
