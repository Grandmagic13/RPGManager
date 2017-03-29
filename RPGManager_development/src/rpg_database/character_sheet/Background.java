package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Background implements CustomSetter<Background> {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR("Ander Survivor", playAs_Mage_Warrior_Rogue()), ANTIVAN_WAYFARER("Antivan Wayfarer", playAs_Warrior_Rogue()),
	AVVAR("Avvar", playAs_Mage_Warrior_Rogue()), CHASIND_WILDER("Chasind Wilder", playAs_Mage_Warrior_Rogue()),
	CITY_ELF("City Elf", playAs_Warrior_Rogue()), DALISH_ELF("Dalish Elf", playAs_Mage_Warrior_Rogue()), DWARF_DUSTER("Dwarf Duster", playAs_Rogue()),
	ELF_APOSTATE("Elf Apostate", playAs_Mage()), ELF_CIRCLE_MAGE("Elf Circle Mage", playAs_Mage()),
	ESCAPED_ELVEN_SLAVE("Escaped Elven Slave", playAs_Mage_Warrior_Rogue()), FERELDAN_CRAFTSMEN("Fereldan Craftsmen", playAs_Warrior_Rogue()),
	FERELDAN_FREEMAN("Fereldan Freeman", playAs_Warrior_Rogue()), FERELDAN_NOBLE("Fereldan Noble", playAs_Warrior_Rogue()),
	FREE_MARCHER("Free Marcher", playAs_Warrior_Rogue()), HIGH_BORN_DWARF("High Born Dwarf", playAs_Warrior_Rogue()),
	HUMAN_APOSTATE("Human Apostate", playAs_Mage()), HUMAN_CIRCLE_MAGE("Human Circle Mage", playAs_Mage()),
	LOW_BORN_DWARF("Low Born Dwarf", playAs_Warrior_Rogue()), NEVARRAN_ADVENTURER("Nevarran Adventurer", playAs_Warrior_Rogue()),
	ORLESIAN_COMMONER("Orlesian Commoner", playAs_Warrior_Rogue()), ORLESIAN_EXILE("Orlesian Exile", playAs_Mage_Warrior_Rogue()),
	ORLESIAN_NOBLE("Orlesian Noble", playAs_Warrior_Rogue()), ORLESIAN_STUDENT("Orlesian Student", playAs_Warrior_Rogue()),
	QUNARI_BERESAAD("Qunari Beresaad", playAs_Warrior_Rogue()), RIVAINI_MERCHANT("Rivaini Merchant", playAs_Warrior_Rogue()),
	SEHERON_CONVERT("Seheron Convert", playAs_Warrior_Rogue()), SURFACE_DWARF("Surface Dwarf", playAs_Warrior_Rogue()),
	TAL_VASHOTH("Tal-Vashoth", playAs_Mage_Warrior_Rogue()), TEVINTER_ALTUS("Tevinter Altus", playAs_Mage()),
	TEVINTER_LAETAN("Tevinter Laetan", playAs_Mage()), TEVINTER_SOPORATI("Tevinter Soporati", playAs_Warrior_Rogue()),
	WAKING_SEA_RAIDER("Waking Sea Raider", playAs_Warrior_Rogue());

	private final String text;
	private final ArrayList<BaseClasses> baseClasses;

	public static final HashSet<Background> elfMages() {
		Background[] backgrounds = { DALISH_ELF, ELF_APOSTATE, ELF_CIRCLE_MAGE, ELF_CIRCLE_MAGE, ESCAPED_ELVEN_SLAVE };
		return new HashSet<Background>(Arrays.asList(backgrounds));
	}

	public static final HashSet<Background> dwarfRogues() {
		Background[] backgrounds = { DWARF_DUSTER, HIGH_BORN_DWARF, LOW_BORN_DWARF, SURFACE_DWARF };
		return new HashSet<Background>(Arrays.asList(backgrounds));
	}

	public static final HashSet<Background> dwarfWarriors() {
		Background[] backgrounds = { HIGH_BORN_DWARF, LOW_BORN_DWARF, SURFACE_DWARF };
		return new HashSet<Background>(Arrays.asList(backgrounds));
	}

	public static final HashSet<Background> humanAndElfWarriors() {
		Background[] backgrounds = { ANDER_SURVIVOR, ANTIVAN_WAYFARER, AVVAR, CHASIND_WILDER, CITY_ELF, DALISH_ELF, ESCAPED_ELVEN_SLAVE,
				FERELDAN_CRAFTSMEN, FERELDAN_FREEMAN, FERELDAN_NOBLE, FREE_MARCHER, NEVARRAN_ADVENTURER, ORLESIAN_COMMONER, ORLESIAN_EXILE,
				ORLESIAN_NOBLE, ORLESIAN_STUDENT, RIVAINI_MERCHANT, SEHERON_CONVERT, TEVINTER_SOPORATI, WAKING_SEA_RAIDER };
		return new HashSet<Background>(Arrays.asList(backgrounds));
	}

	public static final HashSet<Background> qunariMages() {
		return new HashSet<Background>(Arrays.asList(Background.TAL_VASHOTH));
	}

	private Background(final String text, BaseClasses[] baseClasses) {
		this.text = text;
		this.baseClasses = new ArrayList<>(Arrays.asList(baseClasses));
	}

	private static BaseClasses[] playAs_Mage() {
		return new BaseClasses[] { BaseClasses.MAGE };
	}

	private static BaseClasses[] playAs_Rogue() {
		return new BaseClasses[] { BaseClasses.ROGUE };
	}

	private static BaseClasses[] playAs_Mage_Warrior_Rogue() {
		return new BaseClasses[] { BaseClasses.MAGE, BaseClasses.WARRIOR, BaseClasses.ROGUE };
	}

	private static BaseClasses[] playAs_Warrior_Rogue() {
		return new BaseClasses[] { BaseClasses.WARRIOR, BaseClasses.ROGUE };
	}

	public ArrayList<BaseClasses> getAllowedBaseClasses() {
		return baseClasses;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public Class<Background> getImplementingClass() {
		return Background.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		if (!isCharacterBaseClassAllowed(characterSheet)) {
			throw new InvalidCharacterClassException(String.format("%s is not a %s background!", this.toString(), characterSheet.getData(
					Fields.BASECLASS).toString()));
		}
		characterSheet.characterData.put(Fields.BACKGROUND, this);
	}

	private boolean isCharacterBaseClassAllowed(CharacterSheet characterSheet) {
		return baseClasses.contains(characterSheet.getData(Fields.BASECLASS));
	}
}