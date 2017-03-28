package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class InvalidCharacterClassException extends CharacterSheetException {
	public InvalidCharacterClassException(String message) {
		super(message);
	}
}
