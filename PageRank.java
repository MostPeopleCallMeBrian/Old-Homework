//importing for the arraylist, array, and scanner classes
import java.util.*; 
import java.io.*; 

public class PageRank {
  
//First define a website class, stores the URL, rank, and website text, also make it comparable
public static class Website implements Comparable<Website>{
 Double Rank;
 String URL;
 String Text;
 
 public int compareTo(Website compareWebsite){
 Double compareQuantity = ((compareWebsite).Rank);

 if (this.Rank < compareWebsite.Rank){
   return -1;
 }
 
 if(this.Rank > compareWebsite.Rank){
   return 1;
 }

 else{
   return 0;
 }
}
}

//Define a method to count the number of links in a website's text.
public static int CountLinks(String a){
   String[] arrOfStr = a.split("\\s+");
   int LinkCount = 0;
   for (int i=0; i<arrOfStr.length; i++){
     if (arrOfStr[i].contains("http:")){
       LinkCount++;
     }
   }
 return LinkCount; 
}

//Main method
  public static void main(String[] args) throws FileNotFoundException {
    
// variables, scanners, and arrays;
int NumberofPages = 0;
File file = new File("UWP.txt");
Scanner File = new Scanner(file);
Double DefaultValue = 1.0/NumberofPages;
Scanner keyboard = new Scanner(System.in);
CountLinks Count = new CountLinks();

//make an arraylist of the websites
List<Website> Websites = new ArrayList<Website>(10);

//loop through the file to populate the arraylist.
int j=0;
  while (File.hasNext()){
if (File.next() == "PAGE"){
  Website foo = new Website();
  Websites.add(j, foo);
  NumberofPages++;
  Websites.get(j).URL = File.nextLine();
  j++;
}
else {
  Websites.get(j).Text.concat(File.next());
}
}

//calculate the rank of each page. Set each rank to the default first.
for (int k=0; k<NumberofPages; k++){
  Websites.get(k).Rank = DefaultValue;
}
 
//formula: (1-0.l5)/NumberofPages + 0.15*(number of pages that link to it)/(Number of links to outgoing pages)
for (int i=0; i<Websites.size(); i++){
  int OutLinks = CountLinks(Websites.get(i).Text);
  int InLinks = 0;
  for (j=0; j<Websites.size(); j++){
    if (Websites.get(j).Text.contains(Websites.get(i).URL)){
      InLinks++;
  Websites.get(i).Rank= ((.85/NumberofPages)+(.15)*(InLinks/OutLinks));
    }
  }
}


//convert to an array for sorting
Website[] WebsitesArray = Websites.toArray (new Website[Websites.size()]);
//sort the array of websites
Arrays.sort(WebsitesArray);

//search function
System.out.println("Welcome! please input your search terms.");
String terms = keyboard.next();
terms = terms.toLowerCase();

//Simple function, take the the search terms, ignore the case, and compare them to the text of the websites.
//print out the URL of the website
for (int n=0; n<20; n++){
  if (terms==Websites.get(n).Text.toLowerCase()){
    System.out.println(Websites.get(n).URL);
  }
  
//close the scanners
File.close();
keyboard.close();
    
  
}
  }
}