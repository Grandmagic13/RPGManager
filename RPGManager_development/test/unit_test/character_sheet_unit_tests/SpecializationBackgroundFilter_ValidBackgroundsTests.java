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
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import unit_test.character_sheet_unit_tests.common.CommonMethods;
import unit_test.character_sheet_unit_tests.resources.BackgroundUnitTestData;

@RunWith(Parameterized.class)
public class SpecializationBackgroundFilter_ValidBackgroundsTests {
	// TODO
	// Description:
	//
	// This class tests setting up various valid specializations to their
	// respective backgrounds
	//
	// Every valid background - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Specialization: ''{1}'' Background: ''{2}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();

		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.MAGE, SpecializationClasses.KEEPER,
				BackgroundUnitTestData.elfMageBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.MAGE, SpecializationClasses.SAAREBAS, new Background[] {
				Background.TAL_VASHOTH }));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.ROGUE, SpecializationClasses.LEGIONNAIRE_SCOUT,
				BackgroundUnitTestData.dwarfRogueBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.WARRIOR, SpecializationClasses.LEGIONNAIRE_WARRIOR,
				BackgroundUnitTestData.dwarfWarriorBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.WARRIOR, SpecializationClasses.LYRIUM_WARRIOR,
				BackgroundUnitTestData.humanAndElfWarriorBackgrounds));

		return parameters;
	}

	private final static ArrayList<Object[]> createListOfObjectsByClassAndSpec(BaseClasses baseClass, SpecializationClasses specializationClass,
			Background[] backgrounds) {
		ArrayList<Object[]> parameters = new ArrayList<>();
		for (Background background : backgrounds) {
			parameters.add(new Object[] { baseClass, new SpecializationClassesSet(specializationClass), background });
		}
		return parameters;
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public SpecializationClassesSet specializationClassSingleton;

	@Parameter(2)
	public Background background;

	@Test
	public void testSetInvalidSpecializationClassForBackground() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.LEVEL, CommonMethods.LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.BACKGROUND, background);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClassSingleton);

		SpecializationClassesSet actual = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		SpecializationClassesSet expected = specializationClassSingleton;
		assertEquals(expected, actual);
	}
}