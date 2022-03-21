package sevash.livingSword;
import java.util.*;
import android.graphics.*;
import sevash.livingSword.*;
import android.view.*;
import android.widget.RelativeLayout.*;
import android.widget.*;
import android.widget.ImageView.*;

public class Mob
{
	main_properties prop;
	Bitmap hpBar;
	ImageView idle;
	View move;
	View death;
	
   Mob(main_properties prop){
	   this.prop=prop;
	   anim_load();
	   prop.mob=this;
   }
	
	public void anim_load()
	{
	    hpBar=Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options));
		
		idle=new ImageView(prop.context);
		idle.setLayoutParams(new LayoutParams(1200,300));
		idle.setPivotX(0);
		idle.setScaleX(4);
		idle.setBackgroundResource(R.drawable.skeleton_idle);
		
		move=new View(prop.context);
		move.setLayoutParams(new LayoutParams(1200,300));
		move.setY(300);
		move.setPivotX(0);
		move.setScaleX(4);
		move.setBackgroundResource(R.drawable.skeleton_walk);
		
		death=new View(prop.context);
		death.setLayoutParams(new LayoutParams(1200,300));
		death.setY(600);
		death.setPivotX(0);
		death.setScaleX(4);
		death.setBackgroundResource(R.drawable.skeleton_death);
	
	}

	
	
}
	
	
	
	
	


	
	

