package unit_test.character_sheet_unit_tests.common;

import rpg_database.character_sheet.Armors;

public enum DataKeys {
	ARMOR_PENALTY(Integer.class), ARMOR_RATING(Integer.class), ARMOR_TYPE(Armors.class), STRAIN(Integer.class);

	private Class<?> keyClass;

	private DataKeys(Class<?> keyClass) {
		this.keyClass = keyClass;
	}

	public Class<?> getKeyClass() {
		return keyClass;
	}

}
