package sevash.testx;
import android.widget.*;
import android.content.*;
import android.app.*;
import android.graphics.*;
import android.media.*;
import java.util.*;
import android.view.*;

public class main_properties
{
	public RelativeLayout main;
	public RelativeLayout menuLayout;
	public RelativeLayout world;
	public Context context;
	public Activity activity;
	public float playerPosX=0;
	public float playerPosY=0;
	public float screenW=0;
	public float screenH=0;
	public BitmapFactory.Options options;
	public Typeface ttf;
	public Thread worldThread;
	public Game_stage stage;
	public RelativeLayout bl;
	public Runnable worldThreadRun;
	public MediaPlayer music;
	public Player player;
	public Joystick joystick;
	public Money money;
	public RelativeLayout grass;
	public Btn_attack attack;
	public List skeletons;
	public Btn_shield shield;
	public Btn_roll roll;
	public RelativeLayout playerAndUi;
	public Inventory inv;
	public Shop shop;
	public LoadBar loadBar;
	public List musicList=new ArrayList<MediaPlayer>();
	public List treesList=new ArrayList<Bitmap>();
	public Menu menu;
	public List items=new ArrayList<Item>();
	public Words words;
	
	
	
	public main_properties(
	RelativeLayout main,
	RelativeLayout menuLayout,
		RelativeLayout playerAndUi,
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
	List skeletons){
		
		this.main=main;
	this.menuLayout=menuLayout;
    	this.playerAndUi=playerAndUi;
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
	public void setWorld(RelativeLayout world){
		this.world=world;
	}
	public void setInventory(Inventory inv){
		this.inv=inv;
	}
	public void setShop(Shop shp){
		this.shop=shp;
	}
	public void setLoadBar(LoadBar bar){
		this.loadBar=bar;
	}
	public void addMusic(MediaPlayer music){
		this.musicList.add(music);
	}
	public void addTree(ImageView tree){
		this.treesList.add(tree);
	}
	public void setMenu(Menu menu){
		this.menu=menu;
	}
	public void vanishGameButtons(){
	activity.runOnUiThread(r1);
	}
	public void showGameButtons(){
		activity.runOnUiThread(r2);
	}
	public void setWords(Words words){
		this.words=words;
	}
	Runnable r1=new Runnable(){

		@Override
		public void run()
		{
			attack.attack.setVisibility(View.INVISIBLE);
			shield.shield.setVisibility(View.INVISIBLE);
			roll.roll.setVisibility(View.INVISIBLE);
			attack.border.setVisibility(View.INVISIBLE);
			shield.border.setVisibility(View.INVISIBLE);
			roll.border.setVisibility(View.INVISIBLE);
			
		}
	};
	Runnable r2=new Runnable(){

		@Override
		public void run()
		{
			attack.attack.setVisibility(View.VISIBLE);
			shield.shield.setVisibility(View.VISIBLE);
			roll.roll.setVisibility(View.VISIBLE);
			attack.border.setVisibility(View.VISIBLE);
			shield.border.setVisibility(View.VISIBLE);
			roll.border.setVisibility(View.VISIBLE);
			
		}
	};
	Item findItem(int id){
		for(Item item1:items){
			if(item1.id==id){
				 return new Item(this,item1.id,item1.pictureInt,item1.price,item1.name,item1.description,item1.dat);
	
			}
		}
		return null;
	}
}
