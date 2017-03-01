package rpg_database.character_sheet.character_class;

public class InvalidCharacterClassException extends RuntimeException {
	public InvalidCharacterClassException(String message) {
		super(message);
	}
}
