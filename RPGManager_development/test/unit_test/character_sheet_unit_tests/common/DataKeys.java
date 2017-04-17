package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;

public enum DataKeys {
	ARMOR_PENALTY(Integer.class), ARMOR_RATING(Integer.class), ARMOR_TYPE(Armors.class), BACKGROUND(Background.class), BASE_CLASS(BaseClasses.class),
	EXPECTED_MAJORITY(Boolean.class), FIELD(Fields.class), LANGUAGES_ARRAY(Languages.class), MAJORITY_FIELD(Fields.class), STRAIN(Integer.class),
	VALUE_FIELD(Fields.class);

	private Class<?> keyClass;

	private DataKeys(Class<?> keyClass) {
		this.keyClass = keyClass;
	}

	public Class<?> getKeyClass() {
		return keyClass;
	}

}
