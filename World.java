package sevash.testx;
import android.widget.*;
import android.graphics.*;
import java.util.*;
import android.widget.RelativeLayout.*;
import android.view.*;

public class World
{
	int x=0;
	int y=0;
	int k=0;
	int pX=0;
	int pY=0;
	boolean e=false;
	List ll=new ArrayList<Grass>();
	Random r=new Random();
	main_properties prop;
	
	public void start(main_properties prop){
		this.prop=prop;
		check(prop);
		new lW(prop);
	}
	
	public void loadFromFiles(main_properties prop,int x, int y){
		
		List l=new ArrayList<String>();
		files.readWorld(l,prop.activity.getExternalFilesDir("").toString()+"/world/"+x+"_"+y+".sso");
		for(int i=0;i<=l.size()-1;i++){
			String[] s=((String)l.get(i)).split(" ");		
		ll.add(new Grass(prop,Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[0])));
		}		
	}
	
	public void check(main_properties prop){
		pX=(int)(prop.playerPosX/500);
		pY=(int)(prop.playerPosY/500);
		cordXY cord;
		for(int i=pX-8;i<pX+12;i++){
		   for(int j=pY-7;j<pY+9;j++){			
			 for(int ii=0;ii<cords.size();ii++){
				  cord=(cordXY)cords.get(ii);
				   if(cord.xx==i&&cord.yy==j){
					   e=true;
					    break;
					   }
			   }
			   if(e){
				   e=false;
				   continue;
			   }
			   new cordXY(i,j,cords);   
		       if(files.check(prop.activity.getExternalFilesDir("").toString(),"/world/",i+"_"+j,prop))		
		       loadFromFiles(prop,i,j);	
		       else {
		     	for(int ii=0;ii<r.nextInt(5)+1;ii++){				
			       ll.add(new Grass(prop,x=i*500+r.nextInt(500),y=j*500+r.nextInt(500),k=r.nextInt(42)));
				   files.updateWorld(prop.activity.getExternalFilesDir("").toString(),"world/",i+"_"+j,String.valueOf(k)+" "+String.valueOf(x)+" "+String.valueOf(y)+" ");		
		      	 }	
		   	  }	
		   }
		   }
			loadGrs();
	}
	
	 List cords=new ArrayList<cordXY>();
	
   static class cordXY{
	   int xx=0;
	   int yy=0;
	   
	   cordXY(int x, int y,List cl){
		   xx=x;
		   yy=y;
		   cl.add(this);
	   }
   }
	
	
	
	public class lW{
		main_properties prop;
		lW(main_properties prop){
			this.prop=prop;
			new Thread(r1).start();
		}
		 Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
				while(true){
					 try{
						
					if(prop.stage.getStage()==Game_stage.MENU){
						 Thread.sleep(100);
						 continue;
					}
					
						 check(prop);
						Thread.sleep(1000);
					 }catch(Exception e){
							 files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
						 
					}
				}
			}
		};
	}
	
	

		 void loadGrs(){
		
			prop.activity.runOnUiThread(r1);
		}

		Runnable r1=new Runnable(){

			@Override
			public void run()
			{

				for(Grass iv:ll){
					prop.world.addView(iv.gr);
				}
				ll.clear();
			}
		};
	
	
	
	public static void loadTrees(main_properties prop){
		
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree01,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree02,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree03,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree04,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree05,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree06,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree07,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree08,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree09,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree10,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree11,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree12,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree13,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree14,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree15,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree16,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree17,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree18,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree19,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree20,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree21,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree22,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree23,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree24,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree25,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree26,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.tree27,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.object_tree,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.object_tree2,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.object_smallwall,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.object_rock,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.object_flag,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.ground_1,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.ground_2,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.ground_3,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.grass_4,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.ground_5,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.ground_6,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.grass_1,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.grass_2,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.grass_3,prop.options)));
		prop.treesList.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.grass_4,prop.options)));
		
		
		
	}
	
}
