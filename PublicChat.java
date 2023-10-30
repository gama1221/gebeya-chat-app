import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The PublicChat class represents a public chat system.
 * It allows reading and writing messages to a public chat history.
 */
public class PublicChat {
    private List<String> publicChatHistory;

    /**
     * Constructs a PublicChat object with an empty chat history.
     */
    public PublicChat() {
        publicChatHistory = new ArrayList<>();
    }

    /**
     * Retrieves the public chat history.
     *
     * @return The list of messages in the public chat history.
     */
    public List<String> getPublicChatHistory() {
        return publicChatHistory;
    }

    /**
     * Reads messages from a file and updates the public chat history.
     *
     * @param privateMessage The file containing the private messages.
     */
    public void publicRead(File privateMessage) {
        updatePublicChat(privateMessage);
        publicChatHistory.forEach(System.out::println);
    }

    /**
     * Updates the public chat history by reading messages from a file.
     *
     * @param privateMessage The file containing the private messages.
     */
    private void updatePublicChat(File privateMessage) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(privateMessage.getAbsolutePath()));
            publicChatHistory.clear();
            publicChatHistory.addAll(lines);
        } catch (IOException e) {
            System.out.println("NO PUBLIC CHATS");
        }
    }

    /**
     * Writes a message to the public chat history.
     *
     * @param privateMessage The message to be written.
     * @param name           The name of the sender.
     */
    public void publicWrite(String privateMessage, String name) {
        File publicFile = new File("./Eurakarte.log");
        try {
            if (!publicFile.exists()) {
                publicFile.createNewFile();
            }
            FileWriter publicMessageWriter = new FileWriter(publicFile, true);
            PrintWriter appendWriter = new PrintWriter(publicMessageWriter);
            appendWriter.println("<" + name + "> " + privateMessage); // No need for additional new line
            appendWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}