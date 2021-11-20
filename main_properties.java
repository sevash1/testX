package sevash.testx;
import android.widget.*;
import android.content.*;
import android.app.*;
import android.graphics.*;
import android.media.*;
import java.util.*;

public class main_properties
{
	public RelativeLayout main;
	public RelativeLayout menu;
	public RelativeLayout world;
	public Context context;
	public Activity activity;
	public float playerPosX;
	public float playerPosY=0;
	public float screenW=0;
	public float screenH=0;
	public BitmapFactory.Options options;
	public Typeface ttf;
	public Thread worldThread;
	public Game_stage stage;
	public RelativeLayout bl;
	public Runnable worldThreadRun;
	public MediaPlayer menu_music;
	public Player player;
	public Joystick joystick;
	public Money money;
	public RelativeLayout grass;
	public Btn_attack attack;
	public List skeletons;
	public Btn_shield shield;
	public Btn_roll roll;
	
	public main_properties(
	RelativeLayout main,
	RelativeLayout menu,
	RelativeLayout world,
	Context context,
	Activity activity,
	float screenW,
	float screenH,
	BitmapFactory.Options options,
	Typeface ttf,
	Thread worldThread,
	Game_stage stage,
	RelativeLayout bl,
	Runnable run,
	MediaPlayer menu_music,
	float playerPosX,
	List skeletons){
		
		this.main=main;
		this.menu=menu;
		this.world=world;
		this.context=context;
		this.activity=activity;
		this.screenW=screenW;
		this.screenH=screenH;
		this.options=options;
		this.ttf=ttf;
		this.worldThread=worldThread;
		this.stage=stage;
		this.bl=bl;
		this.worldThreadRun=run;
		this.menu_music=menu_music;
		this.playerPosX=playerPosX;
		this.skeletons=skeletons;
	}
	public void setPlayer(Player player){
		this.player=player;
	}
	public void setJoystick(Joystick joy){
		this.joystick=joy;
	}
	public void setMoney(Money money){
		this.money=money;
	}
	public void setGrass(RelativeLayout grass){
		this.grass=grass;
	}
	public void setAttack(Btn_attack attack){
		this.attack=attack;
	}
	public void setShield(Btn_shield shield){
		this.shield=shield;
	}
	public void setRoll(Btn_roll roll){
		this.roll=roll;
	}
	
}
