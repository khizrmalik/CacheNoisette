import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Squirrels
{
    Picture d;
    Picture c;
    Picture b;
    Picture a;

    public Squirrels(String r, int rotation)
    {
        if (r == "red")
        {
            c = new Picture("RedSquirrel1Nut.png", rotation);
            b = new Picture("RedSquirrel2.png", rotation);
            a = new Picture("RedSquirrel1.png", rotation);
        }

        if (r == "grey")
        {
            c = new Picture("GreySquirrel1Nut.png", rotation);
            b = new Picture("GreySquirrel2.png", rotation);
            a = new Picture("GreySquirrel1.png", rotation);
        }

        if (r == "brown")
        {
            c = new Picture("BrownSquirrel1Nut.png", rotation);
            b = new Picture("BrownSquirrel2.png", rotation);
            a = new Picture("BrownSquirrel1.png", rotation);
            d = new Picture("SquirrelFlower.png", rotation);
        }

        if (r == "black")
        {
            c = new Picture("BlackSquirrel1Nut.png", rotation);
            b = new Picture("BlackSquirrel2.png", rotation);
            a = new Picture("BlackSquirrel1.png", rotation);
            d = new Picture("SquirrelFlower.png", rotation);
        }
    }
}