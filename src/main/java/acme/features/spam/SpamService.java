package acme.features.spam;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamService {
	

	@Autowired
	protected SpamReposistory repository;

	
	
	public Collection<String> getSpamWordsByLetter(final char c) {
		return this.repository.finByLetter(c);
	}
	
	public Set<String> getSpamWordsByString(final String s){
		final Set<String> res= new HashSet<>();
		
		final String[] words= s.split(" ");
		final int tam=words.length;
		
		final int i=0;
		while(i<tam && res.size()/tam<=0.1) {
			final String word=words[i];
			final Collection<String> pSpam=this.getSpamWordsByLetter(word.charAt(0));
			if(pSpam.contains(word)) res.add(word);
		}
		
		
		return res;
	}
}
