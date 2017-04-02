package rpg_database.character_sheet;

import static rpg_database.character_sheet.Background.dwarfRogues;
import static rpg_database.character_sheet.Background.dwarfWarriors;
import static rpg_database.character_sheet.Background.elfMages;
import static rpg_database.character_sheet.Background.humanAndElfWarriors;
import static rpg_database.character_sheet.Background.qunariMages;

import java.util.HashSet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum SpecializationClasses {
	ARCANE_WARRIOR(BaseClasses.MAGE), ASSASSIN(BaseClasses.ROGUE), BARD(BaseClasses.ROGUE), BERSERKER(BaseClasses.WARRIOR),
	BLOOD_MAGE(BaseClasses.MAGE), CHAMPION(BaseClasses.WARRIOR), CHEVALIER(BaseClasses.WARRIOR), DUELIST(BaseClasses.ROGUE),
	FORCE_MAGE(BaseClasses.MAGE), GUARDIAN(BaseClasses.WARRIOR), KEEPER(BaseClasses.MAGE, elfMages()),
	LEGIONNAIRE_SCOUT(BaseClasses.ROGUE, dwarfRogues()), LEGIONNAIRE_WARRIOR(BaseClasses.WARRIOR, dwarfWarriors()),
	LYRIUM_WARRIOR(BaseClasses.WARRIOR, humanAndElfWarriors()), NECROMANCER(BaseClasses.MAGE), MARKSMAN(BaseClasses.ROGUE), RANGER(BaseClasses.ROGUE),
	REAVER(BaseClasses.WARRIOR), SAAREBAS(BaseClasses.MAGE, qunariMages()), SHADOW(BaseClasses.ROGUE), SHAPESHIFTER(BaseClasses.MAGE),
	SPIRIT_HEALER(BaseClasses.MAGE), SPIRIT_WARRIOR(BaseClasses.WARRIOR), TEMPEST(BaseClasses.ROGUE), TEMPLAR(BaseClasses.WARRIOR);

	private final String text;
	private final BaseClasses baseClass;
	private final HashSet<Background> restrictedBackgrounds;

	private SpecializationClasses(BaseClasses baseClass) {
		this.text = generateEnumText(this.name());
		this.baseClass = baseClass;
		this.restrictedBackgrounds = new HashSet<Background>();
	}

	private SpecializationClasses(BaseClasses baseClass, HashSet<Background> restrictedBackgrounds) {
		this.text = generateEnumText(this.name());
		this.baseClass = baseClass;
		this.restrictedBackgrounds = restrictedBackgrounds;
	}

	public BaseClasses getBaseClass() {
		return baseClass;
	}

	public boolean isBackgroundRestricted() {
		return !restrictedBackgrounds.isEmpty();
	}

	public HashSet<Background> getRestrictedBackgrounds() {
		return restrictedBackgrounds;
	}

	@Override
	public String toString() {
		return text;
	}

	public boolean isBaseClassCompatible(BaseClasses baseClass) {
		return getBaseClass().equals(baseClass);
	}
}