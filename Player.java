package sevash.livingSword;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import android.view.*;
import android.media.*;

public class Player 
{
	
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
		
		float attackDamagePercent=0f;
		float attackDamageFixed=0f;
		float bonusGoldPercent=0f;
		float bonusGoldFixed=0f;
	
		public float max_health=20;
		public float health=20;
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
		thread1.start();
		}
		
		else if(type==Player.type.WORLD){
			load_textures();
			player.setTranslationX((screenW/2)-64*2);
			player.setTranslationY((screenH/2)-64*2);
			player.setZ(1);
			prop.playerAndUi.addView(player);
			thread1=new Thread(run3);
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
			
			prop.menuLayout.addView(player);
		}
		
		};
	int s=0;
	int o=0;
	Random r= new Random();
	
	void playM(){
		if(Game_stage.EXIT==prop.stage.getStage()) return;
		
		prop.music=MediaPlayer.create(prop.context,prop.musicList.get(r.nextInt(47)));
		if(prop.music!=null)
			if(prop.menu!=null)
			prop.music.setVolume(prop.menu.settings.musicVolume.volume
			                    ,prop.menu.settings.musicVolume.volume);
		    else
				prop.music.setVolume(0.5f,0.5f);
		if(prop.music!=null)if(prop.music.isPlaying())return;
		prop.music.start();
		int length=prop.music.getDuration();
		if(length<16000){
			o=4;
			}
		else if(length<32000){
			o=2;
		}
			prop.music.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
					@Override
					public void onCompletion(MediaPlayer mp){
						if(Game_stage.EXIT==prop.stage.getStage()) return;	
						o--;
						if(o>0)
						    prop.music.start();
						else
							playM();
						}
						});
	}
	
	long time1=0,time2=0,deltaTime=0,
    time3=0;
	
	Runnable run2=new Runnable(){
		
		@Override
		public void run()
		{
			
			while(true){
				if(Game_stage.EXIT==prop.stage.getStage())
					return;
				
				try{
					Thread.sleep(250);
					}catch(Exception e){continue;}
					if(prop.stage.getStage()==Game_stage.MENU){
						prop.activity.runOnUiThread(run5);
					s++;
					if(s>14) s=0;
					
						
					}
				
			}
		}
			
		};
		
	Runnable run5=new Runnable(){

		@Override
		public void run()
		{
			player.setImageBitmap((Bitmap)anim_idle.get(s));
			
		}
		};
		
		
		Skeleton skel1;
	void attack(){
		prop.sounds.sp.play(prop.sounds.s2,1f,1f,1,0,1f);
			for(int i=0;i<prop.skeletons.size();i++){
				skel1=(Skeleton)prop.skeletons.get(i);
				if(prop.distanceToPlayer(skel1.posX,skel1.posY)<200)
				{skel1.sendDamage(1+attackDamageFixed);		 
	     	    sendDamage(1);
				}
		    }
	}
	
	void sendDamage(float count){
		if(health-count<0)health=0;
		else
			health-=count;
		prop.healthBar.update();
	}
		
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			while(true){
				if(Game_stage.EXIT==prop.stage.getStage()) 
					return;
				
				
				try{
					time1=System.currentTimeMillis();
					Thread.sleep(8);
					time2=System.currentTimeMillis();
					deltaTime=time2-time1;
					time3+=deltaTime;
						for(Skeleton skel2:prop.skeletons)
						skel2.update();
					}
				catch(Exception e) {e.printStackTrace();}
				if(time3>120){
					time3=0;
					if(prop.stage.getStage()==Game_stage.WORLD){
					
						updateAnim();
						if(anim_stage>anim.size()-2) anim_stage=0;
						prop.joystick.updateRotate();
						prop.activity.runOnUiThread(run4);
						
						for(Skeleton skel3:prop.forAdd)
							prop.skeletons.add(skel3);
						prop.forAdd.clear();
						for(Object skel4:prop.forRemove)
							prop.skeletons.remove(skel4);
						prop.forRemove.clear();
						
						if(a_anim==active_anim.ATTACK){
							if(anim_stage==10||anim_stage==5||anim_stage==17)
								attack();
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
								if(prop.joystick.joystick_pressed)
									prop.player.a_anim=Player.active_anim.RUN;
								else
								    prop.player.a_anim=Player.active_anim.IDLE;
						
						}
						}
						anim_stage++;
					}
				}
			}
		}

	};
	
	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			player.setImageBitmap((Bitmap)anim.get(anim_stage));
			
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
