package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.util.ArrayList;
import java.util.HashSet;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Background implements CustomSetter<Background> {
	ANDER_SURVIVOR, ANTIVAN_WAYFARER, AVVAR, CHASIND_WILDER, CITY_ELF, DALISH_ELF, DWARF_DUSTER, ELF_APOSTATE, ELF_CIRCLE_MAGE, ESCAPED_ELVEN_SLAVE,
	FERELDAN_CRAFTSMEN, FERELDAN_FREEMAN, FERELDAN_NOBLE, FREE_MARCHER, HIGH_BORN_DWARF, HUMAN_APOSTATE, HUMAN_CIRCLE_MAGE, LOW_BORN_DWARF,
	NEVARRAN_ADVENTURER, ORLESIAN_COMMONER, ORLESIAN_EXILE, ORLESIAN_NOBLE, ORLESIAN_STUDENT, QUNARI_BERESAAD, RIVAINI_MERCHANT, SEHERON_CONVERT,
	SURFACE_DWARF, TAL_VASHOTH, TEVINTER_ALTUS, TEVINTER_LAETAN, TEVINTER_SOPORATI, WAKING_SEA_RAIDER;

	private final String text;
	private final HashSet<BaseClasses> baseClasses;
	private final LanguagesSet languages;
	private final HashSet<Race> allowedRaces;

	private Background() {
		this.text = generateEnumText(this.name());

		FieldRules backgroundRule = FieldRulesFactory.getFieldRules(FieldRulesFactory.BACKGROUND);
		this.baseClasses = new HashSet<>();
		this.baseClasses.addAll(backgroundRule.getEnumsForField(this, BaseClasses.class, Keys.BASE_CLASSES_ARRAY));
		LanguagesSet languagesSet = new LanguagesSet();
		ArrayList<Languages> languagesFromRule = backgroundRule.getEnumsForField(this, Languages.class, Keys.LANGUAGES_ARRAY);
		languagesSet.addAll(languagesFromRule);
		this.languages = languagesSet;
		this.allowedRaces = new HashSet<>();
		this.allowedRaces.addAll(backgroundRule.getEnumsForField(this, Race.class, Keys.RACES_ARRAY));
	}

	public HashSet<BaseClasses> getAllowedBaseClasses() {
		return baseClasses;
	}

	public HashSet<Race> getAllowedRaces() {
		return allowedRaces;
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
		characterSheet.characterData.put(Fields.RACE, this.allowedRaces.iterator().next());
		characterSheet.characterData.put(Fields.LANGUAGES, new LanguagesSet(this.languages));
	}

	private boolean isCharacterBaseClassAllowed(CharacterSheet characterSheet) {
		return baseClasses.contains(characterSheet.getData(Fields.BASECLASS));
	}
}