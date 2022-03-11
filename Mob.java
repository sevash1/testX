package sevash.livingSword;
import java.util.*;
import android.graphics.*;
import sevash.livingSword.*;

public class Mob
{

   public static final int NULL=-1;
   public static final int SKELETON=0;
	List anim_idle=new ArrayList<Bitmap>();
	List anim_walk=new ArrayList<Bitmap>();
	List anim_takehit=new ArrayList<Bitmap>();
	List anim_shield=new ArrayList<Bitmap>();
	List anim_death=new ArrayList<Bitmap>();
	List anim_attack=new ArrayList<Bitmap>();
	main_properties prop;
	Bitmap hpBar;
	
   Mob(main_properties prop){
	   this.prop=prop;
	   anim_load();
	   prop.mob=this;
   }
	
	public void anim_load()
	{
	    hpBar=Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options));
		
		List l=anim_idle;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_idle,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_idle,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_idle,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_idle,prop.options),450,0,150,150));

		l=anim_walk;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_walk,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_walk,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_walk,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_walk,prop.options),450,0,150,150));

		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_death,prop.options),0,0,150,150));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_death,prop.options),150,0,150,150));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_death,prop.options),300,0,150,150));
		anim_death.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_death,prop.options),450,0,150,150));

		
		/*
		l=anim_takehit;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_takehit,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_takehit,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_takehit,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_takehit,prop.options),450,0,150,150));

		l=anim_shield;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_shield,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_shield,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_shield,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_shield,prop.options),450,0,150,150));


			l=anim_attack;
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),0,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),150,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),300,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),450,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),600,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),750,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),900,0,150,150));
		l.add(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.context.getResources(),R.drawable.skeleton_attack,prop.options),1050,0,150,150));
*/
	}

	
	
}
	
	
	
	
	


	
	

