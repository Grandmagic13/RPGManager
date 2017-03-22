package rpg_database.character_sheet;

import java.util.HashSet;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class), BACKGROUND(Background.class),
	BASECLASS(BaseClasses.class), SPECIALIZATIONCLASS(SpecializationClasses.class), LEVEL(Integer.class), DEFENSE(Integer.class),
	ARMOR_RATING(Integer.class), HEALTH_POINTS(Integer.class), MANA_POINTS(Integer.class), APPEARANCE(String.class),
	DISTINGUISHING_FEATURES(String.class), OFTEN_USED_EQUIPMENT(String.class), GOALS_AND_TIES(String.class), EQUIPMENT(String.class),
	MONEY(Money.class), GOLD_COIN(Integer.class, MONEY), SILVER_COIN(Integer.class, MONEY), COPPER_COIN(Integer.class, MONEY),
	LANGUAGES_SETTER(LanguagesSetter.class), LANGUAGES(HashSet.class, LANGUAGES_SETTER);

	private Class<?> fieldClass;
	private Fields containingField;

	private Fields(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
		this.containingField = null;
	}

	private Fields(Class<?> fieldClass, Fields containingField) {
		this.fieldClass = fieldClass;
		this.containingField = containingField;
	}

	public Class<?> getAllowedClass() {
		return fieldClass;
	}

	public Fields getContainingField() {
		return containingField;
	}

	public boolean isContainted() {
		return containingField != null;
	}
}