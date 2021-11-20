package sevash.testx;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.view.*;
import android.widget.ImageView.*;

public class Btn_exit_menu extends Ui
{
	RelativeLayout world;
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView btn;
	LayoutParams params=new LayoutParams(210*2,60*2);
	TextView tv;
	main_properties prop;
	Thread worldThread;

	Btn_exit_menu(main_properties prop){
		this.prop=prop;
		this.world=prop.menu;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		this.worldThread=prop.worldThread;
		btn=new ImageView(prop.context);
		tv=new TextView(prop.context);
		btn.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.btns_background,prop.options)));
		btn.setLayoutParams(params);
		btn.setScaleType(ScaleType.FIT_XY);
		btn.setTranslationX(50);
		btn.setTranslationY(screenH/2);
		btn.setOnClickListener(click);
		tv.setText(" Выйти в меню ");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(16);
		tv.setTranslationX(50);
		tv.setTranslationY(screenH/2);
		tv.setLayoutParams(params);
		tv.setTextColor(Color.BLUE);
		tv.setTypeface(prop.ttf);
		prop.bl.addView(btn);
		prop.bl.addView(tv);
	}

	

	OnClickListener click=new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
			try{
				prop.stage.setStage(Game_stage.MENU);
				prop.menu.setVisibility(View.VISIBLE);
				prop.world.setVisibility(View.GONE);
				prop.bl.setVisibility(View.GONE);
				prop.money.setType(Money.Type.MENU);
			}catch(Exception e){
				files.writeFile(prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				

			}
		}


	};
}
