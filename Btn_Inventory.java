package sevash.testx;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.view.*;
import android.widget.ImageView.*;

public class Btn_Inventory
{
	RelativeLayout playerAndUi;
	main_properties prop;
	public ImageView inv;
	RelativeLayout inven;
	public boolean isOpen=false;
	LayoutParams params=new LayoutParams(96,96);
	LayoutParams params2=new LayoutParams(96,96);
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	LayoutParams params4=new LayoutParams(96+48,96+48);
	LayoutParams params5=new LayoutParams(90+45,90+45);
	
	RelativeLayout lay;
	
	ImageView[][] in=new ImageView[10][10];

	Btn_Inventory(main_properties prop){
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
		
		for(int i=0;i<10;i++){
			for(int j=0;j<5;j++){
				in[i][j]=new ImageView(prop.context);
				in[i][j].setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.cell01,prop.options)));
			in[i][j].setTranslationX(200+i*106);
				in[i][j].setTranslationY(200+j*106);
				in[i][j].setTranslationZ(9999);
				in[i][j].setAlpha(192);
				inven.addView(in[i][j],params);
			}
		}
		slot_0();
		slot_1();
		slot_2();
		slot_3();
		//slot_4();
		
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
							prop.showGameButtons();
							 inven.setVisibility(View.INVISIBLE);
							isOpen=false;
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
			inven.setVisibility(View.INVISIBLE);
			isOpen=false;
			// TODO: Implement this method
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
	 inven.addView(ground);
	 ImageView arm=new ImageView(prop.context);
	 arm.setImageResource(R.drawable.armor_01c);
	 arm.setTranslationX(ground.getTranslationX()+4.5f);
	 arm.setTranslationY(ground.getY()+4.5f);
	 arm.setTranslationZ(9999);
	 arm.setImageAlpha(48);
	 arm.setLayoutParams(params5);
	 inven.addView(arm);
 }
 void slot_2(){
	 ImageView ground=new ImageView(prop.context);
	 ground.setImageDrawable(in[0][0].getDrawable());
	 ground.setTranslationX(prop.screenW-prop.screenW*3/10);
	 ground.setTranslationY(prop.screenH*6/10);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
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
	 ground.setTranslationX(prop.screenW-prop.screenW*2/6);
	 ground.setTranslationY(prop.screenH/6);
	 ground.setTranslationZ(9999);
	 ground.setAlpha(192);
	 ground.setLayoutParams(params4);
	 inven.addView(ground);
	 ImageView arm=new ImageView(prop.context);
	 arm.setImageResource(R.drawable.armor_01c);
	 arm.setTranslationX(ground.getTranslationX()+4.5f);
	 arm.setTranslationY(ground.getY()+4.5f);
	 arm.setTranslationZ(9999);
	 arm.setImageAlpha(48);
	 arm.setLayoutParams(params5);
	 inven.addView(arm);
 }
 
}
