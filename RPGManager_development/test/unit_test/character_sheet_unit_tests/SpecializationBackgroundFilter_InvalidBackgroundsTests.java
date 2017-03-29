package unit_test.character_sheet_unit_tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.exceptions.InvalidBackgroundException;
import unit_test.character_sheet_unit_tests.common.CommonMethods;
import unit_test.character_sheet_unit_tests.resources.BackgroundUnitTestData;

@RunWith(Parameterized.class)
public class SpecializationBackgroundFilter_InvalidBackgroundsTests {

	// Description:
	//
	// This class tests setting up various invalid specializations to their
	// respective backgrounds
	//
	// Every invalid background - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Specialization: ''{1}'' Background: ''{2}''")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();
		final HashSet<Background> notElfMageBackgrounds = getOtherBackgrounds(BackgroundUnitTestData.elfMageBackgrounds,
				BackgroundUnitTestData.allClassesBackgrounds, BackgroundUnitTestData.mageOnlyBackgrounds);
		final HashSet<Background> notQunariMageBackgrounds = getOtherBackgrounds(new Background[] { Background.TAL_VASHOTH },
				BackgroundUnitTestData.allClassesBackgrounds, BackgroundUnitTestData.mageOnlyBackgrounds);
		final HashSet<Background> notDwarfRogueBackgrounds = getOtherBackgrounds(BackgroundUnitTestData.dwarfRogueBackgrounds,
				BackgroundUnitTestData.allClassesBackgrounds, BackgroundUnitTestData.rogueAndWarriorBackgrounds,
				BackgroundUnitTestData.rogueOnlyBackgrounds);
		final HashSet<Background> notDwarfWarriorBackgrounds = getOtherBackgrounds(BackgroundUnitTestData.dwarfWarriorBackgrounds,
				BackgroundUnitTestData.allClassesBackgrounds, BackgroundUnitTestData.rogueAndWarriorBackgrounds);
		final HashSet<Background> notHumanAndElfWarriorBackgrounds = getOtherBackgrounds(BackgroundUnitTestData.humanAndElfWarriorBackgrounds,
				BackgroundUnitTestData.allClassesBackgrounds, BackgroundUnitTestData.rogueAndWarriorBackgrounds);

		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.MAGE, SpecializationClasses.KEEPER, notElfMageBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.MAGE, SpecializationClasses.SAAREBAS, notQunariMageBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.ROGUE, SpecializationClasses.LEGIONNAIRE_SCOUT, notDwarfRogueBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.WARRIOR, SpecializationClasses.LEGIONNAIRE_WARRIOR,
				notDwarfWarriorBackgrounds));
		parameters.addAll(createListOfObjectsByClassAndSpec(BaseClasses.WARRIOR, SpecializationClasses.LYRIUM_WARRIOR,
				notHumanAndElfWarriorBackgrounds));

		return parameters;
	}

	private final static HashSet<Background> getOtherBackgrounds(Background[] specializationSpecificBackgrounds,
			Background[]... classSpecificBackgrounds) {
		HashSet<Background> otherBackgrounds = new HashSet<>();
		for (Background[] backgroundsByClass : classSpecificBackgrounds) {
			otherBackgrounds.addAll(Arrays.asList(backgroundsByClass));
		}
		otherBackgrounds.removeAll(Arrays.asList(specializationSpecificBackgrounds));
		return otherBackgrounds;
	}

	private final static ArrayList<Object[]> createListOfObjectsByClassAndSpec(BaseClasses baseClass, SpecializationClasses specializationClass,
			HashSet<Background> backgrounds) {
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

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSetInvalidSpecializationClassForBackground() {
		thrown.expect(InvalidBackgroundException.class);
		thrown.expectMessage(String.format("Can not take %s specialization with the %s background!", specializationClassSingleton.toString(),
				background.toString()));
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.LEVEL, CommonMethods.LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.BACKGROUND, background);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClassSingleton);
	}
}