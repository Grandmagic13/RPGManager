package rpg_database.character_sheet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

public enum SpecializationClasses implements CustomSetter<SpecializationClasses> {
	NOT_APPLICABLE("N/A"), ARCANE_WARRIOR("Arcane Warrior", BaseClasses.MAGE), ASSASSIN("Assassin", BaseClasses.ROGUE),
	BARD("Bard", BaseClasses.ROGUE), BERSERKER("Berserker", BaseClasses.WARRIOR), BLOOD_MAGE("Blood Mage", BaseClasses.MAGE),
	CHAMPION("Champion", BaseClasses.WARRIOR), CHEVALIER("Chevalier", BaseClasses.WARRIOR), DUELIST("Duelist", BaseClasses.ROGUE),
	FORCE_MAGE("Force Mage", BaseClasses.MAGE), GUARDIAN("Guardian", BaseClasses.WARRIOR), KEEPER("Keeper", BaseClasses.MAGE),
	LEGIONNAIRE_SCOUT("Legionnaire Scout", BaseClasses.ROGUE), LEGIONNAIRE_WARRIOR("Legionnaire Warrior", BaseClasses.WARRIOR),
	LYRIUM_WARRIOR("Lyrium Warrior", BaseClasses.WARRIOR), NECROMANCER("Necromancer", BaseClasses.MAGE), MARKSMAN("Marksman", BaseClasses.ROGUE),
	RANGER("Ranger", BaseClasses.ROGUE), REAVER("Reaver", BaseClasses.WARRIOR), SAAREBAS("Saarebas", BaseClasses.MAGE),
	SHADOW("Shadow", BaseClasses.ROGUE), SHAPESHIFTER("Shapeshifter", BaseClasses.MAGE), SPIRIT_HEALER("Spirit Healer", BaseClasses.MAGE),
	SPIRIT_WARRIOR("Spirit Warrior", BaseClasses.WARRIOR), TEMPEST("Tempest", BaseClasses.ROGUE), TEMPLAR("Templar", BaseClasses.WARRIOR);

	private final String text;
	private final BaseClasses baseClass;

	private SpecializationClasses(final String text, BaseClasses baseClass) {
		this.text = text;
		this.baseClass = baseClass;
	}

	// TODO does this work or cause errors?
	private SpecializationClasses(final String text) {
		this.text = text;
		this.baseClass = null;
	}

	public boolean hasBase() {
		return baseClass != null;
	}

	public BaseClasses getBaseClass() {
		return baseClass;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		BaseClasses baseClass = characterSheet.getData(Fields.BASECLASS);
		if (isBaseClassCompatible(baseClass)) {
			characterSheet.characterData.put(Fields.SPECIALIZATIONCLASS, this);
		} else {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", baseClass, this));
		}
	}

	public boolean isBaseClassCompatible(BaseClasses baseClass) {
		return (hasBase() && getBaseClass().equals(baseClass)) || !hasBase();
	}

	@Override
	public Class<SpecializationClasses> getImplementingClass() {
		return SpecializationClasses.class;
	}
}