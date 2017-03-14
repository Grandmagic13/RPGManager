package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;

public class CommonMethods {

	public static CharacterSheet createCharacterSheetWithCustomClasses(BaseClasses baseClass, SpecializationClassesSet specializationClasses) {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClasses);
		return characterSheet;
	}

}
