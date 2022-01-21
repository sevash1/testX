package sevash.testx;
import android.widget.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Btn_shield
{
	main_properties prop;
	public ImageView shield;
	public boolean pressed=false;
	ImageView border;
	LayoutParams params=new LayoutParams((int)(160*0.6),(int)(160*0.6));
	LayoutParams params2=new LayoutParams((int)(260*0.6),(int)(260*0.6));
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	RelativeLayout lay;

	Btn_shield(main_properties prop){
		this.prop=prop;
		shield=new ImageView(prop.context);
		shield.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.shield_02e,prop.options)));
		shield.setLayoutParams(params);
		shield.setScaleType(ScaleType.FIT_XY);
		shield.setTranslationX(prop.screenW-675);
		shield.setTranslationY(prop.screenH/1.2f);
		shield.setTranslationZ(1023);
		shield.setOnTouchListener(touch);
		
		border=new ImageView(prop.context);
		border.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.circle01,prop.options)));
		border.setLayoutParams(params2);
		border.setScaleType(ScaleType.FIT_XY);
		border.setTranslationX(shield.getTranslationX()+((int)(160*0.6)/2-(int)(260*0.6)/2));
		border.setTranslationY(shield.getTranslationY()+((int)(160*0.6)/2-(int)(260*0.6)/2));
		border.setTranslationZ(1022);

		prop.setShield(this);
		prop.playerAndUi.addView(border);
		prop.playerAndUi.addView(shield);

	}

	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(prop.stage.getStage_in_world()==Game_stage.PAUSE) return false;
				switch (p2.getAction()){
					case MotionEvent.ACTION_DOWN:{
							if(prop.roll.isActive) break;
							prop.player.anim_stage=0;
							prop.player.a_anim=Player.active_anim.SHIELD;
							pressed=true;
							break;
						}
					case MotionEvent.ACTION_UP:{
							pressed=false;
							break;
						}
				}
				return true;
			}catch(Exception e){
				files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				return true;
			}
		}


	};
}
