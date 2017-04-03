package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;
import static rpg_database.character_sheet.Languages.*;

public enum Background implements CustomSetter<Background> {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR(playAs_Mage_Warrior_Rogue(), ANDER_TRADE), 
	ANTIVAN_WAYFARER(playAs_Warrior_Rogue(), ANTIVAN_TRADE),
	AVVAR(playAs_Mage_Warrior_Rogue(), TRADE),
	CHASIND_WILDER(playAs_Mage_Warrior_Rogue(), TRADE), 
	CITY_ELF(playAs_Warrior_Rogue(), TRADE),
	DALISH_ELF(playAs_Mage_Warrior_Rogue(), ELVEN_TRADE), 
	DWARF_DUSTER(playAs_Rogue(), DWARVEN_TRADE), 
	ELF_APOSTATE(playAs_Mage(), TRADE),
	ELF_CIRCLE_MAGE(playAs_Mage(), ANCIENT_TEVENE_TRADE),
	ESCAPED_ELVEN_SLAVE(playAs_Mage_Warrior_Rogue(), TEVENE_TRADE), 
	FERELDAN_CRAFTSMEN(playAs_Warrior_Rogue(), TRADE),
	FERELDAN_FREEMAN(playAs_Warrior_Rogue(), TRADE), 
	FERELDAN_NOBLE(playAs_Warrior_Rogue(), TRADE),
	FREE_MARCHER(playAs_Warrior_Rogue(), TRADE), 
	HIGH_BORN_DWARF(playAs_Warrior_Rogue(), DWARVEN_TRADE),
	HUMAN_APOSTATE(playAs_Mage(), TRADE),
	HUMAN_CIRCLE_MAGE(playAs_Mage(), ANCIENT_TEVENE_TRADE),
	LOW_BORN_DWARF(playAs_Warrior_Rogue(), DWARVEN_TRADE), 
	NEVARRAN_ADVENTURER(playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_COMMONER(playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_EXILE(playAs_Mage_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_NOBLE(playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	ORLESIAN_STUDENT(playAs_Warrior_Rogue(), ORLESIAN_TRADE), 
	QUNARI_BERESAAD(playAs_Warrior_Rogue(), QUNLAT_TRADE), 
	RIVAINI_MERCHANT(playAs_Warrior_Rogue(), RIVAINI_TRADE), 
	SEHERON_CONVERT(playAs_Warrior_Rogue(), QUNLAT_TEVENE_TRADE),  
	SURFACE_DWARF(playAs_Warrior_Rogue(), DWARVEN_TRADE), 
	TAL_VASHOTH(playAs_Mage_Warrior_Rogue(), QUNLAT_TRADE), 
	TEVINTER_ALTUS(playAs_Mage(), TEVENE_TRADE), 
	TEVINTER_LAETAN(playAs_Mage(), TEVENE_TRADE), 
	TEVINTER_SOPORATI(playAs_Warrior_Rogue(), TEVENE_TRADE), 
	WAKING_SEA_RAIDER(playAs_Warrior_Rogue(), ANTIVAN_TRADE);
	
	private final String text;
	private final ArrayList<BaseClasses> baseClasses;
	private final LanguagesSet language;
	private final HashSet<Background> restrictedBackgrounds;
	
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
	
	private Background(BaseClasses[] baseClasses, final LanguagesSet languages){
		this.text = generateEnumText(this.name());
		this.baseClasses = new ArrayList<>(Arrays.asList(baseClasses));
		this.language = languages;
		this.restrictedBackgrounds = new HashSet<Background>();
	}
	
	private Background(BaseClasses[] baseClasses, final LanguagesSet languages, HashSet<Background> restrictedBackgrounds){
		this.text = generateEnumText(this.name());
		this.baseClasses = new ArrayList<>(Arrays.asList(baseClasses));
		this.language = languages;
		this.restrictedBackgrounds = restrictedBackgrounds;
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