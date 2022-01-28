package sevash.testx;

public class Words
{
	language lang=language.RU;
	main_properties prop;
	final static enum language{
		RU,
		EN
	}
	
	Words(main_properties prop){
		this.prop=prop;
		prop.setWords(this);
	}
	final static enum words{
		PLAY,
		CONTINUE,
		EXIT_MENU,
		EXIT_GAME,
		armor01a,
		armor01b,
		armor01c,
		armor01d,
		armor01e
	}
	
	public void setLanduage(language l){
		lang=l;
	}
	
	public String get(words w){
		if(lang==language.RU)return getRu(w);
		else if(lang==language.EN)return getEn(w);
		return "";
	}
	
	public static String getRu(words w){
		if(w==words.PLAY)return "играть";
		if(w==words.CONTINUE)return "Продолжить";
		if(w==words.EXIT_MENU)return "выйти в лобби";
		if(w==words.EXIT_GAME)return "выйти из игры";
		if(w==words.armor01a)return "кожанный нагрудник";
		if(w==words.armor01b)return "не кожанный нагрудник";
		if(w==words.armor01d)return "золотой нагрудник";
		if(w==words.armor01c)return "сапфировый нагрудник";
		if(w==words.armor01e)return "рубиновый нагрудник";
		
		return "";
	}
	private String getEn(words w){
		if(w==words.PLAY)return "Play";
		if(w==words.CONTINUE)return "Conrinue";
		if(w==words.EXIT_MENU)return "Exit to lobby";
		if(w==words.EXIT_GAME)return "Exit the game";
		if(w==words.armor01a)return "leather chestplate";
		if(w==words.armor01b)return "no leather chestplate";
		if(w==words.armor01d)return "gold chestplate";
		if(w==words.armor01c)return "sapphire chestplate";
		if(w==words.armor01e)return "ruby chestplate";
		
		return "";
	}
}
