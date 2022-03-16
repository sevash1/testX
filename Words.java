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
		STUDIED,
		NOT_STUDIED,
		STUDY,
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
		else if(w==words.LANGUAGE)return "Russian";
		else if(w==words.PLAY)return "Играть";
		else if(w==words.MUSIC)return "Музыка";
		else if(w==words.CONTINUE)return "Продолжить";
		else if(w==words.EXIT_MENU)return "выйти в лобби";
		else if(w==words.EXIT_GAME)return "выйти из игры";
		else if(w==words.armor01a)return "кожанный нагрудник";
		else if(w==words.armor01b)return "не кожанный нагрудник";
		else if(w==words.armor01d)return "золотой нагрудник";
		else if(w==words.armor01c)return "сапфировый нагрудник";
		else if(w==words.armor01e)return "рубиновый нагрудник";
		else if(w==words.BUYED)return "Куплено!";
		else if(w==words.NOT_BUYED)return "Не куплено!";
		else if(w==words.BUY)return "Купить";
		else if(w==words.STUDIED)return "Изучено!";
		else if(w==words.NOT_STUDIED)return "Не изучено!";
		else if(w==words.STUDY)return "Изучить";
		
		return "нет перевода!";
	}
	private String getEn(words w){
		if(w==words.SETT_LANG)return "Language";
		else if(w==words.LANGUAGE)return "Английский";
		else if(w==words.PLAY)return "Play";
		else if(w==words.MUSIC)return "Music";
		else if(w==words.CONTINUE)return "Continue";
		else if(w==words.EXIT_MENU)return "Exit to lobby";
		else if(w==words.EXIT_GAME)return "Exit the game";
		else if(w==words.armor01a)return "leather chestplate";
		else if(w==words.armor01b)return "no leather chestplate";
		else if(w==words.armor01d)return "gold chestplate";
		else if(w==words.armor01c)return "sapphire chestplate";
		else if(w==words.armor01e)return "ruby chestplate";
		else if(w==words.BUYED)return "Buyed!";
		else if(w==words.NOT_BUYED)return "Not buyed!";
		else if(w==words.BUY)return "Buy";
		else if(w==words.STUDIED)return "Studied!";
		else if(w==words.NOT_STUDIED)return "Not studied!";
		else if(w==words.STUDY)return "Study";
		
		return "not have a translation!";
	}
}
