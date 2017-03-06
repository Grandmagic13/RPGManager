package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Background implements CustomSetter<Background> {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR("Ander Survivor", playAs_Mage_Warrior_Rogue()), APOSTATE("Apostate", playAs_Mage()),
	ANTIVAN_WAYFARER("Antivan Wayfarer", playAs_Warrior_Rogue()), AVVAR("Avvar", playAs_Mage_Warrior_Rogue()),
	CHASIND_WILDER("Chasind Wilder", playAs_Mage_Warrior_Rogue()), CIRCLE_MAGE("Circle Mage", playAs_Mage()),
	CITY_ELF("City Elf", playAs_Warrior_Rogue()), DALISH_ELF("Dalish Elf", playAs_Mage_Warrior_Rogue()), DWARF_DUSTER("Dwarf Duster", playAs_Rogue()),
	ESCAPED_ELVEN_SLAVE("Escaped Elven Slave", playAs_Mage_Warrior_Rogue()), FERELDAN_CRAFTSMEN("Fereldan Craftsmen", playAs_Warrior_Rogue()),
	FERELDAN_FREEMAN("Fereldan Freeman", playAs_Warrior_Rogue()), FERELDAN_NOBLE("Fereldan Noble", playAs_Warrior_Rogue()),
	FREE_MARCHER("Free Marcher", playAs_Warrior_Rogue()), HIGH_BORN_DWARF("High Born Dwarf", playAs_Warrior_Rogue()),
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