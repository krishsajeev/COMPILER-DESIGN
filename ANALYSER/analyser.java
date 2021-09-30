import java.io.FileReader;
public class analyser {
    public static void analyse(String line){

    }
    public static void main(String[] args)throws Exception{        
        FileReader fr=new FileReader("./testcase/src.py");
        String line=new String();
        for(int i;(i=fr.read())!=-1;){
            if((char)i=='\n'){
                analyse(line);
                line=new String();
            }
            else{
                line+=((char)i);
            }
        }
        fr.close();
    }
}
