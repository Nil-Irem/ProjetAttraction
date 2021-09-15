package ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)

public class AttractionException extends RuntimeException{


	public AttractionException(String message,Throwable err){
		super(message,err);
	}
	
	
	public AttractionException(String message){
		super(message);
	}
	
	
	public AttractionException(){
		super("Données invalides");
	}
}
