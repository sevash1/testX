package sevash.testx;
import android.widget.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.widget.ImageView.*;
import java.util.*;
import android.content.*;

public class Menu
{
	main_properties prop;
	public boolean settingsIsOpen=false;
	public Settings settings;
	LayoutParams p1=new LayoutParams(500,500);
	Avatar avatar;
	PlayerLevel playerLevel;
	Bonuses bonuses;
	
	Menu(main_properties prop){
		prop.setMenu(this);
		this.prop=prop;
		LoadMenu(prop);
		Intent m;
	}
	
	public void LoadMenu(main_properties prop){
		new LoadBar(prop);
		avatar=new Avatar();
		playerLevel=new PlayerLevel();
		prop.money=new Money(prop,Money.Type.MENU);
		prop.loadBar.addPoint();
		Music.load(prop);
		new Btn_play(prop);
		settings =new Settings();
		new Bonuses();
	
	}
	
	class Avatar{
		ImageView icon;
		Random r=new Random();
	
		Avatar(){
			Item.loadAvatars(prop);
			icon=((Item)prop.icons.get(r.nextInt(prop.icons.size()-1))).picture;
			icon.setX(prop.screenW/30);
			icon.setY(prop.screenH*0.025f);
			prop.onUi(r1);
			prop.avatar=this;
			
				}
		
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(icon,200,200);	
			}
	};
	}
	
	class PlayerLevel{
		double points=0;
		ImageView level_bar;
		ImageView level_bar_backgound;
		TextView lvl;
		TextView pointsOfThisLevel;
		
		PlayerLevel(){
			level_bar_backgound=new ImageView(prop.context);
			level_bar_backgound.setImageResource(R.drawable.black_bar);
			level_bar_backgound.setX(avatar.icon.getX()+avatar.icon.getLayoutParams().width+10);
			level_bar_backgound.setY(prop.screenH/24);
			level_bar_backgound.setScaleType(ScaleType.FIT_XY);
			
			level_bar=new ImageView(prop.context);
			level_bar.setImageResource(R.drawable.red_bar);
			level_bar.setX(avatar.icon.getX()+avatar.icon.getLayoutParams().width+10);
			level_bar.setY(prop.screenH/24);
			level_bar.setScaleType(ScaleType.FIT_XY);
			
			lvl=new TextView(prop.context);
			lvl.setTypeface(prop.ttf);
			lvl.setTextSize(6);
			lvl.setX(level_bar.getX()+40);
			lvl.setY(level_bar.getY()-15);
			lvl.setText(String.valueOf((int)(points/100))+"  lvl.");
			
			pointsOfThisLevel=new TextView(prop.context);
			pointsOfThisLevel.setTypeface(prop.ttf);
			pointsOfThisLevel.setTextSize(6);
		    pointsOfThisLevel.setX(level_bar.getX()+600+15);
			pointsOfThisLevel.setY(level_bar.getY());
			pointsOfThisLevel.setText(String.valueOf((int)(points%100))+" / 100");
			
			
			prop.onUi(r1);
			}
			
			public void addExp(int count){
				points+=count;
				prop.onUi(r2);
			}
			
			public void update(){
				prop.onUi(r2);
			}
			
		Runnable r2=new Runnable(){

			@Override
			public void run()
			{
			    level_bar.setLayoutParams(new LayoutParams((int)(points%100)*6,10));
				lvl.setText(String.valueOf((int)(points/100))+"  lvl.");
				pointsOfThisLevel.setText(String.valueOf((int)(points%100))+" / 100");
				
			}
		};
			
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(level_bar_backgound,600,10);
				prop.menuLayout.addView(level_bar,500,10);
				prop.menuLayout.addView(lvl);
				prop.menuLayout.addView(pointsOfThisLevel);
				
				
			}
		};
		
	}
	
	
	class Bonuses{
		ImageView intermediary;
		RelativeLayout bonuses_layout;
		
		Bonuses(){
			bonuses=this;
			intermediary=new ImageView(prop.context);
			intermediary.setImageResource(R.drawable.bonus42);
			intermediary.setScaleType(ScaleType.FIT_XY);
			intermediary.setX(settings.set.getX()-160);
			intermediary.setY(settings.set.getY());
			intermediary.setOnTouchListener(t1);
			
			bonuses_layout=new RelativeLayout(prop.context);
			bonuses_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		    bonuses_layout.setBackgroundColor(Color.argb(0xd4,0,0,0));
			bonuses_layout.setVisibility(View.INVISIBLE);
			
			prop.onUi(r1);
			}
			
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(intermediary,settings.set.getLayoutParams());
				prop.menuLayout.addView(bonuses_layout);

			}
		};
		
		OnTouchListener t1=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				if(p2.getAction()==MotionEvent.ACTION_UP)
				{
					if(bonuses_layout.getVisibility()==View.INVISIBLE){
						intermediary.setZ(11);
						bonuses_layout.setZ(10);
						bonuses_layout.setVisibility(View.VISIBLE);
						
					}
					else{
						intermediary.setZ(0);
						bonuses_layout.setZ(0);
						bonuses_layout.setVisibility(View.INVISIBLE);
						
					}
				}
				return true;
			}


		};
		
	}
	
	
	class Settings{
		
		ImageView set;
		LayoutParams p1=new LayoutParams(100,100);
		RelativeLayout settingsLayout;
		public MusicVolume musicVolume;
		
		
		Settings(){
			set=new ImageView(prop.context);
			set.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.settings_button,prop.options)));
			set.setTranslationX(prop.screenW-250);
			set.setTranslationY(60);
			set.setTranslationZ(2);
			set.setOnTouchListener(t1);
			settingsLayout=new RelativeLayout(prop.context);
			settingsLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			settingsLayout.setBackgroundColor(Color.argb(192,0,0,0));
			settingsLayout.setOnTouchListener(t2);
			settingsLayout.setVisibility(View.INVISIBLE);
			settingsLayout.setTranslationZ(1);
			prop.activity.runOnUiThread(r1);
			musicVolume= new MusicVolume();
		}
		
		public void closeSettings(){
			settingsLayout.setVisibility(View.INVISIBLE);
			settingsIsOpen=false;
		}
		
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				try{
				prop.menuLayout.addView(set,p1);
				prop.menuLayout.addView(settingsLayout);
				}catch(Exception e){
					files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
					
				}
			}

			
		};
		OnTouchListener t1=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				if(p2.getAction()==MotionEvent.ACTION_UP)
				{
					if(settingsIsOpen){
						closeSettings();
						return true;
					}
					settingsLayout.setVisibility(View.VISIBLE);
					settingsIsOpen=true;
					}
				return true;
			}

			
		};
		
	OnTouchListener t2=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			
			return true;
		}

	};
	
	class MusicVolume{
		TextView text;
		ImageView bar;
		ImageView point;
		RelativeLayout barsLayout;
			LayoutParams params1=new LayoutParams(1200,10);
			float volume=0;
		
		
		MusicVolume(){
			text=new TextView(prop.context);
			text.setGravity(Gravity.RIGHT);
			text.setTextSize(12);
			text.setTranslationX(140);
			text.setTranslationY(prop.screenH/9);
			text.setLayoutParams(new LayoutParams(300,50));
			text.setTextColor(Color.BLUE);
			text.setTypeface(prop.ttf);
		text.setText("Музыка");
		
		bar=new ImageView(prop.context);
			bar.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options)));
			bar.setScaleType(ScaleType.FIT_XY);
			bar.setLayoutParams(new LayoutParams(1200,10));
			bar.setTranslationX(525);
			bar.setTranslationY(prop.screenH/9);
			
			barsLayout=new RelativeLayout(prop.context);
			barsLayout.setLayoutParams(new LayoutParams(1200,70));
			barsLayout.setTranslationX(500);
			barsLayout.setTranslationY(prop.screenH/9-30);
			barsLayout.setOnTouchListener(t3);
		
			point=new ImageView(prop.context);
			point.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.black_bar,prop.options)));
			point.setScaleType(ScaleType.FIT_XY);
			point.setLayoutParams(new LayoutParams(70,10));
			point.setRotation(90);
			point.setTranslationX(900);
			point.setTranslationY(prop.screenH/9);
			
		prop.activity.runOnUiThread(r3);
		}
		
		public void updatePoint(){
			point.setTranslationX(500+1200*volume);
			if(prop.music!=null)
			prop.music.setVolume(volume,volume);
			
		}
			Runnable r4=new Runnable(){

				@Override
				public void run()
				{
					updatePoint();
				}
			};
		
			OnTouchListener t3=new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					if(p2.getAction()==MotionEvent.ACTION_MOVE){
						if(barsLayout.getTranslationX()+p2.getX()>barsLayout.getTranslationX()
						   &&barsLayout.getTranslationX()+p2.getX()<barsLayout.getTranslationX()+1200){
						//point.setTranslationX(barsLayout.getTranslationX()+p2.getX());
						volume=p2.getX()/1200;
						prop.activity.runOnUiThread(r4);
						}
					}
					return true;
				}

			
		};
		
			Runnable r3=new Runnable(){
				@Override
				public void run()
				{
					try{
					settingsLayout.addView(text);
					settingsLayout.addView(bar);
					settingsLayout.addView(point);
					settingsLayout.addView(barsLayout);
					}catch(Exception e){
						files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				
					}
				}

			};
			
	}
	
	}
}
