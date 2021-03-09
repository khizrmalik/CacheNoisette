import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.BorderFactory;

public class CacheNoisette implements ActionListener
{
    private JFrame window;
    private JPanel mainPanel;
    private JPanel grid;
    private Menu Levels;
    private MenuBar bar;

    private Picture upPic = new Picture("BigArrow.png", 0);
    private JButton up = new JButton(upPic);

    private Picture rightPic = new Picture("Arrow.png", 90);
    private JButton right = new JButton(rightPic);

    private Picture leftPic = new Picture("Arrow.png", 270);
    private JButton left = new JButton(leftPic);

    private Picture downPic = new Picture("BigArrow.png", 180);
    private JButton down = new JButton(downPic);

    private Picture p = new Picture("Empty.png", 0);
    private JButton[] tiles = new JButton[16];
    private JButton temp = new JButton(p);

    private Picture h = new Picture("Hole.png", 0);
    private JButton hTemp = new JButton(h);

    private Picture f = new Picture("Flower.png", 0);

    private Picture hN = new Picture("HoleNut.png", 0);

    private MenuItem[] level = new MenuItem[3];


    private Picture greysqHN = new Picture("GreySquirrel1Nut.png", 0);
    private Picture greysqT = new Picture("GreySquirrel2.png", 0);

    Picture c;
    Picture b;
    Picture a;

    int sqchoice;
    int current;
    int currentsqg;
    int currentsqr;

    Squirrels r1;
    Squirrels r2;


    public CacheNoisette()
    {
        /* Initialising the window properties*/
        window = new JFrame();
        window.setTitle("Cache Noisette");
        window.setSize(680, 675);
        window.setBackground(Color.BLACK);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        up.addActionListener(this);
        right.addActionListener(this);
        left.addActionListener(this);
        down.addActionListener(this);

        /* Initialising the MenuBar properties*/
        Levels = new Menu("Levels");
        bar = new MenuBar();
        for (int z = 1; z <= 2; z++)
        {
            level[z] = new MenuItem("Level" + Integer.toString(z));
            Levels.add(level[z]);
            level[z].addActionListener(this);
        }

        bar.add(Levels);
        window.setMenuBar(bar);

        grid = new JPanel();
        grid.setLayout(new GridLayout(4, 4));
        grid.setBackground(Color.BLACK);

        for (int i = 0; i < 16; i++)
        {
            if (i == 2 || i == 4 || i == 9 || i == 15)
            {
                tiles[i] = new JButton(h);
                grid.add(tiles[i]);
                tiles[i].addActionListener(this);
            }

            else
            {
                tiles[i] = new JButton(p);
                grid.add(tiles[i]);
                tiles[i].addActionListener(this);
            }
            
        }

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        mainPanel.add("North", up);
        up.setBackground(Color.BLACK);
        mainPanel.add("East", right);
        right.setBackground(Color.BLACK);
        mainPanel.add("West", left);
        left.setBackground(Color.BLACK);
        mainPanel.add("South", down);
        down.setBackground(Color.BLACK);
        mainPanel.add("Center", grid);

        window.setContentPane(mainPanel);
        window.setVisible(true);        
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == level[1])
        {
            for (int i = 0; i < 16; i++)
            {
                if (i == 2 || i == 4 || i == 9 || i == 15)
                {
                    tiles[i].setIcon(h);
                }

                else
                {
                    tiles[i].setIcon(p);
                }
                
            }
            current = 1;
            System.out.println("Level 1");

            r1 = new Squirrels("red", 270);
            tiles[5].setIcon(r1.c);
            sqchoice = 5;
            tiles[6].setIcon(r1.b);
            tiles[9].setIcon(f);

            r2 = new Squirrels("grey", 0);
            tiles[10].setIcon(r2.c);
            tiles[14].setIcon(r2.b);
        }

        for (int z = 0; z < 16; z++)
        {
            if (e.getSource() == tiles[z])
            {
                sqchoice = z;
            }
        }

        if (e.getSource() == level[2])
        {
            System.out.println("Level 2");
        }

