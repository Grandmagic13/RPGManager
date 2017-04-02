package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Background implements CustomSetter<Background> {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR(playAs_Mage_Warrior_Rogue()), ANTIVAN_WAYFARER(playAs_Warrior_Rogue()), AVVAR(playAs_Mage_Warrior_Rogue()),
	CHASIND_WILDER(playAs_Mage_Warrior_Rogue()), CITY_ELF(playAs_Warrior_Rogue()), DALISH_ELF(playAs_Mage_Warrior_Rogue()),
	DWARF_DUSTER(playAs_Rogue()), ELF_APOSTATE(playAs_Mage()), ELF_CIRCLE_MAGE(playAs_Mage()), ESCAPED_ELVEN_SLAVE(playAs_Mage_Warrior_Rogue()),
	FERELDAN_CRAFTSMEN(playAs_Warrior_Rogue()), FERELDAN_FREEMAN(playAs_Warrior_Rogue()), FERELDAN_NOBLE(playAs_Warrior_Rogue()),
	FREE_MARCHER(playAs_Warrior_Rogue()), HIGH_BORN_DWARF(playAs_Warrior_Rogue()), HUMAN_APOSTATE(playAs_Mage()), HUMAN_CIRCLE_MAGE(playAs_Mage()),
	LOW_BORN_DWARF(playAs_Warrior_Rogue()), NEVARRAN_ADVENTURER(playAs_Warrior_Rogue()), ORLESIAN_COMMONER(playAs_Warrior_Rogue()),
	ORLESIAN_EXILE(playAs_Mage_Warrior_Rogue()), ORLESIAN_NOBLE(playAs_Warrior_Rogue()), ORLESIAN_STUDENT(playAs_Warrior_Rogue()),
	QUNARI_BERESAAD(playAs_Warrior_Rogue()), RIVAINI_MERCHANT(playAs_Warrior_Rogue()), SEHERON_CONVERT(playAs_Warrior_Rogue()),
	SURFACE_DWARF(playAs_Warrior_Rogue()), TAL_VASHOTH(playAs_Mage_Warrior_Rogue()), TEVINTER_ALTUS(playAs_Mage()), TEVINTER_LAETAN(playAs_Mage()),
	TEVINTER_SOPORATI(playAs_Warrior_Rogue()), WAKING_SEA_RAIDER(playAs_Warrior_Rogue());

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

	private Background(BaseClasses[] baseClasses) {
		this.text = generateEnumText(this.name());
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