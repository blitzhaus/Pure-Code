package view;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.err;
import static java.lang.System.out;

public class TestHTMLRendering
    {
        protected static final String TITLE_STRING = "Print..";
		protected static final String VERSION_STRING = "Report";

		public static void main()
		{
		SwingUtilities.invokeLater(new Runnable() {
			/**
			 * } fire up a JFrame on the Swing thread
			 */
			public void run() {
				String localPath = null;
				out.println("Starting");
				final JFrame jframe = new JFrame(TITLE_STRING + " "
						+ VERSION_STRING);
				Container contentPane = jframe.getContentPane();

				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int height = screenSize.height;
				int width = screenSize.width;
				jframe.setSize((int) (width * 0.7), (int) (height * 0.7));
				jframe.setSize(width, height);
				contentPane.setBackground(Color.YELLOW);
				contentPane.setForeground(Color.BLUE);
				jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				try {
					out.println("acquiring URL");

					URL location = HtmlExporter.class.getProtectionDomain()
							.getCodeSource().getLocation();

					String path = location.getPath();
					path = path.replaceAll("%20", " ");
					localPath = "file://" + path + "htmlreport.html";
					URL url = new URL(localPath);
					JEditorPane jep = new JEditorPane(url);
					out.println("URL acquired");
					JScrollPane jsp = new JScrollPane(jep,
							JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					contentPane.add(jsp);

					try {
						jep.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					err.println("can't find URL");
					contentPane.add(new JLabel("can't find URL"));
				}
				jframe.validate();
				jframe.setVisible(true);
				File file = new File(localPath);
				file.delete();
			}
		});
	}// end main
        
    }// end TestHTMLRendering