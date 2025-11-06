package ExercisesFirstPartialExam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class FileNameExistsException extends Exception {
    String fileName;
    String folderName;

    public FileNameExistsException(String fileName, String folderName) {
        this.fileName = fileName;
        this.folderName = folderName;
    }

    public String getMessage() {
        return String.format("There is already a file named %s in the folder %s", fileName, folderName);
    }
}

interface IFile {
    String getFileName();

    long getFileSize();

    String getFileInfo(int indent);

    void sortBySize();

    long findLargestFile();
}

class File implements IFile {
    private String name;
    private long fileSize;

    public File(String name, long fileSize) {
        this.name = name;
        this.fileSize = fileSize;
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public long getFileSize() {
        return this.fileSize;
    }

    @Override
    public String getFileInfo(int indent) {
        return " ".repeat(Math.max(0, indent * 4)) + String.format("File name: %10s File size: %10d\n", name, fileSize);
    }

    @Override
    public void sortBySize() {

    }

    @Override
    public long findLargestFile() {
        return fileSize;
    }
}

class Folder implements IFile {
    private String name;
    private long folderSize;
    private List<IFile> files;

    public Folder(String name) {
        this.name = name;
        this.folderSize = 0;
        this.files = new ArrayList<>();
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public long getFileSize() {
        this.folderSize = files.stream().mapToLong(IFile::getFileSize).sum();
        return folderSize;
    }

    @Override
    public String getFileInfo(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indent * 4)));
        sb.append(String.format("Folder name: %10s Folder size: %10d\n", name, getFileSize()));
        for (IFile f : files) {
            sb.append(f.getFileInfo(indent + 1)); // recursive print with tab
        }
        return sb.toString();
    }

    @Override
    public void sortBySize() {
        files.sort(Comparator.comparingLong(IFile::getFileSize));
        for (IFile f : files) {
            f.sortBySize();
        }
    }

    @Override
    public long findLargestFile() {
        long largest = 0;
        for (IFile f : files) {
            if (f instanceof File) {
                if (f.getFileSize() > largest) {
                    largest = f.getFileSize();
                }
            } else if (f instanceof Folder) {
                long largestFolder = f.findLargestFile();
                if (largestFolder > largest) {
                    largest = largestFolder;
                }
            }
        }
        return largest;
    }

    public void addFile(IFile file) throws FileNameExistsException {
        if (files.stream().map(IFile::getFileName).collect(Collectors.toList()).contains(file.getFileName())) {
            throw new FileNameExistsException(file.getFileName(), this.name);
        } else {
            files.add(file);
        }
    }
}

class FileSystem implements IFile {
    private Folder rootDirectory;

    public FileSystem() {
        this.rootDirectory = new Folder("root");
    }

    @Override
    public String getFileName() {
        return "root";
    }

    @Override
    public long getFileSize() {
        return rootDirectory.getFileSize();
    }

    @Override
    public String getFileInfo(int indent) {
        return rootDirectory.getFileInfo(0);
    }

    @Override
    public void sortBySize() {
        rootDirectory.sortBySize();
    }

    @Override
    public long findLargestFile() {
        return rootDirectory.findLargestFile();
    }

    public void addFile(IFile file) throws FileNameExistsException {
        rootDirectory.addFile(file);
    }

    @Override
    public String toString() {
        return getFileInfo(0);
    }
}

public class FileSystemTest {

    public static Folder readFolder(Scanner sc) {
        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < totalFiles; i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String[] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return folder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());
    }
}