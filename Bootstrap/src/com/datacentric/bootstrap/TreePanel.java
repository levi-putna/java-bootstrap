package com.datacentric.bootstrap;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tree.WebTree;

public class TreePanel extends JFrame{

	public TreePanel() {
		WebTree tree = new WebTree ();
        tree.setShowsRootHandles ( true );
        tree.setVisibleRowCount ( 4 );
        tree.setEditable ( true );

        WebScrollPane treeScroll = new WebScrollPane ( tree );
        treeScroll.setPreferredSize ( new Dimension ( 230, 200 ) );
        this.add(new GroupPanel ( treeScroll ));
	}
}
