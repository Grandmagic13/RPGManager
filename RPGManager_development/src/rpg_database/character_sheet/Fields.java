package rpg_database.character_sheet;

import rpg_database.character_sheet.character_class.BaseClasses;
import rpg_database.character_sheet.character_class.CharacterClass;
import rpg_database.character_sheet.character_class.SpecializationClasses;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class),
	CHARACTERCLASS(CharacterClass.class), BACKGROUND(Background.class), BASECLASS(BaseClasses.class),
	SPECIALIZATIONCLASS(SpecializationClasses.class), MONEY(Integer.class);

	private Class fieldClass;

	private Fields(Class fieldClass) {
		this.fieldClass = fieldClass;
	}

	public Class getAllowedClass() {
		return fieldClass;
	}
}