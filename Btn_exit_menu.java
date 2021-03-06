package sevash.livingSword;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.view.*;
import android.widget.ImageView.*;

public class Btn_exit_menu 
{
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView btn;
	LayoutParams params=new LayoutParams(210*2,60*2);
	TextView tv;
	main_properties prop;

	Btn_exit_menu(main_properties prop){
		this.prop=prop;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		btn=new ImageView(prop.context);
		tv=new TextView(prop.context);
		btn.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.btns_background,prop.options)));
		btn.setLayoutParams(params);
		btn.setScaleType(ScaleType.FIT_XY);
		btn.setTranslationX(50);
		btn.setTranslationY(screenH/2);
		btn.setOnClickListener(click);
		tv.setText(prop.words.get(Words.words.EXIT_MENU));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(16);
		tv.setTranslationX(50);
		tv.setTranslationY(screenH/2);
		tv.setLayoutParams(params);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		prop.Btn_exit_menu=this;
		prop.bl.addView(btn);
		prop.bl.addView(tv);
	}

	void reLang(){
		tv.setText(prop.words.get(Words.words.EXIT_MENU));

	}

	OnClickListener click=new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
			prop.sounds.sp.play(prop.sounds.s1,1f,1f,1,0,1f);
			
				prop.stage.setStage(Game_stage.MENU);
				prop.menuLayout.setVisibility(View.VISIBLE);
				prop.playerAndUi.setVisibility(View.GONE);
				prop.bl.setVisibility(View.GONE);
				prop.money.setType(Money.Type.MENU);
				prop.inv.switchToMenu();
		}


	};
}
