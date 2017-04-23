package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Armors;
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

	// default Armors for BaseClasses

	@Test
	public void testGetDefaultArmorForWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.HEAVY_LEATHER, actualValue);
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
		assertEquals(Armors.ROBE, actualValue);
	}

	// default ArmorRating for BaseClasses

	@Test
	public void testGetDefaultArmorRatingWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(4, actualArmorValue);
	}

	@Test
	public void testGetDefaultArmorRatingRogue() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(3, actualArmorValue);
	}

	@Test
	public void testGetDefaultArmorRatingMage() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(0, actualArmorValue);
	}

	// Functional tests

	@Test
	public void testSetArmorTypeLightPlate() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		Armors expectedArmor = Armors.LIGHT_PLATE;
		characterSheet.setData(Fields.ARMOR_TYPE, expectedArmor);
		Armors actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
		assertEquals(Armors.LIGHT_PLATE, actualValue);
	}

	@Test
	public void testSetArmorRatingHeavyMail() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_MAIL);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(7, actualArmorValue);
	}

	@Test
	public void testSetArmorRatingCustomArmor12() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_PLATE);
		characterSheet.setData(Fields.ARMOR_RATING, 12);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(12, actualArmorValue);
	}

	// Until the talents yet to added, they'll be just comments.

	// We should check that is the warrior has the armor training talent,
	// otherwise we need to test how much penalty will he get from wearing an
	// armor without skill.
	// We should check that is the rogue wearing the armor that he could without
	// armor training.
	// We should check that is the mage has the right specialization to wear
	// armors without penaltys.
	/*
	 * @Test public void testSetArmorForWarrior() { CharacterSheet
	 * characterSheet = new CharacterSheet("characterSheet");
	 * characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR); Armors
	 * expectedArmor = Armors.HEAVY_MAIL;
	 * characterSheet.setData(Fields.ARMOR_TYPE, expectedArmor); Armors
	 * actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
	 * assertEquals(Armors.HEAVY_MAIL, actualValue); }
	 * 
	 * @Test public void testSetArmorForRugue() { CharacterSheet characterSheet
	 * = new CharacterSheet("characterSheet");
	 * characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR); Armors
	 * expectedArmor = Armors.HEAVY_MAIL;
	 * characterSheet.setData(Fields.ARMOR_TYPE, expectedArmor); Armors
	 * actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
	 * assertEquals(Armors.HEAVY_MAIL, actualValue); }
	 * 
	 * @Test public void testSetArmorForMage() { CharacterSheet characterSheet =
	 * new CharacterSheet("characterSheet");
	 * characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE); Armors
	 * expectedArmor = Armors.HEAVY_MAIL;
	 * characterSheet.setData(Fields.ARMOR_TYPE, expectedArmor); Armors
	 * actualValue = characterSheet.getData(Fields.ARMOR_TYPE);
	 * assertEquals(Armors.HEAVY_MAIL, actualValue); }
	 */

	// private functions

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
