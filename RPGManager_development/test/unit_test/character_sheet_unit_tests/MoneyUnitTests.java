package unit_test.character_sheet_unit_tests;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.exceptions.CoinOutOfBoundsException;

public class MoneyUnitTests {
	// fields
	CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");

	// test methods
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetCharacterMoneyMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Money");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.MONEY, malformedInput);
	}

	@Test
	public void expectException_SetCopperCoins100() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "Copper coins cannot be larger than 99!");
		testCharacterSheet.setData(Fields.COPPER_COIN, 100);
	}

	@Test
	public void expectException_SetCopperCoinsMinus5() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "Copper coins cannot be negative numbers!");
		testCharacterSheet.setData(Fields.COPPER_COIN, -5);
	}

	@Test
	public void expectException_SetSilverCoins100() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "Copper coins cannot be larger than 99!");
		testCharacterSheet.setData(Fields.SILVER_COIN, 100);
	}

	@Test
	public void expectException_SetSilverCoinsMinus5() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "Copper coins cannot be negative numbers!");
		testCharacterSheet.setData(Fields.SILVER_COIN, -5);
	}

	@Test
	public void expectException_SetGoldCoins100() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "Copper coins cannot be larger than 99!");
		testCharacterSheet.setData(Fields.GOLD_COIN, 100);
	}

	@Test
	public void expectException_SetGoldCoinsMinus5() {
		expectExceptionWithMessage(CoinOutOfBoundsException.class, "Copper coins cannot be negative numbers!");
		testCharacterSheet.setData(Fields.GOLD_COIN, -5);
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
