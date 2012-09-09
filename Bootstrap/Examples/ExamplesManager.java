/*
* This file is part of WebLookAndFeel library.
*
* WebLookAndFeel library is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* WebLookAndFeel library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.examples.groups;

import com.alee.examples.Example;
import com.alee.examples.ExampleGroup;
import com.alee.examples.FeatureState;
import com.alee.examples.WebLookAndFeelDemo;
import com.alee.examples.groups.android.AndroidStylingGroup;
import com.alee.examples.groups.breadcrumb.BreadcrumbsGroup;
import com.alee.examples.groups.button.ButtonsGroup;
import com.alee.examples.groups.collapsiblepane.CollapsiblePanesGroup;
import com.alee.examples.groups.colorchooser.ColorChoosersGroup;
import com.alee.examples.groups.combobox.ComboBoxesGroup;
import com.alee.examples.groups.date.DateChoosersGroup;
import com.alee.examples.groups.desktoppane.DesktopPaneGroup;
import com.alee.examples.groups.field.FieldsGroup;
import com.alee.examples.groups.filechooser.FileChoosersGroup;
import com.alee.examples.groups.futurico.FuturicoStylingGroup;
import com.alee.examples.groups.gallery.GalleryGroup;
import com.alee.examples.groups.image.ImagesGroup;
import com.alee.examples.groups.label.LabelsGroup;
import com.alee.examples.groups.list.ListsGroup;
import com.alee.examples.groups.menubar.MenuBarsGroup;
import com.alee.examples.groups.ninepatcheditor.NinePatchEditorGroup;
import com.alee.examples.groups.optionpane.OptionPanesGroup;
import com.alee.examples.groups.panel.PanelsGroup;
import com.alee.examples.groups.progress.ProgressGroup;
import com.alee.examples.groups.progressbar.ProgressBarsGroup;
import com.alee.examples.groups.scrollpane.ScrollPaneGroup;
import com.alee.examples.groups.slider.SlidersGroup;
import com.alee.examples.groups.splitpane.SplitPanesGroup;
import com.alee.examples.groups.statusbar.StatusBarsGroup;
import com.alee.examples.groups.tabbedpane.TabbedPanesGroup;
import com.alee.examples.groups.table.TablesGroup;
import com.alee.examples.groups.textarea.TextAreasGroup;
import com.alee.examples.groups.toolbar.ToolbarsGroup;
import com.alee.examples.groups.tooltip.TooltipsGroup;
import com.alee.examples.groups.transition.TransitionsGroup;
import com.alee.examples.groups.tree.TreesGroup;
import com.alee.extended.image.WebImage;
import com.alee.extended.panel.CenterPanel;
import com.alee.extended.window.WebProgressDialog;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.utils.FileUtils;
import com.alee.utils.ImageUtils;
import com.alee.utils.ReflectUtils;
import com.alee.utils.SwingUtils;
import com.alee.utils.file.FileDownloadListener;
import com.alee.utils.reflection.JarEntry;
import com.alee.utils.reflection.JarStructure;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: mgarin Date: 23.01.12 Time: 12:02
 */

public class ExamplesManager
{
    public static final ImageIcon icon1 =
            new ImageIcon ( ExamplesManager.class.getResource ( "icons/icon1.png" ) );
    public static final ImageIcon icon2 =
            new ImageIcon ( ExamplesManager.class.getResource ( "icons/icon2.png" ) );

    public static ImageIcon infoIcon =
            new ImageIcon ( ExamplesManager.class.getResource ( "icons/info.png" ) );

    private static List<ExampleGroup> exampleGroups = null;

    public static List<ExampleGroup> getExampleGroups ()
    {
        if ( exampleGroups == null )
        {
            exampleGroups = new ArrayList<ExampleGroup> ();
            exampleGroups.add ( new LabelsGroup () );
            exampleGroups.add ( new TooltipsGroup () );
            exampleGroups.add ( new ButtonsGroup () );
            exampleGroups.add ( new FieldsGroup () );
            exampleGroups.add ( new TextAreasGroup () );
            exampleGroups.add ( new ComboBoxesGroup () );
            exampleGroups.add ( new BreadcrumbsGroup () );
            exampleGroups.add ( new DateChoosersGroup () );
            exampleGroups.add ( new FileChoosersGroup () );
            exampleGroups.add ( new ColorChoosersGroup () );
            exampleGroups.add ( new ProgressBarsGroup () );
            exampleGroups.add ( new ProgressGroup () );
            exampleGroups.add ( new SlidersGroup () );
            exampleGroups.add ( new ToolbarsGroup () );
            exampleGroups.add ( new MenuBarsGroup () );
            exampleGroups.add ( new StatusBarsGroup () );
            exampleGroups.add ( new ListsGroup () );
            exampleGroups.add ( new TablesGroup () );
            exampleGroups.add ( new TreesGroup () );
            exampleGroups.add ( new PanelsGroup () );
            exampleGroups.add ( new SplitPanesGroup () );
            exampleGroups.add ( new ScrollPaneGroup () );
            exampleGroups.add ( new TabbedPanesGroup () );
            exampleGroups.add ( new CollapsiblePanesGroup () );
            exampleGroups.add ( new DesktopPaneGroup () );
            exampleGroups.add ( new OptionPanesGroup () );
            exampleGroups.add ( new GalleryGroup () );
            exampleGroups.add ( new TransitionsGroup () );
            exampleGroups.add ( new ImagesGroup () );
            exampleGroups.add ( new AndroidStylingGroup () );
            exampleGroups.add ( new FuturicoStylingGroup () );
            exampleGroups.add ( new NinePatchEditorGroup () );
        }
        return exampleGroups;
    }

