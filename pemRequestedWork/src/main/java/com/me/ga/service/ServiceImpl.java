/**
 * 
 */
package com.me.ga.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.ga.daoImpl.EditImpl;
import com.me.ga.entity.Case;
import com.me.ga.entity.CaseForDis;
import com.me.ga.entity.CaseProcessInfo;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
@Transactional
@Service(value = "service")
public class ServiceImpl {

    @Autowired
    @Qualifier(value = "edit")
    private EditImpl edit;

    public void saveAll(JSONArray array) {
        edit.saveAll(array);
    }

    public void update(CaseProcessInfo caseProcessInfo) {
        edit.update(caseProcessInfo);
    }

    public JSONArray selectAll() {
        return edit.selectAll();
    }

    public JSONArray search(CaseForDis caseForDis) {
        return edit.search(caseForDis);
    }
    
    public void saveCase(Case vcase) {
        edit.saveCase(vcase);
    }

    /**
     * edit is returned.
     * <br>
     * @return  edit
     */
    public EditImpl getEdit() {
        return edit;
    }

    /**
     * edit is set up.
     * <br>
     * @param edit EditImpl
     */
    public void setEdit(EditImpl edit) {
        this.edit = edit;
    }



}
