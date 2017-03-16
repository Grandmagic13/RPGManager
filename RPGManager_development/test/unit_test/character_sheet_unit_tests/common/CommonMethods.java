package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClassesSet;

public class CommonMethods {

	public static int LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION = 6;
	public static int LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION = 14;
	public static int LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION = 22;

	public static CharacterSheet createCharacterSheetWithCustomClassesAndLevel(BaseClasses baseClass, SpecializationClassesSet specializationClasses,
			int level) {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.LEVEL, level);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClasses);
		return characterSheet;
	}

}
