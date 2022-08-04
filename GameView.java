import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.InputStream;
import java.io.File;

/**
 * <p>
 * GameView handles the view of the main Game panel. It is the Frame of the Game that holds all the components of the Game panel, including methods to manipulate these components. </p>
 */
public class GameView extends JFrame 
{
    private CardLayout cardLayout;
    private JPanel panel;
    private Font fontStyle;
    
    private JPanel main;
    private JButton play;
    private JButton help;
    private JButton config;
    private JButton exit;

    private GamePanel game;
    private JButton career;
    private JButton salary;
    private JButton house;
    private JButton miles;
    private JButton rolldice;
    private JButton payloan;
    private JButton getloan;
    private JLabel peg1;
    private JLabel peg2;
    private JLabel peg3;
    private JLabel nameTxt;
    private JLabel cashTxt;
    private JLabel loanTxt;
    private JLabel pathTxt;

    /**
     * creates the GameView object. It sets the size of the frame makes the frame unresizable, and sets the frame as visible.
     */
    public GameView () 
    {
        super ("That's Life");
        GraphicsEnvironment ge = null;
        fontStyle = null;
                
        try 
				{
            InputStream is = Main.class.getResourceAsStream("/fonts/PressStart2P.ttf");
            fontStyle = Font.createFont (fontStyle.TRUETYPE_FONT, is);
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
            ge.registerFont (fontStyle);
        } 
				catch (Exception ex) 
				{
            fontStyle = new Font ("Roboto", Font.PLAIN, 1); 
        }
        
        game = new GamePanel ();
        career = new JButton ("Career Card");
        salary = new JButton ("Salary Card");
        house = new JButton ("House Card");
        miles = new JButton ("Milestones");
        nameTxt = new JLabel ();
        cashTxt = new JLabel ();
        loanTxt = new JLabel ();
        pathTxt = new JLabel ();
        rolldice = new JButton ("Roll Dice");
        payloan = new JButton ("Pay Loan");
        getloan = new JButton ("Get Loan");
        peg1 = new JLabel ();
        peg2 = new JLabel ();
        peg3 = new JLabel ();
        
        initializeGUI ();

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setSize (616, 735);
        setLocationRelativeTo (null);
        setBackground (new Color (247, 255, 247));
        setResizable (false);
        setVisible (true);
        playBGMusic();
    }

    /**
     * initializes the panel of the GUI, and the Main Menu and Game panel.
     */
    public void initializeGUI () 
		{
        cardLayout = new CardLayout ();
        panel = new JPanel ();
        panel.setLayout (cardLayout);

        initMainMenu ();
        initGame ();

        panel.add (main, "MAIN");
        panel.add (game, "GAME");

        add (panel);
        cardLayout.show (panel, "MAIN");
    }

    /**
     * initializes the components of the main menu panel.
     */
    public void initMainMenu () 
		{
        main = new JPanel ();
        main.setLayout (new GridBagLayout ());
        GridBagConstraints c = new GridBagConstraints ();

        JLabel title = new JLabel ("That's Life!", SwingConstants.CENTER);
        title.setFont (fontStyle.deriveFont (Font.BOLD, (float) 45));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        main.add (title, c);

        play = new JButton ("START GAME");
        play.setBackground (new Color (78, 205, 196));
        play.setBorder (null);
        play.setFont (fontStyle.deriveFont (Font.BOLD, (float) 30));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.BOTH;
        main.add (play, c);

        help = new JButton("INSTRUCTIONS");
        help.setBackground (new Color(255, 107, 107));
        help.setBorder (null);
        help.setFont (fontStyle.deriveFont (Font.BOLD, (float) 30));
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.BOTH;
        main.add (help, c);

        exit = new JButton ("EXIT GAME");
        exit.setBackground (new Color (255, 230, 109));
        exit.setBorder (null);
        exit.setFont (fontStyle.deriveFont (Font.BOLD, (float) 30));
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1.0;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.BOTH;
        main.add (exit, c);
    }

