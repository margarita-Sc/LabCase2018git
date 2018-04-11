package Fase1;

public class Request {
	public String source;
	public String target;
	public String id;
	
	public Request (String i, String s, String t) {
		source= s;
		target= t;
		id=i;
	}
	public boolean equals(Request obj) { 
        boolean result=false;
  try {
      result= obj.id.equalsIgnoreCase(this.id) && 
        obj.source.equalsIgnoreCase(this.source) &&    obj.target.equalsIgnoreCase(this.target);
        } catch (Exception e) {
              //obj is null or some of the attributes are null
              result=false;
        }
        return result;
  }
}
