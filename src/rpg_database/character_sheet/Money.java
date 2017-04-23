package rpg_database.character_sheet;

import java.security.InvalidParameterException;

import rpg_database.character_sheet.exceptions.CoinOutOfBoundsException;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class Money implements MultipleFieldsGetterSetter<Money, Integer> {
	int money;
	final static int COPPER_EXCHANGE = 1;
	final static int SILVER_EXCHANGE = 100;
	final static int GOLD_EXCHANGE = 10000;

	public Money() {
		money = 0;
	}

	public void setMoney(Fields field, int sum) {
		if (sumExceedsDigitThreshold(field, sum)) {
			throw new CoinOutOfBoundsException(String.format("%s cannot be %s!", field.toString(), getMessageCloser(sum)));
		}
		money -= (getStoredValueByField(field) * getPlaceValueByField(field));
		money += sum * getPlaceValueByField(field);
	}

	private String getMessageCloser(int sum) {
		return sum < 0 ? "negative number" : "larger than 99";
	}

	private boolean sumExceedsDigitThreshold(Fields field, int sum) {
		return (sum > 99 && !field.equals(Fields.GOLD_COIN)) || sum < 0;
	}

	@Override
	public Class<Money> getImplementingClass() {
		return Money.class;
	}

	@Override
	public void setSelfValueByField(Fields field, Integer value) {
		setMoney(field, value);
	}

	@Override
	public Class<Integer> getDataTypeClass() {
		return Integer.class;
	}

	@Override
	public Integer getStoredValueByField(Fields field) {
		return getPlaceOrStoredValueByField(field, true);
	}

	private int getPlaceValueByField(Fields field) {
		return getPlaceOrStoredValueByField(field, false);
	}

	private int getPlaceOrStoredValueByField(Fields field, boolean isGetStoredValue) {
		switch (field) {
		case GOLD_COIN:
			return isGetStoredValue ? money / GOLD_EXCHANGE : GOLD_EXCHANGE;
		case SILVER_COIN:
			return isGetStoredValue ? (money % GOLD_EXCHANGE) / SILVER_EXCHANGE : SILVER_EXCHANGE;
		case COPPER_COIN:
			return isGetStoredValue ? money % SILVER_EXCHANGE : COPPER_EXCHANGE;
		default:
			throw new InvalidParameterException(String.format("Unknown field type: '%s'", field.toString()));
		}
	}
}
