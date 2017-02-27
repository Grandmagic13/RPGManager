package rpg_database.character_sheet;

import rpg_database.character_sheet.character_class.CharacterClass;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class),
	CHARACTERCLASS(CharacterClass.class), BACKGROUND(Background.class);

	private Class fieldClass;

	private Fields(Class fieldClass) {
		this.fieldClass = fieldClass;
	}

	public Class getAllowedClass() {
		return fieldClass;
	}
}