package com.lafengmaker.util;

import java.math.BigDecimal;

public class Caculate {
	public static double add(double d1,double d2){
		return add(Double.toString(d1), Double.toString(d2));
	}
	public static double sub(double d1,double d2){
		return sub(Double.toString(d1), Double.toString(d2));
	}
	public static double mul(double d1,double d2){
		return mul(Double.toString(d1), Double.toString(d2));
	}
	public static double div(double d1,double d2){
		return div(Double.toString(d1), Double.toString(d2));
	}
	public static double add(String d1,String d2){
		BigDecimal b1=new BigDecimal(d1);
		BigDecimal b2=new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}
	public static double sub(String d1,String d2){
		BigDecimal b1=new BigDecimal(d1);
		BigDecimal b2=new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}
	public static double mul(String d1,String d2){
		BigDecimal b1=new BigDecimal(d1);
		BigDecimal b2=new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}
	public static double div(String d1,String d2){
		BigDecimal b1=new BigDecimal(d1);
		BigDecimal b2=new BigDecimal(d2);
		return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
