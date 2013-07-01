package br.feevale.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date getPrimeiroDomingoCalendario() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		
		return calendar.getTime();
	}
	
	public static Date getUltimoSabadoCalendario() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return calendar.getTime();
	}

}
