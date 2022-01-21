package sevash.testx;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import android.view.*;

public class Player implements Entity
{
	@Override
	public int getEntityType()
	{
		return PLAYER;
	}
	
	public static enum type{
		MENU,
		WORLD
	}
	
	public static  enum active_anim{
		IDLE,
		RUN,
		SHIELD,
		DEATH,
		ROLL,
		JUMP,
		ATTACK
		}
	
		public float max_health=100;
		public float health=100;
	main_properties prop;
	RelativeLayout menu;
	RelativeLayout world;
	public ImageView player;
	Context context;
	List anim_idle=new ArrayList<Bitmap>();
	List anim_run=new ArrayList<Bitmap>();
	List anim_shield=new ArrayList<Bitmap>();
	List anim_death=new ArrayList<Bitmap>();
	List anim_roll=new ArrayList<Bitmap>();
	List anim_jump=new ArrayList<Bitmap>();
	List anim_attack=new ArrayList<Bitmap>();
	List anim;
	
	public int anim_stage=0;
	public active_anim a_anim;
	float playerPosX=0;
	float playerPosY=0;
	float screenW=0;
	float screenH=0;
	Thread thread1;
	public LayoutParams params1=new LayoutParams(256,256);
	
	Player(main_properties prop, Player.type type){
		
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		this.prop=prop;
		player=new ImageView(prop.context);
		player.setPivotX(128);
		player.setTranslationZ(1);
		load_textures_idle();
		player.setImageBitmap((Bitmap)anim_idle.get(0));
		player.setLayoutParams(params1);
		player.setScaleType(ScaleType.FIT_XY);
		a_anim=active_anim.IDLE;
		anim=anim_idle;
		if(type==Player.type.MENU){
			player.setTranslationX((screenW/2)-64*2);
			player.setTranslationY((screenH/2)-64*5);
		prop.activity.runOnUiThread(run1);
		thread1=new Thread(run2);
		thread1.setDaemon(true);
		thread1.start();
		}
		
		else if(type==Player.type.WORLD){
			this.playerPosX=prop.playerPosX;
			this.playerPosY=prop.playerPosY;
			load_textures();
			player.setTranslationX((screenW/2)-64*2);
			player.setTranslationY((screenH/2)-64*2);
			prop.playerAndUi.addView(player);
			thread1=new Thread(run3);
			thread1.setDaemon(true);
			thread1.start();
			prop.setPlayer(this);
		}
		
	}
	
	
	void load_textures_idle(){
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),0,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),64,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),128,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),192,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),256,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),320,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),384,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),448,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),512,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),576,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),640,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),704,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),768,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),832,0,64,64));
		anim_idle.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_idle,prop.options),896,0,64,64));
		
	}
	void load_textures(){
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),0+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*2+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*3+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*4+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*5+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*6+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*7+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*8+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*9+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*10+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*11+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*12+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*13+32,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*14+32+6,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*15+32+6*2,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*16+32+6*3,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*17+40+6*3,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*18+40+6*3,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*19+40+6*3,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*20+40+6*3,0,64,64));
		anim_attack.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_attack,prop.options),144*21+40+6*3,0,64,64));
	
			anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),55-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),236-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),441-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),602-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),785-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),984-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),1180-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),1375-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),1564-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),1751-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),1935-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),2116-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),2296-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),2476-20,0,64,64));
		anim_roll.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_roll,prop.options),2656-20,0,64,64));
		
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),0,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*2,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*3,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*4,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*5,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*6,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*7,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*8,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*9,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*10,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*11,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*12,0,150,64));
		anim_jump.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_jump,prop.options),150*13,0,150,64));
		
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),0,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*2,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*3,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*4,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*5,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*6,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*7,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*8,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*9,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*10,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*11,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*12,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*13,0,96,64));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_death,prop.options),96*14,0,96,64));
		
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),0+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96*2+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96*3+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96*4+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96*5+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96*6+16,0,64,64));
		anim_run.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_run,prop.options),96*7+16,0,64,64));
		
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),0+16,0,64,64));
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),96+16,0,64,64));
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),96*2+16,0,64,64));
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),96*3+16,0,64,64));
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),96*4+16,0,64,64));
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),96*5+16,0,64,64));
		anim_shield.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.player_shield,prop.options),96*6+16,0,64,64));
		
		}
		
		
	
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			
			prop.menu.addView(player);
		}
		
		};
	int s=0;
	Runnable run2=new Runnable(){

		@Override
		public void run()
		{
			prop.menu_music.setVolume(0.5f,0.5f);

				prop.menu_music.seekTo(0);

			prop.menu_music.start();
			
			while(true){
				
				try{
					Thread.sleep(250);
					if(prop.stage.getStage()==Game_stage.MENU){
					player.setImageBitmap((Bitmap)anim_idle.get(s));
					s++;
					if(s>14) s=0;
					
						
					}
				}catch(Exception e){
					files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
					
				}
			}
		}
			
		};
		
		public boolean attacked=false;
	void attack(){
		try{
			for(Skeleton skel:prop.skeletons){
				if(player.getRotationY()==0)
				if(Math.sqrt(Math.pow((player.getTranslationX()-(skel.iv.getTranslationX()-prop.world.getScrollX())),2)
						  +Math.pow((player.getTranslationY()-(skel.iv.getTranslationY()-prop.world.getScrollY())),2))<200){
			attacked=true;
		}
			
		else attacked=false;
		}
		}catch(Exception e){
			files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

		}
	}
		
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			
			while(true){

				try{
					Thread.sleep(125);
					if(prop.stage.getStage()==Game_stage.WORLD){
						
						updateAnim();
						if(anim_stage>anim.size()-2) anim_stage=0;
						prop.joystick.updateRotate();
						prop.activity.runOnUiThread(run4);
						
						if(a_anim==active_anim.ATTACK){
							attack();
							if(attacked&&(anim_stage==10||anim_stage==5||anim_stage==17))
								
								for(Skeleton skel:prop.skeletons)
								if(skel.isLife){
					
								skel.sendDamage(1);
								}
								
							
							if(anim_stage>12&&anim_stage<16){
								if(player.getRotationY()==180)
									prop.joystick.attackX=-0.4f;
								else prop.joystick.attackX=0.4f;
							}else prop.joystick.attackX=0f;
							}
							
						if(prop.player.a_anim==Player.active_anim.SHIELD){
							if(anim_stage==anim.size()-2)anim_stage-=2;
						}
						if(prop.player.a_anim==Player.active_anim.ROLL){
							
							if(anim_stage>anim.size()-3){
								
								prop.roll.isActive=false;
								prop.player.a_anim=Player.active_anim.IDLE;
						
						}
						}
						anim_stage++;
					}
				}catch(Exception e){
					files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
					
				}
				
			}
		}

	};
	
	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			try{
				
			player.setImageBitmap((Bitmap)anim.get(anim_stage));
			}catch(Exception e){
				files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

			}
		}

		
	};
	
	
	
	int live_skels=0;
	
	
	void updateAnim(){
		if(a_anim==active_anim.IDLE) anim=anim_idle;
		else if(a_anim==active_anim.ATTACK) anim=anim_attack;
		else if(a_anim==active_anim.DEATH) anim=anim_death;
		else if(a_anim==active_anim.JUMP) anim=anim_jump;
		else if(a_anim==active_anim.ROLL) anim=anim_roll;
		else if(a_anim==active_anim.RUN) anim=anim_run;
		else if(a_anim==active_anim.SHIELD) anim=anim_shield;
	}
}
