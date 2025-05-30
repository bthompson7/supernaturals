package commands.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import supernaturals.Permission;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseCommand {
	String[] aliases();
	Permission permission();
	
	String usage() default "";
	String desc();
	
	boolean player() default true;
	boolean console() default true;
	
	int min() default 0;
	int max() default -1;
	
	boolean hidden() default false;
}