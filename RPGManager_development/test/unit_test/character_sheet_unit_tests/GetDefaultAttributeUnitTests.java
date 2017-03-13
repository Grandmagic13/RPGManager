package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.CharacterAttribute;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

@RunWith(Parameterized.class)
public class GetDefaultAttributeUnitTests {

	@Parameters(name = "{0}: isMajor: ''{1}''")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { Fields.ATTRIBUTE_COMMUNICATION, false }, { Fields.ATTRIBUTE_CONSTITUTION, true }, {
				Fields.ATTRIBUTE_CUNNING, false }, { Fields.ATTRIBUTE_DEXTERITY, true }, { Fields.ATTRIBUTE_MAGIC, false }, {
						Fields.ATTRIBUTE_PERCEPTION, false }, { Fields.ATTRIBUTE_STRENGTH, true }, { Fields.ATTRIBUTE_WILLPOWER, false } });
	}

	@Parameter(0)
	public Fields field;

	@Parameter(1)
	public boolean expectedMajority;

	@Test
	public void testGetDefaultAttributeMajority() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		boolean actual = testCharacterSheet.<CharacterAttribute>getData(field).isMajor();
		assertEquals(expectedMajority, actual);
	}

	public void testGetDefaultAttributeValue() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		int actual = testCharacterSheet.<CharacterAttribute>getData(field).getValue();
		assertEquals(0, actual);
	}
}