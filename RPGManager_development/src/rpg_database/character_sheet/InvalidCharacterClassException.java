package rpg_database.character_sheet;

public class InvalidCharacterClassException extends RuntimeException {
	public InvalidCharacterClassException(String message) {
		super(message);
	}
}
