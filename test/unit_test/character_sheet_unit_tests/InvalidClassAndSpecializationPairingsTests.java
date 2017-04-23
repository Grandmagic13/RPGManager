package unit_test.character_sheet_unit_tests;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import unit_test.character_sheet_unit_tests.resources.SpecializationCompatibilityData;

@RunWith(Parameterized.class)
public class InvalidClassAndSpecializationPairingsTests {

	// Description:
	//
	// This class tests setting up various invalid specializations to their base
	// classes (mage, rogue, warrior)
	//
	// Every invalid class - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{1}'' Specialization: ''{0}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();
		for (BaseClasses baseClass : BaseClasses.values()) {
			switch (baseClass) {
			case MAGE:
				addParameters(parameters, baseClass, SpecializationCompatibilityData.warriorSpecializations);
				addParameters(parameters, baseClass, SpecializationCompatibilityData.rogueSpecializations);
				break;
			case ROGUE:
				addParameters(parameters, baseClass, SpecializationCompatibilityData.mageSpecializations);
				addParameters(parameters, baseClass, SpecializationCompatibilityData.warriorSpecializations);
				break;
			case WARRIOR:
				addParameters(parameters, baseClass, SpecializationCompatibilityData.rogueSpecializations);
				addParameters(parameters, baseClass, SpecializationCompatibilityData.mageSpecializations);
				break;
			}
		}
		return parameters;
	}

	private static void addParameters(ArrayList<Object[]> parameters, BaseClasses baseClass, SpecializationClasses[] specializationClasses) {
		for (SpecializationClasses specializationClass : specializationClasses) {
			parameters.add(new Object[] { new SpecializationClassesSet(specializationClass), baseClass });
		}
	}

	@Parameter(0)
	public SpecializationClassesSet specializationClassSingleton;

	@Parameter(1)
	public BaseClasses baseClass;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSetInvalidSpecializationClass() {
		thrown.expect(InvalidCharacterClassException.class);
		thrown.expectMessage(String.format("%s is not a base class of %s", baseClass.toString(), specializationClassSingleton.toString()));
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClassSingleton);
	}
}