        if (e.getSource() == up)
        {
            System.out.println("up");
            for (int i = 0; i < 16; i++)
            {
                if (i == sqchoice)
                {
                    if (current == 1)
                    {
                        if (tiles[i-4].getIcon() != p && tiles[i-4].getIcon() != h)
                        {
                            System.out.println("not gonna happen");
                        }
                        else
                        {
                            if (sqchoice == currentsqr || sqchoice == 5)
                            {
                                if (tiles[i-4].getIcon() == h)
                                {
                                    tiles[i+1].setIcon(p);
                                    tiles[i].setIcon(hN);
                                    tiles[i-4].setIcon(r1.a);
                                    tiles[i-3].setIcon(r1.b);
                                    currentsqr = i-4;
                                }

                                else
                                {
                                    tiles[i+1].setIcon(p);
                                    tiles[i].setIcon(hN);
                                    tiles[i-4].setIcon(r1.a);
                                    tiles[i-3].setIcon(r1.b);
                                    currentsqr = i-4;
                                }
                            }


                            if (sqchoice == currentsqg || sqchoice == 10)
                            {
                                if (tiles[i-4].getIcon() == h)
                                {
                                    tiles[i+4].setIcon(p);
                                    tiles[i].setIcon(hN);
                                    tiles[i-4].setIcon(r2.a);
                                    tiles[i].setIcon(r2.b);
                                    currentsqg = i-4;
                                }
                                else
                                {
                                    tiles[i].setIcon(p);
                                    tiles[i-4].setIcon(r2.c);

                                    tiles[i+4].setIcon(p);
                                    tiles[i].setIcon(r2.b);
                                    currentsqg = i-4;
                                }
                            }
                            
                            else
                            {
                                return;
                            }
                        }
                    }
                }
            }
        }

        if (e.getSource() == right)
        {
            System.out.println("right");

            for (int i = 0; i < 16; i++)
            {
                if (i == sqchoice)
                {

                    if ((i+1) == 4 || (i+1) == 8 || (i+1) == 12)
                    {
                        System.out.println("Boundaries!");
                        return;
                    }

                    else 
                    {
                        if (sqchoice == currentsqr || sqchoice == 5)
                        {
                            if (tiles[i+2].getIcon() != p && tiles[i+2].getIcon() != h)
                            {
                                System.out.println("cannot go this way!");
                                return;
                            }
                            else
                            {
                                if (tiles[i+1].getIcon() == h)
                                    tiles[i+1].setIcon(r1.a);
                                if (i == 4)
                                {
                                    tiles[i].setIcon(hN);
                                    tiles[i+1].setIcon(r1.a);
                                }
                                
                                else    
                                {
                                    tiles[i+1].setIcon(r1.c);
                                    tiles[i].setIcon(p);
                                }
                                tiles[i+2].setIcon(r1.b);
                                currentsqr = i+1;
                            }
                        }
                        if (sqchoice == currentsqg || sqchoice == 10)
                        {
                            if (tiles[i+1].getIcon() != p && tiles[i+1].getIcon() != h)
                            {
                                System.out.println("cannot go this way!");
                                return;
                            }
                            else
                            {
                                tiles[i].setIcon(p);
                                tiles[i+1].setIcon(r2.c);
                                tiles[i+4].setIcon(p);
                                tiles[i+5].setIcon(r2.b);
                                currentsqg = i+1;
                            }
                        }
                        
                    }
                }
            }
        }

        if (e.getSource() == left)
        {
            System.out.println("left");
            for (int i = 0; i < 16; i++)
            {
                if (i == sqchoice)
                {
                    if (current == 1)
                    {
                        if (tiles[i-1].getIcon() != p && tiles[i-1].getIcon() != h)
                        {
                            System.out.println("not gonna happen");
                            return;
                        }

                        if ((i-1) == 3 || (i-1) == 7 || (i-1) == 11)
                        {
                            System.out.println("Boundaries!");
                            return;
                        }
                        else
                        {
                            if (sqchoice == currentsqr || sqchoice == 5)
                            {
                                tiles[i+1].setIcon(p);
                                tiles[i].setIcon(r1.b);
                                if (tiles[i-1].getIcon() == h)
                                    tiles[i-1].setIcon(r1.a);
                                else
                                    tiles[i-1].setIcon(r1.c);
                                currentsqr = i-1;
                            }

                            if (sqchoice == currentsqg || sqchoice == 10)
                            {
                                tiles[i].setIcon(p);
                                tiles[i-1].setIcon(r2.c);

                                tiles[i+4].setIcon(p);
                                tiles[i+3].setIcon(r2.b);
                                currentsqg = i-1;
                            }
                        }
                    }
                }
            }
        }

        if (e.getSource() == down)
        {
             System.out.println("down");
        }
    }
}