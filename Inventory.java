package sevash.livingSword;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.view.*;
import android.widget.ImageView.*;
import java.util.*;

public class Inventory
{
	main_properties prop;
	public ImageView inv;
	RelativeLayout inven;
	public boolean isOpen=false;
	LayoutParams params0;
	LayoutParams params1=new LayoutParams(96,96);
	LayoutParams params2=new LayoutParams(90,90);
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	LayoutParams params4=new LayoutParams(96+48,96+48);
	LayoutParams params5=new LayoutParams(90+45,90+45);
	Boolean[][] z=new Boolean[10][5];
	Item[] armItems=new Item[8];
	List items=new ArrayList<Item>();
	List armorL=new ArrayList<ImageView>();
	SlotsPos[] slots=new SlotsPos[8];
	RelativeLayout lay;
	ImageView ground;
	ImageView[][] in=new ImageView[10][10];
	OnTouchListener[] TList=new OnTouchListener[8];
	int lastSlot=0;
	RelativeLayout descriptionL;
	TextView name;
	TextView descT;

	Inventory(main_properties prop){
		this.prop=prop;
		inv=new ImageView(prop.context);
		inv.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.wood_01a,prop.options)));
		inv.setLayoutParams(params1);
		inv.setScaleType(ScaleType.FIT_XY);
		inv.setTranslationZ(0);
		inv.setOnTouchListener(touch);
		inven=new RelativeLayout(prop.context);
		inven.setTranslationZ(2);
		inven.setVisibility(View.INVISIBLE);
		inven.setBackgroundColor(Color.argb(64,0,0,0));
		inven.setOnTouchListener(t2);
		inven.setLayoutParams(params3);
		ground=new ImageView(prop.context);
		ground.setImageResource(R.drawable.cellbig_02);
		ground.setTranslationX(0);
		ground.setTranslationY(0);
		ground.setTranslationZ(9999+1);
		ground.setVisibility(View.INVISIBLE);
		ground.setAlpha(216);
		ground.setLayoutParams(params1);
		inven.addView(ground);
		for(int i=0;i<10;i++){
			for(int j=0;j<5;j++){
				z[i][j]=true;
				in[i][j]=new ImageView(prop.context);
				in[i][j].setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.cell01,prop.options)));
			in[i][j].setTranslationX(200+i*106);
				in[i][j].setTranslationY(200+j*106);
				in[i][j].setTranslationZ(9999);
				in[i][j].setAlpha(192);
				armorL.add(in[i][j]);
				inven.addView(in[i][j],params1);
			}
		}
		ImageView del=new ImageView(prop.context);
		del.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.cell01,prop.options)));
		del.setTranslationX(200+9*106);
		del.setTranslationY(200-106);
		del.setTranslationZ(9999);
		del.setAlpha(192);
		del.setOnTouchListener(t3);
		inven.addView(del,params1);
		ImageView del1=new ImageView(prop.context);
		del1.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.delete,prop.options)));
		del1.setTranslationX(200+9*106+8);
		del1.setTranslationY(200-106+8);
		del1.setTranslationZ(9999);
		del1.setAlpha(192);
		inven.addView(del1,80,80);
		
		descriptionL=new RelativeLayout(prop.context);
		descriptionL.setLayoutParams(new LayoutParams(1000,200));
		descriptionL.setTranslationX(prop.screenW/12);
		descriptionL.setTranslationY(prop.screenH-300);
		descriptionL.setBackgroundColor(Color.argb(192,150,160,240));
		descriptionL.setVisibility(View.INVISIBLE);
		inven.addView(descriptionL);
		
		name=new TextView(prop.context);
		name.setText(" Продолжить ");
		name.setGravity(Gravity.CENTER);
		name.setTextSize(18);
		name.setTranslationX(15);
		name.setTranslationY(20);
		name.setLayoutParams(new LayoutParams(685,LayoutParams.WRAP_CONTENT));
		name.setTextColor(Color.GREEN);
		name.setTypeface(prop.ttf);
		name.setShadowLayer(20,5,5,Color.argb(255,166,166,255));
		descriptionL.addView(name);

		descT=new TextView(prop.context);
		descT.setText(" Продолжить ");
		descT.setGravity(Gravity.LEFT);
		descT.setTextSize(8);
		descT.setTranslationX(15);
		descT.setTranslationY(100);
		descT.setLayoutParams(new LayoutParams(685,LayoutParams.MATCH_PARENT));
		descT.setTextColor(Color.YELLOW);
		descT.setTypeface(prop.ttf);
		descT.setShadowLayer(20,5,5,Color.argb(255,166,166,255));
		descriptionL.addView(descT);
		
		slot_0();
		slot_1();
		slot_2();
		slot_3();
		slot_4();
		slot_5();
		slot_6();
		slot_7();
		TList[0]=tA0;
			TList[1]=tA1;
			TList[2]=tA2;
			TList[3]=tA3;
			TList[4]=tA4;
			TList[5]=tA5;
			TList[6]=tA6;
			TList[7]=tA7;
		prop.setInventory(this);
		
		switchToMenu();
		
		
	}
	
	Runnable r5=new Runnable(){

		@Override
		public void run()
		{
			if(ground.getVisibility()==View.INVISIBLE)
				descriptionL.setVisibility(View.INVISIBLE);
			else
				descriptionL.setVisibility(View.VISIBLE);
			name.setText(item.name);
		}
	};

	
	

	OnTouchListener t2=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			prop.activity.runOnUiThread(r3);
			prop.onUi(r5);
			return true;
		}
	};
	
	OnTouchListener t3=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			if(p2.getAction()==MotionEvent.ACTION_UP){
				if(ground.getVisibility()==View.VISIBLE){
					if(item.armSlot<0){
						items.remove(item);
						z[item.slotX][item.slotY]=true;
						prop.onUi(r4);
						prop.onUi(r5);
						}
					else{
						armItems[item.armSlot]=null;
						prop.onUi(r4);
						prop.onUi(r5);
					}
				}
			}
			return true;
		}

		
	};
	
	Runnable r4=new Runnable(){

		@Override
		public void run()
		{
			ground.setVisibility(View.INVISIBLE);
			inven.removeView(item.picture);
			
		}
	};
	
	Runnable r3=new Runnable(){

		@Override
		public void run()
		{
			ground.setVisibility(View.INVISIBLE);
		}
	};
	
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				switch (p2.getAction()){
					
					case MotionEvent.ACTION_UP:{
							if(prop.shop.isOpen)break;
							if(!isOpen){
							openInventory();
							}
						else{
							closeInventory();
							 }
							break;
						}
				}
				return true;
			
		}
	};
	public void closeInventory(){
		prop.activity.runOnUiThread(run);
	}
	
	public void openInventory(){
		prop.onUi(run2);
	}
	
	void switchToMenu(){
		prop.onUi(run3);
	}
	
	void switchToWorld(){
		prop.onUi(run4);
	}
	
	
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			prop.showGameButtons();
			inven.setVisibility(View.INVISIBLE);
			inv.setZ(0);
			isOpen=false;
		} 
 };
 
	Runnable run2=new Runnable(){

		@Override
		public void run()
		{
			prop.vanishGameButtons();
			inv.setZ(11);
			inven.setVisibility(View.VISIBLE);
			isOpen=true;
		} 
	};
	
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.removeView(inven);
			prop.menuLayout.removeView(inven);
			prop.menuLayout.addView(inven);
			prop.playerAndUi.removeView(inv);
			inv.setTranslationX(prop.screenW-250-320);
			inv.setTranslationY(60);
			inven.setBackgroundColor(Color.argb(64,255,255,255));
			prop.menuLayout.removeView(inv);
			prop.menuLayout.addView(inv);
		} 
	};

	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			prop.menuLayout.removeView(inven);
			inven.setBackgroundColor(Color.argb(64,0,0,0));
			prop.playerAndUi.removeView(inven);
			prop.playerAndUi.addView(inven);
			prop.menuLayout.removeView(inv);
			inv.setTranslationX(prop.screenW-250);
			inv.setTranslationY(prop.screenH/8f);
			prop.playerAndUi.removeView(inv);
			prop.playerAndUi.addView(inv);
		} 
	};
	
 
 int x=0;
 int y=0;
 boolean freeSlot(){
	 for(int i=0;i<5;i++){
		 for(int j=0;j<10;j++){
			 if(z[j][i]){
				 x=j;
				 y=i;
				 return true;
				 }
		 }
	 }
	 return false;
 }
 
	ImageView ivv;
	public boolean addItem(String from, Item item,int p){
		if(from.contentEquals("armF")){
			ivv=item.picture;
			ivv.setImageResource(item.pictureInt);
			ivv.setLayoutParams(params4);
			ivv.setX(slots[p].x);
			ivv.setY(slots[p].y);
			ivv.setZ(10000);
			armItems[p]=item;
			item.armSlot=p;
			ivv.setOnTouchListener(aT);
			inven.addView(ivv);
			return true;
		}
		
		if(!freeSlot())return false;
		if(prop.money.money_count-item.price<0&&from.contentEquals("shop"))return false;
		if(from.contentEquals("shop"))prop.money.addMoney(-item.price);
		
		ivv=item.picture;
		ivv.setImageResource(item.pictureInt);
		ivv.setLayoutParams(params2);
		ivv.setX(203+106*x);
		ivv.setY(203+106*y);
		ivv.setZ(10000);
		ivv.setOnTouchListener(t1);
		items.add(item);
		item.slotX=x;
		item.slotY=y;
		z[x][y]=false;
		inven.addView(ivv);
		return true;
	}
 
	
 ImageView it;
 Item item;
 
	void setItem(ImageView t,int f){
		if(f==0)
		for(Item item1:items){
			if(item1==null)continue;
			if(item1.picture==t){
				item=item1;
				it=item.picture;
			}
		}
		else if(f==1)
			for(Item item1:armItems){
				if(item1==null)continue;
				if(item1.picture==t){
					item=item1;
					it=item.picture;
				}
			}
	}
	
	
	View pp=null;
	OnTouchListener t1=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			if(p2.getAction()==MotionEvent.ACTION_UP){
				pp=p1;
				params0=params1;
				tmpItem=item;
				setItem((ImageView)p1,0);
				prop.activity.runOnUiThread(r2);
				prop.onUi(r5);
			}
			return true;
			
		}
	};
	
	OnTouchListener aT=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					pp=p1;
					params0=params4;
					tmpItem=item;
					setItem((ImageView)p1,1);
					prop.activity.runOnUiThread(r2);
					prop.onUi(r5);
				}
			return true;
		}

	};
	
	
	Runnable r2=new Runnable(){

		@Override
		public void run()
		{
			ground.setLayoutParams(params0);
			if(ground.getVisibility()==View.VISIBLE){
				if(item.t==tmpItem.t&& item!=tmpItem&&item.armSlot!=tmpItem.armSlot){
					
					if(item.armSlot>=0){
						
						tmpItem.picture.setLayoutParams(item.picture.getLayoutParams());
						tmpItem.picture.setX(item.picture.getX());
						tmpItem.picture.setY(item.picture.getY());
						items.remove(tmpItem);
						armItems[item.armSlot]=tmpItem;
						tmpItem.armSlot=item.armSlot;
						tmpItem.picture.setOnTouchListener(aT);
						
						item.picture.setX(203+106*tmpItem.slotX);
						item.picture.setY(203+106*tmpItem.slotY);
						item.slotX=tmpItem.slotX;
						item.slotY=tmpItem.slotY;
						item.picture.setLayoutParams(params2);
						item.armSlot=-1;
						items.add(item);
						item.picture.setOnTouchListener(t1);
					
						}
						
					else if(item.armSlot==-1){

						tmpItem.picture.setLayoutParams(item.picture.getLayoutParams());
						item.picture.setX(tmpItem.picture.getX());
						item.picture.setY(tmpItem.picture.getY());
						items.remove(item);
						armItems[tmpItem.armSlot]=item;
						item.armSlot=tmpItem.armSlot;
						tmpItem.picture.setOnTouchListener(t1);

						tmpItem.picture.setX(203+106*item.slotX);
						tmpItem.picture.setY(203+106*item.slotY);
						tmpItem.slotX=item.slotX;
						tmpItem.slotY=item.slotY;
						item.picture.setLayoutParams(params5);
						tmpItem.armSlot=-1;
						items.add(tmpItem);
						item.picture.setOnTouchListener(aT);

					}
				}
			   ground.setVisibility(View.INVISIBLE);
			   }
			else
				ground.setVisibility(View.VISIBLE);
				ground.setX(pp.getX());
				ground.setY(pp.getY());
				prop.onUi(r5);
		}	
	};
	
	class SlotsPos{
		float x;
		float y;
		SlotsPos(float x,float y){
			this.x=x;
			this.y=y;
		}
	}
 
 void slot_0(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*3/10);
	 ground.setTranslationY(prop.screenH*2/10);
	 slots[0]=new SlotsPos(prop.screenW-prop.screenW*3/10,
	                       prop.screenH*2/10);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
	 ground.setOnTouchListener(tA0);
	 inven.addView(ground);
	 ImageView helmet=new ImageView(prop.context);
	 helmet.setImageResource(R.drawable.helmet_01c);
	 helmet.setTranslationX(ground.getTranslationX()+4.5f);
	 helmet.setTranslationY(ground.getY()+4.5f);
	 helmet.setTranslationZ(9999);
	 helmet.setImageAlpha(48);
	 helmet.setLayoutParams(params5);
	 inven.addView(helmet);
 }
 void slot_1(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*3/10);
	 ground.setTranslationY(prop.screenH*4/10);
	 slots[1]=new SlotsPos(prop.screenW-prop.screenW*3/10,
	                       prop.screenH*4/10);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
	 ground.setOnTouchListener(tA1);
	 inven.addView(ground);
	 ImageView arm=new ImageView(prop.context);
	 arm.setImageResource(R.drawable.armor_01c);
	 arm.setTranslationX(ground.getTranslationX()+4.5f);
	 arm.setTranslationY(ground.getY()+4.5f);
	 arm.setTranslationZ(9999);
	 arm.setImageAlpha(48);
	 arm.setLayoutParams(params5);
	 armorL.add(ground);
	 inven.addView(arm);
 }
 void slot_2(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*4/10);
	 ground.setTranslationY(prop.screenH*6/10);
	 slots[2]=new SlotsPos(prop.screenW-prop.screenW*4/10,
	                       prop.screenH*6/10);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
	 ground.setOnTouchListener(tA2);
	 inven.addView(ground);
	 ImageView boots=new ImageView(prop.context);
	 boots.setImageResource(R.drawable.boots_01c);
	 boots.setTranslationX(ground.getTranslationX()+4.5f);
	 boots.setTranslationY(ground.getY()+4.5f);
	 boots.setTranslationZ(9999);
	 boots.setImageAlpha(48);
	 boots.setLayoutParams(params5);
	 inven.addView(boots);
 }
 void slot_3(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*2/10);
	 ground.setTranslationY(prop.screenH*4/10);
	 slots[3]=new SlotsPos(prop.screenW-prop.screenW*2/10,
	                       prop.screenH*4/10);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
	 ground.setOnTouchListener(tA3);
	 inven.addView(ground);
	 ImageView gloves=new ImageView(prop.context);
	 gloves.setImageResource(R.drawable.gloves_01c);
	 gloves.setTranslationX(ground.getTranslationX()+4.5f);
	 gloves.setTranslationY(ground.getY()+4.5f);
	 gloves.setTranslationZ(9999);
	 gloves.setImageAlpha(48);
	 gloves.setLayoutParams(params5);
	 inven.addView(gloves);
 }
 void slot_4(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*2/10);
	 ground.setTranslationY(prop.screenH*6/10);
	 slots[4]=new SlotsPos(prop.screenW-prop.screenW*2/10,
	                       prop.screenH*6/10);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
	 ground.setOnTouchListener(tA4);
	 inven.addView(ground);
	 ImageView shield=new ImageView(prop.context);
	 shield.setImageResource(R.drawable.shield_01c);
	 shield.setTranslationX(ground.getTranslationX()+4.5f);
	 shield.setTranslationY(ground.getY()+4.5f);
	 shield.setTranslationZ(9999);
	 shield.setImageAlpha(48);
	 shield.setLayoutParams(params5);
	 inven.addView(shield);
 }
	void slot_5(){
		ImageView ground=new ImageView(prop.context);
		ground.setImageDrawable(in[0][0].getDrawable());
		ground.setTranslationX(prop.screenW-prop.screenW*4/10);
		ground.setTranslationY(prop.screenH*4/10);
		slots[5]=new SlotsPos(prop.screenW-prop.screenW*4/10,
							  prop.screenH*4/10);
		ground.setTranslationZ(9999);
		ground.setAlpha(192);
		ground.setLayoutParams(params4);
		ground.setOnTouchListener(tA5);
		inven.addView(ground);
		ImageView sword=new ImageView(prop.context);
		sword.setImageResource(R.drawable.sword_01c);
		sword.setTranslationX(ground.getTranslationX()+4.5f);
		sword.setTranslationY(ground.getY()+4.5f);
		sword.setTranslationZ(9999);
		sword.setImageAlpha(48);
		sword.setLayoutParams(params5);
		inven.addView(sword);
	}
	void slot_6(){
		ImageView ground=new ImageView(prop.context);
		ground.setImageDrawable(in[0][0].getDrawable());
		ground.setTranslationX(prop.screenW-prop.screenW*2/10);
		ground.setTranslationY(prop.screenH*2/10)
			;slots[6]=new SlotsPos(prop.screenW-prop.screenW*2/10,
								   prop.screenH*2/10);
		ground.setTranslationZ(9999);
		ground.setAlpha(192);
		ground.setLayoutParams(params4);
		ground.setOnTouchListener(tA6);
		inven.addView(ground);
		ImageView ring=new ImageView(prop.context);
		ring.setImageResource(R.drawable.ring_01c);
		ring.setTranslationX(ground.getTranslationX()+4.5f);
		ring.setTranslationY(ground.getY()+4.5f);
		ring.setTranslationZ(9999);
		ring.setImageAlpha(48);
		ring.setLayoutParams(params5);
		inven.addView(ring);
	}
	
	void slot_7(){
		ImageView ground=new ImageView(prop.context);
		ground.setImageDrawable(in[0][0].getDrawable());
		ground.setTranslationX(prop.screenW-prop.screenW*4/10);
		ground.setTranslationY(prop.screenH*2/10);
		slots[7]=new SlotsPos(prop.screenW-prop.screenW*4/10,
							  prop.screenH*2/10);
		ground.setTranslationZ(9999);
		ground.setAlpha(192);
		ground.setLayoutParams(params4);
		ground.setOnTouchListener(tA7);
		inven.addView(ground);
		ImageView necklace=new ImageView(prop.context);
		necklace.setImageResource(R.drawable.necklace_01c);
		necklace.setTranslationX(ground.getTranslationX()+4.5f);
		necklace.setTranslationY(ground.getY()+4.5f);
		necklace.setTranslationZ(9999);
		necklace.setImageAlpha(48);
		necklace.setLayoutParams(params5);
		inven.addView(necklace);
	}
	
	
	Item tmpItem;
	
	OnTouchListener tA0=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isHelmet)return true;
					if(armItems[0]!=null)return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[0]=item;
					item.armSlot=0;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA1=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			if(p2.getAction()==MotionEvent.ACTION_UP){
				if(item==null)return true;
				if(ground.getVisibility()==View.INVISIBLE)return true;
				if(!item.isArmor)return true;
				if(armItems[1]!=null) return true;
				z[item.slotX][item.slotY]=true;
				it.setLayoutParams(params5);
				it.setX(p1.getX()+3);
				it.setY(p1.getY()+3);
				ground.setLayoutParams(params4);
				ground.setX(p1.getX());
				ground.setY(p1.getY());
				ground.setVisibility(View.INVISIBLE);
				setItem((ImageView)p1,0);
				items.remove(item);
				armItems[1]=item;
				item.armSlot=1;
				it.setOnTouchListener(aT);
				prop.onUi(r5);
			}
			return true;
		}
	};
	
	OnTouchListener tA2=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isBoots)return true;
					if(armItems[2]!=null) return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[2]=item;
					item.armSlot=2;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			return true;
		}
	};
	
	OnTouchListener tA3=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isGloves)return true;
					if(armItems[3]!=null) return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[3]=item;
					item.armSlot=3;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			return true;
		}
	};
	
	OnTouchListener tA4=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isShield)return true;
					if(armItems[4]!=null) return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[4]=item;
					item.armSlot=4;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			return true;
		}
	};
	
	OnTouchListener tA5=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isSword)return true;
					if(armItems[5]!=null) return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[5]=item;
					item.armSlot=5;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			return true;
		}
	};
	
	OnTouchListener tA6=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isRing)return true;
					if(armItems[6]!=null) return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[6]=item;
					item.armSlot=6;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			return true;
		}
	};
	
	OnTouchListener tA7=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
				if(p2.getAction()==MotionEvent.ACTION_UP){
					if(item==null)return true;
					if(ground.getVisibility()==View.INVISIBLE)return true;
					if(!item.isNecklace)return true;
					if(armItems[7]!=null) return true;
					z[item.slotX][item.slotY]=true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					ground.setVisibility(View.INVISIBLE);
					setItem((ImageView)p1,0);
					items.remove(item);
					armItems[7]=item;
					item.armSlot=7;
					it.setOnTouchListener(aT);
					prop.onUi(r5);
				}
			return true;
		}
	};
}
