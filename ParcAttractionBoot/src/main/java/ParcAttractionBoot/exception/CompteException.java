package ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class CompteException extends RuntimeException {
	
	
	public CompteException(String message,Throwable err){
		super(message,err);
	}
	
	public CompteException(String message){
		super(message);
	}
	
	public CompteException(){
		super("Données invalides dans compte");
	}
	

}
