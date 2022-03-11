package sevash.livingSword;
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
	ImageView pic1;
	
	public static final enum Type{
		MENU,
		WORLD
	}
	
	Money(main_properties prop,Type type){
		
		this.prop=prop;
		
		iv=new ImageView(prop.context);
		tv=new TextView(prop.context);
		iv.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.coin_04d,prop.options)));
		iv.setLayoutParams(params);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setOnTouchListener(touch);
		tv.setText(String.valueOf((int)(money_count)));
		tv.setTextSize(20);
		tv.setTranslationY(10);
		tv.setTranslationZ(1);
		iv.setTranslationZ(1);
		tv.setLayoutParams(params2);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		setType(type);
		}
		
	float posX=0;
	float posY=0;
	float endX=0;
	float endY=0;
	float rX=0;
	float rY=0;
	float count=0;
	
	 Money(main_properties prop,String s, float x, float y){
		pic1=new ImageView(prop.context);
		pic1.setLayoutParams(params);
		pic1.setX(x-prop.world.getScrollX());
		pic1.setY(y-prop.world.getScrollY());
		pic1.setScaleType(ScaleType.FIT_XY);
		pic1.setImageResource(R.drawable.coin_01d);
		
		this.prop=prop;
		 posX=pic1.getX();
		 posY=pic1.getY();
		 endX=prop.screenW;
		 rX=(endX-posX)/100;
		 rY=(-posY)/100;
			new Thread(r4).start();
	}
	
	static void drop(main_properties prop,String s,float x, float y,float count){
		prop.money.addMoney(count);
		new Money(prop,s,x,y);
	}
	
	Runnable r4=new Runnable(){

		@Override
		public void run()
		{
			prop.onUi(r6);
			new Thread(r1).start();
			for(int i=0; i<100;i++){
			    try{
				  Thread.sleep(10+1);
			    }catch(Exception e){}
			    posX+=rX;
				posY+=rY;
				prop.onUi(r5);
			}
			
			}
	};
	
	Runnable r5=new Runnable(){

		@Override
		public void run()
		{
			pic1.setX(posX);
			pic1.setY(posY);
		}
	};
		
	Runnable r6=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.addView(pic1);
		}
	};
	
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
			tv.setText(String.valueOf((int)(money_count)));
			
		}

			
		};
		
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			prop.menuLayout.removeView(iv);
			prop.menuLayout.removeView(tv);
			iv.setTranslationX(prop.screenW-iv.getWidth()-30);
			iv.setY(10);
			iv.setZ(11);
			tv.setTranslationX(prop.screenW-iv.getWidth()-48*6-40);
			tv.setY(20);
			tv.setZ(11);
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
			iv.setTranslationX(prop.avatar.icon.getX()+prop.avatar.background.getLayoutParams().width+10);
			iv.setY(prop.menu.playerLevel.level_bar.getY()+prop.menu.playerLevel.level_bar.getLayoutParams().height+5);
			tv.setGravity(Gravity.LEFT);
			tv.setTranslationX(iv.getX()+iv.getLayoutParams().width+10);
			tv.setY(iv.getY()+10);
			tv.setAlpha(255);
			prop.money.iv.setTranslationZ(0);
			prop.money.tv.setTranslationZ(0);
			
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
				tv.setText(String.valueOf((int)(money_count)));
			}	
		};
		
		
	Runnable r1=new Runnable(){

		@Override
		public void run()
		{
					prop.activity.runOnUiThread(r2);
					
					try{
						Thread.sleep(1500);
					}catch(Exception ignore){}
					
					if(prop.stage.getStage()==Game_stage.WORLD){
						prop.activity.runOnUiThread(r3);
					}
					prop.onUi(r7);
		}
	};
	
	Runnable r7=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.removeView(pic1);
		}
	};
		
	Runnable r2=new Runnable(){

		@Override
		public void run()
		{
			prop.money.tv.setAlpha(1);
		}
			
		};
		
	Runnable r3=new Runnable(){

		@Override
		public void run()
		{
			if(prop.stage.getStage()==Game_stage.WORLD)
			   prop.money.tv.setAlpha(0);
		}

	};
		
		
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
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
			}


	};
}
