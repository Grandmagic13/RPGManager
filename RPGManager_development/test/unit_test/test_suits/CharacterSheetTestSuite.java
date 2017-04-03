package unit_test.test_suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unit_test.character_sheet_unit_tests.AttributeMajorityContentTests;
import unit_test.character_sheet_unit_tests.BackgroundUnitTests;
import unit_test.character_sheet_unit_tests.BaseClassUnitTests;
import unit_test.character_sheet_unit_tests.CharacterAttributeUnitTests;
import unit_test.character_sheet_unit_tests.CharacterSheetCommonUnitTests;
import unit_test.character_sheet_unit_tests.CharacterSheetUnitTests;
import unit_test.character_sheet_unit_tests.FieldsUnitTests;
import unit_test.character_sheet_unit_tests.GetDefaultAttributeUnitTests;
import unit_test.character_sheet_unit_tests.GetDefaultValuesUnitTest;
import unit_test.character_sheet_unit_tests.InvalidBackgroundUnitTests;
import unit_test.character_sheet_unit_tests.InvalidClassAndSpecializationPairingsTests;
import unit_test.character_sheet_unit_tests.MoneyUnitTests;
import unit_test.character_sheet_unit_tests.SpecializationBackgroundFilter_ContentTests;
import unit_test.character_sheet_unit_tests.SpecializationBackgroundFilter_InvalidBackgroundsTests;
import unit_test.character_sheet_unit_tests.SpecializationBackgroundFilter_ValidBackgroundsTests;
import unit_test.character_sheet_unit_tests.SpecializationClassUnitTests;
import unit_test.character_sheet_unit_tests.ValidBackgroundUnitTests;
import unit_test.character_sheet_unit_tests.ValidClassAndSpecializationPairingsTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AttributeMajorityContentTests.class, GetDefaultValuesUnitTest.class, GetDefaultAttributeUnitTests.class,
		CharacterSheetCommonUnitTests.class, CharacterSheetUnitTests.class, FieldsUnitTests.class, BaseClassUnitTests.class,
		SpecializationClassUnitTests.class, BackgroundUnitTests.class, InvalidBackgroundUnitTests.class,
		InvalidClassAndSpecializationPairingsTests.class, ValidBackgroundUnitTests.class, ValidClassAndSpecializationPairingsTests.class,
		MoneyUnitTests.class, CharacterAttributeUnitTests.class, SpecializationBackgroundFilter_InvalidBackgroundsTests.class,
		SpecializationBackgroundFilter_ValidBackgroundsTests.class, SpecializationBackgroundFilter_ContentTests.class })

public class CharacterSheetTestSuite {

}
