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
	TextView coords;
	String coord="";
	boolean run1_c=false;
	long time1=0,time2=0;
	long deltaTime=0, time5=0;
	Long last_back_pressed_time1=0l;
	Long last_back_pressed_time2=0l;
	float last_playerPosX=0;
	float last_playerPosY=0;
	int fps=0;
	Thread thread;
	Context context;
	boolean isPause=false;
	RelativeLayout pause_lay;
	Thread thread9;
	Activity activity;
	
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
				|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
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
		prop.stage.setStage(Game_stage.EXIT);
		if(prop.music!=null)
		prop.music.stop();
		files.writeFile(prop);
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
		files.writeFile(prop);
		
		super.onPause();
	}
				
	Runnable run = new Runnable(){
		@Override
		public void run()
		{
			prop.runs.add(r1);
			prop.runs.add(prop.player.run2);
			prop.runs.add(prop.player.run3);
			
			while(prop.stage.getStage()!=Game_stage.EXIT){
				try{
					fps++;
					time1=System.currentTimeMillis();
					Thread.sleep(8);
					time2=System.currentTimeMillis();
					deltaTime=time2-time1;
					prop.deltaTime=deltaTime;
					
			        for(Runnable run:prop.runs)
						run.run();		
					
					for(Runnable run:prop.forAddRuns){
						prop.runs.add(run);
						}
					prop.forAddRuns.clear();

					for(Runnable run:prop.forRemoveRuns){
						prop.runs.remove(run);
						}
					prop.forRemoveRuns.clear();
					
						
					for(Skeleton skel3:prop.forAdd){
						prop.skeletons.add(skel3);
					}
					prop.forAdd.clear();

					for(Skeleton skel4:prop.forRemove){
						prop.skeletons.remove(skel4);
					}
					prop.forRemove.clear();
					
						
				}catch(Exception e){e.printStackTrace();}
			}
		}
	};
		
	Runnable r1 = new Runnable(){
		@Override
		public void run()
		{
			time5+=prop.deltaTime;
			
			if(prop.stage.getStage()==Game_stage.WORLD){
				
			if(prop.player.a_anim==Player.active_anim.ATTACK){
				if(!prop.joystick.joystick_pressed
				   &&!Float.isNaN(prop.joystick.attackX)){
					prop.playerPosX=prop.playerPosX+prop.joystick.attackX*deltaTime;
				}
				else if(prop.attack.pressed
						&&!Float.isNaN(prop.joystick.attackX)){
					prop.playerPosX=prop.playerPosX+prop.joystick.attackX*deltaTime;
				}
			}

			else if(prop.player.a_anim==Player.active_anim.SHIELD){

			}

			else if(prop.player.a_anim==Player.active_anim.IDLE){
				if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
				   ||(Float.isNaN(prop.joystick.ratioX)
				   ||Float.isNaN(prop.joystick.ratioY)
				   ||Float.isNaN(prop.playerPosX)
				   ||Float.isNaN(prop.playerPosY))){}
			}
			else if(prop.player.a_anim==Player.active_anim.ROLL){
				if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
				   ||(Float.isNaN(prop.joystick.ratioX)
				   ||Float.isNaN(prop.joystick.ratioY)
				   ||Float.isNaN(prop.playerPosX)
				   ||Float.isNaN(prop.playerPosY))){}else{
					prop.playerPosX=prop.playerPosX+prop.joystick.ratioX*deltaTime;
					prop.playerPosY=prop.playerPosY+prop.joystick.ratioY*deltaTime;

				}}

			else if(prop.player.a_anim==Player.active_anim.RUN){
				if(prop.joystick.ratioX==0&&prop.joystick.ratioY==0
				   ||(Float.isNaN(prop.joystick.ratioX)
				   ||Float.isNaN(prop.joystick.ratioY)
				   ||Float.isNaN(prop.playerPosX)
				   ||Float.isNaN(prop.playerPosY))){}else{
					prop.playerPosX=prop.playerPosX+prop.joystick.ratioX*deltaTime;
					prop.playerPosY=prop.playerPosY+prop.joystick.ratioY*deltaTime;	
				}}

			if(last_playerPosX==prop.playerPosX&&last_playerPosY==prop.playerPosY){
				prop.playerMove=false;
			}
			else {
				prop.playerMove=true;
				last_playerPosX=prop.playerPosX;
				last_playerPosY=prop.playerPosY;
				grass_layout.setScrollX((int)(prop.playerPosX-((int)(prop.playerPosX/256))*256+256*6));
				grass_layout.setScrollY((int)(prop.playerPosY-((int)(prop.playerPosY/256))*256+256*3));
				prop.world.setScrollX((int)(prop.playerPosX));
				prop.world.setScrollY((int)(prop.playerPosY));

			}

			if(time5>1000){
				time5=0;
				files.writeFile(prop);
				coord="X:"+(int)prop.playerPosX+"\n"+"Y:"+(int)prop.playerPosY+"\n"+String.valueOf(deltaTime)+"\n"+fps+"\n"+String.valueOf(prop.skeletons.size()+"\n"+(Math.sqrt(Math.pow(prop.world.getScrollX()+((Skeleton)prop.skeletons.get(0)).posX,2))));
				fps=0;
				Log.d("seva",String.valueOf(
						  Runtime.getRuntime().totalMemory()/1048576)+"/"+
					  String.valueOf(Runtime.getRuntime().freeMemory()/1048576));
				runOnUiThread(run3);
				}
			}
		}
	};
		
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
				main.addView(playerAndUi,params2);	
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
			
			files.readFile(activity,read,getExternalFilesDir("")+"/f.txt");
		
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
				DisplayMetrics dm=new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getRealMetrics(dm);
				prop=new main_properties(main,menu,playerAndUi,context,activity,dm.widthPixels,dm.heightPixels,op,face,pause_lay);
			    prop.stage=new Game_stage(Game_stage.MENU);
			    new Words(prop);
				new Music(prop);
				Item.loadItems(prop);
				new Shop(prop,0);
				new Inventory(prop,0);
			    new Player(prop,Player.type.MENU);
			    new Player(prop,Player.type.WORLD);
			
				new Menu(prop);
				abc();
				prop.loadBar.addPoint();
				coords=new TextView(context);
				prop.loadBar.addPoint();
				coords.setTranslationY(750);
				prop.loadBar.addPoint();
				coords.setTranslationZ(1023);
				prop.loadBar.addPoint();
				coords.setTextColor(Color.YELLOW);
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
			prop.menuLoadComplete=true;
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
				prop.loadBar.addPoint();
				new Player_health(prop);
				prop.loadBar.addPoint();
				new World().start(prop);
				new Joystick(prop);
				prop.loadBar.addPoint();
			    new Mob(prop);
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
				prop.onUi(run1);
				thread.start();
				prop.loadBar.addPoint();
				prop.stage.world_load_complete=true;
}
		
	};
	
	
	
	Runnable run5=new Runnable(){

		@Override
		public void run(){
			try{
				Thread.sleep(1500);
			}catch(Exception e){}
			main.setBackgroundResource(R.drawable.g2);
			
			main.addView(menu,params2);
			
		}
	};
	
	

	@Override
	public void onBackPressed()
	{
		if(prop.stage.getStage()==Game_stage.MENU){
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
			if(prop.menu.playerDat.iconsInv.isOpen){
				prop.menu.playerDat.iconsInv.closeInventory();
			}
			
			if(prop.menu.avShop.isOpen){
				prop.menu.avShop.openOrClose();
				return;
			}
				
			if(prop.menu.playerDat.isOpen){
				prop.menu.playerDat.close();
				return;
			}
			
			
		last_back_pressed_time2=System.currentTimeMillis();
		if(last_back_pressed_time2-last_back_pressed_time1<2000){
			
			finish();
			
		}else{
			Toast.makeText(context,"?????? ??????..",Toast.LENGTH_SHORT).show();
		}
		last_back_pressed_time1=System.currentTimeMillis();
		
		}
		
		else if(prop.stage.getStage()==Game_stage.WORLD){
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
				
				else if(s2[0].contentEquals("player_position_y:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.playerPosY=(Float.parseFloat(s2[1]));

				}
				else if(s2[0].contentEquals("money:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.money.setMoneyCount(Float.parseFloat(s2[1]));
				}
				
				else if(s2[0].contentEquals("musicVolume:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.menu.settings.musicVolume.volume=(Float.parseFloat(s2[1]));
					prop.menu.settings.musicVolume.updatePoint();
				}
				
				else if(s2[0].contentEquals("effectsVolume:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.menu.settings.effectsVolume.volume=(Float.parseFloat(s2[1]));
					prop.menu.settings.effectsVolume.updatePoint();
				}
				
				else if(s2[0].contains("power:")){
					String a="\\{";
					String b="\\}";
					String s3[]=s2[0].split(a);
					String s4[]=s3[1].split(b);
					for(String s5:s4[0].split(",")){
						String[] s6=s5.split(":");
						if(s6[0].contentEquals("????????????????")) 
							prop.menu.power.health.upLevels(Integer.parseInt(s6[1]));
						else if(s6[0].contentEquals("????????")) 
							prop.menu.power.damage.upLevels(Integer.parseInt(s6[1]));
						else if(s6[0].contentEquals("??????????????????????????")) 
							prop.menu.power.attackSpeed.upLevels(Integer.parseInt(s6[1]));
						else if(s6[0].contentEquals("??????????????????????")) 
							prop.menu.power.regen.upLevels(Integer.parseInt(s6[1]));
					}
				}
				
				else if(s2[0].contentEquals("icon:")){
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.menu.avatar.icon.setImageResource(prop.findItem(Integer.parseInt(s2[1]),1).pictureInt);
					prop.menu.avatar.iconN=prop.findItem(Integer.parseInt(s2[1]),1).id;
				}
					
				else if(s2[0].contentEquals("icons:")){
					if(s2.length==1)continue;	
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					for(String s3:s2[1].split("/")){
						if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
						prop.iconsBuyed.add(prop.findItem(Integer.parseInt(s3),1));
						prop.menu.playerDat.iconsInv.addItem("",(Item)prop.iconsBuyed.get(prop.iconsBuyed.size()-1),1);
						
					}
				}
				
				else if(s2[0].contentEquals("inven:")){
					if(s2.length==1)continue;
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					for(String s3:s2[1].split("/")){
						if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
						String[] s4=s3.split(":");
						if(s4[0]==null||s4[0].contentEquals("NaN")||s4[0].contentEquals(""))continue;
						prop.inv.addItem("inventory",prop.findItem(Integer.parseInt(s4[0]),0),0);
					}
				}
	
				else if(s2[0].contentEquals("language:")){
					if(s2.length==1)continue;
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					prop.words.setLanguage(s2[1]);
				}
				
				else if(s2[0].contentEquals("bonuses:")){
						if(s2.length==1)continue;	
					if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
					for(String s3:s2[1].split("/")){
							if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
							prop.menu.bonuses.update(Integer.parseInt(s3));
							}
				}
				else if(s2[0].contentEquals("exp:")){
						prop.menu.playerLevel.points=Double.parseDouble(s2[1]);
						prop.menu.playerLevel.pointsOnThisLevel=prop.menu.playerLevel.points;
						prop.menu.playerLevel.update();
						}
				else if(s2[0].contentEquals("armor:")){
						if(s2.length==1)continue;
						
							if(s2[1].contentEquals("NaN") || s2[1].contentEquals("")||s2[1]==null) continue;
							for(String s3:s2[1].split("/")){
								if(s3.contentEquals("NaN") || s3.contentEquals("")||s3==null) continue;
								String[] s4=s3.split("_");
								String[] s5=s4[1].split(":");
								if(s5[0]==null)continue;
								
								prop.inv.addItem("armF",prop.findItem(Integer.parseInt(s5[0]),0),Integer.parseInt(s4[0]));
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
