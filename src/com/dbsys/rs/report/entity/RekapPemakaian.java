package com.dbsys.rs.report.entity;

import javax.persistence.Entity;

@Entity
public class RekapPemakaian extends RekapTagihan {

	public void substract(Long jumlah) {
		Long jumlahPemakaian = getJumlah();
		jumlahPemakaian -= jumlah;
		
		setJumlah(jumlahPemakaian);
	}
}
