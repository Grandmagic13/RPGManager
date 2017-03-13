package rpg_database.character_sheet;

public class CharacterAttribute {

	private int value;
	private boolean isMajor;

	protected CharacterAttribute(int value, boolean isMajor) {
		this.value = value;
		this.isMajor = isMajor;
	}

	public int getValue() {
		return 0;
	}

	public boolean isMajor() {
		return isMajor;
	}
}
