package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
/**
 * This class opens the session factory and close it
 *
 */
public class Dao {

    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * This method saves  the compare object and merge it.
     *
     */
    public void saveAndMerge(Compare compare) throws Exception {
        Session session =sessionFactory.openSession();
        session.beginTransaction();
        //find or create caps
        String queryStr ="from Caps where name='"
                + compare.getCapsInstance().getCaps().getName()+"'";

        List<Caps> capsList =session.createQuery(queryStr).getResultList();

        //caps style_key is in database
        if (capsList.size() ==1){
            capsList.get(0).setStyle_key(compare.getCapsInstance().getCaps().getStyle_key());


            //map caps in caps_instance
            compare.getCapsInstance().setCaps(capsList.get(0));
        } else if (capsList.size()==0) {

            session.saveOrUpdate(compare.getCapsInstance().getCaps());
        }  else {
            throw new Exception("Multiple products with the same style_key");
        }
        //find caps_instance
        //check if  caps_instance has the same products with  the same colour by id
        queryStr = "from CapsInstance where colour='"+compare.getCapsInstance().getColour()+"'";
        queryStr += "and caps_id=" +compare.getCapsInstance().getCaps().getId();
        List<CapsInstance>capsInstanceList = session.createQuery(queryStr).getResultList();

//        caps_instance is in the database
        if(capsInstanceList.size()== 1){
            //update product image  link
            capsInstanceList.get(0).setImage_url(compare.getCapsInstance().getImage_url());

            //set mapped cap_instance in caps
            compare.setCapsInstance(capsInstanceList.get(0));

            //no cap with that colour
        } else if (capsInstanceList.size()==0) {

            session.saveOrUpdate(compare.getCapsInstance());
        }else {
            throw new Exception("Multiple products with the same colour");

        }
        //save compare
        queryStr =" from Compare  where url='"+compare.getUrl()+"'";
        List<Compare>compareList =session.createQuery(queryStr).getResultList();

        //if  the price is in  the database
        if (compareList.size() ==1) {
            //update the product link
            compareList.get(0).setPrice(compare.getPrice());
        } else if (compareList.size()==0) {

            //save new price and url
            session.saveOrUpdate(compare);


        }else {
            throw  new Exception("Multiple products with the same url link ");


        }
        //commit transaction and save changes
        session.getTransaction().commit();

        //close the session

        session.close();
        System.out.println("Product   added  with the style key: "+compare.getCapsInstance().getCaps().getStyle_key()+
                " Colour: " + compare.capsInstance.getColour() +" Price: "+ compare.getPrice());

    }
}
