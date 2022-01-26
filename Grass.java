package sevash.testx;
import android.widget.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.widget.ImageView.*;
import java.util.*;

public class Grass
{
	main_properties prop;
	ImageView gr;
	static LayoutParams params=new LayoutParams(256,256);
	
	public Grass(main_properties prop,int code1, int code2, int n){
		this.prop=prop;
		gr=new ImageView(prop.context);
		gr.setImageBitmap((Bitmap)(prop.treesList.get(n)));
		gr.setScaleType(ScaleType.FIT_XY);
		gr.setTranslationX(code1);
		gr.setTranslationY(code2);
		gr.setRotationY(10);
		gr.setLayoutParams(params);
	//	prop.activity.runOnUiThread(r1);
	}
	
	static class Load{
		
	main_properties prop;
	 List l;
	 Load(List l,main_properties prop){
		this.prop=prop;
		this.l=l;
		prop.activity.runOnUiThread(r1);
		}

	Runnable r1=new Runnable(){

		@Override
		public void run()
		{
			for(Grass iv:l){
			prop.world.addView(iv.gr,params);
			}
			l.clear();
		}
	};
	}
	
	
}
