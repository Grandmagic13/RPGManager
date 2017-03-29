package rpg_database.character_sheet;

import static rpg_database.character_sheet.Background.dwarfRogues;
import static rpg_database.character_sheet.Background.dwarfWarriors;
import static rpg_database.character_sheet.Background.elfMages;
import static rpg_database.character_sheet.Background.humanAndElfWarriors;
import static rpg_database.character_sheet.Background.qunariMages;

import java.util.HashSet;

public enum SpecializationClasses {
	ARCANE_WARRIOR("Arcane Warrior", BaseClasses.MAGE), ASSASSIN("Assassin", BaseClasses.ROGUE), BARD("Bard", BaseClasses.ROGUE),
	BERSERKER("Berserker", BaseClasses.WARRIOR), BLOOD_MAGE("Blood Mage", BaseClasses.MAGE), CHAMPION("Champion", BaseClasses.WARRIOR),
	CHEVALIER("Chevalier", BaseClasses.WARRIOR), DUELIST("Duelist", BaseClasses.ROGUE), FORCE_MAGE("Force Mage", BaseClasses.MAGE),
	GUARDIAN("Guardian", BaseClasses.WARRIOR), KEEPER("Keeper", BaseClasses.MAGE, elfMages()),
	LEGIONNAIRE_SCOUT("Legionnaire Scout", BaseClasses.ROGUE, dwarfRogues()),
	LEGIONNAIRE_WARRIOR("Legionnaire Warrior", BaseClasses.WARRIOR, dwarfWarriors()),
	LYRIUM_WARRIOR("Lyrium Warrior", BaseClasses.WARRIOR, humanAndElfWarriors()), NECROMANCER("Necromancer", BaseClasses.MAGE),
	MARKSMAN("Marksman", BaseClasses.ROGUE), RANGER("Ranger", BaseClasses.ROGUE), REAVER("Reaver", BaseClasses.WARRIOR),
	SAAREBAS("Saarebas", BaseClasses.MAGE, qunariMages()), SHADOW("Shadow", BaseClasses.ROGUE), SHAPESHIFTER("Shapeshifter", BaseClasses.MAGE),
	SPIRIT_HEALER("Spirit Healer", BaseClasses.MAGE), SPIRIT_WARRIOR("Spirit Warrior", BaseClasses.WARRIOR), TEMPEST("Tempest", BaseClasses.ROGUE),
	TEMPLAR("Templar", BaseClasses.WARRIOR);

	private final String text;
	private final BaseClasses baseClass;
	private final HashSet<Background> restrictedBackgrounds;

	private SpecializationClasses(final String text, BaseClasses baseClass) {
		this.text = text;
		this.baseClass = baseClass;
		this.restrictedBackgrounds = new HashSet<Background>();
	}

	private SpecializationClasses(final String text, BaseClasses baseClass, HashSet<Background> restrictedBackgrounds) {
		this.text = text;
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