package esameOOP.project.Util;
import java.util.Vector;
import java.util.Locale;
import esameOOP.project.Model.*;
import java.io.*;
public class Operations {

	public Operations() {
		// TODO Auto-generated constructor stub
	}
	

	public static boolean checkKeywords(String s, String[] words) {
		for (String d : words ) {
			if (s.contains(s.toLowerCase(Locale.ITALIAN)) ||s.contains(d)) return true;
		}
		return false;
	}
	
	public static Post[] requestAndParseJSON() {
		return null;
		
	}
	
	public static String readFromFile(String fileName){
		String token = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			token = reader.readLine();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;

	}
}
