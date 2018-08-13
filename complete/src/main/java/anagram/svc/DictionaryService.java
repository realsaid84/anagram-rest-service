package anagram.svc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * A Dictionary Service to lazy load Words Dictionary at Application startup. <br>
 * It also provides mechanisms for basic Create, Read and Delete operations of the Dictionary.
 * @see #init()
 * @see #addToDictionary(String)
 * @see #deleteWord(String)
 * @see #printAnagrams(String)
 * @author slasisi
 *
 */
@Service
public class DictionaryService {

	private static final String NEWLINE_DELIMITER = "\\n";

	private static final String DICTIONARY_FILE_PATH = "classpath:assets/words.txt";

	private static Logger logger = LoggerFactory.getLogger(DictionaryService.class);

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private DictionaryWordRepository dictionaryRepository;

	private AnagramGenerator anagram;

	public DictionaryService() {
		anagram = new AnagramGenerator();
	}

	/**
	 * Method called after DictionaryMap Bean Initialisation
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	public void init() throws IOException {
		Resource resource = resourceLoader.getResource(DICTIONARY_FILE_PATH);
		logger.debug("Loading Dictionary from {}", DICTIONARY_FILE_PATH);
		try (BufferedReader fileBufferReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String fileLineContent;
			while ((fileLineContent = fileBufferReader.readLine()) != null) {
				DictionaryWord word = new DictionaryWord(fileLineContent);
				dictionaryRepository.save(word);
			}
			logger.debug("Succesfully loaded {} into InMemory DB", DICTIONARY_FILE_PATH);
		} catch (IOException e) {
			logger.error("Error Loading the Dictionary Map into Memory", e);
			throw e;
		}

	}

	public DictionaryWord addToDictionary(String word) {
		logger.info("Saving {} to database", word);
		DictionaryWord dictionaryWord = new DictionaryWord(word);
		return dictionaryRepository.save(dictionaryWord);
	}

	public void deleteWord(String word) {
		logger.info("Deleting {} from database", word);
		List<DictionaryWord> dictionaryWords = dictionaryRepository.findByWord(word);
		if (dictionaryWords != null && !dictionaryWords.isEmpty()) {
			for (DictionaryWord wordToDelete : dictionaryWords) {
				dictionaryRepository.delete(wordToDelete);
				logger.info("Deleted {}:{} from database", wordToDelete.getId(), word);
			}
		}
	}

	public String printAnagrams(String word) {
		logger.info("Checking {} exists in database", word);
		String dictionaryWord = dictionaryRepository.findDistinctWord(word);
		if (isBlank(dictionaryWord)) {
			return "";
		}
		logger.info("Found {} in database, will now generate Anagrams", word);
		//String validAnagramsFromDictionary = generateValidAnagrams(anagram);
		return anagram.loadWord(dictionaryWord).getAnagrams();
	}

	/**
	 * Performs a DB check against a generated anagram <br>
	 * Checks if the word exist in the Dictionary <br>
	 * Returns only valid Anagrams that exist in the Dictionary.
	 * @param anagram
	 * @return
	 */
	protected String generateValidAnagrams(AnagramGenerator anagram) {
		StringBuilder validAnagramBuilder = new StringBuilder();
		String[] anagrams = anagram.getAnagramsArray();

		if (anagrams == null || anagrams.length < 1) {
			return "";
		}

		for (String candidateAnagram : anagrams) {
			String validAnagram = dictionaryRepository.findDistinctWord(candidateAnagram);
			if (!isBlank(validAnagram)) {
				validAnagramBuilder.append(validAnagram).append(NEWLINE_DELIMITER);
			}
		}
		return validAnagramBuilder.toString();
	}

	private boolean isBlank(String validAnagram) {
		return validAnagram == null || validAnagram.isEmpty();
	}

}
