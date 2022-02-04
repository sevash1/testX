package sevash.testx;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Money 
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
	public float money_count=0;
	
	public static final enum Type{
		MENU,
		WORLD
	}
	
	Money(main_properties prop,Type type){
		
		this.prop=prop;
		
		iv=new ImageView(prop.context);
		tv=new TextView(prop.context);
		iv.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.coin_04b,prop.options)));
		iv.setLayoutParams(params);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setOnTouchListener(touch);
		tv.setText(String.valueOf(((float)((int)(money_count*10))/10)));
		tv.setTextSize(20);
		tv.setTranslationY(10);
		tv.setTranslationZ(1);
		iv.setTranslationZ(1);
		tv.setLayoutParams(params2);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		setType(type);
		new Thread(r1).start();
		
		}
		
		public void setType(Type type){
			if(type==Type.MENU){
				prop.activity.runOnUiThread(run2);
			
			}
			if(type==Type.WORLD){
				prop.activity.runOnUiThread(run1);
			}
		}
		
		
		public void setMoneyCount(float count){
			money_count=count;
			prop.activity.runOnUiThread(run3);
		}
		
		
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			tv.setText(String.valueOf(((float)((int)(money_count*10))/10)));
			
		}

			
		};
		
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			prop.menuLayout.removeView(iv);
			prop.menuLayout.removeView(tv);
			iv.setTranslationX(prop.screenW-iv.getWidth()-30);
			tv.setTranslationX(prop.screenW-iv.getWidth()-48*6-40);
			tv.setGravity(Gravity.RIGHT);
			tv.setAlpha(0);
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
			tv.setAlpha(255);
			prop.menuLayout.addView(iv);
			prop.menuLayout.addView(tv);
		}


	};
		public void addMoney(float count){
			money_count+=count;
			prop.activity.runOnUiThread(run);
		}
		
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			try{
				tv.setText(String.valueOf(((float)((int)(money_count*10))/10)));
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				}
			// TODO: Implement this method
		}	
		};
		
		boolean t=false;
		int al=255;
		
		public void v(){
			al=255;
			t=true;
			prop.activity.runOnUiThread(r2);
			
		}
		
	Runnable r1=new Runnable(){

		@Override
		public void run()
		{
			while(true){
				if(prop.stage.getStage()==Game_stage.EXIT)
					Thread.currentThread().stop();
				
					try{
						Thread.sleep(8);
						if(t){
							al=1;
							prop.activity.runOnUiThread(r2);
							
							Thread.sleep(1500);
								if(prop.stage.getStage()==Game_stage.WORLD)
								al=0;
									prop.activity.runOnUiThread(r2);
								t=false;
								break;
							}
					}
						catch(Exception ignore){}
					
			}
		}
		};
		
	Runnable r2=new Runnable(){

		@Override
		public void run()
		{
			tv.setAlpha(al);
		}

			
		};
		
		
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(prop.stage.getStage_in_world()==Game_stage.PAUSE&&
				prop.stage.getStage()!=Game_stage.MENU) return false;
				
				switch (p2.getAction()){

					case MotionEvent.ACTION_UP:{
						if(prop.inv.isOpen){
							prop.inv.closeInventory();
							prop.shop.openShop();
							break;
							}
							else if(!prop.shop.isOpen){
								prop.shop.openShop();
								break;
							
						}else if(prop.shop.isOpen){
							prop.shop.closeShop();
							break;
						}
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
