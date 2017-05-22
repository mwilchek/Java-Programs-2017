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

public class AddressPropogation {

	public static void main(String[] args) throws IOException {
		JFrame parent = new JFrame();
		String multiLineMsg[] = {
				"Please select a file with concatenated addresses.",
				"The file must be just one column of the data." };
		JOptionPane.showMessageDialog(parent, multiLineMsg);

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
				address = address.substring(0, address.length() - 1);
				String[] words = address.split("\\s+");

				StringBuilder sb = new StringBuilder();

				if (words.length > 1) {
					String lastword = words[words.length - 1];
					String firstword = words[0];
					words[0] = lastword;
					words[words.length - 1] = firstword;
					String newAddress = Arrays.toString(words).replaceAll(
							"[\\[\\]\\,]", "");
					sb.append(address);
					sb.append(',');
					sb.append(newAddress);
					sb.append('\n');
					pw.write(sb.toString());
				}
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