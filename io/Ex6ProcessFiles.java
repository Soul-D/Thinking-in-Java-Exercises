package io;

import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Ex6ProcessFiles {
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private Calendar date;

    public Ex6ProcessFiles(Strategy strategy, Calendar date) {
        this.strategy = strategy;
        this.date = date;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0)
                processDirectoryTree(new File("."));
            else
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        if (arg.endsWith(".java") && fileArg.lastModified() >= date.getTimeInMillis())
                            strategy.process(new File(arg).getCanonicalFile());
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), ".*\\.java"))
            if (file.lastModified() >= date.getTimeInMillis())
                strategy.process(file.getCanonicalFile());
    }

    public static void main(String[] args) {
        new Ex6ProcessFiles(new Ex6ProcessFiles.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, new GregorianCalendar(2016, 11, 29)).start(args);
    }
}
