package classSrc;

abstract public class Person {
    protected String name;
    protected String ID;

    public Person(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public void setID(String id) {
    	this.ID = id;
    }
    
    public String getID() {
    	return this.ID;
    }
}

