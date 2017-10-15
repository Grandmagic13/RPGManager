package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.util.HashSet;

import rpg_database.character_sheet.exceptions.CharacterSheetException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Race implements CustomSetter<Race> {

	HUMAN(10), ELF(12), DWARF(8), QUNARI(10);

	private final String text;
	private final int speed;

	private Race(int speed) {
		this.text = generateEnumText(this.name());
		this.speed = speed;
	}

	public int getBaseSpeed() {
		return this.speed;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public Class<Race> getImplementingClass() {
		return Race.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		Background background = getCharacterSheetBackground(characterSheet);
		HashSet<Race> allowedRaces = getAllowedRacesForBackground(background);
		if (allowedRaces.size() > 1)
			if (allowedRaces.contains(this))
				characterSheet.characterData.put(Fields.RACE, this);
			else
				throw new CharacterSheetException(String.format("%s can not originate from %s heritage!", background.toString(), this.text));
		else
			throw new CharacterSheetException("The race can not be set manually!");
	}

	private HashSet<Race> getAllowedRacesForBackground(Background background) {
		return background.getAllowedRaces();
	}

	private Background getCharacterSheetBackground(CharacterSheet characterSheet) {
		return (Background) characterSheet.characterData.get(Fields.BACKGROUND);
	}

}
