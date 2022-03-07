package sevash.livingSword;
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
	static LayoutParams params2=new LayoutParams(125,150);
	static LayoutParams params3=new LayoutParams(130,60);
	static LayoutParams params4=new LayoutParams(95,50);
	static LayoutParams params5=new LayoutParams(145,160);
	static LayoutParams params6=new LayoutParams(80,25);
	static LayoutParams params7=new LayoutParams(80,15);
	static LayoutParams params8=new LayoutParams(80,30);
	static LayoutParams params9=new LayoutParams(75,15);
	static LayoutParams params10=new LayoutParams(80,40);
	static LayoutParams params11=new LayoutParams(65,50);
	static LayoutParams params12=new LayoutParams(60,45);
	static LayoutParams params13=new LayoutParams(50,40);
	
	public Grass(main_properties prop,int code1, int code2, int n){
		this.prop=prop;
		gr=new ImageView(prop.context);
		gr.setImageBitmap((Bitmap)(prop.treesList.get(n)));
		gr.setScaleType(ScaleType.FIT_XY);
		gr.setTranslationX(code1);
		gr.setTranslationY(code2);
	    gr.setRotationX(10);
		if(n<27)gr.setLayoutParams(params);
		else if(n==27||n==28)gr.setLayoutParams(params2);
		else if(n==29)gr.setLayoutParams(params3);
		else if(n==30)gr.setLayoutParams(params4);
		else if(n==31)gr.setLayoutParams(params5);
		else if(n==32||n==36)gr.setLayoutParams(params6);
		else if(n==33||n==35)gr.setLayoutParams(params7);
		else if(n==34)gr.setLayoutParams(params8);
		else if(n==37)gr.setLayoutParams(params9);
		else if(n==38)gr.setLayoutParams(params10);
		else if(n==39)gr.setLayoutParams(params11);
		else if(n==40)gr.setLayoutParams(params12);
		else if(n==41)gr.setLayoutParams(params13);
		gr.setTranslationZ(gr.getTranslationZ()+(code2+gr.getLayoutParams().height)/100);
		
		}
	
	
	
	
}
