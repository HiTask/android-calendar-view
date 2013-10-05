package com.tivogi.helper;

import android.util.Log;

public class LogHelper {
	private static final String NULL_MESSAGE = "(null)";

	public static void d(Class<?> cls, String message) {
		d(cls.getSimpleName(), message);
	}

	public static void d(Class<?> cls, String message, Throwable tr) {
		d(cls.getSimpleName(), message, tr);
	}

	public static void d(Class<?> cls, Throwable tr) {
		d(cls.getSimpleName(), tr);
	}

	public static void d(String tag, String message) {
		d(tag, message, null);
	}

	public static void d(String tag, String message, Throwable tr) {
		String logMessage = replaceNull(message);
		if (tr != null) logMessage += " " + getThrowableMessage(tr);
		Log.d(tag, logMessage);
	}

	public static void d(String tag, Throwable tr) {
		d(tag, getThrowableMessage(tr));
	}

	public static void e(Class<?> cls, String message) {
		e(cls.getSimpleName(), message);
	}

	public static void e(Class<?> cls, String message, Throwable tr) {
		e(cls.getSimpleName(), message, tr);
	}

	public static void e(Class<?> cls, Throwable tr) {
		e(cls.getSimpleName(), tr);
	}

	public static void e(String tag, String message) {
		e(tag, message, null);
	}

	public static void e(String tag, String message, Throwable tr) {
		String logMessage = replaceNull(message);
		if (tr != null) logMessage += " " + getThrowableMessage(tr);
		Log.e(tag, logMessage);
	}

	public static void e(String tag, Throwable tr) {
		e(tag, getThrowableMessage(tr));
	}

	private static String getThrowableMessage(Throwable tr) {
		String result = tr.getMessage();
		if (result == null) result = tr.getClass().getName() + ": " + NULL_MESSAGE;
		return result;
	}

	public static void i(Class<?> cls, String message) {
		i(cls.getSimpleName(), message);
	}

	public static void i(Class<?> cls, String message, Throwable tr) {
		i(cls.getSimpleName(), message, tr);
	}

	public static void i(Class<?> cls, Throwable tr) {
		i(cls.getSimpleName(), tr);
	}

	public static void i(String tag, String message) {
		i(tag, message, null);
	}

	public static void i(String tag, String message, Throwable tr) {
		String logMessage = replaceNull(message);
		if (tr != null) logMessage += " " + getThrowableMessage(tr);
		Log.i(tag, logMessage);
	}

	public static void i(String tag, Throwable tr) {
		i(tag, getThrowableMessage(tr));
	}

	private static String replaceNull(String message) {
		if (message == null) return NULL_MESSAGE;
		return message;
	}

	public static void w(Class<?> cls, String message) {
		w(cls.getSimpleName(), message);
	}

	public static void w(Class<?> cls, String message, Throwable tr) {
		w(cls.getSimpleName(), message, tr);
	}

	public static void w(Class<?> cls, Throwable tr) {
		w(cls.getSimpleName(), tr);
	}

	public static void w(String tag, String message) {
		w(tag, message, null);
	}

	public static void w(String tag, String message, Throwable tr) {
		String logMessage = replaceNull(message);
		if (tr != null) logMessage += " " + getThrowableMessage(tr);
		Log.w(tag, logMessage);
	}

	public static void w(String tag, Throwable tr) {
		w(tag, getThrowableMessage(tr));
	}
}
