package esameOOP.project.Util;

import java.util.Locale;

import esameOOP.project.Exceptions.TokenNotFoundException;
import java.io.*;

/**
 * La classe esegue operazioni come checkKeywords e readFromFile
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class Operations {

	/**
	 * Controlla se i post contengono determinate parole.
	 * 
	 * @param s     contenente il testo dei post
	 * @param words contenente le parole da ricercare
	 * @return booleano true nel caso in cui sono state trovte le parole ricercate,
	 *         false se non sono state trovte
	 */

	public static boolean checkKeywords(String s, String[] words) {
		for (String d : words) {
			if (s.contains(d.toLowerCase(Locale.ITALIAN)) || s.contains(d))
				return true;
		}
		return false;
	}

	/**
	 * Legge il token da un file il cui nome viene passato come parametro.
	 * 
	 * @param fileName nome del file
	 * @return String contente il token
	 * @throws TokenNotFoundException se vengono generati errori di token non
	 *                                trovato
	 */
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
