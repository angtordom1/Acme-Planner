package acme.features.spam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;

@Service
public class SpamService {
	

	@Autowired
	protected SpamReposistory repository;

	

	public List<String> getSpamWordsByString(final String s){
		
		final List<String> res= new ArrayList<String>();
		
		final List<SpamWord> spamWords=this.repository.getSpamWords();

		final int tam=s.split(" ").length;
		
		Double nWords = 0.;
		
		int i=0;
		
		while(i<spamWords.size()) {
			
			final SpamWord word=spamWords.get(i);
			
			if(Pattern.compile(Pattern.quote(word.getWord()), Pattern.CASE_INSENSITIVE).matcher(s).find()) {
				
				res.add(word.getWord());
				
				nWords+=word.getSize();
				
			}
			
			i++;
		}
		
		final Double p=100*nWords/tam;
		
		if(this.repository.getUmbral()>=p) res.clear();
		
		return res;
	}
}
