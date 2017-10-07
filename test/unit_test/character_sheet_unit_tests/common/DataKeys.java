package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.Race;
import rpg_database.character_sheet.SpecializationClasses;

public enum DataKeys {
	ARMOR_PENALTY(Integer.class), ARMOR_RATING(Integer.class), ARMOR_TYPE(Armors.class), BACKGROUND(Background.class),
	BACKGROUND_ARRAY(Background.class), BASE_CLASS(BaseClasses.class), EXPECTED_MAJORITY(Boolean.class), EXPECTED_RESTRICTION(Boolean.class),
	FIELD(Fields.class), LANGUAGES_ARRAY(Languages.class), MAJORITY_FIELD(Fields.class), SPECIALIZATION_CLASS(SpecializationClasses.class),
	STRAIN(Integer.class), VALUE_FIELD(Fields.class), VALUE(Integer.class), ATTRIBUTE_REQUIREMENT(null), RACES_ARRAY(Race.class),
	SPEED(Integer.class);

	private Class<?> keyClass;

	private DataKeys(Class<?> keyClass) {
		this.keyClass = keyClass;
	}

	public Class<?> getKeyClass() {
		return keyClass;
	}

}
