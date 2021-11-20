package sevash.testx;
import java.util.*;
import android.widget.*;

public interface Entity
{
	
	
	public static final int NULL=-1;
	public static final int PLAYER=0;
	public static final int DROP=1;
	public static final int MOB=2;
	public static final int STRUCTURE=3;
	public static final int UI=4;
	
	public float worldPosX;
	public float worldPosY;

    
	public int getEntityType();
	
		
	
}
