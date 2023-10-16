package stopWatch;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.Timer;

class stopwatch extends JFrame implements ActionListener {

	JFrame frame = new JFrame();
	// for timer label
	JLabel timeLabel = new JLabel();
	JLabel start_button;
	JLabel reset_button;

	static int elapsedTime = 0;
	static int seconds = 0;
	static int minutes = 0;
	static int miliseconds = 0;
	static int hours = 0;
	static int count = 0;
	boolean started = false;

	public stopwatch() {

		// timer label
		timeLabel = new JLabel("00:00:00:00");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(Color.BLACK);
		timeLabel.setBounds(10, 32, 306, 92);
		timeLabel.setFont(new Font("Verdana", Font.BOLD, 33));

		frame.getContentPane().add(timeLabel);

		// for showing previous output
		JLabel lastOutputLabel = new JLabel("New label");
		lastOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastOutputLabel.setForeground(Color.BLACK);
		lastOutputLabel.setBounds(41, 135, 238, 165);
		lastOutputLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lastOutputLabel.setVisible(false);
		frame.getContentPane().add(lastOutputLabel);
		// start button
		start_button = new JLabel("New label");
		start_button.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		start_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				start_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Start_button_2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				start_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Start_button.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getSource() == start_button) {

					if (started == false) {

						if (count == 1) {
							lastOutputLabel.setVisible(true);
						}

						started = true;

						Thread t = new Thread() {

							public void run() {

								while (started == true) {

									try {

										sleep(10);

										miliseconds++;
										if (miliseconds == 100) {
											seconds++;
											miliseconds = 0;
										}
										if (seconds == 60) {
											minutes++;
											seconds = 0;
										}
										if (minutes == 60) {
											hours++;
											minutes = 0;
										}

										String miliseconds_string = String.format("%02d", miliseconds);
										String seconds_string = String.format("%02d", seconds);
										String minutes_string = String.format("%02d", minutes);
										String hours_string = String.format("%02d", hours);

										timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string
												+ ":" + miliseconds_string);

										start_button.setIcon(new ImageIcon(
												stopwatch.class.getResource("/stopWatch/Stop_button.png")));
									} catch (Exception ex) {
										System.out.println(ex);
									}

								}
							}

						};

						t.start();

					}

					else {
						started = false;
						start_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Start_button.png")));

						//for last output
						count = 1;
						lastOutputLabel.setVisible(false);
						String miliseconds_string = String.format("%02d", miliseconds);
						String seconds_string = String.format("%02d", seconds);
						String minutes_string = String.format("%02d", minutes);
						String hours_string = String.format("%02d", hours);
						lastOutputLabel.setText(
								hours_string + ":" + minutes_string + ":" + seconds_string + ":" + miliseconds_string);

					}
				}

			}
		});
		start_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		start_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Start_button.png")));
		start_button.setBounds(41, 343, 96, 46);

		frame.getContentPane().add(start_button);

		// reset button
		reset_button = new JLabel("New label");
		reset_button.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		reset_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reset_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Reset_button_2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				reset_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Reset_button.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == reset_button) {
					started = false;
					start_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Start_button.png")));
					elapsedTime = 0;
					seconds = 0;
					minutes = 0;

					miliseconds = 0;

					String miliseconds_string = String.format("%02d", miliseconds);
					String seconds_string = String.format("%02d", seconds);
					String minutes_string = String.format("%02d", minutes);
					String hours_string = String.format("%02d", hours);
					timeLabel.setText("00:00:00:00");
					
					//for reset previous output
					lastOutputLabel.setText("00:00:00:00");
					lastOutputLabel.setVisible(false);
					count=0;
				}
			}
		});
		reset_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reset_button.setIcon(new ImageIcon(stopwatch.class.getResource("/stopWatch/Reset_button.png")));
		reset_button.setBounds(185, 343, 105, 46);
		frame.getContentPane().add(reset_button);

		// background image
		ImageIcon icon2 = new ImageIcon(getClass().getResource("img4.jpg"));
		JLabel backgroundImg1 = new JLabel(icon2);
		backgroundImg1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		backgroundImg1.setBounds(0, 0, 326, 480);
		frame.getContentPane().add(backgroundImg1);
		frame.getContentPane().setLayout(null);
		// frame
		frame.setTitle("StopWatch");
		frame.setResizable(false);
		frame.setBounds(800, 300, 342, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		// add icon image
		ImageIcon icon = new ImageIcon(getClass().getResource("stopwatch.png"));
		frame.setIconImage(icon.getImage());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
