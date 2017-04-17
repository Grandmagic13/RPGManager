package unit_test.character_sheet_unit_tests;

import static unit_test.character_sheet_unit_tests.common.CommonMethods.INVALID_CLASS_AND_SPECIALIZATION_PAIRINGS_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestDataHierarchicalToFirstKey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.JSONException;
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
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class InvalidClassAndSpecializationPairingsTests {

	// Description:
	//
	// This class tests setting up various invalid specializations to their base
	// classes (mage, rogue, warrior)
	//
	// Every invalid class - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Specialization: ''{1}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestDataHierarchicalToFirstKey(INVALID_CLASS_AND_SPECIALIZATION_PAIRINGS_DATA, DataKeys.BASE_CLASS, DataKeys.SPECIALIZATION_CLASS);
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public SpecializationClasses specializationClass;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSetInvalidSpecializationClass() {
		SpecializationClassesSet specializationClassSingleton = new SpecializationClassesSet(specializationClass);
		thrown.expect(InvalidCharacterClassException.class);
		thrown.expectMessage(String.format("%s is not a base class of %s", baseClass.toString(), specializationClassSingleton.toString()));
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClassSingleton);
	}
}