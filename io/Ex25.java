package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class Ex25 {

    static public AllocateTest[] tests = {
            new AllocateTest("ChannelCopyTest") {
                @Override
                public void test(boolean flag) throws IOException {
                    FileChannel
                            in = new FileInputStream("./src/main/java/io/data1.txt").getChannel(),
                            out = new FileOutputStream("./src/main/java/io/data2.txt").getChannel();
                    ByteBuffer buffer = allocateDirect(BSIZE, flag);
                    while (in.read(buffer) != -1) {
                        buffer.flip();
                        out.write(buffer);
                        buffer.clear();
                    }
                }
            },
            new AllocateTest("BufferToText") {
                @Override
                public void test(boolean flag) throws IOException {
                    FileChannel fc = new FileOutputStream("./src/main/java/io/data2.txt").getChannel();
                    fc.write(ByteBuffer.wrap("Some text".getBytes()));
                    fc.close();
                    fc = new FileInputStream("./src/main/java/io/data2.txt").getChannel();
                    ByteBuffer buff = allocateDirect(BSIZE, flag);
                    fc.read(buff);
                    buff.flip();
                    buff.rewind();
                    String encoding = System.getProperty("file.encoding");
                    fc = new FileOutputStream("./src/main/java/io/data2.txt").getChannel();
                    fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
                    fc.close();
                    fc = new FileInputStream("./src/main/java/io/data2.txt").getChannel();
                    buff.clear();
                    fc.read(buff);
                    buff.flip();
                    fc = new FileOutputStream("./src/main/java/io/data2.txt").getChannel();
                    buff = allocateDirect(24, flag);
                    buff.asCharBuffer().put("Some text");
                    fc.write(buff);
                    fc.close();
                    fc = new FileInputStream("./src/main/java/io/data2.txt").getChannel();
                    buff.clear();
                    fc.read(buff);
                    buff.flip();
                }
            },
            new AllocateTest("GetData") {
                @Override
                public void test(boolean flag) throws IOException {
                    ByteBuffer bb = allocateDirect(BSIZE, flag);
                    int i = 0;
                    while (i++ < bb.limit())
                        if (bb.get() != 0)
                            System.out.println("nonzero");
                    bb.rewind();
                    bb.asCharBuffer().put("Howdy!");
                    bb.rewind();
                    bb.asShortBuffer().put((short) 471142);
                    bb.rewind();
                    bb.asIntBuffer().put(99471142);
                    bb.rewind();
                    bb.asLongBuffer().put(99471142);
                    bb.rewind();
                    bb.asFloatBuffer().put(99471142);
                    bb.rewind();
                    bb.asDoubleBuffer().put(99471142);
                    bb.rewind();
                }
            },
            new AllocateTest("IntBufferDemo") {
                @Override
                public void test(boolean flag) throws IOException {
                    ByteBuffer bb = allocateDirect(BSIZE, flag);
                    IntBuffer ib = bb.asIntBuffer();
                    ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
                    ib.get(3);
                    ib.put(3, 1811);
                    ib.flip();
                    while (ib.hasRemaining()) {
                        int i = ib.get();
                    }
                }
            }
    };

    public static void main(String[] args) throws IOException {
        for (AllocateTest test : tests) {
            test.runTest();
        }
    }
}

abstract class AllocateTest {
    private String name;

    public AllocateTest(String name) {
        this.name = name;
    }

    public static final int BSIZE = 1024;

    public static ByteBuffer allocateDirect(int capacity, boolean flag) {
        Long time = System.nanoTime();
        ByteBuffer res = flag ? ByteBuffer.allocateDirect(capacity) : ByteBuffer.allocate(capacity);
        System.out.println("Allocate " + flag + " " + (System.nanoTime() - time));
        return res;
    }

    public void runTest() throws IOException {
        Long time = System.nanoTime();
        test(false);
        time = System.nanoTime() - time;
        Long time2 = System.nanoTime();
        test(true);
        time2 = System.nanoTime() - time2;
        System.out.println("    " + name + ": A - DA = " + (time - time2));
    }

    public abstract void test(boolean flag) throws IOException;
}

