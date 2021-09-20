package ParcAttractionBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ParcAttractionBoot.repositories.CompteRepository;


@Service
public class ConsoleService implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private CompteRepository compteRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		//initPassword();
		
	}
	
	private void initPassword() {
		compteRepo.findAll().stream().forEach(u->{
			u.setPassword(passwordEncoder.encode(u.getPassword()));
			compteRepo.save(u);
		});
	}

}
