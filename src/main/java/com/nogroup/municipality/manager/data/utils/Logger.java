package com.nogroup.municipality.manager.data.utils;

public class Logger{
    /**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;

    public static final boolean LOGGING = true ;
    public static void print(String msg){
        if(LOGGING){
            System.out.println(msg) ;
        }
    }
}
