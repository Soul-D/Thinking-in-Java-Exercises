package io;

import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;

public class Ex5ProcessFiles {
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String regex;

    public Ex5ProcessFiles(Strategy strategy, String regex) {
        this.strategy = strategy;
        this.regex = regex;
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
                        if (arg.matches(regex)) {
                            strategy.process(new File(arg).getCanonicalFile());
                        }
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void
    processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(
                root.getAbsolutePath(), regex))
            strategy.process(file.getCanonicalFile());
    }

    public static void main(String[] args) {
        new Ex5ProcessFiles(new Ex5ProcessFiles.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, "O.*\\.java").start(args);
    }
}
