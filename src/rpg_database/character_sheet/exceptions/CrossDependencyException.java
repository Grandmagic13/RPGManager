package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class CrossDependencyException extends CharacterSheetException {
	public CrossDependencyException(String message) {
		super(message);
	}
}
