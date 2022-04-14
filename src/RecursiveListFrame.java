import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class RecursiveListFrame extends JFrame {
    JPanel mainPnl;
    JTextArea outputTxt;
    JScrollPane scrollTxt;
    JButton inputBtn;
    private static ArrayList<String> paths = new ArrayList<>();

    public RecursiveListFrame(){
        mainPnl = new JPanel();
        mainPnl.setBorder(new TitledBorder(new EtchedBorder(), "RecursiveLister "));
        mainPnl.setLayout(new GridLayout(2,1));

        inputBtn = new JButton("Select File Path");
        inputBtn.addActionListener((ActionEvent ae) ->{
            clearOutputTxt();
            getFileList();
            for (String path : paths) {
                outputTxt.append(path + "\n");
            }
        });

        outputTxt = new JTextArea("",20,130);
        scrollTxt = new JScrollPane(outputTxt);

        mainPnl.add(inputBtn);
        mainPnl.add(scrollTxt);

        add(mainPnl);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void getFileList(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(mainPnl);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            getFiles(file);
        }else{
            outputTxt.append("File not found");
        }
    }

    public void getFiles(File f){
        File[] fList = f.listFiles();

        if(fList != null){
            for(File file: fList){
                if(file.isFile()){
                    paths.add(file.getPath());
                }
                else if (file.isDirectory()){
                    getFiles(file);
                }
            }
        }
    }

    public void clearOutputTxt(){
        outputTxt.selectAll();
        outputTxt.replaceSelection("");
    }
}
