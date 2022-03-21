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
	LayoutParams params3=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	RelativeLayout layForg;
	RelativeLayout layTextures;
	ImageView hp;
	int anim_etap=0;
	public float max_health=3;
	float health=3;
	float lengthX;
	float lengthY;
	float length;
	public boolean isLife=true;
	int minExp=2;
	int maxExp=5;
	float moveSpeed=0.1f;
	float tX=0;
	float tY=0;
	float rX=0;
	float rY=0;
	float alignHpX=0;
	float posX=0;
	float posY=0;
	boolean stop=false;
	boolean f=true;
	int scrX=0;
	int scrY=0;
	
	Skeleton(main_properties prop,float posX,float posY){
		this.prop=prop;
		this.posX=posX;
		this.posY=posY;
		prop.mob.anim_load();
		scrX=(int)(prop.playerPosX+posX);
		scrY=(int)(prop.playerPosY+posY);
		
		layForg=new RelativeLayout(prop.context);
		layForg.setLayoutParams(params3);
		layForg.setScrollX(scrX);
		layForg.setScrollY(scrY);
		
		layTextures=new RelativeLayout(prop.context);
		layTextures.setLayoutParams(params);
		layTextures.setX(prop.screenW/2-150);
		layTextures.setY(prop.screenH/2-150);
		layTextures.addView(prop.mob.idle);
		layTextures.addView(prop.mob.move);
		layTextures.addView(prop.mob.death);
		
		hp=new ImageView(prop.context);
		hp.setImageBitmap(prop.mob.hpBar);
	    hp.setLayoutParams(params2);
		hp.setScaleType(ScaleType.FIT_XY);
		alignHpX=layTextures.getLayoutParams().width/2-hp.getLayoutParams().width/2;
		hp.setX(layTextures.getX()+alignHpX);
		hp.setY(layTextures.getY());
		hp.setZ(12f);
		layForg.addView(layTextures);
		layForg.addView(hp);
		prop.activity.runOnUiThread(run6);
		prop.forAdd.add(this);
		prop.forAddRuns.add(run2);
	}
	
	public void update(){
		if(isLife){
	     	posX+=rX;
		    posY+=rY;
		}
		scrX=(int)(prop.playerPosX+posX);
		scrY=(int)(prop.playerPosY+posY);
		
		layForg.setScrollX(scrX);
		layForg.setScrollY(scrY);
		if(stop&&f){
		prop.activity.runOnUiThread(run9);
		f=false;
		}
	}
	
	Runnable run6=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.addView(layForg,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
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
		if(health<=0&&isLife){
			isLife=false;
			prop.menu.playerLevel.addExp((float)(prop.rand.nextInt(maxExp-minExp)+minExp));
			deathCoin(1f+0.01f*prop.player.bonusGoldPercent,
			          layTextures.getX()+150,
					  layTextures.getY()+150);	
			prop.forAddRuns.add(run10);
			new Skeleton(prop,prop.rand.nextInt(2000)-1000,prop.rand.nextInt(1000)-000);
			new Skeleton(prop,prop.rand.nextInt(2000)-1000,prop.rand.nextInt(1000)-000);
			new Skeleton(prop,prop.rand.nextInt(2000)-1000,prop.rand.nextInt(1000)-000);
			anim_etap=0;
			layTextures.setScrollY(600);
			}
	}
	

	Runnable run0=new Runnable(){

		@Override
		public void run()
		{
			hp.setScaleX(health/max_health);
		}

		
	};
	long time1=0;
	
	Runnable run2=new Runnable(){

		@Override
		public void run()
		{
			if(prop.stage.getStage()==Game_stage.WORLD){
				time1+=prop.deltaTime;
				if(time1>250){
					time1=0;
					if(prop.rand.nextInt(10)==0&&isLife){
						layTextures.setScrollY(300);
						lengthX=prop.rand.nextInt(400)-200;
						lengthY=prop.rand.nextInt(400)-200;
						length=(Math.abs(lengthX)+Math.abs(lengthY));
						rX=lengthX/length;
						rY=lengthY/length;
					}
					layTextures.setScrollX(anim_etap*300);
					if(layTextures.getScrollY()==600&&anim_etap>=3){
						return;
					}
					anim_etap++;
					if(anim_etap>3)anim_etap=0;
				}
			}
		}
	};
	
	long time10=0;
	Runnable run10=new Runnable(){

		@Override
		public void run()
		{
			if(prop.stage.getStage()==Game_stage.WORLD){
				time10+=prop.deltaTime;
				if(time10>10000){
					time10=0;		
					stop=true;
					prop.forRemoveRuns.add(run2);
					prop.forRemoveRuns.add(run10);
					prop.forRemove.add(this);
				}
			}
		}
	};
	
	Runnable run9=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.removeView(layForg);
			prop.playerAndUi.removeView(pic1);
			
		}

	};
	
	ImageView pic1;
	float coinPosX=0,coinPosY=0;
	float endX=0;
	float coinRX=0;
	float coinRY=0;
	
	void deathCoin(float count, float x, float y){
		prop.money.addMoney(count);
		pic1=new ImageView(prop.context);
		pic1.setLayoutParams(params);
		pic1.setX(x);
		pic1.setY(y);
		pic1.setScaleType(ScaleType.FIT_XY);
		pic1.setImageResource(R.drawable.coin_01d);
		pic1.setLayoutParams(new LayoutParams(96,96));
		coinPosX=pic1.getX();
		coinPosY=pic1.getY();
		endX=prop.screenW;
		coinRX=(endX-coinPosX)/100;
		coinRY=(-coinPosY)/100;
		prop.forAddRuns.add(r4);
	}
	
	
	long time4=0;
	int t=0;
	Runnable r4=new Runnable(){

		@Override
		public void run()
		{
			if(prop.stage.getStage()==Game_stage.WORLD){
				time4+=prop.deltaTime;
				if(time4>10){
					time4-=10;
					t++;
					coinPosX+=coinRX;
				    coinPosY+=coinRY;
					prop.onUi(r5);
				}
				if(t>100){
				prop.money.showMoney();
				prop.forRemoveRuns.add(r4);
				}
			}
		}
	};
	
	Runnable r5=new Runnable(){

		@Override
		public void run()
		{
			pic1.setX(coinPosX);
			pic1.setY(coinPosY);
		}
	};

	Runnable r6=new Runnable(){

		@Override
		public void run()
		{
			prop.playerAndUi.removeView(pic1);
			
			prop.playerAndUi.addView(pic1);
		}
	};
	
	
	
}
