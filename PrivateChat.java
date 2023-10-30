import java.io.*;
import java.util.*;

/**
 * The PrivateChat class represents a private chat history between two users.
 * It provides methods to read and write private messages.
 */
public class PrivateChat {
    private ArrayList<String> privateChatHistory;

    /**
     * Constructs a PrivateChat object with an empty chat history.
     */
    public PrivateChat() {
        privateChatHistory = new ArrayList<>();
    }

    /**
     * Reads and displays the private chat history from two message files.
     *
     * @param privateMessage1 The first private message file.
     * @param privateMessage2 The second private message file.
     * @throws FileNotFoundException If either of the private message files is not found.
     */
    public void privateRead(File privateMessage1, File privateMessage2) throws FileNotFoundException {
        updatePrivateChat(privateMessage1, privateMessage2);
        for (String chat : privateChatHistory) {
            System.out.println(chat);
        }
    }

    /**
     * Updates the private chat history by reading from two message files.
     *
     * @param privateMessage1 The first private message file.
     * @param privateMessage2 The second private message file.
     * @throws FileNotFoundException If either of the private message files is not found.
     */
    private void updatePrivateChat(File privateMessage1, File privateMessage2) throws FileNotFoundException {
        try {
            Scanner privateReader1 = new Scanner(privateMessage1);
            while (privateReader1.hasNextLine()) {
                String privateInput = privateReader1.nextLine();
                privateChatHistory.add(privateInput);
            }
            privateReader1.close();
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            Scanner privateReader2 = new Scanner(privateMessage2);
            while (privateReader2.hasNextLine()) {
                String privateInput = privateReader2.nextLine();
                privateChatHistory.add(privateInput);
            }
            privateReader2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a private message to the appropriate message file based on the IP addresses.
     *
     * @param privateMessage The private message to be written.
     * @param ip1            The IP address of the first user.
     * @param ip2            The IP address of the second user.
     * @param name           The name of the sender.
     * @throws IOException If an I/O error occurs.
     */
    public void privateWrite(String privateMessage, String ip1, String ip2, String name) throws IOException {
        File privateFile1 = new File("./Donut[" + ip1 + ip2 + "].log");
        File privateFile2 = new File("./Donut[" + ip2 + ip1 + "].log");

        try {
            if (privateFile1.exists()) {
                FileWriter publicMessageWriter = new FileWriter(privateFile1, true);
                PrintWriter appendWriter = new PrintWriter(publicMessageWriter);
                appendWriter.println("<" + name + "> " + privateMessage + "\n");
                appendWriter.close();
            } else if (privateFile2.exists()) {
                FileWriter publicMessageWriter = new FileWriter(privateFile2, true);
                PrintWriter appendWriter = new PrintWriter(publicMessageWriter);
                appendWriter.println("<" + name + "> " + privateMessage + "\n");
                appendWriter.close();
            } else {
                privateFile1.createNewFile();
                PrintWriter publicMessageWriter = new PrintWriter(privateFile1);
                publicMessageWriter.write("<" + name + ">" + privateMessage + "\n");
                publicMessageWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}