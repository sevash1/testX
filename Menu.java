package sevash.livingSword;
import android.widget.*;
import android.graphics.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.widget.ImageView.*;
import java.util.*;
import android.content.*;
import android.media.*;

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
		settings =new Settings();
		new Btn_play(prop);
	
	}
	
	class Avatar{
		ImageView icon;
		ImageView background;
		Random r=new Random();
	
		Avatar(){
			Item.loadAvatars(prop);
			
			background=new ImageView(prop.context);
			background.setImageResource(R.drawable.background3);
			background.setX(prop.screenW/30);
			background.setY(prop.screenH*0.025f);
			background.setScaleType(ScaleType.FIT_XY);
			
			icon=new ImageView(prop.context);
			icon.setImageResource(((Item)prop.icons.get(r.nextInt(prop.icons.size()-1))).pictureInt);
			icon.setX(background.getX()+25);
			icon.setY(background.getY()+25);
			icon.setScaleType(ScaleType.FIT_XY);
			
			prop.menuLayout.addView(background,200,200);
			prop.menuLayout.addView(icon,150,150);	
			prop.avatar=this;
			
				}
		
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				
			}
	};
	}
	
	class PlayerLevel{
		float points=0;
		float pointsOnThisLevel=0;
		float maxPointsOnThisLevel=20;
		int level=0;
		ImageView level_bar;
		ImageView level_bar_backgound;
		TextView lvl;
		TextView pointsOfThisLevel;
		
		PlayerLevel(){
			level_bar_backgound=new ImageView(prop.context);
			level_bar_backgound.setImageResource(R.drawable.black_bar);
			level_bar_backgound.setX(avatar.icon.getX()+avatar.background.getLayoutParams().width+10);
			level_bar_backgound.setY(prop.screenH/24);
			level_bar_backgound.setScaleType(ScaleType.FIT_XY);
			
			level_bar=new ImageView(prop.context);
			level_bar.setImageResource(R.drawable.red_bar);
			level_bar.setPivotX(0);
			level_bar.setX(avatar.icon.getX()+avatar.background.getLayoutParams().width+10);
			level_bar.setY(prop.screenH/24);
			level_bar.setScaleType(ScaleType.FIT_XY);
			
			pointsOfThisLevel=new TextView(prop.context);
			pointsOfThisLevel.setTypeface(prop.ttf);
			pointsOfThisLevel.setTextColor(Color.WHITE);
			pointsOfThisLevel.setTextSize(6);
		    pointsOfThisLevel.setX(level_bar.getX()+600+15);
			pointsOfThisLevel.setY(level_bar.getY());
			
			lvl=new TextView(prop.context);
			lvl.setTypeface(prop.ttf);
			lvl.setTextColor(Color.WHITE);
			lvl.setTextSize(6);
			lvl.setX(level_bar.getX()+40);
			lvl.setY(level_bar.getY()-15);
			
			r2.run();
		
			prop.onUi(r1);
			}
			
			public void addExp(float count){
				points+=count;
				pointsOnThisLevel+=count;
				update();
			}
			
			public void update(){
				while(pointsOnThisLevel>maxPointsOnThisLevel){
					pointsOnThisLevel-=maxPointsOnThisLevel;
					level++;
					maxPointsOnThisLevel*=1.33f;

				}
				r2.run();
				//prop.onUi(r2);
			}
			
		Runnable r2=new Runnable(){

			@Override
			public void run()
			{
				
			    level_bar.setScaleX(pointsOnThisLevel/maxPointsOnThisLevel);
				lvl.setText(String.valueOf(level)+"  lvl.");
				pointsOfThisLevel.setText(String.valueOf((int)(pointsOnThisLevel))+" / "+String.valueOf((int)maxPointsOnThisLevel));
				
			}
		};
			
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(level_bar_backgound,600,10);
				prop.menuLayout.addView(level_bar,600,10);
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
		RelativeLayout descB;
		TextView description;
		TextView name;
		LayoutParams params1=new LayoutParams(100,100);
		List bonusList=new ArrayList<Bonus>();
		Bonus tmpBonus;
		float midX=0;
		float midY=0;
		boolean isOpen=false;
		TextView btnText;;
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
			description_layout.setVisibility(View.INVISIBLE);
			description_layout.setZ(2);
			description_layout.setLayoutParams(new LayoutParams(500,800));
			
			descB=new RelativeLayout(prop.context);
			descB.setLayoutParams(new LayoutParams(500,LayoutParams.WRAP_CONTENT));
			descB.setBackgroundResource(R.drawable.background3);
			
			btnText=new TextView(prop.context);
			btnText.setBackgroundResource(R.drawable.btn);
			btnText.setGravity(Gravity.CENTER);
			btnText.setTypeface(prop.ttf);
			btnText.setTextSize(8);
			btnText.setTextColor(Color.YELLOW);
			btnText.setX(250);
			btnText.setLayoutParams(new LayoutParams(200,50));
			btnText.setText("изучить");
			btnText.setOnTouchListener(t3);
			
			name=new TextView(prop.context);
			name.setTypeface(prop.ttf);
			name.setTextColor(Color.WHITE);
			name.setTextSize(12);
			name.setX(45);
			name.setPaintFlags(name.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
			
			descB.setY(btnText.getY()
			          +btnText.getLayoutParams().height+20);
			name.setY(25);
			
			description=new TextView(prop.context);
			description.setTypeface(prop.ttf);
			description.setTextColor(Color.WHITE);
			description.setTextSize(8);
			description.setX(25);
			description.setY(name.getY()+15);
			
			descB.addView(name,description_layout.getLayoutParams().width-75,100);
			descB.addView(description,description_layout.getLayoutParams().width-50,100);
			description_layout.addView(descB);
			description_layout.addView(btnText);
			
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
				
				if(description_layout.getVisibility()==View.VISIBLE){
					description_layout.setVisibility(View.INVISIBLE);
					}
				else{
					description_layout.setVisibility(View.VISIBLE);
					}
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
				description_layout.setY(tmpBonus.picture.getY()-100);
				name.setText(tmpBonus.name);
				name.setLayoutParams(new LayoutParams(name.getLayoutParams().width,
													   name.getLineCount()*name.getLineHeight()));
				
				description.setY(name.getY()+name.getLineHeight()*name.getLineCount()+10);
				description.setText(tmpBonus.description);
				description.setLayoutParams(new LayoutParams(description.getLayoutParams().width,
													  description.getLineCount()*description.getLineHeight()));
				
				descB.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)(
							   description.getY()+description.getLineCount()*description.getLineHeight()+50)));
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
				 if(y2>y1&&y1>=0){
					 setScrollY((int)(y1));
					 setY((int)(y1));
				 }else 
				 if(y2<y1&&y2>=0){
					 setScrollY((int)(y2));
					 setY((int)(y2));
				 }else 
				 if(y2==y1){
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
				if(x2>x1&&x1>=0){
					setScrollX((int)(x1));
					setX((int)(x1));
				}else 
				if(x2<x1&&x2>=0){
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
			String description="nullo\nn\nn\nn";
			String name="";
			Bonus lastBonus;
			float cX;
			float cY;
			int parent;
			int id;
			boolean isReceived=false;
			
			Bonus(int id,int pictureInt, String name, Object obj,float x,float y){
				this.name=name;
				this.id=id;
				picture=new ImageView(prop.context);
				picture.setImageResource(pictureInt);
				picture.setScaleType(ScaleType.FIT_XY);
				picture.setLayoutParams(params1);
				picture.setOnTouchListener(t2);
				picture.setZ(1);
				if(id==0){description="начало";
				this.name="аааааааааааааапппачччхххиииииииииииииииииииииииииaxaxaxaxaxaxaxaxaxaxdи";
				}
				if(obj==null){
					picture.setX(midX);
					picture.setY(midY);
					isReceived=true;
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
		
		void update(int n){
			tmpBonus=findBonus(n);
			if(prop.menuLayout.getVisibility()==View.VISIBLE)
				prop.onUi(r3);
			else
				r3.run();
				tmpBonus.isReceived=true;
		}
			
			
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
			
			void openBonuses(){
				intermediary.setZ(11);
				bonuses_layout.setZ(10);
				bonuses_layout.setVisibility(View.VISIBLE);
				isOpen=true;
			}
		
			void closeBonuses(){
				intermediary.setZ(0);
				bonuses_layout.setZ(0);
				bonuses_layout.setVisibility(View.INVISIBLE);
				isOpen=false;
			}
		
		
		OnTouchListener t1=new OnTouchListener(){

			@Override
			public boolean onTouch(View p1, MotionEvent p2)
			{
				
				
				if(p2.getAction()==MotionEvent.ACTION_UP)
				{
					
					if(!isOpen){
						openBonuses();
					}
					else{
						closeBonuses();
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
					if(findBonus(tmpBonus.parent).isReceived)
					update(tmpBonus.id);
					
				}
				return true;
			}
		};
		
		Runnable r3=new Runnable(){

			@Override
			public void run()
			{
				bonuses_layout.addView( new Canv(prop.context,tmpBonus.cX,tmpBonus.cY,findBonus(tmpBonus.parent).cX,findBonus(tmpBonus.parent).cY,Color.GREEN));
				
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
			new Bonus(62,R.drawable.bonus91,"Начало пути..",11,150,0);
			new Bonus(63,R.drawable.bonus92,"Начало пути..",12,150,0);
			new Bonus(64,R.drawable.bonus93,"Начало пути..",13,150,0);
			new Bonus(65,R.drawable.bonus94,"Начало пути..",14,150,0);
			new Bonus(66,R.drawable.bonus95,"Начало пути..",8,150,0);
			new Bonus(67,R.drawable.bonus96,"Начало пути..",63,150,0);
			new Bonus(68,R.drawable.bonus97,"Начало пути..",64,150,0);
			
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
			
			new Bonus(69,R.drawable.bonus60,"Начало пути..",0,550,550);
			new Bonus(70,R.drawable.bonus61,"Начало пути..",69,150,0);
			new Bonus(71,R.drawable.bonus62,"Начало пути..",69,150,150);
			new Bonus(72,R.drawable.bonus63,"Начало пути..",69,0,150);
			new Bonus(73,R.drawable.bonus64,"Начало пути..",70,150,150);
			new Bonus(74,R.drawable.bonus65,"Начало пути..",70,150,0);
			new Bonus(75,R.drawable.bonus66,"Начало пути..",71,150,150);
			new Bonus(76,R.drawable.bonus67,"Начало пути..",72,0,150);
			new Bonus(77,R.drawable.bonus68,"Начало пути..",72,150,150);
			new Bonus(78,R.drawable.bonus69,"Начало пути..",73,150,150);
			new Bonus(79,R.drawable.bonus70,"Начало пути..",74,150,150);
			new Bonus(80,R.drawable.bonus71,"Начало пути..",76,150,150);
			new Bonus(81,R.drawable.bonus72,"Начало пути..",77,150,150);
			
			new Bonus(82,R.drawable.bonus73,"Начало пути..",0,-550,-550);
			new Bonus(83,R.drawable.bonus74,"Начало пути..",82,-150,0);
			new Bonus(84,R.drawable.bonus75,"Начало пути..",82,-150,-150);
			new Bonus(85,R.drawable.bonus76,"Начало пути..",82,0,-150);
			new Bonus(86,R.drawable.bonus77,"Начало пути..",83,-150,-150);
			new Bonus(87,R.drawable.bonus78,"Начало пути..",83,-150,0);
			new Bonus(88,R.drawable.bonus79,"Начало пути..",84,-150,-150);
			new Bonus(89,R.drawable.bonus80,"Начало пути..",85,0,-150);
			new Bonus(90,R.drawable.bonus81,"Начало пути..",85,-150,-150);
			new Bonus(91,R.drawable.bonus82,"Начало пути..",86,-150,-150);
			new Bonus(92,R.drawable.bonus83,"Начало пути..",87,-150,-150);
			new Bonus(93,R.drawable.bonus84,"Начало пути..",88,-150,-150);
			new Bonus(94,R.drawable.bonus85,"Начало пути..",89,-150,-150);
			new Bonus(95,R.drawable.bonus86,"Начало пути..",90,-150,-150);
			new Bonus(96,R.drawable.bonus87,"Начало пути..",91,-150,-150);
			new Bonus(97,R.drawable.bonus88,"Начало пути..",92,-150,-150);
			new Bonus(98,R.drawable.bonus89,"Начало пути..",94,-150,-150);
			new Bonus(99,R.drawable.bonus90,"Начало пути..",95,-150,-150);
			
			new Bonus(100,R.drawable.bonus01,"Начало пути..",0,550,-550);
			new Bonus(101,R.drawable.bonus02,"Начало пути..",100,0,-150);
			new Bonus(102,R.drawable.bonus03,"Начало пути..",100,150,-150);
			new Bonus(103,R.drawable.bonus04,"Начало пути..",100,150,0);
			new Bonus(104,R.drawable.bonus05,"Начало пути..",101,0,-150);
			new Bonus(105,R.drawable.bonus06,"Начало пути..",101,150,-150);
			new Bonus(106,R.drawable.bonus07,"Начало пути..",102,150,-150);
			new Bonus(107,R.drawable.bonus08,"Начало пути..",103,150,-150);
			new Bonus(108,R.drawable.bonus09,"Начало пути..",103,150,0);
			new Bonus(109,R.drawable.bonus10,"Начало пути..",104,150,-150);
			new Bonus(110,R.drawable.bonus11,"Начало пути..",105,150,-150);
			new Bonus(111,R.drawable.bonus12,"Начало пути..",106,150,-150);
			new Bonus(112,R.drawable.bonus13,"Начало пути..",107,150,-150);
			new Bonus(113,R.drawable.bonus14,"Начало пути..",108,150,-150);
			new Bonus(114,R.drawable.bonus15,"Начало пути..",109,150,-150);
			new Bonus(115,R.drawable.bonus16,"Начало пути..",110,150,-150);
			new Bonus(116,R.drawable.bonus17,"Начало пути..",111,150,-150);
			new Bonus(117,R.drawable.bonus18,"Начало пути..",112,150,-150);
			new Bonus(118,R.drawable.bonus19,"Начало пути..",113,150,-150);
			new Bonus(119,R.drawable.bonus20,"Начало пути..",114,150,-150);
			new Bonus(120,R.drawable.bonus21,"Начало пути..",118,150,-150);
			new Bonus(121,R.drawable.bonus22,"Начало пути..",116,150,-150);
			
			new Bonus(122,R.drawable.bonus23,"Начало пути..",0,-550,550);
			new Bonus(123,R.drawable.bonus24,"Начало пути..",122,0,150);
			new Bonus(124,R.drawable.bonus25,"Начало пути..",122,-150,150);
			new Bonus(125,R.drawable.bonus26,"Начало пути..",122,-150,0);
			new Bonus(126,R.drawable.bonus27,"Начало пути..",123,0,150);
			new Bonus(127,R.drawable.bonus28,"Начало пути..",123,-150,150);
			new Bonus(128,R.drawable.bonus29,"Начало пути..",124,-150,150);
			new Bonus(129,R.drawable.bonus30,"Начало пути..",125,-150,150);
			new Bonus(130,R.drawable.bonus31,"Начало пути..",125,-150,0);
			new Bonus(131,R.drawable.bonus32,"Начало пути..",126,-150,150);
			new Bonus(132,R.drawable.bonus33,"Начало пути..",127,-150,150);
			new Bonus(133,R.drawable.bonus34,"Начало пути..",128,-150,150);
			new Bonus(134,R.drawable.bonus35,"Начало пути..",129,-150,150);
			new Bonus(135,R.drawable.bonus36,"Начало пути..",130,-150,150);
			new Bonus(136,R.drawable.bonus37,"Начало пути..",131,-150,150);
			new Bonus(137,R.drawable.bonus38,"Начало пути..",132,-150,150);
			new Bonus(138,R.drawable.bonus39,"Начало пути..",133,-150,150);
			new Bonus(139,R.drawable.bonus40,"Начало пути..",134,-150,150);
			new Bonus(140,R.drawable.bonus41,"Начало пути..",135,-150,150);
			new Bonus(141,R.drawable.bonus43,"Начало пути..",136,-150,150);
			new Bonus(142,R.drawable.bonus44,"Начало пути..",138,-150,150);
			new Bonus(143,R.drawable.bonus45,"Начало пути..",140,-150,150);
			
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
			set.setTranslationZ(0);
			set.setOnTouchListener(t1);
			settingsLayout=new RelativeLayout(prop.context);
			settingsLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			settingsLayout.setBackgroundColor(Color.argb(192,110,110,110));
			settingsLayout.setOnTouchListener(t2);
			settingsLayout.setVisibility(View.INVISIBLE);
			settingsLayout.setTranslationZ(1);
			prop.activity.runOnUiThread(r1);
			musicVolume= new MusicVolume();
			new Languge();
		}
		
		public void closeSettings(){
			settingsLayout.setVisibility(View.INVISIBLE);
			settingsIsOpen=false;
			set.setZ(0);
		}
		
		Runnable r1=new Runnable(){

			@Override
			public void run()
			{
				prop.menuLayout.addView(set,p1);
				prop.menuLayout.addView(settingsLayout);
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
					set.setZ(11);
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
	
	class Languge{
		TextView text;
		TextView btn_text;
		ImageView picture;
		SoundPool sp;
		int t;
		Languge(){
			
			text=new TextView(prop.context);
			text.setGravity(Gravity.RIGHT);
			text.setTextSize(12);
			text.setTranslationX(140);
			text.setTranslationY(prop.screenH/3.5f);
			text.setLayoutParams(new LayoutParams(300,50));
			text.setTextColor(Color.BLUE);
			text.setTypeface(prop.ttf);
			text.setText(prop.words.get(Words.words.SETT_LANG));
			
			picture=new ImageView(prop.context);
			picture.setX(text.getX()+500);
			picture.setY(text.getY()-25);
			picture.setLayoutParams(new LayoutParams(500,100));
			picture.setScaleType(ScaleType.FIT_XY);
			picture.setImageResource(R.drawable.btn);
			picture.setOnTouchListener(t1);
			
			btn_text=new TextView(prop.context);
			btn_text.setGravity(Gravity.CENTER);
			btn_text.setTextSize(16);
			btn_text.setTranslationX(picture.getX());
			btn_text.setTranslationY(picture.getY());
			btn_text.setLayoutParams(picture.getLayoutParams());
			btn_text.setTextColor(Color.argb(255,100,100,255));
			btn_text.setTypeface(prop.ttf);
			btn_text.setText(prop.words.get(Words.words.LANGUAGE));
			
			sp=new SoundPool(10,AudioManager.STREAM_MUSIC,0);
			t=sp.load(prop.context,R.raw.music01,1);
			
			settingsLayout.addView(text);
			settingsLayout.addView(picture);
			settingsLayout.addView(btn_text);
		}
		void reLang(){
			text.setText(prop.words.get(Words.words.SETT_LANG));
			btn_text.setText(prop.words.get(Words.words.LANGUAGE));
			prop.shop.reLang();
			prop.btn_continue.reLang();
			prop.Btn_exit_game.reLang();
			prop.Btn_exit_menu.reLang();
			prop.Btn_play.reLang();
		}
		
			OnTouchListener t1=new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					if(p2.getAction()==MotionEvent.ACTION_UP){
						if(prop.words.lang==Words.language.RU)
						    prop.words.setLanguage(Words.language.EN);
						else
							prop.words.setLanguage(Words.language.RU);
						prop.onUi(r1);
					}
					return true;
				}	
		};
		
			Runnable r1=new Runnable(){

				@Override
				public void run()
				{sp.play(t,1f,1f,1,5,1f);
					
					reLang();
					musicVolume.reLang();
				}
			
		};
	}
	
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
		text.setText(prop.words.get(Words.words.MUSIC));
		
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
		void reLang(){
			text.setText(prop.words.get(Words.words.MUSIC));
			
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
					settingsLayout.addView(text);
					settingsLayout.addView(bar);
					settingsLayout.addView(point);
					settingsLayout.addView(barsLayout);
					}

			};
			
	}
	
	}
}
