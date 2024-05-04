/**
 * Traverses through root directory and constructs a file heirarchy.
 */

package utils;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import utils.structures.FileHeirarchyNode;

public class DirectorySurfer {
    private File root;
    private int fileCount;
    private int directoryCount;
    private ExecutorService executor;
    Future<FileHeirarchyNode> future;
    private final boolean DIRECTORY = true;
    private final boolean NONDIRECTORY = false;
    private FileHeirarchyNode topNode;
    public DirectorySurfer(){
        this.root = new File(".");
        this.fileCount = 0;
        this.directoryCount = 0;
        this.executor = Executors.newCachedThreadPool();
        
    }

    public DirectorySurfer(String root){
        this();
        this.root = new File(root);
    }

    public void surf(){
        this.future = executor.submit(new Surfer());
        executor.submit(new Runnable() {
            public void run(){
                try{
                    future.get().printFileHeirarchy();
                } catch (InterruptedException | ExecutionException ex){
                    ex.printStackTrace();
                    System.out.println("Error retrieving thread result");
                }
            }
        });
        
        
    }
    public FileHeirarchyNode getTopNode(){
        return this.topNode;
    }
    public void shutdownExecutors(){
        System.out.println("Executor shutdown requested");
        if(this.executor != null){
            this.executor.shutdown();
            try{
                if(!executor.awaitTermination(500, TimeUnit.MILLISECONDS)){
                    executor.shutdownNow();
                }
            } catch(InterruptedException ex){
                executor.shutdownNow();
            }
            
        }
        System.out.println("Executor shutdown complete");
        
    }

    private class Surfer implements Callable<FileHeirarchyNode>{

        public FileHeirarchyNode call(){
            topNode = new FileHeirarchyNode(root);
            surf(root);
            return topNode;
        }

        private void surf(File file){
            FileHeirarchyNode currentNode = topNode;
            surf(file, currentNode);
        }

        private void surf(File file, FileHeirarchyNode currentNode){
            File[] subFiles = file.listFiles();

            for(File f : subFiles){
                //System.out.println(f.getAbsolutePath()); // Print the absolute file paths. 
                if(Thread.currentThread().isInterrupted()){
                    return;
                }
                if(f.isDirectory()){
                    directoryCount++;
                    FileHeirarchyNode newNode = new FileHeirarchyNode(f, currentNode, DIRECTORY);
                    currentNode.getChildren().add(newNode);
                    surf(f, newNode);
                } else{
                    fileCount++;
                    currentNode.getChildren().add(new FileHeirarchyNode(f, currentNode, NONDIRECTORY));
                }
            }
        }

    }
}