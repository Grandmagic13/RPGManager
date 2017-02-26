package rpg_database;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

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

	public static enum Fields {
		NAME, AGE, XP, GENDER, SPEED, CHARACTERCLASS;

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

	public void setData(Fields field, int value) {
		this.characterData.put(field, value);
	}
}
