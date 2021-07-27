import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TrendsPanel extends JPanel{
	private User UserInfo;
	private JScrollPane scrollpane;
	private JList list;
	private JButton Today,
			Select;
	private JLabel DateLabel;
	private JTextField DateOutput;
	private JPanel ListPanel;
	private JPanel UIPanel;
	private DefaultListModel listModel; //this contols the lists items
	private void ListInitialize() 
	{
		listModel= new DefaultListModel();
		list=new JList(listModel);
		ListPanel=new JPanel();
		scrollpane=new JScrollPane(list);
		ListPanel.add(scrollpane);
		ListPanel.setVisible(true);
	}
	private void ContentInitialize() 
	{
		DateLabel=new JLabel("Date");
		Today=new JButton("Today");
		Today.addActionListener(new TodayActionListener());
		Select=new JButton("Select date");
		Select.addActionListener(new SelectActionListener());
		DateOutput=new JTextField(20);
		DateOutput.setText(UserInfo.getToday().getDate().toString());
		DateOutput.setEditable(false);
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
						
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)	//1. oszlop
						.addComponent(DateOutput,40,70,70)	//felülrõl 0. elem
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)	//2. oszlop
						.addComponent(Today)	//felülrõl 0. elem
						.addComponent(Select)		//felülrõl 1. elem
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
						.addComponent(Select)			//1. sor 0. eleme
						)	
				);

	}
	public void Update() 
	{
		listModel.clear();
		for (int i=0; i<UserInfo.getAllDays().size(); i++)
			listModel.addElement(UserInfo.getAllDays().get(i));
		DateOutput.setText(UserInfo.getSelectDate().toString());
	}
	private class SelectActionListener implements ActionListener //sets selected day to the on selected on the list
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (list.getSelectedValue()!=null)
				{
				String selected[] = list.getSelectedValue().toString().split(" ");
				UserInfo.setSelectDate(selected[0]);
				Update();
			}
		}
	} 
	private class TodayActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
		UserInfo.setSelectDate(UserInfo.getToday().getDate().toString());
		Update();
		}
	} 
	public TrendsPanel() 
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
}