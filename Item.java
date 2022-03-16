package sevash.livingSword;
import android.widget.*;
import android.content.res.*;
import android.widget.RelativeLayout.*;

public class Item
{
	
	static LayoutParams params1=new LayoutParams(90,90);
	main_properties prop;
	int id=0;
	int pictureInt=0;
	ImageView picture;
	int price=0;
	int count=0;
	int slotX,slotY;
	String name="NoN";
	String description="";
	String[] dat;
	boolean isArmor=false;
	boolean isSword=false;
	boolean isNecklace=false;
	boolean isShield=false;
	boolean isRing=false;
	boolean isBoots=false;
	boolean isHelmet=false;
	boolean isGloves=false;
	int t=-1;
	int armSlot=-1;
	
	
	Item(main_properties prop,int id,int picture,int price, String name, String description, String[] dat){
		this.prop=prop;
		this.id=id;
		this.pictureInt=picture;
		this.picture=new ImageView(prop.context);
		this.picture.setImageResource(picture);
		this.price=price;
		this.name=name;
		this.description=name;//description;
		this.dat=dat;
		if(dat!=null)
			for(int i=0;i<dat.length;i++){
				
				if(dat[i].contentEquals("helmet")){
					isHelmet=true;
					t=0;
				}
				if(dat[i].contentEquals("armor")){
				    isArmor=true;
				    t=1;
				}
				if(dat[i].contentEquals("boots")){
					isBoots=true;
					t=2;
				}
				if(dat[i].contentEquals("gloves")){
					isGloves=true;
					t=3;
				}
				if(dat[i].contentEquals("shield")){
					isShield=true;
					t=4;
				}
				if(dat[i].contentEquals("sword")){
				    isSword=true;
				    t=5;
				}
				if(dat[i].contentEquals("ring")){
					isRing=true;
					t=6;
				}
				if(dat[i].contentEquals("necklace")){
					isNecklace=true;
					t=7;
					}
	
				}
		prop.items.add(this);
	}
	
	Item(main_properties prop,String from,int id,int picture,int price, String name, String description, String[] dat){
		this.prop=prop;
		this.id=id;
		this.pictureInt=picture;
		this.picture=new ImageView(prop.context);
		this.picture.setImageResource(picture);
		//this.picture=iv;
		this.price=price;
		this.name=name;
		this.description=description;
		this.dat=dat;
		if(from.contentEquals("ava")){
			prop.icons.add(this);
			return;
		}
		if(dat!=null)
			for(int i=0;i<dat.length;i++){

				if(dat[i].contentEquals("helmet")){
					isHelmet=true;
					t=0;
				}
				if(dat[i].contentEquals("armor")){
				    isArmor=true;
				    t=1;
				}
				if(dat[i].contentEquals("boots")){
					isBoots=true;
					t=2;
				}
				if(dat[i].contentEquals("gloves")){
					isGloves=true;
					t=3;
				}
				if(dat[i].contentEquals("shield")){
					isShield=true;
					t=4;
				}
				if(dat[i].contentEquals("sword")){
				    isSword=true;
				    t=5;
				}
				if(dat[i].contentEquals("ring")){
					isRing=true;
					t=6;
				}
				if(dat[i].contentEquals("necklace")){
					isNecklace=true;
					t=7;
				}

			}
	}
	
	public Item copy(){
		return new Item(prop,"copy",id,pictureInt,price,name,description,dat);
	}
	
