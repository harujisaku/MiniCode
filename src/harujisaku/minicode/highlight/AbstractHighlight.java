package harujisaku.minicode.highlight;

import java.awt.Color;

/**
* ハイライトの定義の抽象クラスです
*/

public abstract class AbstractHighlight {
	
	/**
	* 空白文字の正規表現
	*/
	
	public String WHITE_SPACE_REGEX = "[ /\\\\(\\)\"':,.;<>~\\!@#$%\\^&*\\|\\+=\\[\\]\\{\\}`\\?\\-…\\t]";
	/**
	デフォルトの色の文字の正規表現
	*/
	public String[] DEFAULT_KEYWORD={""};
	/**
	* 黒の文字の正規表現
	*/
	public String[] BLACK_KEYWORDS={""};
	/**
	* 青の文字の正規表現
	*/
	public String[] BLUE_KEYWORDS={""};
	/**
	* シアンの文字の正規表現
	*/
	public String[] CYAN_KEYWORDS={""};
	/**
	* 暗い灰色の文字の正規表現
	*/
	public String[] DARK_GRAY_KEYWORDS={""};
	/**
	* 灰色の文字の正規表現
	*/
	public String[] GRAY_KEYWORDS={""};
	/**
	* 緑の文字の正規表現
	*/
	public String[] GREEN_KEYWORDS={""};
	/**
	* 明るい灰色の文字の正規表現
	*/
	public String[] LIGHT_GRAY_KEYWORDS={""};
	/**
	* マゼンタの文字の正規表現
	*/
	public String[] MAGENTA_KEYWORDS={""};
	/**
	* オレンジの文字の正規表現
	*/
	public String[] ORANGE_KEYWORDS={""};
	/**
	* ピンクの文字の正規表現
	*/
	public String[] PINK_KEYWORDS={""};
	/**
	* 赤の文字の正規表現
	*/
	public String[] RED_KEYWORDS={""};
	/**
	* 白の文字の正規表現
	*/
	public String[] WHITE_KEYWORDS={""};
	/**
	* 黄色の文字の正規表現
	*/
	public String[] YELLOW_KEYWORDS={""};
	/**
	* コーテーションマークのときの動作
	* @return デフォルトは黒色
	*/
	public Color quotation(){
		return Color.BLACK;
	};
}