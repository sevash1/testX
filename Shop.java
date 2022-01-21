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
	float y=0;
	List items=new ArrayList<ImageView>();
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	public boolean isOpen=false;
	ImageView[][] in=new ImageView[10][35];
	ImageView temp;
	Shop(main_properties prop){
		this.prop=prop;
	shop=new RelativeLayout(prop.context);
	shop.setTranslationZ(10);
	shop.setVisibility(View.INVISIBLE);
	shop.setBackgroundColor(Color.argb(64,0,0,0));
	shop.setOnTouchListener(touch);
	load_items();
	int f=0;
		for(int i=0;i<10;i++){
			for(int j=0;j<35;j++){
				in[i][j]=new ImageView(prop.context);
				in[i][j].setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.cell01,prop.options)));
				in[i][j].setTranslationX(200+i*106);
				in[i][j].setTranslationY(200+j*106);
				in[i][j].setTranslationZ(9999);
				in[i][j].setAlpha(192);
				shop.addView(in[i][j],params);
			
			}
		}
		for(int i=0;i<35;i++){
			for(int j=0;j<10;j++){
				((ImageView)items.get(f)).setTranslationX(200+3+j*106);
				((ImageView)items.get(f)).setTranslationY(200+3+i*106);
				((ImageView)items.get(f)).setTranslationZ(99999);
				shop.addView((ImageView)items.get(f),params1);
				f++;
				}
				}
	
	prop.setShop(this);
		prop.playerAndUi.addView(shop,params3);
	}
	
	
	public void openShop(){
		prop.activity.runOnUiThread(run);
	}
	Runnable run=new Runnable(){

		@Override
		public void run()
		{
			shop.setVisibility(View.VISIBLE);
			isOpen=true;
			// TODO: Implement this method
		}


	};
	OnTouchListener touch=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			switch (p2.getAction()){
				case MotionEvent.ACTION_DOWN:{
						y=shop.getScrollY()+p2.getY();
					break;
				}
				case MotionEvent.ACTION_MOVE:{
					shop.setScrollY((int)(y-p2.getY()));
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
}
