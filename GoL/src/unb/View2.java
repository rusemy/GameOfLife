package unb;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import java.awt.Panel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class View2 {

	Statistics statistics = new Statistics();
	private GameEngine engine = new Conway(10, 10, statistics);
	private GameController controller  = new GameController();
	//controller.setEngine(engine);
	//controller.setStatistics(statistics);
	private static final String ERRO_INVARG = "Invalid arguments!";
	private static final String ERRO_NIMPL = "Not implemented yet.";
	
	private JFrame frame;
	private JTextField textFieldxalive;
	private JTextField textFieldyalive;
	private JTextField textFieldxkill;
	private JTextField textFieldykill;
	private JTextField textFieldxnext;
	private JTextField textFieldxundo;
	private JTable table;
	private JButton[][] buttons;
	private JPanel grid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View2 window = new View2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Make Cell Alive");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int x, y;
				try{
					x = Integer.parseInt(textFieldxalive.getText());
					y = Integer.parseInt(textFieldyalive.getText());
					//controller.makeCellAlive(y, x);
					engine.makeCellAlive(y, x);
					update(grid);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, ERRO_INVARG);
				}
			}
		});
		btnNewButton.setBounds(124, 314, 129, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnKillAliveCell = new JButton("Kill Alive Cell");
		btnKillAliveCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int x, y;
				try{
					x = Integer.parseInt(textFieldxkill.getText());
					y = Integer.parseInt(textFieldykill.getText());
					//controller.killAliveCell(y, x);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, ERRO_INVARG);
				}
			}
		});
		btnKillAliveCell.setBounds(124, 385, 129, 48);
		frame.getContentPane().add(btnKillAliveCell);
		
		JButton btnNextGeneration = new JButton("Next Generation");
		btnNextGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try{
					controller.nextGeneration();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, ERRO_NIMPL);
				}
			}
		});
		btnNextGeneration.setBounds(263, 314, 129, 48);
		frame.getContentPane().add(btnNextGeneration);
		
		JButton btnNextXGenerations = new JButton("Next X Generations");
		btnNextXGenerations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int x;
				try{
					x = Integer.parseInt(textFieldxnext.getText());
					//controller.nextGenerations(x);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, ERRO_INVARG);
				}
			}
		});
		btnNextXGenerations.setBounds(481, 314, 129, 48);
		frame.getContentPane().add(btnNextXGenerations);
		
		JButton btnUndo = new JButton("Undo a Generation");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try{
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, ERRO_NIMPL);
				}
			}
		});
		btnUndo.setBounds(263, 385, 129, 48);
		frame.getContentPane().add(btnUndo);
		
		JButton btnUndoXGenerations = new JButton("Undo X Generations");
		btnUndoXGenerations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int x;
				try{
					x = Integer.parseInt(textFieldxundo.getText());
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, ERRO_INVARG);
				}
			}
		});
		btnUndoXGenerations.setBounds(481, 385, 129, 48);
		frame.getContentPane().add(btnUndoXGenerations);
		
		textFieldxalive = new JTextField();
		textFieldxalive.setBounds(28, 314, 86, 20);
		frame.getContentPane().add(textFieldxalive);
		textFieldxalive.setColumns(10);
		
		JLabel lblX = new JLabel("X: ");
		lblX.setBounds(10, 314, 46, 20);
		frame.getContentPane().add(lblX);
		
		JLabel label = new JLabel("X: ");
		label.setBounds(10, 385, 46, 20);
		frame.getContentPane().add(label);
		
		JLabel lblY = new JLabel("Y: ");
		lblY.setBounds(10, 342, 46, 20);
		frame.getContentPane().add(lblY);
		
		JLabel lblY_1 = new JLabel("Y: ");
		lblY_1.setBounds(10, 413, 46, 20);
		frame.getContentPane().add(lblY_1);
		
		textFieldyalive = new JTextField();
		textFieldyalive.setColumns(10);
		textFieldyalive.setBounds(28, 342, 86, 20);
		frame.getContentPane().add(textFieldyalive);
		
		textFieldxkill = new JTextField();
		textFieldxkill.setColumns(10);
		textFieldxkill.setBounds(28, 385, 86, 20);
		frame.getContentPane().add(textFieldxkill);
		
		textFieldykill = new JTextField();
		textFieldykill.setColumns(10);
		textFieldykill.setBounds(28, 413, 86, 20);
		frame.getContentPane().add(textFieldykill);
		
		textFieldxnext = new JTextField();
		textFieldxnext.setText("0");
		textFieldxnext.setBounds(412, 328, 59, 20);
		frame.getContentPane().add(textFieldxnext);
		textFieldxnext.setColumns(10);
		
		textFieldxundo = new JTextField();
		textFieldxundo.setText("0");
		textFieldxundo.setBounds(412, 399, 59, 20);
		frame.getContentPane().add(textFieldxundo);
		textFieldxundo.setColumns(10);
		
		table = new JTable();
		table.setForeground(Color.GREEN);
		table.setBackground(Color.BLACK);
		table.setBounds(10, 305, 674, -291);
		frame.getContentPane().add(table);
		
		grid = new JPanel();
		grid.setBounds(10, 11, 674, 292);
		frame.getContentPane().add(grid);
		grid.setLayout(new GridLayout(engine.getHeight(), engine.getWidth(), 0, 0));
		
		buttons = new JButton[engine.getHeight()][engine.getWidth()];
		//public void update(){
			for(int k=0;k < engine.getHeight();k++){
				for(int j=0;j < engine.getWidth();j++){
					buttons[k][j] = new JButton(engine.isCellAlive(k, j) ? "#" : " ");
					grid.add(buttons[k][j]);
				}
				
			}
	}
	private void update(JPanel grid) {
		for(int k=0;k < engine.getHeight();k++){
			for(int j=0;j < engine.getWidth();j++){
				buttons[k][j] = new JButton(engine.isCellAlive(k, j) ? "#" : " ");
				grid.add(buttons[k][j]);
			}
			
		}
	}
}
