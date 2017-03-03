package rpg_database.character_sheet;

public class Money {
	int gold;
	int silver;
	int copper;
	int money_temp;
	public Money(int money){
		while(money >= 10000){
			money_temp = money / 10000;
			money = money % 10000;
			gold = money_temp;
		}
		while(money >= 100){
			money_temp = money / 100;
			money = money % 100;
			silver = money_temp;
		}
		copper = money;
	}
	public String toWriteConsole(){
		String coins = String.format("You have %s gold, %s silver and %s copper coins", gold, silver, copper);
		return coins;
	}
}
