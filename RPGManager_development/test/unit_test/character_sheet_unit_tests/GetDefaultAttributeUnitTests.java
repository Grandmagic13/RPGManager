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
		return Arrays.asList(new Object[][] { { Fields.COMMUNICATION_MAJORITY, false, Fields.COMMUNICATION_VALUE }, { Fields.CONSTITUTION_MAJORITY,
				true, Fields.CONSTITUTION_VALUE }, { Fields.CUNNING_MAJORITY, false, Fields.CUNNING_VALUE }, { Fields.DEXTERITY_MAJORITY, true,
						Fields.DEXTERITY_VALUE }, { Fields.MAGIC_MAJORITY, false, Fields.MAGIC_VALUE }, { Fields.PERCEPTION_MAJORITY, false,
								Fields.PERCEPTION_VALUE }, { Fields.STRENGTH_MAJORITY, true, Fields.STRENGTH_VALUE }, { Fields.WILLPOWER_MAJORITY,
										false, Fields.WILLPOWER_VALUE } });
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