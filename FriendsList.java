import java.io.*;
import java.util.*;

/**
 * The FriendsList class represents a list of friends along with their IP addresses.
 * It provides methods to display, read, and write friends' information.
 */
public class FriendsList {
    private ArrayList<String> friendsListNames;
    private ArrayList<String> friendsListIp;
    public int max;

    /**
     * Constructs a FriendsList object with empty lists for names and IP addresses.
     */
    public FriendsList() {
        friendsListNames = new ArrayList<>();
        friendsListIp = new ArrayList<>();
    }

    /**
     * Retrieves the IP address of a friend by index.
     *
     * @param i The index of the friend.
     * @return The IP address of the friend.
     */
    public String getByIndex(int i) {
        return friendsListIp.get(i - 1);
    }

    /**
     * Displays the list of friends' names and prompts for user input.
     *
     * @param friendsList The file containing the friends' information.
     * @throws FileNotFoundException If the friendsList file is not found.
     */
    public void displayList(File friendsList) throws FileNotFoundException {
        updateFriends(friendsList);
        int i = 1;
        for (String friendName : friendsListNames) {
            System.out.println(i++ + " " + friendName);
        }
        max = i - 1;
        System.out.println("INPUT INDEX: ");
    }

    /**
     * Reads and displays the list of friends' names.
     *
     * @param friendsList The file containing the friends' information.
     * @throws FileNotFoundException If the friendsList file is not found.
     */
    public void friendRead(File friendsList) throws FileNotFoundException {
        updateFriends(friendsList);
        for (String friendName : friendsListNames) {
            System.out.println(friendName);
        }
    }

    /**
     * Updates the friends' lists by reading from the friendsList file.
     *
     * @param friendsList The file containing the friends' information.
     * @throws FileNotFoundException If the friendsList file is not found.
     */
    private void updateFriends(File friendsList) throws FileNotFoundException {
        Scanner friendReader = new Scanner(friendsList);
        friendsListNames.clear();
        friendsListIp.clear();
        int i = 0;
        while (friendReader.hasNextLine()) {
            String friendName = friendReader.nextLine();
            if (i++ % 2 == 0)
                friendsListNames.add(friendName);
            else
                friendsListIp.add(friendName);
        }
        friendReader.close();
    }

    /**
     * Writes a friend's name to the friendsList file.
     *
     * @param friendsName The name of the friend to be written.
     * @throws IOException If an I/O error occurs.
     */
    public void friendWrite(String friendsName) throws IOException {
        File friendFile = new File("./friends.list");
        try {
            if (!friendFile.exists()) {
                friendFile.createNewFile();
            }
            FileWriter friendNameWriter = new FileWriter(friendFile, true);
            PrintWriter appendWriter = new PrintWriter(friendNameWriter);
            appendWriter.println(friendsName); // Append a new line of data
            appendWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}