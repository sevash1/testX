package sevash.testx;
import java.io.*;
import android.util.*;
import java.util.*;

public class files
{
	
		static void writeFile(final main_properties prop, final String dir,final String sfile,final String[] args) {
			new Thread(){
				public void run(){
				
			if(sfile.contentEquals("f.txt")){
			try {
				File file = new File(dir,sfile);
				try {
					if(!file.exists()){
					file.createNewFile();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("player_position_x: "+String.valueOf(prop.playerPosX)+"\n");
				bw.write("player_position_y: "+String.valueOf(prop.playerPosY)+"\n");
				bw.write("money: "+String.valueOf(prop.money.money_count)+"\n");
				bw.write("musicVolume: "+String.valueOf(prop.menu.settings.musicVolume.volume)+"\n");
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
				bw.close();
				return;
			} catch (Exception ignore) {}
		}
		
		if(sfile=="error.txt"){
			
		
			try {


				File file = new File(dir, sfile);
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
				bw.write(args[0]+"\n");

				bw.close();

			} catch (Exception e) {
				
			}
		}
		else{
			try{
				File file = new File(dir, sfile);
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
					for(int h=0;h<args.length;h++)
				bw.write(args[h]);

				bw.close();

			} catch (Exception e) {
			
			}
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
				} catch (Exception ignore) {}
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
						files.writeFile(prop,prop.activity.getExternalFilesDir("").toString(),"error.txt",(new String[]{e.toString()}));
					
						return false;}
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

		}
    }
		
	public static void readFile(List list1,String fileName) {
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
			
          }
    }
	
	
}
