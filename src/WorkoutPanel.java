import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class WorkoutPanel extends JPanel{
	//PLACEHOLDER, NO MORE IMPLEMENTATION
	private User UserInfo;
	private JPanel ListPanel;	//panels with their own initializer
	private JPanel UIPanel;
	private JScrollPane scrollpane;
	private JList list;
	
	private JButton Today;		//basic date content
	private JLabel DateLabel;
	private JTextField DateOutput;
	
	private JButton AddH,		//UI content
					AddKm,
					AddCtn,
					Delete;
	private JTextField 	HBurn,
						HInput,
						KmBurn,
						KmInput,
						CtnBurn,
						CtnInput,
						CustomName;
	private JLabel 	BurnHLabel,
					HLabel,
					BurnKmLabel,
					KmLabel,
					BurnCtnLabel,
					CtnLabel,
					NameLabel;
	private JComboBox WorkoutDropDown;

	
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
		
		AddH=new JButton("Add Hours");
		AddKm=new JButton("Add Kms");
		AddCtn=new JButton("Add count");
		Delete=new JButton("Delete");
		
		HBurn=new JTextField(10);
		HInput=new JTextField(10);
		KmBurn=new JTextField(10);
		KmInput=new JTextField(10);
		CtnBurn=new JTextField(10);
		CtnInput=new JTextField(10);
		CustomName=new JTextField(10);
		
		HBurn.setEditable(false);
		KmBurn.setEditable(false);
		CtnBurn.setEditable(false);
		CustomName.setEditable(false);
		
		BurnHLabel=new JLabel("Burn/h:");
		HLabel=new JLabel("Hours:");
		BurnKmLabel=new JLabel("Burn/Km:");
		KmLabel=new JLabel("Kms:");
		BurnCtnLabel=new JLabel("Burn/count:");
		CtnLabel=new JLabel("Count:");
		NameLabel=new JLabel("Custom name:");
		
		WorkoutDropDown=new JComboBox();
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
						.addComponent(BurnHLabel)
						.addComponent(HLabel)
						.addComponent(BurnKmLabel)
						.addComponent(KmLabel)
						.addComponent(BurnCtnLabel)
						.addComponent(CtnLabel)
						.addComponent(NameLabel)
						.addComponent(WorkoutDropDown,50,150,200)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)	//1. oszlop
						.addComponent(DateOutput,40,70,70)	//felülrõl 0. elem
						.addComponent(HBurn,40,70,70)
						.addComponent(HInput,40,70,70)
						.addComponent(KmBurn,40,70,70)
						.addComponent(KmInput,40,70,70)
						.addComponent(CtnBurn,40,70,70)
						.addComponent(CtnInput,40,70,70)
						.addComponent(CustomName,40,70,70)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)	//2. oszlop
						.addComponent(Today)			//felülrõl 0. elem
						.addComponent(Delete)
						.addComponent(AddH)			//felülrõl 0. elem
						.addComponent(AddKm)		//felülrõl 1. elem
						.addComponent(AddCtn)
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
						.addComponent(BurnHLabel)			//1. sor 0. eleme
						.addComponent(HBurn)
						.addComponent(Delete)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(HLabel)				//2. sor 0. eleme
						.addComponent(HInput)
						.addComponent(AddH)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(BurnKmLabel)				//3. sor 0. eleme
						.addComponent(KmBurn)

						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(KmLabel)				//3. sor 0. eleme
						.addComponent(KmInput)
						.addComponent(AddKm)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(BurnCtnLabel)			//5. sor 0. eleme
						.addComponent(CtnBurn)

						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(CtnLabel)				//3. sor 0. eleme
						.addComponent(CtnInput)
						.addComponent(AddCtn)
						
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(NameLabel)			//7. sor 0. eleme
						.addComponent(CustomName)

						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(WorkoutDropDown)			//8. sor 0. eleme

						)	
				);

	}
	public WorkoutPanel() 
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
