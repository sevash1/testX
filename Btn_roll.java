package sevash.testx;
import android.widget.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Btn_roll
{
	
	main_properties prop;
	public ImageView roll;
	public boolean pressed=false;
	public boolean isActive=false;
	public boolean isRunning=false;
	ImageView border;
	LayoutParams params=new LayoutParams((int)(160*0.6),(int)(160*0.6));
	LayoutParams params2=new LayoutParams((int)(260*0.6),(int)(260*0.6));
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	RelativeLayout lay;
	

	Btn_roll(main_properties prop){
		this.prop=prop;
		roll=new ImageView(prop.context);
		roll.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.pearl_01c,prop.options)));
		roll.setLayoutParams(params);
		roll.setScaleType(ScaleType.FIT_XY);
		roll.setTranslationX(prop.screenW-500);
		roll.setTranslationY(prop.screenH/1.2f);
		roll.setTranslationZ(2);
	    roll.setOnTouchListener(touch);

		border=new ImageView(prop.context);
		border.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.circle01,prop.options)));
		border.setLayoutParams(params2);
		border.setScaleType(ScaleType.FIT_XY);
		border.setTranslationX(roll.getTranslationX()+((int)(160*0.6)/2-(int)(260*0.6)/2));
		border.setTranslationY(roll.getTranslationY()+((int)(160*0.6)/2-(int)(260*0.6)/2));
		border.setTranslationZ(1);
		pressed=false;
		isActive=false;
		prop.setRoll(this);
		prop.playerAndUi.addView(border);
		prop.playerAndUi.addView(roll);

	}

	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(prop.stage.getStage_in_world()==Game_stage.PAUSE) return false;
				switch (p2.getAction()){
					case MotionEvent.ACTION_DOWN:{
						if(isActive) break;
							
							prop.player.anim_stage=0;
							
							pressed=true;
							isActive=true;
							prop.player.a_anim=Player.active_anim.ROLL;
							break;
						}
					case MotionEvent.ACTION_UP:{
							pressed=false;
							break;
						}
				}
				return true;
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				return true;
			}
		}


	};
}
