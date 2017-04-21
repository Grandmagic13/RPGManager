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

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import unit_test.character_sheet_unit_tests.resources.BackgroundUnitTestData;

@RunWith(Parameterized.class)
public class InvalidBackgroundUnitTests {

	// Description:
	//
	// This class tests setting up various invalid backgrounds to their base
	// classes (mage, rogue, warrior)
	//
	// Every invalid class - background pairing is tested

	@Parameters(name = "{index}: Class: ''{1}'' Background: ''{0}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();

		for (BaseClasses baseClass : BaseClasses.values()) {
			switch (baseClass) {
			case MAGE:
				for (Background background : BackgroundUnitTestData.rogueOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : BackgroundUnitTestData.rogueAndWarriorBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				break;
			case ROGUE:
				for (Background background : BackgroundUnitTestData.mageOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				break;
			case WARRIOR:
				for (Background background : BackgroundUnitTestData.mageOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : BackgroundUnitTestData.rogueOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				break;
			}
		}
		return parameters;
	}

	@Parameter(0)
	public Background background;

	@Parameter(1)
	public BaseClasses baseClass;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSetInvalidBackground() {
		thrown.expect(InvalidCharacterClassException.class);
		thrown.expectMessage(String.format("%s is not a %s background!", background.toString(), baseClass.toString()));
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, baseClass);
		testCharacterSheet.setData(Fields.BACKGROUND, background);
	}
}