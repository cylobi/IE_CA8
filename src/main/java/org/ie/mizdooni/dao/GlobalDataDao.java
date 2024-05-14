package org.ie.mizdooni.dao;

import java.util.List;

import org.ie.mizdooni.model.GlobalData;
import org.springframework.stereotype.Repository;

public class GlobalDataDao extends BaseDao< GlobalData >{
    public GlobalDataDao(){
        super(GlobalData.class);
    }
}
