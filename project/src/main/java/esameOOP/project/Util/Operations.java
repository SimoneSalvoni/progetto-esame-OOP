package esameOOP.project.Util;
import java.util.Locale;

import esameOOP.project.Exceptions.TokenNotFoundException;
import esameOOP.project.Model.*;
import java.io.*;
public class Operations {

	public Operations() {
		// TODO Auto-generated constructor stub
	}
	

	public static boolean checkKeywords(String s, String[] words) {
		for (String d : words ) {
			if (s.contains(d.toLowerCase(Locale.ITALIAN)) ||s.contains(d)) return true;
		}
		return false;
	}
	
	public static Post[] requestAndParseJSON() {
		return null;
		
	}
	
	public static String readFromFile(String fileName) throws TokenNotFoundException{
		String token = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			token = reader.readLine();
			reader.close();
		} catch (IOException e) {
			throw new TokenNotFoundException("No access token found");
		}
		if (token==null) throw new TokenNotFoundException("No access token found");
		return token;

	}
}
