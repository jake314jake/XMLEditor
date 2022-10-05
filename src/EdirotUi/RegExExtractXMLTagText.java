package EdirotUi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class RegExExtractXMLTagText {
 
    
        static String XMLTagPattern( String XMLstring,String Tag) {
        	
    
    //create a pattern object
        	//(.+?) [^<]* [\s\S]*?
    Pattern pattern = Pattern.compile("<"+Tag+"[^>]*>([\\\s\\S]*?)</"+Tag+">");
    //create a matcher object
    Matcher matcher = pattern.matcher(XMLstring);
    String result="";
    int count=0;
    while( matcher.find() ) {
    	System.out.println( "id= "+count++ );
        System.out.println( matcher.group(1) );
        result+=matcher.group(1);
        result+="\n";
        
    }
    return result;
        }
            	
}  
   

    
