package ggze.actuator;

public class ActuatorUpdate implements Actuator {
    private ActuatorCommand beanMap;
    public void setBeanMap(ActuatorCommand beanMap){
        this.beanMap=beanMap;
    }
    @Override
    public Object execute(Object object) throws Exception {
        return beanMap.update(object);
    }
}
