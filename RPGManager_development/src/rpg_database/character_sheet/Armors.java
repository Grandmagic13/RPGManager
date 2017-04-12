package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Armors implements CustomSetter<Armors> {
	// TODO Implementing mechanism for Lining with Concealed Pockets
	// TODO Later, we should revise the set method, because it'll depends on,
	// which talent and/or specialization will the user choose.

	LIGHT_LEATHER(3, 0), HEAVY_LEATHER(4, -1), LIGHT_MAIL(5, -2), HEAVY_MAIL(7, -3), LIGHT_PLATE(8, -4), HEAVY_PLATE(10, -5),
	LIGHT_LEATHER_DUSTER(3, 0), TAILORED_LEATHER_DUSTER(4, 0), ROBE(0, 0);

	private final String text;
	private final int armorRating;
	private final int armorPenalty;

	private Armors(int armorRating, int armorPenalty) {
		this.text = generateEnumText(this.name());
		this.armorRating = armorRating;
		this.armorPenalty = armorPenalty;
	}

	@Override
	public Class<Armors> getImplementingClass() {
		return Armors.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.ARMOR_TYPE, this);
	}

	@Override
	public String toString() {
		return text;
	}

	public int getArmorPenalty() {
		return this.armorPenalty;
	}

	public int getArmorRating() {
		return this.armorRating;
	}

}
