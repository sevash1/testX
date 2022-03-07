package sevash.livingSword;
import java.util.*;
import android.graphics.*;
import sevash.livingSword.*;

public class Mob implements Entity
{

   public static final int NULL=-1;
   public static final int SKELETON=0;
	List anim_idle=new ArrayList<Bitmap>();
	List anim_walk=new ArrayList<Bitmap>();
	List anim_takehit=new ArrayList<Bitmap>();
	List anim_shield=new ArrayList<Bitmap>();
	List anim_death=new ArrayList<Bitmap>();
	List anim_attack=new ArrayList<Bitmap>();
	
	
   public final enum mobs{
	   NULL,
	   SKELETON
	   
   }


	@Override
	public int getEntityType()
	{
		// TODO: Implement this method
		return MOB;
	}
	
	
	
	public int getMobId(){
		return NULL;
	}

	
	

	
	
}
	
	
	
	
	


	
	

