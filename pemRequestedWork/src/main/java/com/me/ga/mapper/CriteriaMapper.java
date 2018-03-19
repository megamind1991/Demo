/**
 * 
 */
package com.me.ga.mapper;

import java.util.List;

import com.me.ga.entity.Case;
import com.me.ga.entity.CaseForDis;
import com.me.ga.entity.CaseProcessInfo;
import com.me.ga.entity.Customer;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
public interface CriteriaMapper {

    int insert$customerBykey(String id, String name, String age);

    int delete$customerByKey(int id);

    int update$customerByKey(Customer cus);

    List<Customer> selectall$customerByKey();

    List<Customer> selectlike$customerByKey(String param);

    //-------------------------------------------------------------------------------------------------
    int insert$case(Case vcase);

    int insert$caseProcessInfo(CaseProcessInfo caseProcessInfo);

    List<CaseForDis> selectall$case();

    int delete$case();

    int delete$caseProcessInfo();

    int delete$caseProcessInfoByKey(String caseSeqno);

    CaseProcessInfo select$caseProcessInfoByKey(String caseSeqno);

    int delete$caseByKey(String caseSeqno);

    Case select$caseByKey(String caseSeqno);

    List<CaseForDis> select$specificCase(CaseForDis caseForDis);

}
