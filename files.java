package sevash.livingSword;
import java.io.*;
import android.util.*;
import java.util.*;
import android.app.*;

public class files
{
	
		static void writeFile(final main_properties prop) {
			new Thread(){
				public void run(){
				
			try {
				File file = new File(prop.activity.getExternalFilesDir(""),"/f.txt");
				try{	
				if(!file.exists())
					   file.createNewFile();	
				}catch(Exception e){
				 e.printStackTrace();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("ver: 0.1\n");
				bw.write("language: "+prop.words.get(Words.words.LANGUAGE)+"\n");
				bw.write("player_position_x: "+String.valueOf(prop.playerPosX)+"\n");
				bw.write("player_position_y: "+String.valueOf(prop.playerPosY)+"\n");
				bw.write("exp: "+String.valueOf(prop.menu.playerLevel.points)+"\n");
				bw.write("money: "+String.valueOf(prop.money.money_count)+"\n");
				bw.write("musicVolume: "+String.valueOf(prop.menu.settings.musicVolume.volume)+"\n");
				bw.write("icons: ");
				for(Item item:prop.iconsBuyed){
					bw.write(String.valueOf(item.id)+"/");
				}
				bw.write("\n");
				bw.write("inven: ");
				for(Item item:prop.inv.items){
					bw.write(String.valueOf(item.id)+":"+String.valueOf(item.count)+"/");
				}
				bw.write("\n");
					bw.write("armor: ");
					if(prop.inv.armItems!=null)
					for(int i=0;i<prop.inv.armItems.length;i++){
						if(prop.inv.armItems[i]!=null)
								bw.write(String.valueOf(i)+"_"+String.valueOf(prop.inv.armItems[i].id)+":"+String.valueOf(prop.inv.armItems[i].count)+"/");
					}
				bw.write("\n");
				bw.write("bonuses: ");
				for(Menu.Bonuses.Bonus bonus:prop.menu.bonuses.bonusList)
				if(bonus.isReceived) bw.write(String.valueOf(bonus.id)+"/");
				bw.write("\n");
				bw.close();
				return;
			} catch (Exception e) { 
				e.printStackTrace();
			}
				}}.start();
		}
		
	public static void updateWorld(String dir1,String dir2, String fileName, String text1){
			if(dir2.contentEquals("world/")){
				try {
					File file1 = new File(dir1,dir2);
					file1.mkdirs();
					File file=new File(file1,fileName+".sso");
					try {
						if(!file.exists()){
							file.createNewFile();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					String[] text=text1.split(" ");
					BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
					bw.write(text[0]+" "+text[1]+" "+text[2]+"\n");
					bw.close();
					return;
				} catch (Exception e) {
					e.printStackTrace();		
				}
			}
		}
		public static boolean check(String dir1, String dir2,String s,main_properties prop){
			if(dir2.contentEquals("/world/")){
				try {
					File file1 = new File(dir1,dir2);
					if(!file1.exists())return false;
					File file=new File(file1,s+".sso");
					if(file.exists())return true;
					else return false;
					}
					catch(Exception e){
						e.printStackTrace();
					}
					}
					return false;
		}
		
	public static void readWorld(List list1,String fileName) {
        try {

            FileInputStream inputStream = new FileInputStream(new File(fileName));

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;

                while ((line = reader.readLine()) != null) {
                    list1.add(line);
                }
                inputStream.close();
			}
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
		
	public static void readFile(Activity activity, List list1,String fileName) {
        try {
			File file = new File(fileName);
			try{	
				if(!file.exists())
					file.createNewFile();	
			}catch(Exception e){
				e.printStackTrace();
			}
            FileInputStream inputStream = new FileInputStream(file);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;

                while ((line = reader.readLine()) != null) {
                    list1.add(line);
                }
                inputStream.close();
				}
        } catch (Exception e) {
			e.printStackTrace();
          }
    }
	
	
}
