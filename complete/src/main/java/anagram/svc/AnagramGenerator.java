package anagram.svc;

/**
 * Generates all possible of a loaded word
 * @author slasisi
 *
 */
public class AnagramGenerator {

    private char[] charArray;
    private StringBuilder anagramBuilder = null;
    
    private static final String NEWLINE_DELIMITER = "\n";
    
    public AnagramGenerator() {
    	
    }

    public AnagramGenerator(String word) {
        loadWord(word);
    }

	public AnagramGenerator loadWord(String word) {
		charArray = word.toCharArray();
		anagramBuilder = new StringBuilder();
        generateAnagram(charArray.length);
        return this;
	}
    
    public String getAnagrams() {
    		return (anagramBuilder != null) ? anagramBuilder.toString() :"";
    }
    
    public String[] getAnagramsArray() {
    		return getAnagrams().split("\\n");
    }

    private void changeOrder(int newsize) {
        int j;
        int pointAt = charArray.length - newsize;
        char temp = charArray[pointAt];

        for (j = pointAt + 1; j < charArray.length; j++) {
            charArray[j - 1] = charArray[j];
        }

        charArray[j - 1] = temp;

    }

   private void generateAnagram(int newsize) {
        if (newsize == 1) {
            return;
        }
        for (int i = 0; i < newsize; i++) {
        	generateAnagram(newsize - 1);
            if (newsize == 2) {
                appendAnagram();
            }
            changeOrder(newsize);
        }
    }

    private void appendAnagram() {
        for (int i = 0; i < charArray.length; i++) {
        		anagramBuilder.append(charArray[i]);
        }
        anagramBuilder.append(NEWLINE_DELIMITER);
    }
    

}
