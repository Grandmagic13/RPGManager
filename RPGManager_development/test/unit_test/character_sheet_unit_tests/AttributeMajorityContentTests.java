package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

@RunWith(Parameterized.class)
public class AttributeMajorityContentTests {

	@Parameters(name = "Class: ''{2}'', Attribute majority field: ''{0}'' Expected Majority:''{1}''")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { Fields.COMMUNICATION_MAJORITY, false, BaseClasses.WARRIOR }, { Fields.CONSTITUTION_MAJORITY, true,
				BaseClasses.WARRIOR }, { Fields.CUNNING_MAJORITY, false, BaseClasses.WARRIOR }, { Fields.DEXTERITY_MAJORITY, true,
						BaseClasses.WARRIOR }, { Fields.MAGIC_MAJORITY, false, BaseClasses.WARRIOR }, { Fields.PERCEPTION_MAJORITY, false,
								BaseClasses.WARRIOR }, { Fields.STRENGTH_MAJORITY, true, BaseClasses.WARRIOR }, { Fields.WILLPOWER_MAJORITY, false,
										BaseClasses.WARRIOR },

				{ Fields.COMMUNICATION_MAJORITY, true, BaseClasses.ROGUE }, { Fields.CONSTITUTION_MAJORITY, false, BaseClasses.ROGUE }, {
						Fields.CUNNING_MAJORITY, false, BaseClasses.ROGUE }, { Fields.DEXTERITY_MAJORITY, true, BaseClasses.ROGUE }, {
								Fields.MAGIC_MAJORITY, false, BaseClasses.ROGUE }, { Fields.PERCEPTION_MAJORITY, true, BaseClasses.ROGUE }, {
										Fields.STRENGTH_MAJORITY, false, BaseClasses.ROGUE }, { Fields.WILLPOWER_MAJORITY, false, BaseClasses.ROGUE },

				{ Fields.COMMUNICATION_MAJORITY, false, BaseClasses.MAGE }, { Fields.CONSTITUTION_MAJORITY, false, BaseClasses.MAGE }, {
						Fields.CUNNING_MAJORITY, true, BaseClasses.MAGE }, { Fields.DEXTERITY_MAJORITY, false, BaseClasses.MAGE }, {
								Fields.MAGIC_MAJORITY, true, BaseClasses.MAGE }, { Fields.PERCEPTION_MAJORITY, false, BaseClasses.MAGE }, {
										Fields.STRENGTH_MAJORITY, false, BaseClasses.MAGE }, { Fields.WILLPOWER_MAJORITY, true, BaseClasses.MAGE } });
	}

	@Parameter(0)
	public Fields majority;

	@Parameter(1)
	public boolean expectedMajority;

	@Parameter(2)
	public BaseClasses baseClass;

	@Test
	public void testAttributeMajority() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, baseClass);
		boolean actual = testCharacterSheet.getData(majority);
		assertEquals(expectedMajority, actual);
	}

}