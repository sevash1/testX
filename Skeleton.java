package sevash.testx;
import java.util.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.app.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import sevash.testx.*;
import android.util.*;
import android.view.*;

public class Skeleton extends Mob
{
	
	main_properties prop;
	LayoutParams params=new LayoutParams(300,300);
	LayoutParams params2=new LayoutParams(200,6);
	LayoutParams params6=new LayoutParams(48*2,48*2);
	ImageView iv;
	ImageView hp;
	Thread th;
	int anim_etap=0;
	public float playerPosX;
    public float playerPosY;
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
	ImageView money;
	public boolean isLife=true;
	
	Skeleton(main_properties prop,float posX,float posY){
		this.prop=prop;
		
		iv=new ImageView(prop.context);
		hp=new ImageView(prop.context);
		money=new ImageView(prop.context);
		money.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.coin_01b,prop.options)));
		money.setLayoutParams(params6);
		money.setVisibility(View.INVISIBLE);
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
		th.setDaemon(true);
		prop.activity.runOnUiThread(run6);
		prop.skeletons.add(this);
		
	}
	
	public void update(float x,float y){
		try{
		playerPosX=x;
		playerPosY=y;
		prop.activity.runOnUiThread(run1);
		}catch(Exception e){
			files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

		}
	}
	
	Runnable run6=new Runnable(){

		@Override
		public void run()
		{
			prop.world.addView(hp);
			prop.world.addView(iv,params);
			prop.world.addView(money);
			th.start();
		}	
	};
	
	
	Runnable run1=new Runnable(){

		@Override
		public void run()
		{
			try{
				
		//	iv.setTranslationX(posX-playerPosX);
		//	iv.setTranslationY(posY-playerPosY);
			
			hp.setTranslationX(iv.getTranslationX()+iv.getWidth()/2-hp.getWidth()/2);
			hp.setTranslationY(iv.getTranslationY()+30);
			}catch(Exception e){
			files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

		}
		}
	};
	
	public void sendDamage(float count){
		try{
			if(!isLife)return;
		health=health-count;
		checkHealth();
		}catch(Exception e){
		files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

	}
	}

	
	void checkHealth(){
		try{
			prop.activity.runOnUiThread(run0);
		if(health<=0){
			isLife=false;
			prop.skeletons.remove(this);
			Random rand=new Random();
			new Skeleton(prop,rand.nextInt(1000)-500,rand.nextInt(1000)-500);
			anim_etap=0;
			anim=anim_death;
			Thread th=new Thread(run3);
			th.setDaemon(true);
			th.start();
		}
		
		}catch(Exception e){
			files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

		}
	}
	
	
	@Override
	public int getMobId()
	{
		return Mob.SKELETON;
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
					Thread.currentThread().stop();
				
				
				try{
					Thread.sleep(250);
					
					if(prop.stage.getStage()==Game_stage.WORLD){
					prop.activity.runOnUiThread(run7);
					if(anim==anim_death&&anim_etap>=anim.size()-2){
						return;
					}
					anim_etap++;
					if(anim_etap>anim.size()-2)anim_etap=0;
					
					}
					
	
					
				}catch(Exception e){
					files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
					
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
		prop.world.removeView(money);
		}


	};

	Runnable run10=new Runnable(){

		@Override
		public void run()
		{
			
				try{
					Thread.sleep(10000);

						prop.activity.runOnUiThread(run9);
		

				}catch(Exception e){
					files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

				}
			}
};
	

	Runnable run3=new Runnable(){

		@Override
		public void run()
		{
			try{
			prop.activity.runOnUiThread(run4);
		new Thread(run10).start();
				x=iv.getTranslationX()+iv.getWidth()/2;
				y=iv.getTranslationY()+iv.getHeight()/2;
			lengthX=prop.screenW-48-30-x;
			lengthY=10-y;
		    length=(float)Math.sqrt(Math.pow(lengthX,2)+Math.pow(lengthY,2));
			ratioX=lengthX/length;
		    ratioY=lengthY/length;
			
			for(int s=ss;s<100;s++){
				try{
					if(money.getTranslationX()>prop.screenW-48-30
					   &&money.getTranslationY()<10){
					
						Thread.sleep(100);
						prop.activity.runOnUiThread(run8);
						s=100;
						   break;
						   }
					ss=s;
					Thread.sleep(15);
			prop.activity.runOnUiThread(run5);
				}catch(Exception e){
					files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

				}
				
			}
			prop.money.v();
				prop.money.addMoney(2.1f);
			}catch(Exception e){
			files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

		}
	
		}

		
	};
	
	Runnable run4=new Runnable(){

		@Override
		public void run()
		{
			try{
			money.setTranslationX(iv.getTranslationX()+iv.getWidth()/2);
			money.setTranslationY(iv.getTranslationY()+iv.getHeight()/2);
			money.setVisibility(View.VISIBLE);
			
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

			}
		}

		
	};
	Runnable run5=new Runnable(){

		@Override
		public void run()
		{
			try{
				money.setTranslationX(money.getTranslationX()+ratioX*ss);
				money.setTranslationY(money.getTranslationY()+ratioY*ss);
			}catch(Exception e){
				files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));

			}
		}

		
	};
	
	Runnable run8=new Runnable(){

		@Override
		public void run()
		{
			money.setVisibility(View.GONE);
			
		}

		
	};
}
