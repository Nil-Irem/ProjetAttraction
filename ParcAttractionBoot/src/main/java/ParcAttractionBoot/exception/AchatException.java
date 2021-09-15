package ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class AchatException extends RuntimeException{

	public AchatException(String message,Throwable err){
		super(message,err);
	}
	
	public AchatException(String message){
		super(message);
	}
	
	public AchatException(){
		super("Données invalides dans achat");
	}
}