    public static JarStructure createJarStructure ( final WebProgressDialog progress )
    {
        // Download listener in case of remote jar-file (for e.g. demo loaded from .jnlp)
        FileDownloadListener fdl = new FileDownloadListener ()
        {
            private int totalSize = 0;

            public void sizeDetermined ( int totalSize )
            {
                // Download started
                this.totalSize = totalSize;
                updateProgress ( 0 );
            }

            public void partDownloaded ( int totalBytesDownloaded )
            {
                // Some part loaded
                updateProgress ( totalBytesDownloaded );
            }

            public boolean shouldStopDownload ()
            {
                return false;
            }

            private void updateProgress ( int downloaded )
            {
                // Updating progress text
                progress.setTitleText ( "<html>Loading source files... <b>" +
                        FileUtils.getFileSizeString ( downloaded, 1 ) + "</b> of <b>" +
                        FileUtils.getFileSizeString ( totalSize, 1 ) + "</b> done</html>" );
            }

            public void fileDownloaded ( File file )
            {
                // Updating progress text
                progress.setTitleText ( "Creating source files structure..." );
            }

            public void fileDownloadFailed ( Throwable e )
            {
                // Updating progress text
                progress.setTitleText ( "Filed to download source files" );
            }
        };

        // Creating structure using any of classes contained inside jar
        progress.setTitleText ( "Creating source files structure..." );
        JarStructure jarStructure = ReflectUtils.getJarStructure ( ExamplesManager.class,
                Arrays.asList ( ".java", ".png", ".gif", ".jpg", ".txt", ".xml" ),
                Arrays.asList ( "com/alee", "licenses" ), fdl );

        // Updating some of package icons
        jarStructure.setPackageIcon ( WebLookAndFeelDemo.class.getPackage (),
                new ImageIcon ( WebLookAndFeelDemo.getIcons ().get ( 0 ) ) );
        for ( ExampleGroup exampleGroup : getExampleGroups () )
        {
            jarStructure.setClassIcon ( exampleGroup.getClass (),
                    ( ImageIcon ) exampleGroup.getGroupIcon () );
        }

        return jarStructure;
    }

    public static WebTabbedPane createExampleTabs ( WebLookAndFeelDemo owner,
                                                    WebProgressDialog load )
    {
        // All example groups
        load.setTitleText ( "Loading groups list" );
        List<ExampleGroup> exampleGroups = getExampleGroups ();
        load.setMinimum ( 0 );
        load.setMaximum ( exampleGroups.size () + 1 );
        load.setProgress ( 0 );

        // Example tabs
        WebTabbedPane exampleTabs = new WebTabbedPane ();
        exampleTabs.setTabbedPaneStyle ( TabbedPaneStyle.attached );

        // Retrieving progress component
        WebPanel progressComponent = ( WebPanel ) load.getMiddleComponent ();
        WebImage wi = ( WebImage ) progressComponent.getComponent ( 0 );

        // Creating all examples
        int progress = 1;
        for ( ExampleGroup group : exampleGroups )
        {
            // Updating progress state
            load.setTitleText ( "<html>Loading <b>" + group.getGroupName () + "</b> group" );
            load.setProgress ( progress );
            progress++;

            // Updating progress icons
            Icon gi = group.getGroupIcon ();
            wi.setImage ( ImageUtils
                    .combineIcons ( 2, wi.getImage (), ImageUtils.getBufferedImage ( gi ) ) );

            // Adding group view to new tab
            exampleTabs.addTab ( group.getGroupName (), gi, createGroupView ( owner, group ) );

            // Applying foreground settings
            exampleTabs.setSelectedForegroundAt ( exampleTabs.getTabCount () - 1,
                    group.getPreferredForeground () );

            // Applying specific group settings to tab
            group.modifyExampleTab ( exampleTabs.getTabCount () - 1, exampleTabs );
        }
        load.setProgress ( progress );

        return exampleTabs;
    }

