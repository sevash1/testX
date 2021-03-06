package sevash.livingSword;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import android.view.*;

public class Btn_play 
{
	RelativeLayout menu;
	Context context;
	float screenW=0;
	float screenH=0;
	ImageView btn;
	LayoutParams params=new LayoutParams(210*4,60*4);
	TextView tv;
	main_properties prop;
	
	Btn_play(main_properties prop){
		this.prop=prop;
			this.menu=prop.menuLayout;
		this.context=prop.context;
		this.screenW=prop.screenW;
		this.screenH=prop.screenH;
		btn=new ImageView(prop.context);
		tv=new TextView(prop.context);
		btn.setImageResource(R.drawable.btn_v20);
		btn.setLayoutParams(params);
		btn.setScaleType(ScaleType.FIT_XY);
		btn.setTranslationX((screenW/2)-105*4);
		btn.setTranslationY(screenH-75*5);
		btn.setOnClickListener(click);
		tv.setText(prop.words.get(Words.words.PLAY));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(32);
		tv.setTranslationX(btn.getX());
		tv.setTranslationY(btn.getY());
		tv.setLayoutParams(params);
		tv.setTextColor(Color.YELLOW);
		tv.setTypeface(prop.ttf);
		prop.Btn_play=this;
			prop.menuLayout.addView(btn);
			prop.menuLayout.addView(tv);
		
	}
	
	void reLang(){
		tv.setText(prop.words.get(Words.words.PLAY));
		}
		
	
	OnClickListener click=new OnClickListener(){

		@Override
		public void onClick(View p1)
		{
				if(!prop.stage.world_load_complete)return;
				prop.stage.setStage(Game_stage.WORLD);
			    prop.sounds.sp.play(prop.sounds.s1,1f,1f,1,0,1f);
				prop.stage.setStage_in_world(Game_stage.NOT_PAUSE);
				prop.joystick.lay.setVisibility(View.VISIBLE);
			    prop.inv.switchToWorld();
				prop.money.setType(Money.Type.WORLD);
			    prop.playerAndUi.setVisibility(View.VISIBLE);
			    prop.menuLayout.setVisibility(View.GONE);
				}
	};
}
