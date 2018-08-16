/**
 * 
 */
package com.ruban.deployment.common.log.log4j.util;

import java.util.Comparator;

import org.apache.log4j.Logger;

/**
 * logger排序，使用loggerName字符串自身排序 <br>
 * 
 */
public class LoggerComparator implements Comparator<Logger> {

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Logger o1, Logger o2) {
		if (null == o1 || null == o1.getName())
			return -1;
		if (null == o2 || null == o2.getName())
			return 1;
		return o1.getName().compareTo(o2.getName());
	}

}
