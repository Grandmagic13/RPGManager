package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.character_class.BaseClasses;
import unit_test.character_sheet_unit_tests.resources.BackgroundUnitTestData;

@RunWith(Parameterized.class)
public class ValidBackgroundUnitTests {

	// Description:
	//
	// This class tests setting up various valid backgrounds to their base
	// classes (mage, rogue, warrior)
	//
	// Every valid class - background pairing is tested

	@Parameters(name = "{index}: Class: ''{1}'' Background: ''{0}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();
		for (BaseClasses baseClass : BaseClasses.values()) {
			if (baseClass.equals(BaseClasses.MAGE)) {
				for (Background background : BackgroundUnitTestData.mageOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : BackgroundUnitTestData.allClassesBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
			} else if (baseClass.equals(BaseClasses.ROGUE)) {
				for (Background background : BackgroundUnitTestData.rogueOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : BackgroundUnitTestData.rogueAndWarriorBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : BackgroundUnitTestData.allClassesBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
			} else if (baseClass.equals(BaseClasses.WARRIOR)) {
				for (Background background : BackgroundUnitTestData.rogueAndWarriorBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : BackgroundUnitTestData.allClassesBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
			}
		}
		return parameters;
	}

	@Parameter(0)
	public Background background;

	@Parameter(1)
	public BaseClasses baseClass;

	@Test
	public void testSetBackground() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, baseClass);
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		Background background = testCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(this.background, background);
	}
}
