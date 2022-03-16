package sevash.livingSword;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Player_health 
{
	RelativeLayout menu;
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView progress;
	ImageView back;
	ImageView border;
	TextView tv;
	LayoutParams params1=new LayoutParams(100,12);
	LayoutParams params2=new LayoutParams(800,10);
	LayoutParams params3=new LayoutParams(800,10);
	LayoutParams params4=new LayoutParams(832,12);
	
	
	main_properties prop;
	

	Player_health(main_properties prop){
		
		this.prop=prop;
		this.menu=prop.menuLayout;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		
		progress=new ImageView(prop.context);
		progress.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options)));
		progress.setLayoutParams(params2);
	    progress.setScaleType(ScaleType.FIT_XY);
		progress.setTranslationX(65);
		progress.setTranslationY(10);
		progress.setTranslationZ(1023);
		progress.setPivotX(0);
		back=new ImageView(prop.context);
		back.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.black_bar,prop.options)));
		back.setLayoutParams(params3);
		back.setScaleType(ScaleType.FIT_XY);
		back.setTranslationX(65);
		back.setTranslationY(10);
		back.setTranslationZ(1023);
		
		border=new ImageView(prop.context);
		border.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.hp_bar_border,prop.options)));
		border.setLayoutParams(params4);
		border.setScaleType(ScaleType.FIT_XY);
		border.setTranslationX(49);
		border.setTranslationY(8);
		border.setTranslationZ(1023);

		tv=new TextView(prop.context);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(4);
		tv.setTranslationX(882);
		tv.setTranslationY(10);
		tv.setTranslationZ(1023);
		tv.setTextColor(Color.RED);
		tv.setTypeface(prop.ttf);
		tv.setLayoutParams(params1);
		run0.run();
		prop.playerAndUi.addView(back);
		prop.playerAndUi.addView(progress);
		prop.playerAndUi.addView(border);
		prop.playerAndUi.addView(tv);
		prop.healthBar=this;
	}
	
	
	Runnable run0=new Runnable(){

		@Override
		public void run()
		{
			tv.setText(String.valueOf((int)prop.player.health)+" | "
					   +String.valueOf((int)prop.player.max_health));
			
			progress.setScaleX(prop.player.health/prop.player.max_health);
		}
	};
	
	void update(){
		prop.onUi(run0);
	}
}
