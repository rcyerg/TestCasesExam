import java.util.ArrayList;
import java.util.List;

public class SocialHandler {

    private ArrayList<String> handles;

    public SocialHandler() {
        this.handles = new ArrayList<>();
    }

    public ArrayList<String> getHandles(){
        return this.handles;
    }

    public void addHandle(String handle) {

        String handleToAdd = handle;

        boolean handleGood = false;

        try {
            handleGood = checkHandle(handleToAdd);

        } catch (NullPointerException e){
            System.out.println("(!) New handle cannot be null!");

        }

        if (handleGood) {
            String newHandle = "@" + handle.toLowerCase();
            if (!handles.contains(newHandle)) {
                handles.add(newHandle);
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
        return true;
    }


    public static void main(String[] args) {

    }
}
