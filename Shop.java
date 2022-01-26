package sevash.testx;
import android.widget.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.graphics.*;
import java.util.*;

public class Shop
{
	RelativeLayout shop;
	main_properties prop;
	LayoutParams params=new LayoutParams(96,96);
	LayoutParams params1=new LayoutParams(90,90);
	LayoutParams params4=new LayoutParams(90*2,90*2);
	float y=0;
	List items=new ArrayList<ImageView>();
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	public boolean isOpen=false;
	ImageView[][] in=new ImageView[10][35];
	ImageView temp;
	RelativeLayout shopItems;
	TargetItem ti;
	
	Shop(main_properties prop){
		this.prop=prop;
	shop=new RelativeLayout(prop.context);
	shop.setTranslationZ(10);
	shopItems=new RelativeLayout(prop.context);
	shopItems.setLayoutParams(params3);
	shopItems.setOnTouchListener(touch);
	shop.setBackgroundColor(Color.argb(64,0,0,0));
		shop.addView(shopItems);
	
	
	load_items();
	int f=0;
		for(int i=0;i<10;i++){
			for(int j=0;j<35;j++){
				in[i][j]=new ImageView(prop.context);
				in[i][j].setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.cell01,prop.options)));
				in[i][j].setTranslationX(200+i*106);
				in[i][j].setTranslationY(100+j*106);
				in[i][j].setTranslationZ(3);
				in[i][j].setAlpha(192);
				shopItems.addView(in[i][j],params);
			
			}
		}
		for(int i=0;i<35;i++){
			for(int j=0;j<10;j++){
				((ImageView)items.get(f)).setTranslationX(200+3+j*106);
				((ImageView)items.get(f)).setTranslationY(100+3+i*106);
				((ImageView)items.get(f)).setTranslationZ(4);
				((ImageView)items.get(f)).setOnTouchListener(t2);
				shopItems.addView((ImageView)items.get(f),params1);
				f++;
				}
				}
	ti=new TargetItem();
	prop.setShop(this);
		
	}
	
	
	public void openShop(){
		prop.vanishGameButtons();
		prop.activity.runOnUiThread(run);
	}
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			prop.money.iv.setTranslationZ(11);
			prop.money.tv.setTranslationZ(11);
			
			if(prop.stage.getStage()==Game_stage.WORLD)
			    prop.playerAndUi.addView(shop,params3);
			if(prop.stage.getStage()==Game_stage.MENU)
				prop.menuLayout.addView(shop,params3);
			
			isOpen=true;
			// TODO: Implement this method
		}


	};
	
	OnTouchListener t1=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
		
			return true;
		}

		
	};
	
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			switch (p2.getAction()){
				case MotionEvent.ACTION_DOWN:{
						y=shopItems.getScrollY()+p2.getY();
					break;
				}
				case MotionEvent.ACTION_MOVE:{
					shopItems.setScrollY((int)(y-p2.getY()));
				}
			}
			// TODO: Implement this method
			return true;
		}

		
	};
	
	
	void load_items(){
		
		for(int i=0;i<22;i++){
			for(int j=0;j<16;j++){
				temp=new ImageView(prop.context);
				temp.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.items,prop.options),48*j,48*i,48,48));
				
				items.add(temp);
				}
				}
	}
	
	public void closeShop(){
		prop.activity.runOnUiThread(run1);
		prop.showGameButtons();
	}
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			if(prop.stage.getStage()==Game_stage.WORLD)
			    prop.playerAndUi.removeView(shop);
			if(prop.stage.getStage()==Game_stage.MENU)
				prop.menuLayout.removeView(shop);
			
			prop.money.iv.setTranslationZ(1);
			prop.money.tv.setTranslationZ(1);
			
			isOpen=false;
			// TODO: Implement this method
		}
	};
	
	OnTouchListener t2=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1,MotionEvent p2)
		{
		    if(p2.getAction()==MotionEvent.ACTION_DOWN){
				y=shopItems.getScrollY()+p2.getY()+p1.getTranslationY();
				
			}
			if(p2.getAction()==MotionEvent.ACTION_MOVE){
					shopItems.setScrollY((int)(y-(p2.getY()+p1.getTranslationY())));
			}
			if(p2.getAction()==MotionEvent.ACTION_UP){
			ti.setItem((ImageView)p1);
			}
			
			return true;
		}
		
	};
	
	class TargetItem{
		RelativeLayout lay;
		RelativeLayout itemL;
		RelativeLayout priceL;
		RelativeLayout descriptionL;
		ImageView target;
		ImageView it;
		
		TargetItem(){
			lay=new RelativeLayout(prop.context);
			lay.setLayoutParams(new LayoutParams((int)(prop.screenW/3),(int)(prop.screenH/1.2)));
			lay.setTranslationX(prop.screenW-prop.screenW/3-60);
			lay.setTranslationY(90);
			lay.setBackgroundColor(Color.argb(128,240,32,32));
			lay.setOnTouchListener(t1);
			
			itemL=new RelativeLayout(prop.context);
			itemL.setLayoutParams(new LayoutParams(300,300));
			itemL.setTranslationX(15);
			itemL.setTranslationY(15);
			itemL.setBackgroundColor(Color.argb(64,64,64,240));
			lay.addView(itemL);
			
			priceL=new RelativeLayout(prop.context);
			priceL.setLayoutParams(new LayoutParams(300,300));
			priceL.setTranslationX(430);
			priceL.setTranslationY(15);
			priceL.setBackgroundColor(Color.argb(64,64,240,64));
			lay.addView(priceL);
			
			descriptionL=new RelativeLayout(prop.context);
			descriptionL.setLayoutParams(new LayoutParams(715,600));
			descriptionL.setTranslationX(15);
			descriptionL.setTranslationY(330);
			descriptionL.setBackgroundColor(Color.argb(64,240,192,150));
			lay.addView(descriptionL);
			
			target=new ImageView(prop.context);
			target.setLayoutParams(params4);
			target.setTranslationX(50);
			target.setTranslationY(65);
			target.setTranslationZ(4);
			itemL.addView(target)
			;
			prop.activity.runOnUiThread(r3);
			}
			
	void setItem(ImageView t){
		it=t;
		prop.activity.runOnUiThread(r4);
	}
	
		Runnable r3=new Runnable(){

			@Override
			public void run()
			{
				shop.addView(lay);
			}
	};
	
		Runnable r4=new Runnable(){

			@Override
			public void run()
			{
				target.setImageDrawable(it.getDrawable());
			}
	};
	}
}
