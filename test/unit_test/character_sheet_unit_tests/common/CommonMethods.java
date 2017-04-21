package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterAttribute;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClassesSet;

public class CommonMethods {

	public static int LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION = 6;
	public static int LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION = 14;
	public static int LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION = 22;

	public static CharacterSheet createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses baseClass,
			SpecializationClassesSet specializationClasses, int level) {
		CharacterSheet characterSheet = setAllAttributesTo5(new CharacterSheet("CharacterSheet"));
		characterSheet.setData(Fields.LEVEL, level);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClasses);
		return characterSheet;
	}

	public static CharacterSheet setAllAttributesTo5(CharacterSheet characterSheet) {
		for (Fields attribute : CharacterAttribute.ATTRIBUTES) {
			characterSheet.setData(Fields.valueOf(attribute.name() + "_VALUE"), 5);
		}
		return characterSheet;
	}

}
