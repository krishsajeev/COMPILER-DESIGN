import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.*; 

public class analyser {    
    public static HashMap<String,String> analyse(String line){
        //code to create symboltable
        ArrayList<String> keywords = new ArrayList<String>(Arrays.asList("and","as","assert","break", "class", "continue", "def", "del", "del", "elif", "print", "else", "except", "False", "finally", "for", "from", "global", "if", "import", "in", "lambda", "None", "nonlocal", "not", "or", "pass", "raise", "return", "True", "try", "while", "with", "yield"));
        ArrayList<String> operators = new ArrayList<String>(Arrays.asList("+","-","*","/","%","**","//",">","<",">=","<=", "and", "or", "not", "is", "is not", "in", "not in","&","|","^","~","<<",">>"));
        ArrayList<String> delimiters = new ArrayList<String>(Arrays.asList("+=","-=","*=","/=","%=","//=","**=","&=","|=","^=",">>=","<<=","==","!=", "^=", ":", "`",";","[","]","(",")","{","}","=",",")); 
        HashMap<String,String> symboltable=new HashMap<String,String>();
        if(line.charAt(0)=='#')
        {
            symboltable.put(line, "comment");
        }
        else
        {
            for (String val: line.split("[' '.]+")) //split by whitespace, dot
            {
                if(keywords.contains(val)) //checks keyword
                {
                    symboltable.put(val,"keyword");
                }
                else if(Pattern.matches("(\")+(.)*(\")",val) || Pattern.matches("(\')+(.)*(\')",val)) //checks string constant
                {
                    symboltable.put(val,"const");
                }
                else if(Pattern.matches("([a-z]|[A-Z]|[_])+([0-9])*", val))//checks identifier
                {
                    symboltable.put(val,"id");
                }
                else if(Pattern.matches("[0-9]+", val))//checks number constant
                {
                    symboltable.put(val,"const");
                }
                else if(operators.contains(val))//checks operator
                {
                    symboltable.put(val,"op");
                }
                else if(delimiters.contains(val)) //checks delimiter
                {
                    symboltable.put(val,"delimter");
                }
            }
        }
        return symboltable;
    }
    public static void main(String[] args)throws Exception{        
        FileReader fr=new FileReader("./testcase/simple_test.py"); //get file
        String line=new String(); //python is interpreted, so read line-by-line
        for(int i;(i=fr.read())!=-1;){ //until end-of-file
            if((char)i=='\n' || (char)i==';'){ //end-of-line
                HashMap<String,String> symboltable=analyse(line); //pass to analysis phase
                line=new String(); //make fresh line
                System.out.println(symboltable);
            }
            else{
                line+=((char)i); //add charachters
            }
        }
        fr.close();
    }
}