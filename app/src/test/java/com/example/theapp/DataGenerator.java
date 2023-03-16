package com.example.theapp;

import org.springframework.boot.jdbc.DataSourceBuilder;

import java.sql.SQLException;
import java.util.UUID;

public class DataGenerator {

	public static void main(String[] args) throws SQLException {
		var dataSource = DataSourceBuilder.create()
				.url("jdbc:postgresql://localhost:5432/local_db")
				.username("user")
				.password("password")
				.build();

		try (
				var connection = dataSource.getConnection();
				var addressStatement = connection.prepareStatement("INSERT INTO ADDRESS (ID, STREET, CITY, ZIP_CODE) VALUES (?, ?, ?, ?)");
				var profileStatement = connection.prepareStatement("INSERT INTO CUSTOMER_PROFILE (ID, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS_ID) VALUES (?, ?, ?, ?, ?)")
		) {
			for (int i = 1; i < 1_000_001; i++) {

				var addressId = UUID.randomUUID().toString();
				addressStatement.setString(1, addressId);
				addressStatement.setString(2, "Milky Way Street 42");
				addressStatement.setString(3, "Munich");
				addressStatement.setString(4, "89000");
				addressStatement.addBatch();

				var profileId = UUID.randomUUID().toString();
				profileStatement.setString(1, profileId);
				profileStatement.setString(2, "Marvin");
				profileStatement.setString(3, "The Paranoid Android");
				profileStatement.setString(4, "marvin@the-heart-of-gold.org");
				profileStatement.setString(5, addressId);
				profileStatement.addBatch();

				if( i % 1000 == 0) {
					addressStatement.executeBatch();
					profileStatement.executeBatch();

					System.out.println(i);
				}
			}
		}
	}
}
