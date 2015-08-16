package ir.dayasoft.steelpars.Class;


public class Drawer {
    private int Icon;
    private String Name;

    public int getIcon() {
        return Icon;
    }
    public void setIcon(int icon) {
        Icon = icon;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }


    // Constructor.
    public Drawer(int icon, String name) {

        setIcon(icon);
        setName(name);
    }
    public Drawer() {

    }

    public void SetObject (int icon, String name) {
        Icon = icon;
        Name = name;
    }



}
