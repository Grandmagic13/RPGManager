package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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

@RunWith(Parameterized.class)
public class CharacterSheetValidBackgroundUnitTests {

	// Description:
	//
	// This class tests setting up various valid backgrounds to their base
	// classes (mage, rogue, warrior)
	//
	// Every valid class - background pairing is tested

	@Parameters(name = "{index}: Class: ''{1}'' Background: ''{0}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();
		Background[] mageOnlyBackgrounds = { Background.APOSTATE, Background.CIRCLE_MAGE, Background.TEVINTER_ALTUS, Background.TEVINTER_LAETAN };
		Background[] rogueOnlyBackgrounds = { Background.DWARF_DUSTER };
		Background[] rogueAndWarriorBackgrounds = { Background.ANTIVAN_WAYFARER, Background.CITY_ELF, Background.FERELDAN_CRAFTSMEN,
				Background.FERELDAN_FREEMAN, Background.FERELDAN_NOBLE, Background.FREE_MARCHER, Background.HIGH_BORN_DWARF,
				Background.LOW_BORN_DWARF, Background.NEVARRAN_ADVENTURER, Background.ORLESIAN_COMMONER, Background.ORLESIAN_NOBLE,
				Background.ORLESIAN_STUDENT, Background.QUNARI_BERESAAD, Background.RIVAINI_MERCHANT, Background.SEHERON_CONVERT,
				Background.SURFACE_DWARF, Background.TEVINTER_SOPORATI, Background.WAKING_SEA_RAIDER };
		Background[] allClassesBackgrounds = { Background.ANDER_SURVIVOR, Background.AVVAR, Background.CHASIND_WILDER, Background.DALISH_ELF,
				Background.ESCAPED_ELVEN_SLAVE, Background.ORLESIAN_EXILE, Background.TAL_VASHOTH };
		for (BaseClasses baseClass : BaseClasses.values()) {
			if (baseClass.equals(BaseClasses.MAGE)) {
				for (Background background : mageOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : allClassesBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
			} else if (baseClass.equals(BaseClasses.ROGUE)) {
				for (Background background : rogueOnlyBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : rogueAndWarriorBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : allClassesBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
			} else if (baseClass.equals(BaseClasses.WARRIOR)) {
				for (Background background : rogueAndWarriorBackgrounds) {
					parameters.add(new Object[] { background, baseClass });
				}
				for (Background background : allClassesBackgrounds) {
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
