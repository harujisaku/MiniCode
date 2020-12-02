package harujisaku.minicode;

import java.awt.Color;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.ListSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JPopupMenu;
import javax.swing.BorderFactory;
import javax.swing.text.Utilities;
import javax.swing.text.BadLocationException;

public class AutoCompletePanel{
	public SuggestString suggest = new SuggestString();
	private JList list;
	JEditorPane editorPane;
	private JPopupMenu popupMenu;
	private String subWord;
	private final int insertionPosition;

	public AutoCompletePanel(JEditorPane editorPane, int position, String subWord, Point location,int count) {
		this.insertionPosition = position;
		this.subWord = subWord;
		this.editorPane = editorPane;
		popupMenu = new JPopupMenu();
		popupMenu.removeAll();
		popupMenu.setOpaque(false);
		popupMenu.setBorder(null);
		list = createSuggestionList(position, subWord);
		if (list!=null) {
			popupMenu.add(list, BorderLayout.CENTER);
			popupMenu.show(editorPane, location.x, editorPane.getBaseline(0, 0) + location.y+location.y/(count==0?1:count));
		}
	}

	public void hide() {
		popupMenu.setVisible(false);
	}

	private JList createSuggestionList(final int position, final String subWord) {
		String[] data = suggest.serche(subWord);
		if (data.length==0) {
			return null;
		}
		JList list = new JList(data);
		list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setFocusable(false);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					insertSelection();
				}
			}
		});
		return list;
	}

	public boolean insertSelection() {
		if (list.getSelectedValue() != null) {
			try {
				final String selectedSuggestion = ((String) list.getSelectedValue()).substring(subWord.length());
				editorPane.getDocument().insertString(insertionPosition, selectedSuggestion, null);
				return true;
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			hide();
		}
		return false;
	}

	public void moveUp() {
		int index = Math.max(list.getSelectedIndex() - 1, 0);
		selectIndex(index);
	}

	public void moveDown() {
		int index = Math.min(list.getSelectedIndex() + 1, list.getModel().getSize() - 1);
		selectIndex(index);
	}

	private void selectIndex(int index) {
		final int position = editorPane.getCaretPosition();
		list.setSelectedIndex(index);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				editorPane.setCaretPosition(position);
			};
		});
	}
}
