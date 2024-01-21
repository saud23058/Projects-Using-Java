package Travel_Mangement_System;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class TravelMangementSystem implements ActionListener {
    String name;
    JFrame frame;
    JLabel welcomeLabel;
    JTextField nameField;
    JLabel namLabel;
    JPanel panel;
    JButton getStartedButtton;
    TravelMangementSystem(){
        
        panel=new JPanel();
        namLabel=new JLabel("Enter Your Name ");
        nameField=new JTextField(20);
        nameField.setBounds(190,135,100,25);
        //name=nameField.getText();
        frame=new JFrame("Travelling Mangemnet System");
        welcomeLabel=new JLabel();
        welcomeLabel.setText("Welcome To Travelling Mangement System");
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getStartedButtton=new JButton("Get Start");
        getStartedButtton.setBounds(190, 190, 120, 25);
        getStartedButtton.addActionListener(this);



        frame.add(getStartedButtton);
        panel.add(welcomeLabel, BorderLayout.CENTER);
        frame.add(panel,BorderLayout.NORTH);
        frame.add(nameField);
        frame.add(namLabel,BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==getStartedButtton){
        name=nameField.getText();
        frame.dispose();
        new Adding_Location(name);
       }
    }
    public static void main(String[] args) {
        new TravelMangementSystem();
    }
    
}
// Travelling details
class Travelling_Details {
    private String placeName;
    private String dateOfVisite;
    private String cost;
    public Travelling_Details(String placeName, String dateOfVisite, String cost) {
        this.placeName = placeName;
        this.dateOfVisite = dateOfVisite;
        this.cost = cost;
    }
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public String getDateOfVisite() {
        return dateOfVisite;
    }
    public void setDateOfVisite(String dateOfVisite) {
        this.dateOfVisite = dateOfVisite;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }

}

//Adding Location 
class Adding_Location implements ActionListener {
     Travelling_Details travelling_Details;
    static ArrayList<Travelling_Details> list = new ArrayList<>();
    private JFrame frame;
    private JLabel welcomeLabel;
    private JLabel placeLabel;
    private JLabel dateLabel;
    private JLabel costLabel;
    private JTextField placField;
    private JTextField dateField;
    private JTextField costField;
    private JButton addLocaButton;
    private JButton totalCosButton;
    private JButton PreviousRecButton;
    private JPanel panel;
    private JPanel panel1;

    Adding_Location(String name) {
        frame = new JFrame("Adding Location");
        frame.setLayout(new GridLayout(4, 3, 5, 5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        panel1 = new JPanel();
        panel = new JPanel();
        name = String.format("Please fill the given Information ' %s", name);
        welcomeLabel = new JLabel(name);
        panel1.add(welcomeLabel);
        placeLabel = new JLabel("Enter Place name");
        placField = new JTextField(30);
        dateLabel = new JLabel("Enter Date");
        dateField = new JTextField(15);
        costLabel = new JLabel("Enter Cost");
        costField = new JTextField(10);

        // Adding Buttons
        addLocaButton = new JButton("Add Location");
        addLocaButton.addActionListener(this);
        totalCosButton = new JButton("Total Cost");
        totalCosButton.addActionListener(this);
        PreviousRecButton = new JButton("Previous Record");
        PreviousRecButton.addActionListener(this);

        panel.add(placeLabel);
        panel.add(placField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(costLabel);
        panel.add(costField);
        panel.add(addLocaButton);
        panel.add(totalCosButton);
        panel.add(PreviousRecButton);

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==addLocaButton){
        String n=placField.getText();
        String d=dateField.getText();
        String c=costField.getText();
        travelling_Details=new Travelling_Details(n, d, c);
        list.add(travelling_Details);
       }
       else if(e.getSource()==PreviousRecButton){
        new previouseRec();
       }
       else if (e.getSource()==totalCosButton) {
        JOptionPane.showMessageDialog(null,"Toatl Expense ="+ travelling_Details.getCost(), "Total ", JOptionPane.INFORMATION_MESSAGE);
       }
    }

}

// Previouse Record
class previouseRec {
    JPanel recordPanel;
    JFrame frame;

    previouseRec() {
        frame = new JFrame("Previous Record");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        recordPanel = new JPanel();
        recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS));

        displayRecords(Adding_Location.list);

        JScrollPane scrollPane = new JScrollPane(recordPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void displayRecords(ArrayList<Travelling_Details> records) {
        for (Travelling_Details travelling_Details : records) {
            JLabel placeLabel = new JLabel("Place: " + travelling_Details.getPlaceName());
            JLabel dateLabel = new JLabel("Date: " + travelling_Details.getDateOfVisite());
            JPanel recordEntry = new JPanel();
            recordEntry.setLayout(new GridLayout(3, 1));
            recordEntry.add(placeLabel);
            recordEntry.add(dateLabel);

            recordPanel.add(recordEntry);
        }
    }
}
