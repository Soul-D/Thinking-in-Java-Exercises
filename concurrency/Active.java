package concurrency;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Active {
}
