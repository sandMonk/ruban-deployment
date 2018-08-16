/**
 * 
 */
package com.ruban.deployment.common.datasource.annocation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ruban.deployment.common.datasource.Source;

/**
 * 在service的func上使用 <br>
 * 描述使用何种数据源
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TheDataSource {

	/**
	 * 需要使用什么数据源
	 */
	Source value() default Source.MASTER;

}
