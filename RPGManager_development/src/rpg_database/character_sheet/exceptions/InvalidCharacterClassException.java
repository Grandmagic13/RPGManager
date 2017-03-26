package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class InvalidCharacterClassException extends RuntimeException {
	public InvalidCharacterClassException(String message) {
		super(message);
	}
}
