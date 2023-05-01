import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //File menu item
    JMenuItem newFile, openFile, saveFile;
    //Edit menu item
    JMenuItem cut, copy, paste, selectall, close;
    JTextArea textArea;
    TextEditor(){
        //initialized frame
        frame = new JFrame();

        //initialized menuBar
        menuBar = new JMenuBar();

        //initialized text area
        textArea = new JTextArea();

        file = new JMenu("File");

        edit = new JMenu("Edit");

        //Adding File menu to MenuBar
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Adding to Edit o MenuBar
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        //Adding to Edit
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);
        edit.add(close);

        //Adding file menu to MenuBar
        menuBar.add(file);
        //Adding Edit menu to MenuBar
        menuBar.add(edit);
        //Setting menuBar to Frame
        frame.setJMenuBar(menuBar);

        //Creating content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        //Adding textArea to Panel
        panel.add(textArea, BorderLayout.CENTER);
        //Creating scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Adding scrollBar to panel
        panel.add(scrollPane);
        //Adding panel to frame
        frame.add(panel);
        //Setting Boundaries of frame
        frame.setBounds(100, 100, 400,400);

        frame.setVisible(true);

        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource()== copy){
            textArea.copy();
        }
        if(actionEvent.getSource()== paste){
            textArea.paste();
        }
        if(actionEvent.getSource()== selectall){
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0);
        }
        if(actionEvent.getSource()== openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();

                String filePath = file.getPath();

                try {
                    //FileReader ini
                    FileReader fileReader = new FileReader(filePath);
                    //BufferReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file
                    while ((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }

                    textArea.setText(output);
                    bufferedReader.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            //FileChooser ini
            JFileChooser fileChooser1 = new JFileChooser("C:");
//                getting choose option from chooser
            int chooseOption1 = fileChooser1.showSaveDialog(null);
//                Checking which option is chosen
            if(chooseOption1 == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser1.getSelectedFile().getAbsoluteFile()+".txt");

                try{
                    //Ini fileWriter
                    FileWriter fileWriter = new FileWriter(file);
                    //Ini BufferedReader
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of textArea
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }

            }

        }
        if(actionEvent.getSource() == newFile){
            new TextEditor();
        }
        
    }
    public static void main(String[] args) {

        new TextEditor();
    }
}