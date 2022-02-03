package sevash.testx;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.view.*;
import android.widget.ImageView.*;
import java.util.*;

public class Inventory
{
	RelativeLayout playerAndUi;
	main_properties prop;
	public ImageView inv;
	RelativeLayout inven;
	public boolean isOpen=false;
	LayoutParams params=new LayoutParams(96,96);
	LayoutParams params2=new LayoutParams(90,90);
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	LayoutParams params4=new LayoutParams(96+48,96+48);
	LayoutParams params5=new LayoutParams(90+45,90+45);
	Boolean[][] z=new Boolean[10][5];
	Item[] armItems=new Item[8];
	List items=new ArrayList<Item>();
	List itemsP=new ArrayList<ImageView>();
	List armorL=new ArrayList<ImageView>();
	RelativeLayout lay;
	ImageView ground;
	ImageView[][] in=new ImageView[10][10];

	int lastSlot=0;

	Inventory(main_properties prop){
		this.prop=prop;
		inv=new ImageView(prop.context);
		inv.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.wood_01a,prop.options)));
		inv.setLayoutParams(params);
		inv.setScaleType(ScaleType.FIT_XY);
		inv.setTranslationX(prop.screenW-250);
		inv.setTranslationY(prop.screenH/8f);
		inv.setTranslationZ(2);
		inv.setOnTouchListener(touch);
		inven=new RelativeLayout(prop.context);
		inven.setTranslationZ(1);
		inven.setVisibility(View.INVISIBLE);
		inven.setBackgroundColor(Color.argb(64,0,0,0));
		inven.setOnTouchListener(t2);
		ground=new ImageView(prop.context);
		ground.setImageResource(R.drawable.cellbig_02);
		ground.setTranslationX(0);
		ground.setTranslationY(0);
		ground.setTranslationZ(9999+1);
		ground.setVisibility(View.INVISIBLE);
		ground.setAlpha(216);
		ground.setLayoutParams(params);
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
				inven.addView(in[i][j],params);
			}
		}
		slot_0();
		slot_1();
		slot_2();
		slot_3();
		slot_4();
		slot_5();
		slot_6();
		slot_7();
		
		prop.setInventory(this);
		
		prop.playerAndUi.addView(inv);
		prop.playerAndUi.addView(inven,params3);
		
	}

	OnTouchListener t2=new OnTouchListener(){

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
			try{
				if(prop.stage.getStage_in_world()==Game_stage.PAUSE) return false;
				switch (p2.getAction()){
					
					case MotionEvent.ACTION_UP:{
						if(inven.getVisibility()==View.INVISIBLE){
							if(prop.shop.isOpen)break;
							prop.vanishGameButtons();
							inven.setVisibility(View.VISIBLE);
							isOpen=true;
							}
						else{
							closeInventory();
							 }
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
	public void closeInventory(){
		prop.activity.runOnUiThread(run);
	}
	
	
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			prop.showGameButtons();
			inven.setVisibility(View.INVISIBLE);
			isOpen=false;
			// TODO: Implement this method
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
	public boolean addItem(String from, Item item){
		if(!freeSlot())return false;
		if(prop.money.money_count-item.price<0&&from.contentEquals("shop"))return false;
		if(from.contentEquals("shop"))prop.money.addMoney(-item.price);
		ivv=new ImageView(prop.context);
		ivv.setImageDrawable(item.picture.getDrawable());
		ivv.setLayoutParams(params2);
		ivv.setX(203+106*x);
		ivv.setY(203+106*y);
		ivv.setZ(10000);
		ivv.setOnTouchListener(t1);
		items.add(new Item(prop,"inv",item.id,item.pictureInt,ivv,item.price,item.name,item.description,item.dat));
		z[x][y]=false;
		itemsP.add(ivv);
		prop.activity.runOnUiThread(r1);
		return true;
	}
 
	Runnable r1=new Runnable(){

		@Override
		public void run()
		{
			inven.addView(ivv);
		}
 };
 ImageView it;
 Item item;
	void setItem(ImageView t){
		item=findItem(prop,t,items,item);
		it=item.picture;
	}
	
	static Item findItem(main_properties prop,ImageView pic,List items,Item im){
		int i=0;
		for(Item item:items){
			if(item.picture==pic){
		
				return item;
			}
			i++;
		}
		return null;
	}
	
	
	
	View pp=null;
	OnTouchListener t1=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
			if(p2.getAction()==MotionEvent.ACTION_UP){
				
				pp=p1;
				setItem((ImageView)p1);
				prop.activity.runOnUiThread(r2);
			}
				}catch(Exception e){
					files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				}
			return true;
			
		}
	};
	
	OnTouchListener t4=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					pp=p1;
					setItem((ImageView)p1);
					prop.activity.runOnUiThread(r2);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return false;

		}
	};
	
	Runnable r2=new Runnable(){

		@Override
		public void run()
		{
			ground.setLayoutParams(params);
			ground.setVisibility(View.VISIBLE);
			ground.setX(pp.getX());
			ground.setY(pp.getY());
		}	
	};
 
 void slot_0(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*3/10);
	 ground.setTranslationY(prop.screenH*2/10);
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
		ground.setTranslationY(prop.screenH*2/10);
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
	
	OnTouchListener tA0=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isHelmet)return true;
					if(armItems[0]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[0]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
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
			try{
			if(p2.getAction()==MotionEvent.ACTION_UP){
				
				if(!item.isArmor)return true;
				if(armItems[1]!=null) return true;
				it.setLayoutParams(params5);
				it.setX(p1.getX()+3);
				it.setY(p1.getY()+3);
				ground.setLayoutParams(params4);
				ground.setX(p1.getX());
				ground.setY(p1.getY());
				armItems[1]=findItem(prop,it,items,item);
				it.setOnTouchListener(null);
			}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA2=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isBoots)return true;
					if(armItems[2]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[2]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA3=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isGloves)return true;
					if(armItems[3]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[3]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA4=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isShield)return true;
					if(armItems[4]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[4]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA5=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isSword)return true;
					if(armItems[5]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[5]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA6=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isRing)return true;
					if(armItems[6]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[6]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
	
	OnTouchListener tA7=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			try{
				if(p2.getAction()==MotionEvent.ACTION_UP){

					if(!item.isNecklace)return true;
					if(armItems[7]!=null) return true;
					it.setLayoutParams(params5);
					it.setX(p1.getX()+3);
					it.setY(p1.getY()+3);
					ground.setLayoutParams(params4);
					ground.setX(p1.getX());
					ground.setY(p1.getY());
					armItems[7]=findItem(prop,it,items,item);
					it.setOnTouchListener(null);
				}
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
			}
			return true;
		}
	};
}