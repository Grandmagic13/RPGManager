package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class InvalidLevelException extends RuntimeException {
	public InvalidLevelException(String message) {
		super(message);
	}
}
