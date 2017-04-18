package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.VALID_CLASS_AND_SPECIALIZATION_PAIRINGS_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestDataHierarchicalToFirstKey;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.setAllAttributesTo5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class ValidClassAndSpecializationPairingsTests {

	// Description:
	//
	// This class tests setting up various valid specializations to their base
	// classes (mage, rogue, warrior)
	//
	// Every valid class - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Specialization: ''{1}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestDataHierarchicalToFirstKey(VALID_CLASS_AND_SPECIALIZATION_PAIRINGS_DATA, DataKeys.BASE_CLASS, DataKeys.SPECIALIZATION_CLASS);
	}

	private static Background getAnyRequiredBackground(SpecializationClasses specializationClass) {
		return specializationClass.isBackgroundRestricted() ? specializationClass.getRestrictedBackgrounds().iterator().next()
				: Background.ANDER_SURVIVOR;
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public SpecializationClasses specializationClass;

	@Test
	public void testSetSpecializationClass() {
		SpecializationClassesSet specializationClassSingleton = new SpecializationClassesSet(specializationClass);
		CharacterSheet characterSheet = setAllAttributesTo5(new CharacterSheet("CharacterSheet"));
		characterSheet.setData(Fields.LEVEL, 6);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.BACKGROUND, getAnyRequiredBackground(specializationClass));
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClassSingleton);
		assertEquals(specializationClassSingleton, characterSheet.getData(Fields.SPECIALIZATIONCLASSES));
	}
}