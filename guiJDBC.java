import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
public class guiJDBC extends Frame {

    guiJDBC(){
        setTitle("Inserting Data in Table");
        setSize(500, 500);
        //setLocation(100, 50);
        setVisible(true);
        setLayout(null);

    }
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/orders"; //change "orders" to your database 
        final String username = "root"; //change username accordingly
        final String password = "root"; //change password accordingly
        guiJDBC frame1 = new guiJDBC();
        Button b1 = new Button("Insert data");
        Button b2 = new Button("Show data");
        Button b3 = new Button("Delete Data");
        b1.setBounds(50,320,80,30);
        b2.setBounds(150,320,80,30);
        b3.setBounds(250,320,80,30);
        frame1.add(b1);
        frame1.add(b2);
        frame1.add(b3);

        Label l1 = new Label("Enter the id: ");
        Label l2 = new Label("Enter the name: ");
        Label l3,l4,l5,l6;
        l3 = new Label("Enter customer city: ");
        l4=new Label("Enter customer grade: ");
        l5=new Label("Enter customer salesman id: ");
        l6=new Label();
        l1.setBounds(10, 50, 120, 10);
        l2.setBounds(10, 100, 120,  10);
        l3.setBounds(10, 150, 120,  10);
        l4.setBounds(10, 200, 120,  10);
        l5.setBounds(10, 250, 200,  10);
        l6.setBounds(10,400,200,20);
        frame1.add(l1);
        frame1.add(l2);

        TextField t3,t4,t5;
        t3=new TextField();
        t4=new TextField();
        t5 = new TextField();
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        t1.setBounds(10, 70, 160, 30);
        t2.setBounds(10, 120, 180,  30);
        t3.setBounds(10, 170, 180,  30);
        t4.setBounds(10, 220, 180,  30);
        t5.setBounds(10, 270, 180,  30);
        frame1.add(t1);
        frame1.add(t2);
        frame1.add(l3);
        frame1.add(l4);
        frame1.add(l5);
        frame1.add(t3);
        frame1.add(t4);
        frame1.add(t5);
        frame1.add(l6);

        // Adds feature to remove pre written text
        t1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e ){
                t1.setText("");
            }

            public void focusLost(FocusEvent e){
                // Do nothing
            }
        });
        t2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e ){
                t2.setText("");
            }

            public void focusLost(FocusEvent e){
                // Do nothing
            }
        });
        t3.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e ){
                t3.setText("");
            }

            public void focusLost(FocusEvent e){
                // Do nothing
            }
        });
        t4.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e ){
                t4.setText("");
            }

            public void focusLost(FocusEvent e){
                // Do nothing
            }
        });

        b2.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url,username, password);

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from customer");
                    l6.setText("Displaying data on console...");
                    System.out.println("-------------------------------"+"Customer Information"+"----------------------------------");
                    while(rs.next()){
                        System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+ " " +rs.getInt(4)+" "+rs.getInt(5));
                    }
                    System.out.println("-----------------------------------------------------------------------------------------");
                }catch (Exception ex) {
                    System.out.println("Error displaying the table");
                    ex.printStackTrace();
                }
                
            }
            public void mouseExited(MouseEvent me){

            }
            public void mouseEntered(MouseEvent me){

            }
            public void mousePressed(MouseEvent me){

            }
            public void mouseReleased(MouseEvent me){

            }
        });

        b1.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    int custID = Integer.parseInt(t1.getText());
                    String custName = t2.getText();
                    String address = t3.getText();
                    int custGrade = Integer.parseInt(t4.getText());
                    int salesID = Integer.parseInt(t5.getText());
                    Connection con = DriverManager.getConnection(url,username, password);
                    String insertQuery = "insert into customer values(?,?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(insertQuery);
                    pstmt.setInt(1,custID);
                    pstmt.setString(2, custName);
                    pstmt.setString(3, address);
                    pstmt.setInt(4, custGrade);
                    pstmt.setInt(5,salesID);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        l6.setText("Data inserted successfully!");
                    }
                    else{
                        l6.setText("Error while inserting data.");
                    }
                    
                    
                }catch (Exception ex) {
                    System.out.println("Error inserting in the table");
                    ex.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent me){

            }
            public void mouseEntered(MouseEvent me){

            }
            public void mousePressed(MouseEvent me){

            }
            public void mouseReleased(MouseEvent me){

            }
        });

        b3.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    int custID = Integer.parseInt(t1.getText());
                    // String custName = t2.getText();
                    // String address = t3.getText();
                    // int custGrade = Integer.parseInt(t4.getText());
                    // int salesID = Integer.parseInt(t5.getText());
                    Connection con = DriverManager.getConnection(url,username, password);
                    String deleteQuery = "delete from customer where  customer_id=?";
                    PreparedStatement pstmt = con.prepareStatement(deleteQuery);
                    pstmt.setInt(1,custID);
                    // pstmt.setString(2, custName);
                    // pstmt.setString(3, address);
                    // pstmt.setInt(4, custGrade);
                    // pstmt.setInt(5,salesID);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        l6.setText("Data deleted successfully!");
                    }
                    else{
                        l6.setText("Error while deleting the data.");
                    }
                    
                    
                }catch (Exception ex) {
                    System.out.println("Error inserting in the table");
                    ex.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent me){

            }
            public void mouseEntered(MouseEvent me){

            }
            public void mousePressed(MouseEvent me){

            }
            public void mouseReleased(MouseEvent me){

            }
        });

        frame1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
              
    }
}

