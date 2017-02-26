package rpg_database;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import rpg_database.CharacterSheet.Background;

public class CharacterSheet {

	private String entryName;
	private HashMap<Fields, Object> characterData;

	public static enum Gender {
		MALE("Male"), FEMALE("Female");

		private final String text;

		private Gender(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public static enum CharacterClass {
		WARRIOR("Warrior"), ROGUE("Rogue"), MAGE("Mage");

		private final String text;

		private CharacterClass(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public static enum Background {
		// TODO lot of enum information. Factory class for reading from file?
		ANDER_SURVIVOR("Ander Survivor"), APOSTATE("Apostate"), ANTIVAN_WAYFARER("Antivan Wayfarer"), AVVAR("Avvar"),
		CHASIND_WILDER("Chasind Wilder"), CIRCLE_MAGE("Circle Mage"), CITY_ELF("City Elf"), DALISH_ELF("Dalish Elf"),
		DWARF_DUSTER("Dwarf Duster"), ESCAPED_ELVEN_SLAVE("Escaped Elven Slave"), FERELDAN_CRAFTSMEN(
				"Fereldan Craftsmen"), FERELDAN_FREEMAN("Fereldan Freeman"), FERELDAN_NOBLE("Fereldan Noble"),
		FREE_MARCHER("Free Marcher"), HIGH_BORN_DWARF("High Born Dwarf"), LOW_BORN_DWARF("Low Born Dwarf"),
		NEVARRAN_ADVENTURER("Nevarran Adventurer"), ORLESIAN_COMMONER("Orlesian Commoner"), ORLESIAN_EXILE(
				"Orlesian Exile"), ORLESIAN_NOBLE("Orlesian Noble"), ORLESIAN_STUDENT("Orlesian Student"),
		QUNARI_BERESAAD("Qunari Beresaad"), RIVAINI_MERCHANT("Rivaini Merchant"), SEHERON_CONVERT("Seheron Convert"),
		SURFACE_DWARF("Surface Dwarf"), TAL_VASHOTH("Tal-Vashoth"), TEVINTER_ALTUS("Tevinter Altus"), TEVINTER_LAETAN(
				"Tevinter Laetan"), TEVINTER_SOPORATI("Tevinter Soporati"), WAKING_SEA_RAIDER("Waking Sea Raider");

		private final String text;

		private Background(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public static enum Fields {
		NAME, AGE, XP, GENDER, SPEED, CHARACTERCLASS, BACKGROUND;

		public static ArrayList<Fields> stringValues() {
			return new ArrayList<Fields>() {
				{
					add(NAME);
				}
			};
		}

		public static ArrayList<Fields> genderValues() {
			return new ArrayList<Fields>() {
				{
					add(GENDER);
				}
			};
		}

		public static ArrayList<Fields> intValues() {
			return new ArrayList<Fields>() {
				{
					add(AGE);
					add(XP);
					add(SPEED);
				}
			};
		}

		public static ArrayList<Fields> characterClassValues() {
			return new ArrayList<Fields>() {
				{
					add(CHARACTERCLASS);
				}
			};
		}

		public static ArrayList<Fields> backgroundValues() {
			return new ArrayList<Fields>() {
				{
					add(BACKGROUND);
				}
			};
		}
	}

	public CharacterSheet(String entryName) {
		this.characterData = new HashMap<>();
		this.entryName = entryName;
		for (Fields field : Fields.values()) {
			putDefaultValueByField(field);
		}
	}

	private void putDefaultValueByField(Fields field) {
		if (Fields.stringValues().contains(field))
			characterData.put(field, "");
		else if (Fields.intValues().contains(field))
			characterData.put(field, 0);
		else if (Fields.genderValues().contains(field))
			characterData.put(field, Gender.MALE);
		else if (Fields.characterClassValues().contains(field))
			characterData.put(field, CharacterClass.WARRIOR);
		else if (Fields.backgroundValues().contains(field))
			characterData.put(field, Background.ANDER_SURVIVOR);
		else
			throw new InvalidParameterException();
	}

	public String getEntryName() {
		return entryName;
	}

	public Object getData(Fields field) {
		return characterData.get(field);
	}

	public void setData(Fields field, String value) {
		this.characterData.put(field, value);
	}

	public void setData(Fields field, Gender gender) {
		this.characterData.put(field, gender);
	}

	public void setData(Fields field, CharacterClass characterClass) {
		this.characterData.put(field, characterClass);
	}

	public void setData(Fields field, Background background) {
		this.characterData.put(field, background);
	}

	public void setData(Fields field, int value) {
		this.characterData.put(field, value);
	}
}
