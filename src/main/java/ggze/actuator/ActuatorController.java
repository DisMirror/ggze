package ggze.actuator;

import org.springframework.stereotype.Service;

@Service
public class ActuatorController implements Actuator{
    @Override
    public Object execute(Object object) throws Exception {
        return null;
    }
    public Object execute(Object bean,String orders,Object dao) throws Exception {
        ActuatorCommand beanMap=new ActuatorCommand(bean);
        ActuatorOrder broker=new ActuatorOrder(beanMap);
        Actuator order= (Actuator) broker.execute(orders);
        return  order.execute(dao);
    }
}
