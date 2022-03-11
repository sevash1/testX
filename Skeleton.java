package sevash.livingSword;
import java.util.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.app.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import sevash.livingSword.*;
import android.util.*;
import android.view.*;

public class Skeleton 
{
	
	main_properties prop;
	LayoutParams params=new LayoutParams(300,300);
	LayoutParams params2=new LayoutParams(200,6);
	RelativeLayout lay;
	ImageView iv;
	ImageView hp;
	Thread th;
	int anim_etap=0;
	public float posX=0;
	public float posY=0;
	public float max_health=3;
	float health=3;
	List anim;
	float lengthX;
	float lengthY;
	float length;
	public boolean isLife=true;
	Random rand=new Random();
	int minExp=2;
	int maxExp=5;
	float moveSpeed=0.1f;
	float tX=0;
	float tY=0;
	float rX=0;
	float rY=0;
	float posHpX=0;
	float posHpY=0;
	float alignHpX=0;
	
	Skeleton(main_properties prop,float posX,float posY){
		this.prop=prop;
		lay=new RelativeLayout(prop.context);
		iv=new ImageView(prop.context);
		hp=new ImageView(prop.context);
		iv.setPivotX(75);
		iv.setPivotY(75);
		//iv.setScaleType(ScaleType.FIT_XY);
		iv.setImageBitmap((Bitmap)prop.mob.anim_idle.get(0));
		this.posX=posX+prop.screenW/2-150f;
		this.posY=posY+prop.screenH/2-150f;
		iv.setTranslationX(this.posX);
		iv.setTranslationY(this.posY);
		iv.setLayoutParams(params);
		hp.setImageBitmap(prop.mob.hpBar);
	    hp.setLayoutParams(params2);
		hp.setScaleType(ScaleType.FIT_XY);
		alignHpX=iv.getLayoutParams().width/2-hp.getLayoutParams().width/2;
		hp.setTranslationX(iv.getTranslationX()+alignHpX);
		hp.setTranslationY(iv.getTranslationY());
		hp.setZ(12f);
		lay.addView(iv);
		lay.addView(hp);
		th=new Thread(run2);
		prop.activity.runOnUiThread(run6);
		prop.forAdd.add(this);
	}
	
	public void update(){
		
		lay.setScrollX((int)(prop.playerPosX));
		lay.setScrollY((int)(prop.playerPosY));
		
	}
	
	Runnable run6=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.addView(lay,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		    th.start();
		}	
	};
	
	
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			iv.setX(posX);
			iv.setY(posY);
			hp.setX(posHpX);
			hp.setY(posY);
			}
	};
	
	public void sendDamage(float count){
			if(!isLife)return;
	if(health-count<0)health=0;
	else
		health=health-count;
		checkHealth();
	}

	
	void checkHealth(){
			prop.activity.runOnUiThread(run0);
		if(health<=0){
			isLife=false;
			prop.menu.playerLevel.addExp((float)(rand.nextInt(maxExp-minExp)+minExp));
			//Money.drop(prop,"",iv.getX()+iv.getWidth()/2,iv.getY()+iv.getHeight()/2,1f+0.01f*prop.player.bonusGoldPercent);	
		//	new Thread(run10).start();
	        prop.onUi(run9);
			stop=false;
			new Skeleton(prop,rand.nextInt(2000)-1000,rand.nextInt(2000)-000);
			new Skeleton(prop,rand.nextInt(2000)-1000,rand.nextInt(2000)-000);
			new Skeleton(prop,rand.nextInt(2000)-1000,rand.nextInt(2000)-000);
			anim_etap=0;
			anim=prop.mob.anim_death;
			}
	}
	

	Runnable run0=new Runnable(){

		@Override
		public void run()
		{
			hp.setScaleX(health/max_health);
		}

		
	};
	
	Runnable run7=new Runnable(){

		@Override
		public void run()
		{
			iv.setImageBitmap((Bitmap)anim.get(anim_etap));
		}

		
	};
	
	Runnable run2=new Runnable(){

		@Override
		public void run()
		{
			
			anim=prop.mob.anim_idle;
			while(stop){
				if(Game_stage.EXIT==prop.stage.getStage())
					return;		
			
				try{
					Thread.sleep(250);
							if(prop.stage.getStage()==Game_stage.WORLD){
						
						if(rand.nextInt(10)==0){
							anim=prop.mob.anim_walk;
							lengthX=rand.nextInt(400)-200;
							lengthY=rand.nextInt(400)-200;
							length=(Math.abs(lengthX)+Math.abs(lengthY));
							rX=lengthX/length;
							rY=lengthY/length;
						}
						
					prop.activity.runOnUiThread(run7);
					if(anim==prop.mob.anim_death&&anim_etap>=anim.size()-2){
						return;
					}
					anim_etap++;
					if(anim_etap>anim.size()-1)anim_etap=0;
					
					}
				}catch(Exception e){e.printStackTrace();}
				
			}
			
		}

		
	};
	
	Runnable run9=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.removeView(lay);
		}


	};
boolean stop=true;
	Runnable run10=new Runnable(){

		@Override
		public void run()
		{
			
				try{
					Thread.sleep(10000);
				}catch(Exception e){
					
				}
						prop.activity.runOnUiThread(run9);
			stop=false;
			
				}
};
	

	
}
