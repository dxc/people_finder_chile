package chileayuda.chilepersonfinder.webservice.resources;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

public class Result implements Comparable<Result>{
	
	private static final SimpleDateFormat format = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss"
	);
	
	public String name;
	public String info;
	public String status;
	public String source;
	public String link;
	public Date timestamp;
	
	public Result(String timestamp, String info, String status, String source, String link)
		throws ParseException
	{
		this.timestamp = Result.format.parse(timestamp.substring(0,10)+" "+timestamp.substring(11,19));
		this.info = info;
		this.status = status;
		this.source = source;
		this.link = link;
		this.name = null;
	}
	
	public Result(String timestamp, String name, String info, String status, String source, String link)
		throws ParseException
	{
		this(timestamp,info,status,source,link);
		this.name = name;
	}
	
	public String toString(){
		return "Timestamp:"+timestamp+"\nName: "+name+"\nInfo:"+info+"\nStatus:"+status+"\n"+source+"\n"+link+"\n\n";
	}
	
	public int compareTo(Result result){
		return result.timestamp.compareTo(this.timestamp);
	}
	
	public String toJSon(){
		String s = String.format(
			"{%s\"texto\": \"%s\",\"estado\": \"%s\",\"fuente\": \"%s\",\"enlace\": \"%s\"}"
			,this.name == null ? "" : String.format("\"name\": \"%s\",",this.name)
			,this.info.replaceAll("\\u0022","\"")
			,this.status.replaceAll("\\u0022","\"")
			,this.source.replaceAll("\\u0022","\"")
			,this.link.replaceAll("\\u0022","\"")
		);
		return s;
	}
}