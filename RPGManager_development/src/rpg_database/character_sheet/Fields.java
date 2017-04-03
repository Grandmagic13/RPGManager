package rpg_database.character_sheet;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class), BACKGROUND(Background.class),
	BASECLASS(BaseClasses.class), SPECIALIZATIONCLASSES(SpecializationClassesSet.class), LEVEL(Integer.class), DEFENSE(Integer.class),
	ARMOR_RATING(Integer.class), HEALTH_POINTS(Integer.class), MANA_POINTS(Integer.class), APPEARANCE(String.class),
	DISTINGUISHING_FEATURES(String.class), OFTEN_USED_EQUIPMENT(String.class), GOALS_AND_TIES(String.class), EQUIPMENT(String.class),
	MONEY(Money.class), GOLD_COIN(Integer.class, MONEY), SILVER_COIN(Integer.class, MONEY), COPPER_COIN(Integer.class, MONEY),
	STRENGTH(CharacterAttribute.class), COMMUNICATION(CharacterAttribute.class), CONSTITUTION(CharacterAttribute.class),
	CUNNING(CharacterAttribute.class), DEXTERITY(CharacterAttribute.class), MAGIC(CharacterAttribute.class), PERCEPTION(CharacterAttribute.class),
	WILLPOWER(CharacterAttribute.class), STRENGTH_VALUE(Integer.class, STRENGTH), COMMUNICATION_VALUE(Integer.class, COMMUNICATION),
	CONSTITUTION_VALUE(Integer.class, CONSTITUTION), CUNNING_VALUE(Integer.class, CUNNING), DEXTERITY_VALUE(Integer.class, DEXTERITY),
	MAGIC_VALUE(Integer.class, MAGIC), PERCEPTION_VALUE(Integer.class, PERCEPTION), WILLPOWER_VALUE(Integer.class, WILLPOWER),
	STRENGTH_MAJORITY(Boolean.class, STRENGTH), COMMUNICATION_MAJORITY(Boolean.class, COMMUNICATION),
	CONSTITUTION_MAJORITY(Boolean.class, CONSTITUTION), CUNNING_MAJORITY(Boolean.class, CUNNING), DEXTERITY_MAJORITY(Boolean.class, DEXTERITY),
	MAGIC_MAJORITY(Boolean.class, MAGIC), PERCEPTION_MAJORITY(Boolean.class, PERCEPTION), WILLPOWER_MAJORITY(Boolean.class, WILLPOWER);

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
		if (containingField == null)
			throw new NullPointerException(String.format("%s is not contained by another field!", this.toString()));
		else
			return containingField;
	}

	public boolean isContainted() {
		return containingField != null;
	}
}