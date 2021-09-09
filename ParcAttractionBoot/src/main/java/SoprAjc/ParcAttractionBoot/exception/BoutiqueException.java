package SoprAjc.ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class BoutiqueException extends RuntimeException{

	public BoutiqueException(String message,Throwable err){
		super(message,err);
	}
	
	public BoutiqueException(String message){
		super(message);
	}
	
	public BoutiqueException(){
		super("Données invalides dans boutique");
	}

}
