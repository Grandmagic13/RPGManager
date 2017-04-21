package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum BaseClasses implements CustomSetter<BaseClasses> {
	WARRIOR(Armors.HEAVY_LEATHER), ROGUE(Armors.LIGHT_LEATHER), MAGE(Armors.ROBE);

	private final String text;

	private final Armors defaultArmor;

	private final static ArrayList<Fields> warriorMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.STRENGTH, Fields.DEXTERITY,
			Fields.CONSTITUTION }));
	private final static ArrayList<Fields> mageMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.CUNNING, Fields.MAGIC,
			Fields.WILLPOWER }));
	private final static ArrayList<Fields> rogueMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.COMMUNICATION, Fields.DEXTERITY,
			Fields.PERCEPTION }));

	@SuppressWarnings("serial")
	private final static HashMap<BaseClasses, ArrayList<Fields>> majorAttributes = new HashMap<BaseClasses, ArrayList<Fields>>() {
		{
			put(WARRIOR, warriorMajors);
			put(ROGUE, rogueMajors);
			put(MAGE, mageMajors);
		}
	};

	private BaseClasses(Armors armor) {
		this.text = generateEnumText(this.name());
		this.defaultArmor = armor;
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
		characterSheet.characterData.put(Fields.ARMOR_TYPE, this.defaultArmor);
		characterSheet.characterData.put(Fields.ARMOR_RATING, this.defaultArmor.getArmorRating());
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