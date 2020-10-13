package spring.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class is created solely to generate Bean objects for appropriate
 * consumption. It is a singleton class. It contains one generic method
 * to generate a common-use Bean.
 *
 * <p>
 * The class is an origin for Dependency Injection using Spring methodology.
 * It is an abstract version of DI and it uses generic variables and
 * methods to make the class flexible as much as we can.
 *
 * @author Muhammed Bera KOÇ
 * @since 1.3
 */
public class BeanCreator {

    public static BeanCreator instance = new BeanCreator();

    private BeanCreator() {}


    /**
     * Returns a Bean object that can be used for triggering specified
     * method. Config class must contain only a getter that returns the
     * common ancestor of given class. Consumer is a special types which
     * is autowired by Spring and it is a @Component.
     * <p>
     *     This method is intended to be used by inherited classes. Since
     *     it triggers a common method.
     * </p>
     *
     * @param configClass A Spring configuration class
     * @param consumerClass A Spring consumer class used by method autowire
     * @param <T> Generic type
     * @return The Bean object to trigger common behaviour
     */
    public <T> T createBean(Class<?> configClass, Class<T> consumerClass) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(configClass);
        return applicationContext.getBean(consumerClass);
    }
}
