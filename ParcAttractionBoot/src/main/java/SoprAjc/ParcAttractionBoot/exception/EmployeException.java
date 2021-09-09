package SoprAjc.ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST, reason="Données invalides")
public class EmployeException extends RuntimeException {

	public EmployeException(String message) {
		super(message);
	}

}
