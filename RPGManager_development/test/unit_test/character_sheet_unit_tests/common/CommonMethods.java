package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;

public class CommonMethods {

	public static CharacterSheet createCharacterSheetWithCustomClasses(BaseClasses baseClass, SpecializationClasses specializationClass) {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, specializationClass);
		return characterSheet;
	}

}
