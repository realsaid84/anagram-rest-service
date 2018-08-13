package anagram.svc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DictionaryWord {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
     
    private String word;
    
    public DictionaryWord() {
    	
    }

	public DictionaryWord(String word) {
		super();
		this.word = word;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	
	@Override
    public String toString() {
        return String.format(
                "DictionaryWord[id=%d, word='%s']", id, word);
    }
     
}