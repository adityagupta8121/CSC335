/**
 * @author adityagupta
 * Spell Check Singleton
 * Loading Dictionary; Instance is called in Window
 */

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

public class SpellCheck {

	private static SpellCheck instance;
	private Map<String, String> dictionary = new HashMap<String, String>();

	public static SpellCheck getInstance(){
		if (instance == null){
			synchronized (SpellCheck.class) {
				if (instance == null){
					instance = new SpellCheck();
				}
			}
		}
		
		
		return instance;
	}
	
	public void LoadDictionary(String dictionaryPath){
		System.out.println("Reading...");
		BufferedReader reader =  null;
		try {
			String word;
			reader = new BufferedReader(new FileReader(dictionaryPath));
			while ((word = reader.readLine()) != null) {
				this.dictionary.put(word, word);
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public Boolean isMisspelled(String word){
		return !this.dictionary.containsKey(word);
	}

	
}
