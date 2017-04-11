package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

public class ArmorsUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetArmorsMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Armors");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, malformedInput);
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

	@Test
	public void testSetArmorTypeLightPlate() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.LIGHT_PLATE);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.LIGHT_PLATE, actualValue);
	}

	@Test
	public void testGetDefaultArmorForWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.LIGHT_MAIL, actualValue);
	}

	@Test
	public void testGetDefaultArmorForRogue() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.LIGHT_LEATHER, actualValue);
	}

	@Test
	public void testGetDefaultArmorForMage() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.NONE, actualValue);
	}

	@Test
	public void testSetArmorForWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_MAIL);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.HEAVY_MAIL, actualValue);
	}

	@Test
	public void testGetDexterityArmorForWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_MAIL);
		int actualValue = characterSheet.getData(Fields.DEXTERITY_VALUE);
		assertEquals(-3, actualValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorPenalty());
	}

	@Test
	public void testGetCostArmorForWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		Armors armor = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(50, armor.getCost());
	}

	@Test
	public void testGetCostArmorForMage() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		Armors armor = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(0, armor.getCost());
	}

	@Test
	public void testGetDefaultDefenseAnderSurvivor() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_PLATE);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(10, actualArmorValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorRating());
	}

	@Test
	public void testGetDefaultDefenseAnderSurvivor1() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_MAIL);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(7, actualArmorValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorRating());
	}

	@Test
	public void testGetDefaultDefenseAnderSurvivor2() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.LIGHT_PLATE);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(8, actualArmorValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorRating());
	}

	@Test
	public void testGetDefaultDefenseHumanCircleMage() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		characterSheet.setData(Fields.BACKGROUND, Background.HUMAN_CIRCLE_MAGE);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(0, actualArmorValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorRating());
	}

	@Test
	public void testGetDefaultDefenseRogue() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
		Armors armor = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(3, armor.getArmorRating());
	}
}
