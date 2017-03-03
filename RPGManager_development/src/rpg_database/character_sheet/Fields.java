package rpg_database.character_sheet;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class), BACKGROUND(Background.class),
	BASECLASS(BaseClasses.class), SPECIALIZATIONCLASS(SpecializationClasses.class), MONEY(Integer.class), LEVEL(Integer.class),
	DEFENSE(Integer.class), ARMOR_RATING(Integer.class), HEALTH_POINTS(Integer.class), MANA_POINTS(Integer.class),
	APPEARANCE(String.class), DISTINGUISHING_FEATURES(String.class), OFTEN_USED_EQUIPMENT(String.class), GOALS_AND_TIES(String.class),
	EQUIPMENT(String.class), GOLD_COIN(Integer.class), SILVER_COIN(Integer.class), COPPER_COIN(Integer.class);

	private Class<?> fieldClass;

	private Fields(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
	}

	public Class<?> getAllowedClass() {
		return fieldClass;
	}
}