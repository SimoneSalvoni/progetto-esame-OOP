package esameOOP.project.Filters;

import java.util.Calendar;

import esameOOP.project.Model.Post;

public class BetweenDatesFilter extends DateFilter {
	private Calendar date1;
	private Calendar date2;

	public BetweenDatesFilter(String field, String operator, Calendar date1, Calendar date2) {
		super(field, operator);
		this.date1 = date1;
		this.date2 = date2;
	}

	@Override
	public boolean checkFilter(Post post) {
		int y1, y2, y3, m1, m2, m3, d1, d2, d3;
		y1 = post.getCreated_time().get(Calendar.YEAR);
		y2 = date1.get(Calendar.YEAR);
		y3 = date2.get(Calendar.YEAR);
		m1 = post.getCreated_time().get(Calendar.MONTH) + 1;
		m2 = date1.get(Calendar.MONTH) + 1;
		m3 = date2.get(Calendar.MONTH) + 1;
		d1 = post.getCreated_time().get(Calendar.DAY_OF_MONTH);
		d2 = date1.get(Calendar.DAY_OF_MONTH);
		d3 = date2.get(Calendar.DAY_OF_MONTH);
		if (((y1 > y2) && (y1 < y3)) || ((y1 == y2) && (m1 > m2)) && (m1 < m3)
				|| ((y1 == y2) && (m1 == m2) && (d1 >= d2) && (d1 <= d3)))
			return true;
		return false;
	}
}
