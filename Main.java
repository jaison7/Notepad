/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//package javaapplication1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.FreeTTS;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Checkbox;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.*;
//import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;



// About class

// About class
class notepad extends JFrame implements ActionListener
{

    Container c;
    public JScrollPane sc;
    public JScrollPane sc2;
    public JTextArea t;

    private JMenuBar menubar;

    private JMenu file;
    private JMenuItem file_new;
    private JMenuItem file_open;
    private JSeparator file_sep1;
    private JMenuItem file_save;
    private JMenuItem file_save_as;
    private JSeparator file_sep2;
    private JMenuItem file_print;
    private JSeparator file_sep3;
    private JMenuItem file_close;
    private JMenuItem file_exit;

    private JMenu edit;
    private JMenuItem edit_undo;
    private JMenuItem edit_redo;
    private JSeparator edit_sep1;
    private JMenuItem edit_copy;
    private JMenuItem edit_cut;
    private JMenuItem edit_paste;
    private JMenuItem edit_delete;
    private JSeparator edit_sep2;
    private JMenuItem edit_find;
    private JMenuItem edit_find_next;
    private JMenuItem edit_replace;
    private JSeparator edit_sep3;
    private JMenuItem edit_selectall;
    private JMenuItem edit_timedate;

    private JMenu format;
    private JMenuItem format_font;
    private JMenu convert;
    private JMenuItem str2uppr, str2lwr;
    private JCheckBoxMenuItem format_wordwarp;

    private JMenu help;
    private JMenuItem help_about;

    private JMenu speech;
    private JMenuItem play;
    private JMenuItem pause;
    private JMenuItem resume;
    private JMenuItem stop;

    private JMenu tools;
    private JMenuItem dict;
    private JCheckBoxMenuItem transl;


    UndoManager undo = new UndoManager();
    UIManager.LookAndFeelInfo lnf[];

    JFrame jf =new JFrame();     //Dictionary Tooltip
    JToolTip jtp=new JToolTip();

    JPanel panel = new JPanel();
    String subject[]={"hi","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello"};
    JList list = new JList(subject);


    find finder;
    font_chooser fc;
    about abt;
    int jtpheight;
    String path, content;
    String definition;

    //transliteration variables
    Transliteration trans;