	public static void loadItems(main_properties prop){
		new Item(prop,1,R.drawable.helmet_01a,4444,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,2,R.drawable.helmet_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,3,R.drawable.helmet_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,4,R.drawable.helmet_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,5,R.drawable.helmet_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,6,R.drawable.helmet_02a,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,7,R.drawable.helmet_02b,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,8,R.drawable.helmet_02c,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,9,R.drawable.helmet_02d,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,10,R.drawable.helmet_02e,4,prop.words.get(Words.words.armor01e),"",new String[]{"helmet"});
		new Item(prop,11,R.drawable.armor_01a,0,prop.words.get(Words.words.armor01a),"",new String[]{"armor"});
		new Item(prop,12,R.drawable.armor_01b,1,prop.words.get(Words.words.armor01b),"",new String[]{"armor"});
		new Item(prop,13,R.drawable.armor_01c,2,prop.words.get(Words.words.armor01c),"",new String[]{"armor"});
		new Item(prop,14,R.drawable.armor_01d,3,prop.words.get(Words.words.armor01d),"",new String[]{"armor"});
		new Item(prop,15,R.drawable.armor_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"armor"});
		new Item(prop,16,R.drawable.gloves_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{"gloves"});
		new Item(prop,17,R.drawable.gloves_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"gloves"});
		new Item(prop,18,R.drawable.gloves_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"gloves"});
		new Item(prop,19,R.drawable.gloves_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"gloves"});
		new Item(prop,20,R.drawable.gloves_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"gloves"});
		new Item(prop,21,R.drawable.boots_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{"boots"});
		new Item(prop,22,R.drawable.boots_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"boots"});
		new Item(prop,23,R.drawable.boots_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"boots"});
		new Item(prop,24,R.drawable.boots_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"boots"});
		new Item(prop,25,R.drawable.boots_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"boots"});
		new Item(prop,26,R.drawable.necklace_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,27,R.drawable.necklace_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,28,R.drawable.necklace_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,29,R.drawable.necklace_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,30,R.drawable.necklace_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,31,R.drawable.necklace_02a,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,32,R.drawable.necklace_02b,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,33,R.drawable.necklace_02c,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,34,R.drawable.necklace_02d,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,35,R.drawable.necklace_02e,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,36,R.drawable.necklace_03a,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,37,R.drawable.necklace_03b,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,38,R.drawable.necklace_03c,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,39,R.drawable.necklace_03d,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,40,R.drawable.necklace_03e,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,41,R.drawable.shield_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,42,R.drawable.shield_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,43,R.drawable.shield_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,44,R.drawable.shield_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,45,R.drawable.shield_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,46,R.drawable.shield_02a,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,47,R.drawable.shield_02b,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,48,R.drawable.shield_02c,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,49,R.drawable.shield_02d,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,50,R.drawable.shield_02e,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,51,R.drawable.shield_03a,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,52,R.drawable.shield_03b,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,53,R.drawable.shield_03c,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,54,R.drawable.shield_03d,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,55,R.drawable.shield_03e,4,prop.words.get(Words.words.armor01e),"",new String[]{"shield"});
		new Item(prop,56,R.drawable.ring_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,57,R.drawable.ring_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,58,R.drawable.ring_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,59,R.drawable.ring_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,60,R.drawable.ring_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,61,R.drawable.ring_02a,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,62,R.drawable.ring_02b,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,63,R.drawable.ring_02c,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,64,R.drawable.ring_02d,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,65,R.drawable.ring_02e,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,66,R.drawable.ring_03a,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,67,R.drawable.ring_03b,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,68,R.drawable.ring_03c,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,69,R.drawable.ring_03d,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,70,R.drawable.ring_03e,4,prop.words.get(Words.words.armor01e),"",new String[]{"ring"});
		new Item(prop,71,R.drawable.sword_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,72,R.drawable.sword_01b,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,73,R.drawable.sword_01c,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,74,R.drawable.sword_01d,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,75,R.drawable.sword_01e,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,76,R.drawable.sword_02a,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,77,R.drawable.sword_02b,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,78,R.drawable.sword_02c,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,79,R.drawable.sword_02d,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,80,R.drawable.sword_02e,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,81,R.drawable.sword_03a,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,82,R.drawable.sword_03b,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,83,R.drawable.sword_03c,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,84,R.drawable.sword_03d,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,85,R.drawable.sword_03e,4,prop.words.get(Words.words.armor01e),"",new String[]{"sword"});
		new Item(prop,86,R.drawable.wood_01a,4,prop.words.get(Words.words.armor01e),"",new String[]{""});
	
		loadAvatars(prop);
		}
		
	public static void loadAvatars(main_properties prop){
		new Item(prop,"ava",36,R.drawable.icon01,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon02,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon03,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon04,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon05,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon06,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon07,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon08,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon09,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon10,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon11,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon12,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon13,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon14,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon15,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon16,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon17,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon18,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon19,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon20,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon21,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon22,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon23,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon24,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon25,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon26,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon27,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon28,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon29,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon30,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon31,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon32,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon33,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon34,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon35,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon36,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon37,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon38,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon39,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon40,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon41,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon42,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon43,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon44,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon45,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon46,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon47,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon48,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon49,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon50,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon51,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon52,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon53,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon54,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon55,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon56,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon57,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon58,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon59,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon60,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon61,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon62,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon63,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon64,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon65,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon66,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon67,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon68,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon69,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon70,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon71,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon72,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon73,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon74,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon75,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon76,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon77,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon78,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon79,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon80,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon81,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		new Item(prop,"ava",36,R.drawable.icon82,4,prop.words.get(Words.words.armor01e),"",new String[]{"necklace"});
		
	}
}
