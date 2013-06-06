import java.lang.*;
import java.io.*;
import java.util.*;
public class trieNode{
    private static final double PI = 3.1416;
	int id;
    char trieChar;
    boolean isEnd;
    trieNode[] suffix= new trieNode[27];
    static String foundString=null;
void createTrie(char y){
    id =0;
    trieChar=y;
    isEnd=true;
    foundString=null;
}
// Inserts character 'c' with id 'insertedId' at position 'idToInsertAt'
void insertSuffixAt(char c,int insertedId, int idToInsertAt){

for (int i=0;i<27;i++){
if (suffix[i]!=null){
        if (suffix[i].id==idToInsertAt){
            suffix[i].insertInTrie(c,true,insertedId);
        }
        else suffix[i].insertSuffixAt(c,insertedId,idToInsertAt);
    }
}
}
int insertInTrie(char c, boolean ending, int currId){
    if (this.suffix[c-'a']==null){
        this.suffix[c-'a']= new trieNode();
        if (ending==true){
            if (isEnd)
                isEnd=false;
            this.suffix[c-'a'].trieChar=c;
            this.suffix[c-'a'].id=currId;
            this.suffix[c - 'a'].isEnd=true;
        }
    }
    return 1;
} 
// inserts a string at the beginning of the game.Call this with root object, s is string to insert, returns 1
int insertInString(String s, int p){
if (s.length()==1){
    if (suffix[s.charAt(0)-'a']!=null){
    suffix[s.charAt(0)-'a'].trieChar=s.charAt(0);
    suffix[s.charAt(0)-'a'].id=p;
    suffix[s.charAt(0)-'a'].isEnd=true;
}else {
    suffix[s.charAt(0)-'a']= new trieNode();
    suffix[s.charAt(0)-'a'].trieChar=s.charAt(0);
    suffix[s.charAt(0)-'a'].id=p;
    suffix[s.charAt(0)-'a'].isEnd=true;
}
    return 1;
}
else {
    if (suffix[s.charAt(0)-'a']==null)
    {
        suffix[s.charAt(0)-'a']= new trieNode();
        suffix[s.charAt(0)-'a'].trieChar=s.charAt(0);
        suffix[s.charAt(0)-'a'].id=p;
        suffix[s.charAt(0)-'a'].insertInString(s.substring(1,s.length()),p+1);
    }
    else{
        suffix[s.charAt(0)-'a'].id=p; 
        suffix[s.charAt(0)-'a'].insertInString(s.substring(1,s.length()),p+1);
    }
}return 1;
}

// prints out the current trie with id of node
int print(){
System.out.println("+++++++++++++++");
System.out.println("id  "+id);
System.out.println("char is "+trieChar);
System.out.println("+++++++++++++++");
for (int i=0;i<27;i++){
if (suffix[i]!=null){
suffix[i].print();
}
}
return 1;
}
// Runs on root node. Make suffix tree node all null so that garbage collector can collect it.
int reset(){
    isEnd=true;
for (int i=0;i<27;i++){
    suffix[i]=null;
}
return 1;
}
// Do a dfs search, as you travese the node append the current char.Only returns the string if id is found
// Int and another empty string on input  and string output with return string printed
String dfsSearchLetter(String currStr,int endId){
    String appendStr=new String(currStr + trieChar);
    //static String foundString=null;
    //System.out.println(appendStr);
for (int i=0;i<27;i++){
if (suffix[i]!=null){
        if (suffix[i].id==endId){
            foundString=new String(appendStr+suffix[i].trieChar);
            //System.out.println("Found string   "+ foundString);
            return foundString;
        }
        else suffix[i].dfsSearchLetter(appendStr,endId);
    }
}
return foundString;
}
//change y+radius in recursive call to addxy to whatever you want the vale to be , i.e y+k
void addXY(HashMap coord, double x, double y, double theta){
	final int radius =70 ;
	ArrayList nodesAt = new ArrayList();
if (trieChar=='#'){
	//x=0;
	//y=0;
	theta=-PI/2;
}
xycoord a= new xycoord();
a.x=x+(radius*Math.cos(theta));
a.y=Math.abs(y)+Math.abs(radius*Math.sin(theta));
coord.put(id,a);
System.out.println("Inserted " + trieChar +"  "+ id +"at  x=" + a.x + " at y=   "+ a.y);
	int count=0;
	for (int i=0;i<27;i++){
		if (suffix[i]!=null){
			nodesAt.add(i);
			count++;
		}
	}
	if (count==0)
		return;
	theta = PI/count;
	//ArrayList angles= new ArrayList(count);
	if (count%2==0){
		for (int i=0;i<count;i++){
			
			if (i<count/2)
			suffix[(Integer) nodesAt.get(i)].addXY(coord,a.x,a.y+radius,-PI/2+theta*(i-count/2));
			if (i>=count/2)
				suffix[(Integer) nodesAt.get(i)].addXY(coord,a.x,a.y+radius,-PI/2+theta*((i+1)-count/2));
		}
	}
	else {
		if (count>=1){
	suffix[(Integer) nodesAt.get(((count+1)/2)-1)].addXY(coord,a.x,a.y+radius,-PI/2);
	count--;
	for (int i=0;i<count/2;i++){
		suffix[(Integer) nodesAt.get(i)].addXY(coord,a.x,a.y+radius,-PI/2-theta*(count-i));
	}
	for (int i=count/2+1;i<=count;i++){
		suffix[(Integer) nodesAt.get(i)].addXY(coord,a.x,a.y+radius,-PI/2+theta*(i-(count/2)));
	}
	
		}
		
	}
		
	}
}
