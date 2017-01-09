package io;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex26JGrep {
    public static void main(String[] args) throws Exception {
        Pattern p = Pattern.compile("\\w+");
        int index = 0;
        Matcher m = p.matcher("");
        FileChannel fc = new RandomAccessFile(new File("./src/main/java/io/Ex26JGrep.java"), "r").getChannel();
        String encoding = System.getProperty("file.encoding");
        CharBuffer chb = Charset.forName(encoding).decode(fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()));
        StringBuilder sb = new StringBuilder();
        while (chb.hasRemaining()) {
            char cur = chb.get();
            if (cur == '\n') {
                m.reset(sb.toString());
                while (m.find())
                    System.out.println(index++ + ": " + m.group() + ": " + m.start());
                sb.setLength(0);
            } else
                sb.append(cur);
        }
    }
}
