package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum BaseClasses implements CustomSetter<BaseClasses> {
	WARRIOR, ROGUE, MAGE;

	private final String text;

	private final static ArrayList<Fields> warriorMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.STRENGTH,
			Fields.DEXTERITY, Fields.CONSTITUTION }));
	private final static ArrayList<Fields> mageMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.CUNNING, Fields.MAGIC,
			Fields.WILLPOWER }));
	private final static ArrayList<Fields> rogueMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.COMMUNICATION,
			Fields.DEXTERITY, Fields.PERCEPTION }));

	@SuppressWarnings("serial")
	private final static HashMap<BaseClasses, ArrayList<Fields>> majorAttributes = new HashMap<BaseClasses, ArrayList<Fields>>() {
		{
			put(WARRIOR, warriorMajors);
			put(ROGUE, rogueMajors);
			put(MAGE, mageMajors);
		}
	};

	private BaseClasses() {
		this.text = generateEnumText(this.name());
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		SpecializationClassesSet specializationClassesSet = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		for (SpecializationClasses specializationClass : specializationClassesSet) {
			if (!specializationClass.isBaseClassCompatible(this)) {
				throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", this, specializationClass.toString()));
			}
		}
		characterSheet.characterData.put(Fields.BASECLASS, this);
		for (Fields attribute : CharacterAttribute.ATTRIBUTES) {
			CharacterAttribute characterAttribute = (CharacterAttribute) characterSheet.characterData.get(attribute);
			characterAttribute.setMajority(isAttributeMajor(attribute));
		}
	}

	@Override
	public Class<BaseClasses> getImplementingClass() {
		return BaseClasses.class;
	}

	public boolean isAttributeMajor(Fields field) {
		if (field.getAllowedClass() == CharacterAttribute.class) {
			return majorAttributes.get(this).contains(field);
		} else {
			throw new InvalidParameterException("Field allowed class is not CharacterAttribute!");
		}
	}
}