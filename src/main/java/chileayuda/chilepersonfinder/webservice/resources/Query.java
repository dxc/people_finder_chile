package chileayuda.chilepersonfinder.webservice.resources;

public class Query{
	
	private String name;
	private String info;
	
	public Query(String name, String info){
		this.name = name.replaceAll("\\p{Space}+"," ");
		if(info != null)
			this.info = info.replaceAll("\\p{Space}+"," ");
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getInfo(){
		return this.info;
	}
	
	public String getAll(){
		return this.name + (this.info != null ? " "+this.info : "");
	}
	
	public String toString(){
		return "Name: "+this.name+"\tInfo: "+this.info;
	}
}