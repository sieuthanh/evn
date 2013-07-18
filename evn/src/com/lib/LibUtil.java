package com.lib;

public class LibUtil {
	public static String getSelected(int source, int des) {
		if (source == des) {
			return "selected";
		} else
			return "";
	}
}
