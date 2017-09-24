package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum BaseClasses implements CustomSetter<BaseClasses> {
	WARRIOR, ROGUE, MAGE;

	private final String text;

	private final Armors defaultArmor;

	@SuppressWarnings("serial")
	private final static HashMap<BaseClasses, HashSet<Fields>> majorAttributes = new HashMap<BaseClasses, HashSet<Fields>>() {
		{
			FieldRules baseClassesRules = FieldRulesFactory.getFieldRules(FieldRulesFactory.BASE_CLASSES);
			HashSet<Fields> warriorMajors = new HashSet<>();
			warriorMajors.addAll(baseClassesRules.getEnumsForField(WARRIOR, Fields.class, Keys.MAJOR_ATTRIBUTES_ARRAY));
			put(WARRIOR, warriorMajors);
			HashSet<Fields> rogueMajors = new HashSet<>();
			rogueMajors.addAll(baseClassesRules.getEnumsForField(ROGUE, Fields.class, Keys.MAJOR_ATTRIBUTES_ARRAY));
			put(ROGUE, rogueMajors);
			HashSet<Fields> mageMajors = new HashSet<>();
			mageMajors.addAll(baseClassesRules.getEnumsForField(MAGE, Fields.class, Keys.MAJOR_ATTRIBUTES_ARRAY));
			put(MAGE, mageMajors);
		}
	};

	private BaseClasses() {
		FieldRules baseClassesRules = FieldRulesFactory.getFieldRules(FieldRulesFactory.BASE_CLASSES);
		this.text = generateEnumText(this.name());
		this.defaultArmor = baseClassesRules.getEnumForField(this, Armors.class, Keys.ARMOR_TYPE);
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
		characterSheet.characterData.put(Fields.WEAPON_GROUPS, new WeaponGroupsSet(WeaponGroups.BRAWLING));
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