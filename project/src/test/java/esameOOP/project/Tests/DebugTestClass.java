package esameOOP.project.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import esameOOP.project.Filters.FilterHandler;
import esameOOP.project.Model.Post;
import esameOOP.project.Model.Post.Politic;
import esameOOP.project.Model.Stat;


class DebugTestClass {

	private Post post1,post2,post3;
	private Stat stat;
	private Calendar date1,date2,date3;
	private Vector<Post> v;
	private FilterHandler filter;

	@BeforeEach
	void setUp() throws Exception {
		date1=Calendar.getInstance();
		date2=Calendar.getInstance();
		date3=Calendar.getInstance();
		date1.set(2020,1,1,10,30,0);
		date2.set(2020,3,1,16,30,0);
		date3.set(2020,5,1,22,30,0);
		post1=new Post("2","Questo è un messaggio di test", date1,null,"status");
		post2=new Post("3","Questo è un messaggio di test politico", date2,"www.provapolitica.com","link");
		post3=new Post("34","Questo è un messaggio di test libero come gli USA", date3,"www.linkdiunafoto.com","photo");
		v = new Vector<Post>();
		v.add(post1);
		v.add(post2);
		v.add(post3);
		stat=new Stat(v);
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	void politicControlTest() {
		assertAll("politic",()->assertEquals(Politic.NON_POLITIC,post1.getPolitic()),
				()->assertEquals(Politic.POLITIC,post2.getPolitic()),
						()->assertEquals(Politic.INTERNATIONAL,post3.getPolitic()));
	}
	@Test
	void politicStatTest() {
		assertEquals(1,stat.getStatPolitics().getNumPoliticPost());
		assertEquals(0,stat.getStatPolitics().getNumNationalPost());
		assertEquals(0,stat.getStatPolitics().getNumEUPost());
		assertEquals(0,stat.getStatPolitics().getNumExtraEUPost());
		assertEquals(1,stat.getStatPolitics().getNumInternationalPost());
	}
	@Test
	void lenghtStatTest() {
		assertEquals(29, stat.getStatLenght().getMinChar());
		assertEquals(49, stat.getStatLenght().getMaxChar());
		assertEquals(116/3,stat.getStatLenght().getAvgChar());
		double stdev=Math.sqrt(((29-116/3)^2+(38-116/3)^2+(49-116/3)^2)/3);
		assertEquals(stdev,stat.getStatLenght().getStdDev());
	}
	//questo da cambiare una volta che rendiamo postPerHour un HashMap
	@Test
	void timeStatTest() {
		double[] h = new double[24];
		for (int i=0;i<24;i++) h[i]=0;
		h[10]=1;
		h[16]=1;
		h[22]=1;
		assertEquals(h,stat.getStatTime().getPostPerHour());
	}
	
	@Test
	void mediaFilterTest() {
		Vector <Post> v2;
		String body="{\"type\":\"photo\"}";
		filter= new FilterHandler(body);
		v2=filter.filterFeed(v);
		assertEquals(post3, v2.elementAt(0));
	}
	@Test
	void politicFilterTest() {
		Vector <Post> v2;
		String body ="{\"category\":\"NON_POLITIC\"}";
		filter=new FilterHandler(body);
		v2=filter.filterFeed(v);
		assertEquals(post1, v2.elementAt(0));
	}
	@Test
	void dateFilterTest() {
		Vector <Post> v2;
		String body ="{\"created_time\":{\"after\":\"2020/02/01\"}}";
		filter=new FilterHandler(body);
		v2=filter.filterFeed(v);
		assertEquals(post2, v2.elementAt(0));
		assertEquals(post3, v2.elementAt(1));
	}
	@Test
	void doubleFilterTest() {
		Vector <Post> v2;
		String body="[{\"category\":\"INTERNATIONAL\"},OR,{\"type\":\"status\"}]";
		filter=new FilterHandler(body);
		v2=filter.filterFeed(v);
		assertEquals(post1, v2.elementAt(0));
		assertEquals(post3, v2.elementAt(1));
	}
}
