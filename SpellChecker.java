
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if(str.length() > 1){
			return str.substring(1);
		} else {
			return "";
		}
	}

	public static int levenshtein(String word1, String word2) {
		
		if(word1.isEmpty()){
			return word2.length();
		}
		if(word2.isEmpty()){
			return word1.length();
		}
		
		if (word1.charAt(0) == word2.charAt(0)){
		return levenshtein(tail(word1), tail(word2));
		}
	
		
		int check1 = levenshtein(tail(word1), word2) + 1;
		int check2 = levenshtein(word1, tail(word2)) +1;
		int check3= levenshtein(tail(word1), tail(word2))+1;
		
		return Math.min(check3,(Math.min(check1, check2)));
	}



	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
			int maxInteger= 10000;
			String closestWord= word;

		for (int i = 0; i < dictionary.length; i++) {
			String checker = dictionary[i];

			int distance= levenshtein(checker, word);

			if(distance< maxInteger){
				maxInteger= distance;
				closestWord= checker;
			}
		}

			if (maxInteger <= threshold) {
				return closestWord;
				
			} else {
				return word;
			}


			
		}

	}

