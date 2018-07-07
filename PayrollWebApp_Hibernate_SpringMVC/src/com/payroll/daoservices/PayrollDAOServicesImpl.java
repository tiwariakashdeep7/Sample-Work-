package com.payroll.daoservices;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.beans.Associate;
@Repository (value="payrollDAOServices")
@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
public class PayrollDAOServicesImpl implements PayrollDAOServices {
	@Autowired(required=true)
	private HibernateTemplate hibernateTemplate;
	@Override
	public int insertAssociate(Associate associate) throws SQLException {
		return (Integer) hibernateTemplate.save(associate);
	}
	@Override
	public boolean updateAssociate(Associate associate) throws SQLException {
		hibernateTemplate.update(associate);
		return true;
	}
	@Override
	public boolean deleteAssociate(int associateId) throws SQLException {
		Associate associate = hibernateTemplate.get(Associate.class, associateId);
		hibernateTemplate.delete(associate);
		return true;
	}
	@Override
	public Associate getAssociate(int associateId) throws SQLException {
		return  hibernateTemplate.get(Associate.class, associateId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Associate> getAssociates() throws SQLException {
		 return (List<Associate>) hibernateTemplate.find("from Associate a", Associate.class);
	}
	

}