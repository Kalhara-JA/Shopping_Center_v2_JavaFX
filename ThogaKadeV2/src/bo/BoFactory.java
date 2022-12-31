package bo;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.ItemDaoImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}
    public static BoFactory getInstance(){
        return boFactory==null ? boFactory=new BoFactory():boFactory;
    }
    public enum BoType{
        ITEM,CUSTOMER
    }

    public <T> T getBo(BoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case ITEM:
                return (T) new ItemBoImpl();
            default:
                return null;
        }
    }
}
