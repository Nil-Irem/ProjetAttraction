package SoprAjc.ParcAttractionBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SoprAjc.ParcAttractionBoot.model.Compte;
import SoprAjc.ParcAttractionBoot.model.CompteUserDetails;
import SoprAjc.ParcAttractionBoot.repositories.CompteRepository;

@Service
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	private CompteRepository compteRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional <Compte> opt=compteRepo.findByLogin(username);
		if(opt.isPresent()) {
			return new CompteUserDetails(opt.get());
		}
		throw new UsernameNotFoundException("compte inconnu");
	}

}
