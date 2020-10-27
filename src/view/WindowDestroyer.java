package view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
public class WindowDestroyer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {	
       System.exit(0);
       	}
   

}