    public static Component createGroupView ( WebLookAndFeelDemo owner, ExampleGroup group )
    {
        // Creating group view
        Component exampleView;
        List<Example> examples = group.getGroupExamples ();
        if ( group.isSingleExample () && examples.size () == 1 )
        {
            Example example = examples.get ( 0 );
            exampleView = example.getPreview ( owner );
        }
        else
        {
            WebPanel groupPanel = group.isShowWatermark () ? new ImagePanel () : new WebPanel ();

            int rowsAmount = examples.size () > 1 ? examples.size () * 2 - 1 : 1;
            double[] rows = new double[ 6 + rowsAmount ];
            rows[ 0 ] = TableLayout.FILL;
            rows[ 1 ] = 20;
            rows[ 2 ] = TableLayout.PREFERRED;
            for ( int i = 3; i < rows.length - 3; i++ )
            {
                rows[ i ] = TableLayout.PREFERRED;
            }
            rows[ rows.length - 3 ] = TableLayout.PREFERRED;
            rows[ rows.length - 2 ] = 20;
            rows[ rows.length - 1 ] = TableLayout.FILL;

            double[] columns = { 20, 1f - group.getContentPartSize (), TableLayout.PREFERRED,
                    TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                    TableLayout.PREFERRED, group.getContentPartSize (), 20 };

            TableLayout groupLayout = new TableLayout ( new double[][]{ columns, rows } );
            groupLayout.setHGap ( 4 );
            groupLayout.setVGap ( 4 );
            groupPanel.setLayout ( groupLayout );

            groupPanel.add ( group.modifySeparator ( new WebSeparator ( WebSeparator.VERTICAL ) ),
                    "2,0,2," + ( rows.length - 1 ) );
            groupPanel.add ( group.modifySeparator ( new WebSeparator ( WebSeparator.VERTICAL ) ),
                    "4,0,4," + ( rows.length - 1 ) );
            groupPanel.add ( group.modifySeparator ( new WebSeparator ( WebSeparator.VERTICAL ) ),
                    "6,0,6," + ( rows.length - 1 ) );

            groupPanel.add ( group.modifySeparator ( new WebSeparator ( WebSeparator.HORIZONTAL ) ),
                    "0,2," + ( columns.length - 1 ) + ",2" );
            groupPanel.add ( group.modifySeparator ( new WebSeparator ( WebSeparator.HORIZONTAL ) ),
                    "0," + ( rows.length - 3 ) + "," + ( columns.length - 1 ) + "," +
                            ( rows.length - 3 ) );

            int row = 3;
            for ( Example example : examples )
            {
                // Title & description
                groupPanel.add ( createDescription ( owner, example, group ), "1," + row );

                // Marks
                groupPanel.add ( createMarks ( owner, example ), "3," + row );

                // Source code
                groupPanel.add ( createSourceButton ( owner, example ), "5," + row );

                // Preview
                groupPanel.add ( createPreview ( owner, example ), "7," + row );

                // Rows separator
                if ( row > 3 )
                {
                    groupPanel.add ( group
                            .modifySeparator ( new WebSeparator ( WebSeparator.HORIZONTAL ) ),
                            "0," + ( row - 1 ) + "," + ( columns.length - 1 ) + "," + ( row - 1 ),
                            0 );
                }

                row += 2;
            }

            exampleView = groupPanel;
        }
        return exampleView;
    }

    private static Component createDescription ( final WebLookAndFeelDemo owner, Example example,
                                                 ExampleGroup group )
    {
        Color foreground = group.getPreferredForeground ();

        String title = example.getDescription () == null ? example.getTitle () :
                "<html><p align=right>" + example.getTitle () + "<br><font color=gray size=2>" +
                        example.getDescription () + "</font></p></html>";

        WebLabel description = new WebLabel ( title, WebLabel.RIGHT );
        description.setForeground ( foreground );
        return description;
    }

    private static Component createMarks ( final WebLookAndFeelDemo owner, final Example example )
    {
        final FeatureState fs = example.getFeatureState ();
        ImageIcon fsIcon = fs.getIcon ();
        final WebLabel featureState = new WebLabel ( fsIcon );
        TooltipManager.setTooltip ( featureState, fsIcon, fs.getDescription (), TooltipWay.up );
        featureState.addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                owner.showLegend ( featureState, fs );
            }
        } );
        return new CenterPanel ( SwingUtils.setBorder ( featureState, 4 ), false, true );
    }

    private static Component createSourceButton ( final WebLookAndFeelDemo owner,
                                                  final Example example )
    {
        final Class classType = example.getClass ();
        final String cn = ReflectUtils.getJavaClassName ( classType );

        final WebButton sourceButton = WebButton.createIconWebButton ( JarEntry.javaIcon );
        TooltipManager.setTooltip ( sourceButton, JarEntry.javaIcon, cn, TooltipWay.up );
        sourceButton.setRolloverDecoratedOnly ( true );
        sourceButton.setFocusable ( false );

        sourceButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                owner.showSource ( classType );
            }
        } );

        return new CenterPanel ( sourceButton, false, true );
    }

    private static Component createPreview ( WebLookAndFeelDemo owner, Example example )
    {
        WebPanel previewPanel = new WebPanel ();
        previewPanel.setOpaque ( false );
        previewPanel.setLayout ( new TableLayout ( new double[][]{
                { example.isFillWidth () ? TableLayout.FILL : TableLayout.PREFERRED },
                { TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL } } ) );

        previewPanel.add ( example.getPreview ( owner ), "0,1" );

        return previewPanel;
    }

    public static String createLongString ()
    {
        return FileUtils.readToString ( ExamplesManager.class, "resources/text.txt" );
    }
}
