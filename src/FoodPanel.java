import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




public class FoodPanel extends JPanel{
	private User UserInfo;
	private ArrayList<Food> FoodList;			//food list read from a file
	private JPanel ListPanel;					//panels with their own initializer
	private JPanel UIPanel;
	private JScrollPane scrollpane;
	private JList list;
	
	private JButton Today;		//basic date content
	private JLabel DateLabel;
	private JTextField DateOutput;
	
	private JButton AddAmount,		//UI content
					AddPortion,
					Delete;
	private JTextField 	kcalOutput,
						fatOutput,
						carbsOutput,
						protOutput,
						maintain,
						gain,
						lose;
	private JTextField 	kcalInput,
						fatInput,
						carbsInput,
						protInput,
						amountInput,
						portionInput,
						portionSize,
						NameInput;
	private JLabel 	DailyLabel,
					kcalLabel,
					fatLabel,
					carbsLabel,
					protLabel,
					maintainLabel,
					gainLabel,
					loseLabel,
					CustomNameLabel;
	private JComboBox FoodDropDown;
	private DefaultListModel listModel;
	
	private void ListInitialize() //creates the upper list 
	{
		listModel= new DefaultListModel();
		list=new JList(listModel);
		ListPanel=new JPanel();
		scrollpane=new JScrollPane(list);
		ListPanel.add(scrollpane);
		ListPanel.setVisible(true);
	}
	private void ContentInitialize() //initialize all the labels, buttons, combobox, textfields
	{
		DateLabel=new JLabel("Date");
		Today=new JButton("Today");
		Today.addActionListener(new TodayActionListener());
		DateOutput=new JTextField(20);

		
		DateOutput.setText(UserInfo.getToday().getDate().toString());
		DateOutput.setEditable(false);
		
		AddAmount=new JButton("Add amount");
		AddAmount.addActionListener(new AddAmountActionListener());
		AddPortion=new JButton("Add Portion");
		AddPortion.addActionListener(new AddPortionActionListener());
		Delete=new JButton("Delete");
		Delete.addActionListener(new DeleteActionListener());
		
		kcalOutput=new JTextField();
		fatOutput=new JTextField();
		carbsOutput=new JTextField();
		protOutput=new JTextField();
		maintain=new JTextField();
		gain=new JTextField();
		lose=new JTextField();
		kcalInput=new JTextField();
		fatInput=new JTextField();
		carbsInput=new JTextField();
		protInput=new JTextField();
		amountInput=new JTextField();
		portionInput=new JTextField();
		portionSize=new JTextField();
		NameInput=new JTextField();
		
		kcalOutput.setEditable(false);
		fatOutput.setEditable(false);
		carbsOutput.setEditable(false);
		protOutput.setEditable(false);
		maintain.setEditable(false);
		gain.setEditable(false);
		lose.setEditable(false);
		portionSize.setEditable(false);
		
		maintain.setText(""+UserInfo.Maintain());
		gain.setText(""+UserInfo.Gain());
		lose.setText(""+UserInfo.Lose());
		
		kcalInput.setEditable(false);
		fatInput.setEditable(false);
		carbsInput.setEditable(false);
		protInput.setEditable(false);
		NameInput.setEditable(false);
		
		DailyLabel=new JLabel("Daily");
		kcalLabel=new JLabel("kcal");
		fatLabel=new JLabel("fat");
		carbsLabel=new JLabel("carbs");
		protLabel=new JLabel("protein");
		maintainLabel=new JLabel("kcal to maintain");
		gainLabel=new JLabel("kcal to gain");
		loseLabel=new JLabel("kcal to lose");
		CustomNameLabel=new JLabel("Custom Name");
		
		FoodDropDown=new JComboBox<Food>();
		ComboBoxInitialize();
	}
	private void ComboBoxInitialize() //fill the combobox with a pre-selected list of foods
	{
		FoodList=new ArrayList<Food>();
		//public Food(String name,double kcal,double portionSize,double amount,double fat,double carbs,double protein)
		try 
		{
			BufferedReader br;
			br=new BufferedReader(new InputStreamReader(new FileInputStream("FoodList.txt")));
			while (true) 
			{
				String line = br.readLine();
				if (line == null) break;
				String[] FoodString=line.split(",");
				FoodList.add(new Food(
						FoodString[0],
						Double.parseDouble(FoodString[1]),
						Double.parseDouble(FoodString[2]),
						Double.parseDouble(FoodString[3]),
						Double.parseDouble(FoodString[4]),
						Double.parseDouble(FoodString[5]),
						Double.parseDouble(FoodString[6])
						));
			}
			br.close();
		}
		catch (Exception e)
		{
            e.printStackTrace();
            return;
		}
		if (FoodList.isEmpty()) {}
		else {
			for (int i=0; i<FoodList.size(); i++)
				FoodDropDown.addItem(FoodList.get(i));	
			}
		FoodDropDown.addItemListener(new DropdownItemListener());
	}
	private void UIPanelInitialize()  //create the lower half of UI with button placements
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
						.addComponent(DailyLabel)
						.addComponent(kcalLabel)
						.addComponent(fatLabel)
						.addComponent(carbsLabel)
						.addComponent(protLabel)
						.addComponent(maintainLabel)
						.addComponent(gainLabel)
						.addComponent(loseLabel)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)	//1. oszlop
						.addComponent(DateOutput,40,70,70)	//felülrõl 0. elem
						.addComponent(kcalOutput,40,70,70)
						.addComponent(fatOutput,40,70,70)
						.addComponent(carbsOutput,40,70,70)
						.addComponent(protOutput,40,70,70)
						.addComponent(maintain,40,70,70)
						.addComponent(gain,40,70,70)
						.addComponent(lose,40,70,70)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)	//2. oszlop
						.addComponent(AddAmount)			//felülrõl 0. elem
						.addComponent(AddPortion)		//felülrõl 1. elem
						.addComponent(CustomNameLabel)
						.addComponent(Delete)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)	//3. oszlop
						.addComponent(Today)			//felülrõl 0. elem
						.addComponent(FoodDropDown,40,150,200)		//felülrõl 1. elem
						.addComponent(kcalInput,40,70,70)
						.addComponent(fatInput,40,70,70)
						.addComponent(carbsInput,40,70,70)
						.addComponent(protInput,40,70,70)
						.addComponent(amountInput,40,70,70)
						.addComponent(portionInput,40,70,70)
						.addComponent(NameInput,40,150,200)
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
						.addComponent(DailyLabel)			//1. sor 0. eleme
						.addComponent(FoodDropDown)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(kcalLabel)				//2. sor 0. eleme
						.addComponent(kcalOutput)
						.addComponent(kcalInput)
						)
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(fatLabel)				//3. sor 0. eleme
						.addComponent(fatOutput)
						.addComponent(fatInput)

						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(carbsLabel)				//3. sor 0. eleme
						.addComponent(carbsOutput)
						.addComponent(carbsInput)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(protLabel)			//5. sor 0. eleme
						.addComponent(protOutput)
						.addComponent(protInput)

						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(maintainLabel)				//6. sor 0. eleme
						.addComponent(maintain)
						.addComponent(AddAmount)
						.addComponent(amountInput)
						
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(gainLabel)			//7. sor 0. eleme
						.addComponent(gain)
						.addComponent(AddPortion)
						.addComponent(portionInput)

						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(loseLabel)			//8. sor 0. eleme
						.addComponent(lose)
						.addComponent(CustomNameLabel)
						.addComponent(NameInput)
						)	
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING, false)	//egy sorban lévõ
						.addComponent(Delete)			//9. sor 0. eleme
						)	
				);

	}
	public void Update() //updates the list,date stamp, kcal and macros, and weightloss stuff
	{
		Day ActiveDay=UserInfo.getSelectDateDay();
		
		
		DateOutput.setText(ActiveDay.getDate().toString());
		kcalOutput.setText(""+ActiveDay.getAggregateKcal());
		fatOutput.setText(""+ActiveDay.getAggregateFat());
		carbsOutput.setText(""+ActiveDay.getAggregateCarb());
		protOutput.setText(""+ActiveDay.getAggregateProt());
		maintain.setText(""+UserInfo.Maintain());
		gain.setText(""+UserInfo.Gain());
		lose.setText(""+UserInfo.Lose());
		 
		
		listModel.clear();
		if (ActiveDay.getFoods().isEmpty()) {}
		else {
			for (int i=0; i<ActiveDay.getFoods().size(); i++)
				listModel.addElement(ActiveDay.getFoods().get(i));
		}
	}
	private class TodayActionListener implements ActionListener //changes selected day to today
	{
		public void actionPerformed(ActionEvent e) 
		{
		UserInfo.setSelectDate(UserInfo.getToday().getDate().toString());
		Update();
		}
	} 
	private class AddAmountActionListener implements ActionListener 
	{
		//public Food(String name,double kcal,double portionSize,double amount,double fat,double carbs,double protein)
		//FoodDropDown.getSelectedItem();
		public void actionPerformed(ActionEvent e) 
		{
			if (NameInput.isEditable()) 
			{//if editable then check that all fields are not empty, if empty should do nothing
				if (!NameInput.getText().equals("") &&
					!kcalInput.getText().equals("") &&
					!fatInput.getText().equals("") &&
					!carbsInput.getText().equals("") &&
					!protInput.getText().equals("") &&
					!amountInput.getText().equals(""))
					{//if everything is filled out, checks if it is filled out with numbers then adds a new food
						try 
						{
							double kcal=Double.parseDouble(kcalInput.getText());
							double fat=Double.parseDouble(fatInput.getText());
							double carbs=Double.parseDouble(carbsInput.getText());
							double protein=Double.parseDouble(protInput.getText());
							double amount=Double.parseDouble(amountInput.getText());
							UserInfo.getSelectDateDay().addFood(new Food(NameInput.getText(),
																		kcal,
																		-1,
																		amount,
																		fat,
																		carbs,
																		protein));
							Update();
						}
						catch (Exception ex) 
						{
							ex.printStackTrace();
							return;
						}
					}
			} 
			else 
			{
				if (!amountInput.getText().equals("")) 
				{
					//adds amount of selected food
					Food sel=(Food)FoodDropDown.getSelectedItem();
					double mult=Double.parseDouble(amountInput.getText()); //in relation to 100g
					UserInfo.getSelectDateDay().addFood(new Food(			//original values multiplied by this relational prodict
							sel.getName(),
							Math.round(sel.getKcal()*mult)/100.0d,
							-1,	//portionSize not needed
							Math.round(mult*100)/100.0d,
							Math.round(sel.getFat()*mult)/100.0d,
							Math.round(sel.getCarbs()*mult)/100.0d,
							Math.round(sel.getProtein()*mult)/100.0d));
					Update();
				}
			}
			
			
			
		}
	}
	private class AddPortionActionListener implements ActionListener //add portion*portionInput amount of selected food
	{
		//public Food(String name,double kcal,double portionSize,double amount,double fat,double carbs,double protein)
		//FoodDropDown.getSelectedItem();
		public void actionPerformed(ActionEvent e) 
		{
			if (NameInput.isEditable()) {} //custom cannot add portions
			else if (portionInput.getText().equals("")) {} //didnt specify portion count
			else 
			{
				Food sel=(Food)FoodDropDown.getSelectedItem();
				double mult=Double.parseDouble(portionInput.getText()); //number of portions
				mult*=sel.getPortionSize()/100.0;						//in relation to 100g
				UserInfo.getSelectDateDay().addFood(new Food(			//original values multiplied by this relational prodict
						sel.getName(),
						Math.round(sel.getKcal()*mult*100)/100.0d,
						-1,	//portionSize not needed
						Math.round(sel.getPortionSize()*mult*100)/100.0d,
						Math.round(sel.getFat()*mult*100)/100.0d,
						Math.round(sel.getCarbs()*mult*100)/100.0d,
						Math.round(sel.getProtein()*mult*100)/100.0d));
				Update();
			}
		}
	}
	private class DropdownItemListener implements ItemListener 
	{
		public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	           Food kiv = (Food)event.getItem();
	           if (kiv.getName().equals("Custom food")) //for custom foods all values must be user added
	           {
	        	   	kcalInput.setEditable(true);
					fatInput.setEditable(true);
					carbsInput.setEditable(true);
					protInput.setEditable(true);
					amountInput.setEditable(true);
					portionInput.setEditable(true);
					portionSize.setEditable(true);
					NameInput.setEditable(true);
					kcalInput.setText("");
					fatInput.setText("");
					carbsInput.setText("");
					protInput.setText("");
					amountInput.setText("");
					portionInput.setText("");
					portionSize.setText("");
					NameInput.setText("");
	           }
	           else //else only the amount, or number of portions with pre-existing selected values
	           {
	        	   	kcalInput.setEditable(false);
					fatInput.setEditable(false);
					carbsInput.setEditable(false);
					protInput.setEditable(false);
					portionSize.setEditable(false);
					NameInput.setEditable(false);
					kcalInput.setText(""+kiv.getKcal());
					fatInput.setText(""+kiv.getFat());
					carbsInput.setText(""+kiv.getCarbs());
					protInput.setText(""+kiv.getProtein());
					amountInput.setText("");
					portionInput.setText("");
					NameInput.setText(""+kiv.getName());
					
	           }
	        }
	     }       
		
	}
	private class DeleteActionListener implements ActionListener //delete currently selected food from day's foodlist
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(list.getSelectedValue()!=null)
			{
			String selected[] = list.getSelectedValue().toString().split(", ");
			String keresett[]= new String[2];
			keresett[0]=selected[0];
			keresett[1]=selected[1].substring(0, selected[1].length()-1);
			UserInfo.getSelectDateDay().removeFood(keresett);
			Update();
			}
		}
	}
	public FoodPanel() 
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