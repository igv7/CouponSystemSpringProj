//package com.igor.CouponSystemSpringProj.utils;
//
//import java.text.SimpleDateFormat;
//
//import javax.xml.bind.annotation.adapters.XmlAdapter;
//
//import org.springframework.expression.ParseException;
//
//public class SqlDateAdapter extends XmlAdapter<String, java.sql.Date> {
//
//	public String marshal(java.sql.Date d) {
//		return d.toString();
//	}
//
//	public java.sql.Date unmarshal(String v) throws java.text.ParseException {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		java.sql.Date sqlDate = null;
//		try {
//			java.util.Date convertedDate = dateFormat.parse(v);
//			sqlDate = new java.sql.Date(convertedDate.getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return sqlDate;
//	}
//
//}
