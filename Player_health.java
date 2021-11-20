package sevash.testx;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Player_health extends Ui
{
	RelativeLayout menu;
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView progress;
	ImageView back;
	ImageView border;
	TextView tv;
	LayoutParams params2=new LayoutParams(272*2,21*2);
	LayoutParams params3=new LayoutParams(272*2,21*2);
	LayoutParams params4=new LayoutParams(280*2,31*2);
	
	
	main_properties prop;
	

	Player_health(main_properties prop){
		
		this.prop=prop;
		this.menu=prop.menu;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		
		progress=new ImageView(prop.context);
		progress.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.hp_bar_progress,prop.options)));
		progress.setLayoutParams(params2);
	    progress.setScaleType(ScaleType.FIT_XY);
		progress.setTranslationX(30);
		progress.setTranslationY(30);
		progress.setTranslationZ(1023);
		
		back=new ImageView(prop.context);
		back.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.hp_bar_back,prop.options)));
		back.setLayoutParams(params3);
		back.setScaleType(ScaleType.FIT_XY);
		back.setTranslationX(30);
		back.setTranslationY(30);
		back.setTranslationZ(1023);
		
		border=new ImageView(prop.context);
		border.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.hp_bar_border,prop.options)));
		border.setLayoutParams(params4);
		border.setScaleType(ScaleType.FIT_XY);
		border.setTranslationX(20);
		border.setTranslationY(20);
		border.setTranslationZ(1023);

		tv=new TextView(prop.context);
		tv.setText("100"+" | "+"100");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(16);
		tv.setTranslationX(272*2+100);
		tv.setTranslationY(30);
		tv.setTranslationZ(1023);
		tv.setTextColor(Color.RED);
		tv.setTypeface(prop.ttf);
		
		prop.world.addView(back);
		prop.world.addView(progress);
		prop.world.addView(border);
		prop.world.addView(tv);
		
	}
	
	
	Runnable run0=new Runnable(){

		@Override
		public void run()
		{
			progress.setScaleX(prop.player.health/prop.player.max_health);
		}


	};
}
