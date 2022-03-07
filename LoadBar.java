package sevash.livingSword;
import android.widget.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.widget.RelativeLayout.*;
import android.view.*;

public class LoadBar
{
	ImageView bar;
	TextView tv;
	main_properties prop;
	int max_points=37;
	float points=1;
	LayoutParams params2=new LayoutParams(800,20);
	
	LoadBar(main_properties prop){
		this.prop=prop;
		bar=new ImageView(prop.context);
		tv=new TextView(prop.context);
	bar.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options)));
	bar.setLayoutParams(params2);
	bar.setScaleType(ScaleType.FIT_XY);
	bar.setTranslationX(prop.screenW/2-400);
	bar.setTranslationY(100);
		tv.setText("0 / "+String.valueOf( max_points));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(6);
		tv.setTranslationX(prop.screenW/2-400);
		tv.setTranslationY(100);
		tv.setLayoutParams(params2);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		prop.menuLayout.addView(bar);
		prop.menuLayout.addView(tv);
		prop.setLoadBar(this);
}

	Runnable run0=new Runnable(){

		@Override
		public void run()
		{
			tv.setText(String.valueOf((int)points)+"    /    "+String.valueOf(max_points));
			bar.setScaleX(points/max_points);
		}

	};
	
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			tv.setText("з а г р у ж е н о");
			
		}

	};
	
	
	Runnable run2=new Runnable(){

		@Override
		public void run()
		{
	
			try{
				prop.activity.runOnUiThread(run1);
				Thread.sleep(2500);
				prop.activity.runOnUiThread(run3);
			}catch(Exception ignore){}
		}

	};
	
	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			tv.setVisibility(View.GONE);
			bar.setVisibility(View.GONE);

		}

	};
	
	public void addPoint(){
		points++;
		prop.activity.runOnUiThread(run0);
		if(points!=max_points)return;
		prop.loadBar=null;
		new Thread(run2).start();
	}
	
}
