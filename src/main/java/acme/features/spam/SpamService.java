package acme.features.spam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;

@Service
public class SpamService {
	

	@Autowired
	protected SpamReposistory repository;

	

	public List<String> getSpamWordsByString(final String s){
		
		final List<String> res = new ArrayList<>();
		final List<SpamWord> spamWords = this.repository.getSpamWords();
		final int tam = s.split(" ").length;
		
		Double nWords = 0.;
		int i=0;
		
		while(i<spamWords.size()) {
			final SpamWord spamWord = spamWords.get(i);
			final String word = spamWord.getWord();
			final Integer size = spamWord.getSize();
			final Pattern p = Pattern.compile("\\b"+Pattern.quote(word)+"\\b", Pattern.CASE_INSENSITIVE);
			final Matcher m = p.matcher(s);
			while(m.find()) {
					if(!res.contains(word)) res.add(word);
					nWords += size;
			}
			i++;
		}	
		
		final Double p = 100*nWords/tam;
		
		if(this.repository.getUmbral() > p) res.clear();
		
		return res;
	}
}
