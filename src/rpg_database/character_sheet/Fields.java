package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum Fields {
	NAME(String.class), AGE(Integer.class), XP(Integer.class), GENDER(Gender.class), SPEED(Integer.class), BACKGROUND(Background.class),
	BASECLASS(BaseClasses.class), SPECIALIZATIONCLASSES(SpecializationClassesSet.class), LEVEL(Integer.class), DEFENSE(Integer.class),
	ARMOR_RATING(Integer.class), HEALTH_POINTS(Integer.class), MANA_POINTS(Integer.class), APPEARANCE(String.class),
	DISTINGUISHING_FEATURES(String.class), OFTEN_USED_EQUIPMENT(String.class), GOALS_AND_TIES(String.class), EQUIPMENT(String.class),
	MONEY(Money.class), GOLD_COIN(Integer.class, MONEY), SILVER_COIN(Integer.class, MONEY), COPPER_COIN(Integer.class, MONEY),
	LANGUAGES(LanguagesSet.class), STRENGTH(CharacterAttribute.class), COMMUNICATION(CharacterAttribute.class),
	CONSTITUTION(CharacterAttribute.class), CUNNING(CharacterAttribute.class), DEXTERITY(CharacterAttribute.class), MAGIC(CharacterAttribute.class),
	PERCEPTION(CharacterAttribute.class), WILLPOWER(CharacterAttribute.class), STRENGTH_VALUE(Integer.class, STRENGTH),
	COMMUNICATION_VALUE(Integer.class, COMMUNICATION), CONSTITUTION_VALUE(Integer.class, CONSTITUTION), CUNNING_VALUE(Integer.class, CUNNING),
	DEXTERITY_VALUE(Integer.class, DEXTERITY), MAGIC_VALUE(Integer.class, MAGIC), PERCEPTION_VALUE(Integer.class, PERCEPTION),
	WILLPOWER_VALUE(Integer.class, WILLPOWER), STRENGTH_MAJORITY(Boolean.class, STRENGTH), COMMUNICATION_MAJORITY(Boolean.class, COMMUNICATION),
	CONSTITUTION_MAJORITY(Boolean.class, CONSTITUTION), CUNNING_MAJORITY(Boolean.class, CUNNING), DEXTERITY_MAJORITY(Boolean.class, DEXTERITY),
	MAGIC_MAJORITY(Boolean.class, MAGIC), PERCEPTION_MAJORITY(Boolean.class, PERCEPTION), WILLPOWER_MAJORITY(Boolean.class, WILLPOWER),
	ARMOR_TYPE(Armors.class), STRAIN(Integer.class), WEAPON_GROUPS(WeaponGroupsSet.class), FOCUSES(FocusesSet.class), RACE(Race.class);

	private final Class<?> fieldClass;
	private final Fields containingField;
	private final String text;

	private Fields(Class<?> fieldClass) {
		this(fieldClass, null);
	}

	private Fields(Class<?> fieldClass, Fields containingField) {
		this.fieldClass = fieldClass;
		this.containingField = containingField;
		this.text = generateEnumText(this.name());
	}

	public Class<?> getAllowedClass() {
		return fieldClass;
	}

	public Fields getContainingField() {
		if (containingField == null)
			throw new NullPointerException(String.format("%s is not contained by another field!", this.name()));
		else
			return containingField;
	}

	public boolean isContainted() {
		return containingField != null;
	}

	@Override
	public String toString() {
		return text;
	}
}