import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AddressPropagation {

	public static void main(String[] args) throws IOException {
		int rowNum = 0;
		JFrame parent = new JFrame();
		String trainQuestion = JOptionPane
				.showInputDialog("This program can create train/test address datasets. If you "
						+ "want propagated \ndata that does not match your original addresses, enter what row number "
						+ "the data \nshould begin to no longer match. If you do not want train/test data, enter a row "
						+ "number \nthat is larger than your current address dataset. ");
		int trainNum = Integer.parseInt(trainQuestion);
		String multiLineMsg[] = {
				"Please select a file with concatenated addresses.",
				"The file must be just one column of the data." };
		JOptionPane.showMessageDialog(parent, multiLineMsg);
		//Sample Data from http://assessments.milwaukee.gov/
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView()
				.getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			Scanner scanner = new Scanner((Readable) new FileReader(
					selectedFile));

			PrintWriter pw = new PrintWriter(new File(
					"C:\\Propagation_Data.csv")); // Change directory where to
													// output file

			while (scanner.hasNextLine()) {
				String address = scanner.nextLine();
				address = address.substring(0, address.length());
				String[] words = address.split("\\s+");
				StringBuilder sb = new StringBuilder();

				if (words.length > 1) {
					int randomize1 = 0;
					int randomize2 = 0;
					char dir = words[1].charAt(0); // get the first char.
					String lastword = words[words.length - 1];

					switch (dir) {
					case 'S':
						randomize1 = (int) (Math.random() * 2) + 1;
						if (randomize1 == 1) {
							words[1] = "South";
						} else {
							break;
						}
					case 'N':
						randomize1 = (int) (Math.random() * 2) + 1;
						if (randomize1 == 1) {
							words[1] = "North";
						} else {
							break;
						}
					case 'E':
						randomize1 = (int) (Math.random() * 2) + 1;
						if (randomize1 == 1) {
							words[1] = "East";
						} else {
							break;
						}
					case 'W':
						randomize1 = (int) (Math.random() * 2) + 1;
						if (randomize1 == 1) {
							words[1] = "West";
						} else {
							break;
						}
					}

					String streetTYPE = lastword;
					switch (streetTYPE) {
					case "PK":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Parkway";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}

					case "ST":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Street";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "AV":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Avenue";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "RD":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Road";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "DR":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Drive";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "PL":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Place";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "HWY":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Highway";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "CR":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Circle";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					case "BL":
						randomize2 = (int) (Math.random() * 2) + 1;
						if (randomize2 == 1) {
							lastword = "Boulevard";
						}
						if (rowNum > trainNum) {
							lastword = "Planet";
						} else {
							break;
						}
					}
					// String firstWord = words[0];
					String secondWord = words[1];

					words[1] = lastword;
					words[words.length - 1] = secondWord;
					String newAddress = Arrays.toString(words).replaceAll(
							"[\\[\\]\\,]", "");
					sb.append(address);
					sb.append(',');
					sb.append(newAddress);
					sb.append('\n');
					pw.write(sb.toString());
				}
				rowNum += 1;
			}
			scanner.close();
			pw.close();
		}

		String multiLineMsg2[] = { "Propagation Complete",
				"Your new file is located under your C: saved as Propagation_Data.csv" };
		JOptionPane.showMessageDialog(parent, multiLineMsg2);

		System.exit(0);
	}
}