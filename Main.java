import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        PrivateChat privateChat = new PrivateChat();
        PublicChat publicChat = new PublicChat();
        FriendsList friendList = new FriendsList();
        System.out.println("ENTER YOUR NAME: ");
        Scanner entry = new Scanner(System.in);
        String name = entry.nextLine();
        System.out.println("ENTER YOUR OWN IP: ");
        String ip = entry.nextLine();
        System.out.println("---------------------------------------------------------");
        System.out.println(" PRESS....");
        System.out.println("""
                1: LIST FRIENDS.
                2: ADD FRIEND.
                3: MESSAGE PUBLIC.
                4: MESSAGE PRIVATE.
                5: READ PUBLIC.
                6: READ PRIVATE.
                7: CHANGE NAME AND IP.
                8: QUIT""");

        int choice = entry.nextInt();
        entry.nextLine();
        while (choice != 8) {
            if (choice == 1) {
                try {
                    File friendPath = new File("./friends.list");

                    friendList.friendRead(friendPath);
                } catch (Exception e) {
                    System.out.println("No Friends.");
                }
            } else if (choice == 2) {
                System.out.println("INPUT NAME: ");
                String friend = entry.nextLine();
                System.out.println("INPUT IP: ");
                friend += "\n" + entry.nextLine();

                friendList.friendWrite(friend);
            } else if (choice == 3) {
                System.out.println("MESSAGE: ");
                String publicMessage = entry.nextLine();
                publicChat.publicWrite(publicMessage, name);
            } else if (choice == 4) {
                try {
                    File friendPath = new File("./friends.list");

                    friendList.displayList(friendPath);
                    int index = entry.nextInt();
                    entry.nextLine();
                    if (index < 1 || index > friendList.max) {
                        System.out.println("INDEX DOESNT EXIST.");
                    } else {
                        System.out.println("MESSAGE: ");
                        String privateMessage = entry.nextLine();
                        privateChat.privateWrite(privateMessage, ip, friendList.getByIndex(index), name);

                    }
                } catch (Exception e) {
                    System.out.println("No Friends.");
                }


            } else if (choice == 5) {
                File publicPath = new File("./Eurakarte.log");
                publicChat.publicRead(publicPath);
            } else if (choice == 6) {
                try {
                    File friendPath = new File("./friends.list");

                    friendList.displayList(friendPath);
                    int index = entry.nextInt();
                    entry.nextLine();
                    if (index < 1 || index > friendList.max) {
                        System.out.println("INDEX DOESNT EXIST.");
                    } else {
                        File privateFile1 = new File("./Donut[" + ip + friendList.getByIndex(index) + "].log");
                        File privateFile2 = new File("./Donut[" + friendList.getByIndex(index) + ip + "].log");
                        privateChat.privateRead(privateFile1, privateFile2);

                    }
                } catch (Exception e) {
                    System.out.println("No Friends.");
                }

            } else if (choice == 7) {
                System.out.println("ENTER YOUR NAME: ");
                name = entry.nextLine();
                System.out.println("ENTER YOUR OWN IP: ");
                ip = entry.nextLine();
            }
            System.out.println("---------------------------------------------------------");
            System.out.println(" PRESS....");
            System.out.println("""
                    1: LIST FRIENDS.
                    2: ADD FRIEND.
                    3: MESSAGE PUBLIC.
                    4: MESSAGE PRIVATE.
                    5: READ PUBLIC.
                    6: READ PRIVATE.
                    7: CHANGE NAME AND IP.
                    8: QUIT""");
            choice = entry.nextInt();
            entry.nextLine();
        }

    }
}