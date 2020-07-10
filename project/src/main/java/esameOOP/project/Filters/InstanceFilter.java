package esameOOP.project.Filters;

import java.util.Calendar;

import esameOOP.project.Exceptions.InvalidFilterException;
import esameOOP.project.Model.Post.Politic;

public class InstanceFilter {

	public static MediaFilter createMediaFilter(String field, String type) throws InvalidFilterException{
		if (type.compareTo("status") == 0 || type.compareTo("photo") == 0 || type.compareTo("video") == 0
				|| type.compareTo("link") == 0)
			return new MediaFilter(field, type);
		else
			throw new InvalidFilterException("Not an allowed type of post");
	}
	
	public static PoliticFilter createPoliticFilter(String field, String category) throws InvalidFilterException{
		switch (category) {
		case "NON_POLITIC":
			return new PoliticFilter(field, Politic.NON_POLITIC);
		case "POLITIC":
			return new PoliticFilter(field, Politic.POLITIC);
		case "NATIONAL":
			return new PoliticFilter(field, Politic.NATIONAL);
		case "EU":
			return new PoliticFilter(field, Politic.EU);
		case "EXTRA_EU":
			return new PoliticFilter(field, Politic.EXTRA_EU);
		case "INTERNATIONAL": 
			return new PoliticFilter(field, Politic.INTERNATIONAL);
		default:
			throw new InvalidFilterException("Not an allowed category of post");
		}
	}
	public static DateFilter createDateFilter (String field, String operator,String d) 
			throws InvalidFilterException{
		switch (operator) {
		case "before" :{
			int y=Integer.parseInt(d.substring(0,3));
			int m=Integer.parseInt(d.substring(5,6));
			int day=Integer.parseInt(d.substring(8,9));
			Calendar date =  Calendar.getInstance();
			date.set(y, m,day);
			return new BeforeDateFilter(field, operator, date);
		}
		case "after":{
			int y=Integer.parseInt(d.substring(0,3));
			int m=Integer.parseInt(d.substring(5,6));
			int day=Integer.parseInt(d.substring(8,9));
			Calendar date =  Calendar.getInstance();
			date.set(y, m,day);
			return new AfterDateFilter(field, operator, date);
		}
		case "between":{
			int y1=Integer.parseInt(d.substring(0,3));
			int m1=Integer.parseInt(d.substring(5,6));
			int day1=Integer.parseInt(d.substring(8,9));
			int y2=Integer.parseInt(d.substring(11,14));
			int m2=Integer.parseInt(d.substring(16,17));
			int day2=Integer.parseInt(d.substring(19,20));
			Calendar Date1 =Calendar.getInstance();
			Calendar Date2=Calendar.getInstance();
			Date1.set(y1, m1,day1);
			Date2.set(y2, m2,day2);
			return new BetweenDatesFilter(field, operator, Date1, Date2);
			}
		default: throw new InvalidFilterException("Invalid operator");
		}
	}
	{
		
	}

}
