package com.w2a.rough;

import java.util.Date;

public class Test{
	public static void main(String args[]){
		Date d = new Date();
		String s= d.toString();
		//s  = s.replace(" ","_").replace(":","_");
		s = s.substring(4, 19);
		s = s.replace(" ","_").replace(":","-");
		s = s+System.currentTimeMillis();
		System.out.println(s);
		System.currentTimeMillis();
	}
	
}
