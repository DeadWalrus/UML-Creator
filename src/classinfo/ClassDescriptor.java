package classinfo;

import java.io.File;
import java.util.ArrayList;

public class ClassDescriptor {
    private String name;
    private int sizeInBytes;
    private File classFile;
    
    private ClassDescriptor superClass;
    private ArrayList<ClassDescriptor> subClasses;
    public ClassDescriptor(){
        this.name = "";
        this.sizeInBytes = 0;
        this.superClass = new ClassDescriptor();
        this.subClasses = new ArrayList<>();
    }
    public ClassDescriptor(String name){
        this();
        this.name = name;
    }

    // Field accessor methods
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getSizeInBytes(){
        return this.sizeInBytes;
    }
    public void setSizeInBytes(int sizeInBytes){
        if(sizeInBytes < 0){
            System.out.println("WARNING: Class size cannot be less than zero, setting size to zero");
            this.sizeInBytes = 0;
            return;
        }
        this.sizeInBytes = sizeInBytes;
    }
    public File getClassFile(){
        return this.classFile;
    }
    public void setClassFile(File classFile){
        if(classFile == null){
            System.out.println("WARNING: Class file is being set to null. May cause issues later");
        }
        this.classFile = classFile;
    }
    public ClassDescriptor getSuperClass(){
        return this.superClass;
    }
    public void setSuperClass(ClassDescriptor superClass){
        this.superClass = superClass;
    }
    public ArrayList<ClassDescriptor> getSubClasses(){
        return this.subClasses;
    }
}
