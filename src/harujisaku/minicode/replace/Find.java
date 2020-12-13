package harujisaku.minicode.replace;

/**
* 検索結果を格納するクラスです.
*/

public class Find{
	protected int start,end,length;
	
	/**
	* 検索結果がなかった場合に使用するコンストラクタ
	*/
	
	public Find(){
		start=0;
		end=0;
		length=0;
	}
	
	/**
	デフォルトコンストラクタ.
	* @param start 検索結果の開始地点
	* @param end 検索結果の終了地点
	*/
	
	public Find(int start,int end){
		this.start=start;
		this.end=end;
		length=end-start;
	}

	/**
	* 開始地点を取得します.
	* @return 開始地点
	*/

	public int getStart(){
		return start;
	}
	
	/**
	* 終了地点を取得します.
	* @return 終了地点
	*/
	
	public int getEnd(){
		return end;
	}
	
	/**
	* 長さを取得します.
	* @return 長さを地点
	*/
	public int length(){
		return length;
	}
}