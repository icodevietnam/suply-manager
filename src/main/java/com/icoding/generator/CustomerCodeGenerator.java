package com.icoding.generator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomerCodeGenerator implements IdentifierGenerator
{
	private String defaultPrefix = "PE";
	private int defaultNumber = 1;

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String customerId = "";
		String digits = "";
		
		Connection connection = session.connection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT code from customer order by code desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs != null && rs.next()) {
				customerId = rs.getString("code");
				String prefix = customerId.substring(0,2);
				String str[] = customerId.split(prefix);
				digits = String.format("%06d", Integer.parseInt(str[1]) + 1);
				customerId = prefix.concat(digits);
			} else {
				digits = String.format("%06d", defaultNumber);
				customerId = defaultPrefix.concat(digits);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerId;
	}

}
