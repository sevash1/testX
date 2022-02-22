package sevash.testx;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import android.view.*;

public class Btn_play 
{
	RelativeLayout menu;
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView btn;
	LayoutParams params=new LayoutParams(210*4,60*4);
	TextView tv;
	main_properties prop;
	Thread worldThread;
	
	Btn_play(main_properties prop){
		try{
			
		this.prop=prop;
			this.menu=prop.menuLayout;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		this.worldThread=prop.worldThread;
		btn=new ImageView(prop.context);
		tv=new TextView(prop.context);
		btn.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.btn_play,prop.options)));
		btn.setLayoutParams(params);
		btn.setScaleType(ScaleType.FIT_XY);
		btn.setTranslationX((screenW/2)-105*4);
		btn.setTranslationY(screenH-75*4);
		btn.setOnClickListener(click);
		tv.setText(Words.getRu(Words.words.PLAY));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(32);
		tv.setTranslationX((screenW/2)-105*4);
		tv.setTranslationY(screenH-75*4);
		tv.setLayoutParams(params);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		
			prop.menuLayout.addView(btn);
			prop.menuLayout.addView(tv);
		}catch(Exception e){
			files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"erro.txt",(new String[]{e.toString()}));
		}
	}
	
	
	OnClickListener click=new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
			try{
				if(!prop.stage.world_load_complete)return;
				prop.stage.setStage(Game_stage.WORLD);
				
				prop.worldThread=new Thread(prop.worldThreadRun);
				prop.worldThread.setDaemon(true);
				prop.worldThread.start();
			
				prop.stage.setStage_in_world(Game_stage.NOT_PAUSE);
				prop.joystick.lay.setVisibility(View.VISIBLE);
			    prop.inv.switchToWorld();
				}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				

			}
		}

		
	};
}
