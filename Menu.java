package sevash.testx;
import android.widget.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.widget.ImageView.*;

public class Menu
{
	main_properties prop;
	public boolean settingsIsOpen=false;
	public Settings settings;
	
	
	Menu(main_properties prop){
		prop.setMenu(this);
		this.prop=prop;
		LoadMenu(prop);
	}
	
	public void LoadMenu(main_properties prop){
		new LoadBar(prop);
		prop.money=new Money(prop,Money.Type.MENU);
		prop.loadBar.addPoint();
		Music.load(prop);
		new Player(prop,Player.type.MENU);
		new Btn_play(prop);
		settings =new Settings();
	}
	
	
	
	class Settings{
		
		ImageView set;
		LayoutParams p1=new LayoutParams(100,100);
		RelativeLayout settingsLayout;
		public MusicVolume musicVolume;
		
		
		Settings(){
			set=new ImageView(prop.context);
		//	files.updateWorld(prop.activity.getExternalFilesDir("").toString(),"world/","0_0");
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
				prop.menuLayout.addView(set,p1);
				prop.menuLayout.addView(settingsLayout);
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
		}
		
			OnTouchListener t3=new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					if(p2.getAction()==MotionEvent.ACTION_MOVE){
						if(barsLayout.getTranslationX()+p2.getX()>barsLayout.getTranslationX()
						   &&barsLayout.getTranslationX()+p2.getX()<barsLayout.getTranslationX()+1200)
						point.setTranslationX(barsLayout.getTranslationX()+p2.getX());
						volume=p2.getX()/1200;
						prop.music.setVolume(volume,volume);
					}
					return true;
				}

			
		};
		
			Runnable r3=new Runnable(){
				@Override
				public void run()
				{
					prop.menu.settings.settingsLayout.addView(text);
					prop.menu.settings.settingsLayout.addView(bar);
					prop.menu.settings.settingsLayout.addView(point);
					prop.menu.settings.settingsLayout.addView(barsLayout);
				}

			};
			
	}
	
	}
}
