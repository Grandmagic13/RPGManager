package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;
import static rpg_database.character_sheet.Languages.*;

public enum Background implements CustomSetter<Background> {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR("Ander Survivor", playAs_Mage_Warrior_Rogue(), ANDER_TRADE), 
	APOSTATE("Apostate", playAs_Mage(), new LanguagesSet(Languages.TRADE_TONGUE)),
	ANTIVAN_WAYFARER("Antivan Wayfarer", playAs_Warrior_Rogue(), ANTIVAN_TRADE),
	AVVAR("Avvar", playAs_Mage_Warrior_Rogue(), TRADE),
	CHASIND_WILDER("Chasind Wilder", playAs_Mage_Warrior_Rogue(), TRADE), 
	CIRCLE_MAGE("Circle Mage", playAs_Mage(), ANCIENT_TEVENE_TRADE),
	CITY_ELF("City Elf", playAs_Warrior_Rogue(), TRADE),
	DALISH_ELF("Dalish Elf", playAs_Mage_Warrior_Rogue(), ELVEN_TRADE), 
	DWARF_DUSTER("Dwarf Duster", playAs_Rogue(), DWARVEN_TRADE), 
	ESCAPED_ELVEN_SLAVE("Escaped Elven Slave", playAs_Mage_Warrior_Rogue(), TEVENE_TRADE), 
	FERELDAN_CRAFTSMEN("Fereldan Craftsmen", playAs_Warrior_Rogue(), TRADE),
	FERELDAN_FREEMAN("Fereldan Freeman", playAs_Warrior_Rogue(), TRADE), 
	FERELDAN_NOBLE("Fereldan Noble", playAs_Warrior_Rogue(), TRADE),
	FREE_MARCHER("Free Marcher", playAs_Warrior_Rogue(), TRADE), 
	HIGH_BORN_DWARF("High Born Dwarf", playAs_Warrior_Rogue(), DWARVEN_TRADE), 
	LOW_BORN_DWARF("Low Born Dwarf", playAs_Warrior_Rogue(), DWARVEN_TRADE), 
	NEVARRAN_ADVENTURER("Nevarran Adventurer", playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_COMMONER("Orlesian Commoner", playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_EXILE("Orlesian Exile", playAs_Mage_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_NOBLE("Orlesian Noble", playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_STUDENT("Orlesian Student", playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	QUNARI_BERESAAD("Qunari Beresaad", playAs_Warrior_Rogue(), QUNLAT_TRADE), 
	RIVAINI_MERCHANT("Rivaini Merchant", playAs_Warrior_Rogue(), RIVAINI_TRADE), 
	SEHERON_CONVERT("Seheron Convert", playAs_Warrior_Rogue(), QUNLAT_TEVENE_TRADE),  
	SURFACE_DWARF("Surface Dwarf", playAs_Warrior_Rogue(), DWARVEN_TRADE), 
	TAL_VASHOTH("Tal-Vashoth", playAs_Mage_Warrior_Rogue(), QUNLAT_TRADE), 
	TEVINTER_ALTUS("Tevinter Altus", playAs_Mage(), TEVENE_TRADE), 
	TEVINTER_LAETAN("Tevinter Laetan", playAs_Mage(), TEVENE_TRADE), 
	TEVINTER_SOPORATI("Tevinter Soporati", playAs_Warrior_Rogue(), TEVENE_TRADE), 
	WAKING_SEA_RAIDER("Waking Sea Raider", playAs_Warrior_Rogue(), ANTIVAN_TRADE);

	private final String text;
	private final ArrayList<BaseClasses> baseClasses;
	private final LanguagesSet language;
	
	private Background(final String text, BaseClasses[] baseClasses, final LanguagesSet languages){
		this.text = text;
		this.baseClasses = new ArrayList<>(Arrays.asList(baseClasses));
		this.language = languages;
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
		characterSheet.characterData.remove(Fields.LANGUAGES);
		characterSheet.characterData.put(Fields.BACKGROUND, this);
		LanguagesSet thisLanguages = new LanguagesSet();
		thisLanguages.addAll(this.language);
		characterSheet.characterData.put(Fields.LANGUAGES, thisLanguages);
	}

	private boolean isCharacterBaseClassAllowed(CharacterSheet characterSheet) {
		return baseClasses.contains(characterSheet.getData(Fields.BASECLASS));
	}
}