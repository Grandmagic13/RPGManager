package rpg_database.character_sheet;

import java.util.HashSet;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class), BACKGROUND(Background.class),
	BASECLASS(BaseClasses.class), SPECIALIZATIONCLASS(SpecializationClasses.class), LEVEL(Integer.class), DEFENSE(Integer.class),
	ARMOR_RATING(Integer.class), HEALTH_POINTS(Integer.class), MANA_POINTS(Integer.class), APPEARANCE(String.class),
	DISTINGUISHING_FEATURES(String.class), OFTEN_USED_EQUIPMENT(String.class), GOALS_AND_TIES(String.class), EQUIPMENT(String.class),
	MONEY(Money.class), GOLD_COIN(Integer.class, MONEY), SILVER_COIN(Integer.class, MONEY), COPPER_COIN(Integer.class, MONEY),
	LANGUAGES_SETTER(LanguagesSetter.class), LANGUAGES(HashSet.class, LANGUAGES_SETTER),
	ATTRIBUTE_STRENGTH(CharacterAttribute.class), ATTRIBUTE_COMMUNICATION(CharacterAttribute.class), ATTRIBUTE_CONSTITUTION(CharacterAttribute.class),
	ATTRIBUTE_CUNNING(CharacterAttribute.class), ATTRIBUTE_DEXTERITY(CharacterAttribute.class), ATTRIBUTE_MAGIC(CharacterAttribute.class),
	ATTRIBUTE_PERCEPTION(CharacterAttribute.class), ATTRIBUTE_WILLPOWER(CharacterAttribute.class),
	ATTRIBUTE_STRENGTH_VALUE(Integer.class, ATTRIBUTE_STRENGTH), ATTRIBUTE_COMMUNICATION_VALUE(Integer.class, ATTRIBUTE_COMMUNICATION),
	ATTRIBUTE_CONSTITUTION_VALUE(Integer.class, ATTRIBUTE_CONSTITUTION), ATTRIBUTE_CUNNING_VALUE(Integer.class, ATTRIBUTE_CUNNING),
	ATTRIBUTE_DEXTERITY_VALUE(Integer.class, ATTRIBUTE_DEXTERITY), ATTRIBUTE_MAGIC_VALUE(Integer.class, ATTRIBUTE_MAGIC),
	ATTRIBUTE_PERCEPTION_VALUE(Integer.class, ATTRIBUTE_PERCEPTION), ATTRIBUTE_WILLPOWER_VALUE(Integer.class, ATTRIBUTE_WILLPOWER),
	ATTRIBUTE_STRENGTH_MAJORITY(Boolean.class, ATTRIBUTE_STRENGTH), ATTRIBUTE_COMMUNICATION_MAJORITY(Boolean.class, ATTRIBUTE_COMMUNICATION),
	ATTRIBUTE_CONSTITUTION_MAJORITY(Boolean.class, ATTRIBUTE_CONSTITUTION), ATTRIBUTE_CUNNING_MAJORITY(Boolean.class, ATTRIBUTE_CUNNING),
	ATTRIBUTE_DEXTERITY_MAJORITY(Boolean.class, ATTRIBUTE_DEXTERITY), ATTRIBUTE_MAGIC_MAJORITY(Boolean.class, ATTRIBUTE_MAGIC),
	ATTRIBUTE_PERCEPTION_MAJORITY(Boolean.class, ATTRIBUTE_PERCEPTION), ATTRIBUTE_WILLPOWER_MAJORITY(Boolean.class, ATTRIBUTE_WILLPOWER);

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