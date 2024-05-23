
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SocialHandler {

    private TreeMap<Integer, String> handles;

    private static Integer idIterator = 0;

    public SocialHandler() {
        this.handles = new TreeMap<>();
    }

    public TreeMap<Integer, String> getHandles() {
        return this.handles;
    }

    public void addHandle(String handle) {

        String handleToAdd = handle;

        boolean handleGood = false;

        try {
            handleGood = checkHandle(handleToAdd);

        } catch (NullPointerException e) {
            System.out.println("(!) New handle cannot be null!");

        }

        if (handleGood) {
            String newHandle = "@" + handle.toLowerCase();
            if (!this.handles.containsValue(newHandle)) {
                this.handles.put(idIterator, newHandle);
                idIterator++;
                System.out.println("Created new handle: " + newHandle);
            } else {
                System.out.println("(!) Handle: " + newHandle + " already exists!");

            }
        }
    }

    public boolean checkHandle(String handleToCheck) throws NullPointerException {
        if (handleToCheck.length() > 9) {
            System.out.println("(!) Handle cannot be longer than 9 characters!");
            return false;
        }
        if (handleToCheck.contains(" ")) {
            System.out.println("(!) Handle cannot contain blank characters!");
            return false;
        }
        if (handleToCheck.isEmpty()) {
            System.out.println("(!) Handle cannot be empty!");
            return false;
        }
        return true;
    }

    public void removeHandle(String handleToRemove) {
        if (!this.handles.containsValue(handleToRemove)) {
            System.out.println("(!) Handle does not exist!");
        } else {
            for (Map.Entry<Integer, String> entry : this.handles.entrySet()) {
                if (entry.getValue().equals(handleToRemove)) {
                    this.handles.remove(entry.getKey());
                    System.out.println("Removed handle: " + handleToRemove);
                    break;
                }
            }
        }
    }

    public void updateHandle(String handleToUpdate, String handleToReplaceWith) {
        boolean newHandleGood = false;
        if (this.handles.containsValue(handleToUpdate)) {
            newHandleGood = checkHandle(handleToReplaceWith);
        } else {
            System.out.println("(!) Handle to update does not exist!");
        }
        if (newHandleGood) {
            for (Map.Entry<Integer, String> entry : this.handles.entrySet()) {
                if (entry.getValue().equals(handleToUpdate)) {
                    handleToReplaceWith = "@" + handleToReplaceWith;
                    this.handles.replace(entry.getKey(), handleToReplaceWith);
                    System.out.println("Updated handle: " + handleToUpdate + " successfully.");
                    System.out.println("New handle: " + handleToReplaceWith);
                    break;
                }
            }
        }
    }

    public void writeAllHandlesToFile() {

        FileWriter fileWriter = null;
        String handle;
        try {
            fileWriter = new FileWriter("src/main/resources/allHandles/");
            for (Map.Entry<Integer, String> entry : this.getHandles().entrySet()) {

                handle = entry.getValue();
                fileWriter.write(handle + "\n");
            }

        } catch (IOException e) {
            System.out.println("Unable to write to file");
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                System.out.println("\nWrote to file successfully\n");
            } catch (IOException e) {
                System.out.println("Unable to close resource");
            }
        }
    }

    public void readAllHandlesFromFile(){
        File file = new File("src/main/resources/allHandles/");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            System.out.println("\nRead from file successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SocialHandler socialHandler = new SocialHandler();

        socialHandler.addHandle("asdfghjk");
        socialHandler.addHandle("zxcvbnmn");
        socialHandler.addHandle("qwertyuio");

        socialHandler.writeAllHandlesToFile();

        socialHandler.readAllHandlesFromFile();
    }
}