import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class WeightPanel extends JPanel{
	//PLACEHOLDER, NO MORE IMPLEMENTATION, REMOVE ACTION LISTENER
	private User UserInfo;
	
	private JPanel ListPanel;	//panels with their own initializer
	private JPanel UIPanel;
	private JScrollPane scrollpane;
	private JList list;
	
	private JButton Today;		//basic date content
	private JLabel DateLabel;
	private JTextField DateOutput;
	
	private JButton Add,		//UI content
					Delete;
	private JTextField Input;
	private JLabel SentenceLabel;

	
	private void ListInitialize() 
	{
		list=new JList();
		ListPanel=new JPanel();
		scrollpane=new JScrollPane(list);
		ListPanel.add(scrollpane);
		ListPanel.setVisible(true);
	}
	private void ContentInitialize() 
	{
		DateLabel=new JLabel("Date");
		Today=new JButton("Today");
		DateOutput=new JTextField(20);
		DateOutput.setText("PLACEHOLDER");
		DateOutput.setEditable(false);
		
		Add=new JButton("Add");
		Delete=new JButton("Delete");
		Input=new JTextField(15);
		Add.addActionListener(new ButtonActionListener());
		SentenceLabel=new JLabel("New weight for this date");
	}
	private void UIPanelInitialize() 
	{
		UIPanel=new JPanel();
		GroupLayout group=new GroupLayout(UIPanel);
		UIPanel.setLayout(group);
		UIPanel.setVisible(true);
		
		group.setAutoCreateGaps(true);
		group.setAutoCreateContainerGaps(true);
		group.setHorizontalGroup(
				group.createSequentialGroup()
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)	//0. oszlop
						.addComponent(DateLabel)	//felülrõl 0. elem
						.addComponent(SentenceLabel)
						.addComponent(Delete)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)	//1. oszlop
						.addComponent(DateOutput,40,70,70)	//felülrõl 0. elem
						.addComponent(Input,40,70,70)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)	//2. oszlop
						.addComponent(Today)	//felülrõl 0. elem
						.addComponent(Add)		//felülrõl 1. elem
						)
				);
		group.setVerticalGroup(
				group.createSequentialGroup()		//szekvenciális felülrõl lefele
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(DateLabel)			//0. sor 0. eleme
						.addComponent(DateOutput)			//0. sor 1. eleme
						.addComponent(Today)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(SentenceLabel)			//1. sor 0. eleme
						.addComponent(Input)
						.addComponent(Add)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(Delete)			//2. sor 0. eleme
						)	
				);

	}
	public WeightPanel() 
	{
		this.UserInfo=User.getInstance();
		ContentInitialize();
		ListInitialize();
		UIPanelInitialize();
		
		BorderLayout border=new BorderLayout();
		this.setLayout(border);
		this.add(ListPanel,BorderLayout.WEST);
		this.add(UIPanel,BorderLayout.PAGE_END);
		this.setVisible(true);
	}
	private class ButtonActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
		Input.setText(""+UserInfo.getAge());
		}
	}
}