    public notepad()
    {
        super("Untitled - Notepad+");



        trans = new Transliteration();

        //===translit code ends====

	
	this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        Container c = getContentPane();
        t = new JTextArea("", 5,5);
        t.setFont(new Font("sans serif", Font.BOLD,24));
        //t.setFont(new Font("Verdana",Font.PLAIN, 12));

        sc = new JScrollPane(t, sc.VERTICAL_SCROLLBAR_AS_NEEDED, sc.HORIZONTAL_SCROLLBAR_AS_NEEDED); //adding scrollbar to text area;
        c.add(sc);

        sc2=new JScrollPane(list, sc.VERTICAL_SCROLLBAR_AS_NEEDED, sc.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //c.add(sc2,BorderLayout.EAST);
        //sc2.setVisible(false);


        menubar = new JMenuBar();

        file = new JMenu("File");
        file_new = new JMenuItem("New");
        file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        file_new.addActionListener(this);
        file.add(file_new);
        file_open = new JMenuItem("Open");
        file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        file_open.addActionListener(this);
        file.add(file_open);
        file_sep1 = new JSeparator();
        file.add(file_sep1);
        file_save = new JMenuItem("Save");
        file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        file_save.addActionListener(this);
        file.add(file_save);
        file_save_as = new JMenuItem("Save As");
        file_save_as.addActionListener(this);
        file.add(file_save_as);
        file_sep2 = new JSeparator();
        file.add(file_sep2);
        file_print = new JMenuItem("Print");
        file_print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        file_print.addActionListener(this);
        file.add(file_print);
        file_sep3 = new JSeparator();
        file.add(file_sep3);
        file_close = new JMenuItem("Close");
        file_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
        file_close.addActionListener(this);
        file.add(file_close);
        file_exit = new JMenuItem("Exit");
        file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        file_exit.addActionListener(this);
        file.add(file_exit);
        menubar.add(file);

        edit = new JMenu("Edit");
        edit_undo = new JMenuItem("Undo");
        edit_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        edit_undo.addActionListener(this);
        edit.add(edit_undo);
        edit_redo = new JMenuItem("Redo");
        edit_redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        edit_redo.addActionListener(this);
        edit.add(edit_redo);
        edit_sep1 = new JSeparator();
        edit.add(edit_sep1);
        edit_copy = new JMenuItem("Copy");
        edit_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        edit_copy.addActionListener(this);
        edit.add(edit_copy);
        edit_cut = new JMenuItem("Cut");
        edit_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        edit_cut.addActionListener(this);
        edit.add(edit_cut);
        edit_paste = new JMenuItem("Paste");
        edit_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        edit_paste.addActionListener(this);
        edit.add(edit_paste);
        edit_delete = new JMenuItem("Delete");
        edit_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        edit_delete.addActionListener(this);
        edit.add(edit_delete);
        edit_sep2 = new JSeparator();
        edit.add(edit_sep2);
        edit_find = new JMenuItem("Find");
        edit_find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        edit_find.addActionListener(this);
        edit.add(edit_find);
        edit_find_next = new JMenuItem("Find Next");
        edit_find_next.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        edit_find_next.addActionListener(this);
        edit.add(edit_find_next);
        edit_replace = new JMenuItem("Replace");
        edit_replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        edit_replace.addActionListener(this);
        edit.add(edit_replace);
        edit_sep3 = new JSeparator();
        edit.add(edit_sep3);
        edit_selectall = new JMenuItem("Select All");
        edit_selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        edit_selectall.addActionListener(this);
        edit.add(edit_selectall);
        edit_timedate = new JMenuItem("Time/Date");
        edit_timedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        edit_timedate.addActionListener(this);
        edit.add(edit_timedate);
        menubar.add(edit);

        format = new JMenu("Format");
        format_font = new JMenuItem("Font");
        format_font.addActionListener(this);
        format.add(format_font);

        convert = new JMenu("Convert");
        str2uppr = new JMenuItem("To Uppercase...");
        str2uppr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK));
        str2uppr.addActionListener(this);
        convert.add(str2uppr);
        str2lwr = new JMenuItem("To Lowercase...");
        str2lwr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK));
        str2lwr.addActionListener(this);
        convert.add(str2lwr);
        format.add(convert);

        format_wordwarp = new JCheckBoxMenuItem("Word Warp");
        format_wordwarp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        format_wordwarp.addActionListener(this);
        format.add(format_wordwarp);
        menubar.add(format);

	speech=new JMenu("Speech");
	play=new JMenuItem("Play");
	play.addActionListener(this);
	speech.add(play);

	/*pause=new JMenuItem("Pause");
	pause.addActionListener(this);
	speech.add(pause);

	resume=new JMenuItem("Resume");
	resume.addActionListener(this);
	speech.add(resume);

	stop=new JMenuItem("Stop");
	stop.addActionListener(this);
	speech.add(stop);*/

	menubar.add(speech);


	tools =new JMenu("Tools");
	transl=new JCheckBoxMenuItem("Transliteration");
        transl.addActionListener(this);
	transl.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
	tools.add(transl);

	dict=new JMenuItem("Dictionary");
	dict.addActionListener(this);
	dict.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
	tools.add(dict);
	menubar.add(tools);


        help = new JMenu("Help");
        help_about = new JMenuItem("About");
        help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        help_about.addActionListener(this);
        help.add(help_about);
        menubar.add(help);

        c.add(menubar, BorderLayout.NORTH);





        // undo manager
      /*  Document doc= t.getDocument();
        doc.addUndoableEditListener(
                new UndoableEditListener( )
                {
                    public void undoableEditHappened( UndoableEditEvent event )
                    {
                        undo.addEdit(event.getEdit());
                    }
                }
        );*/


        // find_window
        finder = new find(this);
        finder.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // font chooser
        fc = new font_chooser(this);
        abt = new about(this);


        // set window size
        int w = 400;
        int h = 450;
        setSize(400, 450);
        // set window position
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(center.x-w/2, center.y-h/2);
        this.setVisible(true);
        path = "";

        t.addKeyListener(new kListenerback());


        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jf.setUndecorated(true);
        jf.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);


    }
    public void dictionary()
    {

	dict d1 = new dict();
        String full="";


    jf.setSize(300,150);  //jtpheight

    //jtp.setBackground(Color.TRANSLUCENT);



    jf.addWindowFocusListener(new wListener());
    jf.addKeyListener(new kListener());


	if((t.getText().equals("")) == false)
	{
		if(t.getSelectedText() != null)
		{
			 full = t.getSelectedText().trim();
			if(full.equals("") == true)  // "        "
				definition="No text selected. Please select some text to and the press Ctrl + D";
			else if(full.indexOf(" ") == -1)
			{

				d1.init(full.substring(0,1));
				definition=d1.finds(full);
                                //jtpheight=d1.finds(full).length()/60;
			}
			else
			{
				String word[] = full.split(" ");
				d1.init(word[0].substring(0,1));
				definition=d1.finds(word[0]);
                                //jtpheight=d1.finds(word[0]).length()/60;
			}
		}
		else
		                definition="No text selected. Please select some text to and the press Ctrl + D";
	}
	else
	{
                definition="No Definition Found";

	}





    jtp.setTipText("<html><body><b>"+full+" :</b><br/> " + definition + "</body></html>");
    jf.add(jtp);
    jf.setLocationRelativeTo(getRootPane());
    jf.setVisible(true);

    }


    public void alternates(String old)
    {


        list.setSize(300, 400);
        list.setVisible(true);

    }



    Boolean vowel(char chr)
    {
            if(chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u')
                    return true;
            else
                    return false;
    }
    Boolean consonant(char chr)
    {
            if(chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u')
                    return false;
            else
                    return true;
    }



    public void actionPerformed(ActionEvent e){

        if(e.getSource()==file_new)
            file_new();
        else if(e.getSource()==file_open)
            file_open();
        else if(e.getSource()==file_save)
            file_save();
        else if(e.getSource()==file_save_as)
            file_save_as();
        else if(e.getSource()==file_print)
            file_print();
        else if(e.getSource()==file_close)
            file_close();
        else if(e.getSource()==file_exit)
            file_exit();

        else if(e.getSource()==edit_undo)
            edit_undo();
        else if(e.getSource()==edit_redo)
            edit_redo();
        else if(e.getSource()==edit_cut)
            edit_cut();
        else if(e.getSource()==edit_copy)
            edit_copy();
        else if(e.getSource()==edit_paste)
            edit_paste();
        else if(e.getSource()==edit_delete)
            edit_delete();
        else if(e.getSource()==edit_find)
            edit_find();
        else if(e.getSource()==edit_find_next)
            edit_find_next();
        else if(e.getSource()==edit_replace)
            edit_replace();
        else if(e.getSource()==edit_selectall)
            edit_selectall();
        else if(e.getSource()==edit_timedate)
            edit_timedate();

        else if(e.getSource()==format_font)
            format_font();
        else if(e.getSource()==str2uppr)
            str2uppr();
        else if(e.getSource()==str2lwr)
            str2lwr();
        else if(e.getSource()==format_wordwarp)
            format_wordwarp();
	else if(e.getSource() == dict)
	    dictionary();
        else if(e.getSource()==transl)
        {
            if(transl.getState()==true)
            {
            dict.setEnabled(false);
            speech.setEnabled(false);
            }
            else
            {
                speech.setEnabled(true);
                 dict.setEnabled(true);
            }
        }
        else if(e.getSource() == play)
	    m_play();
        else if(e.getSource()==help_about)
            help_about();
    }


    public void m_play()
    {
	try
	{
	TTS tts_obj = new TTS();




	if(t.getSelectionStart() != t.getSelectionEnd())

		tts_obj.speakText(t.getSelectedText());

	else
		
		JOptionPane.showMessageDialog(null, "No text selected");
	}

	catch(Exception ex)
	{

	}

    }

    public void file_new(){
        if(t.getText().equals("") || t.getText().equals(content))
        {
            t.setText("");
            content = "";
            path = "";
            setTitle("Untitled - Notepad+");
        }
        else
        {
            int a = JOptionPane.showConfirmDialog(null, "The text has been changed\nDo you want to save the changes?");
            if(a==0)
                file_save();
            else if(a==1)
            {
                t.setText("");
                path = "";
                setTitle("untitled - Notepad");
            }
            else if(a==2)
                return;
        }
    }

    public void file_open(){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int r=fc.showOpenDialog(this);
        if(r==fc.CANCEL_OPTION)
            return;
        File myfile = fc.getSelectedFile();
        if(myfile == null || myfile.getName().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Select a file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(myfile));
            StringBuffer str = new StringBuffer();
            String line;
            while((line = input.readLine()) != null) // st is declared as string avobe
                str.append(line+"\n");
            t.setText(str.toString());
            content = t.getText();
            path = myfile.toString();
            setTitle(myfile.getName()+" - Notepad");
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "File not found: "+e);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "IO ERROR: "+e);
        }
    }

    public void file_save(){
        if(path.equals(""))
        {
            file_save_as();
            return;
        }
        try
        {
            FileWriter fw = new FileWriter(path);
            fw.write(t.getText());
            content = t.getText();
            fw.close();
        }
        catch(IOException i)
        {
            JOptionPane.showMessageDialog(this,"Failed to save the file","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void file_save_as(){

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int r = fc.showSaveDialog(this);
        if(r==fc.CANCEL_OPTION)
            return;
        File myfile = fc.getSelectedFile();
        if(myfile==null || myfile.getName().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter a file name!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(myfile.exists())
        {
            r = JOptionPane.showConfirmDialog(this, "A file with same name already exists!\nAre you sure want to overwrite?");
            if(r != 0)
                return;
        }
        try
        {
            FileWriter fw = new FileWriter(myfile);
            fw.write(t.getText());
            content = t.getText();
            setTitle(myfile.getName()+" - Notepad");
            fw.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this,"Failed to save the file","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void file_print() {
        PrinterJob printer = PrinterJob.getPrinterJob();
        //printer.setPrintable( this);
        HashPrintRequestAttributeSet printAttr = new HashPrintRequestAttributeSet();
        if(printer.printDialog(printAttr))     // Display print dialog
        {            // If true is returned...
            try
            {
                printer.print(printAttr);    // then print
            }
            catch(PrinterException e)
            {
                JOptionPane.showMessageDialog(this,"Failed to print the file: "+e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void file_close(){
        if(t.getText().equals("") || t.getText().equals(content))
        {
            t.setText("");
            path = "";
            setTitle("untitled - Notepad");
        }
        else
        {
            int a = JOptionPane.showConfirmDialog(null, "The text has been changed\nDo you want to save the changes?");
            if(a==0)
                file_save();
            else if(a==1)
            {
                t.setText("");
                path = "";
                setTitle("untitled - Notepad");
            }
            else if(a==2)
                return;
        }

    }

    public void file_exit(){

        if(t.getText().equals("") || t.getText().equals(content))
            System.exit(0);
    	else
    	{
            int b = JOptionPane.showConfirmDialog(null, "The text has been changed.\nDo you want to save the changes?");

            if(b==0)
                    file_save();
            else if(b==1)
                    System.exit(0);
            else if(b==2)
                    return;
    	}
    }

    public void edit_undo() {
        if( undo.canUndo())
        {
            try
            {
                undo.undo();
            }
            catch(CannotUndoException e)
            {
            }
        }
    }

    public void edit_redo(){
        if( undo.canRedo())
        {
            try
            {
                undo.redo();
            }
            catch(CannotRedoException e)
            {
            }
        }
    }

    public void edit_cut(){
        t.cut();
    }

    public void edit_copy(){
        t.copy();
    }

    public void edit_paste(){
        t.paste();
    }

    public void edit_delete(){
        String temp = t.getText();
        t.setText(temp.substring(0, t.getSelectionStart())+temp.substring(t.getSelectionEnd()));
    }

    public void edit_find(){
        finder.setVisible(true);
    }

    public void edit_find_next(){
        finder.find_next();
    }

    public void edit_replace(){
        finder.setVisible(true);
    }

    public void edit_selectall(){
        t.selectAll();
    }

    public void edit_timedate(){

        try
        {
        int start = t.getSelectionStart();
        int end   = t.getSelectionEnd();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy h:m a");
        String now = sdf.format(cal.getTime());
        String temp1 = t.getText().substring(0,start);
        String temp2 = t.getText().substring(end);
        t.setText(temp1+" "+now+" "+temp2);
        t.select(start+1, start+1+now.length());
        }
        catch(NullPointerException e){}
    }

    public void format_font(){
        fc.window.setVisible(true);
    }

    public void str2uppr(){
        try
        {
        int start = t.getSelectionStart();
        int end   = t.getSelectionEnd();
        String temp1 = t.getText().substring(0,start);
        String temp2 = t.getText().substring(end);
        String conv  = t.getSelectedText().toUpperCase();
        t.setText(temp1+conv+temp2);
        t.select(start, end);
        }
        catch(NullPointerException e){}
    }

    public void str2lwr(){
        try
        {
        int start = t.getSelectionStart();
        int end   = t.getSelectionEnd();
        String temp1 = t.getText().substring(0,start);
        String temp2 = t.getText().substring(end);
        String conv  = t.getSelectedText().toLowerCase();
        t.setText(temp1+conv+temp2);
        t.select(start, end);
        }
        catch(NullPointerException e){}
    }

    public void format_wordwarp(){
        if(t.getLineWrap()==false)
            t.setLineWrap(true);
        else
            t.setLineWrap(false);
    }

    public void help_about(){
        abt.window.setVisible(true);
    }


        class wListener implements WindowFocusListener
        {

            public void windowGainedFocus(WindowEvent we) {

            }

            public void windowLostFocus(WindowEvent we) {
                jf.setVisible(false);

            }
        }

             class kListener implements KeyListener
        {

        public void keyTyped(KeyEvent ke) {

        }

        public void keyPressed(KeyEvent ke) {

        }

        public void keyReleased(KeyEvent ke) {
            if(ke.getKeyCode()==27)
            {
                jf.setVisible(false);
            }
//            JOptionPane.showMessageDialog(null,ke.getKeyCode());
        }
   }

             class kListenerback implements KeyListener
        {

        public void keyTyped(KeyEvent ke) {

        }

        public void keyPressed(KeyEvent ke) {

            if(transl.getState()==true)
            {


                try
                {

                //JOptionPane.showMessageDialog(null, e.getKeyChar());

                if((ke.getKeyChar() ==' ' )  || (ke.getKeyChar() == '\n'))
                {

                trans.cr = t.getText();
                    int endpos1=t.getCaretPosition();
                String lword=trans.getWord(endpos1);
                trans.split(lword,t);

                }

                }

                catch (Throwable c)
                {
                c.printStackTrace ();
                }

            }

//            if(ke.getKeyCode()==32 || ke.getKeyCode()==8 )
//            {
//                c.add(sc2,BorderLayout.EAST);
//                sc2.setVisible(true);
//                list.setVisible(true);
//                //list.setfocusedddd
//            }

            //JOptionPane.showMessageDialog(null,ke.getKeyCode());

        }

        public void keyReleased(KeyEvent ke) {


        }
   }

}

// About class

class about{
    static JFrame window = new JFrame("About Notepad");
    notepad samp;
    JButton btn;

    public about(notepad ref)
    {
        samp  = ref;
        Container c = window.getContentPane();
        c.setLayout(new FlowLayout());

        String about = "<html>Notepad is a text editor which has all the features of Microsoft Notepad. In addition, it has 3 features.<ol><li>Transliteration<p>It transliterates from English to Hindi. XML technology is used to substitute the English literals to Hindi literals. These Hindi literals are encoded in Unicode format.</p></li><li>Text To Speech<p>It converts text to speeck through a male voice \" Kevin16 \" as configured by FreeTTS. It is a default package required to run this facility.</p></li><li>Dictionary<p>It is used to display meaning of a word selected in Notepad+.</p></li></ol</html>";

        ImageIcon icon = new ImageIcon("author.jpg");
        JLabel l = new JLabel("", icon, SwingConstants.LEFT);
        l.setText(about);
        l.setVerticalTextPosition(SwingConstants.TOP);
        l.setIconTextGap(20);
        c.add(l);

        int w = 340;
        int h = 250;
        window.setSize(w,h);
        // set window position
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(center.x-w/2, center.y-h/2+25);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setVisible(false);

    }
}

// Find Class
 class find extends JFrame implements ActionListener
{
    int startIndex=0;
    Label l1, l2;
    TextField tf, tr;
    JButton find_btn, find_next, replace, replace_all, cancel;

    notepad samp;

    public find(notepad mynote)
    {
        super("Find / Replace");
        samp = mynote;

        l1 = new Label("Find What: ");
        l2 = new Label("Replace With: ");
        tf = new TextField(30);
        tr = new TextField(30);
        find_btn = new JButton("Find");
        find_next = new JButton("Find Next");
        replace = new JButton("Replace");
        replace_all = new JButton("Replace All");
        cancel = new JButton("Cancel");

        setLayout(null);
        int label_w = 80;
        int label_h = 20;
        int tf_w    = 120;

        l1.setBounds(10,10, label_w, label_h);
        add(l1);
        tf.setBounds(10+label_w, 10, tf_w, 20);
        add(tf);
        l2.setBounds(10, 10+label_h+10, label_w, label_h);
        add(l2);
        tr.setBounds(10+label_w, 10+label_h+10, tf_w, 20);
        add(tr);

        find_btn.setBounds(220, 10, 100, 20);
        add(find_btn);
        find_btn.addActionListener(this);
        find_next.setBounds(220, 30, 100, 20);
        add(find_next);
        find_next.addActionListener(this);
        replace.setBounds(220, 50, 100, 20);
        add(replace);
        replace.addActionListener(this);
        replace_all.setBounds(220, 70, 100, 20);
        add(replace_all);
        replace_all.addActionListener(this);
        cancel.setBounds(220, 90, 100, 20);
        add(cancel);
        cancel.addActionListener(this);

        int w = 340;
        int h = 150;

        setSize(w,h);
        // set window position
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(center.x-w/2, center.y-h/2);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e)
    {
         if(e.getSource()==find_btn)
         {
            find();
         }
         else if(e.getSource() == find_next)
         {
            find_next();
         }
         else if(e.getSource() == replace)
         {
             replace();
         }
         else if(e.getSource() == replace_all)
         {
            replace_all();
         }
         else if(e.getSource() == cancel)
         {
            this.setVisible(false);
         }
    }

    public void find()
    {
        int select_start = samp.t.getText().indexOf(tf.getText());
        if(select_start == -1)
        {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find "+tf.getText()+"!");
            return;
        }
        if(select_start == samp.t.getText().lastIndexOf(tf.getText()))
        {
            startIndex = 0;
        }
        int select_end = select_start+tf.getText().length();
        samp.t.select(select_start, select_end);
    }

    public void find_next()
    {

        String selection = samp.t.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {
            selection = tf.getText();
            try
            {
                selection.equals("");
            }
            catch(NullPointerException e2)
            {
                selection = JOptionPane.showInputDialog("Find:");
                tf.setText(selection);
            }
        }
        try
        {
            int select_start = samp.t.getText().indexOf(selection, startIndex);
            int select_end = select_start+selection.length();
            samp.t.select(select_start, select_end);
            startIndex = select_end+1;

            if(select_start == samp.t.getText().lastIndexOf(selection))
            {
                startIndex = 0;
            }
        }
        catch(NullPointerException e)
        {}
    }

    public void replace()
    {
        try
        {
        find();
        samp.t.replaceSelection(tr.getText());
        }
        catch(NullPointerException e)
        {
            System.out.print("Null Pointer Exception: "+e);
        }
    }

    public void replace_all()
    {

        samp.t.setText(samp.t.getText().replaceAll(tf.getText(), tr.getText()));
    }
}

// font class
class font_chooser implements ActionListener, ListSelectionListener{
    static JFrame window = new JFrame("Font Chooser");
    notepad samp;

    JLabel flist_label, fsize_label, fstyle_label, fprev_label, preview;
    JList flist, fsize, fstyle;
    ScrollPane flist_sc, fstyle_sc, fsize_sc;
    JButton ok, cancel;

    GraphicsEnvironment ge; // graphics env
    String font_names[]; // font names array
    Font sample;

    String font_name;
    int font_size, font_style;

    public font_chooser(notepad ref)
    {
        samp  = ref;
        window.setLayout(null);

        // creating font name list
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        font_names = ge.getAvailableFontFamilyNames();
        flist = new JList(font_names);
        flist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flist_label  = new JLabel("Font: ");
        window.add(flist_label);
        flist_label.setBounds(10, 10, 120, 20);
        flist_sc = new ScrollPane();
        flist_sc.add(flist);
        flist_sc.setBounds(10, 30, 120, 200);
        window.add(flist_sc);
        flist.addListSelectionListener(this);

        // font style box
        String styles[] = {"Regular", "Bold", "Italic", "Bold Italic"};
        fstyle = new JList(styles);
        fstyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fstyle_label = new JLabel("Style: ");
        window.add(fstyle_label);
        fstyle_label.setBounds(140, 10, 80, 20);
        fstyle_sc = new ScrollPane();
        fstyle_sc.add(fstyle);
        fstyle_sc.setBounds(140, 30, 80, 70);
        window.add(fstyle_sc);
        fstyle.addListSelectionListener(this);

        // font size box
        Vector a = new Vector(40, 1);
        for (int i = 8; i <= 100; i += 2)
            a.addElement(String.valueOf(i));
        fsize = new JList(a);
        fsize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fsize_label = new JLabel("Size: ");
        fsize_label.setBounds(140, 110, 80, 20);
        window.add(fsize_label);
        fsize_sc = new ScrollPane();
        fsize_sc.add(fsize);
        fsize_sc.setBounds(140, 130, 80, 100);
        window.add(fsize_sc);
        fsize.addListSelectionListener(this);

        // ok and cancel button
        ok = new JButton("OK");
        ok.setBounds(230, 30, 75, 20);
        ok.addActionListener(this);
        window.add(ok);

        cancel = new JButton("Cancel");
        cancel.setBounds(230, 50, 75, 20);
        cancel.addActionListener(this);
        window.add(cancel);

        fprev_label  = new JLabel("Preview: ");
        fprev_label.setBounds(10, 230, 300, 20);
        window.add(fprev_label);

        // preview
        preview = new JLabel("Sample Text");
        preview.setBounds(10, 250, 290, 85);
        preview.setHorizontalAlignment(SwingConstants.CENTER);
        preview.setOpaque(true);
        preview.setBackground(Color.white);
        preview.setBorder(new LineBorder(Color.black, 1));
        window.add(preview);

        int w = 320;
        int h = 380;
        window.setSize(w,h);
        // set window position
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(center.x-w/2, center.y-h/2+25);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setVisible(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
            ok();
        else if(e.getSource()==cancel)
            cancel();
    }

    public void valueChanged(ListSelectionEvent l)
    {
        if(l.getSource()==flist)
        {
            preview.setText( flist.getSelectedValue().toString() );
            changeFontSample();
        }
        else if(l.getSource()==fsize)
        {
            changeFontSample();
        }
        else if(l.getSource()==fstyle)
        {
            changeFontSample();
        }
    }

    private void changeFontSample()
    {

        try
        {
        font_name = flist.getSelectedValue().toString();
        }
        catch(NullPointerException npe)
        {
            font_name = "Verdana";
        }
        try
        {
            font_style = getStyle();
        }
        catch(NullPointerException npe)
        {
            font_style = Font.PLAIN;
        }
        try
        {
            font_size = Integer.parseInt(fsize.getSelectedValue().toString());
        }
        catch(NullPointerException npe)
        {
            font_size = 12;
        }
        sample = new Font(font_name, font_style, font_size);
        preview.setFont(sample);
    }

    private int getStyle()
    {
        if( fstyle.getSelectedValue().toString().equals("Bold") )
            return Font.BOLD;
        if(fstyle.getSelectedValue().toString().equals("Italic") )
            return Font.ITALIC;
        if(fstyle.getSelectedValue().toString().equals("Bold Italic"))
            return Font.BOLD+Font.ITALIC;

        return Font.PLAIN;
    }

    private void ok()
    {
        try
        {
        samp.t.setFont(sample);
        }
        catch(NullPointerException npe){}
        this.window.setVisible(false);
    }

    private void cancel()
    {
        this.window.setVisible(false);
    }

}



class TTS {

	private static final String VOICENAME= "kevin16";

	void speakText(String str)
	{
		try
		{

		Voice voice;
		VoiceManager voiceManager = VoiceManager.getInstance();

		voice = voiceManager.getVoice(VOICENAME);

		voice.allocate();
		voice.speak(str);
		}
		catch(Exception ex){}
	}

}



// main class

public class Main {

    public static void main(String args[]){

        notepad mynote = new notepad();
        mynote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



 class dict
{
	public int total;
	public NodeList data;
	public DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        public DocumentBuilder docBuilder;
       	public Document doc;
	public dict(){

	}
	public void init(String ch)
	{
	try
	{
		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(new File(".//dict//" + ch + ".xml"));
        	doc.getDocumentElement().normalize();
        	data = doc.getElementsByTagName(ch.toUpperCase());
		total = data.getLength();

	}

	catch (SAXParseException err)
		{
        		System.out.println ("** Parsing error" + ", line "
             		+ err.getLineNumber () + ", uri " + err.getSystemId ());
        		System.out.println(" " + err.getMessage ());
		}
		catch (SAXException ex)
		{
        		Exception x = ex.getException ();
        		((x == null) ? ex : x).printStackTrace ();

        	}
		catch (Throwable t)
		{
        		t.printStackTrace ();
        	}

	}

	public String finds(String strFind)
	{
//		JOptionPane.showMessageDialog(null, "Total : " + total);
		int l=0,m,h=total;
		for(int i=0;i<total;i++)
		{
			Node dataNode = data.item(i);

			Element dataElement = (Element)dataNode;
			NodeList fList = dataElement.getElementsByTagName("f");

			Node f = fList.item(0);
			Element fElement = (Element)fList.item(0);
			String ss = fElement.getAttribute("ds");
			m = (l+h)/2;
			if(ss.compareToIgnoreCase(strFind) == 0)
			{

				return fElement.getAttribute("meaning");
			}
			else if(ss.compareToIgnoreCase(strFind) < 0)
			{
				l = m+1;
			}
			else if(ss.compareToIgnoreCase(strFind) > 0)
			{
				h=m-1;
			}
		}
		return null;
	}

}






class Transliteration
{
	private String curr;

	int total;
	NodeList data;
	String[] arr;
        String cr = "",h ;
        int startpos,endpos,rcount;
	static int mlen;
        static String tr;
        static String hin="";
        static int r=0,s=0,len=0,l=0,tval;
        JWindow jw;
       static  int c, first;
       static String word;

        public Transliteration() {
            try
                {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse (new File("demo.xml"));
                // normalize text representation
                doc.getDocumentElement().normalize();

                data = doc.getElementsByTagName("data");
                total = data.getLength();
                }
                catch (SAXParseException err)
                {
                        System.out.println ("** Parsing error" + ", line "
                        + err.getLineNumber () + ", uri " + err.getSystemId ());
                        System.out.println(" " + err.getMessage ());
                }
                catch (SAXException ex)
                {
                        Exception x = ex.getException ();
                        ((x == null) ? ex : x).printStackTrace ();
                }
                catch (Throwable t)
                {
                        t.printStackTrace ();
                }
                curr = " ";

        }



	Boolean vowel(char chr)
	{
		if(chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u')
			return true;
		else
			return false;
	}
	Boolean consonant(char chr)
	{
		if(chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u')
			return false;
		else
			return true;
	}
	void split(String str,JTextArea t)
	{
		h = "";
		arr = new String[str.length()];
		int ctr = 0;
		 c = 0;
		for(int i=0;i<str.length()-1;i++) // khaana
		{
			if(vowel(str.charAt(i)) && consonant(str.charAt(i+1))) // ka haa ni
			{
				arr[c] = str.substring(ctr, i+1);
				curr += arr[c] + " ";
				c += 1;
				ctr = i+1;
			}
			if(consonant(str.charAt(i)) && consonant(str.charAt(i+1)) && (str.charAt(i+1) != 'h')) // khaa na
			{
				arr[c] = str.substring(ctr, i+1);
				curr += arr[c] + " ";
				c += 1;
				ctr = i+1;
			}
		}
		arr[c] = str.substring(ctr, str.length());
		for(int i=0; i<= c; i++ )
                {
                    first++;
			h += transliterate(arr[i]);

                }
                first=0;


                t.replaceRange(h, startpos-rcount, endpos-rcount);

	}

	String transliterate(String tr1)
	{
                                tr=tr1;
		String temp="";
                                r=0;
                                l=0;
                                len=0;
                                s=0;

for( len=tr.length(); len>=0; len--) /*Just len>0 is enough*/
				{
		for(s=0; s<total ; s++)
		{
			Node dataNode = data.item(s);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE)
			{
                    		Element dataElement = (Element)dataNode;
				NodeList eList = dataElement.getElementsByTagName("val");

				String types = dataElement.getAttribute("type");


					if(types.compareToIgnoreCase(tr.substring(0,len)) == 0)
					{
						for(l=tr.length(); l>=0; l--) /*Just len>0 is enough*/
						{
						 tval = eList.getLength();
for( r=0;r<tval;r++)
{
    int dot=0;
Node val = eList.item(r);
Element valElement = (Element)eList.item(r);
String eng = valElement.getAttribute("eng");
if(eng.compareToIgnoreCase(tr.substring(0,l)) == 0)
{
mlen=l;

 if((tr1.charAt(0) =='n'||tr1.charAt(0)=='N') && c != 0 && first !=1 && consonant(tr1.charAt(tr1.length()-1)))
 {
     if( c !=(first-1) ) //dot case
     {
         dot=1;
        hin+="\u0902";
     }
     else
     {
         hin += valElement.getAttribute("hin");
    }
 }
 else
 {
hin += valElement.getAttribute("hin");
 }
if(mlen==tr.length())
{
mlen=0;
//r=tval;
l=0;
len=0;
s=total;
temp=hin;
hin="";
if(consonant(tr1.charAt(tr1.length()-1)) && (c !=0) && dot !=1 && c !=(first-1))
{
     temp+="\u094D";
}
return temp;

}
else
{
temp=tr.substring(l,tr.length());
hin=transliterate(temp);
}

}	}
}
}
}
}
}
temp=hin;
hin="";
if(consonant(tr1.charAt(tr1.length()-1)) && c !=0 && (tr1.charAt(0) != 'n'|| tr1.charAt(0) != 'N'))
{

     temp+="\u094D";
}
return temp;
}


        String getWord(int end)
        {
        endpos=end;
        //startpos=cr.lastIndexOf(" ", endpos);
        if (cr.lastIndexOf(" ",endpos) ==-1)//If only 1 word startpos=-1 /* if no space in first word then lower words wont fnd it */
        {
        startpos=0;
        }
        else
        {
        startpos=Math.max(cr.lastIndexOf(" ", endpos)+1,cr.lastIndexOf("\n", endpos)+1);
        }

        /* This is a fix for an issue where "TextArea.replaceText() works incorrectly
        if text contains escape characters- \r or \b" and so on. This method adjusts its values to compensate for the ignored "\r"*/
        rcount=0;
        for(int i=0;i<startpos;i++)
        if(cr.charAt(i)=='\r')
        rcount++;


        /*Can't use startpos & endpos in other functions as it is, since after extracting substring trim()_is called. so startpos may not be showing the actual startpos of the word (space may be there)*/
        /*Handle null also in split(). getWord() returns null if startpos & endpos same/ empty string*/
        /*Repeated calls to getWord()*/
        /*The waiting space dissapears when we use JDia.. & use space tp press its OK*/
        /* trim() is redundant if we get the correct startpos & endpos*/
        //startpos=Math.max(startpos,0);      //If only 1 word startpos=-1
        // startpos=Math.max(startpos,cr.lastIndexOf("\n", endpos)+1);  //When 'newlines' have been inserted


        //                try{
         word=cr.substring(startpos, endpos);
        //                }
        //                catch(IndexOutOfBoundsException I)
        //                {
        //                    JOptionPane.showMessageDialog(null, "Begin Index is negatice or End Index is greater than substring length");
        //
        //                }
        word=word.trim();   //doesn't trim multiple whitespaces
        if(!word.isEmpty())
        {
        //JOptionPane.showMessageDialog(null, "Start = " + startpos + "\tEndpos = " + endpos + "\n" +word+"|");
        return word;
        }
        else
        return null;

        }




}

