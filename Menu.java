package sevash.testx;
import android.widget.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.widget.ImageView.*;
import java.util.*;
import android.content.*;

public class Menu
{
	main_properties prop;
	public boolean settingsIsOpen=false;
	public Settings settings;
	LayoutParams p1=new LayoutParams(500,500);
	Avatar avatar;
	PlayerLevel playerLevel;
	Bonuses bonuses;
	
	Menu(main_properties prop){
		prop.setMenu(this);
		this.prop=prop;
		LoadMenu(prop);
	}
	
	public void LoadMenu(main_properties prop){
		new LoadBar(prop);
		new Bonuses();
		avatar=new Avatar();
		playerLevel=new PlayerLevel();
		prop.money=new Money(prop,Money.Type.MENU);
		prop.loadBar.addPoint();
		Music.load(prop);
		new Btn_play(prop);
		settings =new Settings();
		
	
	}
	
	class Avatar{
		ImageView icon;
		Random r=new Random();
	
		Avatar(){
			Item.loadAvatars(prop);
			icon=((Item)prop.icons.get(r.nextInt(prop.icons.size()-1))).picture;
			icon.setX(prop.screenW/30);
			icon.setY(prop.screenH*0.025f);
			prop.onUi(r1);
			prop.avatar=this;
			
				}
		
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(icon,200,200);	
			}
	};
	}
	
	class PlayerLevel{
		double points=0;
		ImageView level_bar;
		ImageView level_bar_backgound;
		TextView lvl;
		TextView pointsOfThisLevel;
		
		PlayerLevel(){
			level_bar_backgound=new ImageView(prop.context);
			level_bar_backgound.setImageResource(R.drawable.black_bar);
			level_bar_backgound.setX(avatar.icon.getX()+avatar.icon.getLayoutParams().width+10);
			level_bar_backgound.setY(prop.screenH/24);
			level_bar_backgound.setScaleType(ScaleType.FIT_XY);
			
			level_bar=new ImageView(prop.context);
			level_bar.setImageResource(R.drawable.red_bar);
			level_bar.setX(avatar.icon.getX()+avatar.icon.getLayoutParams().width+10);
			level_bar.setY(prop.screenH/24);
			level_bar.setScaleType(ScaleType.FIT_XY);
			
			lvl=new TextView(prop.context);
			lvl.setTypeface(prop.ttf);
			lvl.setTextSize(6);
			lvl.setX(level_bar.getX()+40);
			lvl.setY(level_bar.getY()-15);
			lvl.setText(String.valueOf((int)(points/100))+"  lvl.");
			
			pointsOfThisLevel=new TextView(prop.context);
			pointsOfThisLevel.setTypeface(prop.ttf);
			pointsOfThisLevel.setTextSize(6);
		    pointsOfThisLevel.setX(level_bar.getX()+600+15);
			pointsOfThisLevel.setY(level_bar.getY());
			pointsOfThisLevel.setText(String.valueOf((int)(points%100))+" / 100");
			
			
			prop.onUi(r1);
			}
			
			public void addExp(int count){
				points+=count;
				prop.onUi(r2);
			}
			
			public void update(){
				prop.onUi(r2);
			}
			
		Runnable r2=new Runnable(){

			@Override
			public void run()
			{
			    level_bar.setLayoutParams(new LayoutParams((int)(points%100)*6,10));
				lvl.setText(String.valueOf((int)(points/100))+"  lvl.");
				pointsOfThisLevel.setText(String.valueOf((int)(points%100))+" / 100");
				
			}
		};
			
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(level_bar_backgound,600,10);
				prop.menuLayout.addView(level_bar,500,10);
				prop.menuLayout.addView(lvl);
				prop.menuLayout.addView(pointsOfThisLevel);
				
				
			}
		};
		
	}
	
	class Bonuses{
		ImageView intermediary;
		RelativeLayout bonuses_layout;
		float x=0;
		float y=0;
		RelativeLayout description_layout;
		TextView description;
		TextView name;
		LayoutParams params1=new LayoutParams(100,100);
		LayoutParams params2=new LayoutParams(500,200);
		List bonusList=new ArrayList<Bonus>();
		Bonus tmpBonus;
		float midX=0;
		float midY=0;
		
		Bonuses(){
			midX=prop.screenW/2;
			midY=prop.screenH/2;
			
			bonuses=this;
			intermediary=new ImageView(prop.context);
			intermediary.setImageResource(R.drawable.bonus42);
			intermediary.setScaleType(ScaleType.FIT_XY);
			intermediary.setX(prop.screenW-250-160);
			intermediary.setY(60);
			intermediary.setOnTouchListener(t1);
			
			bonuses_layout=new RelativeLayout(prop.context);
			bonuses_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		    bonuses_layout.setBackgroundColor(Color.argb(0xd4,0,0,0));
			bonuses_layout.setVisibility(View.INVISIBLE);
			bonuses_layout.setOnTouchListener(touch);
			
			description_layout=new RelativeLayout(prop.context);
			description_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		    description_layout.setBackgroundColor(Color.argb(0xd4,0x8f,0x8f,0x8f));
			description_layout.setVisibility(View.INVISIBLE);
			description_layout.setLayoutParams(params2);
			description_layout.setZ(2);
			description_layout.setClipChildren(false);
			description_layout.setClipToOutline(false);
			description_layout.setOnTouchListener(t3);
			
			name=new TextView(prop.context);
			name.setTypeface(prop.ttf);
			name.setTextSize(12);
			name.setX(45);
			name.setY(20);
			
			description=new TextView(prop.context);
			description.setTypeface(prop.ttf);
			description.setTextSize(8);
			description.setX(25);
			
			description_layout.addView(name);
			description_layout.addView(description);
			
			loadBonuses();
			
			bonuses_layout.addView(description_layout);
			for(Bonus bonus:bonusList)
			bonuses_layout.addView(bonus.picture);
			prop.menuLayout.addView(intermediary,100,100);
			prop.menuLayout.addView(bonuses_layout);
			}
			
		OnTouchListener t2=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				if(p2.getAction()==MotionEvent.ACTION_DOWN)
				{
					y=p2.getY();
					x=p2.getX();

				}
				
				if(p2.getAction()==MotionEvent.ACTION_MOVE)
				{
					bonuses_layout.setScrollX(bonuses_layout.getScrollX()+(int)(x-p2.getX()));
					bonuses_layout.setScrollY(bonuses_layout.getScrollY()+(int)(y-p2.getY()));
				}
				
				if(p2.getAction()==MotionEvent.ACTION_UP){
					updateDescription(findBonus((ImageView)p1));
				}
				return true;
			}

			};
			
		Runnable r2=new Runnable(){

			@Override
			public void run()
			{
				
				if(description_layout.getVisibility()==View.VISIBLE)
					description_layout.setVisibility(View.INVISIBLE);
				else
					description_layout.setVisibility(View.VISIBLE);
				
			}
			};
			
			void updateDescription(Bonus bonus){
				if(bonus==tmpBonus)
					prop.onUi(r2);
				else{
				    tmpBonus=bonus;
					prop.onUi(r4);
					}
				
			}
			
		Runnable r4=new Runnable(){

			@Override
			public void run()
			{
					description_layout.setVisibility(View.VISIBLE);
				description_layout.setX(tmpBonus.picture.getX()+tmpBonus.picture.getWidth()+25);
				description_layout.setY(tmpBonus.picture.getY()-30);
				name.setText(tmpBonus.name);
				description.setY(name.getY()+name.getHeight());
				description.setText(tmpBonus.description);
			}
		};
			
			Bonus findBonus(ImageView picture){
				for(Bonus bonus:bonusList){
					if(bonus.picture==picture)
						return bonus;
				}
				return null;
			}
			
		class Canv extends View{
			Paint paint=new Paint();
			Canvas canvas=new Canvas();
			float x1, y1, x2, y2;

			public Canv(Context context,float x1,float y1,float x2, float y2,int color){
				super(context);
				this.x1=x1;
				this.x2=x2;
				this.y1=y1;
				this.y2=y2;
				if(y2<y1&&y2<0){
				   setScrollY((int)(y2));
				   setY((int)(y2));
				}else 
				 if(y2>y1&&y1<0){
				   setScrollY((int)(y1));
				   setY((int)(y1));
				 }else 
				 if(y2>y1&&y1>0){
					 setScrollY((int)(y1));
					 setY((int)(y1));
				 }else 
				 if(y2<y1&&y2>0){
					 setScrollY((int)(y2));
					 setY((int)(y2));
				 }
				if(x2<x1&&x2<0){
					setScrollX((int)(x2));
					setX((int)(x2));
				}else 
				if(x2>x1&&x1<0){
					setScrollX((int)(x1));
					setX((int)(x1));
				}else 
				if(x2>x1&&x1>0){
					setScrollX((int)(x1));
					setX((int)(x1));
				}else 
				if(x2<x1&&x2>0){
					setScrollX((int)(x2));
					setX((int)(x2));
				}
				paint.setColor(color);
				paint.setStrokeWidth(3);
				this.setClipToOutline(false);
			}
			@Override
			protected void onDraw(Canvas canvas)
			{
				canvas.drawLine(x1,y1,x2,y2,paint);
				super.onDraw(canvas);
			}
		}
			
		class Bonus{
			ImageView picture;
			String description="nullo";
			String name="";
			Bonus lastBonus;
			float cX;
			float cY;
			int parent;
			int id;
			
			Bonus(int id,int pictureInt, String name, Object obj,float x,float y){
				this.name=name;
				this.id=id;
				picture=new ImageView(prop.context);
				picture.setImageResource(pictureInt);
				picture.setScaleType(ScaleType.FIT_XY);
				picture.setLayoutParams(params1);
				picture.setOnTouchListener(t2);
				picture.setZ(1);
				
				if(obj==null){
					picture.setX(midX);
					picture.setY(midY);
					cX=picture.getX()+picture.getLayoutParams().width/2;
					cY=picture.getY()+picture.getLayoutParams().height/2;
					
				}
				else{
					picture.setX(findBonus(((int)obj)).picture.getX()+x);
					picture.setY(findBonus(((int)obj)).picture.getY()+y);
					cX=picture.getX()+picture.getLayoutParams().width/2;
					cY=picture.getY()+picture.getLayoutParams().height/2;
					
					parent=(int)obj;
					tmpBonus=findBonus(((int)obj));
					bonuses_layout.addView( new Canv(prop.context,tmpBonus.cX,tmpBonus.cY,cX,cY,Color.RED));
				}
			
				
				bonusList.add(this);
			}
		}
		
		Bonus findBonus(int id){
			for(Bonus bonus:bonusList){
				if(bonus.id==id) return bonus;
			}
			return null;
		};
			
			
			OnTouchListener touch=new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					switch (p2.getAction()){
						case MotionEvent.ACTION_DOWN:{
								y=bonuses_layout.getScrollY()+p2.getY();
								x=bonuses_layout.getScrollX()+p2.getX();
								break;
							}
						case MotionEvent.ACTION_MOVE:{
								bonuses_layout.setScrollX((int)(x-p2.getX()));
								bonuses_layout.setScrollY((int)(y-p2.getY()));
							}
					}
					return true;
				}
			};
			
		
		
		
		OnTouchListener t1=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				
				
				if(p2.getAction()==MotionEvent.ACTION_UP)
				{
					
					if(bonuses_layout.getVisibility()==View.INVISIBLE){
						intermediary.setZ(11);
						bonuses_layout.setZ(10);
						bonuses_layout.setVisibility(View.VISIBLE);
						
					}
					else{
						intermediary.setZ(0);
						bonuses_layout.setZ(0);
						bonuses_layout.setVisibility(View.INVISIBLE);
						
					}
				}
				return true;
			}
		};
		
		OnTouchListener t3=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				
				if(p2.getAction()==MotionEvent.ACTION_UP)
				{
					prop.onUi(r3);
				}
				return true;
			}
		};
		
		Runnable r3=new Runnable(){

			@Override
			public void run()
			{
				bonuses_layout.addView( new Canv(prop.context,tmpBonus.cX,tmpBonus.cY,((Bonus)(bonusList.get(tmpBonus.parent))).cX,((Bonus)(bonusList.get(tmpBonus.parent))).cY,Color.GREEN));
				
			}
		};
		
		void loadBonuses(){
			new Bonus(0,R.drawable.bonus42,"Начало пути..",null,midX,midY);
			
			new Bonus(1,R.drawable.bonus51,"Начало пути..",0,400,0);
			new Bonus(2,R.drawable.bonus50,"Начало пути..",1,150,-150);
			new Bonus(3,R.drawable.bonus49,"Начало пути..",1,150,0);
			new Bonus(4,R.drawable.bonus48,"Начало пути..",1,150,150);
			new Bonus(5,R.drawable.bonus47,"Начало пути..",2,150,-150);
			new Bonus(6,R.drawable.bonus46,"Начало пути..",2,150,0);
			new Bonus(7,R.drawable.bonus52,"Начало пути..",3,150,0);
			new Bonus(8,R.drawable.bonus53,"Начало пути..",7,150,0);
			new Bonus(9,R.drawable.bonus54,"Начало пути..",4,150,150);
			new Bonus(10,R.drawable.bonus55,"Начало пути..",4,150,0);
			new Bonus(11,R.drawable.bonus56,"Начало пути..",5,150,0);
			new Bonus(12,R.drawable.bonus57,"Начало пути..",6,150,0);
			new Bonus(13,R.drawable.bonus58,"Начало пути..",10,150,0);
			new Bonus(14,R.drawable.bonus59,"Начало пути..",9,150,0);
			
			new Bonus(15,R.drawable.bonus116,"Начало пути..",0,-400,0);
			new Bonus(16,R.drawable.bonus117,"Начало пути..",15,-150,-150);
			new Bonus(17,R.drawable.bonus118,"Начало пути..",15,-150,0);
			new Bonus(18,R.drawable.bonus119,"Начало пути..",15,-150,150);
			new Bonus(19,R.drawable.bonus120,"Начало пути..",16,-150,-150);
			new Bonus(20,R.drawable.bonus121,"Начало пути..",16,-150,0);
			new Bonus(21,R.drawable.bonus128,"Начало пути..",17,-150,0);
			new Bonus(22,R.drawable.bonus129,"Начало пути..",21,-150,0);
			new Bonus(23,R.drawable.bonus130,"Начало пути..",18,-150,150);
			new Bonus(24,R.drawable.bonus131,"Начало пути..",18,-150,0);
			new Bonus(25,R.drawable.bonus132,"Начало пути..",19,-150,0);
			new Bonus(26,R.drawable.bonus133,"Начало пути..",20,-150,0);
			new Bonus(27,R.drawable.bonus134,"Начало пути..",23,-150,0);
			new Bonus(28,R.drawable.bonus135,"Начало пути..",24,-150,0);
			new Bonus(29,R.drawable.bonus136,"Начало пути..",27,-150,0);
			new Bonus(30,R.drawable.bonus137,"Начало пути..",28,-150,0);
			new Bonus(31,R.drawable.bonus138,"Начало пути..",25,-150,0);
			new Bonus(32,R.drawable.bonus139,"Начало пути..",26,-150,0);
			new Bonus(57,R.drawable.bonus140,"Начало пути..",22,-150,0);
			new Bonus(58,R.drawable.bonus141,"Начало пути..",29,-150,0);
			new Bonus(59,R.drawable.bonus142,"Начало пути..",30,-150,0);
			new Bonus(60,R.drawable.bonus143,"Начало пути..",31,-150,0);
			new Bonus(61,R.drawable.bonus144,"Начало пути..",32,-150,0);
			
			new Bonus(33,R.drawable.bonus98,"Начало пути..",0,0,-400);
			new Bonus(34,R.drawable.bonus99,"Начало пути..",33,-150,-150);
			new Bonus(35,R.drawable.bonus100,"Начало пути..",33,0,-150);
			new Bonus(36,R.drawable.bonus101,"Начало пути..",33,150,-150);
			new Bonus(37,R.drawable.bonus102,"Начало пути..",34,-150,-150);
			new Bonus(38,R.drawable.bonus103,"Начало пути..",34,0,-150);
			new Bonus(39,R.drawable.bonus104,"Начало пути..",35,0,-150);
			new Bonus(40,R.drawable.bonus105,"Начало пути..",36,150,-150);
			new Bonus(41,R.drawable.bonus106,"Начало пути..",36,0,-150);
			new Bonus(42,R.drawable.bonus107,"Начало пути..",37,0,-150);
			new Bonus(43,R.drawable.bonus108,"Начало пути..",39,0,-150);
			new Bonus(44,R.drawable.bonus109,"Начало пути..",40,0,-150);
			
			new Bonus(45,R.drawable.bonus110,"Начало пути..",0,0,400);
			new Bonus(46,R.drawable.bonus111,"Начало пути..",45,-150,150);
			new Bonus(47,R.drawable.bonus112,"Начало пути..",45,0,150);
			new Bonus(48,R.drawable.bonus113,"Начало пути..",45,150,150);
			new Bonus(49,R.drawable.bonus114,"Начало пути..",46,-150,150);
			new Bonus(50,R.drawable.bonus115,"Начало пути..",46,0,150);
			new Bonus(51,R.drawable.bonus122,"Начало пути..",47,0,150);
			new Bonus(52,R.drawable.bonus123,"Начало пути..",48,150,150);
			new Bonus(53,R.drawable.bonus124,"Начало пути..",48,0,150);
			new Bonus(54,R.drawable.bonus125,"Начало пути..",49,0,150);
			new Bonus(55,R.drawable.bonus126,"Начало пути..",51,0,150);
			new Bonus(56,R.drawable.bonus127,"Начало пути..",52,0,150);
			
		}
		
	}
	
	
	class Settings{
		
		ImageView set;
		LayoutParams p1=new LayoutParams(100,100);
		RelativeLayout settingsLayout;
		public MusicVolume musicVolume;
		
		
		Settings(){
			set=new ImageView(prop.context);
			set.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.settings_button,prop.options)));
			set.setTranslationX(prop.screenW-250);
			set.setTranslationY(60);
			set.setTranslationZ(2);
			set.setOnTouchListener(t1);
			settingsLayout=new RelativeLayout(prop.context);
			settingsLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			settingsLayout.setBackgroundColor(Color.argb(192,0,0,0));
			settingsLayout.setOnTouchListener(t2);
			settingsLayout.setVisibility(View.INVISIBLE);
			settingsLayout.setTranslationZ(1);
			prop.activity.runOnUiThread(r1);
			musicVolume= new MusicVolume();
		}
		
		public void closeSettings(){
			settingsLayout.setVisibility(View.INVISIBLE);
			settingsIsOpen=false;
		}
		
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				try{
				prop.menuLayout.addView(set,p1);
				prop.menuLayout.addView(settingsLayout);
				}catch(Exception e){
					files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
					
				}
			}

			
		};
		OnTouchListener t1=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				if(p2.getAction()==MotionEvent.ACTION_UP)
				{
					if(settingsIsOpen){
						closeSettings();
						return true;
					}
					settingsLayout.setVisibility(View.VISIBLE);
					settingsIsOpen=true;
					}
				return true;
			}

			
		};
		
	OnTouchListener t2=new OnTouchListener(){

		@Override
		public boolean onTouch(View p1, MotionEvent p2)
		{
			
			return true;
		}

	};
	
	class MusicVolume{
		TextView text;
		ImageView bar;
		ImageView point;
		RelativeLayout barsLayout;
			LayoutParams params1=new LayoutParams(1200,10);
			float volume=0;
		
		
		MusicVolume(){
			text=new TextView(prop.context);
			text.setGravity(Gravity.RIGHT);
			text.setTextSize(12);
			text.setTranslationX(140);
			text.setTranslationY(prop.screenH/9);
			text.setLayoutParams(new LayoutParams(300,50));
			text.setTextColor(Color.BLUE);
			text.setTypeface(prop.ttf);
		text.setText("Музыка");
		
		bar=new ImageView(prop.context);
			bar.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.red_bar,prop.options)));
			bar.setScaleType(ScaleType.FIT_XY);
			bar.setLayoutParams(new LayoutParams(1200,10));
			bar.setTranslationX(525);
			bar.setTranslationY(prop.screenH/9);
			
			barsLayout=new RelativeLayout(prop.context);
			barsLayout.setLayoutParams(new LayoutParams(1200,70));
			barsLayout.setTranslationX(500);
			barsLayout.setTranslationY(prop.screenH/9-30);
			barsLayout.setOnTouchListener(t3);
		
			point=new ImageView(prop.context);
			point.setImageBitmap(Bitmap.createBitmap(BitmapFactory.decodeResource(prop.activity.getResources(),R.drawable.black_bar,prop.options)));
			point.setScaleType(ScaleType.FIT_XY);
			point.setLayoutParams(new LayoutParams(70,10));
			point.setRotation(90);
			point.setTranslationX(900);
			point.setTranslationY(prop.screenH/9);
			
		prop.activity.runOnUiThread(r3);
		}
		
		public void updatePoint(){
			point.setTranslationX(500+1200*volume);
			if(prop.music!=null)
			prop.music.setVolume(volume,volume);
			
		}
			Runnable r4=new Runnable(){

				@Override
				public void run()
				{
					updatePoint();
				}
			};
		
			OnTouchListener t3=new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					if(p2.getAction()==MotionEvent.ACTION_MOVE){
						if(barsLayout.getTranslationX()+p2.getX()>barsLayout.getTranslationX()
						   &&barsLayout.getTranslationX()+p2.getX()<barsLayout.getTranslationX()+1200){
						//point.setTranslationX(barsLayout.getTranslationX()+p2.getX());
						volume=p2.getX()/1200;
						prop.activity.runOnUiThread(r4);
						}
					}
					return true;
				}

			
		};
		
			Runnable r3=new Runnable(){
				@Override
				public void run()
				{
					try{
					settingsLayout.addView(text);
					settingsLayout.addView(bar);
					settingsLayout.addView(point);
					settingsLayout.addView(barsLayout);
					}catch(Exception e){
						files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
				
					}
				}

			};
			
	}
	
	}
}
