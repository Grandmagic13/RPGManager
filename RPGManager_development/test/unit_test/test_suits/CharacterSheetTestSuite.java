package unit_test.test_suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unit_test.character_sheet_unit_tests.BaseClassDependentUnitTests;
import unit_test.character_sheet_unit_tests.CharacterSheetUnitTests;
import unit_test.character_sheet_unit_tests.GetDefaultValuesUnitTest;
import unit_test.character_sheet_unit_tests.InvalidBackgroundUnitTests;
import unit_test.character_sheet_unit_tests.InvalidClassAndSpecializationPairingsTests;
import unit_test.character_sheet_unit_tests.MoneyUnitTests;
import unit_test.character_sheet_unit_tests.ValidBackgroundUnitTests;
import unit_test.character_sheet_unit_tests.ValidClassAndSpecializationPairingsTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GetDefaultValuesUnitTest.class, CharacterSheetUnitTests.class, BaseClassDependentUnitTests.class,
		InvalidBackgroundUnitTests.class, InvalidClassAndSpecializationPairingsTests.class, ValidBackgroundUnitTests.class,
		ValidClassAndSpecializationPairingsTests.class, MoneyUnitTests.class })

public class CharacterSheetTestSuite {

}
