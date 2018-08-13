package anagram.svc;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController that exposes different Rest CRUD end-points as part of this WebService
 * @author slasisi
 */
@RestController
public class AnagramController {

    private final AtomicLong counter = new AtomicLong();
    
    @Autowired DictionaryService dictionaryService;
    
    private static Logger logger = LoggerFactory.getLogger(AnagramController.class);

    @RequestMapping("/anagram")
    public DictionaryWord greeting(@RequestParam(value="word", defaultValue="World") String word) {
    		DictionaryWord dictionaryWord = new DictionaryWord(word);
    		dictionaryWord.setId(counter.incrementAndGet());
        return dictionaryWord;
    }
    
    // -------------------Retrieve Anagrams of a given word------------------------------------------
    
    @RequestMapping(value = "/anagram/{word}", method = RequestMethod.GET)
    public ResponseEntity<?> getAnagrams(@PathVariable("word") String word) {
        logger.info("Fetching Anagrams for {}", word);
        String anagrams = dictionaryService.printAnagrams(word);
        if (anagrams == null || anagrams.isEmpty()) {
            logger.error("The word {} was not found in the dictionary", word);
            return new ResponseEntity<String>(String.format("The word %s was not found in the dictionary", word), 
            		HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(anagrams, HttpStatus.OK);
    }
    
    // -------------------Creates a new word in the Dictionary -------------------------------------------
    
    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public ResponseEntity<?> createWord(@RequestBody DictionaryWord word) {
        logger.info("Adding a new Word to the dictionary : {}", word);
        DictionaryWord savedWord = dictionaryService.addToDictionary(word.getWord());
        
        return new ResponseEntity<DictionaryWord>(savedWord, HttpStatus.CREATED);
    }
    
 // ------------------- Delete a word from the dictionary-----------------------------------------
    
    @RequestMapping(value = "/word/{word}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("word") String word) {
        logger.info("Fetching & Deleting Dictionary word {}", word);
        dictionaryService.deleteWord(word);
        return new ResponseEntity<String>("Deleted:"+word, HttpStatus.OK);
    }
}