    /**
     * creates and initializes the components in the game panel.
     */
    public void initGame () 
        {
        int bWidthL = 120;
        int bHeightL = 25;
        int bWidthR = 220;
        int bHeightR = 35;
        
        game.setLayout (null);

        career.setBackground (new Color (41, 47, 54));
        career.setForeground (new Color (247, 255, 247));
        career.setBorder (null);
        career.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 10));
        career.setBounds (25, 610, bWidthL, bHeightL);
        game.add (career);

        salary.setBackground (new Color (41, 47, 54));
        salary.setForeground(new Color (247, 255, 247));
        salary.setBorder (null);
        salary.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 10));
        salary.setBounds (25, 645, bWidthL, bHeightL);
        game.add (salary);

        house.setBackground (new Color(41, 47, 54));
        house.setForeground (new Color(247, 255, 247));
        house.setBorder (null);
        house.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 10));
        house.setBounds (155, 610, bWidthL, bHeightL);
        game.add (house);

        miles.setBackground (new Color (41, 47, 54));
        miles.setForeground (new Color (247, 255, 247));
        miles.setBorder (null);
        miles.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 10));
        miles.setBounds (155, 645, bWidthL, bHeightL);
        game.add (miles);

        nameTxt.setFont (fontStyle.deriveFont (Font.BOLD, (float) 24));
        nameTxt.setBounds (30, 440, 200, 50);
        game.add (nameTxt);
        
        cashTxt.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 14));
        cashTxt.setBounds (45, 470, 200, 50);
        game.add (cashTxt);

        loanTxt.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 14));
        loanTxt.setBounds (45, 490, 200, 50);
        game.add (loanTxt);

        JLabel cur = new JLabel ("Currently at: ");
        cur.setFont (fontStyle.deriveFont (Font.BOLD, (float) 14));
        cur.setBounds (30, 540, 300, 50);
        game.add (cur);

        pathTxt.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 10));
        pathTxt.setBounds (40, 560, 300, 50);
        game.add (pathTxt);

        JLabel action = new JLabel ("What will you do?");
        action.setFont (fontStyle.deriveFont (Font.BOLD, (float) 14));
        action.setBounds (332, 480, 300, 50);
        game.add (action);

        rolldice.setBackground (new Color (41, 47, 54));
        rolldice.setForeground (new Color (247, 255, 247));
        rolldice.setBorder (null);
        rolldice.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 18));
        rolldice.setBounds (340, 520, bWidthR, bHeightR);
        game.add (rolldice);

        payloan.setBackground (new Color (41, 47, 54));
        payloan.setForeground (new Color (247, 255, 247));
        payloan.setBorder (null);
        payloan.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 18));
        payloan.setBounds (340, 560, bWidthR, bHeightR);
        game.add (payloan);

        getloan.setBackground (new Color (41, 47, 54));
        getloan.setForeground (new Color (247, 255, 247));
        getloan.setBorder (null);
        getloan.setFont (fontStyle.deriveFont (Font.PLAIN, (float) 18));
        getloan.setBounds (340, 600, bWidthR, bHeightR);
        game.add (getloan);
        
        ImageIcon image1 = new ImageIcon ("images/peg1.png");
        peg1.setIcon (image1);
        peg1.setBounds (95, 54, 26, 26);
        game.add (peg1);
        
        ImageIcon image2 = new ImageIcon ("images/peg2.png");
        peg2.setIcon (image2);
        peg2.setBounds (95, 54, 26, 26);
        game.add (peg2);
        
        ImageIcon image3 = new ImageIcon ("images/peg3.png");
        peg3.setIcon (image3);
        peg3.setBounds (95, 54, 26, 26);
        game.add (peg3);
    }

		/**
     * plays a sound file when button is pressed.
     */
		public void playButtonSound ()
    {  
        try
        {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
					(new File("sounds/boop.wav").getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
        }
        catch (Exception e)
        {
            
        }
    }

		/**
		 * plays the background music
		 */              
    public void playBGMusic ()
    {
      try
    	{
        AudioInputStream bgMusic = AudioSystem.getAudioInputStream(new File("sounds/music.wav"));
        Clip clip = AudioSystem.getClip();
      	clip.open(bgMusic);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				FloatControl control = 
    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				control.setValue(-25.0f); 
        clip.start();   
      }
      catch (Exception ex)
      {
                        
      }
    }
    
		/**
		 * sets the pegs of the player to the current space
		 * 
		 * @param p is the player that is currently in turn
		 */
    public void setPegs (Player p) 
		{
        int startX = 95;
        int startY = 54;
        int distance = 24;
        
        int[][] careercoords = {
            {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1},
            {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5},
            {7, 6}, {8, 6}, {9, 6}, {9, 5}, {9, 4},
            {9, 3}, {9, 2}, {9, 1}, {10, 1}, {11, 1},
            {11, 2}, {11, 3}, {11, 4}, {11, 5}, {11, 6},
            {12, 6}, {13, 6}, {14, 6}, {15, 6}, {16, 6},
            {17, 6}, {17, 7}, {17, 8}, {17, 9}, {17, 10},
            {17, 11}, {17, 12}, {17, 13}, {16, 13}, {15, 13},
            {14, 13}, {13, 13}, {12, 13}, {11, 13}, {10, 13},
            {9, 13}, {9, 12}, {9, 11}, {9, 10}, {9, 9},
            {9, 8}, {8, 8}, {7, 8}, {6, 8}, {5, 8},
            {4, 8}, {4, 9}, {4, 10}, {4, 11}, {4, 12}
        };
        
        int[][] collegecoords = {
            {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6},
            {2, 6}, {3, 6}, {4, 6}, {5, 6}, {6, 6}
        };
        
        int[][] changecoords = {
            {12, 1}, {13, 1}, {14, 1}, {15, 1}, {16, 1},
            {17, 1}, {17, 2}, {17, 3}, {17, 4}, {17, 5}
        };
        
        int[][] familycoords = {
            {15, 12}, {15, 11}, {15, 10}, {15, 9}, {15, 8},
            {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8}
        };
        
        int x = 1, y = 1;
        Path path = p.getPath();
        switch (path.getName())
				{
            case "Career Path":
                x = startX+distance*(careercoords[p.getCurrentIndex()][0] - 1);
                y = startY+distance*(careercoords[p.getCurrentIndex()][1] - 1);
                break;
            case "College Path":
                x = startX+distance*(collegecoords[p.getCurrentIndex()][0] - 1);
                y = startY+distance*(collegecoords[p.getCurrentIndex()][1] - 1);
                break;
            case "C. Career Path":
                x = startX+distance*(changecoords[p.getCurrentIndex()][0] - 1);
                y = startY+distance*(changecoords[p.getCurrentIndex()][1] - 1);
                break;
            case "Family Path":
                x = startX+distance*(familycoords[p.getCurrentIndex()][0] - 1);
                y = startY+distance*(familycoords[p.getCurrentIndex()][1] - 1);
                break;
        }
        
        switch(p.getNumber())
				{
            case 1:
                peg1.setLocation(x, y);
                break;
            case 2:
                peg2.setLocation(x, y);
                break;
            case 3:
                peg3.setLocation(x, y);
                break;
        }
    }

    /**
     * creates and instantiate the different components needed to show the actionCard that the player got.
     *
     * @param ac is the actionCard that the player got.
     */
    public void showActionCard(ActionCard ac) 
		{
        JDialog card = new JDialog((Frame) null, "Action Card",
                Dialog.ModalityType.APPLICATION_MODAL);

        ActionCardPanel bg = new ActionCardPanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel txt = new JLabel("Got an Action Card!", SwingConstants.CENTER);
        txt.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));
        txt.setBounds(5, 20, 200, 20);
        bg.add(txt);

        String type, textA, textB, textC = "";
        if (ac instanceof PayPlayer) 
				{
            type = "Pay Player";
            textA = "Pay $" + Integer.toString(ac.getAmt());
            if (ac.getDesc() == 0) 
						{
                textB = "to a chosen";
                textC = "Player!";
            } 
						else 
						{
                textB = "to all Players!";
            }
        } 
				else if (ac instanceof CollectFromPlayer) 
				{
            type = "Collect From Player";
            textA = "Get $" + Integer.toString(ac.getAmt());
            if (ac.getDesc() == 0) 
						{
                textB = "from a chosen";
                textC = "Player!";
            } 
						else 
						{
                textB = "from all Players!";
            }
        } 
				else if (ac instanceof PayBank) 
				{
            type = "Pay Bank";
            textA = "Pay $" + Integer.toString(ac.getAmt());
            textB = "to the Bank!";
        } 
				else 
				{
            type = "Collect from Bank";
            textA = "Collect $" + Integer.toString(ac.getAmt());
            textB = "from the Bank!";
        }

        JLabel title = new JLabel(type, SwingConstants.CENTER);
        title.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 7));
        title.setBounds(0, 72, 200, 20);
        bg.add(title);

        String desc = ac.toString();
        String[] descs = desc.split(" ", 2);
        if (desc.equals("It's your Birthday!")) 
				{
            descs[0] = "It's your";
            descs[1] = "Birthday!";
        } 
				else if (desc.equals("Win a competition")) 
				{
            descs[0] = "Win a";
            descs[1] = "competition";
        }

        JLabel descA = new JLabel(descs[0], SwingConstants.CENTER);
        descA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descA.setBounds(0, 120, 200, 20);
        bg.add(descA);

        if (descs.length == 2) 
				{
            JLabel descB = new JLabel(descs[1], SwingConstants.CENTER);
            descB.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
            descB.setBounds(0, 135, 200, 20);
            bg.add(descB);
        }

        JLabel txtA = new JLabel(textA, SwingConstants.CENTER);
        txtA.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));
        txtA.setBounds(0, 170, 200, 20);
        bg.add(txtA);

        JLabel txtB = new JLabel(textB, SwingConstants.CENTER);
        txtB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));
        txtB.setBounds(0, 185, 200, 20);
        bg.add(txtB);

        JLabel txtC = new JLabel(textC, SwingConstants.CENTER);
        txtC.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));
        txtC.setBounds(0, 200, 200, 20);
        bg.add(txtC);

        card.setSize(216, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);
    }

    /**
     * creates and instantiate the different components needed to show the blueCard that the player got.
     *
     * @param bc is the blueCard that the player got
     */
    public void showBlueCard(BlueCard bc) 
		{
        JDialog card = new JDialog((Frame) null, "Blue Card",
                Dialog.ModalityType.APPLICATION_MODAL);

        BlueCardPanel bg = new BlueCardPanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel txt = new JLabel("Got a Blue Card!", SwingConstants.CENTER);
        txt.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        txt.setBounds(5, 20, 200, 20);
        bg.add(txt);

        String type;
        if (bc instanceof Lawsuit) 
				{
            type = "Lawsuit";
        } 
				else if (bc instanceof CompRepair) 
				{
            type = "Computer Repair";
        } 
				else if (bc instanceof WorldCup) 
				{
            type = "World Cup";
        } 
				else if (bc instanceof TipServer) 
				{
            type = "Tip the Server";
        } 
				else if (bc instanceof SkiAccident) 
				{
            type = "Ski Accident";
        } 
				else if (bc instanceof SalaryTaxDue) 
				{
            type = "Salary Tax Due";
        } 
				else 
				{
            type = "F1 Race";
        }

        JLabel title = new JLabel(type, SwingConstants.CENTER);
        title.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 7));
        title.setBounds(0, 72, 200, 20);
        bg.add(title);

        String desc = bc.toString();
        String[] descs = desc.split(" ", 6);

        JLabel descA = new JLabel(descs[0], SwingConstants.CENTER);
        descA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descA.setBounds(0, 120, 200, 20);
        bg.add(descA);

        JLabel descB = new JLabel(descs[1], SwingConstants.CENTER);
        descB.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descB.setBounds(0, 135, 200, 20);
        bg.add(descB);

        JLabel descC = new JLabel(descs[2], SwingConstants.CENTER);
        descC.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descC.setBounds(0, 150, 200, 20);
        bg.add(descC);

        JLabel descD = new JLabel(descs[3], SwingConstants.CENTER);
        descD.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descD.setBounds(0, 165, 200, 20);
        bg.add(descD);

        JLabel descE = new JLabel(descs[4], SwingConstants.CENTER);
        descE.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descE.setBounds(0, 180, 200, 20);
        bg.add(descE);

        JLabel descF = new JLabel(descs[5], SwingConstants.CENTER);
        descF.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        descF.setBounds(0, 195, 200, 20);
        bg.add(descF);

        card.setSize(216, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);
    }

    /**
     * creates and instantiate the different components needed to show the careerCard.
     *
     * @param cc is the careerCard to be shown
     */
    public void showCareerCard(CareerCard cc) 
		{
        JDialog card = new JDialog((Frame) null, "Career Card",
                Dialog.ModalityType.APPLICATION_MODAL);

        CareerCardPanel bg = new CareerCardPanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel txt = new JLabel("Your Career:", SwingConstants.CENTER);
        txt.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        txt.setBounds(5, 20, 200, 20);
        bg.add(txt);

        ImageIcon image;
        JLabel car = new JLabel(cc.getCareer(), SwingConstants.CENTER);
        car.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));

        switch (cc.getCareer()) 
				{
            case "Computer Consultant":
                image = new ImageIcon("images/computerConsultant.png");
                car.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 7));
                break;
            case "Athlete":
                image = new ImageIcon("images/athlete.png");
                break;
            case "Doctor":
                image = new ImageIcon("images/doctor.png");
                break;
            case "Accountant":
                image = new ImageIcon("images/accountant.png");
                break;
            case "Lawyer":
                image = new ImageIcon("images/lawyer.png");
                break;
            case "Racecar Driver":
                image = new ImageIcon("images/racecarDriver.png");
                break;
            default:
                image = new ImageIcon("images/server.png");
                break;
        }

        car.setBounds(0, 70, 200, 20);
        bg.add(car);

        JLabel icon = new JLabel();
        icon.setIcon(image);
        icon.setBounds(68, 105, 64, 64);
        bg.add(icon);

        JLabel payA = new JLabel("Max # of Pay Raise", SwingConstants.CENTER);
        payA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 7));
        payA.setBounds(0, 175, 200, 20);
        bg.add(payA);

        JLabel payB = new JLabel(Integer.toString(cc.getPayRaiseLimit()), SwingConstants.CENTER);
        payB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        payB.setBounds(0, 190, 200, 20);
        bg.add(payB);

        JLabel degA = new JLabel("Requires a Degree?", SwingConstants.CENTER);
        degA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 7));
        degA.setBounds(0, 200, 200, 20);
        bg.add(degA);

        String yn;
        if (cc.getDegreeReq()) 
				{
            yn = "Yes";
        } 
				else 
				{
            yn = "No";
        }

        JLabel degB = new JLabel(yn, SwingConstants.CENTER);
        degB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        degB.setBounds(0, 212, 200, 20);
        bg.add(degB);

        card.setSize(216, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);
    }

    /**
     * creates and instantiate the different components needed to show the
     * salaryCard.
     *
     * @param sc the salaryCard to be shown.
     */
    public void showSalaryCard(SalaryCard sc) 
		{
        JDialog card = new JDialog((Frame) null, "Salary Card",
                Dialog.ModalityType.APPLICATION_MODAL);

        SalaryCardPanel bg = new SalaryCardPanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel txt = new JLabel("Your Salary:", SwingConstants.CENTER);
        txt.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        txt.setBounds(5, 20, 200, 20);
        bg.add(txt);

        JLabel title = new JLabel("Salary Card", SwingConstants.CENTER);
        title.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        title.setBounds(0, 72, 200, 20);
        bg.add(title);

        JLabel salA = new JLabel("Salary", SwingConstants.CENTER);
        salA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        salA.setBounds(0, 105, 200, 20);
        bg.add(salA);

        JLabel salB = new JLabel("$" + Integer.toString(sc.getSalary()), SwingConstants.CENTER);
        salB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        salB.setBounds(0, 120, 200, 20);
        bg.add(salB);

        JLabel taxA = new JLabel("Tax Due", SwingConstants.CENTER);
        taxA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        taxA.setBounds(0, 145, 200, 20);
        bg.add(taxA);

        JLabel taxB = new JLabel("$" + Integer.toString(sc.getTaxDue()), SwingConstants.CENTER);
        taxB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        taxB.setBounds(0, 160, 200, 20);
        bg.add(taxB);

        JLabel prvA = new JLabel("Pay Raise Value", SwingConstants.CENTER);
        prvA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 8));
        prvA.setBounds(0, 185, 200, 20);
        bg.add(prvA);

        JLabel prvB = new JLabel("$" + Integer.toString(sc.getPayRaiseVal()), SwingConstants.CENTER);
        prvB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        prvB.setBounds(0, 200, 200, 20);
        bg.add(prvB);

        card.setSize(216, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);
    }

    /**
     * shows the houseCard of the player
     *
     * @param hc is the houseCard of the player
     */
    public void showHouseCard(HouseCard hc) 
		{
        JDialog card = new JDialog((Frame) null, "House Card",
                Dialog.ModalityType.APPLICATION_MODAL);

        HouseCardPanel bg = new HouseCardPanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel txt = new JLabel("Your House:", SwingConstants.CENTER);
        txt.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        txt.setBounds(5, 20, 200, 20);
        bg.add(txt);

        JLabel title = new JLabel("House Card", SwingConstants.CENTER);
        title.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 7));
        title.setBounds(0, 72, 200, 20);
        bg.add(title);

        String type = hc.getType();
        String[] typesSplit = type.split(" ", 2);
				String[] types = {"", ""};

				if (typesSplit.length == 1)
					types [0] = typesSplit [0];
				else
					types = typesSplit;

        JLabel typeA = new JLabel(types[0], SwingConstants.CENTER);
        typeA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        typeA.setBounds(0, 110, 200, 20);
        bg.add(typeA);

        JLabel typeB = new JLabel(types[1], SwingConstants.CENTER);
        typeB.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        typeB.setBounds(0, 125, 200, 20);
        bg.add(typeB);

        JLabel sellA = new JLabel("Selling Price", SwingConstants.CENTER);
        sellA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        sellA.setBounds(0, 150, 200, 20);
        bg.add(sellA);

        JLabel sellB = new JLabel("$" + Integer.toString(hc.getSellingPrice()), SwingConstants.CENTER);
        sellB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        sellB.setBounds(0, 165, 200, 20);
        bg.add(sellB);

        JLabel buyA = new JLabel("Buying Price", SwingConstants.CENTER);
        buyA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        buyA.setBounds(0, 190, 200, 20);
        bg.add(buyA);

        JLabel buyB = new JLabel("$" + Integer.toString(hc.getBuyingPrice()), SwingConstants.CENTER);
        buyB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        buyB.setBounds(0, 205, 200, 20);
        bg.add(buyB);

        card.setSize(216, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);
    }

    /**
     * asks the user how many players there would be
     *
     * @return number of players; 0 if the player closes the dialog box
     */
    public int askPlayerNum() {
        int num;

        Object[] nums = {"Two Players", "Three Players"};

        num = JOptionPane.showOptionDialog(this,
                "Which path will you take?", "Player Setup",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, nums, null);

        switch (num) {
            case JOptionPane.YES_OPTION:
                return 2;
            case JOptionPane.NO_OPTION:
                return 3;
            default:
                return 0;
        }
    }

    /**
     * asks the player for his name
     *
     * @param num is the number associated with the player
     * @return name of the player; null if the player closes the dialog box
     */
    public String askPlayerName(int num) 
		{
        String name;
        name = JOptionPane.showInputDialog("Player " + num
                + ", what is your name?");

        return name;
    }

    /**
     * asks the player which path he would like to go through
     *
     * @return the number associated with the path that the player chose
     */
    public int askPlayerPath() 
		{
        int path;

        Object[] paths = {"Career Path", "College Path"};

        path = JOptionPane.showOptionDialog(this,
                "Which path will you take?", "Player Setup",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, paths, null);

        switch (path) 
				{
            case JOptionPane.YES_OPTION:
                return 0;
            case JOptionPane.NO_OPTION:
                return 1;
            default:
                return -1;
        }
    }

    /**
     * shows the instruction of the game using a dialog box
     */
    public void showInstructions() 
		{
        JOptionPane.showMessageDialog(this, "That’s Life! mirrors life events of people from going to college, having a career, \nraising a family, investing, buying a house, working, and retiring. \n\nThe goal of this game is for the players to reach retirement as early as possible \nwith the most savings on hand. The player decides what kind of life he wants \nto experience during the game. \n\nAt the start of each round, the player press for a randomly generated number \n[1-10]. This determines the number of spaces he will advance on the board. \nUnlike the usual board games, where there is only one path from start to end, \nThat’s Life!, at some areas along the way, present players with two options: to \ncontinue moving forward, or to take a shorter (or maybe longer) route to reach \nanother space on the board.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * shows the error when a person presses "okay" when entering his name
     * without writing any character
     */
    public void showErrorName () 
		{
        JOptionPane.showMessageDialog(this, "Please enter your name.", "ALERT", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * shows the message when the player choose the college path.
     */
    public void showCollegePath() 
		{
        JOptionPane.showMessageDialog(this, "You have borrowed $40000 from the Bank.", "College Path", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * shows the number generated when the roll dice was clicked by the player
     *
     * @param roll is the number generated
     * @param space is the space that the player landed on
     */
    public void showDiceRoll (int roll, Space space) 
		{
        String rollTxt = "a ";
        if (roll == 8) 
				{
            rollTxt = "an ";
        }

        JOptionPane.showMessageDialog(this, "You have rolled " + rollTxt
                + Integer.toString(roll) + ". " + space.toString(), "Roll Dice", JOptionPane.PLAIN_MESSAGE);
    }

		/**
		 * asks the player how much he would like to pay for his loans
		 * @return amount that the player would like to pay in String
		 */
		public String payLoanPrompt() 
		{      
        String amt = JOptionPane.showInputDialog(this, "How much will you pay?", "Pay Loans", JOptionPane.PLAIN_MESSAGE);
        return amt;
    }

		/**
		 * asks the player how much he would like to loan
		 * @return amount that the player would like to loan in String
		 */
    public String getLoanPrompt() 
		{      
        String amt = JOptionPane.showInputDialog(this, "How much will you loan?",
                "Get a Loan", JOptionPane.PLAIN_MESSAGE);
        return amt;
    }

    /**
     * shows what the actionCard that the player got does.
     *
     * @param ac the actionCard that the player got.
     */
    public void showActionPrompt(ActionCard ac) 
		{
        String text = "You have ";
        if (ac instanceof PayPlayer) 
				{
            text += "paid $" + ac.getAmt();
            if (ac.getDesc() == 0) 
						{
                text += " to the chosen player!";
            } 
						else 
						{
                text += " to everyone!";
            }
        } 
				else if (ac instanceof PayBank) 
				{
            text = text + "paid $" + ac.getAmt() + " to the Bank!";
        } 
				else if (ac instanceof CollectFromPlayer) 
				{
            text = text + "collected $" + ac.getAmt();
            if (ac.getDesc() == 0) 
						{
                text += " from the chosen player!";
            } 
						else 
						{
                text += " from everyone!";
            }
        } 
				else 
				{
            text = text + "collected $" + ac.getAmt() + " from the Bank!";
        }

        JOptionPane.showMessageDialog(this, text, "Action Card", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * shows what the blueCard that the player got does.
     *
     * @param bc is the blueCard that the player got
     * @param match is the index of the player that matches the career indicated in the blueCard; if none, -1
     * @param index is the index of the player currently in turn
     */
    public void showBluePrompt(BlueCard bc, int match, int index) 
		{
        String text, pay = "";
        if (match == index) 
				{
            text = bc.matchText();
        } 
				else 
				{
            text = bc.notMatchText();
            pay = bc.payText();
            if (match != -1) 
						{
                pay += "to Player " + Integer.toString(match + 1) + "!";
            } 
						else 
						{
                pay += "to the Bank!";
            }
        }

        JOptionPane.showMessageDialog(this, text, "Blue Card", JOptionPane.PLAIN_MESSAGE);

        if (match != index) 
				{
            if (bc instanceof CompRepair || bc instanceof TipServer) 
						{
                JOptionPane.showMessageDialog(this, "Press OK to generate a random number!", "Random Number Generator", JOptionPane.PLAIN_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, pay, "Blue Card", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * shows the information about the green space that the player landed on
     *
     * @param sc is the salaryCard of the player
     * @param gs is the space that the player landed on
     */
    public void showGreenPrompt(SalaryCard sc, GreenSpace gs) 
		{
        String text, pay = "";
        if (gs.getType().equals("Pay Day")) 
				{
            text = "You have landed on a Pay Day Space, get $" + sc.getSalary() + " from the Bank!";
        } 
				else if (sc.getMaxSalary () != sc.getSalary()) 
				{
            text = "You have landed on a Pay Raise Space, salary increased by $" + sc.getPayRaiseVal () + ", and get $" + (sc.getSalary() + sc.getPayRaiseVal ()) + " from the Bank!";
        }
				else
				{
						text = "You have landed on a Pay Raise Space,but you have reached the maximum number of pay raises.\nGet $" + sc.getSalary() + " from the Bank!";
				}

        JOptionPane.showMessageDialog(this, text, "Green Space", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * asks the player if he wants to change his path to change career path or stay in his path
     *
     * @return player choice for his path
     */
    public int askChangeCareerPath() 
		{
        int path;

        Object[] paths = {"Career Path", "Change Career Path"};

        path = JOptionPane.showOptionDialog(this,
                "JUNCTION! Which Path?", "Junction",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, paths, null);

        switch (path) 
				{
            case JOptionPane.YES_OPTION:
                return 0;
            case JOptionPane.NO_OPTION:
                return 2;
            default:
                return -1;
        }
    }

    /**
     * asks the player if he wants to change his path to start a family path or stay in his path
     *
     * @return player choice for his path
     */
    public int askFamilyPath() 
		{
        int path;

        Object[] paths = {"Career Path", "Family Path"};

        path = JOptionPane.showOptionDialog(this,
                "JUNCTION! Which Path?", "Junction",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, paths, null);

        switch (path) 
				{
            case JOptionPane.YES_OPTION:
                return 0;
            case JOptionPane.NO_OPTION:
                return 3;
            default:
                return -1;
        }
    }

    /**
     * asks the player which player he would like to take the action on
     *
     * @param p is the array of player numbers that the player can choose from
     * @return the index of the player that the player can choose from
     */
    public int askWhichPlayer(int[] p) 
		{
        Object[] players = {"Player " + Integer.toString(p[0]+1), "Player " + Integer.toString(p[1]+1)};

        int play = JOptionPane.showOptionDialog(this,
                "Choose which player.", "Action Card",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, players, null);

        switch (play) 
				{
            case JOptionPane.YES_OPTION:
                return p[0];
            case JOptionPane.NO_OPTION:
                return p[1];
            default:
                return -1;
        }
    }

    /**
     * asks the player to choose between cards.
     *
     * @param sc is the ArrayList of salaryCards
     * @param cc is the ArrayList of careerCards
		 * @param type specified if the prompt is for college career choice or for job search
     * @return the player choice of which salaryCard and careerCard he would like
     */
    public int chooseBetweenCards(ArrayList<SalaryCard> sc,
            ArrayList<CareerCard> cc, int type) 
		{

        String title, textA, textB;
        Object[] buttons = {"", ""};
        if (type == 1) 
				{
            title = "College Career Choice";
            textA = "Pick between the sets. Press X if you are ready to choose!";
            textB = "Which set will you choose?";
            buttons[0] = "Set A";
            buttons[1] = "Set B";
        } 
				else 
				{
            title = "Job Search";
            textA = "Will you change careers? Press X if you are ready to choose!";
            textB = "Will you change your cards?";
            buttons[0] = "Retain Cards";
            buttons[1] = "Change Cards";
        }

        JDialog card = new JDialog((Frame) null, title, Dialog.ModalityType.DOCUMENT_MODAL);

        ChooseBetwCardsPanel bg = new ChooseBetwCardsPanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel q = new JLabel(textA, SwingConstants.CENTER);
        q.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        q.setBounds(5, 20, 800, 20);
        bg.add(q);

        ImageIcon imageA;
        JLabel carA = new JLabel(cc.get(0).getCareer(), SwingConstants.CENTER);
        carA.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));

        switch (cc.get(0).getCareer()) 
				{
            case "Computer Consultant":
                imageA = new ImageIcon("images/computerConsultant.png");
                carA.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 7));
                break;
            case "Athlete":
                imageA = new ImageIcon("images/athlete.png");
                break;
            case "Doctor":
                imageA = new ImageIcon("images/doctor.png");
                break;
            case "Accountant":
                imageA = new ImageIcon("images/accountant.png");
                break;
            case "Lawyer":
                imageA = new ImageIcon("images/lawyer.png");
                break;
            case "Racecar Driver":
                imageA = new ImageIcon("images/racecarDriver.png");
                break;
            default:
                imageA = new ImageIcon("images/server.png");
                break;
        }

        carA.setBounds(30, 70, 156, 20);
        bg.add(carA);

        JLabel iconA = new JLabel();
        iconA.setIcon(imageA);
        iconA.setBounds(76, 105, 64, 64);
        bg.add(iconA);

        JLabel payAA = new JLabel("Max # of Pay Raise", SwingConstants.CENTER);
        payAA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 7));
        payAA.setBounds(30, 175, 156, 20);
        bg.add(payAA);

        JLabel payAB = new JLabel(Integer.toString(cc.get(0).getPayRaiseLimit()), SwingConstants.CENTER);
        payAB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        payAB.setBounds(30, 190, 156, 20);
        bg.add(payAB);

        JLabel degAA = new JLabel("Requires a Degree?", SwingConstants.CENTER);
        degAA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 7));
        degAA.setBounds(30, 200, 156, 20);
        bg.add(degAA);

        String ynA;
        if (cc.get(0).getDegreeReq()) 
				{
            ynA = "Yes";
        } 
				else 
				{
            ynA = "No";
        }

        JLabel degAB = new JLabel(ynA, SwingConstants.CENTER);
        degAB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        degAB.setBounds(30, 212, 156, 20);
        bg.add(degAB);

        JLabel scA = new JLabel("Salary Card", SwingConstants.CENTER);
        scA.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        scA.setBounds(214, 72, 156, 20);
        bg.add(scA);

        JLabel salAA = new JLabel("Salary", SwingConstants.CENTER);
        salAA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        salAA.setBounds(214, 105, 156, 20);
        bg.add(salAA);

        JLabel salAB = new JLabel("$" + Integer.toString(sc.get(0).getSalary()), SwingConstants.CENTER);
        salAB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        salAB.setBounds(214, 120, 156, 20);
        bg.add(salAB);

        JLabel taxAA = new JLabel("Tax Due", SwingConstants.CENTER);
        taxAA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        taxAA.setBounds(214, 145, 156, 20);
        bg.add(taxAA);

        JLabel taxAB = new JLabel("$" + Integer.toString(sc.get(0).getTaxDue()), SwingConstants.CENTER);
        taxAB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        taxAB.setBounds(214, 160, 156, 20);
        bg.add(taxAB);

        JLabel prvAA = new JLabel("Pay Raise Value", SwingConstants.CENTER);
        prvAA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 8));
        prvAA.setBounds(214, 185, 156, 20);
        bg.add(prvAA);

        JLabel prvAB = new JLabel("$" + Integer.toString(sc.get(0).getPayRaiseVal()), SwingConstants.CENTER);
        prvAB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        prvAB.setBounds(214, 200, 156, 20);
        bg.add(prvAB);

        ImageIcon imageB;
        JLabel carB = new JLabel(cc.get(1).getCareer(), SwingConstants.CENTER);
        carB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 8));

        switch (cc.get(1).getCareer()) 
				{
            case "Computer Consultant":
                imageB = new ImageIcon("images/computerConsultant.png");
                carB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 7));
                break;
            case "Athlete":
                imageB = new ImageIcon("images/athlete.png");
                break;
            case "Doctor":
                imageB = new ImageIcon("images/doctor.png");
                break;
            case "Accountant":
                imageB = new ImageIcon("images/accountant.png");
                break;
            case "Lawyer":
                imageB = new ImageIcon("images/lawyer.png");
                break;
            case "Racecar Driver":
                imageB = new ImageIcon("images/racecarDriver.png");
                break;
            default:
                imageB = new ImageIcon("images/server.png");
                break;
        }

        carB.setBounds(430, 70, 156, 20);
        bg.add(carB);

        JLabel iconB = new JLabel();
        iconB.setIcon(imageB);
        iconB.setBounds(476, 105, 64, 64);
        bg.add(iconB);

        JLabel payBA = new JLabel("Max # of Pay Raise", SwingConstants.CENTER);
        payBA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 7));
        payBA.setBounds(430, 175, 156, 20);
        bg.add(payBA);

        JLabel payBB = new JLabel(Integer.toString(cc.get(1).getPayRaiseLimit()), SwingConstants.CENTER);
        payBB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        payBB.setBounds(430, 190, 156, 20);
        bg.add(payBB);

        JLabel degBA = new JLabel("Requires a Degree?", SwingConstants.CENTER);
        degBA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 7));
        degBA.setBounds(430, 200, 156, 20);
        bg.add(degBA);

        String ynB;
        if (cc.get(1).getDegreeReq()) 
				{
            ynB = "Yes";
        } 
				else 
				{
            ynB = "No";
        }

        JLabel degBB = new JLabel(ynB, SwingConstants.CENTER);
        degBB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        degBB.setBounds(430, 212, 156, 20);
        bg.add(degBB);

        JLabel scB = new JLabel("Salary Card", SwingConstants.CENTER);
        scB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        scB.setBounds(614, 72, 156, 20);
        bg.add(scB);

        JLabel salBA = new JLabel("Salary", SwingConstants.CENTER);
        salBA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        salBA.setBounds(614, 105, 156, 20);
        bg.add(salBA);

        JLabel salBB = new JLabel("$" + Integer.toString(sc.get(1).getSalary()), SwingConstants.CENTER);
        salBB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        salBB.setBounds(614, 120, 156, 20);
        bg.add(salBB);

        JLabel taxBA = new JLabel("Tax Due", SwingConstants.CENTER);
        taxBA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        taxBA.setBounds(614, 145, 156, 20);
        bg.add(taxBA);

        JLabel taxBB = new JLabel("$" + Integer.toString(sc.get(1).getTaxDue()), SwingConstants.CENTER);
        taxBB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        taxBB.setBounds(614, 160, 156, 20);
        bg.add(taxBB);

        JLabel prvBA = new JLabel("Pay Raise Value", SwingConstants.CENTER);
        prvBA.setFont(fontStyle.deriveFont (Font.BOLD, (float) 8));
        prvBA.setBounds(614, 185, 156, 20);
        bg.add(prvBA);

        JLabel prvBB = new JLabel("$" + Integer.toString(sc.get(1).getPayRaiseVal()), SwingConstants.CENTER);
        prvBB.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        prvBB.setBounds(614, 200, 156, 20);
        bg.add(prvBB);

        card.setSize(800, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);

        int val = JOptionPane.showOptionDialog(this, textB, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, buttons, null);
        return val + 1;
    }

    /**
     * asks the player to choose a house to buy from the available houses.
     *
     * @param hcs is the ArrayList of houses that the player can choose from
     * @return index of the house card that the player chose; -1 if the player closes the prompt
     */
    public int chooseHouse(ArrayList<HouseCard> hcs)
		{
        Object[] choices = {"", "", "", "", ""};
            
        int x = 22;
        int distancex = 200;
        int width = 156;
        
        for (int j = 0; j < hcs.size(); j++) 
				{
            choices[j] = hcs.get(j).getType();
        }

        JDialog card = new JDialog((Frame) null, "Buy a House",
                Dialog.ModalityType.DOCUMENT_MODAL);

        BuyHousePanel bg = new BuyHousePanel();
        bg.setLayout(null);
        card.add(bg);

        JLabel q = new JLabel("Which house would you want to buy? "
                + "Press X if you are ready to choose!", SwingConstants.CENTER);
        q.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 12));
        q.setBounds(0, 20, 1000, 20);
        bg.add(q);

        JLabel title1 = new JLabel ("House Card", SwingConstants.CENTER);
        title1.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        title1.setBounds(x, 72, width, 20);
        bg.add(title1);

        String type1 = hcs.get(0).getType ();
        String [] types1 = {"", ""};
				String [] typesSplit1 = type1.split(" ", 2);
				if (typesSplit1.length == 1)
					types1 [0] = typesSplit1[0];
				else
					types1 = typesSplit1;

        JLabel type1A = new JLabel(types1[0], SwingConstants.CENTER);
        type1A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type1A.setBounds(x, 110, width, 20);
        bg.add(type1A);

        JLabel type1B = new JLabel(types1[1], SwingConstants.CENTER);
        type1B.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type1B.setBounds(x, 125, width, 20);
        bg.add(type1B);

        JLabel sell1A = new JLabel("Selling Price", SwingConstants.CENTER);
        sell1A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        sell1A.setBounds(x, 150, width, 20);
        bg.add(sell1A);

        JLabel sell1B = new JLabel("$" + Integer.toString(hcs.get(0).getSellingPrice()), SwingConstants.CENTER);
        sell1B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        sell1B.setBounds(x, 165, width, 20);
        bg.add(sell1B);

        JLabel buy1A = new JLabel("Buying Price", SwingConstants.CENTER);
        buy1A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        buy1A.setBounds(x, 190, width, 20);
        bg.add(buy1A);

        JLabel buy1B = new JLabel("$" + Integer.toString(hcs.get(0).getBuyingPrice()), SwingConstants.CENTER);
        buy1B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        buy1B.setBounds(x, 205, width, 20);
        bg.add(buy1B);
        
        JLabel title2 = new JLabel("House Card", SwingConstants.CENTER);
        title2.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        title2.setBounds(x+distancex, 72, width, 20);
        bg.add(title2);

        String type2 = hcs.get (1).getType ();
        String [] types2 = {"", ""};
				String [] typesSplit2 = type2.split(" ", 2);
				
				if (typesSplit2.length == 1)
					types2 [0] = typesSplit2 [0];
				else
					types2 = typesSplit2;

        JLabel type2A = new JLabel(types2[0], SwingConstants.CENTER);
        type2A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type2A.setBounds(x+distancex, 110, width, 20);
        bg.add(type2A);

        JLabel type2B = new JLabel(types2[1], SwingConstants.CENTER);
        type2B.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type2B.setBounds(x+distancex, 125, width, 20);
        bg.add(type2B);

        JLabel sell2A = new JLabel("Selling Price", SwingConstants.CENTER);
        sell2A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        sell2A.setBounds(x+distancex, 150, width, 20);
        bg.add(sell2A);

        JLabel sell2B = new JLabel("$"
                + Integer.toString(hcs.get(1).getSellingPrice()),
                SwingConstants.CENTER);
        sell2B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        sell2B.setBounds(x+distancex, 165, width, 20);
        bg.add(sell2B);

        JLabel buy2A = new JLabel("Buying Price", SwingConstants.CENTER);
        buy2A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        buy2A.setBounds(x+distancex, 190, width, 20);
        bg.add(buy2A);

        JLabel buy2B = new JLabel("$" + Integer.toString(hcs.get(1).getBuyingPrice()), SwingConstants.CENTER);
        buy2B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        buy2B.setBounds(x+distancex, 205, width, 20);
        bg.add(buy2B);
        
        JLabel title3 = new JLabel("House Card", SwingConstants.CENTER);
        title3.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        title3.setBounds(x+distancex*2, 72, width, 20);
        bg.add(title3);

        String type3 = hcs.get(2).getType();
        String [] types3 = {"", ""};
				String [] typesSplit3 = type3.split(" ", 2);
				if (typesSplit3.length == 1)
					types3 [0] = typesSplit3[0];
				else
					types3 = typesSplit3;

        JLabel type3A = new JLabel(types3[0], SwingConstants.CENTER);
        type3A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type3A.setBounds(x+distancex*2, 110, width, 20);
        bg.add(type3A);

        JLabel type3B = new JLabel(types3[1], SwingConstants.CENTER);
        type3B.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type3B.setBounds(x+distancex*2, 125, width, 20);
        bg.add(type3B);

        JLabel sell3A = new JLabel("Selling Price", SwingConstants.CENTER);
        sell3A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        sell3A.setBounds(x+distancex*2, 150, width, 20);
        bg.add(sell3A);

        JLabel sell3B = new JLabel("$"
                + Integer.toString(hcs.get(2).getSellingPrice()),
                SwingConstants.CENTER);
        sell3B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        sell3B.setBounds(x+distancex*2, 165, width, 20);
        bg.add(sell3B);

        JLabel buy3A = new JLabel("Buying Price", SwingConstants.CENTER);
        buy3A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        buy3A.setBounds(x+distancex*2, 190, width, 20);
        bg.add(buy3A);

        JLabel buy3B = new JLabel("$" + Integer.toString(hcs.get(2).getBuyingPrice()), SwingConstants.CENTER);
        buy3B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        buy3B.setBounds(x+distancex*2, 205, width, 20);
        bg.add(buy3B);
        
        JLabel title4 = new JLabel("House Card", SwingConstants.CENTER);
        title4.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        title4.setBounds(x+distancex*3, 72, width, 20);
        bg.add(title4);

        String type4 = hcs.get(3).getType();
        String [] types4 = {"", ""};
				String [] typesSplit4 = type4.split(" ", 2);
				if (typesSplit4.length == 1)
					types4 [0] = typesSplit4 [0];
				else
					types4 = typesSplit4;

        JLabel type4A = new JLabel(types4[0], SwingConstants.CENTER);
        type4A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type4A.setBounds(x+distancex*3, 110, width, 20);
        bg.add(type4A);

        JLabel type4B = new JLabel(types4[1], SwingConstants.CENTER);
        type4B.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type4B.setBounds(x+distancex*3, 125, width, 20);
        bg.add(type4B);

        JLabel sell4A = new JLabel("Selling Price", SwingConstants.CENTER);
        sell4A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        sell4A.setBounds(x+distancex*3, 150, width, 20);
        bg.add(sell4A);

        JLabel sell4B = new JLabel("$"
                + Integer.toString(hcs.get(3).getSellingPrice()),
                SwingConstants.CENTER);
        sell4B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        sell4B.setBounds(x+distancex*3, 165, width, 20);
        bg.add(sell4B);

        JLabel buy4A = new JLabel("Buying Price", SwingConstants.CENTER);
        buy4A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        buy4A.setBounds(x+distancex*3, 190, width, 20);
        bg.add(buy4A);

        JLabel buy4B = new JLabel("$" + Integer.toString(hcs.get(3).getBuyingPrice()), SwingConstants.CENTER);
        buy4B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        buy4B.setBounds(x+distancex*3, 205, width, 20);
        bg.add(buy4B);
        
        JLabel title5 = new JLabel("House Card", SwingConstants.CENTER);
        title5.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 10));
        title5.setBounds(x+distancex*4, 72, width, 20);
        bg.add(title5);

        String type5 = hcs.get(4).getType();
        String [] types5 = {"", ""};
				String [] typesSplit5 = type5.split(" ", 2);
				if (typesSplit5.length == 1)
					types5 [0] = typesSplit5 [0];
				else
					types5 = typesSplit5;

        JLabel type5A = new JLabel(types5[0], SwingConstants.CENTER);
        type5A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type5A.setBounds(x+distancex*4, 110, width, 20);
        bg.add(type5A);

        JLabel type5B = new JLabel(types5[1], SwingConstants.CENTER);
        type5B.setFont(fontStyle.deriveFont (Font.BOLD, (float) 12));
        type5B.setBounds(x+distancex*4, 125, width, 20);
        bg.add(type5B);

        JLabel sell5A = new JLabel("Selling Price", SwingConstants.CENTER);
        sell5A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        sell5A.setBounds(x+distancex*4, 150, width, 20);
        bg.add(sell5A);

        JLabel sell5B = new JLabel("$"
                + Integer.toString(hcs.get(4).getSellingPrice()),
                SwingConstants.CENTER);
        sell5B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        sell5B.setBounds(x+distancex*4, 165, width, 20);
        bg.add(sell5B);

        JLabel buy5A = new JLabel("Buying Price", SwingConstants.CENTER);
        buy5A.setFont(fontStyle.deriveFont (Font.BOLD, (float) 10));
        buy5A.setBounds(x+distancex*4, 190, width, 20);
        bg.add(buy5A);

        JLabel buy5B = new JLabel("$" + Integer.toString(hcs.get(4).getBuyingPrice()), SwingConstants.CENTER);
        buy5B.setFont(fontStyle.deriveFont (Font.PLAIN, (float) 14));
        buy5B.setBounds(x+distancex*4, 205, width, 20);
        bg.add(buy5B);

        card.setSize(1000, 300);
        card.setLocationRelativeTo(null);
        card.setVisible(true);

        Object o = JOptionPane.showInputDialog(this, "Which house will you buy?", "Buy a House", JOptionPane.PLAIN_MESSAGE, null, choices, null);

        if (o == choices[0]) 
				{
            return 0;
        } 
				else if (o == choices[1]) 
				{
            return 1;
        } 
				else if (o == choices[2]) 
				{
            return 2;
        } 
				else if (o == choices[3]) 
				{
            return 3;
        } 
				else if (o == choices[4]) 
				{
            return 4;
        } 
				else 
				{
            return -1;
        }
    }

    /**
     * shows that the player graduated
     */
    public void graduatePrompt() 
		{
        JOptionPane.showMessageDialog(this, "Congratulations, you have " + "graduated!", "Graduation Day", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * shows that if the player got married or is alreaady married.
     *
     * @param num -1 if the player is already married and cannot get married anymore; otherwise, it is a positive integer that corresponds to the rolled number
     */
    public void getMarriedPrompt(int num) 
		{
        if (num == -1) 
				{
            JOptionPane.showMessageDialog(this, "You are already married.",
                    "Wedding Ceremony?", JOptionPane.PLAIN_MESSAGE);
        } 
				else 
				{
            JOptionPane.showMessageDialog(this, "Congratulations, you got married!\n"
                    + "Press OK to generate a number for your wedding gift.",
                    "Wedding Ceremony", JOptionPane.PLAIN_MESSAGE);
            String amt, oe;
            if (num % 2 == 0) 
						{
                amt = "$10000";
                oe = "even";
            } 
						else 
						{
                amt = "$5000";
                oe = "odd";
            }
            
            JOptionPane.showMessageDialog(this, "You rolled an " + oe + " number ("
                    + Integer.toString(num) + "). Collect " + amt + " from each player",
                    "Wedding Ceremony", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * shows the player the kids he would have and how much he got from the players
     *
     * @param num is the number of kids that will get added to the player; -1 if the player is not married and cannot have kids
     */
    public void haveKidsPrompt (int num) 
		{
        if (num == -1) 
				{
            JOptionPane.showMessageDialog (this, "You are not married. "
                    + "You cannot have children.",
                    "Have Kids?", JOptionPane.PLAIN_MESSAGE);
        } 
				else 
				{
            String kids;
            if (num == 1) 
						{
                kids = "a child";
            } 
						else 
						{
                kids = "twins";
            }

            JOptionPane.showMessageDialog(this, "Congratulations, you had " + kids + "!\n"
                    + "You collect $5000 gift for each child from each player!",
                    "Have Kids?", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * shows the milestones of the player
     *
     * @param p is the player that is currently in turn
     */
    public void showMilestone(Player p) 
		{
        String text = "";
        boolean check = false;
        if (p.isWithDegree()) 
				{
            text = "You have graduated!\n";
            check = true;
        }

        if (p.isMarried()) 
				{
            check = true;
            text = text + "You are married.\n";
        }

        if (p.getNoOfChildren() != 0) 
				{
            text = text + "You have " + p.getNoOfChildren() + " kid/s.\n";
            check = true;
        }

        if (!check) 
				{
            text = "You have no milestones yet!";
        }

        JOptionPane.showMessageDialog(this, text, "Milestones", JOptionPane.INFORMATION_MESSAGE);
    }

		/**
     * shows the computation of the final cash of the player
     *
     * @param order is the ArrayList of players that is already done with the game
		 * @param number is the amount of loan of the player
     */
    public void retirePrompt (ArrayList<Player> order, int number) 
		{
        String cash, child;
        int noofchild = order.get(order.size () - 1).getNoOfChildren();
        HouseCard h = order.get(order.size () - 1).getHouseCard();
        switch(order.size ())
        {
            case 1:
                cash = "$100000";
                break;
            case 2:
                cash = "$50000";
                break;
            default:
                cash = "$20000";
        }

        if (noofchild > 0)
            child = "You have " + Integer.toString(noofchild) 
                    + " kid/s. You get $" + Integer.toString(noofchild*10000) 
                    + " in total";
        else
            child = "You have no kids";
                  
        JOptionPane.showMessageDialog(this, "Congratulations, you have retired!\n"
                + "You have collected your " + cash + " retirement pay from the bank!\n"
                + child
                + ((h != null) ? ("\nYou sold your house for $" + Integer.toString(h.getSellingPrice())) : "") + ((order.get (order.size () - 1).getLoan () != 0) ? ("\nYou paid your loan of $" + Integer.toString(order.get (order.size () - 1).getLoan ())) : "") + "!\nYou retired with a total of $" + order.get (order.size () - 1).getCash () + ".",
                "Retirement", JOptionPane.PLAIN_MESSAGE);
    }

		/**
     * shows the winner of the game
     *
     * @param p is the player that is the winner
     */
    public void winPrompt(Player p) 
		{
        String text; 
        text = "Player " + p.getName() + " won with $" + Integer.toString(p.getCash()) + "!";
        JOptionPane.showMessageDialog(this, text, "Winner", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * sets the name shown in the game to indicated name
     *
     * @param s is the name that would be shown in the view
     */
    public void setNameTxt(String s) 
		{
        nameTxt.setText(s);
    }

    /**
     * sets the cash shown in the game to the indicated amount
     *
     * @param s is the amount that would be shown in the view
     */
    public void setCashTxt(String s) 
		{
        cashTxt.setText(s);
    }

    /**
     * sets the loan shown in the game to the indicated amount
     *
     * @param s is the amount that would be shown in the view
     */
    public void setLoanTxt(String s) 
		{
        loanTxt.setText(s);
    }

    /**
     * sets the path shown in the game to the indicated String
     *
     * @param s is the name of the path that would be shown in the view
     */
    public void setPathTxt(String s) 
		{
        pathTxt.setText(s);
    }

    /**
     * enables/disables the payLoan button depending on the indicated boolean
     *
     * @param enabled determines if the button will be disabled/enabled
     */
    public void setPayLoanEnabled (boolean enabled) 
		{
        payloan.setEnabled(enabled);
    }

    /**
     * enables/disables the payLoan button depending on the indicated boolean
     *
     * @param enabled determines if the button will be disabled/enabled
     */
    public void setGetLoanEnabled (boolean enabled) 
		{
        getloan.setEnabled(enabled);
    }

    /**
     * enables/disables the house button depending on the indicated boolean
     *
     * @param enabled determines if the button will be disabled/enabled
     */
    public void setHouseCardEnabled (boolean enabled) 
		{
        house.setEnabled(enabled);
    }

    /**
     * enables/disables the salaryCard button depending on the indicated boolean
     *
     * @param enabled determines if the button will be disabled/enabled
     */
    public void setSalaryCardEnabled (boolean enabled) 
		{
        salary.setEnabled(enabled);
    }

		public void setRollDiceEnabled (boolean enabled)
		{
				rolldice.setEnabled (enabled);
		}

    /**
     * enables/disables the careerCard button depending on the indicated boolean
     *
     * @param enabled determines if the button will be disabled/enabled
     */
    public void setCareerCardEnabled (boolean enabled) 
		{
        career.setEnabled(enabled);
    }
    
    public void setPeg3Enabled (boolean visible)
		{
        peg3.setVisible(visible);
    }

    /**
     * shows the error that is passed to it
     *
     * @param error is the String that indicates the error that happened
     */
    public void outputError(String error) 
		{
        JOptionPane.showMessageDialog(this, error, "ALERT", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * changes the panel shown in the frame based on the indicated name of the panel
     *
     * @param s is the name of the panel that would be shown.
     */
    public void changeCard (String s) 
		{
        cardLayout.show(panel, s);
    }

    /**
     * sets the ActionListener of the buttons in the menu to the indicted Actionlistener
     *
     * @param listener is the ActionListener to be assigned to the buttons
     */
    public void setMenuListener (ActionListener listener) 
		{
        play.addActionListener(listener);
        help.addActionListener(listener);
        exit.addActionListener(listener);
    }

    /**
     * sets the ActionListener of the buttons in the game to the indicted Actionlistener
     *
     * @param listener is the ActionListener to be assigned to the buttons
     */
    public void setGameListener (ActionListener listener) 
		{
        career.addActionListener(listener);
        house.addActionListener(listener);
        salary.addActionListener(listener);
        miles.addActionListener(listener);
        getloan.addActionListener(listener);
        payloan.addActionListener(listener);
        rolldice.addActionListener(listener);
    }
}