import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class UserPanel extends JPanel{

	private User UserInfo;
	private JTextField 	NameInput,
						AgeInput,
						WeightInput,
						HeightInput;
	private JComboBox	SexInput; 
	private JLabel		NameLabel,
						AgeLabel,
						WeightLabel,
						HeightLabel,
						SexLabel;
	private JLabel ProfileLabels[]=new JLabel[3];
	private JButton		Confirm,
						Edit;
	private JButton Saves[]=new JButton[3];		
	private JButton Loads[]=new JButton[3];		
	
	private void ContentInitialize() 
	{
		NameInput=new JTextField(20);
		AgeInput=new JTextField(5);
		WeightInput=new JTextField(5);
		HeightInput=new JTextField(5);
		SexInput=new JComboBox(Sex.values());
		
		NameLabel=new JLabel("Name");
		AgeLabel=new JLabel("Age");
		WeightLabel=new JLabel("Weight (kg)");
		HeightLabel=new JLabel("Height (cm)");
		SexLabel=new JLabel("Sex");
		for (int i=0; i<3; i++)
		{
			ProfileLabels[i]=new JLabel("Profile "+(i+1));
			Saves[i]=new JButton("Save");
			Saves[i].addActionListener(new SaveActionListener("profile"+(i+1)+".txt"));
			Loads[i]=new JButton("Load");
			Loads[i].addActionListener(new LoadActionListener("profile"+(i+1)+".txt"));
		}
		Confirm=new JButton("Confirm");
		Edit=new JButton("Edit");

		 
		
		Confirm.addActionListener(new ConfirmActionListener());
		Edit.addActionListener(new EditActionListener());
		
	}
	private void LayoutInitialize() 
	{
		GroupLayout layout=new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)	//0. oszlop
						.addComponent(NameLabel)	//felülrõl 0. elem
						.addComponent(AgeLabel)		//felülrõl 1. elem
						.addComponent(WeightLabel)
						.addComponent(HeightLabel)
						.addComponent(SexLabel)
						.addComponent(Confirm)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING) //1. oszlop
						.addComponent(NameInput,20,110,110)	//felülrõl 0. elem
						.addComponent(AgeInput,20,40,40)	//felülrõl 1. elem
						.addComponent(WeightInput,20,40,40)
						.addComponent(HeightInput,20,40,40)
						.addComponent(SexInput,20,85,85)
						.addComponent(Edit)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(ProfileLabels[0])					
						.addComponent(ProfileLabels[1])					
						.addComponent(ProfileLabels[2])					
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(Saves[0])							
						.addComponent(Saves[1])							
						.addComponent(Saves[2])							
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(Loads[0])							
						.addComponent(Loads[1])							
						.addComponent(Loads[2])							
						)
				);
		
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()		//szekvenciális felülrõl lefele
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(NameLabel)			//0. sor 0. eleme
						.addComponent(NameInput)			//0. sor 1. eleme
						.addComponent(ProfileLabels[0])		
						.addComponent(Saves[0])				
						.addComponent(Loads[0]) 				
						)			
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(AgeLabel)											//1. sor
						.addComponent(AgeInput)
						.addComponent(ProfileLabels[1])		
						.addComponent(Saves[1])				
						.addComponent(Loads[1])				
						)	
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(WeightLabel)										//2. sor
						.addComponent(WeightInput)
						.addComponent(ProfileLabels[2])		
						.addComponent(Saves[2])				
						.addComponent(Loads[2])				
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(HeightLabel)
						.addComponent(HeightInput)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(SexLabel)
						.addComponent(SexInput)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(Confirm)
						.addComponent(Edit)
				)
				);
	}
	private class ConfirmActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (NameInput.isEditable()) 
			{
				double age;
				double weight;
				double height;
				try 
				{
			        if (NameInput.getText()=="") return;
			        age= Double.parseDouble(AgeInput.getText());
					weight = Double.parseDouble(WeightInput.getText());
					height= Double.parseDouble(HeightInput.getText());
			    } catch (NumberFormatException exc) {return;}
				UserInfo.setName(NameInput.getText());
				UserInfo.setAge(age);
				UserInfo.setWeight(weight);
				UserInfo.setHeight(height);
				UserInfo.setSex((Sex)SexInput.getSelectedItem()); 
				NameInput.setEditable(false);
				AgeInput.setEditable(false);
				WeightInput.setEditable(false);
				HeightInput.setEditable(false);
				SexInput.setEnabled(false);
				
			}
			//if already confirmed or not ( loading sets editable to flase) by nameinput.iseditable
			//	if editable check inputs for validity
			//		if valid set userinfo variables
			//			setEditable false on inputs, 
			//else if !editable do nothing because already confirmed
		}
	}
	private class EditActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(UserInfo.getName()!="") //if there is already a filled userinfo
			{
				if (NameInput.isEditable()) //this turns off editable
				{
					NameInput.setText(UserInfo.getName());
					AgeInput.setText(""+UserInfo.getAge());
					WeightInput.setText(""+UserInfo.getWeight());
					HeightInput.setText(""+UserInfo.getHeight());
					SexInput.setSelectedItem(UserInfo.getSex()); 
					NameInput.setEditable(false);
					AgeInput.setEditable(false);
					WeightInput.setEditable(false);
					HeightInput.setEditable(false);
					SexInput.setEnabled(false);
				} 
				else if (!NameInput.isEditable()) //this turns on editable
				{
					NameInput.setEditable(true);
					AgeInput.setEditable(true);
					WeightInput.setEditable(true);
					HeightInput.setEditable(true);
					SexInput.setEnabled(true);
				}
			}
			//if userinfo.name!="" as in already confirmed/loaded
			//	if editable: set text to userinfo variables, then set editable false
			//	else if !editable: set editable
		}
	}
	private class SaveActionListener implements ActionListener 
	{
		String file;
		public void actionPerformed(ActionEvent e) 
		{
			if(UserInfo.getName()!="") //if there is already a filled userinfo
			{						   //save the "state" of if, by serializing the inner data segment
				UserInfo.Save(file);
			}
			
		}
		public SaveActionListener(String filename) 
		{
			file=filename;
		}
	}
	private class LoadActionListener implements ActionListener 
	{
		String file;
		public void actionPerformed(ActionEvent e) //load the data segment into the userinfo 
		{										   //and set this data on the input fields	
			UserInfo.Load(file);				   //as if user pressed confirm
			NameInput.setText(UserInfo.getName());
			AgeInput.setText(""+UserInfo.getAge());
			WeightInput.setText(""+UserInfo.getWeight());
			HeightInput.setText(""+UserInfo.getHeight());
			SexInput.setSelectedItem(UserInfo.getSex()); 
			NameInput.setEditable(false);
			AgeInput.setEditable(false);
			WeightInput.setEditable(false);
			HeightInput.setEditable(false);
			SexInput.setEnabled(false);
			
		}
		public LoadActionListener(String filename) 
		{
			file=filename;
		}
	}
	public UserPanel() 
	{
		this.UserInfo=User.getInstance();
		ContentInitialize();
		LayoutInitialize();
		this.setVisible(true);
	}
}
