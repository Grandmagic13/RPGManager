package rpg_database.character_sheet.exceptions;

@SuppressWarnings("serial")
public class CoinOutOfBoundsException extends RuntimeException {
	public CoinOutOfBoundsException(String message) {
		super(message);
	}
}
