package frames;
import classSrc.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener//implements ActionListener
{

    JLabel name = new JLabel("Username");
    JLabel password = new JLabel("Password");
	JButton signIn = new JButton("Sign In");
    JButton signUp = new JButton("Sign Up");
    JTextField textName = new JTextField("",10);
    JPasswordField textPassword = new JPasswordField("",10);

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{ 
		this.setTitle("Login");
		getContentPane().setBackground(Color.WHITE);	
		getContentPane().setLayout(new GridLayout(4,1,10,5));
		getContentPane().add(new JLabel("Grading System",SwingConstants.CENTER),BorderLayout.CENTER);
		
		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.WHITE);
		namePanel.add(name);
		namePanel.add(textName);
		getContentPane().add(namePanel);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.add(password);
		passwordPanel.add(textPassword);
		getContentPane().add(passwordPanel);
		
		FlowLayout f= new FlowLayout();
		f.setHgap(50);
		JPanel buttonPanel = new JPanel(f);
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(signIn);
		buttonPanel.add(signUp);
		getContentPane().add(buttonPanel);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signIn.addActionListener(this);
		signUp.addActionListener(this);
		setBounds(100, 100, 450, 300); 
	}
	
	public void actionPerformed(ActionEvent e) {
		String n = textName.getText();
        String p = String.valueOf(textPassword.getPassword());
        if (n.isEmpty()){
            JOptionPane.showMessageDialog(getParent(), "Username can't be empty");
            return;
        }else if(p.isEmpty()) {
        	JOptionPane.showMessageDialog(getParent(), "Password cant'y be empty");
        	return;
        }
        if (e.getSource()==signIn){
        	if(GradingSystem.getInstance().LoginCheck(n,p)){
        		
        		//TODO: INSTANTIATE NEW TEACHER CLASS THAT HOLDS ALL THE COURSES, ASSIGNMENTS, ETC LISTED IN THE DATABASE
        		
        		
        		new CourseListFrame(); //TODO: PASS TEACHER.COURSES AS A PARAMETER INTO COURSELISTFRAME
                this.dispose();
            }else {
            	JOptionPane.showMessageDialog(getParent(), "Either your Username or Password is wrong. Please enter again.");
            }
        }else if (e.getSource()==signUp){
        	if(GradingSystem.getInstance().SignUp(n,p)){
        		JOptionPane.showMessageDialog(getParent(), "User created.");
            }else {
            	JOptionPane.showMessageDialog(getParent(), "User already exist");
            }
        }
    }

}
