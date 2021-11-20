package sevash.testx;
import java.io.*;
import android.util.*;
import java.util.*;

public class files
{
	
		static void writeFile(String dir,String sfile,String[] args) {
			
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
				bw.write("player_position_x: "+args[0]+"\n");
				bw.write("player_position_y: "+args[1]+"\n");
				bw.write("money: "+args[2]+"\n");
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
