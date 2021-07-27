import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TrackerFrame extends JFrame{

	private User UserInfo=User.getInstance();
	private JPanel backpanel;	//the base panel that holds all the others
	private CardLayout cards;	//the cardlayout that switches between panels
	private UserPanel userpanel;
	private FoodPanel foodpanel;  //Daily menu item
	private WorkoutPanel workoutpanel;
	private WeightPanel weightpanel;
	private TrendsPanel trendspanel;
	
	private JMenuBar menubar;	//the bar on top of window
	private JMenu dropDownMenu; //the menu, listed as views, contains the following:
	private JMenuItem UserView;	
	private JMenuItem Daily;
	private JMenuItem Workout;
	private JMenuItem Weight;
	private JMenuItem Trends;
	
	
	private void MenuInitialize() 
	{
		menubar=new JMenuBar();				//initialize the menubar, and a menu
		dropDownMenu=new JMenu("Views");
		
		UserView=new JMenuItem("User");			//initialize menu items with corresponding names
		Daily=new JMenuItem("Daily");
		Workout=new JMenuItem("Workout");
		Weight=new JMenuItem("Weight");
		Trends=new JMenuItem("Trends");
		UserView.addActionListener(new MenuButtonActionListener());		//add a listener to all menu items
		Daily.addActionListener(new MenuButtonActionListener()); 	//for when they are clicked
		Workout.addActionListener(new MenuButtonActionListener());
		Weight.addActionListener(new MenuButtonActionListener());
		Trends.addActionListener(new MenuButtonActionListener());
		dropDownMenu.add(UserView);				
		dropDownMenu.add(Daily);		//add all menu items to the menu itself
		dropDownMenu.add(Workout);
		dropDownMenu.add(Weight);
		dropDownMenu.add(Trends);
		
		menubar.add(dropDownMenu);		//add the menu to the menubar
		this.setJMenuBar(menubar);		//add the menubar to the frame
	}
	private class MenuButtonActionListener implements ActionListener //needs to check if userinfo filled out
	{
		//once userinfo is filled out
		//opens the panel with the pressed menu item's name
		public void actionPerformed(ActionEvent e) 
		{
			if (UserInfo.getName()!="") 
			{
				foodpanel.Update();
				trendspanel.Update();
				//workoutpanel.Update(); NOT IMPLEMENTED
				//weightpanel.Update(); NOT IMPLEMENTED
				JMenuItem pressed=(JMenuItem) e.getSource();
				cards.show(backpanel,pressed.getText());

			}
		}
	}
	private void PanelInitialize() 
	{
		cards=new CardLayout();			//initialize the custom panel classes and the cardlayout to hold them
		userpanel=new UserPanel();
		foodpanel=new FoodPanel();			
		workoutpanel=new WorkoutPanel();
		weightpanel=new WeightPanel();
		trendspanel=new TrendsPanel();
		
		backpanel=new JPanel(cards);	//setup the main panel holding all the panels as cards
		backpanel.add(userpanel,"User");
		backpanel.add(foodpanel,"Daily");
		backpanel.add(workoutpanel,"Workout");
		backpanel.add(weightpanel,"Weight");
		backpanel.add(trendspanel,"Trends");
		
		this.add(backpanel);			//add the main panel to the frame
		cards.show(backpanel,"User");	//set the user information panel as the starting visible panel
	}


	public TrackerFrame() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fitness Tracker");
		this.setSize(550,500);
		MenuInitialize();
		PanelInitialize();
		
		this.setResizable(true);
		this.setVisible(true);
	}
}
