package ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class FilterException extends RuntimeException{

	public FilterException(String message,Throwable err){
		super(message,err);
	}
	
	public FilterException(String message){
		super(message);
	}
	
	public FilterException(){
		super("Données invalides dans filtre");
	}
}
