package sevash.testx;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Money extends Ui
{
	RelativeLayout menu;
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView iv;
	LayoutParams params=new LayoutParams(48*2,48*2);
	LayoutParams params2=new LayoutParams(48*6,48*2);
	TextView tv;
	main_properties prop;
	public int money_count=0;
	
	public static final enum Type{
		MENU,
		WORLD
	}
	
	Money(main_properties prop,Type type){
		
		this.prop=prop;
		
		iv=new ImageView(prop.context);
		tv=new TextView(prop.context);
		iv.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.items,prop.options),48*5,48*7,48,48));
		iv.setLayoutParams(params);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setOnTouchListener(touch);
		iv.setTranslationZ(1023);
		tv.setText(String.valueOf(money_count));
		tv.setTextSize(20);
		
		tv.setTranslationY(10);
		tv.setTranslationZ(1023);
		tv.setLayoutParams(params2);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		setType(type);
		
		
		}
		
		public void setType(Type type){
			if(type==Type.MENU){
				prop.activity.runOnUiThread(run2);
			
			}
			if(type==Type.WORLD){
				prop.activity.runOnUiThread(run1);
			}
		}
		public void setMoneyCount(int count){
			money_count=count;
			prop.activity.runOnUiThread(run3);
		}
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			tv.setText(String.valueOf(money_count));
			
		}

			
		};
		
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			prop.menu.removeView(iv);
			prop.menu.removeView(tv);
			iv.setTranslationX(prop.screenW-iv.getWidth()-30);
			tv.setTranslationX(prop.screenW-iv.getWidth()-48*6-40);

			tv.setGravity(Gravity.RIGHT);
			
			prop.playerAndUi.addView(iv);
			prop.playerAndUi.addView(tv);
		}

			
		};
		
	Runnable run2=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.removeView(iv);
			prop.playerAndUi.removeView(tv);
			iv.setTranslationX(64);
			tv.setGravity(Gravity.LEFT);
			tv.setTranslationX(48*2+16+64);

			prop.menu.addView(iv);
			prop.menu.addView(tv);
		}


	};
		public void addMoney(int count){
			money_count+=count;
			prop.activity.runOnUiThread(run);
		}
		
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			try{
			tv.setText(String.valueOf(money_count));
			}catch(Exception e){
				files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				}
			// TODO: Implement this method
		}

			
		};
		
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(prop.stage.getStage_in_world()==Game_stage.PAUSE) return false;
				switch (p2.getAction()){

					case MotionEvent.ACTION_UP:{
							prop.shop.openShop();
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
