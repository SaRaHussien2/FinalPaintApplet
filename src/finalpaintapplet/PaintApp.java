/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Applet.java to edit this template
 */
package finalpaintapplet;

import java.applet.Applet;


import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PaintApp extends Applet {
	// red color =2
	// green color=4
	// blue color =3
	private final int MAX_X = 600;
	private final int MAX_Y = 350;
	Checkbox fill = null;
	int flag = 0;
	int freeFlag = 0;
	int imgFlag = 0;
	Graphics2D g2 = null;
	int circleRadius = 10;
	Dimension appletSize = null;
	BufferedImage bufferedImage;
	Button freeHand = null;
	Button eraser = null;
	Button save = null;
	Button clearAll = null;
	Choice c = null;
	Choice shapeColors = null;
	int width = 0;
	int height = 0;
	int currentX = 0;
	int currentY = 0;
	Point startPoint, endPoint;
	Color c1 = null;
	Image i;
	Button openImg = null;

	List<int[]> shapesList = new ArrayList<>();
	private final GraphicsConfiguration gConfig = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice().getDefaultConfiguration();

	public void init() {
		int xofE = this.getX();
		int yofE = this.getY();
		fill = new Checkbox("Filled", false);
		save = new Button("Save");
		eraser = new Button("Eraser");
		freeHand = new Button("Free");
		clearAll = new Button("Clear");
		openImg = new Button("open");
		c = new Choice();
		shapeColors = new Choice();
		c.add("Line");
		c.add("Oval");
		c.add("Rect");
		shapeColors.add("Black");
		shapeColors.add("Red");
		shapeColors.add("Blue");
		shapeColors.add("Green");
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (flag == 0) {
					shapeColors.setEnabled(false);
					c.setEnabled(false);
					fill.setEnabled(false);
					freeHand.setEnabled(false);
					clearAll.setEnabled(false);
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image image = toolkit.getImage(getCodeBase().toString().substring(5) + "eraser.png");
					Cursor eraserCursor = toolkit.createCustomCursor(image, new Point(xofE, yofE), "eraser");
					setCursor(eraserCursor);
					flag = 1;
				} else {
					shapeColors.setEnabled(true);
					c.setEnabled(true);
					fill.setEnabled(true);
					freeHand.setEnabled(true);
					clearAll.setEnabled(true);
					Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
					setCursor(normalCursor);
					flag = 0;
				}
			}
		});
		
		openImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = JOptionPane.showInputDialog(null, "Enter filename: ", "saving a file",
							JOptionPane.QUESTION_MESSAGE);
					URL n = new URL(getCodeBase(), name);
					i = ImageIO.read(n.openStream());
					imgFlag = 1;
				} catch (MalformedURLException e1) {
					//e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Enter filename: ", "saving a file",
						JOptionPane.QUESTION_MESSAGE);
				storeImage(name);
			}
		});
		clearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapesList.clear();
				repaint();

			}
		});
		freeHand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (freeFlag == 0) {
					shapeColors.setEnabled(false);
					c.setEnabled(false);
					fill.setEnabled(false);
					eraser.setEnabled(false);
					freeFlag = 1;
				} else {
					shapeColors.setEnabled(true);
					c.setEnabled(true);
					fill.setEnabled(true);
					eraser.setEnabled(true);
					freeFlag = 0;
				}
			}
		});
		add(freeHand);
		add(shapeColors);
		add(c);
		add(eraser);
		add(clearAll);
		add(openImg);
		add(save);
		add(fill);

		Color backGround = new Color(255, 255, 255);
		setBackground(backGround);
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (c.getSelectedItem() == "Line") {
					shapesList.add(new int[] { startPoint.x, startPoint.y, endPoint.x, endPoint.y,
							shapeColors.getSelectedIndex(), -1, -1 });
					repaint();
				}
				if (c.getSelectedItem() == "Oval") {
					if (fill.getState() == true) {
						shapesList.add(new int[] { startPoint.x, startPoint.y, width, height,
								shapeColors.getSelectedIndex(), 1, -2 });
					}
					if (fill.getState() == false) {
						shapesList.add(new int[] { startPoint.x, startPoint.y, width, height,
								shapeColors.getSelectedIndex(), 0, -2 });
					}
					repaint();
				}
				if (c.getSelectedItem() == "Rect") {
					if (fill.getState() == true) {
						shapesList.add(new int[] { startPoint.x, startPoint.y, width, height,
								shapeColors.getSelectedIndex(), 1, -3 });
					}
					if (fill.getState() == false) {
						shapesList.add(new int[] { startPoint.x, startPoint.y, width, height,
								shapeColors.getSelectedIndex(), 0, -3 });
					}
					repaint();
				}

			}

			public void mousePressed(MouseEvent e) {
				width = 0;
				height = 0;
				startPoint = e.getPoint();
				endPoint = e.getPoint();

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if (imgFlag == 1) {
					repaint();
					imgFlag = 0;
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (c.getSelectedItem() == "Line") {
					endPoint = e.getPoint();
					repaint();
				}
				if (flag == 1) {
					startPoint = e.getPoint();
					shapesList.add(new int[] { startPoint.x, startPoint.y, 10, 10, 4, -1, -4 });

					repaint();
				}
				if (freeFlag == 1) {
					endPoint = e.getPoint();
					shapesList.add(new int[] { startPoint.x, startPoint.y, endPoint.x, endPoint.y,
							shapeColors.getSelectedIndex(), -1, -5 });
					startPoint = endPoint;
					repaint();
				}
				if (c.getSelectedItem() == "Oval") {
					if (e.getX() > startPoint.x && e.getY() > startPoint.y) {
						width += 2;
						height += 2;
						currentX = e.getX();
						currentY = e.getY();
					} else if (e.getX() < currentX && e.getY() < currentY) {
						width -= 4;
						height -= 4;
					}
					repaint();
				}
				if (c.getSelectedItem() == "Rect") {
					if (e.getX() > startPoint.x && e.getY() > startPoint.y) {
						width += 4;
						height += 2;
						currentX = e.getX();
						currentY = e.getY();
					} else if (e.getX() < currentX && e.getY() < currentY) {
						width -= 4;
						height -= 2;
					}
					repaint();
				}
			}
		});

	}

	public void start() {
		setSize(MAX_X, MAX_Y);
		bufferedImage = create(MAX_X, MAX_Y, true);

	}

	private BufferedImage create(final int width, final int height, final boolean alpha) {
		BufferedImage buffer = gConfig.createCompatibleImage(width, height,
				alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
		return buffer;
	}

	public void paint(Graphics g) {

		openImgFromFile(g);
		colorswitch(shapeColors.getSelectedIndex(), g);
		if (!shapesList.isEmpty()) {
			if (c.getSelectedItem() == "Line") {
				g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			}
			if (c.getSelectedItem() == "Oval") {
				g.drawOval(startPoint.x, startPoint.y, width, height);
			}
			if (c.getSelectedItem() == "Rect") {
				g.drawRect(startPoint.x, startPoint.y, width, height);
			}
			drawInFile(g);
		}

	}

	public void storeImage(String nameOfFile) {
		BufferedImage image = create(MAX_X, MAX_Y, true);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawInFile(g);
		g.dispose();
		try {
                
			ImageIO.write(image, "png", new File("F:\\ITI\\img.png"));
		} catch (IOException e) {
		}
	}

	void drawInFile(Graphics g) {

		for (int[] s : shapesList) {
			if (s[6] == -1) {
				colorswitch(s[4], g);
				g.drawLine(s[0], s[1], s[2], s[3]);
			} else if (s[6] == -2) {
				colorswitch(s[4], g);
				g.drawOval(s[0], s[1], s[2], s[3]);
				if (s[5] == 1) {
					g.fillOval(s[0], s[1], s[2], s[3]);
				}

			} else if (s[6] == -3) {
				colorswitch(s[4], g);
				g.drawRect(s[0], s[1], s[2], s[3]);
				if (s[5] == 1) {
					g.fillRect(s[0], s[1], s[2], s[3]);
				}
			} else if (s[6] == -4) {
				colorswitch(s[4], g);
				g.fillRect(s[0], s[1], s[2], s[3]);
			} else if (s[6] == -5) {
				colorswitch(s[4], g);
				g.drawLine(s[0], s[1], s[2], s[3]);
			}
		}
	}

	void openImgFromFile(Graphics g) {

		g.drawImage(i, 0, 0, null);

	}

	void colorswitch(int num, Graphics g) {
		switch (num) {
		case 1:
			c1 = new Color(255, 0, 0);
			g.setColor(c1);
			break;
		case 2:
			c1 = new Color(0, 0, 255);
			g.setColor(c1);
			break;
		case 3:
			c1 = new Color(0, 255, 0);
			g.setColor(c1);
			break;
		case 4:
			c1 = new Color(255, 255, 255);
			g.setColor(c1);
			break;
		default:
			c1 = new Color(0, 0, 0);
			g.setColor(c1);
			break;

		}
	}
}
