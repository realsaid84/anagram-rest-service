package anagram.svc;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dictionary Repository enables the support for manipulating DictionaryWord Objects<br>
 * @author slasisi
 */
public interface DictionaryWordRepository extends JpaRepository<DictionaryWord, Long> {
	
	List<DictionaryWord> findByWord(String word);
	
	@Query("select distinct(dictionary.word) from DictionaryWord dictionary where dictionary.word = :word")
	String findDistinctWord(@Param("word")String word);
}