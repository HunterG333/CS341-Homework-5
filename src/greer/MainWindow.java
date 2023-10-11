package greer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainWindow {

	private JFrame frame;
	Queue myQueue = new Queue();
	double mean;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setBounds(176, 39, 85, 21);
		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openFile();

			}

			public void openFile() {

				File inputFile;
				Scanner fileInputScan = null;

				try {

					// use JFileChooser to select a file
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						inputFile = jfc.getSelectedFile();

						// open the code file
						fileInputScan = new Scanner(inputFile);

						// do work with file
						getData(fileInputScan);
						getMean();
						getSTD();

					}

				} catch (FileNotFoundException fe) {
					System.out.println("Error - This file could not be found.");
				} finally {
					if (fileInputScan != null) {
						fileInputScan.close();
					}
				}

			}
		});
	}

	/**
	 * Reads the data from the selected file
	 * @param file a file containing real whole numbers
	 */
	public void getData(Scanner file) {

		while (file.hasNext()) {

			try {
				int next = file.nextInt();
				myQueue.enqueue(next);

			} catch (Exception e) {
				file.next();
			}
		}

		System.out.println(myQueue);

	}

	/*
	 * A method which gets the mean from the data provided from the file
	 */
	private void getMean() {
		// TODO Auto-generated method stub

		Node myNode = myQueue.getHead();

		double size = myQueue.getSize();
		int sum = 0;

		while (myNode != null) {
			sum += myNode.n;
			myNode = myNode.next;
		}

		mean = sum / size;
		System.out.println("Mean: " + (sum / size));

	}

	/**
	 * a method which gets the standard deviation from the data provided from the file
	 */
	private void getSTD() {

		Node myNode = myQueue.getHead();

		double top = 0;

		while (myNode != null) {

			double inBrackets = myNode.n - mean;
			top += (inBrackets * inBrackets);
			myNode = myNode.next;

		}

		double insideSQRT = top / myQueue.getSize();
		double std = Math.sqrt(insideSQRT);
		System.out.println("STDL " + std);

	}
}
