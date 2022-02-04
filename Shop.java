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
	LayoutParams params4=new LayoutParams(90*2,90*2);
	float y=0;
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	public boolean isOpen=false;
	ImageView temp;
	RelativeLayout shopItems;
	TargetItem ti;
	String tmpName="";
	List itemPictures=new ArrayList<ImageView>();
	TextView buyed;
	TextView notBuyed;
	
	Shop(main_properties prop){
		this.prop=prop;
	shop=new RelativeLayout(prop.context);
	shop.setTranslationZ(10);
	shopItems=new RelativeLayout(prop.context);
	shopItems.setLayoutParams(params3);
	shopItems.setOnTouchListener(touch);
	shop.setBackgroundColor(Color.argb(64,0,0,0));
		shop.addView(shopItems);
	
	ImageView in;
		for(int i=0;i<10;i++){
			for(int j=0;j<35;j++){
				in=new ImageView(prop.context);
				in.setImageDrawable(((Item)prop.items.get(0)).picture.getDrawable());
				in.setX(200+i*106);
				in.setY(100+j*106);
				in.setZ(3);
				in.setAlpha(192);
				shopItems.addView(in,params);
			
			}
		}
		ImageView it;
		for(int i=1;i<prop.items.size();i++){
			    it=new ImageView(prop.context);
				it.setX(200+3+(i%10)*106);
				it.setY(100+3+(i/10)*106);
				it.setZ(4);
			    it.setOnTouchListener(t2);
				it.setImageDrawable(((ImageView)((Item)prop.items.get(i)).picture).getDrawable());
				itemPictures.add(it);
				shopItems.addView(it,Item.params1);
				
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
						if((y-p2.getY())>(34*106-350)
							
						   ||(y-(p2.getY())<-150))return true;
						
					shopItems.setScrollY((int)(y-p2.getY()));
				}
			}
			// TODO: Implement this method
			return true;
		}

		
	};
	
	
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
				y=p2.getY()+p1.getTranslationY();
				
			}
			if(p2.getAction()==MotionEvent.ACTION_MOVE){
				if(((shopItems.getScrollY()+y)-(p2.getY()+p1.getTranslationY())>(-350+34*106))
				   ||(shopItems.getScrollY()+y)-(p2.getY()+p1.getTranslationY())<-150) return true;
					shopItems.setScrollY((int)((shopItems.getScrollY()+y)-(p2.getY()+p1.getTranslationY())));
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
		TextView name;
		TextView price;
		int tmpPrice=-1;
		Item item;
		
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
			itemL.setBackgroundColor(Color.argb(192,64,64,240));
			lay.addView(itemL);
			
			priceL=new RelativeLayout(prop.context);
			priceL.setLayoutParams(new LayoutParams(300,300));
			priceL.setTranslationX(430);
			priceL.setTranslationY(15);
			priceL.setBackgroundColor(Color.argb(192,64,240,64));
			lay.addView(priceL);
			
			descriptionL=new RelativeLayout(prop.context);
			descriptionL.setLayoutParams(new LayoutParams(715,600));
			descriptionL.setTranslationX(15);
			descriptionL.setTranslationY(330);
			descriptionL.setBackgroundColor(Color.argb(192,240,192,150));
			lay.addView(descriptionL);
			
			name=new TextView(prop.context);
			name.setText(" Продолжить ");
			name.setGravity(Gravity.CENTER);
			name.setTextSize(24);
			name.setTranslationX(15);
			name.setTranslationY(20);
			name.setLayoutParams(new LayoutParams(685,LayoutParams.WRAP_CONTENT));
			name.setTextColor(Color.BLUE);
			name.setTypeface(prop.ttf);
			name.setShadowLayer(20,5,5,Color.argb(255,166,166,255));
			descriptionL.addView(name);
			
			ImageView btn_buy=new ImageView(prop.context);
			btn_buy.setX(25);
			btn_buy.setY(225);
			btn_buy.setImageResource(R.drawable.btns_background);
			btn_buy.setLayoutParams(new LayoutParams(250,50));
			TextView buy=new TextView(prop.context);
			buy.setText(prop.words.get(Words.words.BUY));
			buy.setGravity(Gravity.CENTER);
			buy.setTextSize(9);
			buy.setTranslationX(btn_buy.getX());
			buy.setTranslationY(btn_buy.getY());
			buy.setLayoutParams(new LayoutParams(250,50));
			buy.setTextColor(Color.YELLOW);
			buy.setTypeface(prop.ttf);
			btn_buy.setOnClickListener(c1);
			buyed=new TextView(prop.context);
			buyed.setText(prop.words.get(Words.words.BUYED));
			buyed.setGravity(Gravity.CENTER);
			buyed.setTextSize(9);
			buyed.setAlpha(0);
			buyed.setTranslationX(50);
			buyed.setTranslationY(65);
			buyed.setZ(5);
			buyed.setLayoutParams(new LayoutParams(250,50));
			buyed.setTextColor(Color.GREEN);
			buyed.setTypeface(prop.ttf);
			notBuyed=new TextView(prop.context);
			notBuyed.setText(prop.words.get(Words.words.NOT_BUYED));
			notBuyed.setGravity(Gravity.CENTER);
			notBuyed.setTextSize(9);
			notBuyed.setAlpha(0);
			notBuyed.setTranslationX(50);
			notBuyed.setTranslationY(65);
			notBuyed.setZ(5);
			notBuyed.setLayoutParams(new LayoutParams(250,50));
			notBuyed.setTextColor(Color.RED);
			notBuyed.setTypeface(prop.ttf);
			
			
			price=new TextView(prop.context);
		    price.setText("0");
			price.setGravity(Gravity.RIGHT);
			price.setTextSize(14);
			price.setTranslationX(25);
			price.setTranslationY(25);
			price.setLayoutParams(new LayoutParams(250,175));
			price.setTextColor(Color.RED);
			price.setTypeface(prop.ttf);
			priceL.addView(btn_buy);
			priceL.addView(buy);
			priceL.addView(price);
			
			new Thread(r1).start();
			
			target=new ImageView(prop.context);
			target.setLayoutParams(params4);
			target.setTranslationX(50);
			target.setTranslationY(65);
			target.setTranslationZ(4);
			itemL.addView(target);
			itemL.addView(buyed);
			itemL.addView(notBuyed);
			prop.activity.runOnUiThread(r3);
			}
			
	void setItem(ImageView t){
		it=t;
		item=findItem(prop,t,itemPictures,item);
		tmpName=item.name; 
		tmpPrice=item.price;
		prop.activity.runOnUiThread(r4);
	}
	boolean t=false;
	int al=0;
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				while(true){
					if(prop.stage.getStage()==Game_stage.EXIT)
						Thread.currentThread().stop();
					
					try{
						Thread.sleep(8);
						while(t){
							al--;
							prop.activity.runOnUiThread(r2);
							if(al==-100){
								t=false;
								prop.activity.runOnUiThread(r5);
								break;
							}
							Thread.sleep(8);
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
				buyed.setAlpha(1);
				buyed.setY(al);
			}


		};
		
		Runnable r5=new Runnable(){

			@Override
			public void run()
			{
				buyed.setAlpha(0);
				buyed.setY(65);
			}


		};
		
		Runnable r6=new Runnable(){

			@Override
			public void run()
			{
				try{
							prop.activity.runOnUiThread(r7);
					Thread.sleep(1000);
								prop.activity.runOnUiThread(r8);
							Thread.sleep(8);
						}
					catch(Exception ignore){}
			}
		};

		Runnable r7=new Runnable(){

			@Override
			public void run()
			{
				notBuyed.setAlpha(1);
			}
		};

		Runnable r8=new Runnable(){

			@Override
			public void run()
			{
				notBuyed.setAlpha(0);
			}
		};
		
	
		OnClickListener c1=new OnClickListener(){

			@Override
			public void onClick(View p1)
			{
				if(item==null) return;
				if(!prop.inv.addItem("shop",item,0)){
					
				new Thread(r6).start();
				return;
				}else{
					
				al=65;
				t=true;
				}
			}

	};
	
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
				target.setImageDrawable(item.picture.getDrawable());
				name.setText(item.name);
				price.setText(String.valueOf(item.price));
		}
	};

	}
	
	
	static Item findItem(main_properties prop,ImageView pic,List il,Item item){
		int i=1;
		for(ImageView item1:il){
			if(item1==pic){
				return (Item)prop.items.get(i);
			}
			i++;
		}
		return null;
	}
	
	
	
}
