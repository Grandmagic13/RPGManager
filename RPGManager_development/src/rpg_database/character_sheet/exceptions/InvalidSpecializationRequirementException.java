package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class InvalidSpecializationRequirementException extends CharacterSheetException {
	public InvalidSpecializationRequirementException(String message) {
		super(message);
	}
}
