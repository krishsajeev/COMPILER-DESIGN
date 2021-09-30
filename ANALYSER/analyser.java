import java.io.FileReader;
public class analyser {
    public static void analyse(String line){
        //code to create symboltable
    }
    public static void main(String[] args)throws Exception{        
        FileReader fr=new FileReader("./testcase/src.py"); //get file
        String line=new String(); //python is interpreted, so read line-by-line
        for(int i;(i=fr.read())!=-1;){ //until end-of-file
            if((char)i=='\n' || (char)i==';'){ //end-of-line
                analyse(line); //pass to analysis phase
                line=new String(); //make fresh line
            }
            else{
                line+=((char)i); //add charachters
            }
        }
        fr.close();
    }
}
