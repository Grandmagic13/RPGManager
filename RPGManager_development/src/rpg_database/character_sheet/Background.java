package rpg_database.character_sheet;

import java.text.MessageFormat.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Background implements CustomSetter<Background> {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR("Ander Survivor", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.ANDER,Languages.TRADE_TONGUE }), 
	APOSTATE("Apostate", playAs_Mage(), new Languages[] {Languages.TRADE_TONGUE }),
	ANTIVAN_WAYFARER("Antivan Wayfarer", playAs_Warrior_Rogue(), new Languages[] {Languages.ANTIVAN,Languages.TRADE_TONGUE }),
	AVVAR("Avvar", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }),
	CHASIND_WILDER("Chasind Wilder", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }), 
	CIRCLE_MAGE("Circle Mage", playAs_Mage(), new Languages[] {Languages.ANCIENT_TEVENE,Languages.TRADE_TONGUE }),
	CITY_ELF("City Elf", playAs_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }),
	DALISH_ELF("Dalish Elf", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.ELVISH,Languages.TRADE_TONGUE }), 
	DWARF_DUSTER("Dwarf Duster", playAs_Rogue(), new Languages[] {Languages.DWARVEN_LANGUAGES,Languages.TRADE_TONGUE }), 
	ESCAPED_ELVEN_SLAVE("Escaped Elven Slave", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.TEVENE,Languages.TRADE_TONGUE }), 
	FERELDAN_CRAFTSMEN("Fereldan Craftsmen", playAs_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }),
	FERELDAN_FREEMAN("Fereldan Freeman", playAs_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }), 
	FERELDAN_NOBLE("Fereldan Noble", playAs_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }),
	FREE_MARCHER("Free Marcher", playAs_Warrior_Rogue(), new Languages[] {Languages.TRADE_TONGUE }), 
	HIGH_BORN_DWARF("High Born Dwarf", playAs_Warrior_Rogue(), new Languages[] {Languages.DWARVEN_LANGUAGES,Languages.TRADE_TONGUE }), 
	LOW_BORN_DWARF("Low Born Dwarf", playAs_Warrior_Rogue(), new Languages[] {Languages.DWARVEN_LANGUAGES,Languages.TRADE_TONGUE }), 
	NEVARRAN_ADVENTURER("Nevarran Adventurer", playAs_Warrior_Rogue(), new Languages[] {Languages.ORLESIAN,Languages.TRADE_TONGUE }), 
	ORLESIAN_COMMONER("Orlesian Commoner", playAs_Warrior_Rogue(), new Languages[] {Languages.ORLESIAN,Languages.TRADE_TONGUE }), 
	ORLESIAN_EXILE("Orlesian Exile", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.ORLESIAN,Languages.TRADE_TONGUE }), 
	ORLESIAN_NOBLE("Orlesian Noble", playAs_Warrior_Rogue(), new Languages[] {Languages.ORLESIAN,Languages.TRADE_TONGUE }), 
	ORLESIAN_STUDENT("Orlesian Student", playAs_Warrior_Rogue(), new Languages[] {Languages.ORLESIAN,Languages.TRADE_TONGUE }), 
	QUNARI_BERESAAD("Qunari Beresaad", playAs_Warrior_Rogue(), new Languages[] {Languages.QUNLAT,Languages.TRADE_TONGUE }), 
	RIVAINI_MERCHANT("Rivaini Merchant", playAs_Warrior_Rogue(), new Languages[] {Languages.RIVAINI,Languages.TRADE_TONGUE }), 
	SEHERON_CONVERT("Seheron Convert", playAs_Warrior_Rogue(), new Languages[] {Languages.QUNLAT,Languages.TEVENE,Languages.TRADE_TONGUE }),  
	SURFACE_DWARF("Surface Dwarf", playAs_Warrior_Rogue(), new Languages[] {Languages.DWARVEN_LANGUAGES,Languages.TRADE_TONGUE }), 
	TAL_VASHOTH("Tal-Vashoth", playAs_Mage_Warrior_Rogue(), new Languages[] {Languages.QUNLAT,Languages.TRADE_TONGUE }), 
	TEVINTER_ALTUS("Tevinter Altus", playAs_Mage(), new Languages[] {Languages.TEVENE,Languages.TRADE_TONGUE }), 
	TEVINTER_LAETAN("Tevinter Laetan", playAs_Mage(), new Languages[] {Languages.TEVENE,Languages.TRADE_TONGUE }), 
	TEVINTER_SOPORATI("Tevinter Soporati", playAs_Warrior_Rogue(), new Languages[] {Languages.TEVENE,Languages.TRADE_TONGUE }), 
	WAKING_SEA_RAIDER("Waking Sea Raider", playAs_Warrior_Rogue(), new Languages[] {Languages.ANTIVAN,Languages.TRADE_TONGUE });

	private final String text;
	private final ArrayList<BaseClasses> baseClasses;
	private final HashSet<Languages> language;

	private Background(final String text, BaseClasses[] baseClasses, Languages[] language) {
		this.text = text;
		this.baseClasses = new ArrayList<>(Arrays.asList(baseClasses));
		this.language = new HashSet<>(Arrays.asList(language));
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
		characterSheet.setData(Fields.LANGUAGES, this.language);
	}

	private boolean isCharacterBaseClassAllowed(CharacterSheet characterSheet) {
		return baseClasses.contains(characterSheet.getData(Fields.BASECLASS));
	}
}