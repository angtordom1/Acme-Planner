package acme.features.spam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamService {
	

	@Autowired
	protected SpamReposistory repository;

	
	

	
	public List<String> getSpamWordsByString(final String s){
		final List<String> res= new ArrayList<>();
		final List<String> spamWords=this.repository.getSpamWords();

		final int tam=s.split(" [\\\\s@&.?$+-]+").length;
		
		Integer nWords = 0;
		
		int i=0;
		while(i<spamWords.size()) {
			final String word=spamWords.get(i);
			if(s.contains(word)) { res.add(word); nWords+=word.length();}
			
			i++;
		}
		
		if(this.repository.getUmbral()>=100*nWords/tam) res.add("true");
		else res.add("false");
		
		
		return res;
	}
}
