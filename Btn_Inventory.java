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
		inv.setTranslationZ(1023);
		inv.setOnTouchListener(touch);
		inven=new RelativeLayout(prop.context);
		inven.setTranslationZ(10);
		inven.setVisibility(View.INVISIBLE);
		inven.setBackgroundColor(Color.argb(64,0,0,0));
		
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
		prop.setInventory(this);
		prop.playerAndUi.addView(inv);
		prop.playerAndUi.addView(inven,params3);

	}

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
							inven.setVisibility(View.VISIBLE);
							isOpen=true;
							}
						else{
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
}
