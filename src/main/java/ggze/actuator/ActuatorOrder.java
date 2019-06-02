package ggze.actuator;

public class ActuatorOrder implements Actuator{
    private ActuatorCommand beanMap;
    public ActuatorOrder(ActuatorCommand beanMap){
        this.beanMap=beanMap;
    }
    @Override
    public Object execute(Object object) throws Exception {
        String order=(String) object;
        if(order.equals("add")){
            ActuatorAdd add=new ActuatorAdd();
            add.setBeanMap(beanMap);
            return add;
        }
        if(order.equals("update")){
            ActuatorUpdate update=new ActuatorUpdate();
            update.setBeanMap(beanMap);
            return update;
        }
        return null;
    }
}
