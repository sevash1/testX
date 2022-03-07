package sevash.livingSword;

import android.app.*;
import android.content.pm.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.RelativeLayout.*;
import java.util.*;
import sevash.livingSword.files.*;
import android.widget.ImageView.*;
import android.content.*;
import android.content.res.*;
import android.media.*;
import android.*;
import android.util.*;


public class MainActivity extends Activity 
{
	RelativeLayout main;
	RelativeLayout menu;
	RelativeLayout world;
	RelativeLayout playerAndUi;
	RelativeLayout grass_layout;
	main_properties prop;
	
	LayoutParams params1=new LayoutParams(256,256);
	LayoutParams params2=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	List read=new ArrayList<String>();
	float[] background_posX=new float[32];
	float[] background_posY=new float[16];
	ImageView[][] imm=new ImageView[32][16];
	List skeletons=new ArrayList<Skeleton>();
	TextView coords;
	String coord="";
	boolean run1_c=false;
	long time1=0,time2=0,time3=0;
	long time5=0;
	long last_back_pressed_time1=0;
	long last_back_pressed_time2=0;
	float last_playerPosX=0;
	float last_playerPosY=0;
	int fps=0;
	Thread thread;
	Context context;
	boolean isPause=false;
	Game_stage stage;
	RelativeLayout pause_lay;
	Activity activity;
	Thread thread9;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P){
			getWindow().getAttributes().layoutInDisplayCutoutMode=
				WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
		}
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
			getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				|View.SYSTEM_UI_FLAG_IMMERSIVE
				|View.SYSTEM_UI_FLAG_FULLSCREEN
				|View.DRAWING_CACHE_QUALITY_HIGH
				|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				|View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				|View.SYSTEM_UI_FLAG_LOW_PROFILE);
				}
			setContentView(R.layout.main);
			activity=this;
			new Thread(run4).start();
	}

	@Override
	protected void onDestroy()
	{
		stage.setStage(Game_stage.EXIT);
		if(prop.music!=null)
		prop.music.stop();
		files.writeFile(prop,getExternalFilesDir("").toString(),"f.txt",new String[]{});
		finish();
		super.onDestroy();
	
	}

	@Override
	protected void onPause()
	{
		if(prop.music != null)if( prop.music.isPlaying()){
			isPause=true;
			prop.music.pause();
	
			}
		files.writeFile(prop,getExternalFilesDir("").toString(),"f.txt",new String[]{Float.toString(prop.playerPosX),String.valueOf(prop.playerPosY),String.valueOf(prop.money.money_count)});
		
		super.onPause();
	}
				
	Runnable run = new Runnable(){
		@Override
		public void run()
		{
				runOnUiThread(run1);
				while(!run1_c){
					if(Game_stage.EXIT==prop.stage.getStage()) 
						return;
				}
			while(stage.getStage()==Game_stage.WORLD){
				if(Game_stage.EXIT==prop.stage.getStage()) 
					return;
				
				try{
					time1=System.currentTimeMillis();
					Thread.sleep(8);
					time2=System.currentTimeMillis();
					time3=time2-time1;
					}
			catch(Exception e){continue;}
					
					fps++;
					
					
					if(prop.player.a_anim==Player.active_anim.ATTACK){
					   if(!prop.joystick.joystick_pressed
					&&!Float.isNaN(prop.joystick.attackX)){
					 prop.playerPosX=prop.playerPosX+prop.joystick.attackX*time3;
					}
					else if(prop.attack.pressed
					&&!Float.isNaN(prop.joystick.attackX)){
				prop.playerPosX=prop.playerPosX+prop.joystick.attackX*time3;
						}
						}
						
					if(prop.player.a_anim==Player.active_anim.SHIELD){
						
					}
					
					if(prop.player.a_anim==Player.active_anim.IDLE){
						if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
							||(Float.isNaN(prop.joystick.ratioX)
							||Float.isNaN(prop.joystick.ratioY)
						   ||Float.isNaN(prop.playerPosX)
						   ||Float.isNaN(prop.playerPosY))){}
							}
					if(prop.player.a_anim==Player.active_anim.ROLL){
						if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
						   ||(Float.isNaN(prop.joystick.ratioX)
						   ||Float.isNaN(prop.joystick.ratioY)
						   ||Float.isNaN(prop.playerPosX)
						   ||Float.isNaN(prop.playerPosY))){}else{
							prop.playerPosX=prop.playerPosX+prop.joystick.ratioX*time3;
							prop.playerPosY=prop.playerPosY+prop.joystick.ratioY*time3;

						}}
					
						if(prop.player.a_anim==Player.active_anim.RUN){
						if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
						   ||(Float.isNaN(prop.joystick.ratioX)
						   ||Float.isNaN(prop.joystick.ratioY)
						   ||Float.isNaN(prop.playerPosX)
						   ||Float.isNaN(prop.playerPosY))){}else{
							prop.playerPosX=prop.playerPosX+prop.joystick.ratioX*time3;
							prop.playerPosY=prop.playerPosY+prop.joystick.ratioY*time3;	
					}}
				
					if(last_playerPosX==prop.playerPosX&&last_playerPosY==prop.playerPosY){}
					else {
						last_playerPosX=prop.playerPosX;
						last_playerPosY=prop.playerPosY;
						grass_layout.setScrollX((int)(prop.playerPosX+prop.joystick.screenSpX-((int)(prop.playerPosX/256))*256+256*6));
						grass_layout.setScrollY((int)(prop.playerPosY+prop.joystick.screenSpY-((int)(prop.playerPosY/256))*256+256*3));
						prop.world.setScrollX((int)(prop.playerPosX+prop.joystick.screenSpX));
						prop.world.setScrollY((int)(prop.playerPosY+prop.joystick.screenSpY));
						
					}
					for(Skeleton skeleton:skeletons){
						skeleton.update();
					}
				
					time5+=time3;
					if(time5>1000){
						time5=0;
						files.writeFile(prop,getExternalFilesDir("").toString(),"f.txt",new String[]{});
						coord="X:"+(int)prop.playerPosX+"\n"+"Y:"+(int)prop.playerPosY+"\n"+String.valueOf(time3)+"\n"+fps+"\n";
						fps=0;
						Log.d("seva",String.valueOf(
						Runtime.getRuntime().totalMemory()/1048576)+"/"+
						String.valueOf(Runtime.getRuntime().freeMemory()/1048576));
						runOnUiThread(run3);
					
						}}
	
		}
		};
		
		
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			if(!run1_c){
				main.addView(playerAndUi,params2);
			run1_c=true;
			}
				prop.playerAndUi.setVisibility(View.VISIBLE);
				prop.menuLayout.setVisibility(View.GONE);
		}
		};
		
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
				coords.setText(coord);
		}

	};
	
	
	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			context=getApplicationContext();
			main=findViewById(R.id.main);
			menu=new RelativeLayout(context);
			
				playerAndUi=new RelativeLayout(context);
				pause_lay=new RelativeLayout(context);
			
			files.readFile(read,getExternalFilesDir("")+"/f.txt");
		
				menu.setBackgroundResource(R.drawable.background1);
			    menu.setVisibility(View.INVISIBLE);
				pause_lay.setVisibility(View.GONE);
				pause_lay.setBackgroundColor(Color.argb(63,0,0,0));
				pause_lay.setTranslationZ(11111);
			BitmapFactory.Options op=new BitmapFactory.Options();
			op.inScaled=false;
			op.inPreferQualityOverSpeed=true;
			for(int i=0; i<20;i++){
				for(int j=0;j<10;j++){
					imm[i][j]=new ImageView(context);
					background_posX[i]=((i-2)*256);
					background_posY[j]=((j-1)*256);
					imm[i][j].setImageResource(R.drawable.grass2);
					imm[i][j].setTranslationX(background_posX[i]);
					imm[i][j].setTranslationY(background_posY[j]);
					imm[i][j].setScaleType(ScaleType.FIT_XY);
					imm[i][j].setTranslationZ(-1111);
				}
			}
			thread =new Thread(run);
				Typeface face=Typeface.createFromAsset(getAssets(), "fonts/blazma_regular.ttf"); 
				stage=new Game_stage(Game_stage.MENU);
				DisplayMetrics dm=new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getRealMetrics(dm);
				prop=new main_properties(main,menu,playerAndUi,context,activity,dm.widthPixels,dm.heightPixels,op,face,thread,stage,pause_lay,run,skeletons);
				new Words(prop);

				Item.loadItems(prop);
				new Shop(prop);
				new Inventory(prop);
				new Menu(prop);
				abc();
				new Player(prop,Player.type.MENU);
				
				prop.loadBar.addPoint();
				coords=new TextView(context);
				prop.loadBar.addPoint();
				coords.setTranslationY(750);
				prop.loadBar.addPoint();
				coords.setTranslationZ(1023);
				prop.loadBar.addPoint();
				coords.setTextColor(Color.RED);
				prop.loadBar.addPoint();
				coords.setTypeface(face); 
				prop.loadBar.addPoint();
				playerAndUi.addView(coords);
				prop.loadBar.addPoint();
				prop.setMoney(prop.money);
				prop.loadBar.addPoint();
				menu.setVisibility(View.VISIBLE);
				prop.loadBar.addPoint();
				playerAndUi.setVisibility(View.GONE);
				prop.loadBar.addPoint();
				runOnUiThread(run5);
				prop.loadBar.addPoint();
				world=new RelativeLayout(context);
				prop.loadBar.addPoint();
				world.setLayoutParams(params2);
				prop.loadBar.addPoint();
			    world.setTranslationZ(-0);
				prop.loadBar.addPoint();
				prop.setWorld(world);
				prop.loadBar.addPoint();
				playerAndUi.addView(world);
				prop.loadBar.addPoint();
			
				grass_layout=new RelativeLayout(prop.context);
				prop.loadBar.addPoint();
				grass_layout.setLayoutParams(params2);
				prop.loadBar.addPoint();
			    grass_layout.setTranslationZ(-1);
				prop.loadBar.addPoint();
				for(int i=0;i<20;i++){
					for(int j=0;j<10;j++){
						grass_layout.addView(imm[i][j],params1);
					}
				}
				prop.loadBar.addPoint();
				
				playerAndUi.addView(grass_layout);
				
				prop.loadBar.addPoint();
				
				World.loadTrees(prop);

				prop.loadBar.addPoint();
				new Player(prop,Player.type.WORLD);
				prop.loadBar.addPoint();
				new Player_health(prop);
				prop.loadBar.addPoint();
				new World().start(prop);
				new Joystick(prop);
				prop.loadBar.addPoint();
				new Skeleton(prop,0,0);
				prop.loadBar.addPoint();
				new Btn_attack(prop);
				prop.loadBar.addPoint();
				new Btn_shield(prop);
				prop.loadBar.addPoint();
				new Btn_roll(prop);
				prop.loadBar.addPoint();
				new Btn_continue(prop);
				prop.loadBar.addPoint();
				new Btn_exit_menu(prop);
				prop.loadBar.addPoint();
				new Btn_exit_game(prop);
				prop.loadBar.addPoint();
				
				
				prop.loadBar.addPoint();
				
				prop.loadBar.addPoint();
				playerAndUi.addView(pause_lay,params2);
				prop.loadBar.addPoint();
				prop.stage.world_load_complete=true;
}
		
	};
	
	
	
	Runnable run5=new Runnable(){

		@Override
		public void run(){
			main.addView(menu,params2);
			
		}
	};
	
	

	@Override
	public void onBackPressed()
	{
		if(stage.getStage()==Game_stage.MENU){
			if(prop.shop.isOpen){
				prop.shop.closeShop();
				return;
			}
			if(prop.menu.settingsIsOpen){
				prop.menu.settings.closeSettings();
				return;
			}
			if(prop.menu.bonuses.isOpen){
				prop.menu.bonuses.closeBonuses();
				return;
			}
			if(prop.inv.isOpen){

				prop.inv.closeInventory();
				return;
			}
			
		last_back_pressed_time2=System.currentTimeMillis();
		if(last_back_pressed_time2-last_back_pressed_time1<2000){
			
			finish();
			
		}else{
			Toast.makeText(context,"еще раз..",Toast.LENGTH_SHORT).show();
		}
		last_back_pressed_time1=System.currentTimeMillis();
		
		}
		
		else if(stage.getStage()==Game_stage.WORLD){
			if(prop.stage.getStage_in_world()==Game_stage.NOT_PAUSE){
				if(prop.inv.isOpen){
					prop.inv.closeInventory();
					return;
				}
				if(prop.shop.isOpen){
					prop.shop.closeShop();
					return;
				}
				pause_lay.setVisibility(View.VISIBLE);
			prop.joystick.lay.setVisibility(View.GONE);
			prop.stage.setStage_in_world(Game_stage.PAUSE);
			}
			else {
				pause_lay.setVisibility(View.GONE);
				prop.joystick.lay.setVisibility(View.VISIBLE);
				prop.stage.setStage_in_world(Game_stage.NOT_PAUSE);
				
				}
		}
		
	}
			
			
		void abc(){
			for(String s1:read){
				String[] s2=s1.split(" ");
				
				if(s2[0].contentEquals("player_position_x:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.playerPosX=(Float.parseFloat(s2[1]));

				}
				
				if(s2[0].contentEquals("player_position_y:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.playerPosY=(Float.parseFloat(s2[1]));

				}
				if(s2[0].contentEquals("money:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.money.setMoneyCount(Float.parseFloat(s2[1]));

				}
				
				if(s2[0].contentEquals("musicVolume:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.menu.settings.musicVolume.volume=(Float.parseFloat(s2[1]));
					prop.menu.settings.musicVolume.updatePoint();
				}
					
				if(s2[0].contentEquals("inven:")){
					if(s2.length==1)continue;
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					for(String s3:s2[1].split("/")){
						if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
						String[] s4=s3.split(":");
						if(s4[0]==null||s4[0].contentEquals("NaN")||s4[0].contentEquals(""))continue;
						prop.inv.addItem("inventory",prop.findItem(Integer.parseInt(s4[0])),0);
					}
				}
	
					if(s2[0].contentEquals("bonuses:")){
						if(s2.length==1)continue;	
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					for(String s3:s2[1].split("/")){
							if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
							prop.menu.bonuses.update(Integer.parseInt(s3));
							}
				}
					if(s2[0].contentEquals("exp:")){
						prop.menu.playerLevel.points=Float.parseFloat(s2[1]);
						prop.menu.playerLevel.pointsOnThisLevel=prop.menu.playerLevel.points;
						prop.menu.playerLevel.update();
						}
					if(s2[0].contentEquals("armor:")){
						if(s2.length==1)continue;
						
							if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
							for(String s3:s2[1].split("/")){
								if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
								String[] s4=s3.split("_");
								String[] s5=s4[1].split(":");
								if(s5[0]==null)continue;
								
								prop.inv.addItem("armF",prop.findItem(Integer.parseInt(s5[0])),Integer.parseInt(s4[0]));
							}
						}
						
					
			
				}
				
		}

		@Override
		protected void onResume()
		{
			
			super.onResume();
				if(isPause){
					isPause=false;
					if(prop.music!=null)
				prop.music.start();
				}
		
		}
	
    
}
