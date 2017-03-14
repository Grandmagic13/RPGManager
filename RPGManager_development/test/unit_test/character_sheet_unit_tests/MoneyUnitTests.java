package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.exceptions.CoinOutOfBoundsException;

public class MoneyUnitTests {
	// fields
	CharacterSheet testCharacterSheet;

	// test setup
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void clearCharacterSheet() {
		testCharacterSheet = new CharacterSheet("TestCharacterSheet");
	}

	// test methods
	@Test
	public void expectException_SetCharacterMoneyMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Money");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.MONEY, malformedInput);
	}

	@Test
	public void expectException_SetCopperCoins100() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "COPPER_COIN cannot be larger than 99!");
		testCharacterSheet.setData(Fields.COPPER_COIN, 100);
	}

	@Test
	public void expectException_SetCopperCoinsMinus5() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "COPPER_COIN cannot be negative number!");
		testCharacterSheet.setData(Fields.COPPER_COIN, -5);
	}

	@Test
	public void expectException_SetSilverCoins100() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "SILVER_COIN cannot be larger than 99!");
		testCharacterSheet.setData(Fields.SILVER_COIN, 100);
	}

	@Test
	public void expectException_SetSilverCoinsMinus5() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "SILVER_COIN cannot be negative number!");
		testCharacterSheet.setData(Fields.SILVER_COIN, -5);
	}

	@Test
	public void expectException_SetGoldCoinsMinus5() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "GOLD_COIN cannot be negative number!");
		testCharacterSheet.setData(Fields.GOLD_COIN, -5);
	}

	@Test
	public void testSetGetGoldCoins101() {
		int goldCoins = 101;
		testCharacterSheet.setData(Fields.GOLD_COIN, goldCoins);
		int actual = testCharacterSheet.getData(Fields.GOLD_COIN);
		assertEquals(goldCoins, actual);
	}

	@Test
	public void testSetGetSilverCoins55() {
		int silverCoins = 55;
		testCharacterSheet.setData(Fields.SILVER_COIN, silverCoins);
		int actual = testCharacterSheet.getData(Fields.SILVER_COIN);
		assertEquals(silverCoins, actual);
	}

	@Test
	public void testSetGetCopperCoins35() {
		int copperCoins = 35;
		testCharacterSheet.setData(Fields.COPPER_COIN, copperCoins);
		int actual = testCharacterSheet.getData(Fields.COPPER_COIN);
		assertEquals(copperCoins, actual);
	}

	@Test
	public void testSetGetSilverCoins65WhenGoldCoins40() {
		int goldCoins = 40;
		int silverCoins = 65;
		testCharacterSheet.setData(Fields.GOLD_COIN, goldCoins);
		testCharacterSheet.setData(Fields.SILVER_COIN, silverCoins);
		int actual = testCharacterSheet.getData(Fields.SILVER_COIN);
		assertEquals(silverCoins, actual);
	}

	@Test
	public void testSetGetCopperCoins30WhenGoldCoins40SilverCoins55() {
		int goldCoins = 40;
		int silverCoins = 55;
		int copperCoins = 30;
		testCharacterSheet.setData(Fields.GOLD_COIN, goldCoins);
		testCharacterSheet.setData(Fields.SILVER_COIN, silverCoins);
		testCharacterSheet.setData(Fields.COPPER_COIN, copperCoins);
		int actual = testCharacterSheet.getData(Fields.COPPER_COIN);
		assertEquals(copperCoins, actual);
	}

	@Test
	public void testSettingIndependency() {
		int goldCoins = 40;
		int silverCoins = 55;
		int copperCoins = 30;
		testCharacterSheet.setData(Fields.GOLD_COIN, 10);
		testCharacterSheet.setData(Fields.SILVER_COIN, 10);
		testCharacterSheet.setData(Fields.COPPER_COIN, 10);
		testCharacterSheet.setData(Fields.GOLD_COIN, goldCoins);
		testCharacterSheet.setData(Fields.SILVER_COIN, silverCoins);
		testCharacterSheet.setData(Fields.COPPER_COIN, copperCoins);
		int[] coins = { testCharacterSheet.getData(Fields.COPPER_COIN), testCharacterSheet.getData(Fields.SILVER_COIN), testCharacterSheet.getData(
				Fields.GOLD_COIN) };
		assertArrayEquals(new int[] { copperCoins, silverCoins, goldCoins }, coins);
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}