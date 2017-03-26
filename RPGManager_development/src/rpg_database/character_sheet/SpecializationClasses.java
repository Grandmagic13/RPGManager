package rpg_database.character_sheet;

public enum SpecializationClasses {
	ARCANE_WARRIOR("Arcane Warrior", BaseClasses.MAGE), ASSASSIN("Assassin", BaseClasses.ROGUE), BARD("Bard", BaseClasses.ROGUE),
	BERSERKER("Berserker", BaseClasses.WARRIOR), BLOOD_MAGE("Blood Mage", BaseClasses.MAGE), CHAMPION("Champion", BaseClasses.WARRIOR),
	CHEVALIER("Chevalier", BaseClasses.WARRIOR), DUELIST("Duelist", BaseClasses.ROGUE), FORCE_MAGE("Force Mage", BaseClasses.MAGE),
	GUARDIAN("Guardian", BaseClasses.WARRIOR), KEEPER("Keeper", BaseClasses.MAGE), LEGIONNAIRE_SCOUT("Legionnaire Scout", BaseClasses.ROGUE),
	LEGIONNAIRE_WARRIOR("Legionnaire Warrior", BaseClasses.WARRIOR), LYRIUM_WARRIOR("Lyrium Warrior", BaseClasses.WARRIOR),
	NECROMANCER("Necromancer", BaseClasses.MAGE), MARKSMAN("Marksman", BaseClasses.ROGUE), RANGER("Ranger", BaseClasses.ROGUE),
	REAVER("Reaver", BaseClasses.WARRIOR), SAAREBAS("Saarebas", BaseClasses.MAGE), SHADOW("Shadow", BaseClasses.ROGUE),
	SHAPESHIFTER("Shapeshifter", BaseClasses.MAGE), SPIRIT_HEALER("Spirit Healer", BaseClasses.MAGE),
	SPIRIT_WARRIOR("Spirit Warrior", BaseClasses.WARRIOR), TEMPEST("Tempest", BaseClasses.ROGUE), TEMPLAR("Templar", BaseClasses.WARRIOR);

	private final String text;
	private final BaseClasses baseClass;

	private SpecializationClasses(final String text, BaseClasses baseClass) {
		this.text = text;
		this.baseClass = baseClass;
	}

	public BaseClasses getBaseClass() {
		return baseClass;
	}

	@Override
	public String toString() {
		return text;
	}

	public boolean isBaseClassCompatible(BaseClasses baseClass) {
		return getBaseClass().equals(baseClass);
	}
}