package annotations;

import net.mindview.atunit.*;
import net.mindview.util.BinaryFile;
import net.mindview.util.ProcessFiles;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

@Retention(RetentionPolicy.RUNTIME)
public @interface Ex11TestNote {
    String value();
}

@SuppressWarnings("Duplicates")
class Ex11AtUnit implements ProcessFiles.Strategy {
    static Class<?> testClass;
    static List<String> failedTests = new ArrayList<>();
    static long testsRun = 0;
    static long failures = 0;

    public static void main(String[] args) throws Exception {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        new ProcessFiles(new Ex11AtUnit(), "class").start(new String[]{"target\\classes\\annotations\\Ex11StackStringTest"});
        if (failures == 0)
            print("OK (" + testsRun + " tests)");
        else {
            print("(" + testsRun + " tests)");
            print("\n>>> " + failures + " FAILURE" + (failures > 1 ? "S" : "") + " <<<");
            for (String failed : failedTests)
                print("  " + failed);
        }
    }

    public void process(File cFile) {
        try {
            String cName = ClassNameFinder.thisClass(BinaryFile.read(cFile));
            if (!cName.contains("."))
                return;
            testClass = Class.forName(cName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TestMethods testMethods = new TestMethods();
        Method creator = null;
        Method cleanup = null;
        for (Method m : testClass.getDeclaredMethods()) {
            testMethods.addIfTestMethod(m);
            if (creator == null)
                creator = checkForCreatorMethod(m);
            if (cleanup == null)
                cleanup = checkForCleanupMethod(m);
        }
        if (testMethods.size() > 0) {
            if (creator == null)
                try {
                    if (!Modifier.isPublic(testClass.getDeclaredConstructor().getModifiers())) {
                        print("Error: " + testClass + " default constructor must be public");
                        System.exit(1);
                    }
                } catch (NoSuchMethodException e) {
                }
            print(testClass.getName());
        }
        for (Method m : testMethods) {
            printnb("  . " + m.getName() + " ");
            Ex11TestNote annot = m.getDeclaredAnnotation(Ex11TestNote.class);
            if (annot != null) {
                printnb("[" + annot.value() +"]");
            }
            try {
                Object testObject = createTestObject(creator);
                boolean success = false;
                try {
                    if (m.getReturnType().equals(boolean.class))
                        success = (Boolean) m.invoke(testObject);
                    else {
                        m.invoke(testObject);
                        success = true;
                    }
                } catch (InvocationTargetException e) {
                    print(e.getCause());
                }
                print(success ? "" : "(failed)");
                testsRun++;
                if (!success) {
                    failures++;
                    failedTests.add(testClass.getName() + ": " + m.getName());
                }
                if (cleanup != null)
                    cleanup.invoke(testObject, testObject);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class TestMethods extends ArrayList<Method> {
        void addIfTestMethod(Method m) {
            if (m.getAnnotation(Test.class) == null)
                return;
            if (!(m.getReturnType().equals(boolean.class) || m.getReturnType().equals(void.class)))
                throw new RuntimeException("@Test method must return boolean or void");
            m.setAccessible(true);
            add(m);
        }
    }

    private static Method checkForCreatorMethod(Method m) {
        if (m.getAnnotation(TestObjectCreate.class) == null)
            return null;
        if (!m.getReturnType().equals(testClass))
            throw new RuntimeException("@TestObjectCreate must return instance of Class to be tested");
        if ((m.getModifiers() & java.lang.reflect.Modifier.STATIC) < 1)
            throw new RuntimeException("@TestObjectCreate must be static.");
        m.setAccessible(true);
        return m;
    }

    private static Method checkForCleanupMethod(Method m) {
        if (m.getAnnotation(TestObjectCleanup.class) == null)
            return null;
        if (!m.getReturnType().equals(void.class))
            throw new RuntimeException("@TestObjectCleanup must return void");
        if ((m.getModifiers() & java.lang.reflect.Modifier.STATIC) < 1)
            throw new RuntimeException("@TestObjectCleanup must be static.");
        if (m.getParameterTypes().length == 0 || m.getParameterTypes()[0] != testClass)
            throw new RuntimeException("@TestObjectCleanup must take an argument of the tested type.");
        m.setAccessible(true);
        return m;
    }

    private static Object createTestObject(Method creator) {
        if (creator != null) {
            try {
                return creator.invoke(testClass);
            } catch (Exception e) {
                throw new RuntimeException("Couldn't run @TestObject (creator) method.");
            }
        } else {
            try {
                return testClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Couldn't create a test object. Try using a @TestObject method.");
            }
        }
    }
}

class Ex11StackStringTest extends StackL<String> {
    public Ex11StackStringTest() {
    }

    @Test
    @Ex11TestNote("Testing stack push")
    void _push() {
        push("one");
        assert top().equals("one");
        push("two");
        assert top().equals("two");
    }

    @Test
    @Ex11TestNote("Testing stack pop")
    void _pop() {
        push("one");
        push("two");
        assert pop().equals("two");
        assert pop().equals("one");
    }

    @Test
    @Ex11TestNote("Testing stack top")
    void _top() {
        push("A");
        push("B");
        assert top().equals("B");
        assert top().equals("B");
    }
}


