package sevash.testx;
import android.widget.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import android.view.*;

public class Btn_attack
{
	main_properties prop;
	public ImageView attack;
	public boolean pressed=false;
	ImageView border;
	LayoutParams params=new LayoutParams(160,160);
	LayoutParams params2=new LayoutParams(260,260);
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	RelativeLayout lay;
	
	Btn_attack(main_properties prop){
		this.prop=prop;
		attack=new ImageView(prop.context);
		attack.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.sword_01,prop.options)));
		attack.setLayoutParams(params);
		attack.setScaleType(ScaleType.FIT_XY);
		attack.setTranslationX(prop.screenW-450);
		attack.setTranslationY(prop.screenH/1.6f);
		attack.setTranslationZ(1023);
		attack.setOnTouchListener(touch);
		prop.setAttack(this);
		
		border=new ImageView(prop.context);
		border.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.circle01,prop.options)));
		border.setLayoutParams(params2);
		border.setScaleType(ScaleType.FIT_XY);
		border.setTranslationX(prop.screenW-450-50);
		border.setTranslationY(prop.screenH/1.6f-50);
		border.setTranslationZ(1022);
		
		prop.playerAndUi.addView(border);
		prop.playerAndUi.addView(attack);
		
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
					prop.player.a_anim=Player.active_anim.ATTACK;
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
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				return true;
			}
		}

		
	};
}
