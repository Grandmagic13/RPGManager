package rpg_database.character_sheet;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class), BACKGROUND(Background.class),
	BASECLASS(BaseClasses.class), SPECIALIZATIONCLASS(SpecializationClasses.class), LEVEL(Integer.class), DEFENSE(Integer.class),
	ARMOR_RATING(Integer.class), HEALTH_POINTS(Integer.class), MANA_POINTS(Integer.class), APPEARANCE(String.class),
	DISTINGUISHING_FEATURES(String.class), OFTEN_USED_EQUIPMENT(String.class), GOALS_AND_TIES(String.class), EQUIPMENT(String.class),
	MONEY(Money.class), GOLD_COIN(Integer.class, MONEY), SILVER_COIN(Integer.class, MONEY), COPPER_COIN(Integer.class, MONEY),
	ATTRIBUTE_STRENGTH(CharacterAttribute.class), ATTRIBUTE_COMMUNICATION(CharacterAttribute.class), ATTRIBUTE_CONSTITUTION(CharacterAttribute.class),
	ATTRIBUTE_CUNNING(CharacterAttribute.class), ATTRIBUTE_DEXTERITY(CharacterAttribute.class), ATTRIBUTE_MAGIC(CharacterAttribute.class),
	ATTRIBUTE_PERCEPTION(CharacterAttribute.class), ATTRIBUTE_WILLPOWER(CharacterAttribute.class);

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