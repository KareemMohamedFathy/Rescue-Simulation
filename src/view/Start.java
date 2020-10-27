package view;

import sun.audio.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
public class Start extends JFrame{
	private JButton b;
	private JButton c;

	public JButton getB() {
		return b;
	}

	public void setB(JButton b) {
		this.b = b;
	}
	

	public Start() {
this.setLayout(new GridBagLayout());
GridBagConstraints c1=new GridBagConstraints();
c1.gridx=0;
c1.gridy=0;
 b=new JButton("Start Game");
 setExtendedState(JFrame.MAXIMIZED_BOTH);
  b.setPreferredSize(new Dimension(300, 100));
  b.setBackground(Color.RED);
  c=new JButton("End Game");
  c.setPreferredSize(new Dimension(300, 100));
add(b,c1);
  c1.gridy++;
  c1.weighty=2;
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
add(c,c1);
c1.gridy++;
this.getContentPane().setBackground(Color.gray);
	}

	public JButton getC() {
		return c;
	}

	public void setC(JButton c) {
		this.c = c;
	}

	

}
