package sevash.testx;
import android.widget.*;
import android.content.res.*;
import android.widget.RelativeLayout.*;

public class Item
{
	
	static LayoutParams params1=new LayoutParams(90,90);
	main_properties prop;
	int id=0;
	ImageView picture;
	int price=0;
	String name="NoN";
	String description="";
	
	
	Item(main_properties prop,int id,int picture,int price, String name, String description){
		this.prop=prop;
		this.id=id;
		this.picture=new ImageView(prop.context);
		this.picture.setImageResource(picture);
		this.price=price;
		this.name=name;
		this.description=description;
		prop.items.add(this);
	}
	
	public static void loadItems(main_properties prop){
		new Item(prop,0,R.drawable.cell01,0,"рамка","");
		new Item(prop,1,R.drawable.armor_01a,0,prop.words.get(Words.words.armor01a),"");
		new Item(prop,2,R.drawable.armor_01b,1,prop.words.get(Words.words.armor01b),"");
		new Item(prop,3,R.drawable.armor_01d,2,prop.words.get(Words.words.armor01d),"");
		new Item(prop,4,R.drawable.armor_01c,3,prop.words.get(Words.words.armor01c),"");
		new Item(prop,5,R.drawable.armor_01e,4,prop.words.get(Words.words.armor01e),"");
	
		}
}
