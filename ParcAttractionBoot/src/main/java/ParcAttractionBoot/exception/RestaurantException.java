package ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST, reason="Données invalides")
public class RestaurantException extends RuntimeException {

	public RestaurantException(String message) {
		super(message);
	}

}
