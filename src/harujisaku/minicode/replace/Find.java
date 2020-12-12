package harujisaku.minicode.replace;

public class Find{
	private int start,end,length;
	
	public Find(){
		start=0;
		end=0;
		length=0;
	}
	
	public Find(int start,int end){
		this.start=start;
		this.end=end;
		length=end-start;
	}

	public int getStart(){
		return start;
	}
	
	public int getEnd(){
		return end;
	}
	
	public int length(){
		return length;
	}
}