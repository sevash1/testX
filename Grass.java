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
	LayoutParams params=new LayoutParams(256,256);
	
	public Grass(main_properties prop,int code1, int code2){
		this.prop=prop;
		gr=new ImageView(prop.context);
		gr.setImageBitmap((Bitmap)(prop.treesList.get(Math.abs(code1+code2)%27)));
		gr.setScaleType(ScaleType.FIT_XY);
		gr.setTranslationX(code1*600+code2*code2/10);
		gr.setTranslationY(code2*600+code1*code1/10);
		gr.setRotationY(10);
		prop.activity.runOnUiThread(r1);
	}
	

	Runnable r1=new Runnable(){

		@Override
		public void run()
		{
			prop.world.addView(gr,params);
			// TODO: Implement this method
		}

		
	};
	
	public static void load(main_properties prop){
		for(int i=-50;i<50;i++){
			for(int j=-50;j<50;j++){
				//if(i+j==0||i*j==0)continue;
				new Grass(prop,i,j);
			}
		}
	}
}
