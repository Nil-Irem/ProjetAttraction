package SoprAjc.ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class CommoditeException extends RuntimeException{

	public CommoditeException(String message) {
		super(message);	
		}

}
