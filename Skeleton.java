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

public class Skeleton extends Mob
{
	
	main_properties prop;
	LayoutParams params=new LayoutParams(300,300);
	LayoutParams params2=new LayoutParams(200,6);
		ImageView iv;
	ImageView hp;
	Thread th;
	int anim_etap=0;
	public float posX=0;
	public float posY=0;
	public float max_health=3;
	float health=3;
	List anim;
	int ss=0;
	float x;
	float y;
	float lengthX;
	float lengthY;
	float 	length;
	float ratioX;
	float ratioY;
	public boolean isLife=true;
	Random rand=new Random();
	int minExp=2;
	int maxExp=5;
	
	Skeleton(main_properties prop,float posX,float posY){
		this.prop=prop;
		
		iv=new ImageView(prop.context);
		hp=new ImageView(prop.context);
			anim_idle();
		iv.setPivotX(75);
		iv.setPivotY(75);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setImageBitmap((Bitmap)anim_idle.get(0));
		this.posX=posX+prop.screenW/2-150;
		this.posY=posY+prop.screenH/2-150;
		iv.setTranslationX(this.posX);
		iv.setTranslationY(this.posY);
		hp.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options)));
	    hp.setLayoutParams(params2);
		hp.setScaleType(ScaleType.FIT_XY);
		hp.setTranslationX(iv.getTranslationX()+iv.getWidth()/2-hp.getWidth()/2);
		hp.setTranslationY(iv.getTranslationY());
		hp.setZ(12);
		
		th=new Thread(run2);
		prop.activity.runOnUiThread(run6);
		prop.skeletons.add(this);
		
	}
	
	public void update(){
		prop.activity.runOnUiThread(run1);	
	}
	
	Runnable run6=new Runnable(){

		@Override
		public void run()
		{
			prop.world.addView(hp);
			prop.world.addView(iv,params);
		    th.start();
		}	
	};
	
	
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			hp.setTranslationX(iv.getTranslationX()+iv.getWidth()/2-hp.getWidth()/2);
			hp.setTranslationY(iv.getTranslationY()+30);
			}
	};
	
	public void sendDamage(float count){
			if(!isLife)return;
		health=health-count;
		checkHealth();
	}

	
	void checkHealth(){
			prop.activity.runOnUiThread(run0);
		if(health<=0){
			prop.skeletons.remove(this);
			isLife=false;
			prop.menu.playerLevel.addExp((float)(rand.nextInt(maxExp-minExp)+minExp));
			Money.drop(prop,"",iv.getX()+iv.getWidth()/2,iv.getY()+iv.getHeight()/2,1f);	
			new Thread(run10).start();
			new Skeleton(prop,rand.nextInt(1000)-500,rand.nextInt(1000)-500);
			anim_etap=0;
			anim=anim_death;
			}
	}

	
	public void anim_idle()
	{
		
		
		List l=anim_idle;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_idle,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_idle,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_idle,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_idle,prop.options),450,0,150,150));
		
		 l=anim_walk;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_walk,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_walk,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_walk,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_walk,prop.options),450,0,150,150));
		
		 l=anim_takehit;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_takehit,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_takehit,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_takehit,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_takehit,prop.options),450,0,150,150));
		
		 l=anim_shield;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_shield,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_shield,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_shield,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_shield,prop.options),450,0,150,150));
		
		 
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_death,prop.options),0,0,150,150));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_death,prop.options),150,0,150,150));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_death,prop.options),300,0,150,150));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_death,prop.options),450,0,150,150));
		
		 l=anim_attack;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),450,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),600,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),750,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),900,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.skeleton_attack,prop.options),1050,0,150,150));
		
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
			anim=anim_idle;
			while(true){
				if(Game_stage.EXIT==prop.stage.getStage())
					return;
				
				
				try{
					Thread.sleep(250);
				}catch(Exception e){continue;}
					if(prop.stage.getStage()==Game_stage.WORLD){
					prop.activity.runOnUiThread(run7);
					if(anim==anim_death&&anim_etap>=anim.size()-2){
						return;
					}
					anim_etap++;
					if(anim_etap>anim.size()-2)anim_etap=0;
					
					}
			}
			
		}

		
	};
	
	Runnable run9=new Runnable(){

		@Override
		public void run()
		{
			prop.world.removeView(hp);
		prop.world.removeView(iv);
		}


	};

	Runnable run10=new Runnable(){

		@Override
		public void run()
		{
			
				try{
					Thread.sleep(10000);
				}catch(Exception e){
					
				}
						prop.activity.runOnUiThread(run9);
			}
};
	

	
}
