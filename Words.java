package sevash.livingSword;

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
		SETT_LANG,
        LANGUAGE,
		PLAY,
		CONTINUE,
		EXIT_MENU,
		EXIT_GAME,
		MUSIC,
		BUY,
		BUYED,
		NOT_BUYED,
		armor01a,
		armor01b,
		armor01c,
		armor01d,
		armor01e
	}
	
	public void setLanguage(language l){
		lang=l;
	}
	
	public void setLanguage(String l){
		if(l.contentEquals("Russian")) lang=language.RU;
		if(l.contentEquals("Английский")) lang=language.EN;
		prop.menu.settings.lang.reLangT();
	}
	
	public String get(words w){
		if(lang==language.RU)return getRu(w);
		else if(lang==language.EN)return getEn(w);
		return "";
	}
	
	public static String getRu(words w){
		if(w==words.SETT_LANG)return "Язык";
		if(w==words.LANGUAGE)return "Russian";
		if(w==words.PLAY)return "Играть";
		if(w==words.MUSIC)return "Музыка";
		if(w==words.CONTINUE)return "Продолжить";
		if(w==words.EXIT_MENU)return "выйти в лобби";
		if(w==words.EXIT_GAME)return "выйти из игры";
		if(w==words.armor01a)return "кожанный нагрудник";
		if(w==words.armor01b)return "не кожанный нагрудник";
		if(w==words.armor01d)return "золотой нагрудник";
		if(w==words.armor01c)return "сапфировый нагрудник";
		if(w==words.armor01e)return "рубиновый нагрудник";
		if(w==words.BUYED)return "Куплено!";
		if(w==words.NOT_BUYED)return "Не куплено!";
		if(w==words.BUY)return "Купить";
		return "нет перевода!";
	}
	private String getEn(words w){
		if(w==words.SETT_LANG)return "Language";
		if(w==words.LANGUAGE)return "Английский";
		if(w==words.PLAY)return "Play";
		if(w==words.MUSIC)return "Music";
		if(w==words.CONTINUE)return "Continue";
		if(w==words.EXIT_MENU)return "Exit to lobby";
		if(w==words.EXIT_GAME)return "Exit the game";
		if(w==words.armor01a)return "leather chestplate";
		if(w==words.armor01b)return "no leather chestplate";
		if(w==words.armor01d)return "gold chestplate";
		if(w==words.armor01c)return "sapphire chestplate";
		if(w==words.armor01e)return "ruby chestplate";
		if(w==words.BUYED)return "Buyed!";
		if(w==words.NOT_BUYED)return "Not buyed!";
		if(w==words.BUY)return "Buy";
		return "not have a translation!";
	}
}
