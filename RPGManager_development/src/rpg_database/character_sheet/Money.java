package rpg_database.character_sheet;

import rpg_database.character_sheet.exceptions.CoinOutOfBoundsException;
import rpg_database.character_sheet.interfaces.MultipleFieldsSetter;

public class Money implements MultipleFieldsSetter<Money, Integer> {
	int money;

	public Money() {
		money = 0;
	}

	public void setMoney(Fields field, int sum) {
		if (sum > 99 || sum < 0) {
			String endOfMessage = sum < 0 ? "negative numbers" : "larger than 99";
			throw new CoinOutOfBoundsException(String.format("Copper coins cannot be %s!", endOfMessage));
		}
		// switch (field) {
		// case GOLD_COIN:
		// break;
		// case SILVER_COIN:
		// break;
		// case COPPER_COIN:
		// break;
		// default:
		// throw new RuntimeException("FUCK");
		// }
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
}
