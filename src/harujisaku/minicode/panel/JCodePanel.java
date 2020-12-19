package harujisaku.minicode.panel;

import harujisaku.minicode.pane.JCodePane;

import java.io.File;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
* コードを表示するためのスクロール付きのペインです.
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class JCodePanel extends JScrollPane{
	private JCodePane jcodePane;
	private LineNumberView lineNumberView;
	/**
	* デフォルトコンストラクタ.
	* タブ幅が4の{@link JCodePane}を作成します.
	*/
	
	public JCodePanel(){
		jcodePane	= new JCodePane(4);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
		lineNumberView  = new LineNumberView(jcodePane);
		setRowHeaderView(lineNumberView);
	}
	
	/**
	* タブサイズを指定して作成します.
	* @param tabSize タブのサイズ
	*/
	
	public JCodePanel(int tabSize){
		jcodePane	= new JCodePane(tabSize);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
		setRowHeaderView(new LineNumberView(jcodePane));
	}
	
	/**
	* タブサイズと読み込むファイルを指定して作成します.
	* @param tabSize タブのサイズ
	* @param file 読み込むファイル
	*/
	
	public JCodePanel(int tabSize,File file){
		jcodePane = new JCodePane(tabSize,file);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
		setRowHeaderView(new LineNumberView(jcodePane));
	}
	
	/**
	* {@link JCodePane}を取得します.
	* @return JCodePane
	*/
	
	public JCodePane getCodePane(){
		return jcodePane;
	}
	
	public void updateFont(){
		lineNumberView.updateFont();
	}
	
	class LineNumberView extends JComponent {
	private static final int MARGIN = 5;
	private final JTextPane textArea;
	private FontMetrics fontMetrics;
	private int topInset;
	private int fontAscent;
	private int fontHeight;
	private int fontDescent;
	private  int fontLeading;
	public Font font;

	public LineNumberView(JTextPane textArea) {
		this.textArea = textArea;
		font = UIManager.getFont("TextPane.font");
		if (font==null) {
			System.out.println("null");
			font=textArea.getFont();
		}
		fontMetrics = textArea.getFontMetrics(font);
		fontHeight	= fontMetrics.getHeight();
		fontAscent	= fontMetrics.getAscent();
		fontDescent = fontMetrics.getDescent();
		fontLeading = fontMetrics.getLeading();
		topInset	= textArea.getInsets().top;

		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void insertUpdate(DocumentEvent e) {
				repaint();
			}
			@Override public void removeUpdate(DocumentEvent e) {
				repaint();
			}
			@Override public void changedUpdate(DocumentEvent e) {}
		});
		textArea.addComponentListener(new ComponentAdapter() {
			@Override public void componentResized(ComponentEvent e) {
				revalidate();
				repaint();
			}
		});
		Insets i = textArea.getInsets();
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY),
			BorderFactory.createEmptyBorder(i.top, MARGIN, i.bottom, MARGIN - 1)));
		setOpaque(true);
		setBackground(Color.WHITE);
		setFont(font);
	}
	
	public void updateFont(){
		font = UIManager.getFont("TextPane.font");
		if (font==null) {
			font=textArea.getFont();
		}
		fontMetrics = getFontMetrics(font);
		fontHeight	= fontMetrics.getHeight();
		fontAscent	= fontMetrics.getAscent();
		fontDescent = fontMetrics.getDescent();
		fontLeading = fontMetrics.getLeading();
		topInset	= textArea.getInsets().top;
	}
	
	private int getComponentWidth() {
		Document doc	= textArea.getDocument();
		Element root	= doc.getDefaultRootElement();
		int lineCount = root.getElementIndex(doc.getLength());
		int maxDigits = Math.max(3, String.valueOf(lineCount).length());
		Insets i = getInsets();
		return maxDigits * fontMetrics.stringWidth("0") + i.left + i.right;
		//return 48;
	}
	private int getLineAtPoint(int y) {
		Element root = textArea.getDocument().getDefaultRootElement();
		int pos = textArea.viewToModel(new Point(0, y));
		return root.getElementIndex(pos);
	}
	@Override public Dimension getPreferredSize() {
		return new Dimension(getComponentWidth(), textArea.getHeight());
	}
	@Override protected void paintComponent(Graphics g) {
		updateFont();
		g.setColor(getBackground());
		Rectangle clip = g.getClipBounds();
		g.fillRect(clip.x, clip.y, clip.width, clip.height);

		g.setColor(getForeground());
		int base	= clip.y;
		int start = getLineAtPoint(base);
		int end	 = getLineAtPoint(base + clip.height);
		int y		 = start * fontHeight;
		int rmg	 = getInsets().right;
		for (int i = start; i <= end; i++) {
			String text = String.valueOf(i + 1);
			int x = getComponentWidth() - rmg - fontMetrics.stringWidth(text);
			y += fontAscent;
			g.drawString(text, x, y);
			y += fontDescent + fontLeading;
		}
	}
}
}
