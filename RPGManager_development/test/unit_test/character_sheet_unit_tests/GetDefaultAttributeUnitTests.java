package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

@RunWith(Parameterized.class)
public class GetDefaultAttributeUnitTests {

	@Parameters(name = "Attribute fields: ''{0}'', ''{2}'' Expected Majority:''{1}'' Expected Value: 0")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { Fields.ATTRIBUTE_COMMUNICATION_MAJORITY, false, Fields.ATTRIBUTE_COMMUNICATION_VALUE }, {
				Fields.ATTRIBUTE_CONSTITUTION_MAJORITY, true, Fields.ATTRIBUTE_CONSTITUTION_VALUE }, { Fields.ATTRIBUTE_CUNNING_MAJORITY, false,
						Fields.ATTRIBUTE_CUNNING_VALUE }, { Fields.ATTRIBUTE_DEXTERITY_MAJORITY, true, Fields.ATTRIBUTE_DEXTERITY_VALUE }, {
								Fields.ATTRIBUTE_MAGIC_MAJORITY, false, Fields.ATTRIBUTE_MAGIC_VALUE }, { Fields.ATTRIBUTE_PERCEPTION_MAJORITY, false,
										Fields.ATTRIBUTE_PERCEPTION_VALUE }, { Fields.ATTRIBUTE_STRENGTH_MAJORITY, true,
												Fields.ATTRIBUTE_STRENGTH_VALUE }, { Fields.ATTRIBUTE_WILLPOWER_MAJORITY, false,
														Fields.ATTRIBUTE_WILLPOWER_VALUE } });
	}

	@Parameter(0)
	public Fields majority;

	@Parameter(1)
	public boolean expectedMajority;

	@Parameter(2)
	public Fields value;

	@Test
	public void testGetDefaultAttributeMajority() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		boolean actual = testCharacterSheet.getData(majority);
		assertEquals(expectedMajority, actual);
	}

	@Test
	public void testGetDefaultAttributeValue() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		int actual = testCharacterSheet.getData(value);
		assertEquals(0, actual);
	}
}