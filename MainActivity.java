package sevash.testx;

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
import sevash.testx.files.*;
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
	Display disp;
	LayoutParams params1=new LayoutParams(256,256);
	LayoutParams params2=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	float player_posX=0;
	float player_posY=0;
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
	MediaPlayer music;
	boolean isPause=false;
	Game_stage stage;
	RelativeLayout pause_lay;
	Activity activity;
	Thread thread9;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		try{
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
		catch(Exception e){
			files.writeFile(prop,getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
		}
	}

	@Override
	protected void onDestroy()
	{
		stage.setStage(Game_stage.EXIT);
		prop.music.stop();
		
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		if(prop.music != null && prop.music.isPlaying()){
			isPause=true;
			prop.music.pause();
	
			}
		files.writeFile(prop,getExternalFilesDir("").toString(),"f.txt",new String[]{Float.toString(player_posX),Float.toString(player_posY),Integer.toString(prop.money.money_count)});
		
		super.onPause();
	}
				
	Runnable run = new Runnable(){
		@Override
		public void run()
		{
			try{
				
				runOnUiThread(run1);
				while(!run1_c){}
			prop.money.setType(Money.Type.WORLD);
			while(stage.getStage()==Game_stage.WORLD){
				if(Game_stage.EXIT==prop.stage.getStage()) thread.stop();
				
				try{
					time1=System.currentTimeMillis();
					Thread.sleep(8);
					time2=System.currentTimeMillis();
					time3=time2-time1;
					
					
					fps++;
					
					
					if(prop.player.a_anim==Player.active_anim.ATTACK){
					   if(!prop.joystick.joystick_pressed
					&&!Float.isNaN(prop.joystick.attackX)){
						player_posX=player_posX+prop.joystick.attackX*time3;
					}
					else if(prop.attack.pressed
					&&!Float.isNaN(prop.joystick.attackX)){
						player_posX=player_posX+prop.joystick.attackX*time3;
						}
						}
						
					if(prop.player.a_anim==Player.active_anim.SHIELD){
						
					}
					
					if(prop.player.a_anim==Player.active_anim.IDLE){
						if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
							||(Float.isNaN(prop.joystick.ratioX)
							||Float.isNaN(prop.joystick.ratioY)
							||Float.isNaN(player_posX)
							||Float.isNaN(player_posX))){}
							}
					if(prop.player.a_anim==Player.active_anim.ROLL){
						if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
						   ||(Float.isNaN(prop.joystick.ratioX)
						   ||Float.isNaN(prop.joystick.ratioY)
						   ||Float.isNaN(player_posX)
						   ||Float.isNaN(player_posX))){}else{
							player_posX=player_posX+prop.joystick.ratioX*time3;
							player_posY=player_posY+prop.joystick.ratioY*time3;

						}}
					
						if(prop.player.a_anim==Player.active_anim.RUN){
						if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
						   ||(Float.isNaN(prop.joystick.ratioX)
						   ||Float.isNaN(prop.joystick.ratioY)
						   ||Float.isNaN(player_posX)
						   ||Float.isNaN(player_posX))){}else{
						player_posX=player_posX+prop.joystick.ratioX*time3;
					player_posY=player_posY+prop.joystick.ratioY*time3;	
					}}
				
					if(last_playerPosX==player_posX&&last_playerPosY==player_posY){}
					else {
						last_playerPosX=player_posX;
						last_playerPosY=player_posY;
						grass_layout.setScrollX((int)player_posX-((int)(player_posX/256))*256+256*6);
						grass_layout.setScrollY((int)player_posY-((int)(player_posY/256))*256+256*3);
						prop.world.setScrollX((int)player_posX);
						prop.world.setScrollY((int)player_posY);
						
					}
					prop.playerPosX=player_posX;
					prop.playerPosY=player_posY;
					for(Skeleton skeleton:skeletons){
						skeleton.update(player_posX,player_posY);
					}
				
					time5+=time3;
					if(time5>1000){
						time5=0;
						files.writeFile(prop,getExternalFilesDir("").toString(),"f.txt",new String[]{Float.toString(player_posX),Float.toString(player_posY),Integer.toString(prop.money.money_count)});
						coord="X:"+(int)player_posX+"\n"+"Y:"+(int)player_posY+"\n"+String.valueOf(time3)+"\n"+fps+"\n";
						fps=0;
						Log.d("seva",String.valueOf(
						Runtime.getRuntime().totalMemory()/1048576));
						Log.d("seva",String.valueOf(
								  Runtime.getRuntime().freeMemory()/1048576));
						runOnUiThread(run3);
					
						}
						
				}catch(Exception e){
					files.writeFile(prop,getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				}}
		}catch(Exception e){
			files.writeFile(prop,getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
		}
		}
		};
		
		
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			try{
			if(!run1_c){
				main.addView(playerAndUi,params2);
			run1_c=true;
			}
				prop.playerAndUi.setVisibility(View.VISIBLE);
				prop.menuLayout.setVisibility(View.GONE);
				
			}catch(Exception e){
				files.writeFile(prop,getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));		
			}
		}
		};
		
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			try{
				
				coords.setText(coord);
			}
			catch(Exception e){
				files.writeFile(prop,getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			
		}

	};
	
	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			try{
			context=getApplicationContext();
			main=findViewById(R.id.main);
			menu=new RelativeLayout(context);
			
				playerAndUi=new RelativeLayout(context);
				pause_lay=new RelativeLayout(context);
			
			files.readFile(read,getExternalFilesDir("")+"/f.txt");
				
			menu.setBackgroundResource(R.drawable.menu_background);
				pause_lay.setVisibility(View.GONE);
				pause_lay.setBackgroundColor(Color.argb(63,0,0,0));
				pause_lay.setTranslationZ(1);
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
					imm[i][j].setTranslationZ(-1);
				}
			}
			thread =new Thread(run);
				Typeface face=Typeface.createFromAsset(getAssets(), "fonts/blazma_regular.ttf"); 
				disp=getWindowManager().getDefaultDisplay();
				stage=new Game_stage(Game_stage.MENU);
				music=MediaPlayer.create(context,R.raw.music01);
				prop=new main_properties(main,menu,playerAndUi,context,activity,disp.getWidth(),disp.getHeight(),op,face,thread,stage,pause_lay,run,music,player_posX,skeletons);
				
				new Menu(prop);
				prop.loadBar.addPoint();
				
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
				abc();
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
				new Btn_Inventory(prop);
				prop.loadBar.addPoint();
				new Shop(prop);
				prop.loadBar.addPoint();
				playerAndUi.addView(pause_lay,params2);
				prop.loadBar.addPoint();
				prop.stage.world_load_complete=true;
			}catch(Exception e){
				files.writeFile(prop,getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
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
		try{
		if(stage.getStage()==Game_stage.MENU){
			if(prop.shop.isOpen){
				prop.shop.closeShop();
				return;
			}
			if(prop.menu.settingsIsOpen){
				prop.menu.settings.closeSettings();
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
		}catch(Exception e){
		files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

	}
	}
			
			
		void abc(){
			try{
			for(String s1:read){
				String[] s2=s1.split(" ");
				
				if(s2[0].contentEquals("player_position_x:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")) continue;
					player_posX=(Float.parseFloat(s2[1]));

				}
				
				if(s2[0].contentEquals("player_position_y:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")) continue;
					player_posY=(Float.parseFloat(s2[1]));

				}
				if(s2[0].contentEquals("money:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")) continue;
					prop.money.setMoneyCount(Integer.parseInt(s2[1]));

				}
				
					if(s2[0].contentEquals("musicVolume:")){
						if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")) continue;
						prop.menu.settings.musicVolume.volume=(Float.parseFloat(s2[1]));
						prop.menu.settings.musicVolume.updatePoint();
					}
			
				}
				}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

			}
		}

		@Override
		protected void onResume()
		{
			try{
			
			super.onResume();
				if(isPause){
					isPause=false;
				prop.music.start();
				}
		}catch(Exception e){
			files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

		}
		}
	
    
}
