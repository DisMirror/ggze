package ggze.loader.Printing;

public interface PrintDao extends Print{
    @Override
    void printful() throws Exception;
    void printHead();
    void prntSystem(String beanName) throws Exception;
    void printExtension(String beanName) throws Exception;
    void printing(String beanName) throws  Exception;
}
