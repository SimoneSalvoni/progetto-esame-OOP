package esameOOP.project.Util;

import java.util.Locale;

import esameOOP.project.Exceptions.TokenNotFoundException;
import java.io.*;

public class Operations {

	public static boolean checkKeywords(String s, String[] words) {
		for (String d : words) {
			if (s.contains(d.toLowerCase(Locale.ITALIAN)) || s.contains(d))
				return true;
		}
		return false;
	}

	public static String readFromFile(String fileName) throws TokenNotFoundException {
		String token = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			token = reader.readLine();
			reader.close();
		} catch (IOException e) {
			throw new TokenNotFoundException();
		}
		if (token == null)
			throw new TokenNotFoundException();
		return token;

	}
}
