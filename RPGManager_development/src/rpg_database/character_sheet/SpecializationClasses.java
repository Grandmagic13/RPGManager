package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum SpecializationClasses {
	ARCANE_WARRIOR(BaseClasses.MAGE), ASSASSIN(BaseClasses.ROGUE), BARD(BaseClasses.ROGUE), BERSERKER(BaseClasses.WARRIOR),
	BLOOD_MAGE(BaseClasses.MAGE), CHAMPION(BaseClasses.WARRIOR), CHEVALIER(BaseClasses.WARRIOR), DUELIST(BaseClasses.ROGUE),
	FORCE_MAGE(BaseClasses.MAGE), GUARDIAN(BaseClasses.WARRIOR), KEEPER(BaseClasses.MAGE), LEGIONNAIRE_SCOUT(BaseClasses.ROGUE),
	LEGIONNAIRE_WARRIOR(BaseClasses.WARRIOR), LYRIUM_WARRIOR(BaseClasses.WARRIOR), NECROMANCER(BaseClasses.MAGE), MARKSMAN(BaseClasses.ROGUE),
	RANGER(BaseClasses.ROGUE), REAVER(BaseClasses.WARRIOR), SAAREBAS(BaseClasses.MAGE), SHADOW(BaseClasses.ROGUE), SHAPESHIFTER(BaseClasses.MAGE),
	SPIRIT_HEALER(BaseClasses.MAGE), SPIRIT_WARRIOR(BaseClasses.WARRIOR), TEMPEST(BaseClasses.ROGUE), TEMPLAR(BaseClasses.WARRIOR);

	private final String text;
	private final BaseClasses baseClass;

	private SpecializationClasses(BaseClasses baseClass) {
		this.text = generateEnumText(this.name());
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