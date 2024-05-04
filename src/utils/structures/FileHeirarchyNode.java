package utils.structures;
import java.io.File;
import java.util.ArrayList;
public class FileHeirarchyNode{
    private File file;
    private boolean isDirectory;
    private FileHeirarchyNode parent;
    private ArrayList<FileHeirarchyNode> children;
    public FileHeirarchyNode(File file){
        this.children = new ArrayList<>();
        this.parent = null;
        this.file = file;
    }
    public FileHeirarchyNode(File file, FileHeirarchyNode parent){
        this(file);
        this.parent = parent;
    }
    public FileHeirarchyNode(File file, FileHeirarchyNode parent, boolean isDirectory){
        this(file, parent);
        this.isDirectory = isDirectory;
    }
    public File getFile(){
        return this.file;
    }
    public void setFile(File file){
        this.file = file;
    }
    public FileHeirarchyNode getParent(){
        return this.parent;
    }
    public ArrayList<FileHeirarchyNode> getChildren(){
        return this.children;
    }
    public void printFileHeirarchy(){
        if(Thread.currentThread().isInterrupted()){
            return;
        }
        System.out.println("File path: " + this.file.getAbsolutePath() + "\nFile parent: " + ((this.parent != null) ? this.parent.getFile().getName() : "ORPHAN NODE"));
        for(FileHeirarchyNode fhn : children){
            fhn.printFileHeirarchy();
        }
    }
}
