package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class InvalidLevelException extends CharacterSheetException {
	public InvalidLevelException(String message) {
		super(message);
	}
}
