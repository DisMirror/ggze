package pub;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({java.lang.annotation.ElementType.FIELD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ggze
{
    int leng() default 100;

    String type() default "varchar";
}
