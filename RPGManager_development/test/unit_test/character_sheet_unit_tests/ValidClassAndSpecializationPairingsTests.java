package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import unit_test.character_sheet_unit_tests.resources.SpecializationCompatibilityData;

@RunWith(Parameterized.class)
public class ValidClassAndSpecializationPairingsTests {

	// Description:
	//
	// This class tests setting up various valid specializations to their base
	// classes (mage, rogue, warrior)
	//
	// Every valid class - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{1}'' Specialization: ''{0}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();
		for (BaseClasses baseClass : BaseClasses.values()) {
			switch (baseClass) {
			case MAGE:
				for (SpecializationClasses specializationClass : SpecializationCompatibilityData.mageSpecializations) {
					parameters.add(new Object[] { specializationClass, baseClass });
				}
				break;
			case ROGUE:
				for (SpecializationClasses specializationClass : SpecializationCompatibilityData.rogueSpecializations) {
					parameters.add(new Object[] { specializationClass, baseClass });
				}
				break;
			case WARRIOR:
				for (SpecializationClasses specializationClass : SpecializationCompatibilityData.warriorSpecializations) {
					parameters.add(new Object[] { specializationClass, baseClass });
				}
				break;
			}
		}
		return parameters;
	}

	@Parameter(0)
	public SpecializationClasses specializationClass;

	@Parameter(1)
	public BaseClasses baseClass;

	@Test
	public void testSetSpecializationClass() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, specializationClass);
		assertEquals(specializationClass, characterSheet.getData(Fields.SPECIALIZATIONCLASS));
	}
}