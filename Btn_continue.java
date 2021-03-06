package sevash.livingSword;
import android.widget.*;
import android.content.*;
import android.widget.RelativeLayout.*;
import android.graphics.*;
import android.widget.ImageView.*;
import android.view.*;

public class Btn_continue 
{
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView btn;
	LayoutParams params=new LayoutParams(210*2,60*2);
	TextView tv;
	main_properties prop;

	Btn_continue(main_properties prop){
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
		btn.setTranslationY(screenH/2-60*3);
		btn.setOnClickListener(click);
		tv.setText(prop.words.get(Words.words.CONTINUE));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(16);
		tv.setTranslationX(50);
		tv.setTranslationY(screenH/2-60*3);
		tv.setLayoutParams(params);
		tv.setTextColor(Color.YELLOW);
		tv.setShadowLayer(5,10,10,Color.argb(255,255,127,127));
		tv.setTypeface(prop.ttf);
		prop.bl.addView(btn);
		prop.btn_continue=this;
		prop.bl.addView(tv);
	
	}

	void reLang(){
		tv.setText(prop.words.get(Words.words.CONTINUE));

	}

	OnClickListener click=new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
			prop.sounds.sp.play(prop.sounds.s1,1f,1f,1,0,1f);
			
				prop.bl.setVisibility(View.GONE);
				prop.stage.setStage_in_world(Game_stage.NOT_PAUSE);
				prop.joystick.lay.setVisibility(View.VISIBLE);
			}
	};
	
	
	
}
