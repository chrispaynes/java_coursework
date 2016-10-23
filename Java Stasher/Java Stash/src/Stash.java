import javax.swing.JOptionPane;
import java.io.*;
import java.util.zip.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Stash {

	private static Date stashTime;
	private static String timestamp;
	private static String source;
	private static File file;
	private static String readmeFile;
	private static byte[] readmeNotes;

	public void setSource() {
		Stash.source = JOptionPane.showInputDialog(null, "SOURCE FILE: ");
		Stash.formatTimestamp();
	}

	public Stash(Date stashTime) {
		Stash.stashTime = stashTime;
	}

	public static String formatTimestamp() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("M.d.h.m.s.a.E.y");
		timestamp = dateFormatter.format(stashTime);
		return timestamp;
	}

	public FileOutputStream zipFile() {

		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(Stash.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	public String zipReadmeFile() {
		Stash.readmeFile = "STASH_" + timestamp + ".README.txt";
		return Stash.readmeFile;
	}

	public void timestampZipFile() {
		file = new File(Stash.source + "_STASH_" + Stash.timestamp + ".zip");
	}

	// Converts data to string and appends characters to a character sequence.
	public static StringBuilder getNotes() {
		String notes = JOptionPane.showInputDialog(null, "STASH NOTES");
		return new StringBuilder(notes);
	}

	public void formatNotes() {
		readmeNotes = getNotes().toString().getBytes();
	}

	public static byte[] getReadmeNotes() {
		return readmeNotes;
	}

	public static void main(String[] args) {
		Stash stash = new Stash(new Date());
		stash.setSource();
		stash.timestampZipFile();

		try {
			// Creates a new File instance by converting the given pathname
			// string into an abstract pathname.
			FileOutputStream file = stash.zipFile();

			// Out stores an output stream for writing ZIP files.
			ZipOutputStream out = new ZipOutputStream(file);

			// entry stores a new ZipEntry.
			ZipEntry entry = new ZipEntry(stash.zipReadmeFile());

			// Writes ZIP file entry.
			out.putNextEntry(entry);

			stash.formatNotes();

			// Writes the entire array of characters to output stream.
			out.write(Stash.getReadmeNotes(), 0, Stash.getReadmeNotes().length);
			
			// Closes the ZIP entry.
			out.closeEntry();

			// Closes the ZIP output stream.
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

}