package sevash.livingSword;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.util.*;
import android.widget.ImageView.*;
import android.graphics.*;
import java.util.*;

public class Joystick 
{
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView joystick;
	ImageView border;
	LayoutParams params=new LayoutParams(190,190);
	main_properties prop;
	float joystick_downX=0;
	float joystick_downY=0;
	float joystickX=0;
	float joystickY=0;
	float lengthX=0;
	float lengthY=0;
	float length=0;
	public float ratioX=0f;
	public float ratioY=0f;
	public float attackX=0f;
	public float attackY=0f;
	Thread cd_hide;
	public boolean joystick_pressed=false;
	public RelativeLayout lay;
	float centr=0;
	float bX=0,bY=0,jX=0,jY=0;

	Joystick(main_properties prop){
		this.prop=prop;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		
		joystick=new ImageView(prop.context);
		joystick.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.joystick,prop.options)));
		joystick.setLayoutParams(params);
		joystick.setScaleType(ScaleType.FIT_XY);
		jX=200;
		jY=screenH/1.6f;
		joystick.setTranslationX(200);
		joystick.setTranslationY(screenH/1.6f);
		
		lay=new RelativeLayout(prop.context);
		lay.setLayoutParams(new LayoutParams(-1,-1));
		lay.setOnTouchListener(touch);

		border=new ImageView(prop.context);
		border.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.joystick_border,prop.options)));
		border.setLayoutParams(params);
		border.setScaleType(ScaleType.FIT_XY);
		bX=200;
		bY=screenH/1.6f;
		border.setTranslationX(200);
		border.setTranslationY(screenH/1.6f);
		
		centr=-95;
		cd_hide=new Thread(run);
		border.setVisibility(View.INVISIBLE);
		joystick.setVisibility(View.INVISIBLE);
		
		prop.playerAndUi.addView(border);
		prop.playerAndUi.addView(joystick);
		prop.playerAndUi.addView(lay);
		
		prop.setJoystick(this);
	}
	
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent m)
		{	
				switch(m.getAction()){
									
					case MotionEvent.ACTION_DOWN:{
							if(m.getX()>prop.screenW*0.5f||m.getY()<0.3f){
								return true;
							}
							if(joystick_pressed)break;
							if(Float.isNaN(m.getX())||Float.isNaN(m.getY()))break;
							joystick_downX=m.getX();
							joystick_downY=m.getY();
							prop.activity.runOnUiThread(run3);
							joystick_pressed=true;

							return true;
						}

					case MotionEvent.ACTION_UP:{
							prop.activity.runOnUiThread(run4);
							ratioX=0f;
							ratioY=0f;
							joystick_pressed=false;
							cd_hide=new Thread(run);
							cd_hide.start();
							if(prop.player.a_anim==Player.active_anim.IDLE)break;
							if(prop.attack.pressed)break;
							if(prop.shield.pressed)break;
							if(prop.roll.isActive)break;
							prop.player.anim_stage=0;
							prop.player.a_anim=Player.active_anim.IDLE;

							return true;
						}

					case MotionEvent.ACTION_MOVE:{
							if(!joystick_pressed) break;
							if(Float.isNaN(m.getX())||Float.isNaN(m.getY()))break;
							joystickX=m.getX();
							joystickY=m.getY();
							prop.activity.runOnUiThread(run5);

							lengthX=joystickX-joystick_downX;
							lengthY=joystickY-joystick_downY;
							length=(Math.abs(lengthX)+Math.abs(lengthY));
							ratioX=lengthX/length;
							ratioY=lengthY/length;
							if(prop.attack.pressed)break;
							if(prop.shield.pressed)break;
							if(prop.roll.pressed)break;
							if(prop.roll.isActive) break;

							if(prop.player.a_anim!=Player.active_anim.RUN){
								prop.player.anim_stage=0;
								prop.player.a_anim=Player.active_anim.RUN;

							}
							return true;
						}

				}
				return false;
		}
	};

	
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			try{
				Thread.sleep(1000);
				}catch(Exception e){}
				if(joystick_pressed) return;
				border.setVisibility(View.INVISIBLE);
				joystick.setVisibility(View.INVISIBLE);
				}
	};
	
	
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			joystick.setTranslationX(joystick_downX-95);
			joystick.setTranslationY(joystick_downY+centr);
			border.setTranslationX(joystick_downX-95);
			border.setTranslationY(joystick_downY+centr);
			border.setVisibility(View.VISIBLE);
			joystick.setVisibility(View.VISIBLE);
			}
	};
	
	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			joystick.setTranslationX(border.getTranslationX());
			joystick.setTranslationY(border.getTranslationY());
			}

	};
	
	
	Runnable run5=new Runnable(){

		@Override
		public void run()
		{
			joystick.setTranslationX(joystickX-95);
			joystick.setTranslationY(joystickY+centr);
	     }

	};
	
	public void updateRotate(){
		prop.activity.runOnUiThread(run6);
	}
	
	Runnable run6=new Runnable(){

		@Override
		public void run()
		{
			if(ratioX<0)prop.player.player.setRotationY(180);
			else if(ratioX>0) prop.player.player.setRotationY(0);
			}

	};
}
