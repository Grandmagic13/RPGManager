package rpg_database.character_sheet;

public enum Gender {
	MALE("Male"), FEMALE("Female");

	private final String text;

	private Gender(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}